package com.gukmo.board.hw.repository;

import java.util.Map;

import com.gukmo.board.model.MemberVO;

public interface InterLoginDAO {

	/**
	 * 로그인할 유저가 존재하는지 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 유저가 존재하면 true, 유저가 존재하지않는다면 false를 반환한다.
	 */
	boolean userExistCheck(Map<String,String> paraMap);

	
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
	 * @param userid
	 * @return 현재날짜로부터 마지막비밀번호 변경날짜가 몇일전인지 int형으로 반환
	 */
	int getLastUpdateDay(String userid);

	

}
