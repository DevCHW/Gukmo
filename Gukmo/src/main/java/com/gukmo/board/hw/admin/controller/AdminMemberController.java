package com.gukmo.board.hw.admin.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	
	/**
	 * 회원상세보기 페이지 매핑
	 */
	@RequestMapping(value="/admin/member/detail2.do", method= {RequestMethod.GET})
	public String viewMemberDetail2(@RequestParam String userid, HttpServletRequest request) {
		MemberVO member = service.getUser(userid);
		
		if("정지".equals(member.getStatus())) {	//만약 회원의 상태가 "정지" 라면
			PenaltyVO penalty = service.getPenalty(member.getNickname());	//정지내역 구하기
			request.setAttribute("penalty", penalty);
		}
		
		request.setAttribute("member", member);
		return "admin/member/detail.tiles2";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 회원정보수정하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/edit.do", method= {RequestMethod.POST})
	public String editMember(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		
		boolean result = service.editMember(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 정지내역 insert하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/penalty/new.do", method= {RequestMethod.POST})
	public String insertPenalty(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		paraMap.put("penalty_period",paraMap.get("penalty_period").replace("일",""));
		boolean result = service.penaltyNew(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 정지내역 delete하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/penalty/delete.do", method= {RequestMethod.POST})
	public String deletePenalty(@RequestParam String nickname, HttpServletRequest request) {
		boolean result = service.penaltyDelete(nickname);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	


	
	/**
	 * 이메일 전송하기
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/sendEmail.do", method= {RequestMethod.POST})
	public String deletePenalty(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		boolean sendMailSuccess = service.sendEmail(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sendMailSuccess", sendMailSuccess);
		
		return jsonObj.toString();
	}
	
	
	
	
	
	
	
		
		

		

 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
