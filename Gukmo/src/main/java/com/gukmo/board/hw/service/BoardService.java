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
	 * study 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getStudies(Map<String, String> paraMap) {
		List<BoardVO> studies = dao.getStudies(paraMap);
		return studies;
	}

	
	
	/**
	 * 총 게시물 건수(totalCount) 구하기
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalStudiesCount(Map<String, String> paraMap) {
		int totalCount = dao.getTotalStudiesCount(paraMap);
		return totalCount;
	}

	
	
}
