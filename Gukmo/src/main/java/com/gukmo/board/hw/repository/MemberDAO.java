package com.gukmo.board.hw.repository;

import java.util.List;
import java.util.Map;

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
		int nickname_cnt = gukmo_sql.selectOne("chw.nicknameExistCheck",nickname);	//일반회원
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
	 * 가입된 교육기관명이 존재하는지 여부 검사
	 * @return 가입된 이메일이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@Override
	public boolean academyNameExistCheck(String academyName) {
		int academyNameCnt = gukmo_sql.selectOne("chw.academyNameExistCheck",academyName);
		boolean academyNameExist = false;
		if(academyNameCnt > 0) {	//해당이메일이 존재한다면
			academyNameExist = true;
		}
		else {				//해당이메일이 존재하지 않는다면
			academyNameExist = false;
		}
		
		return academyNameExist;
	}
	
	
	
	
	
	
	
	
	/**
	 * tbl_member_login에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	@Override
	public int insert_member_login(Map<String,String> paraMap) {
		int result = gukmo_sql.insert("chw.insert_member_login",paraMap);
		return result;
	}
	

	/**
	 * tbl_member에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	@Override
	public int insert_member(Map<String,String> paraMap) {
		int result = gukmo_sql.insert("chw.insert_member",paraMap);
		return result;
	}
	
	
	@Override
	public int insert_academy_member(Map<String, String> paraMap) {
		int result = gukmo_sql.insert("chw.insert_academy_member",paraMap);
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
	public List<ActivityVO> getActivities(Map<String, String> paraMap) {
		List<ActivityVO> activities = gukmo_sql.selectList("chw.getActivities",paraMap);
		return activities;
	}


	
	
	/**
	 * 유저의 활동내역 총 갯수를 알아오기
	 * @param 검색어,유저아이디
	 * @return 활동내역 총 갯수
	 */
	@Override
	public int getTotalActivities(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalActivities",paraMap);
		return totalCount;
	}



	/**
	 * 이메일 값으로 회원 아이디 알아내기
	 * @param email
	 * @return
	 */
	@Override
	public String getMyID(String email) {
		String userid = gukmo_sql.selectOne("chw.getMyID",email);
		return userid;
	}



	/**
	 * 계정찾기 비밀번호 변경 해주기
	 * @param email,passwd 인풋값
	 * @return 성공여부 result
	 */
	@Override
	public int editPasswd(Map<String, String> paraMap) {
		int result = gukmo_sql.update("chw.editPasswd",paraMap);
		return result;
	}





	/**
	 * 프사첨부를 안했을경우 회원정보 수정
	 */
	@Override
	public int editMyInfoWithOutFile(MemberVO member) {
		int result = gukmo_sql.update("chw.editMyInfoWithOutFile",member);
		return result;
	}



	/**
	 * 프로필사진을 첨부했을 때 회원정보수정
	 */
	@Override
	public int editMyInfo(Map<String,String> paraMap) {
		int result = gukmo_sql.update("chw.editMyInfo",paraMap);
		return result;
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



	
	
	
	@Override
	public ActivityVO getActivitiesByBoard(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}



	



	





	
	
}
