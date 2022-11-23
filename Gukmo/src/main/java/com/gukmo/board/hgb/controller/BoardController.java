package com.gukmo.board.hgb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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


@Controller
public class BoardController {
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;
	
	
	
	
	
	
	    // === 글1개를 보여주는 페이지 요청 === //
		@RequestMapping(value="/view.action")
		public ModelAndView view(ModelAndView mav, HttpServletRequest request) {
			
			getCurrentURL(request); // 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출 
			
			// 조회하고자 하는 글번호 받아오기
			String seq = request.getParameter("seq");
			
			// 글목록에서 검색되어진 글내용일 경우 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글과 다음글이 나오도록 하기 위한 것이다. 시작  // 
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");
		
		/*	
			System.out.println("~~~ view.action 에서 확인용 searchType : " + searchType);
			System.out.println("~~~ view.action 에서 확인용 searchWord : " + searchWord);
		*/	
			if(searchType == null) {
				searchType = "";
			}
			
			if(searchWord == null) {
				searchWord = "";
			}
			// 글목록에서 검색되어진 글내용일 경우 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글과 다음글이 나오도록 하기 위한 것이다. 끝 // 
			
			
			// === #125. 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
			//           사용자가 "검색된결과목록보기" 버튼을 클릭했을 때 돌아갈 페이지를 알려주기 위해
			//           현재 페이지 주소를 뷰단으로 넘겨준다.
			String gobackURL = request.getParameter("gobackURL");
		//	System.out.println("~~~~ view.action 에서 gobackURL : " + gobackURL);
		//	~~~~ view.action 에서 gobackURL : /list.action?searchType=subject searchWord=java currentShowPageNo=3
			
			if(gobackURL != null && gobackURL.contains(" ") ) {
				gobackURL = gobackURL.replaceAll(" ", "&");
			}
		//	System.out.println("~~~~ 확인용 gobackURL : " + gobackURL);
	    //	~~~~ 확인용 gobackURL : /list.action?searchType=subject&searchWord=java&currentShowPageNo=3

			mav.addObject("gobackURL", gobackURL);
		 // ============= 125번의 작업 끝  ============= //
			
			try {
				Integer.parseInt(seq);
				
				Map<String, String> paraMap = new HashMap<>();
				paraMap.put("seq", seq);
				
				paraMap.put("searchType", searchType); // view.jsp 에서 이전글제목 및 다음글제목 클릭시 사용하기 위해서 임.
				paraMap.put("searchWord", searchWord); // view.jsp 에서 이전글제목 및 다음글제목 클릭시 사용하기 위해서 임.
				mav.addObject("paraMap", paraMap);     // view.jsp 에서 이전글제목 및 다음글제목 클릭시 사용하기 위해서 임.
				
				
				HttpSession session = request.getSession();
				MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
				
				String login_userid = null;
				if(loginuser != null) {
					login_userid = loginuser.getUserid();
					// login_userid 는 로그인 되어진 사용자의 userid 이다.
				}
				paraMap.put("login_userid", login_userid);
				
				// === #68. !!! 중요 !!! 
		        //     글1개를 보여주는 페이지 요청은 select 와 함께 
				//     DML문(지금은 글조회수 증가인 update문)이 포함되어져 있다.
				//     이럴경우 웹브라우저에서 페이지 새로고침(F5)을 했을때 DML문이 실행되어
				//     매번 글조회수 증가가 발생한다.
				//     그래서 우리는 웹브라우저에서 페이지 새로고침(F5)을 했을때는
				//     단순히 select만 해주고 DML문(지금은 글조회수 증가인 update문)은 
				//     실행하지 않도록 해주어야 한다. !!! === //
				
				// 위의 글목록보기 #69. 에서 session.setAttribute("readCountPermission", "yes"); 해두었다.
				BoardVO boardvo = null;
				if("yes".equals(session.getAttribute("readCountPermission"))) {
					// 글목록보기를 클릭한 다음에 특정글을 조회해온 경우이다.
					
					boardvo = service.getView(paraMap);
					// 글조회수 증가와 함께 글1개를 조회를 해주는 것
					// (먼저, 로그인을 한 상태에서 다른 사람의 글을 조회할 경우에는 글조회수 컬럼의 값을 1증가 해야 한다)
					
					session.removeAttribute("readCountPermission");
					// 중요함!!! session 에 저장된 readCountPermission 을 삭제한다.
				}
				else {
					// 웹브라우저에서 새로고침(F5)을 클릭한 경우이다.
					
					boardvo = service.getViewWithNoAddCount(paraMap);
					// 글조회수 증가는 없고 단순히 글1개만 조회 해주는 것
				}
				
				mav.addObject("boardvo", boardvo);
				
			} catch(NumberFormatException e) {
				
			}
			
			mav.setViewName("board/view.tiles1");
			
			return mav;
		}
		
		
		@RequestMapping(value="/view_2.action")
		public ModelAndView view_2(ModelAndView mav, HttpServletRequest request) {
			
			getCurrentURL(request); // 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출 
			
			// 조회하고자 하는 글번호 받아오기
			String seq = request.getParameter("seq");
			
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");
			String gobackURL = request.getParameter("gobackURL");
			
		/*	
			System.out.println("~~~ view_2.action 에서 확인용 searchType : " + searchType);
			System.out.println("~~~ view_2.action 에서 확인용 searchWord : " + searchWord);
			System.out.println("~~~ view_2.action 에서 확인용 gobackURL : " + gobackURL);
		*/	
			//  redirect:/ 를 할때 한글데이터는 한글이 ? 처럼 깨지므로 아래와 같이 한글깨짐을 방지해주어야 한다.
			
			try {
				searchWord = URLEncoder.encode(searchWord, "UTF-8"); // 한글이 ? 처럼 안깨지고 그대로 한글을 사용하도록 해줌.
				gobackURL = URLEncoder.encode(gobackURL, "UTF-8");   // 한글이 ? 처럼 안깨지고 그대로 한글을 사용하도록 해줌.
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			
			HttpSession session = request.getSession();
			session.setAttribute("readCountPermission", "yes");
			
			mav.setViewName("redirect:/view.action?seq="+seq+"&searchType="+searchType+"&searchWord="+searchWord+"&gobackURL="+gobackURL);
			
			return mav;
		}
		
		
		
		
		
		
		
		// === 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 생성 ===
		public void getCurrentURL(HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.setAttribute("goBackURL", MyUtil.getCurrentURL(request));
		}
	
}
