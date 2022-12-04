package com.gukmo.board.hgb.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;

public interface InterBoardDAO {

	/**
	 * 하나의 글 불러오기
	 * @param 글번호 board_num,상세카테고리 detaiL_category가 들어있는 Map
	 * @return BoardVO
	 */
	BoardVO getBoardDetail(Map<String,String> paraMap);

	
	
	/**
	 * 상세카테고리 알아오기
	 * @param 글번호 board_num
	 * @return 하나의 글번호에 대한상세카테고리
	 */
	String getCategory(int board_num);


	// === 글삭제 하기 === //
	int del(Map<String, String> paraMap);


	// === 좋아요 체크하기 === //
	List<String> like_exist(Map<String, String> paraMap);



	/**
	 * 좋아요 체크하기
	 * @param paraMap(글번호,userid)
	 * @return 좋아요 갯수
	 */
	int likeCheck(Map<String, String> paraMap);


	/**
	 * 좋아요 테이블에 delete하기
	 * @param paraMap(글번호,userid)
	 * @return 성공 1 실패 0
	 */
	int likeDelete(Map<String, String> paraMap);


	/**
	 * 좋아요 테이블에 insert하기
	 * @param paraMap(글번호,userid)
	 * @return 성공 1 실패 0
	 */
	int likeInsert(Map<String, String> paraMap);



	

	

}
