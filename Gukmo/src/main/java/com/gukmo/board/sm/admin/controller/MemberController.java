package com.gukmo.board.sm.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.model.DataTableDTO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.sm.admin.service.InterMemberService;

@Controller
public class MemberController {
	@Autowired
	private InterMemberService service;
	
	
	
	// 회원관리 목록 페이지 요청
	@RequestMapping(value="/admin/member/normal/list3.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_memberManageList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	    List<MemberVO> memberList = null;
	    
	    
	    String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String memberStatus = request.getParameter("memberStatus");
		
		String str_page = request.getParameter("page");
		
		if(memberStatus == null) {
			memberStatus = "";	
		}
		
		 if(searchType == null || (!"fk_userid".equals(searchType) && !"nickname".equals(searchType) && !"username".equals(searchType)) ) {
			searchType = "";
		 }
		
		 if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
		 	searchWord = "";
		 }
		 
		 Map<String, String> paraMap = new HashMap<>();
		 paraMap.put("searchType", searchType);
		 paraMap.put("searchWord", searchWord);
		 paraMap.put("memberStatus", memberStatus);

		 
		 // 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
		 // 총 게시물 건수(totalCount)는 검색조건이 있을 때와 없을때로 나뉘어진다.
		 int totalCount = service.getTotalCount(paraMap);           // 총 게시물 건수
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
			
		 paraMap = getRno(page,sizePerPage,paraMap);
		 String url = request.getContextPath()+"/admin/member/normal/list.do";
		 
		 memberList = service.memberList(paraMap);
		 
		Map<String,String> pageMap = new HashMap<>();
		pageMap.put("searchWord",searchWord);
		pageMap.put("searchType",searchType);
		pageMap.put("memberStatus",memberStatus);
		pageMap.put("keyWord", "memberStatus");

		String pageBar = getPageBar(page,totalPage,url,pageMap);
		
		 if( !"".equals(searchType) && !"".equals(searchWord) ) {
			 mav.addObject("paraMap", paraMap);
		 }

		mav.addObject("pageBar", pageBar);
		mav.addObject("memberList", memberList);
		request.setAttribute("paraMap", paraMap);
		request.setAttribute("totalCount", totalCount);			
		
		mav.setViewName("admin/member/normal/list.tiles1");
		return mav;
	}
		
		
		
		
	// ============= 일반회원 관리 끝  ============= //

		
		
		
		
		
		
		
	// ============= 학원회원 관리 시작 ============= //

	// 교육기관회원관리 목록 페이지 요청
	@ResponseBody
	@RequestMapping(value="/admin/member/academy/list2.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public DataTableDTO academyManageList(DataTableDTO dto,@RequestBody MultiValueMap<String, String> formData) {
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
		 
//	    int total = (int)service.getTotalCount_academy();
	    int total = 1;
//		List<MemberVO> data = service.academymemberList();
		
		dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
//	    dto.setData(data);

	    return dto;
	} // end of 학원회원 리스트 보기
		
		
		
		
		
		
		
		
		
		
		
		
	//==================================== 페이징 처리 유틸 메소드 ============================ //		
		
		
	/**
    * 페이지바 만들기 메소드
    * @param page(현재 페이지번호),totalPage(총페이지 수),반응할url,searchWord(검색어)
    * @return pageBar
    */
   private String getPageBar(int page, int totalPage, String url, Map<String,String> pageMap) {
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
                  "  <a class='page-link' href='"+url+"?"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page=1'>" + 
                  "    <i class='fa-solid fa-angles-left'></i>" + 
                  "  </a>" + 
                  "</li>";
         //[<]
         pageBar += "<li class='page-item'>" + 
                  "  <a class='page-link' href='"+url+"?"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+(pageNo-1)+"'>" + 
                  "    <i class='fa-solid fa-angle-left'></i>" + 
                  "  </a>" + 
                  "</li>"; 
      }
      
      while( !(loop > blockSize || pageNo > totalPage) ) {
         
         if(pageNo == page) {   //페이지번호가 현재페이지번호와 같다면 .active
            pageBar += "<li class='page-item active' aria-current='page'>" + 
                     "  <a class='page-link' href='#'>"+pageNo+"</a>" + 
                     "</li>";  
         }
         
         else {   //페이지번호가 현재페이지번호랑 다르다면 .active 뺌
            pageBar += "<li class='page-item'>" + 
                     "  <a class='page-link' href='"+url+"?"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+pageNo+"'>"+pageNo+"</a>" + 
                     "</li>";        
         }
         
         loop++;
         pageNo++;
      }// end of while--------------------------
      
      // === [>][>>] 만들기 === //
      if( pageNo <= totalPage) {
         //[>]
         pageBar += "<li class='page-item'>" + 
                  "  <a class='page-link' href='"+url+"?"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+pageNo+"'>"+
                  "    <i class='fa-solid fa-angle-right'></i>"+
                  "  </a>" + 
                  "</li>";
         
         //[>>] 
         pageBar += "<li class='page-item'>" + 
                  "  <a class='page-link' href='"+url+"?"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+totalPage+"'>"+
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
      if(str_page == null) {   //쿼리스트링에 페이지가 없다면
          // 게시판에 보여지는 초기화면 
          page = 1;
      } else {
          try {
             page = Integer.parseInt(str_page);
             if( page < 1) {   //페이지가 1페이지보다 작은경우
                page = 1;
             }
             else if(page > totalPage) { //페이지가 총페이지보다 큰 경우
                page = totalPage;
             }
          } catch(NumberFormatException e) {   //페이지번호에 글자를 써서 들어올 경우 오류방지
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
		

		
		
		
		
}
