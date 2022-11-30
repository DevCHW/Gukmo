package com.gukmo.board.sun.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;

public interface InterBoardService{
	
	// 게시판 글목록 보기 페이지 요청
	List<BoardVO> boardList(Map<String, String> paraMap);

	// 글쓰기, 댓글 작성시 활동 점수 update, 활동기록 insert (after)
	int pointPlusActivityLog(Map<String, Object> paraMap);

	// 총 게시물 건수 
	int getTotalCount(Map<String, String> paraMap);

	//////////해시태그 ///////////
	// 해시태그 tbl_hashtag 에 insert(테이블에 존재하지 않으면 insert,존재하는 해시태그면 x)
	Boolean saveTag(List<String> hashTags, String board_num);

	// 게시판에 글 등록하기
	int communityNew(BoardVO boardvo);

	// 특정 게시글 내용 가져오기
	BoardVO getBoardDetail(Map<String, String> paraMap);

	// 게시글 수정
	int edit(BoardVO boardvo);

	// 게시글 삭제
	int del(Map<String, String> paraMap);


	
}
