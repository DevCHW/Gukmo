package com.gukmo.board.hgb.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hgb.repository.InterBoardDAO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.common.FileManager;


@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;

	
	
	/**
	 * 하나의 글 불러오기
	 * @param 글번호 board_num
	 * @return BoardVO
	 */
	@Override
	public BoardVO getBoardDetail(int board_num) {
		
		//상세카테고리 알아오기
		String detail_category = dao.getCategory(board_num);				
		
		Map<String,String> paraMap = new HashMap<>();
		
		paraMap.put("board_num", board_num+"");
		paraMap.put("detail_category",detail_category);
		
		BoardVO board = dao.getBoardDetail(paraMap);
		return board;
	}


	// === 글삭제 하기 === //
	@Override
	public int del(Map<String, String> paraMap) {
		
		int n = dao.del(paraMap);
		
		return n;
	}


	// === 좋아요 체크하기 === //
	@Override
	public List<String> like_exist(Map<String, String> paraMap) {
		List<String> like_exist = dao.like_exist(paraMap);
		return like_exist;
	}



	
	
	
}
