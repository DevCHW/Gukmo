package com.gukmo.board.hw.repository;

import java.util.Map;

import com.gukmo.board.model.MemberVO;

public interface InterLoginDAO {

	/**
	 * 회원 아이디,비밀번호 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 아이디,비밀번호를 맞게 입력하였다면 true, 아니라면 false 반환
	 */
	boolean userExistCheck(Map<String,String> paraMap);

	/**
	 * 관리자 로그인 검사
	 * @param 유저가 입력한 관리자아이디, 유저가 입력한 비밀번호
	 * @return 관리자 아이디,비밀번호를 맞게 입력하였다면 true, 아니라면 false 반환
	 */
	boolean adminExistCheck(Map<String, String> paraMap);
	
	/**
	 * 로그인되어질 회원의 상태 체크하기(정지,휴면,승인여부,비밀번호 변경시점 3개월)
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 로그인 관련한 user의 정보
	 */
	MemberVO statusCheck(String userid);

	
	/**
	 * 마지막로그인이 몇일전인지 알아내기
	 * @param 유저아이디
	 * @return 현재날짜로부터 마지막로그인날짜가 몇일전인지 int형으로 반환
	 */
	int getLastLoginday(String userid);

	
	/**
	 * 유저의 상태를 휴면으로 바꾸기
	 * @param 유저아이디
	 */
	int editUserStatus_rest(String userid);

	
	/**
	 * 마지막 비밀변호 변경일이 몇일전인지 알아내기
	 * @param 유저아이디
	 * @return 현재날짜로부터 마지막비밀번호 변경날짜가 몇일전인지 int형으로 반환
	 */
	int getLastUpdateDay(String userid);

	
	/**
	 * 유저 아이디를 통하여 유저 한명에 관련한 정보를 찾기
	 * @param 유저아이디
	 * @return MemberVO 타입 객체
	 */
	MemberVO getUser(String userid);


	/**
	 * 로그인 기록테이블에 로그인 기록하기
	 * @param 유저아이디, 클라이언트 ip
	 */
	void loginRecordSave(Map<String, String> paraMap);

	void adminLoginRecordSave(Map<String, String> paraMap);


	

	

}
