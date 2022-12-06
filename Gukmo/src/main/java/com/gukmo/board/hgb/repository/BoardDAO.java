package com.gukmo.board.hgb.repository;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.model.BoardVO;


@Repository
public class BoardDAO implements InterBoardDAO{
	
	
	@Resource
	private SqlSessionTemplate gukmo_sql;	



	/**
	 * 하나의 글 불러오기
	 * @param 글번호 board_num,상세카테고리 detaiL_category가 들어있는 Map
	 * @return BoardVO
	 */
	@Override
	public BoardVO getBoardDetail(Map<String,String> paraMap) {
		BoardVO board = gukmo_sql.selectOne("hgb.getBoardDetail",paraMap);
		return board;
	}




	/**
	 * 상세카테고리 알아오기
	 * @param 글번호 board_num
	 * @return 하나의 글번호에 대한상세카테고리
	 */	
	@Override
	public String getCategory(int board_num) {
		String detail_category = gukmo_sql.selectOne("hgb.getCategory",board_num);
		return detail_category;
	}



	// === 글삭제 하기 === //
	@Override
	public int del(Map<String, String> paraMap) {
		int n = gukmo_sql.delete("hgb.del", paraMap);
		return n;
	}



    // === 좋아요 체크하기
	@Override
	public List<String> like_exist(Map<String, String> paraMap) {
		List<String> like_exist = gukmo_sql.selectList("hgb.like_exist", paraMap);
		return like_exist;
	}



	/**
	 * 좋아요 체크하기
	 * @param paraMap(글번호,userid)
	 * @return 좋아요 갯수
	 */
	@Override
	public int likeCheck(Map<String, String> paraMap) {
		int likeCnt = gukmo_sql.selectOne("hgb.likeCheck", paraMap);
		return likeCnt;
	}



	/**
	 * 좋아요 테이블에 delete하기
	 * @param paraMap(글번호,userid)
	 * @return 성공 1 실패 0
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int likeDelete(Map<String, String> paraMap) {
		int result1 = gukmo_sql.delete("hgb.likeDelete", paraMap);
		
		//좋아요 개수 알아오기
		int like_cnt = gukmo_sql.selectOne("hgb.getLike_cnt", paraMap);
		like_cnt = like_cnt - 1;
		
		paraMap.put("like_cnt",like_cnt+"");
		
		//좋아요 1 뺀값 업데이트
		int result2 = gukmo_sql.update("hgb.likeCntChange", paraMap);
		
		
		return result1*result2;
	}



	/**
	 * 좋아요 테이블에 insert하기
	 * @param paraMap(글번호,userid)
	 * @return 성공 1 실패 0
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int likeInsert(Map<String, String> paraMap) {
		int result1 = gukmo_sql.insert("hgb.likeInsert", paraMap);
		
		//좋아요 개수 알아오기
		int like_cnt = gukmo_sql.selectOne("hgb.getLike_cnt", paraMap);
		like_cnt = like_cnt + 1;
		
		paraMap.put("like_cnt",like_cnt+"");
		
		//좋아요 1 더한값 업데이트
		int result2 = gukmo_sql.update("hgb.likeCntChange", paraMap);
		
		return result1*result2;
	}



	/**
	 * 글 조회수 1증가하기
	 * @param paraMap(글번호)
	 * @return 성공 1 실패 0
	 */
	@Override
	public void setAddReadCount(String board_num) {
		gukmo_sql.update("hgb.setAddReadCount", board_num);
		
	}



	// 글 상세피이지 진입시 로그인한 회원의 좋아요여부 체크하기
	/*
	@Override
	
	public int ilikethis(Map<String, String> paraMap) {
		
		
		int ilikethis = gukmo_sql.selectList("hgb.ilikethis", paraMap);
		
		return ilikethis;
	}
*/



	
}
