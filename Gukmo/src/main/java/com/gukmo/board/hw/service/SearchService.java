package com.gukmo.board.hw.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.repository.InterSearchDAO;

@Service
public class SearchService implements InterSearchService{

	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterSearchDAO dao;

	
	/**
	 * 검색어 저장시키기
	 * @param 검색어,아이피
	 */
	@Override
	public boolean saveKeyWord(Map<String,String> paraMap) {
		
		boolean saveSuccess = false;
		int n = 0;
		//검색어로 검색한 결과물의 갯수 알아오기
		int result = dao.getResultByKeyword(paraMap);
		
		if(result > 0) {	//검색어로 검색한 결과가 1건이라도 있다면,
			if(paraMap.get("userid") == null || "".equals(paraMap.get("userid"))) {	//검색한 클라이언트가 로그인 중이 아니였다면
				String userid = dao.getUseridWithIp(paraMap.get("search_ip"));	//검색한아이피로 가장 최근 로그인 한 유저아이디 값 알아내기
				paraMap.put("userid",userid);
			}
			n = dao.saveKeyWord(paraMap);	//검색어 저장시키기
		}
		
		saveSuccess = n > 0?true:false;
		
		return saveSuccess;
		
	}
	
	
	
}
