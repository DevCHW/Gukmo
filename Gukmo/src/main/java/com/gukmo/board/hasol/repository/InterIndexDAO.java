package com.gukmo.board.hasol.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.model.SearchVO;

public interface InterIndexDAO {

	// 게시판 목록 불러오는 메소드
	List<BoardVO> getFreeBoardList();

	List<BoardVO> getStudyBoardList();

	List<BoardVO> getQnaBoardList();

	List<BoardVO> getReviewBoardList();

	int getCurriTotalCnt();

	// 학원 목록 불러오는 메소드
	List<BoardVO> getCurriList1();
	List<BoardVO> getCurriList2();
	List<BoardVO> getCurriList3();

	// 주간 해시태그 순위를 불러오는 메소드
	List<HashtagVO> getTopHashList();

	// 주간 검색어 순위르 불러온느 메소드
	List<SearchVO> getTopSearchList();
	
}
