package com.gukmo.board.hw.admin.repository;

import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;

public interface InterNoticeDAO {

	/**
	 * 글번호 채번하기
	 */
	String getBoarSeq();

	/**
	 * tbl_board에 insert
	 */
	int writeNoticeByBoard(Map<String, Object> paraMap);
	
	/**
	 * tbl_notice에 insert 
	 */
	int insertNotice(Map<String, Object> paraMap);

	//선우누나 해시태그
	void saveHashTag(String hashTag);

	//선우누나 해시태그
	HashtagVO findHashtag(String hashTag);

	//선우누나 해시태그
	int upHashTagCount(int hashtag_num);

	//선우누나 해시태그
	int hashtagBoardMapping(Map<String, Object> paraMap);

	/**
	 * 포인트 올려주고 활동내역에 기록해주기
	 */
	int pointPlusActivityRecord(Map<String, Object> paraMap);

	/**
	 * 기존 해시태그매핑테이블 삭제해주기(선우누나꺼)
	 */
	int hashTagDel(String board_num);

	/**
	 * 한개의 공지사항 정보 가져오기
	 */
	BoardVO getNoticeOne(String board_num);

	/**
	 * 공지사항 수정하기
	 */
	int updateNotice(Map<String, Object> paraMap);


}
