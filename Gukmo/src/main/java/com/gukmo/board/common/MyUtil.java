package com.gukmo.board.common;

import javax.servlet.http.HttpServletRequest;

public class MyUtil {

	// 다음의 데이터까지 포함한 현재 URL 주소를 알려주는 메소드 생성
	public static String getCurrentURL(HttpServletRequest request) {
		
		String currentURL = request.getRequestURL().toString();
		
		String queryString = request.getQueryString();
		
		if(queryString != null) { // GET 방식일 경우
			currentURL += "?" + queryString;
		}
		
		String ctxPath = request.getContextPath();
		
		int beginIndex = currentURL.indexOf(ctxPath) + ctxPath.length();
		
		currentURL = currentURL.substring(beginIndex);
		
		return currentURL;
	}
	
	
	// 크로스 사이트 스크립트 공격에 대응하는 안전한 코드(시큐어 코드) 작성
	public static String secureCode(String str) {
		
	/*	
		=== 스마트에디터를 사용 안 할 경우 ===
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
	*/	
		// 스마트에디터를 사용할 경우
		str = str.replaceAll("<script", "&lt;script");
		return str;
	}
	
	
	
	
	
}
