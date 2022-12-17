package com.gukmo.board.sm.admin.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.AdVO;

public interface InterAdvertisementService {

	// 광고현황 리스트 총 페이지 수 알아오기
	int getTotalCount_ad(Map<String, String> paraMap);

	// 광고현황 리스트 불러오기
	List<AdVO> adList(Map<String, String> paraMap);

	// 광고 관련 정보 상세보기 
	AdVO getAdDetail(Map<String, String> paraMap);

	// 광고 등록
	int addAd(AdVO advo);

	// 광고 날짜 변경시 tbl_advertisement 에서 날짜 변경
	int edit_ad(Map<String, String> paraMap);

	// 광고 일정 캘린더에 광고 일정 박기(ajax)
	List<AdVO> getAdList();
}
