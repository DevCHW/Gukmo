package com.gukmo.board.hw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	
	//============================================================================ //
	//============================= 회원가입 관련 시작 ================================== //
	//============================================================================ //
	/**
	 * 이용약관페이지 url매핑
	 */
	@RequestMapping(value="/TOS.do", method= {RequestMethod.GET})
	public String viewTOS(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "member/TOS.tiles1";
			// /WEB-INF/views/tiles1/member/TOS.jsp 페이지.
		}
		return "redirect:/index.do";
	}
	
	
	/**
	 * 회원가입페이지 url매핑
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
	
	
	//=========================================================================== //
	//============================= 회원가입 관련 끝=================================== //
	//=========================================================================== //
	
	
	
	
	
	
	
	//============================================================================== //
	//============================= 마이페이지 관련 시작=================================== //
	//============================================================================== //
	
	/**
	 * 활동내역 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/member/activities.do", method= {RequestMethod.GET})
	public String viewActivities(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "redirect:/index.do";
		}
		
		return "member/activities.tiles1";
		// /WEB-INF/views/tiles1/member/activities.jsp 페이지.
	}
	
	
	
	/**
	 * 내계정 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/member/myId.do", method= {RequestMethod.GET})
	public String viewMyId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "redirect:/index.do";
		}
		
		
		return "member/myId.tiles1";
		// /WEB-INF/views/tiles1/member/myId.tiles1.jsp 페이지.
	}
	
	
	
	/**
	 * 내정보 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/member/myInfo.do", method= {RequestMethod.GET})
	public String viewMyInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "redirect:/index.do";
		}
		return "/member/myInfo.tiles1";
		// /WEB-INF/views/tiles1/member/myInfo.jsp 페이지.
	}
	
	/**
	 * 개인정보보호방침 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/policy/privacy.do", method= {RequestMethod.GET})
	public String viewPrivacyPolicy(HttpServletRequest request) {
		return "policy/privacy_policy.tiles1";
		// /WEB-INF/views/tiles1/policy/privacy_policy.jsp 페이지.
	}
	
	
	//============================================================================== //
	//============================= 마이페이지 관련 시작=================================== //
	//============================================================================== //
	
	
	
      
}
	
	
	
	
	
	
	
	

