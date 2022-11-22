package com.gukmo.board.hw.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gukmo.board.hw.service.InterBoardService;
import com.gukmo.board.model.BoardVO;

@Controller
public class BoardController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;
	
	/**
	 * 스터디 게시판리스트 매핑
	 */
	@RequestMapping(value="/community/studies.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewStudies(HttpServletRequest request) {
		Map<String, String> paraMap = new HashMap<>();
		String str_page = request.getParameter("page");
		String searchWord = request.getParameter("searchWord");
		paraMap.put("searchWord", searchWord);
		
		
		// 총 게시물 건수(totalCount)구하기
		int totalCount = service.getTotalStudiesCount(paraMap);
		int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		//총 페이지 수 계산하기 (총페이지수/한페이지당 보여줄 게시물 건수)의 올림처리
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		
		int page = getPage(str_page,totalPage);	//현재페이지번호 구하기(예외처리 포함)
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap); 
		 
		List<BoardVO> studies = service.getStudies(paraMap);
		// 페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
		 
		// 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 조건
		if(!"".equals(searchWord) ) {
			request.setAttribute("paraMap", paraMap);
		}
		String url = "studies.do";
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage, url,searchWord);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",studies);
  
		return "board/community/boardList.tiles1";
    }
	
	
	


	/**
	 * QnA 게시판리스트 매핑
	 */
	@RequestMapping(value="/community/questions.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewQuestions(HttpServletRequest request) {
		Map<String, String> paraMap = new HashMap<>();
		String str_page = request.getParameter("page");
		String searchWord = request.getParameter("searchWord");
		paraMap.put("searchWord", searchWord);
		
		// 총 게시물 건수(totalCount)구하기
		int totalCount = service.getTotalQuestionsCount(paraMap);
		int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		//총 페이지 수 계산하기 (총페이지수/한페이지당 보여줄 게시물 건수)의 올림처리
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		int page = getPage(str_page,totalPage);	//현재페이지번호 구하기(예외처리 포함)
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap);
		 
		List<BoardVO> questions = service.getQuestions(paraMap);
		// 페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
		// 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 조건
		if(!"".equals(searchWord) ) {
			request.setAttribute("paraMap", paraMap);
		}
		String url = "questions.do";
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage,url,searchWord);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",questions);
  
		return "board/community/boardList.tiles1";
   }
	
	
	
	
	
	
	/**
	 * 취미모임 게시판리스트 매핑
	 */
	@RequestMapping(value="/community/hobbies.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewHobbies(HttpServletRequest request) {
		Map<String, String> paraMap = new HashMap<>();
		String str_page = request.getParameter("page");
		String searchWord = request.getParameter("searchWord");
		paraMap.put("searchWord", searchWord);
		
		// 총 게시물 건수(totalCount)구하기
		int totalCount = service.getTotalHobbiesCount(paraMap);
		int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		//총 페이지 수 계산하기 (총페이지수/한페이지당 보여줄 게시물 건수)의 올림처리
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		int page = getPage(str_page,totalPage);	//현재페이지번호 구하기(예외처리 포함)
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap);
		 
		List<BoardVO> hobbies = service.getHobbies(paraMap);
		// 페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
		// 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 조건
		if(!"".equals(searchWord) ) {
			request.setAttribute("paraMap", paraMap);
		}
		String url = "hobbies.do";
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage,url,searchWord);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",hobbies);
  
		return "board/community/boardList.tiles1";
   }
	
	
	
	/**
	 * 수강/취업후기 게시판리스트 매핑
	 */
	@RequestMapping(value="/community/reviews.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewReviews(HttpServletRequest request) {
		Map<String, String> paraMap = new HashMap<>();
		String str_page = request.getParameter("page");
		String searchWord = request.getParameter("searchWord");
		paraMap.put("searchWord", searchWord);
		
		// 총 게시물 건수(totalCount)구하기
		int totalCount = service.getTotalReviewsCount(paraMap);
		int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		//총 페이지 수 계산하기 (총페이지수/한페이지당 보여줄 게시물 건수)의 올림처리
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		int page = getPage(str_page,totalPage);	//현재페이지번호 구하기(예외처리 포함)
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap);
		 
		List<BoardVO> reviews = service.getReviews(paraMap);
		// 페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
		// 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 조건
		if(!"".equals(searchWord) ) {
			request.setAttribute("paraMap", paraMap);
		}
		String url = "reviews.do";
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage,url,searchWord);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",reviews);
  
		return "board/community/boardList.tiles1";
   }
	
	
	
	
	
	
	
	
	
	
	
	
   // ============================================================================== //
   // ==================================== 페이징처리 유틸 =============================== //
   // ============================================================================== //
	
   /**
    * 페이지바 만들기 메소드
    * @param page(현재 페이지번호)
    * @param totalPage(총페이지 수)
    * @param searchWord(검색어)
    * @return pageBar
    */
   private String getPageBar(int page, int totalPage,String url, String searchWord) {
		// 페이지바 만들기 
		int blockSize = 5;
		// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수이다.
		
		int loop = 1;
		
		int pageNo = ((page - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<ul class='my pagination pagination-md justify-content-center mt-5'>";
		
		// === [<<][<] 만들기 === //
		if(pageNo != 1) {
			//[<<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page=1'>" + 
					   "    <i class='fa-solid fa-angles-left'></i>" + 
					   "  </a>" + 
					   "</li>";
			//[<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+(pageNo-1)+"'>" + 
					   "    <i class='fa-solid fa-angle-left'></i>" + 
					   "  </a>" + 
					   "</li>"; 
		}
		
		while( !(loop > blockSize || pageNo > totalPage) ) {
			
			if(pageNo == page) {	//페이지번호가 현재페이지번호와 같다면 .active
				pageBar += "<li class='page-item active' aria-current='page'>" + 
						   "  <a class='page-link' href='#'>"+pageNo+"</a>" + 
						   "</li>";  
			}
			
			else {	//페이지번호가 현재페이지번호랑 다르다면 .active 뺌
				pageBar += "<li class='page-item'>" + 
						   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+pageNo+"'>"+pageNo+"</a>" + 
						   "</li>";        
			}
			
			loop++;
			pageNo++;
		}// end of while--------------------------
		
		// === [>][>>] 만들기 === //
		if( pageNo <= totalPage) {
			//[>]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+pageNo+"'>"+
					   "    <i class='fa-solid fa-angle-right'></i>"+
					   "  </a>" + 
					   "</li>";
			
			//[>>] 
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+totalPage+"'>"+
					   "    <i class='fas fa-solid fa-angles-right'></i>"+
					   "  </a>" + 
					   "</li>";
		}
		
		pageBar += "</ul>";
		
		return pageBar;
    }//end of getPageBar(){}---
   
   
   
   
   /**
    * 페이지 번호 예외처리하기
    * @param str_page(쿼리스트링으로 날아온 페이지)
    * @param totalPage
    * @return page(현재 페이지번호)
    */
   private int getPage(String str_page,int totalPage) {
	   int page = 0;
	   if(str_page == null) {	//쿼리스트링에 페이지가 없다면
			 // 게시판에 보여지는 초기화면 
			 page = 1;
	   } else {
			 try {
				 page = Integer.parseInt(str_page);
				 if( page < 1) {	//페이지가 1페이지보다 작은경우
					 page = 1;
				 }
				 else if(page > totalPage) { //페이지가 총페이지보다 큰 경우
					 page = totalPage;
				 }
			 } catch(NumberFormatException e) {	//페이지번호에 글자를 써서 들어올 경우 오류방지
				 page = 1;
			 }//end of try-catch
	   }
	   return page;
   }//end of method---
   
   
   /**
    * 시작행번호,끝행번호 구하기
    * @param 페이지번호,한페이지당보여줄 갯수,쓰던 맵
    * @return 맵(시작행번호,끝행번호 담아서 줌)
    */
   private Map<String, String> getRno(int page, int sizePerPage, Map<String, String> paraMap) {
		int startRno = ((page - 1) * sizePerPage) + 1; // 시작 행번호(쿼리문 rownum where절에 쓰임)
		int endRno = startRno + sizePerPage - 1; // 끝 행번호(쿼리문 rownum where절에 쓰임)
		
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		return paraMap;
	}
   
   
   
   
   // ============================================================================== //
   // ==================================== 페이징처리 유틸 =============================== //
   // ============================================================================== //
	
	

	
	
	

}
