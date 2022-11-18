package com.gukmo.board.sm.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.MemberVO;

@Repository
public class MemberDAO implements InterMemberDAO {

	@Resource
	private SqlSessionTemplate gukmo_sql;

	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount", paraMap);
		return n;
	}

	@Override
	public List<MemberVO> memberList(Map<String, String> paraMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
