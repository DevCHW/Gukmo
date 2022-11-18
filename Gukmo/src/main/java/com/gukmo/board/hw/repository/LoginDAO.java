package com.gukmo.board.hw.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.MemberVO;

@Repository
public class LoginDAO implements InterLoginDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	/**
	 * 로그인할 유저가 존재하는지 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호가 들어있는 Map
	 * @return 유저가 존재하면 true, 유저가 존재하지않는다면 false를 반환한다.
	 */
	@Override
	public boolean userExistCheck(Map<String,String> paraMap) {
		int user_cnt = gukmo_sql.selectOne("chw.userExistCheck",paraMap);
		boolean userExist = false;
		if(user_cnt == 1) {	//해당유저 갯수가 1 이라면
			userExist = true;
		}
		else if(user_cnt == 0){	//해당 유저 갯수가 0 이라면
			userExist = false;
		}
		return userExist;
	}
	
	
	/**
	 * 관리자 로그인 검사
	 * @param 유저가 입력한 관리자아이디, 유저가 입력한 비밀번호
	 * @return 관리자 아이디,비밀번호를 맞게 입력하였다면 true, 아니라면 false 반환
	 */
	@Override
	public boolean adminExistCheck(Map<String, String> paraMap) {
		int admin_cnt = gukmo_sql.selectOne("chw.adminExistCheck",paraMap);
		boolean userExist = false;
		if(admin_cnt == 1) {	//해당유저 갯수가 1 이라면
			userExist = true;
		}
		else if(admin_cnt == 0){	//해당 유저 갯수가 0 이라면
			userExist = false;
		}
		return userExist;
	}

	
	/**
	 * 로그인되어질 회원의 상태 체크하기(정지,휴면,승인여부,비밀번호 변경시점 3개월)
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 로그인관련한 회원정보
	 */
	@Override
	public MemberVO statusCheck(String userid) {
		MemberVO user = gukmo_sql.selectOne("chw.statusCheck",userid);
		return user;
	}


	
	/**
	 * 마지막로그인이 몇일전인지 알아내기
	 * @param 유저아이디
	 * @return 현재날짜-마지막로그인날짜를 하여 마지막로그인날짜가 몇일전인지 반환한다.
	 */
	@Override
	public int getLastLoginday(String userid) {
		try {
			int lastLoginday = gukmo_sql.selectOne("chw.getLastLoginday",userid);
			return lastLoginday;
		}catch(NullPointerException e) {	//로그인을 한번도 하지 않았다면
			return 0;
		}
	}


	/**
	 * 유저의 상태를 휴면으로 업데이트해주기
	 * @param 유저아이디
	 */
	@Override
	public int editUserStatus_rest(String userid) {
		int n = gukmo_sql.update("chw.editUserStatus_rest", userid);
		return n;
	}


	/**
	 * 마지막 비밀변호 변경일이 몇일전인지 알아내기
	 * @param userid
	 * @return 현재날짜로부터 마지막비밀번호 변경날짜가 몇일전인지 int형으로 반환
	 */
	@Override
	public int getLastUpdateDay(String userid) {
		int lastUpdateDay = gukmo_sql.selectOne("chw.getLastUpdateDay",userid);
		return lastUpdateDay;
	}


	/**
	 * 유저 아이디를 통하여 유저 한명에 관련한 정보를 찾기
	 * @param 유저아이디
	 * @return MemberVO 타입 객체
	 */
	@Override
	public MemberVO getUser(String userid) {
		MemberVO user = gukmo_sql.selectOne("chw.getUser",userid);
		return user;
	}


	/**
	 * 로그인 기록테이블에 로그인 기록하기
	 * @param 유저아이디, 클라이언트 ip
	 */
	@Override
	public void loginRecordSave(Map<String, String> paraMap) {
		gukmo_sql.insert("chw.loginRecordSave",paraMap);
	}

	/**
	 * 관리자로그인 기록테이블에 로그인 기록하기
	 * @param 관리자아이디, 클라이언트 ip
	 */
	@Override
	public void adminLoginRecordSave(Map<String, String> paraMap) {
		gukmo_sql.insert("chw.adminloginRecordSave",paraMap);
		
	}


	


	
}
