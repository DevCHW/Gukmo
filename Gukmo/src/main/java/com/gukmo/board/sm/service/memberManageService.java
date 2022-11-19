package com.gukmo.board.sm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;
import com.gukmo.board.sm.repository.InterMemberDAO;

@Service
public class memberManageService implements InterMemberManageService {

	// === #34. 의존객체 주입하기(DI: Dependency Injection) ===
	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterMemberDAO dao;
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.board.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
    // 그러므로 dao 는 null 이 아니다.
	
	// 회원 관리 페이지의 총 페이지 수 알아오기
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = dao.getTotalCount(paraMap);
		return n;
	}

	// 회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	@Override
	public List<MemberVO> memberList(Map<String, String> paraMap) {
		List<MemberVO> memberList = dao.memberList(paraMap);
		// System.out.println(memberList);
		return memberList;
	}

	// 회원 상세보기
	@Override
	public MemberVO getMemberDetail(Map<String, String> paraMap) {
		MemberVO MemberDetail = dao.MemberDetail(paraMap);
		return MemberDetail;
	}

	// 회원 정지 등록 완료 페이지
	@Override
	public int addPenalty(PenaltyVO pvo) {
		int n = dao.addPenalty(pvo);
		return n;
	}

	// tbl_member_login에 해당 회원 status 변경(정지)	
	@Override
	public int updateMemberStatus(String userid) {
		int n = dao.updateMemberStatus(userid);
		return n;
	}
	


}
