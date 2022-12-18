package com.gukmo.board.hw.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;



@Repository
public class BoardDAO implements InterBoardDAO{
	
	@Resource
	private SqlSessionTemplate gukmo_sql;


	/**
	 * 총 게시물 건수(totalCount) 구하기(공지사항 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalNoticesCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalNoticesCount",paraMap);
		return totalCount;
	}


	/**
	 * 공지사항 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getNotices(Map<String, String> paraMap) {
		List<BoardVO> notices = gukmo_sql.selectList("chw.getNotices",paraMap);
		return notices;
	}


	
	/**
	 * 총 게시물 건수(totalCount) 구하기(국비학원 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalAcademyCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalAcademyCount",paraMap);
		return totalCount;
	}


	
	/**
	 * 국비학원 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getAcademyList(Map<String, String> paraMap) {
		List<BoardVO> academyList = gukmo_sql.selectList("chw.getAcademyList",paraMap);
		return academyList;
	}

	
	/**
	 * 국비학원 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public String getDetailCategory(int boardNum) {
		String detail_category = gukmo_sql.selectOne("chw.getDetailCategory",boardNum);
		return detail_category;
	}


	
	
	/**
	 * 하나의 글 불러오기(국비학원게시판)
	 * @param 글번호 board_num, 디테일카테고리
	 * @return BoardVO
	 */
	@Override
	public BoardVO getAcademyDetail(Map<String, String> paraMap) {
		BoardVO academy = gukmo_sql.selectOne("chw.getAcademyDetail",paraMap);
		return academy;
	}


	
	
	/**
	 * 총 게시물 건수(totalCount) 구하기(교육과정 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	@Override
	public int getTotalCurriculumCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("chw.getTotalCurriculumCount",paraMap);
		return totalCount;
	}


	/**
	 * 교육과정 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	@Override
	public List<BoardVO> getCurriculumList(Map<String, String> paraMap) {
		List<BoardVO> curriculumList = gukmo_sql.selectList("chw.getCurriculumList",paraMap);
		return curriculumList;
	}


	
	/**
	 * 글번호 알아오기
	 */
	@Override
	public String getBoarSeq() {
		String board_num = gukmo_sql.selectOne("chw.getBoarSeq");
		return board_num;
	}
	
	
	/**
	 * tbl_board에 insert 해주기(학원글)
	 */
	@Override
	public int insertBoardByAcademy(Map<String, Object> paraMap) {
		int result = gukmo_sql.insert("chw.insertBoardByAcademy",paraMap);
		return result;
	}


	/**
	 * tbl_academy에 insert해주기
	 */
	@Override
	public int insertAcademy(Map<String, Object> paraMap) {
		int result = gukmo_sql.insert("chw.insertAcademy",paraMap);
		return result;
	}
	
	/**
	 * tbl_curriculum에 insert 해주기(교육과정글)
	 */
	@Override
	public int insertBoardByCurriculum(Map<String, Object> paraMap) {
		int result = gukmo_sql.insert("chw.insertBoardByCurriculum",paraMap);
		return result;
	}

	/**
	 * tbl_curriculum에 insert해주기
	 */
	@Override
	public int insertCurriculum(Map<String, Object> paraMap) {
		int result = gukmo_sql.insert("chw.insertCurriculum",paraMap);
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
	 * 필독 공지사항 리스트 가져오기
	 */
	@Override
	public List<BoardVO> getRequiredReadNotice() {
		List<BoardVO> noticeListRequiredRead = gukmo_sql.selectList("chw.getRequiredReadNotice");
		return noticeListRequiredRead;
	}

	/**
	 * 글번호로 글 하나 가져오기
	 */
	@Override
	public BoardVO getBoardVO(Map<String,String> paraMap) {
		BoardVO board = gukmo_sql.selectOne("hgb.getBoardDetail",paraMap);
		return board;
	}

	
	/**
	 * 교육과정 수정하기
	 */
	@Override
	public int updateCurriculum(Map<String, Object> paraMap) {
		int result1 = gukmo_sql.update("chw.updateNotice", paraMap);
		int result2 = gukmo_sql.update("chw.updateCurriculum", paraMap);
		return result1 * result2;
	}


	/**
	 * 기존 해시태그 매핑테이블에서 삭제처리
	 */
	@Override
	public int hashTagDel(String board_num) {
		return gukmo_sql.delete("sun.hashTagDel", board_num);
	}


	@Override
	public int updateBoard(Map<String, Object> paraMap) {
		int result1 = gukmo_sql.update("chw.updateNotice", paraMap);
		int result2 = gukmo_sql.update("chw.updateAcademy", paraMap);
		return result1 * result2;
	}


	



	
	
}
