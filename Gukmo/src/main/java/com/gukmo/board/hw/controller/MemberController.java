package com.gukmo.board.hw.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hw.repository.InterMemberDAO;
import com.gukmo.board.hw.service.InterMemberService;

@Controller
public class MemberController {
	
	@Autowired
	private InterMemberService service;
	
	@Autowired
	private InterMemberDAO dao;
	
	/**
	 * 회원아이디가 존재하는지 여부 검사
	 * @return 회원아이디가 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/idExist.do", method= {RequestMethod.POST})
	public String idExistCheck(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		boolean idExist = dao.idExistCheck(userid);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("idExist", idExist);
		
		return jsonObj.toString();
	}
	
      
}
	
	
	
	
	
	
	
	

