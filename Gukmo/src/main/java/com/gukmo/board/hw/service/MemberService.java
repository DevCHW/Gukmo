package com.gukmo.board.hw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.repository.InterMemberDAO;

@Service
public class MemberService implements InterMemberService{
	
	@Autowired
	private InterMemberDAO dao;
	
	
	

}
