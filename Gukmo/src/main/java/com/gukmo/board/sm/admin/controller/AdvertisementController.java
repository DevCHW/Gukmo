
package com.gukmo.board.sm.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.gukmo.board.common.FileManager;
import com.gukmo.board.model.AdVO;
import com.gukmo.board.sm.admin.service.InterAdvertisementService;

@Controller
public class AdvertisementController {
	
	@Autowired
	private InterAdvertisementService service;
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;
	
	//
	

 	
	// 광고관리 목록 페이지 요청
	@RequestMapping(value="/admin/advertisement/list2.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_adManageList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	    List<AdVO> adList = null;
	    
	    String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String memberStatus = request.getParameter("division");
		
		String str_page = request.getParameter("page");
		
		if(memberStatus == null) {
			memberStatus = "";
		}
		
		 if(searchType == null || (!"client_name".equals(searchType) && !"client_phone".equals(searchType) ) ) {
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
		 int totalCount = service.getTotalCount_ad(paraMap);
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
			
		 paraMap = getRno(page,sizePerPage,paraMap);
		 String url = request.getContextPath()+"/admin/advertisement/list.do";
		 
		 adList = service.adList(paraMap);
		 
		 
		Map<String,String> pageMap = new HashMap<>();
		pageMap.put("searchWord",searchWord);
		pageMap.put("searchType",searchType);
		pageMap.put("memberStatus",memberStatus);
		pageMap.put("keyWord", "division");

		String pageBar = getPageBar(page,totalPage,url,pageMap);
		
		
		if( !"".equals(searchType) && !"".equals(searchWord) ) {
			 mav.addObject("paraMap", paraMap);
		 }
		mav.addObject("pageBar", pageBar);
		mav.addObject("adList", adList);

		request.setAttribute("division", memberStatus);
		request.setAttribute("paraMap", paraMap);
		request.setAttribute("totalCount", totalCount);
		
		//재탄생한 페이지
		mav.setViewName("admin/advertisement/list.tiles1");
		
		return mav;
	} // end of 광고내역 리스트 보기

	
	
	
	
	
	
	// 광고 관련 정보 상세보기 
	@RequestMapping(value="/admin/advertisement/detail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_adDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		Map<String, String> paraMap = new HashMap<>();
		String advertisement_num = request.getParameter("advertisement_num");
		paraMap.put("advertisement_num", advertisement_num);
		
		AdVO adDetail = service.getAdDetail(paraMap);
		
		mav.addObject("adDetail", adDetail);
		
		//이전페이지
//				mav.setViewName("admin/adDetail.tiles1");
		
		//재탄생한페이지
		mav.setViewName("admin/advertisement/detail.tiles2");
		
		
		return mav;
	}// 광고 관련 정보 상세보기 끝
	
	
	
	// 광고 등록 페이지 보여주기
	@RequestMapping(value="/admin/advertisement/new.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_adRegister(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	    mav.setViewName("admin/advertisement/new.tiles2");
		return mav;
	} // end of 광고 등록 페이지
	

	
	
	
	// 광고 등록 완료 페이지
	@RequestMapping(value="/admin/advertisement/insert.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView adRegisterResult(MultipartHttpServletRequest mrequest, HttpServletResponse response, ModelAndView mav, AdVO advo) {

		MultipartFile attach = advo.getAttach();

		// WAS 의 webapp 의 절대경로를 알아와야 한다.
		HttpSession session = mrequest.getSession();
		String root = "C:/Users/sist/git/Gukmo/Gukmo/src/main/webapp/resources/images/";
		System.out.println(root);
		String path = root+"resources"+ File.separator +"files";
		// path 가 첨부파일이 저장될 WAS(톰캣)의 폴더가 된다.
		
		String newFileName = "";
		// WAS(톰캣)의 디스크에 저장될 파일명 
		
		byte[] bytes = null;
		// 첨부파일의 내용물을 담는 것
		
		long fileSize = 0;
		// 첨부파일의 크기 
	
		try {
			bytes = attach.getBytes();
			// 첨부파일의 내용물을 읽어오는 것
			
			String originalFilename = attach.getOriginalFilename();
			// attach.getOriginalFilename() 이 첨부파일명의 파일명(예: 강아지.png) 이다. 
			// System.out.println("~~~~ 확인용 originalFilename => " + originalFilename); 
		//	~~~~ 확인용 originalFilename => LG_싸이킹청소기_사용설명서.pdf
			
			newFileName = fileManager.doFileUpload(bytes, originalFilename, path);
			           // 첨부되어진 파일을 업로드 하도록 하는 것이다.  
			
		//	System.out.println(">>> 확인용 newFileName => " + newFileName);
			// >>> 확인용 newFileName => 202210281521341200152368627000.pdf
			
		
		//   3. BoardVO boardvo 에 fileName 값과 orgFilename 값과 fileSize 값을 넣어주기
			
			advo.setFilename(newFileName);
			// WAS(톰캣)에 저장된 파일명(202210281521341200152368627000.pdf)
			
			advo.setOrgFilename(originalFilename);
			// 게시판 페이지에서 첨부된 파일(강아지.png)을 보여줄 때 사용.
			// 또한 사용자가 파일을 다운로드 할때 사용되어지는 파일명으로 사용.
			// LG_싸이킹청소기_사용설명서.pdf
			
			fileSize = attach.getSize(); // 첨부파일의 크기(단위는 byte임)
			advo.setFilesize(String.valueOf(fileSize));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 첨부파일 업로드 작업 끝 !
	
		// tbl_advertisement에 해당 광고 insert
		int n = service.addAd(advo);
				
		String message = "";
		String loc = mrequest.getContextPath()+"/admin/index.do";
		if(n == 1) {	//광고 등록에 성공하였다면		
			message = "광고 등록 성공!";
		} else {	//광고등록에 실패하였다면
			message = "광고 등록 실패! 다시 시도해주세요.";
		}
		mav.addObject("message", message);
		mav.addObject("loc", loc);
		mav.setViewName("msg");
		return mav;
	}
	

	
	// 광고 상세에서 파일 다운로드하는 메소드
	@RequestMapping(value="/admin/download.do")
	public void  download(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> paraMap = new HashMap<>();
		String advertisement_num = request.getParameter("advertisement_num");
		paraMap.put("advertisement_num", advertisement_num);
		
		AdVO advo = service.getAdDetail(paraMap);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		try {
			Integer.parseInt(advertisement_num);

		     if(advo == null || (advo != null && advo.getFilename() == null ) ) {
		    	 out = response.getWriter();
				 // out 은 웹브라우저에 기술하는 대상체라고 생각하자.
					
				 out.println("<script type='text/javascript'>alert('존재하지 않는 글번호 이거나 첨부파일 없으므로 파일다운로드가 불가합니다.'); history.back();</script>"); 
				 return; // 종료
		     }
		     else {
							
				String filename = advo.getFilename();
				String orgfilename = advo.getOrgfilename();
				
				HttpSession session = request.getSession();
				String root = "C:/Users/sist/git/Gukmo/Gukmo/src/main/webapp/resources/images/";
		 
				 String path = root+"resources"+ File.separator +"files";
	
				 
				 boolean flag = false;  // file 다운로드 성공, 실패를 알려주는 용도
				 flag = fileManager.doFileDownload(filename, orgfilename, path, response);
	
				 if(!flag) {
					 // 다운로드가 실패할 경우 메시지를 띄워준다.
					 out = response.getWriter();
					 // out 은 웹브라우저에 기술하는 대상체라고 생각하자.
						
					 out.println("<script type='text/javascript'>alert('파일다운로드가 실패되었습니다.'); history.back();</script>"); 
				 }
		     }
		     
		} catch(NumberFormatException | IOException e) {
			try {
				out = response.getWriter();
				// out 은 웹브라우저에 기술하는 대상체라고 생각하자.
				
				out.println("<script type='text/javascript'>alert('파일다운로드가 불가합니다.'); history.back();</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	} // 광고 상세에서 파일 다운로드하는 메소드 끝
	

	
	// 광고 날짜 변경시 tbl_advertisement 에서 start_date, period 변경
	@ResponseBody
	@RequestMapping(value="/admin/advertisement/edit_ad.do", method={RequestMethod.POST},  produces="text/plain;charset=UTF-8") 
	public String edit_ad(@RequestParam Map<String, String> paraMap) {
		System.out.println(paraMap);
		int result = service.edit_ad(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);  // {"result":1}
		
		return jsonObj.toString(); // {"result":1}
	}
	
	// 광고일정 페이지 매핑
	@RequestMapping(value="/admin/advertisement/calendar.do", method={RequestMethod.GET})
	public String viewCalendar(HttpServletRequest request) {
		//서비스에서 광고 데이터 받아와서 달력에 찍어줘야 할듯?
		//구글캘린더는 모르겠고 뭔가 우리 오라클 DB 써야 할거같긴함.. 사이드바 작업은 이미 해둠
		
		
		return "admin/advertisement/calendar.tiles2";
	}
	
	
	// 광고 일정 캘린더에 광고 일정 박기(ajax)
	@ResponseBody
	@RequestMapping(value="/admin/view_AdSchedule.do", method={RequestMethod.POST},  produces="text/plain;charset=UTF-8") 
	public String view_AdSchedule() {

		List<AdVO> adList = service.getAdList();		
		System.out.println(adList);

		JSONArray jsonArr = new JSONArray();
		
		Map<String, Object> hash = new HashMap<>();
		
		for(int i=0; i < adList.size(); i++) {			
			hash.put("title", adList.get(i).getClient_name());
			hash.put("start", adList.get(i).getStart_date());
			hash.put("end", adList.get(i).getEnd_date());
			
			JSONObject jsonObj = new JSONObject(hash);			
			jsonArr.put(jsonObj);

		} //end of for
		
		System.out.println(jsonArr.toString());
		
		return jsonArr.toString();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   // 페이지바 만들기 메소드
   // @param page(현재 페이지번호),totalPage(총페이지 수),반응할url,searchWord(검색어)
   // @return pageBar
   
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
	   
	   




	
	  // 페이지 번호 예외처리하기
	  // @param str_page(쿼리스트링으로 날아온 페이지),totalPage(총페이지수)
	  // @return page(현재 페이지번호)
	 
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
	
		
	// 시작행번호,끝행번호 구하기
	// @param 페이지번호,한페이지당보여줄 갯수,쓰던 맵
	// @return 맵(시작행번호,끝행번호 담아서 줌)
	 
	private Map<String, String> getRno(int page, int sizePerPage, Map<String, String> paraMap) {
	   int startRno = ((page - 1) * sizePerPage) + 1; // 시작 행번호(쿼리문 rownum where절에 쓰임)
	   int endRno = startRno + sizePerPage - 1; // 끝 행번호(쿼리문 rownum where절에 쓰임)
	   
	   paraMap.put("startRno", String.valueOf(startRno));
	   paraMap.put("endRno", String.valueOf(endRno));
	   return paraMap;
	}
	

}

