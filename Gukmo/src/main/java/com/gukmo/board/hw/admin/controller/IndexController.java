package com.gukmo.board.hw.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gukmo.board.hw.admin.service.InterIndexService;

@Controller
public class IndexController {
	@Autowired
	private InterIndexService service;
	
	
	/**
	 * 관리자메인페이지 매핑
	 */
	@RequestMapping(value="/admin/index.do", method= {RequestMethod.GET})
	public String viewTOS(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "index.tiles1";
			// /WEB-INF/views/tiles1/member/TOS.jsp 페이지.
		}
		return "admin/index.tiles1";
	}
	
}
