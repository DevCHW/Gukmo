package com.gukmo.board.hgb.service;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;



public interface InterBoardService {

	// 글1개 조회하기
	BoardVO getView(Map<String, String> paraMap);

	// 글조회수 증가는 없고 단순히 글1개만 조회 해주는 것
	BoardVO getViewWithNoAddCount(Map<String, String> paraMap);
	


}
