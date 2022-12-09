package com.gukmo.board.sun.repository;

import java.util.List;
import java.util.Map;

public interface InterAdminDAO {

	// 기간별 새로운 게시물 수 
	List<Map<String, String>> newBoardCnt(Map<String, String> paraMap);

}
