package com.gukmo.board.sun.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.sun.service.InterAdminService;

@Controller
public class AdminController {

	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdminService service;
	
	
	
	
	/**
	 * 관리자 통계 페이지 매핑
	 */
	@RequestMapping(value="/admin/chart/board.do")
	//public String requiredAdminLogin_viewChart(@RequestParam String userid, HttpServletRequest request) {
	public String viewChart(HttpServletRequest request, HttpServletResponse response) {
		return "admin/chart/chart.tiles1";
	}
	
	
	/**
	 * 관리자 게시물 통계
	 */
	@ResponseBody
	@RequestMapping(value="/admin/chart/newBoardCnt.do", produces="text/plain;charset=UTF-8")
	//public String requiredAdminLogin_newBoardCnt(@RequestParam String userid, HttpServletRequest request) {
	public String newBoardCnt(HttpServletRequest request, HttpServletResponse response) {
		
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("fromDate", fromDate);
		paraMap.put("toDate", toDate);
		
		List<Map<String, String>> newBoardCnt_List = service.newBoardCnt(paraMap);
		
		JSONArray jsonArr = new JSONArray();  // []
		
		if(newBoardCnt_List != null) {
			for(Map<String, String> map : newBoardCnt_List) {
				JSONObject jsonObj = new JSONObject(); // {}
				jsonObj.put("write_date", map.get("write_date")); 
				jsonObj.put("cnt", map.get("cnt"));
				
				jsonArr.put(jsonObj);
			}// end of for---------------------------
		}
				
		return jsonArr.toString();
	}
	
	
	
	/**
	 * 관리자 게시물 통계
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/detail/activityList.do", produces="text/plain;charset=UTF-8")
	//public String requiredAdminLogin_newMemberCnt(@RequestParam String userid, HttpServletRequest request) {
	public String activityList(@RequestParam Map<String, String> paraMap, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(paraMap.get("userid"));
		
		List<Map<String, String>> activityList = service.activityList(paraMap);
		
		JSONArray jsonArr = new JSONArray();  // []
		
		if(activityList != null) {
			for(Map<String, String> map : activityList) {
				JSONObject jsonObj = new JSONObject(); // {}
				jsonObj.put("fk_board_num", map.get("fk_board_num")); 
				jsonObj.put("activity_date", map.get("activity_date"));
				jsonObj.put("subject", map.get("subject"));
				jsonObj.put("detail_category", map.get("detail_category"));
				jsonObj.put("division", map.get("division"));
				
				jsonArr.put(jsonObj);
			}// end of for---------------------------
		}
		
		return jsonArr.toString();
	}
	
	
	
	/**
	 * 관리자 회원 활동내역 통계
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/detail/activityCntList.do", produces="text/plain;charset=UTF-8")
	//public String requiredAdminLogin_newMemberCnt(@RequestParam String userid, HttpServletRequest request) {
	public String activityCntList(@RequestParam Map<String, String> paraMap, HttpServletRequest request, HttpServletResponse response) {
		
		String sort = paraMap.get("sort");
		
		List<Map<String, String>> activityCntList = null;
		
		if(sort.equals("일자별")) {
			activityCntList = service.activityCntList(paraMap);
		}
		else {
			activityCntList = service.activityCntListYearMonth(paraMap);
		}
		
		JSONArray jsonArr = new JSONArray();  // []
		
		if(activityCntList != null) {
			for(Map<String, String> map : activityCntList) {
				JSONObject jsonObj = new JSONObject(); // {}
				jsonObj.put("activity_date", map.get("activity_date")); 
				jsonObj.put("cnt", map.get("cnt"));
				
				jsonArr.put(jsonObj);
			}// end of for---------------------------
		}
		
		return jsonArr.toString();
	}
	
	
		/**
		 * 관리자 회원 검색어 통계
		 */
		@ResponseBody
		@RequestMapping(value="/admin/member/detail/searchCntList.do", produces="text/plain;charset=UTF-8")
		//public String requiredAdminLogin_newMemberCnt(@RequestParam String userid, HttpServletRequest request) {
		public String searchCntList(@RequestParam String userid, HttpServletRequest request, HttpServletResponse response) {
			
			List<Map<String, String>> searchCntList = service.searchCntList(userid);
			
			JSONArray jsonArr = new JSONArray();  // []
			
			if(searchCntList != null) {
				for(Map<String, String> map : searchCntList) {
					JSONObject jsonObj = new JSONObject(); // {}
					jsonObj.put("key", map.get("key")); 
					jsonObj.put("cnt", map.get("cnt"));
					
					jsonArr.put(jsonObj);
				}// end of for---------------------------
			}
			
			return jsonArr.toString();
		}
}
