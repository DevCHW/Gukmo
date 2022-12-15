package com.gukmo.board.hw.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.admin.repository.InterIndexDAO;
import com.gukmo.board.model.BoardVO;


@Service
public class IndexService implements InterIndexService{
	@Autowired
	private InterIndexDAO dao;

	/**
	 * 인기 게시물 3개 가져오기(조회높은것)
	 */
	@Override
	public List<BoardVO> getPopularBoard() {
		List<BoardVO> popularBoardList = dao.getPopularBoard();
		return popularBoardList;
	}

}
