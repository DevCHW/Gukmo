package com.gukmo.board.sm.admin.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.ReportVO;

public interface InterReportService {
	
	
	// 전체 신고에 대한 총 페이지 수 알아오기
	int getTotalCount_report(Map<String, String> paraMap);

	// 신고내역 리스트 불러오기
	List<ReportVO> reportList(Map<String, String> paraMap);

	// 신고 내역 상세보기
	ReportVO getreportDetail(Map<String, String> paraMap);
	
	// 신고받은 자 아이디값 알아오기
	String getReportedId(String reportedNickname);

	// 신고 전 총 페이지 수 알아오기
	int getTotalCount_report_before(Map<String, String> paraMap);

	// (신고 전) 리스트 알아오기
	List<ReportVO> reportList_before(Map<String, String> paraMap);

	// 신고 후 총 페이지 수 알아오기
	int getTotalCount_report_after(Map<String, String> paraMap);

	// (신고 후) 리스트 알아오기
	List<ReportVO> reportList_after(Map<String, String> paraMap);

	// 신고한 자가 신고한 건수 뽑기
	List<Integer> getreport_cnt(Map<String, String> paraMap);

	// 신고받은 자가 신고한 건수 뽑기
	List<Integer> getreported_cnt(Map<String, String> paraMap);

	// 신고자가 신고한 내역 뽑기
	List<ReportVO> getReport_List(Map<String, String> paraMap);

	// 피신고자가 신고당한 내역 뽑기
	List<ReportVO> getReported_List(Map<String, String> paraMap);


}
