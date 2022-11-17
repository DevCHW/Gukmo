package com.gukmo.board.hw.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.repository.InterMemberDAO;
import com.gukmo.board.hw.service.InterMemberService;
import com.gukmo.board.model.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private InterMemberService service;
	
	@Autowired
	private InterMemberDAO dao;
	
	
	/**
	 * 회원가입페이지 url 매핑
	 */
	@RequestMapping(value="/signup.do", method= {RequestMethod.GET})
	public String viewSignup(HttpServletRequest request) {
		return "member/signup.tiles1";
		// /WEB-INF/views/tiles1/member/signup.jsp 페이지.
	}
	
	
	/**
	 * 회원아이디가 존재하는지 여부 검사
	 * @return 회원아이디가 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/idExistCheck.do", method= {RequestMethod.POST})
	public String idExistCheck(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		
		boolean idExist = true;
		if("admin".equals(userid)) {
			idExist = true;
		}
		else {
			idExist = dao.idExistCheck(userid);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("idExist", idExist);
		
		return jsonObj.toString();
	}
	
	/**
	 * 가입된 닉네임이 존재하는지 여부 검사
	 * @return 가입된 닉네임이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/nicknameExistCheck.do", method= {RequestMethod.POST})
	public String nicknameExistCheck(HttpServletRequest request) {
		String nickname = request.getParameter("nickname");
		boolean nicknameExist = dao.nicknameExistCheck(nickname);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("nicknameExist", nicknameExist);
		
		return jsonObj.toString();
	}
	
	/**
	 * 가입된 이메일이 존재하는지 여부 검사
	 * @return 가입된 이메일이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/emailExistCheck.do", method= {RequestMethod.POST})
	public String emailExistCheck(HttpServletRequest request) {
		String email = request.getParameter("email");
		boolean emailExist = dao.emailExistCheck(email);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("emailExist", emailExist);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 회원가입 하기
	 */
	@RequestMapping(value="/member/save.do", method= {RequestMethod.POST})
	public String saveMember(MemberVO input_member,HttpServletRequest request) throws Throwable {
		if(input_member.getAcademy_name() != null) {	//교육기관회원 가입인경우
			System.out.println("교육기관회원 가입입니다.");
		}
		else {	//일반회원 가입인 경우
			service.saveNormalMember(input_member);
			
		}
		String message = "회원가입 완료!";
		String loc = request.getContextPath()+"/login.do";
		
		request.setAttribute("message", message);
		request.setAttribute("loc", loc);
		
		return "msg";
	}
	
	
	
      
}
	
	
	
	
	
	
	
	

