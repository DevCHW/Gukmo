package com.gukmo.board.hw.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO implements InterLoginDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	/**
	 * 로그인할 유저가 존재하는지 검사
	 * @param 유저가 입력한 아이디, 유저가 입력한 비밀번호가 들어있는 Map
	 * @return 유저가 존재하면 true, 유저가 존재하지않는다면 false를 반환한다.
	 */
	@Override
	public boolean userExistCheck(Map<String,String> paraMap) {
		int user_cnt = gukmo_sql.selectOne("chw.userExistCheck",paraMap);
		boolean userExist = false;
		if(user_cnt == 1) {	//해당유저 갯수가 1 이라면
			userExist = true;
		}
		else if(user_cnt == 0){	//해당 유저 갯수가 0 이라면
			userExist = false;
		}
		return userExist;
	}
}
