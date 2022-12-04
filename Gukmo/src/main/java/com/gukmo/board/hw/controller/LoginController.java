package com.gukmo.board.hw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hw.repository.InterLoginDAO;
import com.gukmo.board.hw.service.InterLoginService;
import com.gukmo.board.model.MemberVO;

@Controller
public class LoginController {
	@Autowired
	InterLoginService service;
	@Autowired
	InterLoginDAO dao;
	
	/**
	 * 로그인 페이지 url매핑
	 */
	@RequestMapping(value="/login.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public ModelAndView login(ModelAndView mav, HttpServletRequest request) {
	  HttpSession session = request.getSession();
	  if(session.getAttribute("user") != null) {	//로그인한 유저가 있다면
		  mav.setViewName("redirect:/index.do");
	  }
	  else {										//로그인한 유저가 없다면
		  mav.setViewName("login/login.tiles1");
	  }
      //   /WEB-INF/views/tiles1/login/login.jsp 파일을 생성한다.
      
      return mav;
   }
	
	
	
	/**
	 * 로그인할 유저가 존재하는지 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호
	 * @return 유저가 존재하면 true, 유저가 존재하지않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/userExist.do", method= {RequestMethod.POST})
	public String loginCheck(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("userid", userid);
		paraMap.put("passwd", passwd);
		boolean userExist = false;
		if("admin".equals(userid)) {	//사용자가 입력한 아이디가 admin이라면
			userExist = service.adminExistCheck(paraMap);
		}
		else {
			userExist = service.userExistCheck(paraMap);
		}
			
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userExist", userExist);
		
		return jsonObj.toString();
	}
	
	
	
	
	/**
	 * 로그인되어질 회원의 상태 체크하기(정지,휴면,대기,비밀번호 변경시점 3개월)
	 * @param 유저가 입력한 아이디
	 * @return 활동중이라면 "활동" 정지회원이라면 "정지" 휴면회원이라면 "휴면" 승인대기라면 "대기"
	 * 		      비밀번호 변경시점 3개월 이상이라면 "비밀번호 변경 권장"
	 */
	@ResponseBody
	@RequestMapping(value="/statusCheck.do",method= {RequestMethod.POST})
	public String status_check(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		
		String status = service.statusCheck(userid);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		
		return jsonObj.toString();
	}
	
	
	
	
	
	/**
	 * 로그인 완료처리하기(로그인기록테이블 insert해주기,MemberVO객체 세션에 저장)
	 * @param 유저아이디, 비밀번호
	 * @return 이전페이지로 이동(추후 구현예정),(추후구현예정) 현재는 index 페이지로 이동,
	 */
	
	@RequestMapping(value="/login.do",method= {RequestMethod.POST})
	public String login_complete(HttpServletRequest request) {
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("userid",request.getParameter("userid"));
		paraMap.put("client_ip",request.getRemoteAddr());
		System.out.println("확인용 userid = " + paraMap.get("userid"));
		System.out.println("확인용 client_ip = " + paraMap.get("client_ip"));
		
		
		MemberVO user = null;
		if("admin".equals(request.getParameter("userid"))) {	//관리자로 로그인하였다면
			String userid = request.getParameter("userid");
			dao.adminLoginRecordSave(paraMap);					//관리자로그인기록하기
			
//			user = new MemberVO(userid, 						// 아이디
//							    null, 							// 비밀번호	
//							    "활동", 							// 상태
//							    null, 							// 마지막비밀번호변경일자
//							    null,							// 이메일
//							    null, 							// 이메일 수신동의
//							    "국비의모든것 관리팀",					// 닉네임 
//							    "9999", 						// 활동점수
//							    null, 							// 가입일자
//							    "user.PNG",							// 프로필이미지
//							    null, 							// 교육기관명
//							    null,  							// 사업자번호
//							    null,							// 홈페이지
//							    null,							// 연락처
//							    null,							// 회원 이름
//							    "0",
//							    "0",
//							    "0");     					// 소셜로그인 여부
		}
		else {	//관리자가 아닌회원으로 로그인하였다면
			user = service.login_complete(paraMap);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		return "redirect:/index.do";
	}
	
	
	
	/**
	 * 로그아웃 처리하기
	 */
	@RequestMapping(value="/logout.do")	//GET이나 POST 둘다 처리하기
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {	//로그인한 유저가 있다면
			session.removeAttribute("user");
			return "redirect:/index.do";
		}
		else {										//로그인한유저가 없다면
			return "redirect:/index.do";
		}
	}
	
	
	
	
	
	
	
	
	
	
}
