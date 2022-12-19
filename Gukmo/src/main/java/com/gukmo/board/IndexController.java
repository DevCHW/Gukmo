package com.gukmo.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hasol.service.InterIndexService;
import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;
import com.gukmo.board.model.SearchVO;

@Controller
public class IndexController {

	@Autowired
	private InterIndexService service;

	@RequestMapping(value = "/index.do")
	public ModelAndView hello_gukmo(ModelAndView mav, HttpServletRequest request) {

		// 학원 목록 불러오는 메소드
		int totalCnt = 0;

		try {
			totalCnt = service.getCurriTotalCnt();
			// System.out.println("totalCnt:" + totalCnt);
			
			List<BoardVO> curriList1 = service.getCurriList1();
			mav.addObject("curriList1", curriList1);
			// System.out.println("curriList1:"+curriList1);
			
			if(totalCnt > 8) {
				List<BoardVO> curriList2 = service.getCurriList2();
				mav.addObject("curriList2", curriList2);
				// System.out.println("curriList2:"+curriList2);
				
				if(totalCnt > 16) {
					List<BoardVO> curriList3 = service.getCurriList3();
					mav.addObject("curriList3", curriList3);
					// System.out.println("curriList3:" +curriList3);
				}
			}

		}catch (NullPointerException e) {
			String html = "<div> 모집 중인 학원이 없습니다. </div>";
			mav.addObject("html" , html);
		}
	
		
		// 주간 해시태그 순위를 불러오는 메소드
		List<HashtagVO> topHashList = service.getTopHashList();
		
		// 주간 검색어 순위를 불러오는 메소드
		List<SearchVO> topSearchList = service.getTopSearchList();

		// 자유 게시판 목록 불러오는 메소드
		List<BoardVO> freeBoardList = service.getFreeBoardList();

		// 스터디 게시판 목록 불러오는 메소드
		List<BoardVO> studyBoardList = service.getStudyBoardList();

		// QnA 게시판 목록 불러오는 메소드
		List<BoardVO> qnaBoardList = service.getQnaBoardList();

		// 후기/정보공유 게시판 목록 불러오는 메소드
		List<BoardVO> reviewBoardList = service.getReviewBoardList();
		
		// 광고 목록 불러오는 메소드
		List<AdVO> advertisementList = service.getAdvertisementList();
		        
		mav.addObject("topHashList", topHashList);
		mav.addObject("topSearchList", topSearchList);
		mav.addObject("freeBoardList", freeBoardList);
		mav.addObject("studyBoardList", studyBoardList);
		mav.addObject("qnaBoardList", qnaBoardList);
		mav.addObject("reviewBoardList", reviewBoardList);
		mav.addObject("advertisementList", advertisementList);

		mav.setViewName("index.tiles1");

		return mav;
	}
	
	
	
	
	/**
	 * 회사소개 페이지 URL 매핑
	 */
	@RequestMapping(value="/about.do", method= {RequestMethod.GET})
	public String viewAbout(HttpServletRequest request) {
		return "about";
	}
	
	
	
	
	
	
	
	
	
	
	
}