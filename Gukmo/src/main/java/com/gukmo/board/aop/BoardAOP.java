package com.gukmo.board.aop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.gukmo.board.common.MyUtil;



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
		if(session.getAttribute("loginuser") == null) {
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
	
	/*  게시물 등록하면 활동 점수가 올라가는 after
	 	
	@Pointcut("execution(public * com.gukmo..*Controller.pointPlus_*(..) )")
	public void pointPlus() {}
	
	@Autowired  
	private InterBoardService service;
	
	@SuppressWarnings("unchecked")
	@After("pointPlus()")
	public void pointPlus(JoinPoint joinpoint) {
		
		Map<String, String> paraMap = (Map<String, String>) joinpoint.getArgs()[0];   
		
		service.pointPlus(paraMap);
	}
 
	 */
		
	
}
