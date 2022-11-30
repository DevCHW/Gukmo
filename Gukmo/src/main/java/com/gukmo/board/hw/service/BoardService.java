package com.gukmo.board.hw.service;

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


}
