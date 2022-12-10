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

	/**
	 * 신고내역 접수하기(게시글)
	 */
	int receiptReportBoard(String report_num);

	/**
	 * 신고내역 접수하기(댓글)
	 */
	int receiptReportComment(String report_num);

	/**
	 * 피신고자가 이미 정지회원인지 체크하기
	 */
	String memberStatusCheck(String nickname);

	/**
	 * 정지내역에 등록하기
	 */
	int penaltyNew(Map<String, String> paraMap);

	/**
	 * 회원 정지로 바꿔주기
	 */
	int memberStatusChange(Map<String, String> paraMap);
}
