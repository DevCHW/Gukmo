package com.gukmo.board.hw.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.admin.service.InterIndexService;
import com.gukmo.board.model.BoardVO;

@Controller
public class IndexController {
	@Autowired
	private InterIndexService service;
	
	
	/**
	 * 관리자메인페이지 매핑
	 */
	@RequestMapping(value="/admin/index.do", method= {RequestMethod.GET})
	public String helloAdmin(HttpServletRequest request) {
		//인기 게시물 3개 가져오기
		List<BoardVO> popularBoardList = service.getPopularBoard();
		//사이트 요약정보 얻기(오늘 회원가입수, 오늘 방문자 수, 오늘 작성 게시물 수)
		Map<String,Integer> summaryMap = service.getSummary();
		
		int cntBoard = Integer.parseInt(String.valueOf(summaryMap.get("CNT_BOARD")));
		int cntJoinMember = Integer.parseInt(String.valueOf(summaryMap.get("CNT_JOIN_MEMBER")));
		int cntVisit = Integer.parseInt(String.valueOf(summaryMap.get("CNT_VISIT")));
		
		float maxCntBoard = Float.parseFloat(String.valueOf(summaryMap.get("MAX_CNT_BOARD")));
		float maxCntJoinMember = Float.parseFloat(String.valueOf(summaryMap.get("MAX_CNT_JOIN_MEMBER")));
		float maxCntVisit = Float.parseFloat(String.valueOf(summaryMap.get("MAX_CNT_VISIT")));
		
		//게시글 작성이 가장 많았던 날의 게시글 수 대비 오늘 게시글 작성 수 퍼센트
		int board_percentage = (int) ((cntBoard/maxCntBoard)*100);
		//회원가입이 제일 많았던 날의 회원가입 수 대비 오늘 회원가입 수 퍼센트
		int member_percentage = (int) ((cntJoinMember/maxCntJoinMember)*100);
		//접속 수가 가장 많았던 날의 접속 수 대비 오늘 접속 수 퍼센트
		int visit_percentage = (int) ((cntVisit/maxCntVisit)*100);
		
		if(board_percentage > 100) {
			board_percentage = 100;
		}
		if(member_percentage > 100) {
			member_percentage = 100;
		}
		if(visit_percentage > 100) {
			visit_percentage = 100;
		}
		
		request.setAttribute("board_percentage",board_percentage);
		request.setAttribute("member_percentage",member_percentage);
		request.setAttribute("visit_percentage",visit_percentage);
		request.setAttribute("summaryMap", summaryMap);						//사이트 요약정보
		request.setAttribute("popularBoardList", popularBoardList);			//인기게시물 3개
		return "admin/index.tiles2";
	}
	
	
	
	/**
	 * 월별 방문자 수 구하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/getVisitCountMonthlyData.do", method= {RequestMethod.GET})
	public List<Integer> getVisitCountMonthlyData(HttpServletRequest request) {
		//월별 방문자 수 구하기
		return service.getVisitCountMonthlyData();
	}
	
	
	/**
	 * 오늘자 커뮤니티활성 데이터 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/getCommunityActiveData.do", method= {RequestMethod.GET})
	public List<Integer> getCommunityActiveData(HttpServletRequest request) {
		List<Integer> data = service.getCommunityActiveData();
		//오늘자 커뮤니티활성 데이터 얻기
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
}
