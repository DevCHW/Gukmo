package com.gukmo.board.hw.repository;


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


	/**
	 * 총 게시물 건수(totalCount) 구하기(공지사항 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalNoticesCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalNoticesCount",paraMap);
		return totalCount;
	}


	/**
	 * 공지사항 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getNotices(Map<String, String> paraMap) {
		List<BoardVO> notices = gukmo_sql.selectList("chw.getNotices",paraMap);
		return notices;
	}


	
	/**
	 * 총 게시물 건수(totalCount) 구하기(국비학원 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalAcademyCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalAcademyCount",paraMap);
		return totalCount;
	}


	
	/**
	 * 국비학원 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getAcademyList(Map<String, String> paraMap) {
		List<BoardVO> academyList = gukmo_sql.selectList("chw.getAcademyList",paraMap);
		return academyList;
	}

	
	/**
	 * 국비학원 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public String getDetailCategory(int boardNum) {
		String detail_category = gukmo_sql.selectOne("chw.getDetailCategory",boardNum);
		return detail_category;
	}


	
	
	/**
	 * 하나의 글 불러오기(국비학원게시판)
	 * @param 글번호 board_num, 디테일카테고리
	 * @return BoardVO
	 */
	@Override
	public BoardVO getAcademyDetail(Map<String, String> paraMap) {
		BoardVO academy = gukmo_sql.selectOne("chw.getAcademyDetail",paraMap);
		return academy;
	}


	
	
	/**
	 * 총 게시물 건수(totalCount) 구하기(교육과정 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalCurriculumCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalCurriculumCount",paraMap);
		return totalCount;
	}


	/**
	 * 교육과정 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getCurriculumList(Map<String, String> paraMap) {
		List<BoardVO> curriculumList = gukmo_sql.selectList("chw.getCurriculumList",paraMap);
		return curriculumList;
	}



	
	
}
