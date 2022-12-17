package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;

public interface InterIndexDAO {

	/**
	 * 인기 게시물 3개 가져오기(조회높은것)
	 */
	List<BoardVO> getPopularBoard();

	/**
	 * 이번년도 월별 방문자 수 구하기
	 */
	List<Integer> getVisitCountMonthlyData();
	
	/**
	 * 사이트요약정보 얻기(오늘 회원가입수, 오늘 방문자 수, 오늘 작성 게시물 수)
	 */
	Map<String, Integer> getSummary();

	/**
	 * 오늘자 커뮤니티활성 데이터 얻기
	 */
	Map<String,Integer> getCommunityActiveData();


}
