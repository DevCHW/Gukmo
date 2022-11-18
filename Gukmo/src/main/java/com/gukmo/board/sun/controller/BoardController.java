package com.gukmo.board.sun.controller;


import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
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

import com.gukmo.board.common.FileManager;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.sun.service.InterBoardService;


@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	
	@Autowired 
	private FileManager fileManager;
	
	
	// 게시판 글목록 보기 페이지 요청
	@RequestMapping(value="/community/freeBoards.do")
	public ModelAndView list(ModelAndView mav, HttpServletRequest request) {
		List<BoardVO> boardList = null;
		
		Map<String, String> paraMap = new HashMap<>();
		boardList = service.boardList(paraMap);
		
		mav.addObject("boardList", boardList);
		
		mav.setViewName("board/community/freeBoardList.tiles1");
		
		return mav;
	}
	
	
	// 게시판 글쓰기 페이지 요청
	@RequestMapping(value="/community/new.do" )
	// public ModelAndView requiredLogin_communityNew(HttpServletRequest request, HttpServletResponse response, ModelAndView mav){ // <== before Advice(로그인체크)
	public ModelAndView communityNew(HttpServletRequest request, HttpServletResponse response, ModelAndView mav){
		
		
		// 카테고리 값 지정용
		// String detail_category = request.getParameter("detail_category");
		
		// mav.addObject("detail_category", detail_category);
		
		mav.setViewName("board/community/communityNew.tiles1");
		
		return mav;
	}

	
   // 스마트에디터. 드래그앤드롭을 사용한 다중사진 파일업로드
   @RequestMapping(value="/image/multiplePhotoUpload.do", method= {RequestMethod.POST} )
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String root = session.getServletContext().getRealPath("/");
		String path = root + "resources"+File.separator+"photo_upload";
		
		System.out.println("~~~~ 확인용 path => " + path);
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		try {
			String filename = request.getHeader("file-name"); // 원본파일명
			
			System.out.println(">>> 확인용 filename ==> " + filename);
			
			InputStream is = request.getInputStream();
			
			String newFilename = fileManager.doFileUpload(is, filename, path);
			
			int width = fileManager.getImageWidth(path+File.separator+newFilename);
			
		    if(width > 600) {
		       width = 600;
		    }
		    
			String ctxPath = request.getContextPath();
			
			String strURL = "";
			strURL += "&bNewLine=true&sFileName="+newFilename; 
			strURL += "&sWidth="+width;
			strURL += "&sFileURL="+ctxPath+"/resources/photo_upload/"+newFilename;
			
			PrintWriter out = response.getWriter();
			out.print(strURL);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	
//	@RequestMapping(value="/newEnd.do", method= {RequestMethod.POST})
////	public ModelAndView pointPlus_communityNewEnd(Map<String, String> paraMap, ModelAndView mav, BoardVO boardvo) {  // <== After Advice(활동점수 올리기)
//	public ModelAndView communityNewEnd(ModelAndView mav, BoardVO boardvo) {  
//		int n = service.communityNew(boardvo);
//	
//	}
//	
	
	
	
	
}
