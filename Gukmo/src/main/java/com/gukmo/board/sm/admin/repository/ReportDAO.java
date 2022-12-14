package com.gukmo.board.sm.admin.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.ReportVO;

@Repository
public class ReportDAO implements InterReportDAO{
	
	
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	// 전체 신고 총페이지수 불러오기
	@Override
	public int getTotalCount_report(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount_report", paraMap);
		return n;
	}

	// 신고리스트 불러오기
	@Override
	public List<ReportVO> reportList(Map<String, String> paraMap) {
		List<ReportVO> reportList = gukmo_sql.selectList("ksm.reportList" ,paraMap);
		return reportList;
	}

	// 신고내역 상세보기
	@Override
	public ReportVO reportDetail(Map<String, String> paraMap) {
		ReportVO reportDetail = gukmo_sql.selectOne("ksm.getReportDetail", paraMap);
		return reportDetail;
	}

	/**
	 * 피신고자 닉네임으로 회원아이디 얻기
	 */
	@Override
	public String getReportedId(String reportedNickname) {
		String ReportedId = gukmo_sql.selectOne("ksm.getReportedId", reportedNickname);
		return ReportedId;
	}

	// (신고 전) 대한 총 페이지 수 알아오기
	@Override
	public int getTotalCount_report_before(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount_report_before", paraMap);
		return n;
	}

	// (신고 전) 리스트 알아오기
	@Override
	public List<ReportVO> reportList_before(Map<String, String> paraMap) {
		List<ReportVO> reportList = gukmo_sql.selectList("ksm.reportList_before" ,paraMap);
		return reportList;
	}

	// (신고 후) 총페이지수 알아오기
	@Override
	public int getTotalCount_report_after(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount_report_after", paraMap);
		return n;
	}
	
	// (신고 후) 리스트 알아오기
	@Override
	public List<ReportVO> reportList_after(Map<String, String> paraMap) {
		List<ReportVO> reportList = gukmo_sql.selectList("ksm.reportList_after" ,paraMap);
		return reportList;
	}

	// 신고한 자가 신고한 건수 뽑기
	@Override
	public List<Integer> getreport_cnt(Map<String, String> paraMap) {
		int result1 = 0;
		int result2 = 0;
		List<Integer> nList = new ArrayList<>();
		
		result1 = gukmo_sql.selectOne("ksm.getreport_cnt_board", paraMap);
		nList.add(result1);
		
		result2 = gukmo_sql.selectOne("ksm.getreport_cnt_comment", paraMap);
		nList.add(result2);
		return nList;
	}

	// 신고받은 자가 신고한 건수 뽑기
	@Override
	public List<Integer> getreported_cnt(Map<String, String> paraMap) {
		int result1 = 0;
		int result2 = 0;
		List<Integer> nList = new ArrayList<>();
		
		result1 = gukmo_sql.selectOne("ksm.getreported_cnt_board", paraMap);
		nList.add(result1);
		
		result2 = gukmo_sql.selectOne("ksm.getreported_cnt_comment", paraMap);
		nList.add(result2);
		return nList;
	}

	// 신고자가 신고한 내역 뽑기
	@Override
	public List<ReportVO> getReport_List(Map<String, String> paraMap) {
		List<ReportVO> report_List = gukmo_sql.selectList("ksm.getReport_List", paraMap);
		return report_List;
	}

	// 피신고자가 신고당한 내역 뽑기
	@Override
	public List<ReportVO> getReported_List(Map<String, String> paraMap) {
		List<ReportVO> reported_List = gukmo_sql.selectList("ksm.getReported_List", paraMap);
		return reported_List;
	}

	

}
