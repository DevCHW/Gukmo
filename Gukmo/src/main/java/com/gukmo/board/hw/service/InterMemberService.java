package com.gukmo.board.hw.service;

import com.gukmo.board.model.MemberVO;

public interface InterMemberService {

	/**
	 * 일반회원 가입하기
	 * @param 사용자가 입력한 정보가 들어있는 MemberVO 객체
	 */
	void saveNormalMember(MemberVO input_member) throws Throwable;

}
