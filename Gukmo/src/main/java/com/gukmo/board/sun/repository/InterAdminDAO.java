package com.gukmo.board.sun.repository;

import java.util.List;
import java.util.Map;

public interface InterAdminDAO {

	// 기간별 새로운 게시물 수 
	List<Map<String, String>> newBoardCnt(Map<String, String> paraMap);

	// 특정 회원 활동내역 리스트 가져오기 
	List<Map<String, String>> activityList(Map<String, String> paraMap);

	// 특정 회원 일자별 활동내역 카운트 가져오기 
	List<Map<String, String>> activityCntList(Map<String, String> paraMap);

	// 특정 회원 연도별, 월별 활동내역 카운트 가져오기 
	List<Map<String, String>> activityCntListYearMonth(Map<String, String> paraMap);

	// 특정 회원 검색어 카운트
	List<Map<String, String>> searchCntList(String userid);


}
