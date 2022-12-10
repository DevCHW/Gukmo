package com.gukmo.board.hgb.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.hgb.repository.InterBoardDAO;
import com.gukmo.board.hgb.service.InterBoardService;
import com.gukmo.board.hw.repository.InterLoginDAO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CommentVO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.common.FileManager;

@Controller
public class BoardController {

	@Autowired // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;
	
	@Autowired
	InterBoardDAO dao;

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
		String board_num = request.getParameter("boardNum");
		int boardNum = 0;
		try {
			boardNum = Integer.parseInt(board_num); 
		} catch (Exception e) {
			String message = "존재하지않는 글번호입니다.";
			String loc = request.getContextPath()+"/index.do";
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			return "msg";
		}//end of try-catch--
		
		String like = null;	  
		String comment_like = null;	  	
		
		Map<String, String> paraMap = new HashMap<>();
		
		HttpSession session = request.getSession();
	
		
//		int board_num = 3;// 글번호(해시태그 있는 글번호 임시 설정)
		BoardVO board= service.getBoardDetail(boardNum);	//하나의 글 불러오기	
				
		
		MemberVO user = (MemberVO)session.getAttribute("user");	
		
		if(user != null) {
			String userid = user.getUserid();		
		
		System.out.println("로그인아이디확인" + userid);			
		
		if(userid != null) { // 로그인 중이라면						
			
				
			
			paraMap.put("userid", userid);	
			paraMap.put("board_num", board_num);								
										
					
			
			int likethis = service.likethis(paraMap);
			
			
			
			System.out.println("확인용 n " + likethis);						
			
			if (likethis == 1) {				
				 like = "1";				
			}	
			
		}
			
			
			String login_nickname = user.getNickname();
			String writer_nickname = board.getNickname();
			System.out.println("login_nickname => " + login_nickname);
			System.out.println("writer_nickname => " + writer_nickname);
			
			if("yes".equals(session.getAttribute("readCountPermission"))) { // 게시글 목록을 통해 상세보기 페이지에 진입한경우
			
				if(login_nickname == null || !login_nickname.equals(writer_nickname)) {
					
					dao.setAddReadCount(board.getBoard_num()); // 조회수 1 증가하기
					
					session.removeAttribute("readCountPermission"); // session 에 저장된 readCountPermission 을 삭제한다.
				}
			
			}
	     		
		}  		
		
		
		request.setAttribute("board", board);
		request.setAttribute("like", like);		
			
		
		return "board/community/boardDetail.tiles1";  	
		
	}
	
	
	
	
	// 댓글 작성 이벤트
		@ResponseBody
		@RequestMapping(value="/addComment.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
		public String addComment(HttpServletRequest request) {
			int n = 0;
			String cmt_board_num = request.getParameter("cmt_board_num");
			String nickname = request.getParameter("nickname");
			String content = request.getParameter("content");
			String parent_write_nickname = request.getParameter("parent_write_nickname");
					
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("cmt_board_num", cmt_board_num);
			paraMap.put("nickname", nickname);
			paraMap.put("content", content);
			paraMap.put("parent_write_nickname", parent_write_nickname);

			System.out.println(paraMap);
			
			try {
				// tbl_comment 테이블에 추가, tbl_board 의 comment_cnt +1, 해당 회원의 포인트 10점 증가
				n = service.addComment(paraMap);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			JSONObject jsonObj = new JSONObject();
			if(n == 1) {
				jsonObj.put("n", n);
				return jsonObj.toString();
			}
			else {
				jsonObj.put("n", n);
				return jsonObj.toString();
			}
		}//end of addComment
		
		
		// 대댓글 작성 ajax
		@ResponseBody
		@RequestMapping(value="/addCommentOfComment.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
		public String addComment_of_Comment(HttpServletRequest request, @RequestParam Map<String, String> paraMap) {

			// System.out.println(paraMap);
			int n = 0;
			
			try {
				// tbl_comment 테이블에 추가, tbl_board 의 comment_cnt +1, 해당 회원의 포인트 10점 증가
				n = service.addComment_of_Comment(paraMap);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			JSONObject jsonObj = new JSONObject();
			if(n == 1) {
				jsonObj.put("n", n);
				return jsonObj.toString();
			}
			else {
				jsonObj.put("n", n);
				return jsonObj.toString();
			}
		}//end of 대댓글 작성
		
		
		
		
		// === 댓글 삭제 + 그 대댓글도 삭제 === //
		@ResponseBody
		@RequestMapping(value="/commentDelete.do", method=RequestMethod.POST)
		public String commentDelete(@RequestParam Map<String,String> paraMap) {				
			int n = 0;
			
			try {
				// 댓글 삭제 및 그 대댓도 삭제
				n = service.commentDelete(paraMap);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("n", n);
			
			return jsonObj.toString();
		}

		
		
		// === 댓글 수정 === //
		@ResponseBody
		@RequestMapping(value="/commentEdit.do", method=RequestMethod.POST)
		public String commentEdit(@RequestParam Map<String,String> paraMap) {				
			int n = 0;
			
			try {
				// 해당 댓글 내용 수정
				n = service.commentEdit(paraMap);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("n", n);
			
			return jsonObj.toString();
		}	
	
	

	// === 글 좋아요 === //
	@ResponseBody
	@RequestMapping(value="/likeProcess.do",method=RequestMethod.POST)
	public String likeProcess(@RequestParam Map<String,String> paraMap,HttpServletRequest request, HttpServletResponse response) {				
		//확인용 board_num,userid
//		System.out.println(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		
		if("".equals(paraMap.get("userid")) || paraMap.get("userid") == null) {	//로그인을 안했다면
			jsonObj.put("JavaData", "login");
		} else {	//로그인중이라면
			String likeResult = service.likeProcess(paraMap); //좋아요 처리하기
			jsonObj.put("JavaData", likeResult);
		}
		
		return jsonObj.toString();
	}	
	
	// === 댓글 좋아요 === //
		@ResponseBody
		@RequestMapping(value="/comment_likeProcess.do",method=RequestMethod.POST)
		public String comment_likeProcess(@RequestParam Map<String,String> paraMap,HttpServletRequest request, HttpServletResponse response) {				
			//확인용 board_num,userid
//			System.out.println(paraMap);
			
			JSONObject jsonObj = new JSONObject();
			
			if("".equals(paraMap.get("userid")) || paraMap.get("userid") == null) {	//로그인을 안했다면
				jsonObj.put("JavaData", "login");
			} else {	//로그인중이라면
				String comment_likeResult = service.comment_likeProcess(paraMap); //좋아요 처리하기
				jsonObj.put("JavaData", comment_likeResult);
			}
			
			return jsonObj.toString();
		}

}
