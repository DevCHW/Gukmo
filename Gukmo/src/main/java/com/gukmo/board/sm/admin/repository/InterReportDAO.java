package com.gukmo.board.sm.admin.repository;

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

	// 전체 신고에 대한 총 페이지 수 알아오기
	int getTotalCount_report_before(Map<String, String> paraMap);

	// (신고 전) 리스트 알아오기
	List<ReportVO> reportList_before(Map<String, String> paraMap);

	// (신고 후) 총 페이지 수 알아오기
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
