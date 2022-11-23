package com.gukmo.board.hgb.repository;

import java.util.Map;

import com.gukmo.board.model.BoardVO;

public interface InterBoardDAO {

	// === 글조회수 증가와 함께 글1개를 조회를 해주는 것 === //
	// (먼저, 로그인을 한 상태에서 다른 사람의 글을 조회할 경우에는 글조회수 컬럼의 값을 1증가 해야 한다)
	BoardVO getView(Map<String, String> paraMap);
	
	// 글조회수 1증가 하기 
	void setAddReadCount(String board_num);

	
}
