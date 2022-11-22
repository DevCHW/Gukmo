package com.gukmo.board.sun.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;

public interface InterBoardDAO {

	// 게시판 글목록 보기 페이지 요청
	List<BoardVO> boardList(Map<String, String> paraMap);
	
	// 게시판에 글등록하기
	int communityNew(BoardVO boardvo);

	// 글쓰기, 댓글 작성시 활동 점수 올리기
	void pointPlus(Map<String, String> paraMap);
	
	
}
