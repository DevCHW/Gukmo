package com.gukmo.board.sun.repository;

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

	// 게시판 글목록 보기 페이지 요청
	@Override
	public List<BoardVO> boardList(Map<String, String> paraMap) {
		List<BoardVO> boardList = gukmo_sql.selectList("sun.boardList");
		return boardList;
	}

	
	// 게시판에 글등록하기
	@Override
	public int communityNew(BoardVO boardvo) {
		int n = gukmo_sql.insert("sun.communityNew", boardvo);
		return n;
	}

	
	// 글쓰기, 댓글 작성시 활동 점수 올리기
	@Override
	public void pointPlus(Map<String, String> paraMap) {
		gukmo_sql.update("sun.pointPlus", paraMap);
	}
	
}
