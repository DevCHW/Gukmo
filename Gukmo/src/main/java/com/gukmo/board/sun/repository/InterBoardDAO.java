package com.gukmo.board.sun.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.model.PenaltyVO;
import com.gukmo.board.model.ReportVO;

public interface InterBoardDAO {

	// 게시판 글목록 보기 페이지 요청
	List<BoardVO> boardList(Map<String, String> paraMap);
	
	// 게시판에 글등록하기
	int communityNew(BoardVO boardvo);

	// 글쓰기, 댓글 작성시 활동 점수 올리기
	int pointPlus(Map<String, Object> paraMap);
	
	// 글쓰기, 댓글 작성시 활동내역에 등록하기
	int activityLog(Map<String, Object> paraMap);

	// 총 게시물 건수
	int getTotalCount(Map<String, String> paraMap);

	// tbl_hashtag에 존재 찾기
	HashtagVO findHashtag(String hashTag);
	
	// tbl_hashtag에 해시태그 추가
	void saveHashTag(String hashTag);

	// tbl_hashtag에 카운트 올리기
	void upHashTagCount(int hashtag_num);

	// tbl_hashtag_board_map 에 insert하기
	int hashtagBoardMapping(Map<String, Object> paraMap);

	// 특정 게시글 내용 가져오기
	BoardVO getBoardDetail(Map<String, Object> paraMap);

	// 게시글 수정
	int modify(BoardVO boardvo);

	// 게시글 삭제
	int boardDel(Map<String, Object> paraMap);

	// 해시태그 수정시 기존 해시태그 삭제
	int hashTagDel(String board_num);

	// 활동테이블에서 활동내역지우기
	int activityDel(Map<String, Object> paraMap);

	// 포인트 차감
	int pointMinus(Map<String, Object> paraMap);

	// 신고하기
	int reportInsert(ReportVO reportvo);

	
	
}
