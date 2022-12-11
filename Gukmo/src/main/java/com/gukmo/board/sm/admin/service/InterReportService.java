package com.gukmo.board.sm.admin.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.ReportVO;

public interface InterReportService {
	
	
	// 총 페이지 수 알아오기
	int getTotalCount_report(Map<String, String> paraMap);

	// 신고내역 리스트 불러오기
	List<ReportVO> reportList(Map<String, String> paraMap);

	// 신고 내역 상세보기
	ReportVO getreportDetail(Map<String, String> paraMap);
	
	// 신고받은 자 아이디값 알아오기
	String getReportedId(String reportedNickname);

}
