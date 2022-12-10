package com.gukmo.board.sm.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

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
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.sm.repository.InterBoardDAO;
import com.gukmo.board.sm.service.InterBoardService;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.CommentVO;
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

	@Autowired
	InterBoardDAO dao;
	
	@RequestMapping(value="/detail.do", method= {RequestMethod.GET})
	   public String viewBoardDetail(HttpServletRequest request) {
			// 나중에 주석 풀기
		String board_num = request.getParameter("boardNum");
						
			Map<String,String> paraMap = new HashMap<>();			
			paraMap.put("board_num", board_num);
			
			String like = null;     
			String comment_like = null;           
			
		    HttpSession session = request.getSession();
		    MemberVO user = (MemberVO)session.getAttribute("user");   
		     
			// 디테일 카테고리 가져오기
	      	String detail_category = service.getCategory(paraMap);	      
			paraMap.put("detail_category",detail_category);
		    	
		    
			//하나의 boardvo 불러오기
			BoardVO board = service.getBoardDetail(paraMap);   
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
		              
		      }//end of ooouter if        
		      
		       
			
			//기본 댓글 리스트 불러오기(기본 : 그냥 댓글, 특수 : 대댓글)
			List<CommentVO> basic_commentList = service.getBasic_commentList(paraMap); 
			List<CommentVO> special_commentList = service.getSpecial_commentList(paraMap); 
			
			

	      request.setAttribute("basic_commentList", basic_commentList);
	      request.setAttribute("special_commentList", special_commentList);	      
	      request.setAttribute("board", board);
	      request.setAttribute("like", like);
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
	
	// 댓글 작성 이벤트
	@ResponseBody
	@RequestMapping(value="/addComment.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String addComment(HttpServletRequest request) {
		int n = 0;
		String cmt_board_num = request.getParameter("cmt_board_num");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		String parent_write_nickname = request.getParameter("parent_write_nickname");
		String subject = request.getParameter("subject");
		String detail_category = request.getParameter("detail_category");

		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		String userid = user.getUserid();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("userid", userid);
		paraMap.put("cmt_board_num", cmt_board_num);
		paraMap.put("nickname", nickname);
		paraMap.put("content", content);
		paraMap.put("parent_write_nickname", parent_write_nickname);
		paraMap.put("subject", subject);
		paraMap.put("detail_category", detail_category);
		
		try {
			// tbl_comment 테이블에 추가, tbl_board 의 comment_cnt +1, 해당 회원의 포인트 10점 증가, 활동내역에 등록
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
		
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		String userid = user.getUserid();
		paraMap.put("userid", userid);


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

	
/*	@ResponseBody
	@RequestMapping(value="/commentList.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String commentList(HttpServletRequest request, @RequestParam Map<String, String> paraMap) {
		List<CommentVO> commentList = service.getCommentList(paraMap);
		
	}
*/	
	

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
//	      System.out.println(paraMap);
	      
	      JSONObject jsonObj = new JSONObject();
	      
	      if("".equals(paraMap.get("userid")) || paraMap.get("userid") == null) {   //로그인을 안했다면
	         jsonObj.put("JavaData", "login");
	      } else {   //로그인중이라면
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
//	         System.out.println(paraMap);
	         
	         JSONObject jsonObj = new JSONObject();
	         
	         if("".equals(paraMap.get("userid")) || paraMap.get("userid") == null) {   //로그인을 안했다면
	            jsonObj.put("JavaData", "login");
	         } else {   //로그인중이라면
	            String comment_likeResult = service.comment_likeProcess(paraMap); //좋아요 처리하기
	            jsonObj.put("JavaData", comment_likeResult);
	         }
	         
	         return jsonObj.toString();
	      }
	      

}
