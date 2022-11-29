package com.gukmo.board.sun.repository;

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

	// 게시판 글목록 보기 페이지 요청
	@Override
	public List<BoardVO> boardList(Map<String, String> paraMap) {
		List<BoardVO> boardList = gukmo_sql.selectList("sun.boardList", paraMap);
		return boardList;
	}

	
	// 게시판에 글등록하기
	@Override
	public int communityNew(BoardVO boardvo) {
		int n = gukmo_sql.insert("sun.communityNew", boardvo);
		System.out.println("글등록 성공 디에이오" + n);
		return n;
	}

	
	// 글쓰기, 댓글 작성시 활동 점수 올리기
	@Override
	public int pointPlus(Map<String, Object> paraMap) {
		int n = gukmo_sql.update("sun.pointPlus", paraMap);
		return n;
	}


	// 글쓰기, 댓글 작성시 활동내역에 등록하기
	@Override
	public int activityLog(Map<String, Object> paraMap) {
		int n = gukmo_sql.update("sun.activityLog", paraMap);
		return n;
	}

    // 게시물 총 수 들고오기
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int totalCount = gukmo_sql.selectOne("sun.getTotalCount", paraMap);
		return totalCount;
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
		System.out.println("1해시태그 추가 성공 일까 디에이오");
	}


	// tbl_hashtag에 카운트 올리기
	@Override
	public void upHashTagCount(int hashtag_num) {
		gukmo_sql.update("sun.upHashTagCount", hashtag_num);
		System.out.println("2해시태그 카운트 성공 일까 디에이오");
	}


	// tbl_hashtag_board_map 에 insert하기
	@Override
	public int hashtagBoardMapping(Map<String, Object> paraMap) {
		int n = gukmo_sql.insert("sun.hashtagBoardMapping", paraMap);
		System.out.println("3해시태그 mapping dao" + n);
		return n;
	}

	
	// 특정 게시글 내용 가져오기
	@Override
	public BoardVO getBoardDetail(Map<String, String> paraMap) {
		BoardVO boardvo = gukmo_sql.selectOne("sun.getBoardDetail", paraMap);
		return boardvo;
	}

	// 게시글 수정
	@Override
	public int edit(BoardVO boardvo) {
		int n = gukmo_sql.update("sun.edit", boardvo);
		return n;
	}


	// 게시글 삭제
	@Override
	public int del(Map<String, String> paraMap) {
		int n = gukmo_sql.delete("sun.del", paraMap);
		return n;
	}
	
}
