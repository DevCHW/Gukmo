package com.gukmo.board.hw.admin.controller;

import javax.servlet.http.HttpServletRequest;

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
	public String helloAdmin2(HttpServletRequest request) {
		return "admin/index.tiles2";
	}
	
	
	/**
	 * 일반회원내역페이지 매핑
	 */
	@RequestMapping(value="/admin/member/normal/list2.do", method= {RequestMethod.GET})
	public String viewNormalMember(HttpServletRequest request) {
		return "admin/member/normal/list.tiles2";
	}
	
	
	
	
	
	
	
	
	
}
