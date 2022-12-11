package com.gukmo.board.hw.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hw.admin.service.InterReportService;
import com.gukmo.board.model.ReportVO;


@Controller
public class ReportController {
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterReportService service;
	
	
	
	/**
	 * 신고접수처리하기(Board신고)
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/receiptBoard.do", method= {RequestMethod.POST})
	public String receiptBoard(@RequestParam String report_num, HttpServletRequest request) {
		
		boolean result = service.receiptReportBoard(report_num);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	/**
	 * 신고접수처리하기(Comment신고)
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/receiptComment.do", method= {RequestMethod.POST})
	public String receiptComment(@RequestParam String report_num, HttpServletRequest request) {
		
		boolean result = service.receiptReportComment(report_num);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 피신고자가 이미 정지회원인지 체크하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/memberStatusCheck.do", method= {RequestMethod.POST})
	public String memberStatusCheck(@RequestParam String nickname, HttpServletRequest request) {
		
		String status = service.memberStatusCheck(nickname);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 피신고자가 정지등록하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/penaltyRegister.do", method= {RequestMethod.POST})
	public String penaltyRegister(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		paraMap.put("penalty_period",paraMap.get("penalty_period").replace("일",""));
		boolean result = service.penaltyRegister(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
