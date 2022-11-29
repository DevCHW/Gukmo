package com.gukmo.board.common;

import java.util.Date;
import java.util.Random;

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
	
	
	//날짜를 몇 초전, 몇분 전, 몇시간 전, 몇일 전, 몇달 전, 몇년 전으로 나타내주기
	private static class TIME_MAXIMUM {
		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 30;
		public static final int MONTH = 12;
	}
	public static String calculateTime(Date date) {
		long curTime = System.currentTimeMillis();
		long regTime = date.getTime();
		long diffTime = (curTime - regTime) / 1000;
		String msg = null;
		if (diffTime < TIME_MAXIMUM.SEC) {
			// sec
			msg = diffTime + "초 전";
		} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
			// min
			msg = diffTime + "분 전";
		} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
			// hour
			msg = (diffTime) + "시간 전";
		} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
			// day
			msg = (diffTime) + "일 전";
		} else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
			// day
			msg = (diffTime) + "달 전";
		} else {
			msg = (diffTime) + "년 전";
		}
		return msg;
	}
	
	
	
	
	/**
	 * 난수 발생 (회원가입할때 이메일로 보낼 인증코드),(닉네임생성시 난수발생시켜서 더하기)
	 * @param 난수를 발생시킬 사이즈
	 * @return 사이즈만큼의 자릿수를가진 난수
	 */
	public static String getAuthKey(int size) {
        Random random = new Random();
        
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }
	
	
	
	
	
}
