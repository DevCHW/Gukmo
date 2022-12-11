package com.gukmo.board.sm.admin.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.MemberVO;

@Repository
public class MemberDAO implements InterMemberDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	
	/**
	 * 회원 관리 페이지의 총 페이지 수 알아오기
	 */
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount", paraMap);
		return n;
	}

	
	/**
	 * 회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	 */
	@Override
	public List<MemberVO> memberList(Map<String, String> paraMap) {
		List<MemberVO> memberList = gukmo_sql.selectList("ksm.memberList" ,paraMap);
		return memberList;
	}

	
	/**
	 * 교육기관회원 관리 페이지의 총 페이지 수 알아오기
	 */
	@Override
	public int getTotalCount_academy(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount_academy", paraMap);
		return n;
	}

	
	/**
	 * 교육기관회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	 */
	@Override
	public List<MemberVO> academymemberList(Map<String, String> paraMap) {
		List<MemberVO> academymemberList = gukmo_sql.selectList("ksm.academymemberList" ,paraMap);
		return academymemberList;
	}
	
	
	
	
}
