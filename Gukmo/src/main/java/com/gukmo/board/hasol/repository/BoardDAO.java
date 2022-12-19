package com.gukmo.board.hasol.repository;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;


@Repository
public class BoardDAO implements InterBoardDAO{
	
	@Resource
	private SqlSessionTemplate gukmo_sql;


	// 배너 노출을 위한 select 
	/*
	 * @Override public List<AdVO> getMainBannerList() { List<AdVO> mainBannerList =
	 * gukmo_sql.selectList("jhs.getMainBanner_advo"); return mainBannerList; }
	 * 
	 */
	
	// 검색 - 총 게시물 건 수를 알아오는 용도
	@Override
	public int getTotalCnt(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("jhs.getTotalCnt", paraMap);
		return n;
	}

	// 검색 - 총 게시물 리스트 알아오는 용도
	@Override
	public List<BoardVO> getSearchList(Map<String, String> paraMap) {
		List<BoardVO> searchList = gukmo_sql.selectList("jhs.getSearchList", paraMap);
		return searchList;
	}

	// 주간 해시태그 검색 용도
	@Override
	public List<HashtagVO> getTopHashList() {
		List<HashtagVO> topHashList = gukmo_sql.selectList("jhs.getTopHashList");
		return topHashList;
	}

	@Override
	public List<HashtagVO> getTopHashList_nodata() {
		List<HashtagVO> topHashList = gukmo_sql.selectList("jhs.getTopHashList_nodata");
		return topHashList;
	}

}
