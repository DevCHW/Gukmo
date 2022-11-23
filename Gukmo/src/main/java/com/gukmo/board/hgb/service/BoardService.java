package com.gukmo.board.hgb.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hgb.repository.InterBoardDAO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.common.FileManager;


@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;
		
	// 글1개 조회하기
	@Override
	public BoardVO getView(Map<String, String> paraMap) {
		
		BoardVO boardvo = dao.getView(paraMap); // 글1개 조회하기 
		
		/*
		String login_userid = paraMap.get("login_userid");
		// paraMap.get("login_userid") 은 로그인을 한 상태이라면 로그인한 사용자의 userid 이고,
		// 로그인을 하지 않은 상태이라면  paraMap.get("login_userid") 은 null 이다. 
		
		if(login_userid != null &&
		   boardvo != null && 
		   !login_userid.equals(boardvo.getNickname()) ) {
			// 글조회수 증가는 로그인을 한 상태에서 다른 사람의 글을 읽을때만 증가하도록 한다.
			
			dao.setAddReadCount(boardvo.getBoard_num()); // 글조회수 1증가 하기 
			boardvo = dao.getView(paraMap);
		}
		*/
		
		return boardvo;
		
	}

	// 글조회수 증가는 없고 단순히 글1개만 조회 해주는 것
	@Override
	public BoardVO getViewWithNoAddCount(Map<String, String> paraMap) {
		BoardVO boardvo = dao.getView(paraMap); // 글1개 조회하기
		return boardvo;
	}
	
	





	
	
	
}
