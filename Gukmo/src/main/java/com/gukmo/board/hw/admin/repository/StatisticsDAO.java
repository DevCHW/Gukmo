package com.gukmo.board.hw.admin.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticsDAO implements InterStatisticsDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;

	/**
	 * 토탈데이터 구하기
	 */
	@Override
	public Map<String, Integer> getTotalData() {
		return gukmo_sql.selectOne("chw.getTotalData");
	}

	/**
	 * 국모 접속브라우저 비중 데이터 얻기
	 */
	@Override
	public List<Integer> getBrowserData() {
		return gukmo_sql.selectList("chw.getBrowserData");
	}

	/**
	 * 이번달 일자별 회원가입 수
	 */
	@Override
	public List<Integer> getJoinMemberData() {
		return gukmo_sql.selectList("chw.getJoinMemberData");
	}

	/**
	 * 일반회원.교육기관회원 비중 구하기
	 */
	@Override
	public List<Integer> getMemberRateData() {
		Map<String,String> dataMap = gukmo_sql.selectOne("chw.getMemberRateData");
		List<Integer> memberRateData = new ArrayList<>();
		memberRateData.add(Integer.parseInt(String.valueOf(dataMap.get("CNT_NORMAL_MEMBER"))));
		memberRateData.add(Integer.parseInt(String.valueOf(dataMap.get("CNT_ACA_MEMBER"))));
		return memberRateData;
	}

	/**
	 * 유입경로별 유입횟수
	 */
	@Override
	public List<Map<String, String>> getVisitMap(Map<String,String> paraMap) {
		return gukmo_sql.selectList("chw.getVisitMap",paraMap);
	}

	/**
	 * 유입 총갯수 알아오기
	 */
	@Override
	public String getTotalVisit() {
		return gukmo_sql.selectOne("chw.getTotalVisit");
	}

	
	/**
	 * 지난주 커뮤니티 상세카테고리별 작성게시물 건수 구하기
	 */
	@Override
	public List<Integer> getLastWeekCntCommunityData() {
		Map<String,String> dataMap = gukmo_sql.selectOne("chw.getLastWeekCntCommunityData");
		List<Integer> data = new ArrayList<Integer>();
		Collection<?> value = dataMap.values();
		Iterator<?> itr = value.iterator();
		while(itr.hasNext()) {
			data.add(Integer.parseInt(String.valueOf(itr.next())));
		}
		return data;
		
	}
	
	
	
	
}
