package com.gukmo.board.sun.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO implements InterAdminDAO {

	
	@Resource
	private SqlSessionTemplate gukmo_sql;

	
	// 기간별 새로운 게시물 수 
	@Override
	public List<Map<String, String>> newBoardCnt(Map<String, String> paraMap) {
		List<Map<String, String>> newBoardCnt_List = gukmo_sql.selectList("sun.newBoardCnt", paraMap); 
		return newBoardCnt_List;
	}
}
