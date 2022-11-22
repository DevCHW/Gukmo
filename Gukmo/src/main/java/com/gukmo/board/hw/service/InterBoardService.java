package com.gukmo.board.hw.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;


public interface InterBoardService {

	

	
	/**
	 * study 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	int getTotalStudiesCount(Map<String, String> paraMap);
	
	
	/**
	 * study 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @return BoardVO리스트
	 */
	List<BoardVO> getStudies(Map<String, String> paraMap);



}
