package com.gukmo.board.sm.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;
import com.gukmo.board.model.ReportVO;
import com.gukmo.board.sm.service.InterAdManageService;
import com.gukmo.board.sm.service.InterMemberManageService;
import com.gukmo.board.sm.service.InterReportManageService;
import com.gukmo.board.common.MyUtil;

@Controller
public class memberManageController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterMemberManageService service;

	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdManageService service_ad;

	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterReportManageService service_report;

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
		 String url = "memberManage_List.do";
		 
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

		// 특정 글 본 뒤에 뒤로가기 클릭시 검색조건 유지한 페이지로 이동하려고.
		String gobackURL = com.gukmo.board.common.MyUtil.getCurrentURL(request);
		// System.out.println(gobackURL);
		
		mav.addObject("gobackURL", gobackURL.replaceAll("&", " "));

		mav.addObject("pageBar", pageBar);
		mav.addObject("memberList", memberList);

		request.setAttribute("paraMap", paraMap);
		request.setAttribute("totalCount", totalCount);			
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
		
	    String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String memberStatus = request.getParameter("division");				

		String str_page = request.getParameter("page");
		
		if(memberStatus == null) {
			memberStatus = "";	
		}
		
		 if(searchType == null || (!"detail_category".equals(searchType) && !"subject".equals(searchType) ) ) {
			searchType = "";
		 }
		
		 if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
		 	searchWord = "";
		 }
		
		 paraMap.put("searchType", searchType);
		 paraMap.put("searchWord", searchWord);
		 paraMap.put("memberStatus", memberStatus);
		
		 // 활동 내역 총 페이지수 알아오기
		 int totalCount = service.getTotalActCount(paraMap);           // 총 게시물 건수
		 int sizePerPage = 5;         // 한 페이지당 보여줄 게시물 건수 
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		
		 paraMap = getRno(page,sizePerPage,paraMap);
		 String url = "memberDetail.do";
		
		 List<ActivityVO> detailActList = service.getDetailActList(paraMap);
		 
		Map<String,String> pageMap = new HashMap<>();
		pageMap.put("searchWord",searchWord);
		pageMap.put("searchType",searchType);
		pageMap.put("memberStatus",memberStatus);
		pageMap.put("keyWord", "division");
		pageMap.put("userid", userid);
		 
		String pageBar = getDetailPageBar(page,totalPage,url,pageMap);
		
		 if( !"".equals(searchType) && !"".equals(searchWord) ) {
			 mav.addObject("paraMap", paraMap);
		 }
	
		
		MemberVO memberDetail = service.getMemberDetail(paraMap);	
		// List<ActivityVO> actList = service.getActList(paraMap);
		
		request.setAttribute("paraMap", paraMap);
		request.setAttribute("totalCount", totalCount);			

		// mav.addObject("actList", actList);
		mav.addObject("pageBar", pageBar);
		mav.addObject("actList", detailActList);
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
				
		String str_page = request.getParameter("page");
		
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

		 
		 int totalCount = service.getTotalCount_academy(paraMap);           // 총 게시물 건수
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );            // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.

		 
		 // 총 게시물 건수(totalCount)
		 // System.out.println(totalCount);
		 // 108
		 paraMap = getRno(page,sizePerPage,paraMap);
		 String url = "academyManage_List.do";

		 academymemberList = service.academymemberList(paraMap);
		
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
		mav.addObject("academymemberList", academymemberList);

			
		request.setAttribute("paraMap", paraMap);
		request.setAttribute("totalCount", totalCount);
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
		List<ActivityVO> actList = service.getActList(paraMap);
		
		mav.addObject("actList", actList);
		
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
		String memberStatus = request.getParameter("division");
		
		// System.out.println(searchType);
		// System.out.println(searchWord);
		
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
		 int totalCount = service_ad.getTotalCount_ad(paraMap);
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
			
		 paraMap = getRno(page,sizePerPage,paraMap);
		 String url = "adManage_List.do";
		 
		 adList = service_ad.adList(paraMap);
		 
		 
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

		request.setAttribute("paraMap", paraMap);
		request.setAttribute("totalCount", totalCount);
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


	// 광고 등록 완료 페이지
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
	
	//// ================ 광고 관련 일단 끝 =================== //
	
	
	// =============== 신고내역 관리 시작 ====================//
	
	@RequestMapping(value="/admin/reportManage_List.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
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
		 int totalCount = service_report.getTotalCount_report(paraMap);
		 int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		 int totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		 int page = getPage(str_page,totalPage);    // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
			
		 paraMap = getRno(page,sizePerPage,paraMap);
		 String url = "reportManage_List.do";
		 
		 reportList = service_report.reportList(paraMap);
		 
		 
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
		mav.setViewName("admin/report_List.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberManage_List.jsp 파일을 생성한다.
		return mav;
	} // end of 학원회원 리스트 보기	
	
	
	
	// 신고 관련 정보 상세보기 
	@RequestMapping(value="/admin/reportDetail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임.
	public ModelAndView requiredAdminLogin_reportDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		Map<String, String> paraMap = new HashMap<>();
		String report_num = request.getParameter("report_num");
		paraMap.put("report_num", report_num);
		
		ReportVO reportDetail = service_report.getreportDetail(paraMap);
		
		String reportedNickname = reportDetail.getReported_nickname();
		
		String reportedId = service_report.getReportedId(reportedNickname);

		request.setAttribute("reportedId", reportedId);		
		mav.addObject("reportDetail", reportDetail);
		
		mav.setViewName("admin/reportDetail.tiles1");
	      //   /WEB-INF/views/tiles1/admin/memberDetail.jsp 파일을 생성한다.
		return mav;
	}// 광고 관련 정보 상세보기 끝
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ================페이지 관련 메소드 ============== //
	
	
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
	   
	   
	   // 상세보기에서 활동 내역에 관한 페이지바 만들기 
	   private String getDetailPageBar(int page, int totalPage, String url, Map<String,String> pageMap) {
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
		                  "  <a class='page-link' href='"+url+"?userid="+pageMap.get("userid")+"&"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page=1'>" + 
		                  "    <i class='fa-solid fa-angles-left'></i>" + 
		                  "  </a>" + 
		                  "</li>";
		         //[<]
		         pageBar += "<li class='page-item'>" + 
		                  "  <a class='page-link' href='"+url+"?userid="+pageMap.get("userid")+"&"+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+(pageNo-1)+"'>" + 
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
		                     "  <a class='page-link' href='"+url+"?userid="+pageMap.get("userid")+"&"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+pageNo+"'>"+pageNo+"</a>" + 
		                     "</li>";        
		         }
		         
		         loop++;
		         pageNo++;
		      }// end of while--------------------------
		      
		      // === [>][>>] 만들기 === //
		      if( pageNo <= totalPage) {
		         //[>]
		         pageBar += "<li class='page-item'>" + 
		                  "  <a class='page-link' href='"+url+"?userid="+pageMap.get("userid")+"&"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+pageNo+"'>"+
		                  "    <i class='fa-solid fa-angle-right'></i>"+
		                  "  </a>" + 
		                  "</li>";
		         
		         //[>>] 
		         pageBar += "<li class='page-item'>" + 
		                  "  <a class='page-link' href='"+url+"?userid="+pageMap.get("userid")+"&"+pageMap.get("keyWord")+"="+pageMap.get("memberStatus")+"&searchType="+pageMap.get("searchType")+"&searchWord="+pageMap.get("searchWord")+"&page="+totalPage+"'>"+
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
