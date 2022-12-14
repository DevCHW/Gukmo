package com.gukmo.board.sm.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CommentVO;
import com.gukmo.board.model.HashtagVO;

public interface InterBoardDAO {
	
	/**
	 * 상세카테고리 알아오기
	 * @param 글번호 board_num
	 * @return 하나의 글번호에 대한상세카테고리
	 */
	String getCategory(Map<String,String> paraMap);

	
	/**
	 * 하나의 글 불러오기
	 * @param 글번호 board_num,상세카테고리 detaiL_category가 들어있는 Map
	 * @return BoardVO
	 */
	BoardVO getBoardDetail(Map<String,String> paraMap);



	// === 글삭제 하기 === //
	int del(Map<String, String> paraMap);


	// === 좋아요 체크하기 === //
	List<String> like_exist(Map<String, String> paraMap);



	/**
	 * 좋아요 체크하기
	 * @param paraMap(글번호,userid)
	 * @return 좋아요 갯수
	 */
	int likeCheck(Map<String, String> paraMap);


	/**
	 * 좋아요 테이블에 delete하기
	 * @param paraMap(글번호,userid)
	 * @return 성공 1 실패 0
	 */
	int likeDelete(Map<String, String> paraMap);


	/**
	 * 좋아요 테이블에 insert하기
	 * @param paraMap(글번호,userid)
	 * @return 성공 1 실패 0
	 */
	int likeInsert(Map<String, String> paraMap);


	// 글조회수 1증가 하기 
	void setAddReadCount(String board_num);


	// 댓글 테이블에 insert
	int addComment(Map<String, String> paraMap);


	// tbl_board의 comment_cnt 컬럼에  +1
	int addComment_cnt(Map<String, String> paraMap);


	// 해당 회원의 포인트 10점 증가
	int addPoint(Map<String, String> paraMap);


	// *대댓글* 댓글 테이블에 insert
	int addComment_of_Comment(Map<String, String> paraMap);


	// 댓글 리스트 불러오기
	List<CommentVO> getCommentList(Map<String, String> paraMap);


	//기본 댓글 리스트 불러오기(기본 : 그냥 댓글, 특수 : 대댓글)
	List<CommentVO> getBasic_commentList(Map<String, String> paraMap);
	List<CommentVO> getSpecial_commentList(Map<String, String> paraMap);


	// 댓글 삭제 및 그 대댓도 삭제
	int commentDelete(Map<String, String> paraMap);

	// 게시판 테이블의 comment_cnt 컬럼에서 댓삭한 개수 삭제
	int board_cmt_cnt_minus(Map<String, String> paraMap);

	
	// 댓글 수정
	int commentEdit(Map<String, String> paraMap);


	// 글 상세페이지 진입시 로그인한 회원의 좋아요여부 체크하기
	int likethis(Map<String, String> paraMap);


	// 활동테이블에  insert
	int addActivity(Map<String, String> paraMap);


	// 댓글 좋아요 체크
	int comment_likeCheck(Map<String, String> paraMap);


	int comment_likeDelete(Map<String, String> paraMap);


	int comment_likeInsert(Map<String, String> paraMap);


	// 삭제된 댓글의 총 개수 알아오기
	int comment_cnt_minus(Map<String, String> paraMap);

	// 글 상세페이지 진입시 로그인한 회원의 댓글좋아요여부 체크하기
	int comment_likethis(Map<String, String> paraMap);





  /*
	// 글 상세피이지 진입시 로그인한 회원의 좋아요여부 체크하기
	int  ilikethis(Map<String, String> paraMap);
*/


	

	

}
