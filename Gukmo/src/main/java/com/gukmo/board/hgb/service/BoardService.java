package com.gukmo.board.hgb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hgb.repository.InterBoardDAO;

@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

	
	
}
