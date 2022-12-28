package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

public interface InterStatisticsDAO {

	/**
	 * 토탈데이터 구하기
	 */
	Map<String, Integer> getTotalData();

	

	/**
	 * 국모 접속브라우저 비중 데이터 얻기
	 */
	List<Integer> getBrowserData();


	/**
	 * 국모 최근 한달간 일자별 회원가입 수
	 */
	List<Integer> getJoinMemberData();

	/**
	 * 일반회원.교육기관회원 비중 구하기
	 */
	List<Integer> getMemberRateData();


	/**
	 * 유입경로별 횟수 알아내기
	 */
	List<Map<String, String>> getVisitMap(Map<String,String> paraMap);


	/**
	 * 유입 총갯수 알아오기
	 */
	String getTotalVisit();


	/**
	 * 지난주 커뮤니티 상세카테고리별 작성게시물 건수 구하기
	 */
	List<Integer> getLastWeekCntCommunityData();

}
