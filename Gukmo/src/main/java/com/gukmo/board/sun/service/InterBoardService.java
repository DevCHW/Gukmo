package com.gukmo.board.sun.service;

import java.util.List;
import java.util.Map;

<<<<<<< HEAD
import com.gukmo.board.model.BoardVO;
=======
import com.gukmo.board.model.MemberVO;
>>>>>>> branch 'main' of https://github.com/hyunwoocastle/Gukmo.git

public interface InterBoardService{
	
	// 게시판 글목록 보기 페이지 요청
	List<BoardVO> boardList(Map<String, String> paraMap);
	
}
