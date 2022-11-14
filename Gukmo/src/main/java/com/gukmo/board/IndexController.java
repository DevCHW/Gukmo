package com.gukmo.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value="/index.do")
	public String hello_gukmo(HttpServletRequest request) {
		return "index";
	}
}
