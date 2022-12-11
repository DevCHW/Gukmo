package com.gukmo.board.sun.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.sun.repository.InterAdminDAO;


@Service
public class AdminServie implements InterAdminService {

	
	@Autowired
	private InterAdminDAO dao;

	
	// 기간별 새로운 게시물 수 
	@Override
	public List<Map<String, String>> newBoardCnt(Map<String, String> paraMap) {
		List<Map<String, String>> newBoardCnt_List = dao.newBoardCnt(paraMap);  
		return newBoardCnt_List;
	}


	// 특정 회원 활동내역 리스트 가져오기 
	@Override
	public List<Map<String, String>> activityList(Map<String, String> paraMap) {
		List<Map<String, String>> activityList = dao.activityList(paraMap);  
		return activityList;
	}


	// 특정 회원 일자별 활동내역 카운트 가져오기 
	@Override
	public List<Map<String, String>> activityCntList(Map<String, String> paraMap) {
		List<Map<String, String>> activityCntList = dao.activityCntList(paraMap);  
		return activityCntList;
	}


	// 특정 회원 연도별, 월별 활동내역 카운트 가져오기 
	@Override
	public List<Map<String, String>> activityCntListYearMonth(Map<String, String> paraMap) {
		List<Map<String, String>> activityCntList = dao.activityCntListYearMonth(paraMap);  
		return activityCntList;
	}


	// 특정 회원 검색어 카운트
	@Override
	public List<Map<String, String>> searchCntList(String userid) {
		List<Map<String, String>> searchCntList = dao.searchCntList(userid);  
		return searchCntList;
	}

	
	
	
}
