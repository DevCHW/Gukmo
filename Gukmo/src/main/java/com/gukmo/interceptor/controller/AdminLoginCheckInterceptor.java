package com.gukmo.interceptor.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.model.MemberVO;


public class AdminLoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception { 
	
		// 로그인 여부 검사
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO) session.getAttribute("user");
		
		if( user == null || 
			(user != null && !"관리자".equals(user.getAuthority())) ) {
			
			// 로그인이 되지 않았거나 로그인 되어진 사용자의 등급이 10 미만 이라면
			String message = "관리자만 접근 가능한 페이지입니다.";
			
			String loc = "";
			try {
				loc = request.getContextPath()+"/login.do?returnUrl="+URLEncoder.encode(MyUtil.getCurrentURL(request), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/msg.jsp"); 
			
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
			return false;
		}
		
		return true;
	}	
}
