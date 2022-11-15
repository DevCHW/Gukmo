package com.gukmo.board.hw.repository;

import java.util.Map;

public interface InterLoginDAO {

	/**
	 * 로그인할 유저가 존재하는지 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 유저가 존재하면 true, 유저가 존재하지않는다면 false를 반환한다.
	 */
	boolean userExistCheck(Map<String,String> paraMap);

}
