package com.gukmo.board.sun.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.ReportVO;

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

	// 특정 회원 일자별 로그인 카운트 가져오기 
	List<Map<String, String>> loginCntList(Map<String, String> paraMap);

	// 특정 회원 연도별, 월별 로그인 카운트 가져오기 
	List<Map<String, String>> loginCntListYearMonth(Map<String, String> paraMap);

	// 특정 회원 로그인 기록 가져오기
	List<Map<String, String>> loginRecordList(Map<String, String> paraMap);

	// 특정 회원 게시글 작성 목록 가져오기
	List<BoardVO> boardList(Map<String, String> paraMap);

	List<ReportVO> reportList(Map<String, String> paraMap);

	List<ReportVO> reportedList(Map<String, String> paraMap);
	//활동내역 갯수 구하기
	int activityListCnt(Map<String, String> paraMap);


}
