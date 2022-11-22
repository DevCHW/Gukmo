package com.gukmo.board.sm.controller;

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

import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;
import com.gukmo.board.sm.service.InterAdManageService;
import com.gukmo.board.sm.service.InterMemberManageService;

@Controller
public class memberManageController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterMemberManageService service;

	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdManageService service_ad;

	// ============= 일반회원 관리 시작 ============= //

	// 회원관리 목록 페이지 요청
	@RequestMapping(value="/admin/memberManage_List.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_memberManageList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	    List<MemberVO> memberList = null;
	    
	    String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String memberStatus = request.getParameter("memberStatus");
		// System.out.println(searchType);
		// System.out.println(searchWord);
		// System.out.println(memberStatus);
		
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
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
		 int totalCount = 0;           // 총 게시물 건수
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int currentShowPageNo = 0;    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		 int totalPage = 0;            // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
			
		 int startRno = 0; // 시작 행번호
		 int endRno = 0;   // 끝 행번호
		 
		 // 총 게시물 건수(totalCount)
		 totalCount = service.getTotalCount(paraMap);
		 request.setAttribute("totalCount", totalCount);
		 // System.out.println(totalCount);
		 // 108
		 
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
		 // System.out.println();
		
		 if( !"".equals(searchType) && !"".equals(searchWord) ) {
			 mav.addObject("paraMap", paraMap);
		 }
		 
			// === #121. 페이지바 만들기 === //
			int blockSize = 10;
			// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수이다.
			/*
				              1  2  3  4  5  6  7  8  9 10 [다음][마지막]  -- 1개블럭
				[맨처음][이전]  11 12 13 14 15 16 17 18 19 20 [다음][마지막]  -- 1개블럭
				[맨처음][이전]  21 22 23
			*/
			
			int loop = 1;
			int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

			String pageBar = "<ul class=\"my pagination pagination-md justify-content-center mt-5\">";
			String url = "memberManage_List.do";

			// === [맨처음][이전] 만들기 === //
			if(pageNo != 1) {
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'><i class=\"fa-solid fa-angles-left\"></i></a></li>";
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'><i class=\"fa-solid fa-angles-left\"></i></a></li>"; 
			}		
			
			while( !(loop > blockSize || pageNo > totalPage) ) {
				
				if(pageNo == currentShowPageNo) {
					pageBar += "<li class=\"page-item active\" aria-current=\"page\">"+pageNo+"</li>";  
				}
				else {
					pageBar += "<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";        
				}
				loop++;
				pageNo++;
			}// end of while--------------------------
			
			// === [다음][마지막] 만들기 === //
			if( pageNo <= totalPage) {
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'><i class=\"fa-solid fa-angle-right\"></i></a></li>";
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'><i class=\"fas fa-solid fa-angles-right\"></i></a></li>";
			}
			
			pageBar += "</ul>";
			
			mav.addObject("pageBar", pageBar);
			mav.addObject("memberList", memberList);

			
		mav.setViewName("admin/memberManage_List.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberManage_List.jsp 파일을 생성한다.
		return mav;
	}
	
	
	
	// 회원 정보 상세보기 
	@RequestMapping(value="/admin/memberDetail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_memberDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		Map<String, String> paraMap = new HashMap<>();
		String userid = request.getParameter("userid");
		paraMap.put("userid", userid);
		
		MemberVO memberDetail = service.getMemberDetail(paraMap);
		
		mav.addObject("memberDetail", memberDetail);
		mav.setViewName("admin/memberDetail.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	}
	
	// ============= 일반회원 관리 끝  ============= //

	// ============= 학원회원 관리 시작 ============= //

	// 회원관리 목록 페이지 요청
	@RequestMapping(value="/admin/academyManage_List.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_academyManageList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	    List<MemberVO> academymemberList = null;
	    
	    String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String memberStatus = request.getParameter("memberStatus");
		
		// System.out.println(searchType);
		// System.out.println(searchWord);
		
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(memberStatus == null) {
			memberStatus = "";
		}
		
		 if(searchType == null || (!"fk_userid".equals(searchType) && !"nickname".equals(searchType) && !"academy_name".equals(searchType)) ) {
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
		 int totalCount = 0;           // 총 게시물 건수
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int currentShowPageNo = 0;    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		 int totalPage = 0;            // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
			
		 int startRno = 0; // 시작 행번호
		 int endRno = 0;   // 끝 행번호
		 
		 // 총 게시물 건수(totalCount)
		 totalCount = service.getTotalCount_academy(paraMap);
		 request.setAttribute("totalCount", totalCount);
		 // System.out.println(totalCount);
		 // 108
		 
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

		 academymemberList = service.academymemberList(paraMap);
		
		 if( !"".equals(searchType) && !"".equals(searchWord) ) {
			 mav.addObject("paraMap", paraMap);
		 }
		 
			// === #121. 페이지바 만들기 === //
			int blockSize = 10;
			// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수이다.
			/*
				              1  2  3  4  5  6  7  8  9 10 [다음][마지막]  -- 1개블럭
				[맨처음][이전]  11 12 13 14 15 16 17 18 19 20 [다음][마지막]  -- 1개블럭
				[맨처음][이전]  21 22 23
			*/
			
			int loop = 1;
			int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

			String pageBar = "<ul class=\"my pagination pagination-md justify-content-center mt-5\">";
			String url = "academyManage_List.do";

			// === [맨처음][이전] 만들기 === //
			if(pageNo != 1) {
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'><i class=\"fa-solid fa-angles-left\"></i></a></li>";
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'><i class=\"fa-solid fa-angles-left\"></i></a></li>"; 
			}		
			
			while( !(loop > blockSize || pageNo > totalPage) ) {
				
				if(pageNo == currentShowPageNo) {
					pageBar += "<li class=\"page-item active\" aria-current=\"page\">"+pageNo+"</li>";  
				}
				else {
					pageBar += "<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";        
				}
				loop++;
				pageNo++;
			}// end of while--------------------------
			
			// === [다음][마지막] 만들기 === //
			if( pageNo <= totalPage) {
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'><i class=\"fa-solid fa-angle-right\"></i></a></li>";
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?memberStatus="+memberStatus+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'><i class=\"fas fa-solid fa-angles-right\"></i></a></li>";
			}
			
			pageBar += "</ul>";
			
			mav.addObject("pageBar", pageBar);
			mav.addObject("academymemberList", academymemberList);

			
		mav.setViewName("admin/academyManage_List.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberManage_List.jsp 파일을 생성한다.
		return mav;
	} // end of 학원회원 리스트 보기
	
	// 회원 정보 상세보기 
	@RequestMapping(value="/admin/aca_memberDetail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_aca_memberDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		Map<String, String> paraMap = new HashMap<>();
		String userid = request.getParameter("userid");
		paraMap.put("userid", userid);
		
		MemberVO aca_memberDetail = service.getAcademyDetail(paraMap);
		
		mav.addObject("aca_memberDetail", aca_memberDetail);
		mav.setViewName("admin/aca_memberDetail.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	}

	
	// 회원가입 요청 승인
	@ResponseBody
	@RequestMapping(value="/admin/Regi_agree.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String requiredAdminLogin_Regi_agree(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		String userid = request.getParameter("userid");

		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("userid", userid);
		
		int n = service.Regi_agree(paraMap);
		JSONObject jsonObj = new JSONObject();
		
		if(n== 1) {
			jsonObj.put("n", n);
		}
		
		return jsonObj.toString(); // "{"n":1,"name":"서영학"}" 또는 "{"n":0,"name":"서영학"}"
		
	} //end of 정지 누나
	
	// ============= 학원회원 관리 끝 ============= //
	
	//////////////////////////////////////////////////////
	
	// ============= 공통(정지 등록, 해제, 휴면 해제) 시작 ============= //

	// 회원 정지 등록 페이지
	@RequestMapping(value="/admin/penaltyRegister.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_penaltyRegister(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		Map<String, String> paraMap = new HashMap<>();
		String userid = request.getParameter("userid");
		String nickname = request.getParameter("nickname");
		
		paraMap.put("userid", userid);
		
		request.setAttribute("userid", userid);
		request.setAttribute("nickname", nickname);
		
	    mav.setViewName("admin/penaltyRegister.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	}	
	
	// 회원 정지 등록 완료 페이지
	@RequestMapping(value="/admin/penaltyRegisterResult.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_penaltyRegisterResult(HttpServletRequest request, HttpServletResponse response, ModelAndView mav, PenaltyVO pvo) {
		
		String userid = request.getParameter("userid");
		Map<String,String> paraMap = new HashMap<>();
		
		// tbl_penalty에 해당 회원 정지 insert
		int n = service.addPenalty(pvo);
				
		if(n == 1 ) {
			
			// tbl_member_login에 해당 회원 status 변경(정지)
			int n2 = service.updateMemberStatus(userid);
			
			if(n2 == 1) {
				mav.setViewName("redirect:/admin/memberManage_List.do");
			}
		}
		
		
		return mav;
	}

	// 정지 해제 
	@ResponseBody
	@RequestMapping(value="/admin/block_recovery.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String requiredAdminLogin_block_recovery(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		String userid = request.getParameter("userid");

		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("userid", userid);
		
		int n = service.block_recovery(paraMap);
		JSONObject jsonObj = new JSONObject();
		
		if(n== 1) {
			jsonObj.put("n", n);
		}
		
		return jsonObj.toString(); // "{"n":1,"name":"서영학"}" 또는 "{"n":0,"name":"서영학"}"
		
	} //end of 정지 누나
	
	
	// 휴면 > 활동
	@ResponseBody
	@RequestMapping(value="/admin/sleep_recovery.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String requiredAdminLogin_sleep_recovery(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		String userid = request.getParameter("userid");

		Map<String, String> paraMap = new HashMap<>();		
		paraMap.put("userid", userid);
		
		int n = service.sleep_recovery(paraMap);
		JSONObject jsonObj = new JSONObject();
		
		if(n== 1) {
			jsonObj.put("n", n);
		}
		
		return jsonObj.toString(); // "{"n":1,"name":"서영학"}" 또는 "{"n":0,"name":"서영학"}"
		
	} //end of 정지 누나
	
	// ============= 공통(정지 등록, 해제, 휴면 해제) 끝 ============= //


	
	// ============= 광고 관리 시작 ============= //
	
	// 광고관리 목록 페이지 요청
	@RequestMapping(value="/admin/adManage_List.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_adManageList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	    List<AdVO> adList = null;
	    
	    String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String division = request.getParameter("division");
		
		// System.out.println(searchType);
		// System.out.println(searchWord);
		
		String str_currentShowPageNo = request.getParameter("currentShowPageNo");
		
		if(division == null) {
			division = "";
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
		 paraMap.put("division", division);

		 
		 // 먼저 총 게시물 건수(totalCount)를 구해와야 한다.
		 // 총 게시물 건수(totalCount)는 검색조건이 있을 때와 없을때로 나뉘어진다.
		 int totalCount = 0;           // 총 게시물 건수
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int currentShowPageNo = 0;    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		 int totalPage = 0;            // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
			
		 int startRno = 0; // 시작 행번호
		 int endRno = 0;   // 끝 행번호
		 
		 // 총 게시물 건수(totalCount)
		 totalCount = service_ad.getTotalCount_ad(paraMap);
		 request.setAttribute("totalCount", totalCount);
		 // System.out.println(totalCount);
		 // 108
		 
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

		 adList = service_ad.adList(paraMap);
		
		 if( !"".equals(searchType) && !"".equals(searchWord) ) {
			 mav.addObject("paraMap", paraMap);
		 }
		 
			// === #121. 페이지바 만들기 === //
			int blockSize = 10;
			// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수이다.
			/*
				              1  2  3  4  5  6  7  8  9 10 [다음][마지막]  -- 1개블럭
				[맨처음][이전]  11 12 13 14 15 16 17 18 19 20 [다음][마지막]  -- 1개블럭
				[맨처음][이전]  21 22 23
			*/
			
			int loop = 1;
			int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

			String pageBar = "<ul class=\"my pagination pagination-md justify-content-center mt-5\">";
			String url = "adManage_List.do";

			// === [맨처음][이전] 만들기 === //
			if(pageNo != 1) {
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?division="+division+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'><i class=\"fa-solid fa-angles-left\"></i></a></li>";
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?division="+division+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'><i class=\"fa-solid fa-angles-left\"></i></a></li>"; 
			}		
			
			while( !(loop > blockSize || pageNo > totalPage) ) {
				
				if(pageNo == currentShowPageNo) {
					pageBar += "<li class=\"page-item active\" aria-current=\"page\">"+pageNo+"</li>";  
				}
				else {
					pageBar += "<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href='"+url+"?division="+division+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";        
				}
				loop++;
				pageNo++;
			}// end of while--------------------------
			
			// === [다음][마지막] 만들기 === //
			if( pageNo <= totalPage) {
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?division="+division+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'><i class=\"fa-solid fa-angle-right\"></i></a></li>";
				pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?division="+division+"&searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'><i class=\"fas fa-solid fa-angles-right\"></i></a></li>";
			}
			
			pageBar += "</ul>";
			
			mav.addObject("pageBar", pageBar);
			mav.addObject("adList", adList);

			
		mav.setViewName("admin/adManage_List.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberManage_List.jsp 파일을 생성한다.
		return mav;
	} // end of 학원회원 리스트 보기

	
	// 광고 관련 정보 상세보기 
	@RequestMapping(value="/admin/adDetail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_adDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		Map<String, String> paraMap = new HashMap<>();
		String advertisement_num = request.getParameter("advertisement_num");
		paraMap.put("advertisement_num", advertisement_num);
		
		AdVO adDetail = service_ad.getAdDetail(paraMap);
		
		mav.addObject("adDetail", adDetail);
		mav.setViewName("admin/adDetail.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	}// 광고 관련 정보 상세보기 끝
	
	
	// 광고 등록 페이지
	@RequestMapping(value="/admin/adRegister.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_adRegister(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		
		Map<String, String> paraMap = new HashMap<>();
				
	    mav.setViewName("admin/adRegister.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	} // end of adRegister


	// 회원 정지 등록 완료 페이지
	@RequestMapping(value="/admin/adRegisterResult.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_adRegisterResult(HttpServletRequest request, HttpServletResponse response, ModelAndView mav, AdVO advo) {
		
		Map<String,String> paraMap = new HashMap<>();
		
		// tbl_penalty에 해당 회원 정지 insert
		int n = service_ad.addAd(advo);
				
		if(n == 1 ) {			
				mav.setViewName("redirect:/admin/adManage_List.do");
		}
		
		
		
		return mav;
	}
	
	
	
	
	
}
