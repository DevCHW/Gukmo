package com.gukmo.board.hw.admin.repository;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

@Repository
public class AdminMemberDAO implements InterAdminMemberDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;

	
	/**
	 * 회원 정보 얻기
	 */
	@Override
	public MemberVO getUser(String userid) {
		MemberVO member = gukmo_sql.selectOne("chw.getUser",userid);
		return member;
	}


	/**
	 * 정지당한 회원 정지사유 얻기
	 */
	@Override
	public PenaltyVO getPenalty(String nickname) {
		PenaltyVO penalty = gukmo_sql.selectOne("chw.getPenalty",nickname);
		return penalty;
	}



	/**
	 * 회원 정보 수정해주기
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int editMember(Map<String,String> paraMap) {
		int result1 = gukmo_sql.update("chw.editMember" ,paraMap);
		int result2 = gukmo_sql.update("chw.editMemberLogin" ,paraMap);
		return result1*result2;
	}


	/**
	 * 정지내역등록하기
	 */
	@Override
	public int penaltyNew(Map<String,String> paraMap) {
		int result = gukmo_sql.insert("chw.penaltyNew" ,paraMap);
		return result;
	}


	/**
	 * 정지내역 delete하기
	 */
	@Override
	public int penaltyDelete(String nickname) {
		int result = gukmo_sql.delete("chw.penaltyDelete" ,nickname);
		return result;
	}


	/**
	 * 교육기관회원 총 갯수 구하기
	 */
	@Override
	public int getTotalCntAcaMember(Map<String, String> paraMap) {
		int totalCnt = gukmo_sql.selectOne("chw.getTotalCnt_academy",paraMap);
		return totalCnt;
	}


	/**
	 * 교육기관회원리스트 얻기 
	 */
	@Override
	public List<Map<String,String>> getAcaMemberList(Map<String, String> paraMap) {
		List<Map<String,String>> data = gukmo_sql.selectList("chw.getAcaMemberList",paraMap);
		if(data != null && data.size() != 0) {
			for(int i=0; i<data.size(); i++) {
				String homepage = data.get(i).get("HOMEPAGE");
				homepage = "<a href="+homepage+">"+data.get(i).get("ACADEMY_NAME")+"</a>";
				data.get(i).put("HOMEPAGE", homepage);
			}//end of for--(링크걸기)
		}
		return data;
	}


	/**
	 * 일반회원 총 수 구하기
	 */
	@Override
	public int getTotalCntNormalMember(Map<String, String> paraMap) {
		int totalCnt = gukmo_sql.selectOne("chw.getTotalCntNormalMember",paraMap);
		return totalCnt;
	}


	/**
	 * 일반회원리스트 얻기 
	 */
	@Override
	public List<Map<String, String>> getNormalMemberList(Map<String, String> paraMap) {
		List<Map<String,String>> data = gukmo_sql.selectList("chw.getNormalMemberList",paraMap);
		return data;
	}


	
	/**
	 * 검색데이터 갯수 가져오기
	 */
	@Override
	public int getTotalSearchCnt(Map<String, String> paraMap) {
		return gukmo_sql.selectOne("chw.getTotalSearchCnt",paraMap);
	}


	/**
	 * 검색데이터 내역 가져오기
	 */
	@Override
	public List<Map<String, String>> getSearchData(Map<String, String> paraMap) {
		return gukmo_sql.selectList("chw.getSearchData",paraMap);
	}


	/**
	 * 회원이 작성한 게시물 총갯수 얻기
	 */
	@Override
	public int getTotalWriteBoard(Map<String, String> paraMap) {
		return gukmo_sql.selectOne("chw.getTotalWriteBoard",paraMap);
	}


	/**
	 * 회원이 작성한 게시물 내역 얻기
	 */
	@Override
	public List<Map<String, String>> getWriteBoardData(Map<String, String> paraMap) {
		List<Map<String, String>> data = gukmo_sql.selectList("chw.getWriteBoardData",paraMap);
		for(int i=0; i<data.size(); i++) {
			String subject = data.get(i).get("SUBJECT");
			if(subject.length() > 14) {
				subject = "<a href='http://localhost:9090/board/detail.do?boardNum="+String.valueOf(data.get(i).get("BOARD_NUM"))+"'>"+subject.substring(0,15)+"...</a>";
				data.get(i).put("SUBJECT",subject);
			} else {
				subject = "<a href='http://localhost:9090/board/detail.do?boardNum="+String.valueOf(data.get(i).get("BOARD_NUM"))+"'>"+subject+"</a>";
				data.get(i).put("SUBJECT",subject);
			}
		}
		return data;
	}


	/**
	 * 회원 로그인 기록 총 갯수 얻기
	 */
	@Override
	public int getTotalLoginRecord(Map<String, String> paraMap) {
		return gukmo_sql.selectOne("chw.getTotalLoginRecord",paraMap);
	}


	/**
	 * 회원 로그인 기록 데이터 얻기
	 */
	@Override
	public List<Map<String, String>> getLoginRecordData(Map<String, String> paraMap) {
		return gukmo_sql.selectList("chw.getLoginRecordData",paraMap);
	}


	/**
	 * 신고한내역 얻기
	 */
	@Override
	public List<Map<String, String>> getReportData(Map<String, String> paraMap) {
		return gukmo_sql.selectList("chw.getReportData",paraMap);
	}


	/**
	 * 신고한내역 갯수 얻기
	 */
	@Override
	public int getTotalReportData(Map<String, String> paraMap) {
		return gukmo_sql.selectOne("chw.getTotalReportData",paraMap);
	}


	/**
	 * 신고당한내역 얻기
	 */
	@Override
	public List<Map<String, String>> getReportedData(Map<String, String> paraMap) {
		return gukmo_sql.selectList("chw.getReportedData",paraMap);
	}


	/**
	 * 신고당한내역 갯수 얻기
	 */
	@Override
	public int getTotalReportedData(Map<String, String> paraMap) {
		return gukmo_sql.selectOne("chw.getTotalReportedData",paraMap);
	}


	
	
	








	
	
	
}
