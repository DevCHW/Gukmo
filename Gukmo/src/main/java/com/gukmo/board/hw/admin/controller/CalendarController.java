package com.gukmo.board.hw.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CalendarController {
	
	/**
	 * 일정페이지 매핑(구글캘린더)
	 */
	@RequestMapping(value="/admin/calendar.do", method= {RequestMethod.GET})
	public String viewCalendar(HttpServletRequest request) {
		return "admin/calendar.tiles2";
	}
}
