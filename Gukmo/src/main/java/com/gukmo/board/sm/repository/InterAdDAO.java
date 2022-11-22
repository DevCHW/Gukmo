package com.gukmo.board.sm.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.AdVO;

public interface InterAdDAO {

	// 광고현황 리스트 총 페이지수 알아오기
	int getTotalCount_ad(Map<String, String> paraMap);

	// 광고현황 리스트 불러오기
	List<AdVO> adList(Map<String, String> paraMap);

	// 광고 관련 정보 상세보기 
	AdVO adDetail(Map<String, String> paraMap);

}
