package com.gukmo.board.sun.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gukmo.board.sun.service.InterBoardService;


@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	
	
	

}
