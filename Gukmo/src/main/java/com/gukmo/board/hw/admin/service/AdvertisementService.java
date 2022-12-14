package com.gukmo.board.hw.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.admin.repository.InterAdvertisementDAO;

@Service
public class AdvertisementService implements InterAdvertisementService{
	
	@Autowired
	private InterAdvertisementDAO dao;

	/**
	 * 광고내역 총 수 구하기
	 */
	@Override
	public int getTotalCntAdvertisement(Map<String, String> paraMap) {
		int totalCnt = dao.getTotalCntAdvertisement(paraMap);
		return totalCnt;
	}

	/**
	 * 페이징처리한 광고내역 가지고오기
	 */
	@Override
	public List<Map<String, String>> getAdvertisementList(Map<String, String> paraMap) {
		List<Map<String, String>> data = dao.getAdvertisementList(paraMap);
		return data;
	}
	
	
	
	
	
	
	
}
