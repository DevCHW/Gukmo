package com.gukmo.board.hw.repository;

import java.util.Map;

public interface InterSearchDAO {

	/**
	 * 검색어로 검색한 결과물의 갯수 알아오기
	 * @param 검색어
	 * @return 검색결과 수
	 */
	int getResultByKeyword(Map<String,String> paraMap);

	/**
	 * 
	 * @param paraMap
	 */
	int saveKeyWord(Map<String, String> paraMap);

	/**
	 * 
	 * @param string
	 * @return
	 */
	String getUseridWithIp(String search_ip);





}
