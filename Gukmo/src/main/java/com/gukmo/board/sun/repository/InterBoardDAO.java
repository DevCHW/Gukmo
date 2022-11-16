package com.gukmo.board.sun.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.MemberDTO;

public interface InterBoardDAO {

	// 게시판 글목록 보기 페이지 요청
	List<BoardVO> boardList(Map<String, String> paraMap);
	
}
