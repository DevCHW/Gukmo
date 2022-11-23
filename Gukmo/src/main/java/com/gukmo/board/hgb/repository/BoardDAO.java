package com.gukmo.board.hgb.repository;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.BoardVO;


@Repository
public class BoardDAO implements InterBoardDAO{
	
	
	@Resource
	private SqlSessionTemplate gukmo_sql;	

	// 글1개 조회하기
	@Override
	public BoardVO getView(Map<String, String> paraMap) {
		BoardVO boardvo = gukmo_sql.selectOne("board.getView", paraMap);
		return boardvo;
	}

	// 글조회수 1증가 하기 
	@Override
	public void setAddReadCount(String board_num) {
		gukmo_sql.update("board.setAddReadCount", board_num);
	}

	
	
}
