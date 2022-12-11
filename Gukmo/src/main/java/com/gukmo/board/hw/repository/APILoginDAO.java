package com.gukmo.board.hw.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
				Integer.parseInt("오류");
			} catch(NumberFormatException e) {
				System.out.println("카카오로그인 실패하였습니다. DB를 확인해주세요");
			}//end of try-catch--
		}
	}
	
	
	/**
	 * 네이버연동유저로 바꾸기(update)
	 */
	@Override
	public void setNaverConnection(Map<String, Object> apiJson) {
		int result = gukmo_sql.update("chw.setNaverConnection",apiJson);
		if(result == 1) {	//업데이트에 성공하였다면
			return;
		} else {	//업데이트에 실패하였다면(임시로 일부러 오류낸거임)
			try {
				Integer.parseInt("오류");
			} catch(NumberFormatException e) {
				System.out.println("네이버로그인 실패하였습니다. DB를 확인해주세요");
			}//end of try-catch--
		}
	}
	
	
	
	
	/**
	 * 구글연동유저로 바꾸기(update)
	 */
	@Override
	public void setGoogleConnection(Map<String, Object> paramMap) {
		int result = gukmo_sql.update("chw.setGoogleConnection",paramMap);
		if(result == 1) {	//업데이트에 성공하였다면
			return;
		} else {	//업데이트에 실패하였다면(임시로 일부러 오류낸거임)
			try {
				Integer.parseInt("오류");
			} catch(NumberFormatException e) {
				System.out.println("구글로그인 실패하였습니다. DB를 확인해주세요");
			}//end of try-catch--
		}
	}
	
	/**
	 * 페이스북연동유저로 바꾸기(update)
	 */
	@Override
	public void setFacebookConnection(Map<String, Object> paramMap) {
		int result = gukmo_sql.update("chw.setFacebookConnection",paramMap);
		if(result == 1) {	//업데이트에 성공하였다면
			return;
		} else {	//업데이트에 실패하였다면(임시로 일부러 오류낸거임)
			try {
				Integer.parseInt("오류");
			} catch(NumberFormatException e) {
				System.out.println("페이스북로그인 실패하였습니다. DB를 확인해주세요");
			}//end of try-catch--
		}
	}


	
	
	
	/**
	 * 닉네임 중복검사하기
	 * @return 닉네임이 존재하면 true, 존재하지 않는다면 false
	 */
	@Override
	public int nicknameDuplicateCheck(String nickname) {
		int nickname_cnt = gukmo_sql.selectOne("chw.nicknameExistCheck",nickname);	//일반회원
		
		return nickname_cnt;
	}

	
	/**
	 * 카카오로그인,네이버로그인,구글로그인일 경우 회원가입시키기
	 * @return 회원가입 성공 1 실패 0 반환
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int userRegisterPro(Map<String, Object> paramMap) {
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

	
	/**
	 * 회원정보얻기
	 * @param 회원아이디
	 * @return 회원정보
	 */
	@Override
	public MemberVO getUser(String userid) {
		MemberVO user = gukmo_sql.selectOne("chw.getUser",userid);
		return user;
	}

	

	
	

	


}
