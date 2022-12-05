package com.gukmo.board.sm.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.PenaltyVO;

public interface InterPenaltyDAO {

	// 정지회원 총 페이지수 알아오기
	int getTotalPenaltyCount(Map<String, String> paraMap);

	// 정지회원 리스트 뽑아오기
	List<PenaltyVO> getPenaltyList(Map<String, String> paraMap);

	// 정지내역 상세보기
	PenaltyVO getPenaltyDetail(Map<String, String> paraMap);

	// 정지 관련 정보 상세보기 
	String getPenaltyId(Map<String, String> paraMap);

	// 정지 테이블에서 해당 회원 삭제
	int del_penalty(Map<String, String> paraMap);

}
