package com.gukmo.board.hw.admin.service;

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

	/**
	 * 신고내역 접수하기(게시글)
	 */
	boolean receiptReportBoard(String report_num);

	/**
	 * 신고내역 접수하기(댓글)
	 */
	boolean receiptReportComment(String report_num);

	/**
	 * 피신고자가 이미 정지회원인지 체크하기
	 */
	String memberStatusCheck(String nickname);

	/**
	 * 피신고자가 이미 정지등록하기
	 */
	boolean penaltyRegister(Map<String, String> paraMap);

}
