package com.gukmo.board.hasol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gukmo.board.hasol.service.InterBoardService;

@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	

}
