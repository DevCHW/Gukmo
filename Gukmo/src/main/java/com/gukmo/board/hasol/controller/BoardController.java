package com.gukmo.board.hasol.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hasol.service.InterBoardService;
import com.gukmo.board.model.AdVO;

@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/open_banner.do", method = {RequestMethod.GET},
	 * produces="text/plain;charset=UTF-8") public String
	 * open_banner(HttpServletRequest request, AdVO advo) {
	 * 
	 * List<AdVO> mainBannerList = service.getMainBannerList(advo);
	 * 
	 * JSONArray jsonArr = new JSONArray();
	 * 
	 * if(mainBannerList != null) { for(AdVO advo : mainBannerList) { JSONObject
	 * jsonobj = new JSONObject(); jsonobj.put("ad_num",
	 * advo.getAdvertisement_num()); jsonobj.put("url", advo.getUrl());
	 * jsonobj.put("period", advo.getPeriod());
	 * 
	 * } }
	 * 
	 * return mav; }
	 */
	
	
}
