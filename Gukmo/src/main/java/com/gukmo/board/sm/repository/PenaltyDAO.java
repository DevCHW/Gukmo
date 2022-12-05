package com.gukmo.board.sm.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

@Repository
public class PenaltyDAO implements InterPenaltyDAO {

	@Resource
	private SqlSessionTemplate gukmo_sql;

	// 정지회원 총 페이지수 알아오기
	@Override
	public int getTotalPenaltyCount(Map<String, String> paraMap) {
		int totalPenaltyCount = gukmo_sql.selectOne("ksm.getTotalCount_penalty", paraMap);
		return totalPenaltyCount;
	}

	// 정지회원 리스트 뽑아오기
	@Override
	public List<PenaltyVO> getPenaltyList(Map<String, String> paraMap) {
		List<PenaltyVO> penaltyList = gukmo_sql.selectList("ksm.getPenaltyList" ,paraMap);
		return penaltyList;
	}

	// 정지내역 상세보기
	@Override
	public PenaltyVO getPenaltyDetail(Map<String, String> paraMap) {
		PenaltyVO penaltyDetail = gukmo_sql.selectOne("ksm.getPenaltyDetail", paraMap);
		return penaltyDetail;
	}

	// 정지 관련 정보 상세보기 
	@Override
	public String getPenaltyId(Map<String, String> paraMap) {
		String id = gukmo_sql.selectOne("ksm.getPenaltyId", paraMap);
		return id;
	}

	// 정지 테이블에서 해당 회원 삭제
	@Override
	public int del_penalty(Map<String, String> paraMap) {
		int n = gukmo_sql.delete("ksm.del_penalty", paraMap);
		return n;
	}
	

	
}
