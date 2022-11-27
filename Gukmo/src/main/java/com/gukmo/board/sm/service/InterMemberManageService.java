package com.gukmo.board.sm.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

public interface InterMemberManageService {

	// ======== 일반 회원  시작 ========//
	
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
	
	// 정지 해제 
	int block_recovery(Map<String, String> paraMap);

	// 휴면 > 활동
	int sleep_recovery(Map<String, String> paraMap);
	// ======== 일반 회원 끝 ========//

////////////////////////////////////////////////////////////
	
	// ======== 학원 회원  시작 ========//

	// 학원회원 관리 페이지의 총 페이지 수 알아오기
	int getTotalCount_academy(Map<String, String> paraMap);

	// 학원회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	List<MemberVO> academymemberList(Map<String, String> paraMap);

	// 학원회원 상세보기 
	MemberVO getAcademyDetail(Map<String, String> paraMap);

	// 회원가입 요청 승인
	int Regi_agree(Map<String, String> paraMap);

	// ======== 학원 회원 끝 ========//
	

}
