package com.gukmo.board.hw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gukmo.board.hw.service.InterBoardService;

@Controller
public class BoardController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;
	

}
