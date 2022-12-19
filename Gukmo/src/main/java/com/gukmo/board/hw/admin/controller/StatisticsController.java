package com.gukmo.board.hw.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.admin.repository.InterStatisticsDAO;
import com.gukmo.board.hw.admin.service.InterStatisticsService;

@Controller
public class StatisticsController {
	@Autowired
	private InterStatisticsService service;
	@Autowired
	private InterStatisticsDAO dao;
	
	/**
	 * 통계 페이지 매핑
	 */
	@RequestMapping(value="/admin/statistics.do",method= {RequestMethod.GET})
	public String viewStatistics(HttpServletRequest request) {
		Map<String,Integer> dataMap = dao.getTotalData();
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("endRno","4");
		String totalVisit = dao.getTotalVisit();
		List<Map<String,String>> visitMap = dao.getVisitMap(paraMap);
		paraMap.put("endRno",totalVisit);
		List<Map<String,String>> totalVisitMap = dao.getVisitMap(paraMap);
		request.setAttribute("totalVisitMap", totalVisitMap);
		request.setAttribute("totalVisit", totalVisit);
		request.setAttribute("dataMap", dataMap);
		request.setAttribute("visitMap", visitMap);
		return "admin/statistics.tiles2";
	}
	
	/**
	 * 국모 접속브라우저 비중 데이터 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/getBrowserData.do", method= {RequestMethod.GET})
	public List<Integer> getBrowserData(HttpServletRequest request) {
		return dao.getBrowserData();
	}
	
	/**
	 * 이번달 일자별 회원가입 수
	 */
	@ResponseBody
	@RequestMapping(value="/admin/getJoinMemberData.do", method= {RequestMethod.GET})
	public List<Integer> getJoinMemberData(HttpServletRequest request) {
		return dao.getJoinMemberData();
	}
	
	
	/**
	 * 회원 비중 구하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/getMemberRateData.do", method= {RequestMethod.GET})
	public List<Integer> getMemberRateData(HttpServletRequest request) {
		return dao.getMemberRateData();
	}
	
	
	
	
	/**
	 * 
	 */
	
	
	
	
	
	
}
