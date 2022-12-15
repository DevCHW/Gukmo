package com.gukmo.visitor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gukmo.board.hw.admin.repository.IndexDAO;

@WebListener
public class VisitCounter implements HttpSessionListener{
	@Autowired
	SqlSessionTemplate template;
	
	private IndexDAO dao;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("제발실행되줘");
		dao = getSessionService(arg0).getMapper(IndexDAO.class);
        //등록되어있는 빈을 사용할수 있도록 설정해준다
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("visit_ip",req.getRemoteAddr());			//ip
		paraMap.put("visit_agent",req.getHeader("User-Agent"));	//접속자가 어느사이트를 타고 들어왔는지
		paraMap.put("visit_refer",req.getHeader("referer"));	//브라우저정보
		
		
		try {	//방문자 insert
			dao.insertVisitor(paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	}
	
	private SqlSessionTemplate getSessionService(HttpSessionEvent se) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		return (SqlSessionTemplate) context.getBean("sqlSession");
	}

	

}

