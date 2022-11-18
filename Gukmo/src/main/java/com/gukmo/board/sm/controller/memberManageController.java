package com.gukmo.board.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.sm.service.InterMemberManageService;

@Controller
public class memberManageController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterMemberManageService service;
	
	
	// 회원관리 목록 페이지 요청
	@RequestMapping(value="/admin/memberManage_List.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView memberManageList(ModelAndView mav, HttpServletRequest request) {
	    List<MemberVO> memberList = null;
	    
	    String searchType = request.getParameter("");
		String searchWord = request.getParameter("");
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		 if(searchType == null || (!"subject".equals(searchType) && !"name".equals(searchType)) ) {
			searchType = "";
		 }
		
		 if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
		 	searchWord = "";
		 }
		 
		 Map<String, String> paraMap = new HashMap<>();
		 paraMap.put("searchType", searchType);
		 paraMap.put("searchWord", searchWord);

		 
		 // 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
		 // 총 게시물 건수(totalCount)는 검색조건이 있을 때와 없을때로 나뉘어진다.
		 int totalCount = 0;           // 총 게시물 건수
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int currentShowPageNo = 0;    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		 int totalPage = 0;            // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
			
		 int startRno = 0; // 시작 행번호
		 int endRno = 0;   // 끝 행번호
		 
		 // 총 게시물 건수(totalCount)
		 totalCount = service.getTotalCount(paraMap);
		 System.out.println(totalCount);
		 
		 
		 totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );

		 if(str_currentShowPageNo == null) {
			 // 게시판에 보여지는 초기화면 
			 currentShowPageNo = 1;
		 }
		 else {
		 
			 try {
				 currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				 if( currentShowPageNo < 1 || currentShowPageNo > totalPage ) {
					 currentShowPageNo = 1;
				 }
			 } catch(NumberFormatException e) {
				 currentShowPageNo = 1;
			 }
		 
		 }		 
		 
		 startRno = ((currentShowPageNo - 1) * sizePerPage) + 1;
		 endRno = startRno + sizePerPage - 1;
		 
		 paraMap.put("startRno", String.valueOf(startRno));
		 paraMap.put("endRno", String.valueOf(endRno));

		 memberList = service.memberList(paraMap);
		 
		
		
		
		mav.setViewName("admin/memberManage_List.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberManage_List.jsp 파일을 생성한다.
		return mav;
	}
	
	
	
	
	
	
	@RequestMapping(value="/admin/memberDetail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView memberDetail(ModelAndView mav) {
	      mav.setViewName("admin/memberDetail.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	}
	
	@RequestMapping(value="/admin/penaltyRegister.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView penaltyRegister(ModelAndView mav) {
	      mav.setViewName("admin/penaltyRegister.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	}	
	
}
