package com.gukmo.board.aop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.hasol.service.InterAlarmService;
import com.gukmo.board.sm.controller.BoardController;

@Aspect     
@Component  
public class BoardAOP {

	@Pointcut("execution(public * com.gukmo..*Controller.requiredLogin_*(..) )")
	public void requiredLogin() {}
	
	@Before("requiredLogin()")
	public void loginCheck(JoinPoint joinpoint) {
		
		HttpServletRequest request = (HttpServletRequest) joinpoint.getArgs()[0];    
		HttpServletResponse response = (HttpServletResponse) joinpoint.getArgs()[1]; 
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			String loc = "";
			try {
				loc = request.getContextPath()+"/login.do?returnUrl="+URLEncoder.encode(MyUtil.getCurrentURL(request), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			request.setAttribute("loc", loc);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/returnLogin.jsp"); 
			
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// 알람에 값 넢는 AOP
	//@Pointcut("execution(public * com.gukmo..*Controller.setAlarm_*(..) )")
	//public void setAlarm() {}
	
	//@Autowired  
	//private InterAlarmService alarm_service;
	
	//@SuppressWarnings("unchecked")
	//@After("setAlarm()")
	//public void setAlarm(JoinPoint joinpoint) {
		
//		System.out.println("여기 와?");
		
//		System.out.println("joinpoint "+joinpoint);
		//HttpServletRequest request = (HttpServletRequest) joinpoint.getArgs()[0];		
		
		//Map<String,String> paraMap = (Map<String, String>) request.getAttribute("alarmMap");
		
		// System.out.println(paraMap);
		// System.out.println("여기 와?");

		//int result = alarm_service.setAlarm(paraMap);
	//	System.out.println("aop 확인용 : " + n);
	
	//} //end of setAlarm
	
	
		
		
		
		
		
		
	 


}
