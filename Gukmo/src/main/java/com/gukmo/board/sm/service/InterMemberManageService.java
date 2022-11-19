package com.gukmo.board.sm.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

public interface InterMemberManageService {

	// 회원 관리 페이지의 총 페이지 수 알아오기
	int getTotalCount(Map<String, String> paraMap);

	// 회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	List<MemberVO> memberList(Map<String, String> paraMap);

	// 회원 상세보기 
	MemberVO getMemberDetail(Map<String, String> paraMap);

	// 회원 정지 등록 완료 페이지
	int addPenalty(PenaltyVO pvo);

	// tbl_member_login에 해당 회원 status 변경(정지)
	int updateMemberStatus(String userid);

}
