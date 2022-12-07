package com.gukmo.board.hasol.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.model.SearchVO;

public interface InterIndexService {

	// 학원 목록 불러오는 메소드
	List<BoardVO> getCurriList1();
	List<BoardVO> getCurriList2();
	List<BoardVO> getCurriList3();
	
	// 학원 목록 촉 개수를 구하기 위한 메소드
	int getCurriTotalCnt();
	
	// 게시판 목록 불러오는 메소드
	List<BoardVO> getFreeBoardList();

	// 스터디 게시판 목록 불러오는 메소드
	List<BoardVO> getStudyBoardList();

	// QnA 게시판 목록 불러오는 메소드
	List<BoardVO> getQnaBoardList();

	// 후기/정보공유 게시판 목록 불러오는 메소드
	List<BoardVO> getReviewBoardList();
	
	// 주간 해시태그 순위를 구해오는 메소드
	List<HashtagVO> getTopHashList();
	
	// 주간 검색어 순위를 구해오는 메소드
	List<SearchVO> getTopSearchList();



}
