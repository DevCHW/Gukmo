package com.gukmo.board.hw.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.MemberVO;

@Repository
public class APILoginDAO implements InterAPILoginDAO{
	
	@Resource
	private SqlSessionTemplate gukmo_sql;

	/**
	 * 카카오로 로그인 체크하기
	 */
	@Override
	public Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap) {
		Map<String, Object> kakaoConnectionCheck = gukmo_sql.selectOne("chw.kakaoConnectionCheck",paramMap);
		return kakaoConnectionCheck;
	}

	/**
	 * 카카오연동유저로 바꾸기(update)
	 */
	@Override
	public void setKakaoConnection(Map<String, Object> paramMap) {
		int result = gukmo_sql.update("chw.setKakaoConnection",paramMap);
		if(result == 1) {	//업데이트에 성공하였다면
			return;
		} else {	//업데이트에 실패하였다면(임시로 일부러 오류낸거임)
			try {
				Integer.parseInt("카카오로그인으로 업데이트가 실패하였습니다 다시 시도해주세요");
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}//end of try-catch--
		}
	}

	/**
	 * 닉네임 중복검사하기
	 * @return 닉네임이 존재하면 true, 존재하지 않는다면 false
	 */
	@Override
	public int nicknameDuplicateCheck(String nickname) {
		int nickname_cnt1 = gukmo_sql.selectOne("chw.nicknameExistCheck1",nickname);	//일반회원
		int nickname_cnt2 = gukmo_sql.selectOne("chw.nicknameExistCheck2",nickname);	//교육기관회원
		
		return nickname_cnt1+nickname_cnt2;
	}

	
	/**
	 * 카카오로그인일 경우 회원가입시키기
	 * @return 회원가입 성공 1 실패 0 반환
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int userKakaoRegisterPro(Map<String, Object> paramMap) {
		int result1 = gukmo_sql.insert("chw.social_insert_member_login",paramMap);
		int result2 = gukmo_sql.insert("chw.social_insert_member",paramMap);
		return result1*result2;
	}

	
	/**
	 * 이메일 값으로 유저아이디 알아내기
	 */
	@Override
	public String getUserid(String email) {
		String userid = gukmo_sql.selectOne("chw.getUserid",email);
		return userid;
	}


}
