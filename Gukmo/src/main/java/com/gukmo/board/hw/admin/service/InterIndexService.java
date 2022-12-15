package com.gukmo.board.hw.admin.service;

import java.util.List;

import com.gukmo.board.model.BoardVO;

public interface InterIndexService {

	/**
	 * 인기 게시물 3개 가져오기
	 */
	List<BoardVO> getPopularBoard();

}
