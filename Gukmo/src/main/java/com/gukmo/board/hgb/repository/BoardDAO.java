package com.gukmo.board.hgb.repository;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;


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


	/**
	 * 해시태그 알아오기
	 * @param 글번호 board_num
	 * @return 하나의 글번호에 해시태그
	 */
	



	
}
