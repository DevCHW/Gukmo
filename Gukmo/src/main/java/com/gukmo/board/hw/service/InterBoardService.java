package com.gukmo.board.hw.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;


public interface InterBoardService {

	
	
	// =============================== 공지사항게시판 시작 ============================== //
	/**
	 * 총 게시물 건수(totalCount) 구하기(공지사항 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	int getTotalNoticesCount(Map<String, String> paraMap);


	
	/**
	 * 공지사항 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	List<BoardVO> getNotices(Map<String, String> paraMap);


	/**
	 * 총 게시물 건수(totalCount) 구하기(국비학원 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	int getTotalAcademyCount(Map<String, String> paraMap);


	
	
	/**
	 * 국비학원 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	List<BoardVO> getAcademyList(Map<String, String> paraMap);

	
	BoardVO getBoardDetail(int boardNum);


	
	
	/**
	 * 총 게시물 건수(totalCount) 구하기(교육과정 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	int getTotalCurriculumCount(Map<String, String> paraMap);


	/**
	 * 교육과정 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	List<BoardVO> getCurriculumList(Map<String, String> paraMap);



	/**
	 * 학원 등록하기
	 */
	boolean insertAcademy(Map<String, Object> paraMap);


	
	/**
	 * 교육과정 등록하기
	 */
	boolean insertCurriculum(Map<String, Object> paraMap);








	
	
	
	// =============================== 공지사항게시판 끝 ============================== //


}
