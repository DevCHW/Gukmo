package com.gukmo.board.hw.service;

import java.util.List;

import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;

public interface InterMemberService {

	/**
	 * 일반회원 가입하기
	 * @param 사용자가 입력한 정보가 들어있는 MemberVO 객체
	 */
	void saveNormalMember(MemberVO input_member) throws Throwable;


	/**
	 * 계정 삭제하기
	 * @param attribute
	 */
	int memberDelete(String userid);

	
	
	/**
	 * 로그인되어있는 유저의 활동내역 리스트 얻기
	 * @param userid
	 * @return 활동내역 리스트
	 */
	List<ActivityVO> getActivities(String userid);


}
