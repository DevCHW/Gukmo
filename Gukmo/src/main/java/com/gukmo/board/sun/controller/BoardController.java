package com.gukmo.board.sun.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		
		mav.setViewName("board/boardList.tiles1");
		
		return mav;
	}

}
