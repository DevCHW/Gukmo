package com.gukmo.board.sm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;
import com.gukmo.board.model.ReportVO;
import com.gukmo.board.sm.repository.InterMemberDAO;
import com.gukmo.board.sm.repository.InterPenaltyDAO;

@Service
public class memberManageService implements InterMemberManageService {

	// === #34. 의존객체 주입하기(DI: Dependency Injection) ===
	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterMemberDAO dao;
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.board.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
    // 그러므로 dao 는 null 이 아니다.
	
	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterPenaltyDAO dao_p;
	
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

	// 정지 해제 
	@Override
	public int block_recovery(Map<String, String> paraMap) {
		int n = dao.block_recovery(paraMap);
		return n;
	}
	
	// 휴면 해제 
	@Override
	public int sleep_recovery(Map<String, String> paraMap) {
		int n = dao.sleep_recovery(paraMap);
		return n;
	}

	// 학원 총페이지수 알아오기
	@Override
	public int getTotalCount_academy(Map<String, String> paraMap) {
		int n = dao.getTotalCount_academy(paraMap);
		return n;	
		
	}

	// 학원회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	@Override
	public List<MemberVO> academymemberList(Map<String, String> paraMap) {
		List<MemberVO> academymemberList = dao.academymemberList(paraMap);
		// System.out.println(memberList);
		return academymemberList;
	}

	// 학원회원 상세보기
	@Override
	public MemberVO getAcademyDetail(Map<String, String> paraMap) {
		MemberVO aca_MemberDetail = dao.aca_MemberDetail(paraMap);
		return aca_MemberDetail;
	}

	// 학원 회원가입 승인 
	@Override
	public int Regi_agree(Map<String, String> paraMap) {
		int n = dao.Regi_agree(paraMap);
		return n;
	}

	// 회원관리에서 해당 회원의 활동 내역 가져오기
	@Override
	public List<ActivityVO> getActList(Map<String, String> paraMap) {
		List<ActivityVO> ActList = dao.getActList(paraMap);
		return ActList;
	}

	 // 활동 내역 총 페이지수 알아오기
	@Override
	public int getTotalActCount(Map<String, String> paraMap) {
		int totalActCount = dao.getTotalActCount(paraMap);
		return totalActCount;
	}

	// 멤버 디테일에서 검색 조건에 맞는 활동내역 리스트 불러오기
	@Override
	public List<ActivityVO> getDetailActList(Map<String, String> paraMap) {
		List<ActivityVO> DetailActList = dao.getDetailActList(paraMap);
		return DetailActList;
	}

	// 정지회원 총 페이지수 알아오기
	@Override
	public int getTotalCount_penalty(Map<String, String> paraMap) {
		int totalPenaltyCount = dao_p.getTotalPenaltyCount(paraMap);
		return totalPenaltyCount;
	}

	// 정지회원 리스트 뽑아오기
	@Override
	public List<PenaltyVO> penaltyList(Map<String, String> paraMap) {
		List<PenaltyVO> penaltyList = dao_p.getPenaltyList(paraMap);
		return penaltyList;
	}

	// 정지내역 상세보기
	@Override
	public PenaltyVO getPenaltyDetail(Map<String, String> paraMap) {
		PenaltyVO penaltyDetail = dao_p.getPenaltyDetail(paraMap);
		return penaltyDetail;
	}

	// 정지 관련 정보 상세보기 
	@Override
	public String getPenaltyId(Map<String, String> paraMap) {
		String id = dao_p.getPenaltyId(paraMap);
		return id;
	}

	// 정지 테이블에서 해당 회원 삭제
	@Override
	public int del_penalty(Map<String, String> paraMap) {
		int n = dao_p.del_penalty(paraMap);
		return n;
	}



}
