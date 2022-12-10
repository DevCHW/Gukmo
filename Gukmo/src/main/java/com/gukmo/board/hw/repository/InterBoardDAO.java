package com.gukmo.board.hw.repository;

import java.util.List;
import java.util.Map;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;


public interface InterBoardDAO {


	
	/**
	 * 총 게시물 건수(totalCount) 구하기(공지사항 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	int getTotalNoticesCount(Map<String, String> paraMap);


	/**
	 * 공지사항 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum)
	 * @return BoardVO리스트
	 */
	List<BoardVO> getNotices(Map<String, String> paraMap);

	/**
	 * 총 게시물 건수(totalCount) 구하기(국비학원 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	int getTotalAcademyCount(Map<String, String> paraMap);


	/**
	 * 국비학원 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	List<BoardVO> getAcademyList(Map<String, String> paraMap);


	

	/**
	 * 총 게시물 건수(totalCount) 구하기(교육과정 게시판)
	 * @param paraMap(검색어)
	 * @return (총 게시물 건수) 를 반환한다. totalCount
	 */
	int getTotalCurriculumCount(Map<String, String> paraMap);

	
	
	/**
	 * 교육과정 게시판을 보여주기 위한 BoardVO 리스트 가져오기
	 * @param paraMap(검색어,시작rownum,끝rownum,정렬)
	 * @return BoardVO리스트
	 */
	List<BoardVO> getCurriculumList(Map<String, String> paraMap);

	
	// 글번호로 상세카테고리 알아내기
	String getDetailCategory(int boardNum);



	/**
	 * tbl_board에 학원글insert 해주기	
	 */
	int insertBoardByAcademy(Map<String, Object> paraMap);

	/**
	 * 글번호 알아오기
	 */
	String getBoarSeq();

	/**
	 * tbl_academy에 insert해주기(학원글)
	 */
	int insertAcademy(Map<String, Object> paraMap);
	
	/**
	 * tbl_board에 교육과정글insert 해주기(교육과정글)
	 */
	int insertBoardByCurriculum(Map<String, Object> paraMap);

	/**
	 * tbl_curriculum에 insert해주기(교육과정글)
	 */
	int insertCurriculum(Map<String, Object> paraMap);


	/**
	 * AOP(paraMap) 포인트올려주고 활동내역기록해주기
	 * @param paraMap
	 * @return
	 */
	int pointPlusActivityRecord(Map<String, Object> paraMap);
	
	
	
	//선우누나 해시태그insert
	void saveHashTag(String hashTag);

	//선우누나 해시태그insert
	HashtagVO findHashtag(String hashTag);

	//선우누나 해시태그insert
	void upHashTagCount(int hashtag_num);

	//선우누나 해시태그insert
	int hashtagBoardMapping(Map<String, Object> paraMap);
	
	
	
	/**
	 * 
	 * @param paraMap
	 * @return
	 */
	BoardVO getAcademyDetail(Map<String, String> paraMap);


	


	
}
