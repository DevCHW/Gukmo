package com.gukmo.board.hgb.controller;

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
	
	@RequestMapping(value="/boardDetail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public ModelAndView login(ModelAndView mav) {
      
      mav.setViewName("board/community/boardDetail.tiles1");
      //   /WEB-INF/views/tiles1/board/community/boardDetail.jsp 파일을 생성한다.
      
      return mav;
	
	}
	
}
