package com.gukmo.board.hw.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gukmo.board.hw.admin.repository.VisitCountDAO;
import com.gukmo.board.model.VisitCountVO;

public class VisitCounter implements HttpSessionListener{
	//현재 접속자 수(비로그인 포함)
	private static int currentConnectCount;

	public static int getCurrentConnectCount() {
		return currentConnectCount;
	}
	
    @Override
    public void sessionCreated(HttpSessionEvent arg0){
    	currentConnectCount++;
        HttpSession session = arg0.getSession();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        //request를 파라미터에 넣지 않고도 사용할수 있도록 설정
        VisitCountDAO visitCountDAO = (VisitCountDAO)wac.getBean("visitCounter");
        //등록되어있는 빈을 사용할수 있도록 설정해준다
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String browser = getUserBrowser(req.getHeader("User-Agent"));
        
        VisitCountVO vo = new VisitCountVO();
        vo.setVisit_ip(req.getRemoteAddr());			//방문자 ip
        vo.setVisit_browser(browser);					//브라우저 정보
        vo.setVisit_refer(req.getHeader("referer"));	//접속 전 사이트 정보
        try {
        	SqlSessionTemplate gukmo_sql = getSessionService(arg0);
			visitCountDAO.insertVisitor(vo,gukmo_sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0){
    	--currentConnectCount;
    	if( currentConnectCount < 0 ) currentConnectCount = 0;
    }
    
    private SqlSessionTemplate getSessionService(HttpSessionEvent se) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());
		return (SqlSessionTemplate) context.getBean("gukmo_sql");
	}
    
    
    private String getUserBrowser(String userAgent) {
    	if(userAgent.indexOf("Trident") > -1) {												// IE
    		return "IE";
    	} else if(userAgent.indexOf("Edg") > -1) {											// Edge
    		return "Edge";
    	} else if(userAgent.indexOf("Whale") > -1) { 										// Naver Whale
    		return "Whale";
    	} else if(userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1) { 		// Opera
    		return "Opera";
    	} else if(userAgent.indexOf("Firefox") > -1) { 										 // Firefox
    		return "Firefox";
    	} else if(userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1 ) {	 // Safari
    		return "Safari";		
    	} else if(userAgent.indexOf("Chrome") > -1) {										 // Chrome	
    		return "Chrome";
    	} else {
    		return "알수없는 브라우저";															//브라우저 정보 X
    	}
	}
    
    
    
}