package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.ReportVO;

public interface InterReportDAO {
	
	// 총 페이지수 알아오기
	int getTotalCount_report(Map<String, String> paraMap);

	// 신고리스트 내역 불러오기
	List<ReportVO> reportList(Map<String, String> paraMap);

	// 신고내역 상세보기
	ReportVO reportDetail(Map<String, String> paraMap);

	// 신고받은 자 아이디 값 불러오기
	String getReportedId(String reportedNickname);
}
