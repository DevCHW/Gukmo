package com.gukmo.board.sun.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.sun.repository.InterBoardDAO;


@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

	
	// 게시판 글목록 보기 페이지 요청
	@Override
	public List<BoardVO> boardList(Map<String, String> paraMap) {
		List<BoardVO> boardList = dao.boardList(paraMap);
		return boardList;
	}

	
	// 글쓰기, 댓글 작성시 활동 점수 올리기
	@Override
	public void pointPlus(Map<String, String> paraMap) {
		dao.pointPlus(paraMap);
	}



}
