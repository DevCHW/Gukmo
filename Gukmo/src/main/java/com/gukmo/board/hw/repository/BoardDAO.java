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
	 * study 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getStudies(Map<String, String> paraMap) {
		List<BoardVO> studies = gukmo_sql.selectList("chw.getStudies",paraMap);
		return studies;
	}

	
	/**
	 * 총 게시물 건수(totalCount) 구하기
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalStudiesCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalStudiesCount",paraMap);
		return totalCount;
	}
	
	
}
