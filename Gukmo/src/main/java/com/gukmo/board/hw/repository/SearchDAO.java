package com.gukmo.board.hw.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDAO implements InterSearchDAO{
	
	@Resource
	private SqlSessionTemplate gukmo_sql;

	
	/**
	 * 검색어로 검색한 결과물의 갯수 알아오기
	 * @param 검색어,상세카테고리
	 * @return 검색결과 수
	 */
	@Override
	public int getResultByKeyword(Map<String,String> paraMap) {
		int result = gukmo_sql.selectOne("chw.getResultByKeyword",paraMap);
		return result;
	}

	
	/**
	 * 검색어 저장하기
	 * @param 검색어,검색ip,유저아이디(null 일수도있음)
	 */
	@Override
	public int saveKeyWord(Map<String, String> paraMap) {
		int result = gukmo_sql.insert("chw.saveKeyWord",paraMap);
		return result;
	}

	/**
	 * 검색아이피에서 가장 최근 로그인한 유저아이디 알아오기(null일수도 있음)
	 * @param 검색ip(null 일수도있음)
	 */
	@Override
	public String getUseridWithIp(String search_ip) {
		System.out.println("왔지!befor sql");
		String userid = gukmo_sql.selectOne("chw.getUseridWithIp",search_ip);
		System.out.println("왔지!");
		return userid;
	}

}
