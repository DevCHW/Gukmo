package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

public interface InterAdminMemberDAO {

	
	/**
	 * 회원 정보 얻기
	 */
	MemberVO getUser(String userid);

	/**
	 * 정지당한 회원 정지사유 얻기
	 */
	PenaltyVO getPenalty(String nickname);
	
	

	/**
	 * 회원 정보 수정해주기
	 */
	int editMember(Map<String,String> paraMap);

	/**
	 * 정지내역등록하기
	 */
	int penaltyNew(Map<String,String> paraMap);

	/**
	 * 정지내역 delete하기
	 */
	int penaltyDelete(String nickname);

	/**
	 * 교육기관회원 총 수 구하기
	 */
	int getTotalCntAcaMember(Map<String,String> paraMap);
	
	/**
	 * 교육기관회원리스트 얻기 
	 */
	List<Map<String,String>> getAcaMemberList(Map<String,String> paraMap);

	/**
	 * 일반회원 총 수 구하기
	 */
	int getTotalCntNormalMember(Map<String, String> paraMap);

	/**
	 * 일반회원리스트 얻기 
	 */
	List<Map<String, String>> getNormalMemberList(Map<String, String> paraMap);




}
