package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementDAO implements InterAdvertisementDAO{

	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	/**
	 * 광고내역 총 수 구하기
	 */
	@Override
	public int getTotalCntAdvertisement(Map<String, String> paraMap) {
		int totalCnt = gukmo_sql.selectOne("chw.getTotalCntAdvertisement", paraMap);
		return totalCnt;
	}

	/**
	 * 페이징처리한 광고내역 가지고오기
	 */
	@Override
	public List<Map<String, String>> getAdvertisementList(Map<String, String> paraMap) {
		List<Map<String, String>> data = gukmo_sql.selectList("chw.getAdvertisementList", paraMap);
		return data;
	}

	
	
}
