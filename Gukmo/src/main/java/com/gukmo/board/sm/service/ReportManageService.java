package com.gukmo.board.sm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.ReportVO;
import com.gukmo.board.sm.repository.InterReportDAO;

@Service
public class ReportManageService implements InterReportManageService {
	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterReportDAO dao;
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.board.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
    // 그러므로 dao 는 null 이 아니다.

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
