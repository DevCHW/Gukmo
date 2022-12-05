package com.gukmo.board.hgb.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;



public interface InterBoardService {

	


	
	/**
	 * 하나의 글 불러오기
	 * @param 글번호 board_num
	 * @return BoardVO
	 */
	BoardVO getBoardDetail(int board_num);

	// === 글삭제 하기 === //
	int del(Map<String, String> paraMap);

	

	/**
	 * 좋아요 처리하기
	 * @param paraMap(글번호,userid)
	 */
	String likeProcess(Map<String, String> paraMap);

	


	
}
