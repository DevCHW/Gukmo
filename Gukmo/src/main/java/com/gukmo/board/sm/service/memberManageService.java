package com.gukmo.board.sm.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.sm.repository.InterMemberDAO;

@Service
public class memberManageService implements InterMemberManageService {

	// === #34. 의존객체 주입하기(DI: Dependency Injection) ===
	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterMemberDAO dao;
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.board.model.BoardDAO 의 bean 을  dao 에 주입시켜준다. 
    // 그러므로 dao 는 null 이 아니다.
	
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int n = dao.getTotalCount(paraMap);
		return n;
	}

}
