package com.gukmo.board.sun.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.sun.repository.InterBoardDAO;


@Service
public class BoardService implements InterBoardService{
	
	@Autowired
	private InterBoardDAO dao;

	
	// 게시판 글목록 보기 페이지 요청
	@Override
	public List<BoardVO> boardList(Map<String, String> paraMap) {
		List<BoardVO> boardList = dao.boardList(paraMap);
		return boardList;
	}


	// 글쓰기, 댓글 작성시 활동 점수 update, 활동기록 insert (after)
	@Override
	public int pointPlusActivityLog(Map<String, Object> paraMap) {
		
		int n = dao.pointPlus(paraMap);
		
		if(n == 1) { // 포인트 업데이트 성공했을 시 활동내역 등록하기
			n += dao.activityLog(paraMap);
		}
		
		return n;
	}

	
	// 총 게시물 건수 
	@Override
	public int getTotalCount(Map<String, String> paraMap) {
		int totalCount = dao.getTotalCount(paraMap);
		return totalCount;
	}
	
	
	// 게시판에 글 등록하기
	@Override
	public int communityNew(BoardVO boardvo) {
		int n = dao.communityNew(boardvo);
		return n;
	}
	

	// 해시태그 tbl_hashtag 에 insert(테이블에 존재하지 않으면 insert,존재하는 해시태그면 x)
	@Override
	public Boolean saveTag(List<String> hashTags, String board_num) {
		
		int result = 1;

        for (String hashTag : hashTags) {
        	
            HashtagVO hashtagvo = dao.findHashtag(hashTag);

            // 등록된 태그가 아니라면 태그부터 추가
            if (hashtagvo == null) {
            	dao.saveHashTag(hashTag);
            	System.out.println("null 해시태그브이오");
            }
            
            // 태그-보드 매핑 테이블에 데이터 추가
            hashtagvo = dao.findHashtag(hashTag);
            int hashtag_num = hashtagvo.getHashtag_num();
           System.out.println("hashtag_num" + hashtag_num);
           
            dao.upHashTagCount(hashtag_num); // 언급 카운트 올리기
            
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("hashtag_num", hashtag_num);
            paraMap.put("board_num", board_num);
            
            result = dao.hashtagBoardMapping(paraMap);
        }

        return result == 1;
	}


	// 특정 게시글 내용 가져오기
	@Override
	public BoardVO getBoardDetail(Map<String, String> paraMap) {
		BoardVO boardvo = dao.getBoardDetail(paraMap); // 글1개 조회하기
		return boardvo;
	}


	// 게시글 수정
	@Override
	public int edit(BoardVO boardvo) {
		int n = dao.edit(boardvo);
		return n;
	}


	// 게시글 삭제
	@Override
	public int del(Map<String, String> paraMap) {
		int n = dao.del(paraMap);
		return n;
	}


	
}
