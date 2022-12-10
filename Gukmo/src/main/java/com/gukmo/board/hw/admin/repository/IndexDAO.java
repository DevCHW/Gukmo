package com.gukmo.board.hw.admin.repository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IndexDAO implements InterIndexDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	

}
