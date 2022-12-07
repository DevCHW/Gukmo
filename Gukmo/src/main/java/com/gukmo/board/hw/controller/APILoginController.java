package com.gukmo.board.hw.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.gukmo.board.common.MyUtil;
import com.gukmo.board.common.NaverLoginBO;
import com.gukmo.board.hw.service.InterAPILoginService;
import com.gukmo.board.model.MemberVO;


@Controller
public class APILoginController {
	
	@Autowired   
	private InterAPILoginService service;
	@Autowired
	private NaverLoginBO naverloginbo;
	
	/**
	 * 카카오로그인 유저정보 받기
	 */
	@ResponseBody
	@RequestMapping(value="/kakaoLoginPro.do", method=RequestMethod.POST)
	public Map<String, Object> kakaoLoginPro(@RequestParam Map<String,Object> paramMap,HttpSession session) throws SQLException, Exception {
		System.out.println("확인용 카카오로그인 날아온 유저정보 :" + paramMap);
		
		Map <String, Object> resultMap = new HashMap<String, Object>();
		
		//소셜로그인연동회원인지 체크
		Map<String, Object> kakaoConnectionCheck = service.kakaoConnectionCheck(paramMap);	
		System.out.println("소셜로그인연동회원인지 체크하는 값 정보 : " + kakaoConnectionCheck);
		
		String userid = service.getUserid(String.valueOf(paramMap.get("email")));
		if(kakaoConnectionCheck == null) { //신규회원이라면 회원가입시키기(insert)
			resultMap.put("JavaData", "register");
			
		}else if("0".equals(kakaoConnectionCheck.get("KAKAO")) && kakaoConnectionCheck.get("EMAIL") != null) { //기존회원이지만 카카오로그인을 시도하였다면
			System.out.println("카카오연동회원으로 전환");
			service.setKakaoConnection(paramMap);	//카카오로그인회원으로 업데이트(update)
			
			resultMap.put("userid",userid);	//로그인에 필요한 유저아이디 반환
			resultMap.put("JavaData", "YES");
			
		}else{	//기존 카카오연동회원이라면
			resultMap.put("userid",userid);	//로그인에 필요한 유저아이디 반환
			resultMap.put("JavaData", "YES");
		}
		return resultMap;
	}
	
	
	
