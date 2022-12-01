package com.gukmo.board.hasol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hasol.repository.InterIndexDAO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;

@Service
public class IndexService implements InterIndexService {

	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterIndexDAO dao;

	// 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getFreeBoardList() {
		List<BoardVO> freeBoardList = dao.getFreeBoardList();
		return freeBoardList;
	}

	// 스터디 목록 불러오는 메소드
	@Override
	public List<BoardVO> getStudyBoardList() {
		List<BoardVO> studyBoardList = dao.getStudyBoardList();
		return studyBoardList;
	}

	//qna 목록 불러오는 메소드
	@Override
	public List<BoardVO> getQnaBoardList() {
		List<BoardVO> qnaBoardList = dao.getQnaBoardList();
		return qnaBoardList;
	}

	// 후기 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getReviewBoardList() {
		List<BoardVO> reviewBoardList = dao.getReviewBoardList();
		return reviewBoardList;
	}
	

}
