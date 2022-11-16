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

@Controller
public class LoginController {
	@Autowired
	InterLoginService service;
	@Autowired
	InterLoginDAO dao;
	
	/**
	 * 로그인 페이지 get요청
	 * @return login페이지
	 */
	@RequestMapping(value="/login.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public ModelAndView login(ModelAndView mav) {
      
      mav.setViewName("login/login.tiles1");
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
		
		boolean userExist = dao.userExistCheck(paraMap);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userExist", userExist);
		
		return jsonObj.toString();
	}
	
	
	
	
	/**
	 * 로그인되어질 회원의 상태 체크하기(정지,휴면,승인여부,비밀번호 변경시점 3개월)
	 * @param 유저가 입력한 아이디
	 * @return 활동중이라면 "활동" 정지회원이라면 "정지" 휴면회원이라면 "휴면" 승인대기라면 "대기"
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
	 * 로그인 완료처리하기
	 * @param 유저아이디, 비밀번호
	 * @return 이전페이지로 이동(추후 구현예정),로그인기록테이블 insert해주기(추후구현예정) 현재는 index 페이지로 이동,
	 */
	
	@RequestMapping(value="/login.do",method= {RequestMethod.POST})
	public String login_complete(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		
		HttpSession session = request.getSession();
		session.setAttribute("userid", userid);
		
		return "index.tiles1";
	}
	
	
	
	
	
	
	
}
