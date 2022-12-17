package com.gukmo.board.hw.admin.service;

import java.util.Map;

import com.gukmo.board.model.BoardVO;

public interface InterNoticeService {

	/**
	 * 공지사항 작성하기
	 */
	boolean writeNotice(Map<String, Object> paraMap);
	
	/**
	 * 공지사항 수정하기
	 */
	boolean editNotice(Map<String, Object> paraMap);

	/**
	 * 글번호로 공지사항 한개 가져오기
	 */
	BoardVO getNoticeOne(String board_num);

}
