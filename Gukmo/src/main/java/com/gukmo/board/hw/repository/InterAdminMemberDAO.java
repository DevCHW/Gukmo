package com.gukmo.board.hw.repository;

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

}
