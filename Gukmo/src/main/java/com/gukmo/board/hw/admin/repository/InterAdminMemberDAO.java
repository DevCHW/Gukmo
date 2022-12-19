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

	/**
	 * 검색데이터 갯수얻기
	 */
	int getTotalSearchCnt(Map<String, String> paraMap);

	/**
	 * 검색데이터 내역 가져오기
	 */
	List<Map<String, String>> getSearchData(Map<String, String> paraMap);

	/**
	 * 회원이 작성한 게시물 총갯수 얻기
	 */
	int getTotalWriteBoard(Map<String, String> paraMap);

	/**
	 * 회원이 작성한 게시물 내역 얻기
	 */
	List<Map<String, String>> getWriteBoardData(Map<String, String> paraMap);

	/**
	 * 회원 로그인 기록 총 갯수 얻기
	 */
	int getTotalLoginRecord(Map<String, String> paraMap);
	
	/**
	 * 회원 로그인 기록 데이터 얻기
	 */
	List<Map<String, String>> getLoginRecordData(Map<String, String> paraMap);

	/**
	 * 신고한내역 얻기
	 */
	List<Map<String, String>> getReportData(Map<String, String> paraMap);

	/**
	 * 신고한 내역 갯수 얻기
	 */
	int getTotalReportData(Map<String, String> paraMap);
	
	
	/**
	 * 신고당한내역 얻기
	 */
	List<Map<String, String>> getReportedData(Map<String, String> paraMap);
	
	/**
	 * 신고당한내역 총 갯수가지고 오기
	 */
	int getTotalReportedData(Map<String, String> paraMap);

	/**
	 * 승인 거부내역 등록
	 */
	boolean refuseNew(Map<String, String> paraMap);

	/**
	 * 승인 거부내역 삭제해주기
	 */
	boolean deleteRefuse(String userid);




}
