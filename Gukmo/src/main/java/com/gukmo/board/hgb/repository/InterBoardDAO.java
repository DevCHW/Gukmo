package com.gukmo.board.hgb.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;

public interface InterBoardDAO {

	// 글1개 조회하기
	BoardVO getView(Map<String, String> paraMap);

	// 글조회수 1증가 하기 
	void setAddReadCount(String board_num);

}