	/**
	 * 네이버로그인폼 URL 반환하기
	 */
	@ResponseBody
	@RequestMapping(value="/naverLogin.do", method=RequestMethod.GET)
	public String getNaverLoginURL(HttpSession session) {
		
		//네이버로그인URL 얻기
		String naverAuthUrl = naverloginbo.getAuthorizationUrl(session);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("naverUrl", naverAuthUrl);
		return jsonObj.toString();
	}
	
	
	/**
	 * 네이버로그인회원정보 얻기
	 */
	@RequestMapping(value="/naverLoginPro.do")
	public String NaverLoginPro(HttpServletRequest request,@RequestParam String code, @RequestParam String state, HttpSession session) throws SQLException, Exception {
		OAuth2AccessToken oauthToken;
		oauthToken = naverloginbo.getAccessToken(session, code, state);
				
				
		String apiResult = naverloginbo.getUserProfile(oauthToken);
		
		//날아온 토큰값 확인
		System.out.println("확인용 apiResult =>"+apiResult);
		
		ObjectMapper objectMapper =new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> apiJson = (Map<String, Object>) objectMapper.readValue(apiResult, Map.class).get("response");
		
		System.out.println("확인용 apiJson =>"+apiJson);
		
		Map<String, Object> naverConnectionCheck = service.kakaoConnectionCheck(apiJson);
		
		if(naverConnectionCheck == null) { //신규 회원이라면
			request.setAttribute("username",apiJson.get("name"));
			request.setAttribute("email",apiJson.get("email"));
			request.setAttribute("userid",apiJson.get("id"));
			request.setAttribute("profile_image",apiJson.get("profile_image"));
			
			return "login/naverMemberJoinForm.tiles1";
			
		}else if("0".equals(naverConnectionCheck.get("NAVER")) && naverConnectionCheck.get("EMAIL") != null) { //기존회원이 네이버로그인을 시도하였다면
			service.setNaverConnection(apiJson);	//네이버연동회원으로 업데이트 해주기
			String userid = service.getUserid((String)apiJson.get("email"));
			MemberVO user = service.getUser(userid);
			session.setAttribute("user", user);
			
		}else { //기존 네이버회원연동회원이라면
			String userid = service.getUserid((String)apiJson.get("email"));
			MemberVO user = service.getUser(userid);
			session.setAttribute("user", user);
		}
		return "index.tiles1";
	}
	
	
	/**
	 * 구글로그인 유저정보 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/googleLoginPro.do", method=RequestMethod.POST)
	public Map<String, Object> googleLoginPro(@RequestParam Map<String,Object> paramMap) throws SQLException, Exception {
		System.out.println("구글로그인 시도한 회원정보 paramMap:" + paramMap);
		
		Map <String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> googleConnectionCheck = service.kakaoConnectionCheck(paramMap);	//�̹� īī�� �α��������ִ��� üũ
		
		System.out.println("소셜로그인연동회원인지 체크하는 값 정보  googleConnectionCheck : " + googleConnectionCheck);
		
		String userid = service.getUserid(String.valueOf(paramMap.get("email")));
		if(googleConnectionCheck == null) { //신규 회원이라면
			resultMap.put("JavaData", "register");
			
		}else if("0".equals(googleConnectionCheck.get("GOOGLE")) && googleConnectionCheck.get("EMAIL") != null) { //기존회원이지만 구글로그인을 시도하였다면
			System.out.println("기존회원 구글연동회원으로 업데이트");
			service.setGoogleConnection(paramMap);	//기존회원 구글연동회원으로 업데이트
			
			resultMap.put("userid",userid);	//구글로그인에 필요한 유저아이디 값 반환
			resultMap.put("JavaData", "YES");
			
		}else{	//기존 구글연동회원이라면
			resultMap.put("userid",userid);	//구글로그인에 필요한 유저아이디 값 반환
			resultMap.put("JavaData", "YES");
		}
		return resultMap;
	}
	
	
	
	/**
	 * 페이스북로그인 유저정보 받기
	 */
	@ResponseBody
	@RequestMapping(value="/facebookLoginPro.do", method=RequestMethod.POST)
	public Map<String, Object> facebookLoginPro(@RequestParam Map<String,Object> paramMap,HttpSession session) throws SQLException, Exception {
		System.out.println("확인용 페이스북로그인 날아온 유저정보 :" + paramMap);
		
		Map <String, Object> resultMap = new HashMap<String, Object>();
		
		//소셜로그인연동회원인지 체크
		Map<String, Object> facebookConnectionCheck = service.kakaoConnectionCheck(paramMap);	
		System.out.println("소셜로그인연동회원인지 체크하는 값 정보 : " + facebookConnectionCheck);
		
		String userid = service.getUserid(String.valueOf(paramMap.get("email")));
		if(facebookConnectionCheck == null) { //신규회원이라면 회원가입시키기(insert)
			resultMap.put("JavaData", "register");
			
		}else if("0".equals(facebookConnectionCheck.get("FACEBOOK")) && facebookConnectionCheck.get("EMAIL") != null) { //기존회원이지만 카카오로그인을 시도하였다면
			System.out.println("페이스북연동회원으로 전환");
			service.setFacebookConnection(paramMap);	//카카오로그인회원으로 업데이트(update)
			
			resultMap.put("userid",userid);	//로그인에 필요한 유저아이디 반환
			resultMap.put("JavaData", "YES");
			
		}else{	//기존 카카오연동회원이라면
			resultMap.put("userid",userid);	//로그인에 필요한 유저아이디 반환
			resultMap.put("JavaData", "YES");
		}
		return resultMap;
	}
	
	
	
	
	
	
	/**
	 * 소셜로그인 시도하였을때 신규회원이 시도하였다면 가입시키기.
	 */
	@ResponseBody
	@RequestMapping(value="/userSnsRegisterPro.do", method=RequestMethod.POST)
	public Map<String, Object> userSnsRegisterPro(@RequestParam Map<String,Object> paramMap,HttpSession session) throws SQLException, Exception {
		System.out.println("소셜로그인 회원가입 메소드입니다. 메소드명 : userSnsRegisterPro\n");
		System.out.println("회원가입에 필요한 확인용 유저 정보 :" + paramMap);
		
		String flag = (String) paramMap.get("flag");
		switch (flag) {
		case "kakao":
			paramMap.put("kakao", "1");
			paramMap.put("naver", "0");
			paramMap.put("google", "0");
			paramMap.put("facebook", "0");
			paramMap.put("userid","kakao"+paramMap.get("userid"));
			break;
		case "naver":
			paramMap.put("kakao", "0");
			paramMap.put("naver", "1");
			paramMap.put("google", "0");
			paramMap.put("facebook", "0");
			paramMap.put("userid","naver"+paramMap.get("userid"));
			break;
		case "google":
			paramMap.put("kakao", "0");
			paramMap.put("naver", "0");
			paramMap.put("google", "1");
			paramMap.put("facebook", "0");
			paramMap.put("userid","google"+paramMap.get("userid"));
			break;
		case "facebook":
			paramMap.put("kakao", "0");
			paramMap.put("naver", "0");
			paramMap.put("google", "0");
			paramMap.put("facebook", "1");
			paramMap.put("userid","facebook"+paramMap.get("userid"));
			break;
		}//end of switch-case---
		
		
		String nickname = (String) paramMap.get("nickname");
		
		// 닉네임이 중복이라면 중복하지않는 닉네임 생성하기
		if(service.nicknameExist(nickname)) {	
			while(!service.nicknameExist(nickname)) {	//닉네임이 중복하지 않을때까지
				nickname = flag+MyUtil.getAuthKey(10);	
			}
			paramMap.put("nickname",nickname);
		}//end of if--
		
		Map <String, Object> resultMap = new HashMap<String, Object>();
		
		boolean joinSuccess = false;
		
		//회원가입시키기
		joinSuccess = service.userRegisterPro(paramMap);
		
		if(joinSuccess) {	//회원가입에 성공하였다면
			String userid = (String)paramMap.get("userid");
			//로그인처리에 필요한 유저아이디 값 반환
			resultMap.put("userid",userid);
			//회원가입 성공값 반환
			resultMap.put("JavaData", "YES");
			
		}else {				//회원가입에 실패하였다면
			resultMap.put("JavaData", "NO");
		}
		return resultMap;
	}
	

}
