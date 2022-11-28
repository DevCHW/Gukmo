package com.gukmo.board.hw.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.hw.service.InterAPILoginService;
import com.gukmo.board.hw.service.InterBoardService;
import com.gukmo.board.model.MemberVO;

@Controller
public class APILoginController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAPILoginService service;
	
	
	/**
	 * 카카오로그인 유저정보 받기
	 */
	@ResponseBody
	@RequestMapping(value="/kakaoLoginPro.do", method=RequestMethod.POST)
	public Map<String, Object> kakaoLoginPro(@RequestParam Map<String,Object> paramMap,HttpSession session) throws SQLException, Exception {
		System.out.println("확인용 날아온 유저정보 paramMap:" + paramMap);
		
		Map <String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> kakaoConnectionCheck = service.kakaoConnectionCheck(paramMap);	//이미 카카오 로그인한적있는지 체크
		
		System.out.println("확인용 kakaoConnectionCheck : " + kakaoConnectionCheck);
		
		String userid = service.getUserid(String.valueOf(paramMap.get("email")));
		if(kakaoConnectionCheck == null) { //일치하는 이메일 없으면 가입(insert)
			resultMap.put("JavaData", "register");
			
		}else if("0".equals(kakaoConnectionCheck.get("KAKAO")) && kakaoConnectionCheck.get("EMAIL") != null) { //이메일 가입 되어있고 카카오 연동 안되어 있을시
			System.out.println("기존회원 kakaoLogin으로 변경");
			service.setKakaoConnection(paramMap);	//카카오연동유저로 바꾸기(update)
			
			resultMap.put("userid",userid);	//로그인처리
			resultMap.put("JavaData", "YES");
		}else{	//카카오로그인연동 되어있는 유저라면
			resultMap.put("userid",userid);	//로그인처리
			resultMap.put("JavaData", "YES");
		}
		return resultMap;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/userSnsRegisterPro.do", method=RequestMethod.POST)
	public Map<String, Object> userSnsRegisterPro(@RequestParam Map<String,Object> paramMap,HttpSession session) throws SQLException, Exception {
		System.out.println("회원가입 폼까지 왔음 userSnsRegisterPro에요\n");
		System.out.println("확인용 날아온 유저정보 paramMap:" + paramMap);
		switch ((String)paramMap.get("flag")) {
		case "kakao":
			paramMap.put("kakao", "1");
			paramMap.put("naver", "0");
			paramMap.put("google", "0");
			break;
		case "naver":
			paramMap.put("kakao", "0");
			paramMap.put("naver", "1");
			paramMap.put("google", "0");
			break;
		case "google":
			paramMap.put("kakao", "0");
			paramMap.put("naver", "0");
			paramMap.put("google", "1");
			break;
		}//end of switch-case---
		
		
		String nickname = (String) paramMap.get("nickname");
		if(service.nicknameExist(nickname)) {	// 이미 존재하는 닉네임이라면 겹치지 않는 닉네임 생성하기
			while(!service.nicknameExist(nickname)) {	//닉네임이 겹치지 않을 때 까지
				nickname = "회원"+MyUtil.getAuthKey(10);	//회원 + 10자리의 난수 발생
			}
			paramMap.put("nickname",nickname);
		}//end of if--
		
		
		Map <String, Object> resultMap = new HashMap<String, Object>();
		String flag = (String) paramMap.get("flag");
		boolean joinSuccess = false;
		
		if(flag.equals("kakao")) {	//카카오로그인일 경우 회원가입처리
			
			paramMap.put("userid","kakao"+paramMap.get("userid"));
			joinSuccess = service.userKakaoRegisterPro(paramMap);
			
		}else if(flag.equals("google")) {	//구글로그인일 경우 회원가입처리
			
			paramMap.put("userid","google"+paramMap.get("userid"));
			joinSuccess = service.userGoogleRegisterPro(paramMap);
			
		}else if(flag.equals("naver")) {	//네이버로그인일 경우 회원가입처리
			paramMap.put("userid","naver"+paramMap.get("userid"));
			joinSuccess = service.userNaverRegisterPro(paramMap);
		}
		
		
		if(joinSuccess) {	//가입에 성공하였을 경우 로그인처리하기
			MemberVO user = null;
			String userid = (String)paramMap.get("userid");
			
			resultMap.put("userid",userid);
			
			//로그인처리
			session.setAttribute("user", user);
			resultMap.put("JavaData", "YES");
		}else {
			resultMap.put("JavaData", "NO");
		}
		return resultMap;
	}
	

}
