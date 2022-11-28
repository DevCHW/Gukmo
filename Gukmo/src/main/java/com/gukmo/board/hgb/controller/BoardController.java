package com.gukmo.board.hgb.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.hgb.service.InterBoardService;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.common.FileManager;

@Controller
public class BoardController {

	@Autowired // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;

	// === 파일업로드 및 다운로드를 해주는 FileManager 클래스 의존객체 주입하기(DI : Dependency Injection)
	// ===
	@Autowired // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;	

	/**
	 * 글 상세보기 페이지 보여주기
	 */
	@RequestMapping(value="/detail.do")
	public String viewBoardDetail(HttpServletRequest request) {
//		나중에 주석 풀기
//		String str_board_num = request.getParameter("num");
//		int board_num = 0;
//		try {
//			board_num = Integer.parseInt(str_board_num); 
//		} catch (NullPointerException e) {
//			return "redirect:index.do";
//		}//end of try-catch--
		
		
		
		
		
		int board_num = 3;// 글번호(해시태그 있는 글번호 임시 설정)
		BoardVO board= service.getBoardDetail(board_num);	//하나의 글 불러오기
		
		System.out.println(board);
		request.setAttribute("board", board);
		request.setAttribute("boardvoMap", board);
		
		
		
		return "board/community/boardDetail.tiles1";  		
	}
	
	 

}
