package com.gukmo.board.hw.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.admin.repository.InterAdminMemberDAO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

@Service
public class AdminMemberService implements InterAdminMemberService{
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdminMemberDAO dao;

	/**
	 * 회원 정보 얻기
	 */
	@Override
	public MemberVO getUser(String userid) {
		MemberVO member = dao.getUser(userid);
		return member;
	}

	
	/**
	 * 정지당한 회원 정지사유 얻기
	 */
	@Override
	public PenaltyVO getPenalty(String nickname) {
		PenaltyVO penalty = dao.getPenalty(nickname);
		return penalty;
	}
	
	
	
	
	
	
	
	
	
	
	
}
