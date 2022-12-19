package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

public interface InterBugDAO {
	
	/**
	 * 버그제보리스트 받아오기
	 */
	List<Map<String, String>> getBugList();
	/**
	 * 버그 상세내용 하나 불러오기
	 */
	Map<String, String> getBug(String bugNum);
	
	/**
	 * 버그 삭제하기
	 */
	boolean delete(String bugNum);

}
