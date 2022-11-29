package com.gukmo.board.hw.service;

import java.util.Map;

import com.gukmo.board.model.MemberVO;

public interface InterAPILoginService {

	
	Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap);
	
	/**
	 * 카카오연동유저로 바꾸기(update)
	 */
	void setKakaoConnection(Map<String, Object> paramMap);
	

	/**
	 * 닉네임 중복검사하기
	 * @return 닉네임이 존재하면 true, 존재하지 않는다면 false
	 */
	boolean nicknameExist(String nickname);
	
	/**
	 * 카카오로그인일 경우 회원가입시키기
	 * @return 회원가입 성공 true 실패 false 반환
	 */
	boolean userKakaoRegisterPro(Map<String, Object> paramMap);

	/**
	 * 구글로그인일 경우 회원가입시키기
	 * @return 회원가입 성공 true 실패 false 반환
	 */
	boolean userGoogleRegisterPro(Map<String, Object> paramMap);

	/**
	 * 네이버로그인일 경우 회원가입시키기
	 * @return 회원가입 성공 true 실패 false 반환
	 */
	boolean userNaverRegisterPro(Map<String, Object> paramMap);

	/**
	 * 이메일 값으로 유저아이디 알아내기
	 */
	String getUserid(String email);



}
