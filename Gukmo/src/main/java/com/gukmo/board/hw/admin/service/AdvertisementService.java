package com.gukmo.board.hw.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.admin.repository.InterAdvertisementDAO;
import com.gukmo.board.model.AdVO;

@Service
public class AdvertisementService implements InterAdvertisementService{
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdvertisementDAO dao;
	
	
	
	
	// 광고현황 리스트 총 페이지수 알아오기
	@Override
	public int getTotalCount_ad(Map<String, String> paraMap) {
		int n = dao.getTotalCount_ad(paraMap);
		return n;
	}

	// 광고리스트 불러오기
	@Override
	public List<AdVO> adList(Map<String, String> paraMap) {
		List<AdVO> adList = dao.adList(paraMap);
		// System.out.println(memberList);
		return adList;
	}

	// 광고 관련 정보 상세보기 
	@Override
	public AdVO getAdDetail(Map<String, String> paraMap) {
		AdVO adDetail = dao.adDetail(paraMap);
		return adDetail;
	}

	// 광고 등록
	@Override
	public int addAd(AdVO advo) {
		int n = dao.addAd(advo);
		return n;
	}

}