package com.gukmo.board.hw.repository;

public interface InterMemberDAO {

	/**
	 * 회원이 존재하는지 여부 검사
	 * @return 회원이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	boolean idExistCheck(String userid);

}
