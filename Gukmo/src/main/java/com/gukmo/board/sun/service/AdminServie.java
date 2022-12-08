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
	public List<Map<String, String>> newBoardCnt() {
		List<Map<String, String>> newBoardCnt_List = dao.newBoardCnt();  
		return newBoardCnt_List;
	}

	
	
	
}
