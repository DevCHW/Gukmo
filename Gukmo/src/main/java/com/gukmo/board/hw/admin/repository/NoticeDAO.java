package com.gukmo.board.hw.admin.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;

@Repository
public class NoticeDAO implements InterNoticeDAO{
	@Resource
	private SqlSessionTemplate gukmo_sql;

	/**
	 * 글번호 채번하기
	 */
	@Override
	public String getBoarSeq() {
		return gukmo_sql.selectOne("chw.getBoarSeq");
	}

	/**
	 * tbl_board에 insert
	 */
	@Override
	public int writeNoticeByBoard(Map<String, Object> paraMap) {
		int result = gukmo_sql.insert("chw.writeNoticeByBoard", paraMap);
		return result;
	}

	/**
	 * tbl_notice에 insert 
	 */
	@Override
	public int insertNotice(Map<String, Object> paraMap) {
		int result = gukmo_sql.insert("chw.insertNotice", paraMap);
		return result;
	}

	
	
	// tbl_hashtag에 존재하는지 찾기
	@Override
	public HashtagVO findHashtag(String hashTag) {
		HashtagVO findResult = gukmo_sql.selectOne("sun.findHashtag", hashTag);
		return findResult;
	}


	// tbl_hashtag에 해시태그 추가
	@Override
	public void saveHashTag(String hashTag) {
		gukmo_sql.insert("sun.saveHashTag", hashTag);
	}


	// tbl_hashtag에 카운트 올리기
	@Override
	public int upHashTagCount(int hashtag_num) {
		return gukmo_sql.update("sun.upHashTagCount", hashtag_num);
	}


	// tbl_hashtag_board_map 에 insert하기
	@Override
	public int hashtagBoardMapping(Map<String, Object> paraMap) {
		int n = gukmo_sql.insert("sun.hashtagBoardMapping", paraMap);
		return n;
	}

	/**
	 * 포인트올려주고 활동내역기록해주기
	 * @param paraMap
	 * @return
	 */
	@Override
	public int pointPlusActivityRecord(Map<String, Object> paraMap) {
		int result1 = gukmo_sql.update("chw.pointPlus", paraMap);
		int result2 = gukmo_sql.insert("chw.ActivityRecord", paraMap);
		return result1*result2;
	}

	/**
	 * 기존 해시태그 매핑테이블에서 삭제처리
	 */
	@Override
	public int hashTagDel(String board_num) {
		return gukmo_sql.delete("sun.hashTagDel", board_num);
	}

	/**
	 * 한개의 공지사항 정보 가져오기
	 */
	@Override
	public BoardVO getNoticeOne(String board_num) {
		return gukmo_sql.selectOne("chw.getNoticeOne", board_num);
	}

	/**
	 * 공지사항 수정하기
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int updateNotice(Map<String, Object> paraMap) {
		int result1 = gukmo_sql.update("chw.updateNotice", paraMap);
		int result2 = gukmo_sql.update("chw.updateMustRead", paraMap);
		return result1*result2;
	}

	

}
