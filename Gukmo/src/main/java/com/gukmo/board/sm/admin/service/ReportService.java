package com.gukmo.board.sm.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.ReportVO;
import com.gukmo.board.sm.admin.repository.InterReportDAO;

@Service
public class ReportService implements InterReportService{

	@Autowired
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
	
	
}
