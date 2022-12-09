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
	@RequestMapping(value="/admin/chart/newMemberCnt.do", produces="text/plain;charset=UTF-8")
	//public String requiredAdminLogin_newMemberCnt(@RequestParam String userid, HttpServletRequest request) {
	public String newMemberCnt(HttpServletRequest request, HttpServletResponse response) {
		
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
	
	
	
	
	
}
