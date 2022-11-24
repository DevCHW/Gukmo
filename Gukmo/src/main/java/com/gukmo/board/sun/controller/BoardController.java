package com.gukmo.board.sun.controller;


import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.common.FileManager;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.sun.repository.InterBoardDAO;
import com.gukmo.board.sun.service.InterBoardService;


@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	
	@Autowired
	private InterBoardDAO bdao;
	
	@Autowired 
	private FileManager fileManager;
	
	
	// 게시판 글목록 보기 페이지 요청
	@RequestMapping(value="/community/freeBoards.do", method= {RequestMethod.GET})
	public String viewFreeBoards(HttpServletRequest request) {
		List<BoardVO> boardList = null;
		
		Map<String, String> paraMap = new HashMap<>();
		 String searchWord = request.getParameter("searchWord");
		 String sortType = request.getParameter("sortType");
		 String str_page = request.getParameter("page");
		 
		 if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
		 	searchWord = "";
		 }
		 
		 if(sortType == null ||sortType == "") {
			 sortType = "write_date";
		 }
		 
		 paraMap.put("searchWord", searchWord);
		 paraMap.put("sortType", sortType);
		 paraMap.put("detail_category", "자유게시판");
		 
		 // 총 게시물 건수
		 int totalCount = service.getTotalCount(paraMap);    
		 // 한 페이지당 보여줄 게시물 건수 
		 int sizePerPage = 10;         
		 // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		 //현재페이지번호 구하기(예외처리 포함)
		 int page = getPage(str_page,totalPage);	
	
		 // 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		 paraMap = getRno(page,sizePerPage,paraMap); 
		 
		 boardList = service.boardList(paraMap); // 글목록 가져오기
		 
		 // 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 것
		 if(!"".equals(searchWord) ) {
			 request.setAttribute("paraMap", paraMap);
		 }
		 
		 String url = "freeBoards.do";
		 //페이지바 얻기
		 String pageBar = getPageBar(page,totalPage, url, searchWord, sortType);

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",boardList);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sortType",sortType);
		
		return "board/community/freeBoardList.tiles1";
	}
	
	
	
	
	@RequestMapping(value="/community/questions.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewQuestions(HttpServletRequest request) {
		List<BoardVO> boardList = null;
		
		Map<String, String> paraMap = new HashMap<>();
		 String searchWord = request.getParameter("searchWord");
		 String sortType = request.getParameter("sortType");
		 String str_page = request.getParameter("page");
		 
		 if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
		 	searchWord = "";
		 }
		 
		 if(sortType == null ||sortType == "") {
			 sortType = "write_date";
		 }
		 
		 paraMap.put("searchWord", searchWord);
		 paraMap.put("sortType", sortType);
		 paraMap.put("detail_category", "QnA");
		 System.out.println("" );
		 
		 // 총 게시물 건수
		 int totalCount = service.getTotalCount(paraMap);    
		 // 한 페이지당 보여줄 게시물 건수 
		 int sizePerPage = 10;         
		 // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		 //현재페이지번호 구하기(예외처리 포함)
		 int page = getPage(str_page,totalPage);	
	
		 // 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		 paraMap = getRno(page,sizePerPage,paraMap); 
		 
		 boardList = service.boardList(paraMap); // 글목록 가져오기
		 
		 // 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 것
		 if(!"".equals(searchWord) ) {
			 request.setAttribute("paraMap", paraMap);
		 }
		 
		 String url = "questions.do";
		 //페이지바 얻기
		 String pageBar = getPageBar(page,totalPage, url, searchWord, sortType);

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",boardList);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sortType",sortType);
		
		return "board/community/freeBoardList.tiles1";
   }
	
	
	
	@RequestMapping(value="/community/studies.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewStudies(HttpServletRequest request) {
		List<BoardVO> boardList = null;
		
		Map<String, String> paraMap = new HashMap<>();
		String searchWord = request.getParameter("searchWord");
		String sortType = request.getParameter("sortType");
		String str_page = request.getParameter("page");
		
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
			searchWord = "";
		}
		
		if(sortType == null ||sortType == "") {
			sortType = "write_date";
		}
		
		paraMap.put("searchWord", searchWord);
		paraMap.put("sortType", sortType);
		paraMap.put("detail_category", "스터디");
		
		// 총 게시물 건수
		int totalCount = service.getTotalCount(paraMap);    
		// 한 페이지당 보여줄 게시물 건수 
		int sizePerPage = 10;         
		// 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		//현재페이지번호 구하기(예외처리 포함)
		int page = getPage(str_page,totalPage);	
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap); 
		
		boardList = service.boardList(paraMap); // 글목록 가져오기
		
		// 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 것
		if(!"".equals(searchWord) ) {
			request.setAttribute("paraMap", paraMap);
		}
		
		String url = "studies.do";
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage, url, searchWord, sortType);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",boardList);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sortType",sortType);
		
		return "board/community/freeBoardList.tiles1";
	}
	
	
	@RequestMapping(value="/community/hobbies.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewHobbies(HttpServletRequest request) {
		List<BoardVO> boardList = null;
		
		Map<String, String> paraMap = new HashMap<>();
		 String searchWord = request.getParameter("searchWord");
		 String sortType = request.getParameter("sortType");
		 String str_page = request.getParameter("page");
		 
		 if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
		 	searchWord = "";
		 }
		 
		 if(sortType == null ||sortType == "") {
			 sortType = "write_date";
		 }
		 
		 paraMap.put("searchWord", searchWord);
		 paraMap.put("sortType", sortType);
		 paraMap.put("detail_category", "취미모임");
		 
		 // 총 게시물 건수
		 int totalCount = service.getTotalCount(paraMap);    
		 // 한 페이지당 보여줄 게시물 건수 
		 int sizePerPage = 10;         
		 // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		 //현재페이지번호 구하기(예외처리 포함)
		 int page = getPage(str_page,totalPage);	
	
		 // 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		 paraMap = getRno(page,sizePerPage,paraMap); 
		 
		 boardList = service.boardList(paraMap); // 글목록 가져오기
		 
		 // 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 것
		 if(!"".equals(searchWord) ) {
			 request.setAttribute("paraMap", paraMap);
		 }
		 
		 String url = "hobbies.do";
		 //페이지바 얻기
		 String pageBar = getPageBar(page,totalPage, url, searchWord, sortType);

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",boardList);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sortType",sortType);
		
		return "board/community/freeBoardList.tiles1";
   }
	
	
	
	
	@RequestMapping(value="/community/reviews.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewReviews(HttpServletRequest request) {
		List<BoardVO> boardList = null;
		
		Map<String, String> paraMap = new HashMap<>();
		 String searchWord = request.getParameter("searchWord");
		 String sortType = request.getParameter("sortType");
		 String str_page = request.getParameter("page");
		 
		 if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
		 	searchWord = "";
		 }
		 
		 if(sortType == null ||sortType == "") {
			 sortType = "write_date";
		 }
		 
		 paraMap.put("searchWord", searchWord);
		 paraMap.put("sortType", sortType);
		 paraMap.put("detail_category", "수강/취업후기");
		 
		 // 총 게시물 건수
		 int totalCount = service.getTotalCount(paraMap);    
		 // 한 페이지당 보여줄 게시물 건수 
		 int sizePerPage = 10;         
		 // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		 //현재페이지번호 구하기(예외처리 포함)
		 int page = getPage(str_page,totalPage);	
	
		 // 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		 paraMap = getRno(page,sizePerPage,paraMap); 
		 
		 boardList = service.boardList(paraMap); // 글목록 가져오기
		 
		 // 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 것
		 if(!"".equals(searchWord) ) {
			 request.setAttribute("paraMap", paraMap);
		 }
		 
		 String url = "reviews.do";
		 //페이지바 얻기
		 String pageBar = getPageBar(page,totalPage, url, searchWord, sortType);

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("boardList",boardList);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sortType",sortType);
		
		return "board/community/freeBoardList.tiles1";
   }
	
	
	
	
	
	
	
	// 게시판 글쓰기 페이지 요청
	@RequestMapping(value="/community/new.do" )
	 public String requiredLogin_communityNew(HttpServletRequest request, HttpServletResponse response){ // <== before Advice(로그인체크)
		
		// 카테고리 값 지정용
		// String detail_category = request.getParameter("detail_category");
		
		// request.setAttribute("detail_category", detail_category);
		return "board/community/communityNew.tiles1";
	}

	
   // 스마트에디터. 드래그앤드롭을 사용한 다중사진 파일업로드
   @RequestMapping(value="/image/multiplePhotoUpload.do", method= {RequestMethod.POST} )
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String root = session.getServletContext().getRealPath("/");
		String path = root + "resources"+File.separator+"photo_upload";
		
		// System.out.println("~~~~ 확인용 path => " + path);
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		try {
			String filename = request.getHeader("file-name"); // 원본파일명
			
			// System.out.println(">>> 확인용 filename ==> " + filename);
			
			InputStream is = request.getInputStream();
			
			String newFilename = fileManager.doFileUpload(is, filename, path);
			
			int width = fileManager.getImageWidth(path+File.separator+newFilename);
			
		    if(width > 600) {
		       width = 600;
		    }
		    
			String ctxPath = request.getContextPath();
			
			String strURL = "";
			strURL += "&bNewLine=true&sFileName="+newFilename; 
			strURL += "&sWidth="+width;
			strURL += "&sFileURL="+ctxPath+"/resources/photo_upload/"+newFilename;
			
			PrintWriter out = response.getWriter();
			out.print(strURL);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


   // 게시판 글쓰기 등록
	@RequestMapping(value="/community/newEnd.do", method= {RequestMethod.POST})
	public ModelAndView pointPlusActivityLog_communityNewEnd(Map<String, Object> paraMap, BoardVO boardvo, HttpServletRequest request, ModelAndView mav) {  // <== After Advice(활동점수 올리기)
		
		int n = bdao.communityNew(boardvo);
		
		if(n==1) {
			
			// pointPlusActivityLog After Advice(포인트 업데이트, 활동내역 등록하기) 
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO)session.getAttribute("user");
			String userid = user.getUserid();
			String nickname = user.getNickname();
			
			int board_num = bdao.getCurrentBoardnum(nickname);// 지금 등록된 글번호 가져오기(겹침방지)
			System.out.println(board_num);
			
			paraMap.put("fk_userid", userid);// 포인트, 활동내역용
			paraMap.put("board_num", board_num);// 활동내역용(지금 등록된 글번호)
			paraMap.put("boardvo", boardvo); // 활동내역용
			paraMap.put("division", "게시글작성");// 활동내역용
			paraMap.put("point", 10); // 포인트용			
			
			mav.setViewName("redirect:/community/freeBoards.do");
			//  글쓰기가 성공되어지면 글목록을 보여주는 list.action 페이지로 이동시킨다.
		}
		else {
//			mav.setViewName("board/error/add_error.tiles1");
//			//  /WEB-INF/views/tiles1/board/error/add_error.jsp 파일을 생성한다.
		}
		

		
		return mav;
	}
	
	
	
	// 신고 페이지 요청
	@RequestMapping(value="/community/report.do" )
	// public ModelAndView report(HttpServletRequest request, HttpServletResponse response, ModelAndView mav){ // <== before Advice(로그인체크)
	public ModelAndView report(HttpServletRequest request, HttpServletResponse response, ModelAndView mav){
		
		
		// 카테고리 값 지정용
		// String detail_category = request.getParameter("detail_category");
		
		// mav.addObject("detail_category", detail_category);
		
		mav.setViewName("/report");
		
		return mav;
	}
	
	
	
	
	
   // ============================================================================== //
   // ==================================== 페이징처리 유틸 =============================== //
   // ============================================================================== //
	
   /**
    * 페이지바 만들기 메소드
    * @param page(현재 페이지번호)
    * @param totalPage(총페이지 수)
    * @param searchWord(검색어)
    * @param sortType(정렬조건)
    * @return pageBar
    */
   private String getPageBar(int page, int totalPage,String url, String searchWord, String sortType) {
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
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&sortType="+sortType+"&page=1'>" + 
					   "    <i class='fa-solid fa-angles-left'></i>" + 
					   "  </a>" + 
					   "</li>";
			//[<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&sortType="+sortType+"&page="+(pageNo-1)+"'>" + 
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
						   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&sortType="+sortType+"&page="+pageNo+"'>"+pageNo+"</a>" + 
						   "</li>";        
			}
			
			loop++;
			pageNo++;
		}// end of while--------------------------
		
		// === [>][>>] 만들기 === //
		if( pageNo <= totalPage) {
			//[>]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&sortType="+sortType+"&page="+pageNo+"'>"+
					   "    <i class='fa-solid fa-angle-right'></i>"+
					   "  </a>" + 
					   "</li>";
			
			//[>>] 
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&sortType="+sortType+"&page="+totalPage+"'>"+
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
