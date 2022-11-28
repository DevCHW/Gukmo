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

	
	/**
	 * 해시태그 불러오기
	 * @param 글번호 board_num
	 * @return BoardVO
	 */
	

	
}
