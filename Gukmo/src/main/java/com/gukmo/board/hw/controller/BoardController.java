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
	 * 공지사항 페이지 매핑
	 */
	@RequestMapping(value="/notices.do", method= {RequestMethod.GET})
	public String viewNotice(HttpServletRequest request) {
		
		Map<String, String> paraMap = new HashMap<>();
		String searchWord = request.getParameter("searchWord");
		String sort = request.getParameter("sort");
		String str_page = request.getParameter("page");
		String category = "공지사항";
		String detail_category = "공지사항";
		
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
			searchWord = "";
		}
		
		if(sort == null || sort.trim() == "") {	//sort 값이 없다면
			sort = "write_date";
		} else {								//sort 값이 있다면 아래 sort 값 구하기 메서드 호출
			sort = getSort(sort);
		}
		
		paraMap.put("searchWord", searchWord);
		paraMap.put("sort", sort);
		paraMap.put("category",category);
		paraMap.put("detail_category", detail_category);
		
		// 총 게시물 건수
		int totalCount = service.getTotalNoticesCount(paraMap);    
		// 한 페이지당 보여줄 게시물 건수 
		int sizePerPage = 10;         
		// 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		//현재페이지번호 구하기(예외처리 포함)
		int page = getPage(str_page,totalPage);	
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap); 
		
		List<BoardVO> notices = service.getNotices(paraMap); // 글목록 가져오기
		
		String url = request.getContextPath()+"notices.do";
		//정렬기준 넣기
		switch (sort) {
			case "write_date":
				sort = "최신순";
				break;
			case "comment_cnt":
				sort = "댓글순";	
				break;
			case "like_cnt":
				sort = "추천순";
				break;
			case "views":
				sort = "조회순";
				break;
			default :
				sort = "최신순";
				break;
		}//end of switch-case---
		
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage, url, searchWord, sort);
		
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("noticeList",notices);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sort",sort);
		request.setAttribute("detail_category",detail_category);
		
		
		return "board/notice/noticeList.tiles1";
	}
	
	
	
	
	
	
	/**
	 * 국비학원 페이지 매핑
	 */
	@RequestMapping(value="academy/academies.do", method= {RequestMethod.GET})
	public String viewAcademies(HttpServletRequest request) {
		
		Map<String, String> paraMap = new HashMap<>();
		String searchWord = request.getParameter("searchWord");
		String sort = request.getParameter("sort");
		String str_page = request.getParameter("page");
		String category = "국비학원";
		String detail_category = "국비학원";
		
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
			searchWord = "";
		}
		
		if(sort == null || sort.trim() == "") {	//sort 값이 없다면
			sort = "write_date";
		} else {								//sort 값이 있다면 아래 sort 값 구하기 메서드 호출
			sort = getSort(sort);
		}
		
		paraMap.put("searchWord", searchWord);
		paraMap.put("sort", sort);
		paraMap.put("category",category);
		paraMap.put("detail_category", detail_category);
		
		// 총 게시물 건수
		int totalCount = service.getTotalAcademyCount(paraMap);    
		// 한 페이지당 보여줄 게시물 건수 
		int sizePerPage = 10;         
		// 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		//현재페이지번호 구하기(예외처리 포함)
		int page = getPage(str_page,totalPage);	
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap); 
		
		List<BoardVO> academyList = service.getAcademyList(paraMap); // 글목록 가져오기
		
		String url = request.getContextPath()+"academy/academies.do";
		//정렬기준 넣기
		switch (sort) {
			case "write_date":
				sort = "최신순";
				break;
			case "comment_cnt":
				sort = "댓글순";	
				break;
			case "like_cnt":
				sort = "추천순";
				break;
			case "views":
				sort = "조회순";
				break;
			default :
				sort = "최신순";
				break;
		}//end of switch-case---
		
		//확인용
		System.out.println(academyList);
		
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage, url, searchWord, sort);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("academyList",academyList);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sort",sort);
		request.setAttribute("detail_category",detail_category);
		
		return "board/academy/academyList.tiles1";
	}
	
	
	
	
	
	
	
	
	/**
	 * 교육과정페이지 매핑
	 */
	@RequestMapping(value="/academy/curricula.do", method= {RequestMethod.GET})
	public String viewCurricula(HttpServletRequest request) {
		Map<String, String> paraMap = new HashMap<>();
		String searchWord = request.getParameter("searchWord");
		String sort = request.getParameter("sort");
		String str_page = request.getParameter("page");
		String category = "국비학원";
		String detail_category = "교육과정";
		
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
			searchWord = "";
		}
		
		if(sort == null || sort.trim() == "") {	//sort 값이 없다면
			sort = "write_date";
		} else {								//sort 값이 있다면 아래 sort 값 구하기 메서드 호출
			sort = getSort(sort);
		}
		
		paraMap.put("searchWord", searchWord);
		paraMap.put("sort", sort);
		paraMap.put("category",category);
		paraMap.put("detail_category", detail_category);
		
		// 총 게시물 건수
		int totalCount = service.getTotalCurriculumCount(paraMap);    
		// 한 페이지당 보여줄 게시물 건수 
		int sizePerPage = 10;         
		// 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            
		//현재페이지번호 구하기(예외처리 포함)
		int page = getPage(str_page,totalPage);	
		
		// 시작행번호,끝 행번호 구하기(맵에 담아서 반환)
		paraMap = getRno(page,sizePerPage,paraMap); 
		
		List<BoardVO> curriculumList = service.getCurriculumList(paraMap); // 글목록 가져오기
		
		String url = "/academy/curricula.do";
		
		//정렬기준 넣기
		switch (sort) {
			case "write_date":
				sort = "최신순";
				break;
			case "comment_cnt":
				sort = "댓글순";	
				break;
			case "like_cnt":
				sort = "추천순";
				break;
			case "views":
				sort = "조회순";
				break;
			default :
				sort = "최신순";
				break;
		}//end of switch-case---
		
		//확인용
		System.out.println(curriculumList);
		
		//페이지바 얻기
		String pageBar = getPageBar(page,totalPage, url, searchWord, sort);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("curriculumList",curriculumList);
		request.setAttribute("searchWord",searchWord);
		request.setAttribute("sort",sort);
		request.setAttribute("detail_category",detail_category);
		
		return "board/academy/curriculumList.tiles1";
	}

	
	
	
	
	
	/**
	 * 학원상세보기 페이지 매핑
	 */
	@RequestMapping(value="/academy/academy.do", method= {RequestMethod.GET})
	public String viewAcademy(HttpServletRequest request) {
		String str_board_num = request.getParameter("boardNum");
		int boardNum = 0;
		try {
			boardNum = Integer.parseInt(str_board_num); 
		} catch (NullPointerException e) {	//글번호에 한글을 입력했다면
			return "redirect:index.do";
		}//end of try-catch--
		
		int board_num = 3;// 글번호(해시태그 있는 글번호 임시 설정)
		BoardVO academy = service.getBoardDetail(boardNum);	//하나의 글 불러오기(학원게시판)
		
		//확인용
		System.out.println(academy);
		
		request.setAttribute("academy", academy);
		
		return "board/academy/academyDetail.tiles1";
	}
	
	
	
	
	
	
	/**
	 * 학원글작성 페이지 매핑(교육기관회원 로그인 필수)
	 */
	@RequestMapping(value="/academy/new.do", method= {RequestMethod.GET})
		public String viewAcademyNew(HttpServletRequest request) {
		return "board/academy/academyNew.tiles1";
	}
	
	
	

	
	
	
	
   // ============================================================================== //
   // ==================================== 페이징처리 유틸 시작 =============================== //
   // ============================================================================== //
	
   /**
    * 페이지바 만들기 메소드
    * @param page(현재 페이지번호),totalPage(총페이지 수),반응할url,searchWord(검색어)
    * @return pageBar
    */
   private String getPageBar(int page, int totalPage,String url, String searchWord,String sort) {
	   
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
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page=1&sort="+sort+"'>" + 
					   "    <i class='fa-solid fa-angles-left'></i>" + 
					   "  </a>" + 
					   "</li>";
			//[<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+(pageNo-1)+"&sort="+sort+"'>" + 
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
						   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+pageNo+"&sort="+sort+"'>"+pageNo+"</a>" + 
						   "</li>";        
			}
			
			loop++;
			pageNo++;
		}// end of while--------------------------
		
		// === [>][>>] 만들기 === //
		if( pageNo <= totalPage) {
			//[>]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+pageNo+"&sort="+sort+"'>"+
					   "    <i class='fa-solid fa-angle-right'></i>"+
					   "  </a>" + 
					   "</li>";
			
			//[>>] 
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+totalPage+"&sort="+sort+"'>"+
					   "    <i class='fas fa-solid fa-angles-right'></i>"+
					   "  </a>" + 
					   "</li>";
		}
		
		pageBar += "</ul>";
		
		return pageBar;
    }//end of getPageBar(){}---
   
   
   
   
   
   
   
   
   /**
    * 페이지 번호 예외처리하기
    * @param str_page(쿼리스트링으로 날아온 페이지),totalPage(총페이지수)
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
   // ==================================== 페이징처리 유틸 끝 =============================== //
   // ============================================================================== //
	
	
   
   
   /**
	 * 리퀘스트에 담겨있는 sort
	 * @param sort
	 * @return 오라클 필드명과 매칭시킨 정렬조건 sort를 반환한다.
	 */
	private String getSort(String sort) {
		if(sort != null) {
			switch (sort.trim()) {
			case "최신순":
				sort = "write_date";
				break;
			case "댓글순":
				sort = "comment_cnt";	
				break;
			case "추천순":
				sort = "like_cnt";
				break;
			case "조회순":
				sort = "views";
				break;
			default: 
				sort = "write_date";
				break;
				
			}//end of switch-case---
			
		}
		else {
			sort = "write_date";
		}
		return sort;
	}

	
	
	

}
