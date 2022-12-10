package com.gukmo.board.hw.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.hw.admin.repository.InterReportDAO;
import com.gukmo.board.model.ReportVO;


@Service
public class ReportService implements InterReportService{
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterReportDAO dao;
	
	// 총 페이지수 알아오기
	@Override
	public int getTotalCount_report(Map<String, String> paraMap) {
		int n = dao.getTotalCount_report(paraMap);
		return n;
	}

	// 신고내역 리스트 불러오기
	@Override
	public List<ReportVO> reportList(Map<String, String> paraMap) {
		List<ReportVO> reportList = dao.reportList(paraMap);
		// System.out.println(memberList);
		return reportList;
	}

	@Override
	public ReportVO getreportDetail(Map<String, String> paraMap) {
		ReportVO reportDetail = dao.reportDetail(paraMap);
		return reportDetail;
		
	}

	// 신고받은 자 아이디값 알아오기
	@Override
	public String getReportedId(String reportedNickname) {
		String getReportedId = dao.getReportedId(reportedNickname);
		return getReportedId;
	}

	/**
	 * 신고내역 접수하기(게시글)
	 */
	@Override
	public boolean receiptReportBoard(String report_num) {
		int result = dao.receiptReportBoard(report_num);
		boolean success = false;
		success = result == 1?true:false;
		return success;
	}

	/**
	 * 신고내역 접수하기(댓글)
	 */
	@Override
	public boolean receiptReportComment(String report_num) {
		int result = dao.receiptReportComment(report_num);
		boolean success = false;
		success = result == 1?true:false;
		return success;
	}

	/**
	 * 피신고자가 이미 정지회원인지 체크하기
	 */
	@Override
	public String memberStatusCheck(String nickname) {
		String status = dao.memberStatusCheck(nickname);
		return status;
	}

	/**
	 * 피신고자  정지등록하기
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public boolean penaltyRegister(Map<String, String> paraMap) {
		int result1 = dao.penaltyNew(paraMap);
		int result2 = dao.memberStatusChange(paraMap);
		
		boolean success = result1*result2 == 1?true:false;
		return success;
	}
	
	
	
}
