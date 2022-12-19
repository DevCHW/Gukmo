package com.gukmo.board.hasol.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hasol.repository.InterIndexDAO;
import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.model.SearchVO;

@Service
public class IndexService implements InterIndexService {

	@Autowired     // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterIndexDAO dao;

	
	
	// 학원 목록을 불러오는 메소드
	@Override
	public List<BoardVO> getCurriList1() {
		List<BoardVO> curriList1 = dao.getCurriList1();
		return curriList1;
	}

	@Override
	public List<BoardVO> getCurriList2() {
		List<BoardVO> curriList2 = dao.getCurriList2();
		return curriList2;
	}

	@Override
	public List<BoardVO> getCurriList3() {
		List<BoardVO> curriList3 = dao.getCurriList3();
		return curriList3;
	}

	
	// 학원 목록 촉 개수를 구하기 위한 메소드
	@Override public int getCurriTotalCnt() {
		int n = dao.getCurriTotalCnt();
		return n;
	}
	 
	// 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getFreeBoardList() {
		List<BoardVO> freeBoardList = dao.getFreeBoardList();
		return freeBoardList;
	}

	// 스터디 목록 불러오는 메소드
	@Override
	public List<BoardVO> getStudyBoardList() {
		List<BoardVO> studyBoardList = dao.getStudyBoardList();
		return studyBoardList;
	}

	//qna 목록 불러오는 메소드
	@Override
	public List<BoardVO> getQnaBoardList() {
		List<BoardVO> qnaBoardList = dao.getQnaBoardList();
		return qnaBoardList;
	}

	// 후기 게시판 목록 불러오는 메소드
	@Override
	public List<BoardVO> getReviewBoardList() {
		List<BoardVO> reviewBoardList = dao.getReviewBoardList();
		return reviewBoardList;
	}

	
	// 주간 해시태그 순위를 불러오는 메소드
	@Override
	public List<HashtagVO> getTopHashList() {
		List<HashtagVO> topHashList = dao.getTopHashList();
		
		if(topHashList.size() < 1) {
			topHashList = dao.getTopHashList_nodata();
		}
		
		return topHashList;
	}

	// 주간 검색어 순위를 불러오는 메소드
	@Override
	public List<SearchVO> getTopSearchList() {
		List<SearchVO> topSearhList = dao.getTopSearchList();
		
		if(topSearhList.size() < 1) {			
			topSearhList = dao.getTopSearchList_nodata();
		}
		
		return topSearhList;
	}

	// 광고 리스트를 구해오는 메소드
	@Override
	public List<AdVO> getAdvertisementList() {
		List<AdVO> advertisementList = dao.getAdvertisementList();
		return advertisementList;
	}





}
