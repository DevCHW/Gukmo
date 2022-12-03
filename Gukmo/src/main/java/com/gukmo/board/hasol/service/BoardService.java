package com.gukmo.board.hasol.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hasol.repository.InterBoardDAO;
import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.BoardVO;

@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

	// 배너 노출을 위한 select
	/*
	 * @Override public List<AdVO> getMainBannerList() { List<AdVO> mainBannerList =
	 * dao.getMainBannerList(); return mainBannerList; }
	 */
	
	// 검색 - 총 게시물 건 수를 알아오는 용도
	@Override
	public int getTotalCnt(Map<String, String> paraMap) {
		int n = dao.getTotalCnt(paraMap);
		return n;
	}

	// 검색 - 총 게시물 리스트 알아오는 용도
	@Override
	public List<BoardVO> getSearchList(Map<String, String> paraMap) {
		List<BoardVO> searchList = dao.getSearchList(paraMap);
		return searchList;
	}

	
}
