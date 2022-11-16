package com.gukmo.board.sun.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.sun.service.InterBoardService;


@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	
	
	// 게시판 글목록 보기 페이지 요청
	@RequestMapping(value="/boardlist.do")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		List<BoardVO> boardList = null;
		
		Map<String, String> paraMap = new HashMap<>();
		boardList = service.boardList(paraMap);
		
		mav.addObject("boardList", boardList);
		
		mav.setViewName("board/community/freeBoardList.tiles1");
		
		return mav;
	}
	
	// 게시판 글쓰기 페이지 요청
	@RequestMapping(value="/communityNew.do" )
	public ModelAndView requiredLogin_communityNew(HttpServletRequest request, HttpServletResponse response, ModelAndView mav){
		
		// 카테고리 값 지정용
		// String detail_category = request.getParameter("detail_category");
		
		// mav.addObject("detail_category", detail_category);
		
		mav.setViewName("board/community/communityNew.tiles1");
		
		return mav;
	}

}
