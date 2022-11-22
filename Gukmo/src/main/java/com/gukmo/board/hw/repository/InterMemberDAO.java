package com.gukmo.board.hw.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;

public interface InterMemberDAO {

	/**
	 * 회원이 존재하는지 여부 검사
	 * @return 회원이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean idExistCheck(String userid);

	
	/**
	 * 가입된 닉네임이 존재하는지 여부 검사
	 * @return 가입된 닉네임이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean nicknameExistCheck(String nickname);

	
	/**
	 * 가입된 이메일이 존재하는지 여부 검사
	 * @return 가입된 이메일이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean emailExistCheck(String email);


	/**
	 * tbl_member_login에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	int insert_member_login(MemberVO member);
	
	
	/**
	 * tbl_member에 insert하기
	 * @param 유저가 입력한 회원정보가 들어있는 MemberVO 객체
	 * @return 쿼리문 성공시 1 실패시 0
	 */
	int insert_member(MemberVO member);


	/**
	 * 계정삭제하기
	 */
	int memberDelete(String userid);


	/**
	 * 로그인되어있는 유저의 활동내역 리스트 얻기
	 * @param userid
	 * @return 활동내역 리스트
	 */
	List<ActivityVO> getActivities(String userid);


	
	/**
	 * 유저의 활동내역 총 갯수를 알아오기
	 * @param 검색어,유저아이디
	 * @return 활동내역 총 갯수
	 */
	int getTotalActivities(Map<String, String> paraMap);


	/**
	 * 이메일 값으로 회원 아이디 알아내기
	 * @param email
	 * @return
	 */
	String getMyID(String email);

	

}
