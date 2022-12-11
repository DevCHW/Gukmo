package com.gukmo.board.hw.admin.repository;


import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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



	/**
	 * 회원 정보 수정해주기
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int editMember(Map<String,String> paraMap) {
		int result1 = gukmo_sql.update("chw.editMember" ,paraMap);
		int result2 = gukmo_sql.update("chw.editMemberLogin" ,paraMap);
		return result1*result2;
	}


	/**
	 * 정지내역등록하기
	 */
	@Override
	public int penaltyNew(Map<String,String> paraMap) {
		int result = gukmo_sql.insert("chw.penaltyNew" ,paraMap);
		return result;
	}


	/**
	 * 정지내역 delete하기
	 */
	@Override
	public int penaltyDelete(String nickname) {
		int result = gukmo_sql.delete("chw.penaltyDelete" ,nickname);
		return result;
	}








	
	
	
}
