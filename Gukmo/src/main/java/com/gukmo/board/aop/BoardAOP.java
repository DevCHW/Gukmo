package com.gukmo.board.aop;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.hw.repository.InterBoardDAO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.sun.service.InterBoardService;

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
			String message = "먼저 로그인 하세요";
			String loc = request.getContextPath()+"/login.do";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			// 로그인 성공후 로그인 하기전 페이지로 돌아가는 작업
			String url = MyUtil.getCurrentURL(request);
			session.setAttribute("goBackURL", url);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/msg.jsp"); 
			
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// 글쓰기, 댓글 작성시 활동 점수 update, 활동기록 insert (after)
	@Pointcut("execution(public * com.gukmo..*Controller.pointPlusActivityLog_*(..) )")
	public void pointPlusActivityLog() {}
	
	@Autowired  
	private InterBoardService service;
	
	@SuppressWarnings("unchecked")
	@After("pointPlusActivityLog()")
	public void pointPlusActivityLog(JoinPoint joinpoint) {
		
		Map<String, Object> paraMap = (Map<String, Object>) joinpoint.getArgs()[0];
		
		int n = service.pointPlusActivityLog(paraMap);
		System.out.println("aop 확인용 : " + n);
		
		if(n == 2) {
			System.out.println("aop 성공");
		}
	
	} //end of pointPlusActivityLog
	
	
	
	
	
	
		
		
		
		
		
		
	@Pointcut("execution(public * com.gukmo..*Controller.requiredAdminLogin_*(..) )")
	public void requiredAdminLogin() {}
	
	@Before("requiredAdminLogin()")
	public void adminLoginCheck(JoinPoint joinpoint) {
		
		HttpServletRequest request = (HttpServletRequest) joinpoint.getArgs()[0];    
		HttpServletResponse response = (HttpServletResponse) joinpoint.getArgs()[1]; 
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("user");
		
		if(session.getAttribute("user") == null || !"관리자".equals(loginuser.getAuthority())) {
			String message = "관리자 이외에는 접근 불가능합니다.";
			String loc = request.getContextPath()+"/login.do";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			// 로그인 성공후 로그인 하기전 페이지로 돌아가는 작업
			String url = MyUtil.getCurrentURL(request);
			session.setAttribute("goBackURL", url);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/msg.jsp"); 
			
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}//end of adminLoginCheck
 
	 


}
