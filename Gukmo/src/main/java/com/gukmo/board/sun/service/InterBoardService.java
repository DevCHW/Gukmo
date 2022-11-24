package com.gukmo.board.sun.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;

public interface InterBoardService{
	
	// 게시판 글목록 보기 페이지 요청
	List<BoardVO> boardList(Map<String, String> paraMap);

	// 글쓰기, 댓글 작성시 활동 점수 update, 활동기록 insert (after)
	int pointPlusActivityLog(Map<String, Object> paraMap);

	// 총 게시물 건수 
	int getTotalCount(Map<String, String> paraMap);


	
}
