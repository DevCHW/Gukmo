package com.gukmo.board.hw.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hw.service.InterBoardService;

@Controller
public class BoardController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;
	
	
	@RequestMapping(value="/login.do", method= {RequestMethod.GET})
	public ModelAndView login(ModelAndView mav) {
		System.out.println("요청이왔나요");
		mav.setViewName("login/login.tiles1");
		//   /WEB-INF/views/tiles1/login/login.jsp 파일을 생성한다.
		return mav;
	}
	
	
	

}
