package com.gukmo.board.hw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.repository.BoardDAO;
import com.gukmo.board.hw.repository.InterBoardDAO;
import com.gukmo.board.model.MemberDTO;

@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

	@Override
	public List<MemberDTO> select_member() {
		List<MemberDTO> memberList = new ArrayList<>();
		memberList = dao.select_member();
		return memberList;
	}
	
	
}
