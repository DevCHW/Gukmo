package com.gukmo.board.sm.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.model.ReportVO;
import com.gukmo.board.sm.admin.service.InterReportService;

@Controller
public class ReportController {
	
	@Autowired
	private InterReportService service;
	
	
/*	
	//신고 리스트 보여주기(전체)
	@RequestMapping(value="/admin/report/list.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_reportManage_List(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	    List<ReportVO> reportList = null;
	    
	    String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String memberStatus = request.getParameter("report_type");
		
		// System.out.println(searchType);
		// System.out.println(searchWord);
		
		String str_page = request.getParameter("page");
		
		if(memberStatus == null) {
			memberStatus = "";
		}
		
		 if(searchType == null || (!"report_nickname".equals(searchType) && !"reported_nickname".equals(searchType) ) ) {
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
		 // 총 게시물 건수(totalCount)
		 int totalCount = service.getTotalCount_report(paraMap);
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
			
		 paraMap = getRno(page,sizePerPage,paraMap);
		 String url = "reportManage_List.do";
		 
		 reportList = service.reportList(paraMap);
		 
		 
		Map<String,String> pageMap = new HashMap<>();
		pageMap.put("searchWord",searchWord);
		pageMap.put("searchType",searchType);
		pageMap.put("memberStatus",memberStatus);
		pageMap.put("keyWord", "report_type");
		
		String pageBar = getPageBar(page,totalPage,url,pageMap);
		
		
		if( !"".equals(searchType) && !"".equals(searchWord) ) {
			 mav.addObject("paraMap", paraMap);
		 }
		mav.addObject("pageBar", pageBar);
		mav.addObject("reportList", reportList);
		
		request.setAttribute("paraMap", paraMap);
		request.setAttribute("totalCount", totalCount);
		
		mav.setViewName("admin/report/list.tiles2");
		
		return mav;
	} // end of 신고내역 리스트 보기
*/	
	
	
//신고 리스트 보여주기(신고전)
@RequestMapping(value="/admin/report/before_receipt_list.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
public ModelAndView requiredAdminLogin_reportManage_List_before(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
    List<ReportVO> reportList = null;
    
    String searchType = request.getParameter("searchType");
	String searchWord = request.getParameter("searchWord");
	String memberStatus = request.getParameter("report_type");
	
	// System.out.println(searchType);
	// System.out.println(searchWord);
	
	String str_page = request.getParameter("page");
	
	if(memberStatus == null) {
		memberStatus = "";
	}
	
	 if(searchType == null || (!"report_nickname".equals(searchType) && !"reported_nickname".equals(searchType) ) ) {
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
	 // 총 게시물 건수(totalCount)
	 int totalCount = service.getTotalCount_report_before(paraMap);
	 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
	 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
	 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		
	 paraMap = getRno(page,sizePerPage,paraMap);
	 String url = "reportManage_List.do";
	 
	 reportList = service.reportList_before(paraMap);
	 
	 
	Map<String,String> pageMap = new HashMap<>();
	pageMap.put("searchWord",searchWord);
	pageMap.put("searchType",searchType);
	pageMap.put("memberStatus",memberStatus);
	pageMap.put("keyWord", "report_type");
	
	String pageBar = getPageBar(page,totalPage,url,pageMap);
	
	
	if( !"".equals(searchType) && !"".equals(searchWord) ) {
		 mav.addObject("paraMap", paraMap);
	 }
	mav.addObject("pageBar", pageBar);
	mav.addObject("reportList", reportList);
	
	request.setAttribute("paraMap", paraMap);
	request.setAttribute("totalCount", totalCount);
	
	mav.setViewName("admin/report/list.tiles1");
	
	return mav;
} // end of 신고내역 리스트 보기
	

	//신고 리스트 보여주기(신고 완료)
	@RequestMapping(value="/admin/report/after_receipt_list.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_reportManage_List_after(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	List<ReportVO> reportList = null;
	
	String searchType = request.getParameter("searchType");
	String searchWord = request.getParameter("searchWord");
	String memberStatus = request.getParameter("report_type");
	
	// System.out.println(searchType);
	// System.out.println(searchWord);
	
	String str_page = request.getParameter("page");
	
	if(memberStatus == null) {
		memberStatus = "";
	}
	
	 if(searchType == null || (!"report_nickname".equals(searchType) && !"reported_nickname".equals(searchType) ) ) {
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
	 // 총 게시물 건수(totalCount)
	 int totalCount = service.getTotalCount_report_after(paraMap);
	 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
	 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
	 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		
	 paraMap = getRno(page,sizePerPage,paraMap);
	 String url = "reportManage_List.do";
	 
	 reportList = service.reportList_after(paraMap);
	 
	 
	Map<String,String> pageMap = new HashMap<>();
	pageMap.put("searchWord",searchWord);
	pageMap.put("searchType",searchType);
	pageMap.put("memberStatus",memberStatus);
	pageMap.put("keyWord", "report_type");
	
	String pageBar = getPageBar(page,totalPage,url,pageMap);
	
	
	if( !"".equals(searchType) && !"".equals(searchWord) ) {
		 mav.addObject("paraMap", paraMap);
	 }
	mav.addObject("pageBar", pageBar);
	mav.addObject("reportList", reportList);
	
	request.setAttribute("paraMap", paraMap);
	request.setAttribute("totalCount", totalCount);
	
	mav.setViewName("admin/report/list.tiles1");
	
	return mav;
	} // end of 신고내역 리스트 보기


	
		
	// 신고 관련 정보 상세보기 
	@RequestMapping(value="/admin/report/detail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_reportDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		Map<String, String> paraMap = new HashMap<>();
		String report_num = request.getParameter("report_num");
		String report_nickname = request.getParameter("report_nickname");
		String reported_nickname = request.getParameter("reported_nickname");

		paraMap.put("report_num", report_num);
		paraMap.put("report_nickname", report_nickname);
		paraMap.put("reported_nickname", reported_nickname);
		
		// 신고 상세 내용 뽑기
		ReportVO reportDetail = service.getreportDetail(paraMap);
		
		// 신고한 자가 신고한 건수 뽑기
 		List<Integer> report_cnt = service.getreport_cnt(paraMap);
		int report_cnt_board = report_cnt.get(0);
		int report_cnt_comment = report_cnt.get(1);
		request.setAttribute("report_cnt_board", report_cnt_board);
		request.setAttribute("report_cnt_comment", report_cnt_comment);
				
		// 신고받은 자가 신고받은 건수 뽑기
 		List<Integer> reported_cnt = service.getreported_cnt(paraMap);
		int reported_cnt_board = reported_cnt.get(0);
		int reported_cnt_comment = reported_cnt.get(1);
		request.setAttribute("reported_cnt_board", reported_cnt_board);
		request.setAttribute("reported_cnt_comment", reported_cnt_comment);
		
		String reportedNickname = reportDetail.getReported_nickname();		
		String reportedId = service.getReportedId(reportedNickname);
		
		request.setAttribute("reportedId", reportedId);
		
		mav.addObject("reportDetail", reportDetail);
				
		mav.setViewName("admin/report/detail.tiles2");
		return mav;
	}// 신고 관련 정보 상세보기 끝
	
	
	
	// 신고자가 신고한 내역보기 클릭시 
	@ResponseBody
	@RequestMapping(value="/admin/get_reportList.do",method = {RequestMethod.POST})
	public String get_reportList(HttpServletRequest request) throws Throwable {
		
		String report_nickname = request.getParameter("nickname");
		
		Map<String, String> paraMap = new HashMap<>();
		
		paraMap.put("report_nickname",report_nickname);
		
		// 신고자가 신고한 내역 뽑기
		List<ReportVO> get_reportList = service.getReport_List(paraMap);
		System.out.println(get_reportList);
		JSONArray jsonArr = new JSONArray();
		
		if(get_reportList != null) {
			for(ReportVO rvo : get_reportList) {
				JSONObject jsObj = new JSONObject();
				jsObj.put("reported_nickname", rvo.getReported_nickname() );
				jsObj.put("simple_report_reason", rvo.getSimple_report_reason() );
				jsObj.put("report_date", rvo.getReport_date() );
				
				jsonArr.put(jsObj);
			}
		}
		
		return jsonArr.toString();
		
	}
	
	// 신고자가 신고한 내역보기 클릭시 
	@ResponseBody
	@RequestMapping(value="/admin/get_reportedList.do",method = {RequestMethod.POST})
	public String get_reportedList(HttpServletRequest request) throws Throwable {
		
		String reported_nickname = request.getParameter("nickname");
		
		Map<String, String> paraMap = new HashMap<>();
		
		paraMap.put("reported_nickname",reported_nickname);
		
		// 피신고자가 신고당한 내역 뽑기
		List<ReportVO> get_reportedList = service.getReported_List(paraMap);
		
		// System.out.println(get_reportedList);
		
		JSONArray jsonArr = new JSONArray();
		
		if(get_reportedList != null) {
			for(ReportVO rvo : get_reportedList) {
				JSONObject jsObj = new JSONObject();
				jsObj.put("report_nickname", rvo.getReport_nickname() );
				jsObj.put("simple_report_reason", rvo.getSimple_report_reason() );
				jsObj.put("report_date", rvo.getReport_date() );
				
				jsonArr.put(jsObj);
			}
		}
		
		return jsonArr.toString();
		
	}
	
	
	
	
	
	// == 페이징처리 유틸 == //
	
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
