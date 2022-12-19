package com.gukmo.board.hw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gukmo.board.hw.repository.InterMemberDAO;

@Controller
public class AdvertisementController {
	
	@Autowired
	private InterMemberDAO dao;
	/**
	 * 광고문의 페이지 매핑
	 */
	@RequestMapping(value="/advertisement/inquiry.do", method= {RequestMethod.GET})
	public String required_login_bugNew(HttpServletRequest request) {
		int cntMember = dao.cntMember();
		
		request.setAttribute("cntMember", cntMember);
		return "inquiry.tiles1";
	}

}
