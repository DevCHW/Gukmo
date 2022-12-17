package com.gukmo.board.hw.admin.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gukmo.board.hw.admin.repository.InterNoticeDAO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;

@Service
public class NoticeService implements InterNoticeService{
	@Autowired
	private InterNoticeDAO dao;

	/**
	 * 공지사항 작성하기
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public boolean writeNotice(Map<String, Object> paraMap) {
		String board_num = dao.getBoarSeq();
		paraMap.put("board_num",board_num);
		
		int result1 = dao.writeNoticeByBoard(paraMap);	//tbl_board에 insert 해주기	
		int result2 = dao.insertNotice(paraMap);		//tbl_curriculum에 insert해주기
		int result3 = 1;
		int result5 = 0;
		
		//해시태그 넣기
		List<String> hashTags = (List<String>) paraMap.get("hashTags");
        for (String hashTag : hashTags) {
        	
            HashtagVO hashtagvo = dao.findHashtag(hashTag);

            // 등록된 태그가 아니라면 태그부터 추가
            if (hashtagvo == null) {
            	dao.saveHashTag(hashTag);
            }
            
            // 태그-보드 매핑 테이블에 데이터 추가
            hashtagvo = dao.findHashtag(hashTag);
            int hashtag_num = hashtagvo.getHashtag_num();
           
            result5 = dao.upHashTagCount(hashtag_num); // 언급 카운트 올리기
            
            paraMap.put("hashtag_num", hashtag_num);
            
            result3 = result3 * dao.hashtagBoardMapping(paraMap);
        }
        
        int result4 = dao.pointPlusActivityRecord(paraMap);

		boolean success = result1*result2*result3*result4*result5 == 1?true:false;
		return success;
	}

	/**
	 * 공지사항 수정하기
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public boolean editNotice(Map<String, Object> paraMap) {
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = dao.updateNotice(paraMap);
		String board_num = (String) paraMap.get("board_num");
		String orgin_hashTag = (String) paraMap.get("orgin_hashTag");
		if(orgin_hashTag != null && !orgin_hashTag.trim().isEmpty()) {
			result1 = dao.hashTagDel(board_num); // 기존 해시태그 맵핑테이블에 있는 데이터 삭제
		}
		String str_hashTag = (String) paraMap.get("str_hashTag");
		
		if(str_hashTag != null && !str_hashTag.trim().isEmpty()) {
			// System.out.println("str_해시태그" + str_hashTag);
			List<String> hashTags = Arrays.asList(str_hashTag.split(","));
			// 해시태그 처리 시작
			for (String hashTag : hashTags) {
	        	
	            HashtagVO hashtagvo = dao.findHashtag(hashTag);

	            // 등록된 태그가 아니라면 태그부터 추가
	            if (hashtagvo == null) {
	            	dao.saveHashTag(hashTag);
	            }
	            
	            // 태그-보드 매핑 테이블에 데이터 추가
	            hashtagvo = dao.findHashtag(hashTag);
	            int hashtag_num = hashtagvo.getHashtag_num();
	           
	            result3 = dao.upHashTagCount(hashtag_num); // 언급 카운트 올리기
	            
	            paraMap.put("hashtag_num", hashtag_num);
	            paraMap.put("board_num", board_num);
	            
	            result2 = dao.hashtagBoardMapping(paraMap);
	        };
		}
		return result1*result2*result3*result4>0?true:false;
	}

	/**
	 * 글번호로 공지사항 한개 가져오기
	 */
	@Override
	public BoardVO getNoticeOne(String board_num) {
		return dao.getNoticeOne(board_num);
	}

}
