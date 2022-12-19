package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class BugDAO implements InterBugDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;

	/**
	 * 버그리스트 얻기
	 */
	@Override
	public List<Map<String, String>> getBugList() {
		return gukmo_sql.selectList("chw.getBugList");
	}
	
	/**
	 * 버그 하나 얻기
	 */
	@Override
	public Map<String, String> getBug(String bugNum) {
		return gukmo_sql.selectOne("chw.getBug",bugNum);
	}

	/**
	 * 버그삭제하기
	 */
	@Override
	public boolean delete(String bugNum) {
		int result = gukmo_sql.delete("chw.deleteBug",bugNum);
		return result==1?true:false;
	}
	

}
