package com.gukmo.board.hw.repository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements InterMemberDAO{

	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
	
	
	
	
}
