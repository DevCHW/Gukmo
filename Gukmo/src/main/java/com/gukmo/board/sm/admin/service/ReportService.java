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

	// (신고 전) 총 페이지 수 알아오기
	@Override
	public int getTotalCount_report_before(Map<String, String> paraMap) {
		int n = dao.getTotalCount_report_before(paraMap);
		return n;
	}

	// (신고 전) 리스트 알아오기
	@Override
	public List<ReportVO> reportList_before(Map<String, String> paraMap) {
		List<ReportVO> reportList = dao.reportList_before(paraMap);
		return reportList;
	}

	// (신고 후) 총 페이지 수 알아오기
	@Override
	public int getTotalCount_report_after(Map<String, String> paraMap) {
		int n = dao.getTotalCount_report_after(paraMap);
		return n;
	}
	
	// (신고 후) 리스트 알아오기
	@Override
	public List<ReportVO> reportList_after(Map<String, String> paraMap) {
		List<ReportVO> reportList = dao.reportList_after(paraMap);
		return reportList;
	}

	// 신고한 자가 신고한 건수 뽑기
	@Override
	public List<Integer> getreport_cnt(Map<String, String> paraMap) {
		List<Integer> nList = dao.getreport_cnt(paraMap);									
		return nList;		
	}

	// 신고받은 자가 신고한 건수 뽑기
	@Override
	public List<Integer> getreported_cnt(Map<String, String> paraMap) {
		List<Integer> nList = dao.getreported_cnt(paraMap);									
		return nList;		
	}

	// 신고자가 신고한 내역 뽑기
	@Override
	public List<ReportVO> getReport_List(Map<String, String> paraMap) {
		List<ReportVO> report_List = dao.getReport_List(paraMap);		
		return report_List;
	}


	// 피신고자가 신고당한 내역 뽑기
	@Override
	public List<ReportVO> getReported_List(Map<String, String> paraMap) {
		List<ReportVO> reported_List = dao.getReported_List(paraMap);		
		return reported_List;
	}

	
}
