package com.gukmo.board.hw.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.util.MultiValueMap;

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

	/**
	 * 교육기관회원 총 수 구하기
	 */
	int getTotalCntAcaMember(Map<String, String> paraMap);
	
	/**
	 * 교육기관회원 페이징 처리 한 데이터 갖고오기
	 */
	List<Map<String, String>> getAcaMemberList(Map<String, String> paraMap);

	/**
	 * 일반회원 총 수 구하기
	 */
	int getTotalCntNormalMember(Map<String, String> paraMap);

	/**
	 * 일반회원 페이징 처리 한 데이터 갖고오기
	 */
	List<Map<String, String>> getNormalMemberList(Map<String, String> paraMap);





}
