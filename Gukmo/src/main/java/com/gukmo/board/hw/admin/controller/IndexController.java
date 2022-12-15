package com.gukmo.board.hw.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gukmo.board.hw.admin.service.InterIndexService;
import com.gukmo.board.model.BoardVO;

@Controller
public class IndexController {
	@Autowired
	private InterIndexService service;
	
	
	/**
	 * 관리자메인페이지 매핑
	 */
	@RequestMapping(value="/admin/index.do", method= {RequestMethod.GET})
	public String helloAdmin(HttpServletRequest request) {
		//인기 게시물 3개 가져오기
		List<BoardVO> popularBoardList = service.getPopularBoard();
		request.setAttribute("popularBoardList", popularBoardList);
		return "admin/index.tiles2";
	}
	
	
	
	
	
	
	
	
	
	
	
}
