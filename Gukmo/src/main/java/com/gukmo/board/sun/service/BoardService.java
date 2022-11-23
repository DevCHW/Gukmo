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


	// 글쓰기, 댓글 작성시 활동 점수 update, 활동기록 insert (after)
	@Override
	public int pointPlusActivityLog(Map<String, Object> paraMap) {
		int n = 0 ;
		n = dao.pointPlus(paraMap);
		System.out.println("포인트 플러스 성공 : " + n);
		
		if(n == 1) { // 포인트 업데이트 성공했을 시 활동내역 등록하기
			n += dao.activityLog(paraMap);
		}
		
		return n;
	}
}
