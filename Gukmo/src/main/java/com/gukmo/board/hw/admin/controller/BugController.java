package com.gukmo.board.hw.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.admin.repository.InterBugDAO;

@Controller
public class BugController {
	@Autowired
	private InterBugDAO dao;
	
	
	/**
	 * 버그문의 페이지 매핑
	 */
	@RequestMapping(value="/admin/bug/list.do", method= {RequestMethod.GET})
	public String viewBugList(HttpServletRequest request) {
		List<Map<String,String>> bugList = dao.getBugList();
		request.setAttribute("bugList", bugList);
		return "admin/bug/list.tiles2";
	}
	
	
	/**
	 * 버그상세 페이지 매핑
	 */
	@RequestMapping(value="/admin/bug/detail.do", method= {RequestMethod.GET})
	public String viewBugDetail(HttpServletRequest request,@RequestParam String bugNum) {
		Map<String,String> bugMap = dao.getBug(bugNum);
		request.setAttribute("bugMap", bugMap);
		return "admin/bug/detail.tiles2";
	}
	
	/**
	 * 버그제보삭제(1개)
	 */
	@ResponseBody
	@RequestMapping(value="/admin/bug/delete.do", method= {RequestMethod.POST})
	public boolean delete(HttpServletRequest request,@RequestParam String bugNum) {
		return dao.delete(bugNum);
	}
	
	
	
}
