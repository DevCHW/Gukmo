package com.gukmo.board.hasol.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;

public interface InterIndexService {

	// 게시판 목록 불러오는 메소드
	List<BoardVO> getFreeBoardList();

	// 스터디 게시판 목록 불러오는 메소드
	List<BoardVO> getStudyBoardList();

	// QnA 게시판 목록 불러오는 메소드
	List<BoardVO> getQnaBoardList();

	// 후기/정보공유 게시판 목록 불러오는 메소드
	List<BoardVO> getReviewBoardList();

}
