package com.gukmo.board.hw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gukmo.board.hw.service.InterLoginService;

@Controller
public class LoginController {
	@Autowired
	
	InterLoginService service;
}
