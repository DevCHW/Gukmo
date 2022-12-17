package com.gukmo.board.hw.admin.repository;


import org.mybatis.spring.SqlSessionTemplate;

import com.gukmo.board.model.VisitCountVO;

public class VisitCountDAO {
	public int insertVisitor(VisitCountVO vo,SqlSessionTemplate gukmo_sql) {
		int result = gukmo_sql.insert("chw.insertVisitor",vo);
		return result;
	}
	
	
	
}
