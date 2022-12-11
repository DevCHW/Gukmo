package com.gukmo.board.sm.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.sm.repository.InterBoardDAO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CommentVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.common.FileManager;


@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;

	
	
	/**
	 * 하나의 글 불러오기
	 * @param 글번호 board_num
	 * @return BoardVO
	 */
	@Override
	public BoardVO getBoardDetail(Map<String,String> paraMap) {				
		BoardVO board = dao.getBoardDetail(paraMap);
		dao.setAddReadCount(board.getBoard_num());
		
		return board;
	}


	// === 글삭제 하기 === //
	@Override
	public int del(Map<String, String> paraMap) {
		
		int n = dao.del(paraMap);
		
		return n;
	}




	
	
	/**
	 * 좋아요 처리하기
	 * @param paraMap(글번호,userid)
	 */
	@Override
	public String likeProcess(Map<String, String> paraMap) {
		int likeCnt = dao.likeCheck(paraMap);	//좋아요 체크하기
		String likeResult = "";
		int result = 0;
		if(likeCnt > 0) {	//좋아요를 눌렀다면
			result = dao.likeDelete(paraMap); //좋아요 테이블에 delete하기
			likeResult = "delete";
		} else {	//좋아요를 누르지 않았다면
			result = dao.likeInsert(paraMap);	//좋아요 테이블에 insert하기
			likeResult = "insert";
		}
		
		if(result != 1) {	//delete나 insert 성공시
			likeResult = "error";
		}
		
		return likeResult;
		
	}


	// 댓글 쓰기
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int addComment(Map<String, String> paraMap) {
		int result1=0, result2=0, result3=0, result4=0;
		
		// tbl_comment 테이블에 추가, tbl_board 의 comment_cnt +1, 해당 회원의 포인트 10점 증가
		
		// 댓글 테이블에 insert
		result1 = dao.addComment(paraMap);
		
		// tbl_board의 comment_cnt 컬럼에  +1
		if(result1 == 1 ) {
			result2 = dao.addComment_cnt(paraMap);
		}
		
		// 해당 회원의 포인트 10점 증가
		if(result2 == 1) {
			result3 = dao.addPoint(paraMap);
		}
		
		// 활동테이블에  insert
		if(result3 == 1) {
			result4 = dao.addActivity(paraMap);
		}
		
		
		if(result1 * result2 * result3 * result4 == 1) {
			return 1;
		}
		else {
			return 2;
		}
		
	}


	//디테일 카테고리 가져오기
	@Override
	public String getCategory(Map<String,String> paraMap) {
		String detail_category = dao.getCategory(paraMap);				
		return detail_category;
	}


	// *대댓글* tbl_comment 테이블에 추가, tbl_board 의 comment_cnt +1, 해당 회원의 포인트 10점 증가
	@Override
	public int addComment_of_Comment(Map<String, String> paraMap) {
		
		int result1=0, result2=0, result3=0, result4=0;
		
		// tbl_comment 테이블에 추가, tbl_board 의 comment_cnt +1, 해당 회원의 포인트 10점 증가
		
		// *대댓글* 댓글 테이블에 insert
		result1 = dao.addComment_of_Comment(paraMap);
		
		// tbl_board의 comment_cnt 컬럼에  +1
		if(result1 == 1 ) {
			result2 = dao.addComment_cnt(paraMap);
		}
		
		// 해당 회원의 포인트 10점 증가
		if(result2 == 1) {
			result3 = dao.addPoint(paraMap);
		}
		
		// 활동테이블에  insert
		if(result3 == 1) {
			result4 = dao.addActivity(paraMap);
		}
				
		if(result1 * result2 * result3 * result4 == 1) {
			return 1;
		}
		else {
			return 2;
		}
		
	}


	// 댓글 리스트 불러오기
	@Override
	public List<CommentVO> getCommentList(Map<String, String> paraMap) {
		List<CommentVO> commentList = dao.getCommentList(paraMap);		
		return commentList;
	}


	//기본 댓글 리스트 불러오기(기본 : 그냥 댓글, 특수 : 대댓글)
	@Override
	public List<CommentVO> getBasic_commentList(Map<String, String> paraMap) {
		List<CommentVO> basic_commentList = dao.getBasic_commentList(paraMap);
		return basic_commentList;
	}

	//기본 댓글 리스트 불러오기(기본 : 그냥 댓글, 특수 : 대댓글)
	@Override
	public List<CommentVO> getSpecial_commentList(Map<String, String> paraMap) {
		List<CommentVO> special_commentList = dao.getSpecial_commentList(paraMap);
		return special_commentList;
	}


	// 댓글 삭제 및 그 대댓도 삭제
	@Override
	public String commentDelete(Map<String, String> paraMap) {
		String result = "";
		// 삭제할 댓글의 총 개수 알아오기
		int n2 = dao.comment_cnt_minus(paraMap);
		result = Integer.toString(n2);
		
		// 댓글 테이블에서 해당 데이터 삭제
		int n = dao.commentDelete(paraMap);
		
		return result;
	}

	// 게시판 테이블의 comment_cnt 컬럼에서 댓삭한 개수 삭제
	@Override
	public int board_cmt_cnt_minus(Map<String, String> paraMap) {
	      int n2 = dao.board_cmt_cnt_minus(paraMap);	      
	      return n2;
	}



	// 댓글 수정
	@Override
	public int commentEdit(Map<String, String> paraMap) {
		int n = dao.commentEdit(paraMap);
		return n;
	}

	// 글 상세페이지 진입시 로그인한 회원의 좋아요여부 체크하기
	   @Override
	   public int likethis(Map<String, String> paraMap) {
	      
	      int likethis = dao.likethis(paraMap);
	      
	      return likethis;
	   }

	
	/**
	 * 댓글 좋아요 처리하기
	 * @param paraMap(글번호,userid)
	 */
	@Override
	public String comment_likeProcess(Map<String, String> paraMap) {
		int comment_likeCnt = dao.comment_likeCheck(paraMap);	//좋아요 개수 체크하기
		String comment_likeResult = "";
		int result = 0;
		if(comment_likeCnt > 0) {	//좋아요를 눌렀다면
			result = dao.comment_likeDelete(paraMap); //좋아요 테이블에 delete하기
			comment_likeResult = "delete";
		} else {	//좋아요를 누르지 않았다면
			result = dao.comment_likeInsert(paraMap);	//좋아요 테이블에 insert하기
			comment_likeResult = "insert";
		}
		
		if(result != 1) {	//delete나 insert 성공시
			comment_likeResult = "error";
		}
		
		return comment_likeResult;
	}


	// id값 알아오기
	@Override
	public String getUserid(Map<String, String> paraMap) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	
}
