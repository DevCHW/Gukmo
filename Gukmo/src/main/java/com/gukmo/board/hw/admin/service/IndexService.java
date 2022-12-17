package com.gukmo.board.hw.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.admin.repository.InterIndexDAO;
import com.gukmo.board.model.BoardVO;


@Service
public class IndexService implements InterIndexService{
	@Autowired
	private InterIndexDAO dao;

	/**
	 * 인기 게시물 3개 가져오기(조회높은것)
	 */
	@Override
	public List<BoardVO> getPopularBoard() {
		List<BoardVO> popularBoardList = dao.getPopularBoard();
		return popularBoardList;
	}
	
	
	/**
	 * 이번년도 월별 방문자 수 구하기
	 */
	@Override
	public List<Integer> getVisitCountMonthlyData() {
		List<Integer> visitCountMonthlydata = dao.getVisitCountMonthlyData();
		return visitCountMonthlydata;
	}


	/**
	 * 사이트요약정보 얻기(오늘 회원가입수, 오늘 방문자 수, 오늘 작성 게시물 수)
	 */
	@Override
	public Map<String, Integer> getSummary() {
		Map<String,Integer> summaryMap = dao.getSummary();
		return summaryMap;
	}


	/**
	 * 오늘자 커뮤니티활성 데이터 얻기
	 */
	@Override
	public List<Integer> getCommunityActiveData() {
		Map<String,Integer> dataMap = dao.getCommunityActiveData();
		List<Integer> data = new ArrayList<Integer>();
		Collection<?> value = dataMap.values();
		Iterator<?> itr = value.iterator();
		while(itr.hasNext()) {
			data.add(Integer.parseInt(String.valueOf(itr.next())));
		}
		return data;
	}

}
