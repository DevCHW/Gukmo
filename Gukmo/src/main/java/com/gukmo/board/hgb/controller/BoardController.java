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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		String str_board_num = request.getParameter("boardNum");
		int boardNum = 0;
		try {
			boardNum = Integer.parseInt(str_board_num); 
		} catch (NullPointerException e) {
			return "redirect:index.do";
		}//end of try-catch--
		
		
		/*
		
		로그인 안했으면 아무것도안함
		
		로그인 했으면 좋아요 눌렀는지 체크
		
		*/
		
		
//		int board_num = 3;// 글번호(해시태그 있는 글번호 임시 설정)
		BoardVO board= service.getBoardDetail(boardNum);	//하나의 글 불러오기
		
		
		//확인용 board
		System.out.println(board);
		
		
		request.setAttribute("board", board);
		
		
		
		
		return "board/community/boardDetail.tiles1";  		
	}
	
	
	
	
	
	// === 글삭제 하기 === //
	@RequestMapping(value="/del_board.do")
	public ModelAndView delEnd(ModelAndView mav, HttpServletRequest request) {
		
		String board_num = request.getParameter("board_num");
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("board_num", board_num);				
		
		int n = service.del(paraMap);
		
		if(n==0) {
			mav.addObject("message", "글 삭제 실패!!");
			mav.addObject("loc", "javascript:history.back()");
		}
		else {
			mav.addObject("message", "글 삭제 성공!!");
			mav.addObject("loc", request.getContextPath()+"/index.do");
		}
		
		mav.setViewName("msg");
		
		return mav;
	}
	
	
	/*
	HttpSession session = request.getSession
			MemberVO user = (MemberVO)session.getAttribuet("user")
			
			if user 가 널 이면
			url
			인덱스로 꺼져라
			
			
			else {	//
				String userid = user.getUserid()
				int boardNum = request.getParameter  글번호
				
				
			}
	*/
	
	
	
	
	// === 좋아요 체크하기 === //
	@ResponseBody
	@RequestMapping(value="/board/likeCheck.do")
	public String likeCheck(HttpServletRequest request) {
		
		
		
		HttpSession session = request.getSession();
		
		String userid = (String) session.getAttribute("userid");
		String boardNum = request.getParameter("boardNum");
		
		boolean login = false;
		
		JSONObject jsonObj = new JSONObject();
		
		if(userid == null || userid.trim().isEmpty()) {	//로그인중인 사람이 없다면 로그인페이지로 리다이렉트
			login = false;
			
			
			
			jsonObj.put("login", login);     
			String json = jsonObj.toString(); 
			
			request.setAttribute("json", json);
			
			
		}
		
		else {	//로그인중인 사용자가 있다면
			login = true;
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("userid", userid);
			paraMap.put("boardNum", boardNum);
			
			List<String> like_exist = service.like_exist(paraMap);
			
			
			
			
			
			if(like_exist != null) {
				
				
				
				
				
				jsonObj.put("like_exist", like_exist);     
				jsonObj.put("login", login); 
				String json = jsonObj.toString(); 
				
				request.setAttribute("json", json);
				
				
			}
			
			
			
		}
		
		
		return jsonObj.toString();
	}

}
