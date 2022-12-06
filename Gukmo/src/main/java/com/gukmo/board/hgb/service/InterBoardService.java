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
	
    /*
	// 글 상세피이지 진입시 로그인한 회원의 좋아요여부 체크하기
	int ilikethis(Map<String, String> paraMap);
*/
	


	
}
