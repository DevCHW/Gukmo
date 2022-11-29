package com.gukmo.board.hasol.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;

public interface InterIndexDAO {

	// 게시판 목록 불러오는 메소드
	List<BoardVO> getFreeBoardList();

	List<BoardVO> getStudyBoardList();

	List<BoardVO> getQnaBoardList();

	List<BoardVO> getReviewBoardList();

}
