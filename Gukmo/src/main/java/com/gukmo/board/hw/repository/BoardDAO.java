package com.gukmo.board.hw.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.MemberDTO;

@Repository
public class BoardDAO implements InterBoardDAO{
	
	@Resource
	private SqlSessionTemplate gukmo_sql;
	
	@Override
	public List<MemberDTO> select_member() {
		List<MemberDTO> memberList = gukmo_sql.selectList("chw.select_member");
		
		return memberList;
	}
	
}
