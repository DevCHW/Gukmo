package com.gukmo.board.sun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.sun.repository.InterBoardDAO;


@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;


}
