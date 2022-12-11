package com.gukmo.board.sm.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CommentVO;



public interface InterBoardService {
	
	/**
	 * 하나의 글 불러오기
	 * @param 글번호 board_num
	 * @return BoardVO
	 */
	BoardVO getBoardDetail(Map<String,String> paraMap);

	// === 글삭제 하기 === //
	int del(Map<String, String> paraMap);

	

	/**
	 * 좋아요 처리하기
	 * @param paraMap(글번호,userid)
	 */
	String likeProcess(Map<String, String> paraMap);

	// 댓글 쓰기 
	int addComment(Map<String, String> paraMap);

	//디테일 카테고리 가져오기
	String getCategory(Map<String, String> paraMap);
	
    /*
	// 글 상세피이지 진입시 로그인한 회원의 좋아요여부 체크하기
	int ilikethis(Map<String, String> paraMap);
*/
	
	// *대댓글* tbl_comment 테이블에 추가, tbl_board 의 comment_cnt +1, 해당 회원의 포인트 10점 증가
	int addComment_of_Comment(Map<String, String> paraMap);
	
	// 아이디값 알아오기
	String getUserid(Map<String, String> paraMap);

	// 댓글 리스트 불러오기
	List<CommentVO> getCommentList(Map<String, String> paraMap);

	//기본 댓글 리스트 불러오기(기본 : 그냥 댓글, 특수 : 대댓글)
	List<CommentVO> getBasic_commentList(Map<String, String> paraMap);
	List<CommentVO> getSpecial_commentList(Map<String, String> paraMap);

	// 댓글 삭제 및 그 대댓도 삭제
	String commentDelete(Map<String, String> paraMap);
	
	// 게시판 테이블의 comment_cnt 컬럼에서 댓삭한 개수 삭제
	int board_cmt_cnt_minus(Map<String, String> paraMap);

	// 댓글 수정
	int commentEdit(Map<String, String> paraMap);

	// 좋아요 체크 여부 확인
	int likethis(Map<String, String> paraMap);

	// 댓글 좋아요
	String comment_likeProcess(Map<String, String> paraMap);




	
}
