package com.gukmo.board.hw.admin.repository;

import java.util.List;

import com.gukmo.board.model.BoardVO;

public interface InterIndexDAO {

	/**
	 * 인기 게시물 3개 가져오기(조회높은것)
	 */
	List<BoardVO> getPopularBoard();

}
