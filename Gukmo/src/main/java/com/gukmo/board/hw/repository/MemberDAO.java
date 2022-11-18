package com.gukmo.board.hw.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;


@Repository
public class MemberDAO implements InterMemberDAO{

	@Resource
	private SqlSessionTemplate gukmo_sql;

	/**
	 * 가입된 회원이 존재하는지 여부 검사
	 * @return 회원이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@Override
	public boolean idExistCheck(String userid) {
		int userid_cnt = gukmo_sql.selectOne("chw.idExistCheck",userid);
		boolean idExist = false;
		if(userid_cnt > 0) {	//해당유저아이디가 존재한다면
			idExist = true;
		}
		else {					//해당유저아이디가 존재하지 않는다면
			idExist = false;
		}
		
		return idExist;
	}

	
	
	/**
	 * 가입된 닉네임이 존재하는지 여부 검사
	 * @return 가입된 닉네임이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@Override
	public boolean nicknameExistCheck(String nickname) {
		int nickname_cnt = gukmo_sql.selectOne("chw.nicknameExistCheck",nickname);
		boolean nicknameExist = false;
		if(nickname_cnt > 0) {	//해당닉네임이 존재한다면
			nicknameExist = true;
		}
		else {					//해당닉네임이 존재하지 않는다면
			nicknameExist = false;
		}
		return nicknameExist;
	}

	
	
	
	
	
	/**
	 * 가입된 이메일이 존재하는지 여부 검사
	 * @return 가입된 이메일이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@Override
	public boolean emailExistCheck(String email) {
		int email_cnt = gukmo_sql.selectOne("chw.emailExistCheck",email);
		boolean emailExist = false;
		if(email_cnt > 0) {	//해당이메일이 존재한다면
			emailExist = true;
		}
		else {				//해당이메일이 존재하지 않는다면
			emailExist = false;
		}
		
		return emailExist;
	}
	
	
	/**
	 * tbl_member_login에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	@Override
	public int insert_member_login(MemberVO member) {
		int result = gukmo_sql.insert("chw.insert_member_login",member);
		return result;
	}
	

	/**
	 * tbl_member에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	@Override
	public int insert_member(MemberVO member) {
		int result = gukmo_sql.insert("chw.insert_member",member);
		return result;
	}


	/**
	 * 계정삭제하기
	 */
	@Override
	public int memberDelete(String userid) {
		int result = gukmo_sql.delete("chw.delete_member",userid);
		return result;
	}



	/**
	 * 로그인되어있는 유저의 활동내역 리스트 얻기
	 * @param userid
	 * @return 활동내역 리스트
	 */
	@Override
	public List<ActivityVO> getActivities(String userid) {
		List<ActivityVO> activities = gukmo_sql.selectList("chw.getActivities",userid);
		return activities;
	}
	
	
}
