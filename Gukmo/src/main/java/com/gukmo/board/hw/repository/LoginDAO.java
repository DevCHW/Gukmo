package com.gukmo.board.hw.repository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class LoginDAO implements InterLoginDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	
}
