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
	
	/**
	 * 스터디 게시판리스트 매핑
	 */
	@RequestMapping(value="/community/studies.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public ModelAndView login(ModelAndView mav) {
      
      mav.setViewName("board/community/study.tiles1");
      //   /WEB-INF/views/tiles1/coummunity/study.jsp 파일
      
      return mav;
   }
	

	
	
	

}
