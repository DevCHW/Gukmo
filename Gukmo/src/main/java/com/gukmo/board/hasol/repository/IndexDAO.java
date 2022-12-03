package com.gukmo.board.hasol.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;

@Repository
public class IndexDAO implements InterIndexDAO {

	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	// 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getFreeBoardList() {
		List<BoardVO> freeBoardList = gukmo_sql.selectList("jhs.getFreeBoardList");
		return freeBoardList;
	}

	// 스터디 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getStudyBoardList() {
		List<BoardVO> studyBoardList = gukmo_sql.selectList("jhs.getStudyBoardList");
		return studyBoardList;
	}

	// qna 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getQnaBoardList() {
		List<BoardVO> qnaBoardList = gukmo_sql.selectList("jhs.getQnaBoardList");
		return qnaBoardList;
	}

	// 후기 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getReviewBoardList() {
		List<BoardVO> reviewBoardList = gukmo_sql.selectList("jhs.getReviewBoardList");
		return reviewBoardList;
	}

}
