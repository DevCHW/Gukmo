package com.gukmo.board.hw.service;

import java.util.Map;

public interface InterSearchService {

	/**
	 * 검색어 저장시키기
	 * @param 검색어,아이피,로그인중인 유저아이디
	 */
	boolean saveKeyWord(Map<String,String> paraMap);

}
