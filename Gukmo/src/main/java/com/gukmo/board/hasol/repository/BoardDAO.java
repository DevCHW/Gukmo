package com.gukmo.board.hasol.repository;


import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.AdVO;


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
}
