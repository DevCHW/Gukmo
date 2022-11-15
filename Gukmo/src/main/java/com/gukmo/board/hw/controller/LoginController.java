package com.gukmo.board.hw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		System.out.println("요청이 왔나요");
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("userid", userid);
		paraMap.put("passwd", passwd);
		
		boolean userExist = dao.userExistCheck(paraMap);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userExist", userExist);
		
		return jsonObj.toString();
	}
	
	
	
	@RequestMapping(value="login_complete.do",method= {RequestMethod.POST})
	public String login_complete(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		return "";
	}
	
	
	
	
	
	
	
}
