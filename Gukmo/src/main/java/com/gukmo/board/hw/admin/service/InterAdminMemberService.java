package com.gukmo.board.hw.admin.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

public interface InterAdminMemberService {

	/**
	 * 회원 정보 얻기
	 */
	MemberVO getUser(String userid);

	/**
	 * 정지당한 회원 정지사유 얻기
	 */
	PenaltyVO getPenalty(String nickname);
	
	
	
	/**
	 * 회원 관리 페이지의 총 페이지 수 알아오기
	 */
	int getTotalCount(Map<String, String> paraMap);

	
	/**
	 * 회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	 */
	List<MemberVO> memberList(Map<String, String> paraMap);


	
	/**
	 * 교육기관회원 관리 페이지의 총 페이지 수 알아오기
	 */
	int getTotalCount_academy(Map<String, String> paraMap);

	/**
	 * 교육기관회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	 */
	List<MemberVO> academymemberList(Map<String, String> paraMap);

	
	/**
	 * 회원 정보 수정해주기
	 * @param paraMap
	 * @return
	 */
	boolean editMember(Map<String,String> paraMap);

	/**
	 * 정지내역등록하기
	 */
	boolean penaltyNew(Map<String,String> paraMap);

	/**
	 * 정지내역 delete하기
	 */
	boolean penaltyDelete(String nickname);

	/**
	 * 회원에게 이메일 전송하기
	 */
	boolean sendEmail(Map<String, String> paraMap);




}
