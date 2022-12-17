package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.VisitCountVO;

@Repository
public class IndexDAO implements InterIndexDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	/**
	 * 인기 게시물 3개 가져오기(조회높은것)
	 */
	@Override
	public List<BoardVO> getPopularBoard() {
		List<BoardVO> popularBoardList = gukmo_sql.selectList("chw.getPopularBoard");
		return popularBoardList;
	}

	
	/**
	 * 이번년도 월별 방문자 수 구하기
	 */
	@Override
	public List<Integer> getVisitCountMonthlyData() {
		return gukmo_sql.selectList("chw.getVisitCountMonthlyData");
	}


	/**
	 * 이번년도 월별 방문자 수 구하기
	 */
	@Override
	public Map<String, Integer> getSummary() {
		return gukmo_sql.selectOne("chw.getSummary");
	}


	/**
	 * 오늘자 커뮤니티활성 데이터 얻기
	 */
	@Override
	public Map<String, Integer> getCommunityActiveData() {
		return gukmo_sql.selectOne("chw.getCommunityActiveData");
	}
	
	
	
	
	

}
