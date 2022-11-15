package com.gukmo.board.hw.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO implements InterMemberDAO{

	@Resource
	private SqlSessionTemplate gukmo_sql;

	/**
	 * 회원이 존재하는지 여부 검사
	 * @return 회원이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@Override
	public boolean idExistCheck(String userid) {
		int userid_cnt = gukmo_sql.selectOne("chw.idExistCheck",userid);
		boolean idExist = false;
		if(userid_cnt == 1) {	//해당유저아이디 갯수가 1 이라면
			idExist = true;
		}
		else if(userid_cnt == 0){	//해당 유저아이디 갯수가 0 이라면
			idExist = false;
		}
		
		return idExist;
	}
	
	
	
	
	
	
}
