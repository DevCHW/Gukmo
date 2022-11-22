package com.gukmo.board.sm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.sm.repository.InterAdDAO;
import com.gukmo.board.sm.repository.InterMemberDAO;

@Service
public class AdManageService implements InterAdManageService {

	// === #34. 의존객체 주입하기(DI: Dependency Injection) ===
	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdDAO dao;
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.board.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
    // 그러므로 dao 는 null 이 아니다.

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
