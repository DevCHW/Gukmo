package com.gukmo.board.sm.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;

@Repository
public class MemberDAO implements InterMemberDAO {

	@Resource
	private SqlSessionTemplate gukmo_sql;

	// 회원 관리 페이지의 총 페이지 수 알아오기
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = gukmo_sql.selectOne("ksm.getTotalCount", paraMap);
		return n;
	}

	
	// 회원 관리 페이지에 보여줄 회원 목록 리스트 뽑아오기
	@Override
	public List<MemberVO> memberList(Map<String, String> paraMap) {
		List<MemberVO> memberList = gukmo_sql.selectList("ksm.memberList" ,paraMap);
		return memberList;
	}

	// 회원정보 상세보기
	@Override
	public MemberVO MemberDetail(Map<String, String> paraMap) {
		MemberVO MemberDetail = gukmo_sql.selectOne("ksm.getMemberDetail", paraMap);
		return MemberDetail;
	}

	@Override
	public int addPenalty(PenaltyVO pvo) {
		int n = gukmo_sql.insert("ksm.addPenalty", pvo);
		return n;
	}

	// tbl_member_login에 해당 회원 status 변경(정지)
	@Override
	public int updateMemberStatus(String userid) {
		int n = gukmo_sql.update("ksm.updateMemberStatus", userid);
		return n;
	}


	// 정지 > 활동
	@Override
	public int block_recovery(Map<String, String> paraMap) {
		int n = gukmo_sql.update("ksm.block_recovery", paraMap);
		return n;
	}

	// 휴면 > 활동
	@Override
	public int sleep_recovery(Map<String, String> paraMap) {
		int n = gukmo_sql.update("ksm.sleep_recovery", paraMap);
		return n;
	}

}
