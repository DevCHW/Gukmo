package com.gukmo.board.hw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hw.service.InterMemberService;

@Controller
public class MemberController {
	
	@Autowired
	private InterMemberService service;
	
	
	@RequestMapping(value="/login.do", method = {RequestMethod.GET})
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("login/login.jsp");
		
		return mav;
	}
	
	
	
	
	
	

}
