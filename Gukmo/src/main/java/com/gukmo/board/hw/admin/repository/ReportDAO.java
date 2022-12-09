package com.gukmo.board.hw.admin.repository;

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

	@Override
	public String getReportedId(String reportedNickname) {
		String ReportedId = gukmo_sql.selectOne("ksm.getReportedId", reportedNickname);
		return ReportedId;
	}
}
