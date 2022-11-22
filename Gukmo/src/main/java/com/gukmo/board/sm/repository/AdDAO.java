package com.gukmo.board.sm.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.MemberVO;

@Repository
public class AdDAO implements InterAdDAO {
	
	@Resource
	private SqlSessionTemplate gukmo_sql;

	@Override
	public int getTotalCount_ad(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount_ad", paraMap);
		return n;
	}

	// 광고현황 리스트 불러오기
	@Override
	public List<AdVO> adList(Map<String, String> paraMap) {
		List<AdVO> adList = gukmo_sql.selectList("ksm.adList" ,paraMap);
		return adList;
	}

	// 광고 관련 정보 상세보기 
	@Override
	public AdVO adDetail(Map<String, String> paraMap) {
		AdVO adDetail = gukmo_sql.selectOne("ksm.getAdDetail", paraMap);
		return adDetail;
	}

}
