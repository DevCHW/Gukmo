package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

public interface InterAdvertisementDAO {

	/**
	 * 광고내역 총 수 구하기
	 */
	int getTotalCntAdvertisement(Map<String, String> paraMap);

	/**
	 * 페이징처리한 광고내역 가지고오기
	 */
	List<Map<String, String>> getAdvertisementList(Map<String, String> paraMap);

}
