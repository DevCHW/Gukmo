package com.gukmo.board.hw.service;

import java.util.Map;

public interface InterLoginService {
	
	/**
	 * 로그인되어질 회원의 상태 체크하기(활동,정지,휴면,대기,비밀번호 변경시점 3개월)
	 * @param 유저가 입력한 아이디
	 * @return 회원의 상태에 따라 활동,정지,휴면,대기,비밀번호 변경 권장  return
	 */
	String statusCheck(String userid);
	
}
