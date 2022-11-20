package com.gukmo.board.sm.repository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PenaltyDAO implements InterPenaltyDAO {

	@Resource
	private SqlSessionTemplate gukmo_sql;

	
}
