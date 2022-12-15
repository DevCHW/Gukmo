package com.gukmo.board.hw.admin.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.VisitCountVO;

@Repository
public class IndexDAO implements InterIndexDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;

	/**
	 * 인기 게시물 3개 가져오기(조회높은것)
	 */
	@Override
	public List<BoardVO> getPopularBoard() {
		List<BoardVO> popularBoardList = gukmo_sql.selectList("chw.getPopularBoard");
		return popularBoardList;
	}
	
	/**
	 * 방문자 정보 insert하기
	 */
    public int insertVisitor(Map<String,String> paraMap) throws Exception{
    	System.out.println("호출은되는건가");
        return gukmo_sql.insert("chw.insertVisitor",paraMap);
    }

}
