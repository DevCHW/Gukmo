package com.gukmo.board.hgb.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
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
	
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterBoardService service;
		
	// ===  파일업로드 및 다운로드를 해주는 FileManager 클래스 의존객체 주입하기(DI : Dependency Injection) ===   
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private FileManager fileManager;
	
	
	/**
	 * 글 상세보기 페이지 GET요청 매핑
	 
	@RequestMapping(value="/detail.do", method= {RequestMethod.GET})  // 오로지 GET 방식만 허락하는 것임. 
	public String viewDetail(HttpServletRequest request) {				           
      
      return "board/community/boardDetail.tiles1";
	}
	
	*/
	
	
	// === 글1개를 보여주는 페이지 요청 === //
	@RequestMapping(value="/detail.do")
	public ModelAndView detail(ModelAndView mav, HttpServletRequest request) {
		
		getCurrentURL(request); // 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출 
		
		// 조회하고자 하는 글번호 받아오기
		String board_num = request.getParameter("board_num");
		
		// 글목록에서 검색되어진 글내용일 경우 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글과 다음글이 나오도록 하기 위한 것이다. 시작  // 		
		String searchWord = request.getParameter("searchWord");
	
	/*	
		System.out.println("~~~ view.action 에서 확인용 searchType : " + searchType);
		System.out.println("~~~ view.action 에서 확인용 searchWord : " + searchWord);
	*/			
		
		if(searchWord == null) {
			searchWord = "";
		}
		// 글목록에서 검색되어진 글내용일 경우 이전글제목, 다음글제목은 검색되어진 결과물내의 이전글과 다음글이 나오도록 하기 위한 것이다. 끝 // 
		
		
		// 페이징 처리되어진 후 특정 글제목을 클릭하여 상세내용을 본 이후
		// 사용자가 "검색된결과목록보기" 버튼을 클릭했을 때 돌아갈 페이지를 알려주기 위해
		// 현재 페이지 주소를 뷰단으로 넘겨준다.
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
			Integer.parseInt(board_num);
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("board_num", board_num);						
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
		
		mav.setViewName("board/community/boardDetail.tiles1");
		
		return mav;
	}
	
	
	@RequestMapping(value="/detail_2.action")
	public ModelAndView detail_2(ModelAndView mav, HttpServletRequest request) {
		
		getCurrentURL(request); // 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 호출 
		
		// 조회하고자 하는 글번호 받아오기
		String board_num = request.getParameter("board_num");
		
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
		
		mav.setViewName("redirect:/detail.do?board_num="+board_num+"&searchWord="+searchWord+"&gobackURL="+gobackURL);
		
		return mav;
	}
	
	
	
	
	
	
	
	
	
	    
		
		
		
		
		
		
		
		// ==== 스마트에디터. 드래그앤드롭을 사용한 다중사진 파일업로드 ====
		@RequestMapping(value="/image/multiplePhotoUpload.action", method={RequestMethod.POST})
		public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
			    
			/*
			   1. 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
			   >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
			        우리는 WAS 의 webapp/resources/photo_upload 라는 폴더로 지정해준다.
			 */
				
			// WAS 의 webapp 의 절대경로를 알아와야 한다. 
			HttpSession session = request.getSession();
			String root = session.getServletContext().getRealPath("/"); 
			String path = root + "resources"+File.separator+"photo_upload";
			// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
				
			// System.out.println(">>>> 확인용 path ==> " + path); 
			// >>>> 확인용 path ==> C:\NCS\workspace(spring)\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\photo_upload  
				
			File dir = new File(path);
			if(!dir.exists()) {
			    dir.mkdirs();
			}    
						
			try {
				  String filename = request.getHeader("file-name"); //파일명을 받는다 - 일반 원본파일명
				  // 네이버 스마트에디터를 사용한 파일업로드시 싱글파일업로드와는 다르게 멀티파일업로드는 파일명이 header 속에 담겨져 넘어오게 되어있다. 
					
					/*
					    [참고]
					    HttpServletRequest의 getHeader() 메소드를 통해 클라이언트 사용자의 정보를 알아올 수 있다. 
			
						request.getHeader("referer");           // 접속 경로(이전 URL)
						request.getHeader("user-agent");        // 클라이언트 사용자의 시스템 정보
						request.getHeader("User-Agent");        // 클라이언트 브라우저 정보 
						request.getHeader("X-Forwarded-For");   // 클라이언트 ip 주소 
						request.getHeader("host");              // Host 네임  예: 로컬 환경일 경우 ==> localhost:9090    
					*/	
				  
			        // System.out.println(">>>> 확인용 filename ==> " + filename); 
			        // >>>> 확인용 filename ==> berkelekle%ED%8A%B8%EB%9E%9C%EB%94%9405.jpg
			    		
				       InputStream is = request.getInputStream(); // is는 네이버 스마트 에디터를 사용하여 사진첨부하기 된 이미지 파일임.
			    	   
			    	   String newFilename = fileManager.doFileUpload(is, filename, path);
			    	
				       int width = fileManager.getImageWidth(path+File.separator+newFilename);
					
				       if(width > 600) {
				          width = 600;
				       }  
						
				     // System.out.println(">>>> 확인용 width ==> " + width);
				     // >>>> 확인용 width ==> 600
				     // >>>> 확인용 width ==> 121
				       
				        String ctxPath = request.getContextPath(); //  /board
						
						String strURL = "";
						strURL += "&bNewLine=true&sFileName="+newFilename; 
						strURL += "&sWidth="+width;
						strURL += "&sFileURL="+ctxPath+"/resources/photo_upload/"+newFilename;
						
						// === 웹브라우저 상에 사진 이미지를 쓰기 === //
						PrintWriter out = response.getWriter();
						out.print(strURL);
				   
			} catch(Exception e){
					e.printStackTrace();
			}
		   
		}
		
		
		
		
		
		// === 로그인 또는 로그아웃을 했을 때 현재 보이던 그 페이지로 그대로 돌아가기 위한 메소드 생성 ===
		public void getCurrentURL(HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.setAttribute("goBackURL", MyUtil.getCurrentURL(request));
		}
	
}
