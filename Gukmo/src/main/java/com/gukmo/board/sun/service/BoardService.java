package com.gukmo.board.sun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.model.ReportVO;
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
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public Boolean saveTag(List<String> hashTags, String board_num) {
		
		int result = 1;

        for (String hashTag : hashTags) {
        	
            HashtagVO hashtagvo = dao.findHashtag(hashTag);

            // 등록된 태그가 아니라면 태그부터 추가
            if (hashtagvo == null) {
            	dao.saveHashTag(hashTag);
            }
            
            // 태그-보드 매핑 테이블에 데이터 추가
            hashtagvo = dao.findHashtag(hashTag);
            int hashtag_num = hashtagvo.getHashtag_num();
           
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
	public BoardVO getBoardDetail(Map<String, Object> paraMap) {
		BoardVO boardvo = dao.getBoardDetail(paraMap); // 글1개 조회하기
		return boardvo;
	}


	// 게시글 수정
	@Override
	public int modify(BoardVO boardvo) {
		int n = dao.modify(boardvo);
		return n;
	}




	// 해시태그 수정시 기존 해시태그 삭제
	@Override
	public int hashTagDel(String board_num) {
		int n = dao.hashTagDel(board_num);
		return n;
	}

	
	// 게시글 삭제
	@Override
	public int boardDel(Map<String, Object> paraMap) {
		int n1=0,n2 = 0,n3 = 0, n4=0;
//		
//		String board_num = (String) paraMap.get("board_num");
//		BoardVO boardvo = (BoardVO)paraMap.get("boardvo");
//		
//		if(boardvo.getHashtags() != null) {
//			n1 = dao.hashTagDel(board_num); 
//			System.out.println("n1성공"+n1);
//		}
//		
//		n2 = dao.activityDel(paraMap);
//		System.out.println("n2성공"+n2);
//		
//		n3 = dao.commentDel(papaMap);
//	
		n3 = dao.boardDel(paraMap);
		System.out.println("n3성공"+n3);
		
//		n3 = dao.pointMinus(paraMap);
//		System.out.println("n4성공"+n4);

		
		return n3;
	}


	// 신고하기
	@Override
	public int reportInsert(ReportVO reportvo) {
		int n = dao.reportInsert(reportvo);
		return n;
	}


	// 댓글 신고하기
	@Override
	public int comment_reportInsert(Map<String, String> paraMap) {
		int n = dao.comment_reportInsert(paraMap);
		return n;
	}

	
}
