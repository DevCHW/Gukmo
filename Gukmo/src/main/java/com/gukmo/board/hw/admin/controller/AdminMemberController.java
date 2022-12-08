package com.gukmo.board.hw.admin.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gukmo.board.hw.admin.service.InterAdminMemberService;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;



@Controller
public class AdminMemberController {
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdminMemberService service;
	
	
	
	
	/**
	 * 회원상세보기 페이지 매핑
	 */
	@RequestMapping(value="/admin/member/detail.do", method= {RequestMethod.GET})
	public String viewMemberDetail(@RequestParam String userid, HttpServletRequest request) {
		MemberVO member = service.getUser(userid);
		
		if("정지".equals(member.getStatus())) {	//만약 회원의 상태가 "정지" 라면
			PenaltyVO penalty = service.getPenalty(member.getNickname());	//정지내역 구하기
			request.setAttribute("penalty", penalty);
		}
		
		request.setAttribute("member", member);
		return "admin/member/detail.tiles1";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
