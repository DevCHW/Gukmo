package com.gukmo.board.hasol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hasol.repository.InterBoardDAO;

@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

}
