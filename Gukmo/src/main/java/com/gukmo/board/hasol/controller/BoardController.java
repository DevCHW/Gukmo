package com.gukmo.board.hasol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hasol.service.InterBoardService;

@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	
}
