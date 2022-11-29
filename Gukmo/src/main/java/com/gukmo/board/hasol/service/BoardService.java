package com.gukmo.board.hasol.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hasol.repository.InterBoardDAO;
import com.gukmo.board.model.AdVO;

@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

	// 배너 노출을 위한 select
	/*
	 * @Override public List<AdVO> getMainBannerList() { List<AdVO> mainBannerList =
	 * dao.getMainBannerList(); return mainBannerList; }
	 */
}
