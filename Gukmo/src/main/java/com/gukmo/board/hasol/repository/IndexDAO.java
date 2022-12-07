package com.gukmo.board.hasol.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.model.SearchVO;

@Repository
public class IndexDAO implements InterIndexDAO {

	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	// 학원 목록 불러오는 메소드
	@Override
	public List<BoardVO> getCurriList1() {
		List<BoardVO> curriList1 = gukmo_sql.selectList("jhs.getCurriList1");
		return curriList1;
	}
	
	@Override
	public List<BoardVO> getCurriList2() {
		List<BoardVO> curriList2 = gukmo_sql.selectList("jhs.getCurriList2");
		return curriList2;
	}

	@Override
	public List<BoardVO> getCurriList3() {
		List<BoardVO> curriList3 = gukmo_sql.selectList("jhs.getCurriList3");
		return curriList3;
	}

	
	// 총 학원 목록 개수 불러오는 메소드
	@Override
	public int getCurriTotalCnt() {
		int n = gukmo_sql.selectOne("jhs.getCurriTotalCnt");
		return n;
	}


	
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

	
	// 주간 해시태그 순위 구하는 메소드
	@Override
	public List<HashtagVO> getTopHashList() {
		List<HashtagVO> topHashList = gukmo_sql.selectList("jhs.getTopHashList");
		return topHashList;
	}

	// 주간 검색어 순위 구하는 메소드
	@Override
	public List<SearchVO> getTopSearchList() {
		List<SearchVO> topSearchList = gukmo_sql.selectList("jhs.getTopSearchList");
		return topSearchList;
	}




}
