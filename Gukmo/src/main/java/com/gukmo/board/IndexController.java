package com.gukmo.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hasol.service.InterBoardService;
import com.gukmo.board.hasol.service.InterIndexService;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CurriculumVO;


@Controller
public class IndexController {
	
	@Autowired
	private InterIndexService service;
	
	
    @RequestMapping(value="/index.do")
    public ModelAndView hello_gukmo(ModelAndView mav, HttpServletRequest request) {
    	
    	
    	// 오늘 날짜 값 불러오기
    	Date currentTime = new Date();
    	SimpleDateFormat date = new SimpleDateFormat("yyyy-dd-mm");
		String today = date.format(currentTime);
		
		/*
		 * // 학원 목록 불러오는 메소드 List<Map<String, String>> curriList =
		 * service.getCurriList();
		 * 
		 * // 수강 시작 d-day 계산 메소드
		 * 
		 * 
		 * // 학원 날짜 값 불러오기 String startDate = curriList.
		 * 
		 * int Dday = 0;
		 * 
		 * 
		 * 
		 * // 페이징 int totalCount = 0;
		 */
	    
	   
    	// 자유 게시판 목록 불러오는 메소드
    	List<BoardVO> freeBoardList = service.getFreeBoardList();
    	
    	// 스터디 게시판 목록 불러오는 메소드
    	List<BoardVO> studyBoardList = service.getStudyBoardList();
    	
    	// QnA 게시판 목록 불러오는 메소드
    	List<BoardVO> qnaBoardList = service.getQnaBoardList();
    	
    	// 후기/정보공유 게시판 목록 불러오는 메소드
    	List<BoardVO> reviewBoardList = service.getReviewBoardList();
    
		mav.addObject("freeBoardList", freeBoardList);
		mav.addObject("studyBoardList", studyBoardList);
		mav.addObject("qnaBoardList", qnaBoardList);
		mav.addObject("reviewBoardList", reviewBoardList);
		
	    mav.setViewName("index.tiles1");
	   
	    return mav;
   }
}