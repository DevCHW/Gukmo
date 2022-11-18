package com.gukmo.board.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.common.Sha256;
import com.gukmo.board.hw.repository.InterMemberDAO;
import com.gukmo.board.model.MemberVO;

@Service
public class MemberService implements InterMemberService{
	
	@Autowired
	private InterMemberDAO dao;
	
	/**
	 * 일반회원 가입하기
	 * @param 사용자가 입력한 정보가 들어있는 MemberVO 객체
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public void saveNormalMember(MemberVO input_member) throws Throwable{
		MemberVO member = new MemberVO(input_member.getUserid(), 
									   Sha256.encrypt(input_member.getPasswd()), //비밀번호 단방향 암호화, 
				 					   input_member.getStatus(), 
				 					   input_member.getUpdate_passwd_date(), 
				 					   input_member.getEmail(),
				 					   input_member.getEmail_acept(), 
				 					   input_member.getNickname(), 
				 					   input_member.getPoint(), 
				 					   input_member.getJoin_date(), 
				 					   "user.PNG",
				 					   input_member.getAcademy_name(), 
				 					   input_member.getCompany_num(), 
				 					   input_member.getHomepage(), 
				 					   input_member.getPhone(),
				 					   input_member.getUsername());
		
		int n = dao.insert_member_login(member);	//tbl_member_login에 insert
		if(n==1) {	//tbl_member에 insert가 성공시
			dao.insert_member(member);	//tbl_member에 insert
		}
	}
	
	
	

}
