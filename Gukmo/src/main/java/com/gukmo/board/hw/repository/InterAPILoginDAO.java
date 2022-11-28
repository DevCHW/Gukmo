package com.gukmo.board.hw.repository;

import java.util.Map;

import com.gukmo.board.model.MemberVO;

public interface InterAPILoginDAO {

	
	/**
	 * 카카오연동회원인지 체크하기
	 */
	Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap);

	
	/**
	 * 카카오연동유저로 바꾸기(update)
	 */
	void setKakaoConnection(Map<String, Object> paramMap);

	
	/**
	 * 닉네임 중복검사하기
	 * @return 닉네임이 존재하면 1, 존재하지 않는다면 0
	 */
	int nicknameDuplicateCheck(String nickname);

	
	/**
	 * 카카오로그인일 경우 회원가입시키기
	 * @return 회원가입 성공 1 실패 0 반환
	 */
	int userKakaoRegisterPro(Map<String, Object> paramMap);


	/**
	 * 이메일 값으로 유저아이디 알아내기
	 */
	String getUserid(String email);

}
