package com.gukmo.board.sm.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.sm.admin.repository.InterMemberDAO;

@Service
public class MemberService implements InterMemberService{

	@Autowired
	private InterMemberDAO dao;
	
	
	/**
	 * 회원 관리 페이지의 총 페이지 수 알아오기
	 */
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = dao.getTotalCount(paraMap);
		return n;
	}

	/**
	 * 회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	 */
	@Override
	public List<MemberVO> memberList(Map<String, String> paraMap) {
		List<MemberVO> memberList = dao.memberList(paraMap);
		// System.out.println(memberList);
		return memberList;
	}

	@Override
	public int getTotalCount_academy(Map<String, String> paraMap) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<MemberVO> academymemberList(Map<String, String> paraMap) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
