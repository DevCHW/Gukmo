package com.gukmo.board.hw.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.service.InterSearchService;


@Controller
public class SearchController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterSearchService service;
	
	/**
	 * 검색어 저장시키기
	 */
	@ResponseBody
	@RequestMapping(value="/saveKeyword.do", method= {RequestMethod.GET})
	public String saveKeyword(HttpServletRequest request,@RequestParam Map<String,String> paraMap) {
		paraMap.put("search_ip",request.getRemoteAddr());
		System.out.println(paraMap);
		boolean result = service.saveKeyWord(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
}
