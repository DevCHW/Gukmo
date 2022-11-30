package com.gukmo.board.hw.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gukmo.board.common.FileManager;
import com.gukmo.board.hw.service.InterBoardService;
import com.gukmo.board.model.BoardVO;

@Controller
public class BoardController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;
	
	
	@Autowired
	private FileManager fileManager;
	
	
	/**
	 * 국비학원 페이지 매핑
	 */
	@RequestMapping(value="academy/academies.do", method= {RequestMethod.GET})
	public String viewAcademy(HttpServletRequest request) {
		
		Map<String, String> paraMap = new HashMap<>();
		String searchWord = request.getParameter("searchWord");
		String sort = request.getParameter("sort");
		String str_page = request.getParameter("page");
		String category = "국비학원";
		String detail_category = "국비학원";
		
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
			searchWord = "";
		}
		
		if(sort == null ||sort == "") {
			sort = "write_date";
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
		
		// 검색어를 뷰단 페이지에서 유지시키기 위한 것
		if(!"".equals(searchWord) ) {
			request.setAttribute("paraMap", paraMap);
		}
		
		String url = "academy/academies.do";
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
	public String view(HttpServletRequest request) {
		return "board/academy/curriculumList.tiles1";
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
		switch (sort) {
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
			default :
				sort = "write_date";
				break;
		}//end of switch-case---
		return sort;
	}

	
	
	

}
