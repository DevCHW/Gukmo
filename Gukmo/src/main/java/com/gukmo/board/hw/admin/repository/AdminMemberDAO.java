package com.gukmo.board.hw.admin.repository;


import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

@Repository
public class AdminMemberDAO implements InterAdminMemberDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;

	
	/**
	 * 회원 정보 얻기
	 */
	@Override
	public MemberVO getUser(String userid) {
		MemberVO member = gukmo_sql.selectOne("chw.getUser",userid);
		return member;
	}


	/**
	 * 정지당한 회원 정지사유 얻기
	 */
	@Override
	public PenaltyVO getPenalty(String nickname) {
		PenaltyVO penalty = gukmo_sql.selectOne("chw.getPenalty",nickname);
		return penalty;
	}
	
	
	
	
	
}
