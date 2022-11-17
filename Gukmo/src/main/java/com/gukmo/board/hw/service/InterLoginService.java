package com.gukmo.board.hw.service;

import java.util.Map;

import com.gukmo.board.model.MemberVO;

public interface InterLoginService {
	
	/**
	 * 로그인되어질 회원의 상태 체크하기(활동,정지,휴면,대기,비밀번호 변경시점 3개월)
	 * @param 유저가 입력한 아이디
	 * @return 회원의 상태에 따라 활동,정지,휴면,대기,비밀번호 변경 권장  return
	 */
	String statusCheck(String userid);

	/**
	 * 회원 아이디,비밀번호 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 아이디,비밀번호를 맞게 입력하였다면 true, 아니라면 false 반환
	 */
	boolean userExistCheck(Map<String, String> paraMap);

	/**
	 * 관리자 로그인 검사
	 * @param 유저가 입력한 관리자아이디, 유저가 입력한 비밀번호
	 * @return 관리자 아이디,비밀번호를 맞게 입력하였다면 true, 아니라면 false 반환
	 */
	boolean adminExistCheck(Map<String, String> paraMap);
	
	/**
	 * 로그인 완료처리하기
	 * @param 유저아이디,클라이언트 ip(userid,client_ip)
	 * @return MemberVO 타입 객체
	 */
	MemberVO login_complete(Map<String, String> paraMap);

	
	
	
	
}
