package com.gukmo.board.hw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.repository.InterBoardDAO;
import com.gukmo.board.model.BoardVO;

@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

	
	/**
	 * 총 게시물 건수(totalCount) 구하기(공지사항 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalNoticesCount(Map<String, String> paraMap) {
		int totalCount = dao.getTotalNoticesCount(paraMap);
		return totalCount;
	}


	/**
	 * 공지사항 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getNotices(Map<String, String> paraMap) {
		List<BoardVO> notices = dao.getNotices(paraMap);
		return notices;
	}

	
	
	/**
	 * 총 게시물 건수(totalCount) 구하기(국비학원 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalAcademyCount(Map<String, String> paraMap) {
		int totalCount = dao.getTotalAcademyCount(paraMap);
		return totalCount;
	}

	
	
	/**
	 * 국비학원 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getAcademyList(Map<String, String> paraMap) {
		List<BoardVO> academyList = dao.getAcademyList(paraMap);
		return academyList;
	}


	
	
	/**
	 * 하나의 글 불러오기(국비학원게시판)
	 * @param 글번호 boardNum
	 * @return BoardVO
	 */
	@Override
	public BoardVO getBoardDetail(int boardNum) {
		
		//상세카테고리 알아오기
		String detail_category = dao.getDetailCategory(boardNum);				
		
		Map<String,String> paraMap = new HashMap<>();
		
		paraMap.put("board_num", boardNum+"");
		paraMap.put("category","국비학원");
		paraMap.put("detail_category",detail_category);
		
		BoardVO board = dao.getAcademyDetail(paraMap);
		return board;
	}


	
	/**
	 * 총 게시물 건수(totalCount) 구하기(교육과정 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalCurriculumCount(Map<String, String> paraMap) {
		int totalCount = dao.getTotalCurriculumCount(paraMap);
		return totalCount;
	}


	
	/**
	 * 교육과정 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getCurriculumList(Map<String, String> paraMap) {
		List<BoardVO> curriculumList = dao.getCurriculumList(paraMap);
		return curriculumList;
	}


}
