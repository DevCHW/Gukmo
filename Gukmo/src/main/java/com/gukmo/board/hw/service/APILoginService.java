package com.gukmo.board.hw.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.common.Sha256;
import com.gukmo.board.hw.repository.InterAPILoginDAO;
import com.gukmo.board.model.MemberVO;

@Service
public class APILoginService implements InterAPILoginService{

	@Autowired
	private InterAPILoginDAO dao;

	
	
	/**
	 * 카카오연동회원인지 체크하기
	 */
	@Override
	public Map<String, Object> kakaoConnectionCheck(Map<String, Object> paramMap) {
		Map<String, Object> kakaoConnectionCheck = dao.kakaoConnectionCheck(paramMap);
		return kakaoConnectionCheck;
	}
	
	
	/**
	 * 카카오연동유저로 바꾸기(update)
	 */
	@Override
	public void setKakaoConnection(Map<String, Object> paramMap) {
		dao.setKakaoConnection(paramMap);
	}
	
	/**
	 * 네이버연동유저로 바꾸기(update)
	 */
	@Override
	public void setNaverConnection(Map<String, Object> apiJson) {
		dao.setNaverConnection(apiJson);
	}
	
	/**
	 * 구글연동유저로 바꾸기(update)
	 */
	@Override
	public void setGoogleConnection(Map<String, Object> paramMap) {
		dao.setGoogleConnection(paramMap);
	}
	
	/**
	 * 페이스북연동유저로 바꾸기(update)
	 */
	@Override
	public void setFacebookConnection(Map<String, Object> paramMap) {
		dao.setFacebookConnection(paramMap);
	}
	
	
	
	/**
	 * 닉네임 중복검사하기
	 * @return 닉네임이 존재하면 true, 존재하지 않는다면 false
	 */
	@Override
	public boolean nicknameExist(String nickname) {
		int result = dao.nicknameDuplicateCheck(nickname);
		
		if(result != 1) {	//닉네임이 존재하지 않는다면
			return false;
		}
		else {				//닉네임이 존재한다면
			return true;
		}
	}


	/**
	 * 카카오로그인,네이버로그인,구글로그인일 경우 회원가입시키기
	 * @return 회원가입 성공 true 실패 false 반환
	 */
	@Override
	public boolean userRegisterPro(Map<String, Object> paramMap) {
		paramMap.put("passwd", Sha256.encrypt("qwe"+MyUtil.getAuthKey(10)));
		int result = dao.userRegisterPro(paramMap);
		if(result == 1) {	//회원가입시키기 성공시
			return true;
		} else {	//회원가입시키기 실패시
			return false;
		}
		
	}
	

	
	


	/**
	 * 이메일 값으로 유저아이디 알아내기
	 */
	@Override
	public String getUserid(String email) {
		String userid = dao.getUserid(email);
		return userid;
	}



	/**
	 * 회원정보얻기
	 * @param 회원아이디
	 * @return 회원정보
	 */
	@Override
	public MemberVO getUser(String userid) {
		MemberVO user = dao.getUser(userid);
		return user;
	}


	


	


	
	
}
