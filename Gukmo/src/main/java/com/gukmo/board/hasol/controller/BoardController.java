package com.gukmo.board.hasol.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hasol.service.InterBoardService;
import com.gukmo.board.model.AdVO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.HashtagVO;

@Controller
public class BoardController {
	
	@Autowired
	private InterBoardService service;
	
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/open_banner.do", method = {RequestMethod.GET},
	 * produces="text/plain;charset=UTF-8") public String
	 * open_banner(HttpServletRequest request, AdVO advo) {
	 * 
	 * List<AdVO> mainBannerList = service.getMainBannerList(advo);
	 * 
	 * JSONArray jsonArr = new JSONArray();
	 * 
	 * if(mainBannerList != null) { for(AdVO advo : mainBannerList) { JSONObject
	 * jsonobj = new JSONObject(); jsonobj.put("ad_num",
	 * advo.getAdvertisement_num()); jsonobj.put("url", advo.getUrl());
	 * jsonobj.put("period", advo.getPeriod());
	 * 
	 * } }
	 * 
	 * return mav; }
	 */
	
	@RequestMapping(value="/main_search.do")
	public ModelAndView main_search(Map<String,String> paraMap, ModelAndView mav, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.setAttribute("readCountPermission", "yes");
		
		// 주간 해시태그 순위를 불러오는 메소드
		List<HashtagVO> topHashList = service.getTopHashList();
		mav.addObject("topHashList", topHashList);
		
		
		String searchWord = request.getParameter("searchWord");
		String hashtag = request.getParameter("hashtag");

		// System.out.println("searchWord="+searchWord);
		String str_currentPageNo = request.getParameter("currentPageNo");
				
				
		if(hashtag == null || "".equals(hashtag) || hashtag.trim().isEmpty()) {
			hashtag = "";
		}
		
		if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		// System.out.println("hashtag:" +hashtag);
		
		
		paraMap = new HashMap<>();
		paraMap.put("searchWord", searchWord);
		paraMap.put("hashtag", hashtag);

		// System.out.println("잘 넘어가나 보자!!!!:" + searchWord);
		
		int totalCnt = 0; // 총 게시물 건 수
		int sizePerpage = 10; // 한 페이지 당 보여줄 게시글 건 수
		int currentPageNo = 0; // 현재 보고 있는 페이지 번호
		int totalPage = 0; // 총 페이지 수(== 페이징 바)
		
		int startRno = 0; // 시작 행 번호
		int endRno = 0; // 끝 행 번호
		
		
		try {			
			// 총 게시물 건 수(totalCnt)
			totalCnt = service.getTotalCnt(paraMap);
			//System.out.println("totalCnt:" + totalCnt);
						
			
			// 총 페이지 수(== 페이징 바)
			totalPage= (int)Math.ceil(((double)totalCnt/sizePerpage));		
			//System.out.println("totalPage:" + totalPage);
			
			// 현재 보고 있는 페이지 1로 초기화
			if(str_currentPageNo == null) { currentPageNo = 1; } // 초기화면
			else {
				
				try {
					currentPageNo = Integer.parseInt(str_currentPageNo);
					if(currentPageNo < 1 || currentPageNo > totalPage) {
						currentPageNo = 1;
					}
				} catch (NumberFormatException e) {
					currentPageNo = 1;
				}
			}
			
			startRno = ((currentPageNo - 1 ) * sizePerpage ) + 1 ;
			endRno = startRno + sizePerpage - 1;
			
			paraMap.put("startRno", String.valueOf(startRno));
			paraMap.put("endRno", String.valueOf(endRno));

		
			
			List<BoardVO> searchList = service.getSearchList(paraMap);
			// System.out.println("searchList:" + searchList.toString() );		

			// 페이지 바 만들기
			// 페이지 바 블럭 수
			int blockSize = 10;
			
			// 페이지 바 블럭 loop 반복
			int loop = 1;
			
			// 페이지 바 블럭 번호 시작 값
			int blockStart = ((currentPageNo - 1) / blockSize ) * blockSize + 1;
			
			String pageBar = "<ul class='pagination'>";
			String url = "main_search.do";
			
			// 처음과 이전 바 만들기
			if(blockStart != 1) {
				// 처음
				pageBar += "<li class='page-item'>" +
						   "	<a class='page-link' href='" +url+ "?searchWord=" +searchWord+ "&currentPageNo=1' aria-label='super_previous'>" +
						   "		<i class='fa-solid fa-angles-left'></i>"+
						   "	</a>" + 
						   "</li>";
				// 이전
				pageBar += "<li class='page-item'>" +
						   "	<a class='page-link' href='" +url+ "?searchWord=" +searchWord+ "&currentPageNo=" +(blockStart-1)+ "' aria-label='previous'>" +
						   "		<i class='fa-solid fa-angle-left'></i>"+
						   "	</a>" + 
						   "</li>";
			}
			
			while (!(loop>blockSize || blockStart > totalPage)) {
				
				if(blockStart == currentPageNo) {
					// 현재 보는 페이지와 블록 시작 페이지가 같을 경우 (선택된 경우)
					pageBar += "<li class='page-item disabled'>" +
							   "	<a class='page-link'>" +blockStart+ "</a>"+
							   "</li>";
				}
				else {
					// 현재 보는 페이지와 블록 시작 페이지가 다른경우(선택되지 않은 경우)
					pageBar += "<li class='page-item'>" +
							   "	<a class='page-link' href='" +url+ "?searchWord=" +searchWord+ "&currentPageNo=" +blockStart+ "'>" +blockStart+ "</a>"+
							   "</li>";
				}
				
				loop++;
				blockStart++;
				
				// 없어
				// blockstart - 1 / current - 1 / [1] / loop-2 / blockstart-2
				// blockstart - 2 / current - 1 / [1] [2(링크)] / loop-3 / blockstart-3
				// blockstart - 3 / current - 1 / [1] [2(링크)] / loop-4 / blockstart-4
				// blockstart - 4 / current - 1 / [1] [2(링크)] / loop-5 / blockstart-5
				// blockstart - 5 / current - 1 / [1] [2(링크)] / loop-6 / blockstart-6
				// blockstart - 6 / current - 1 / [1] [2(링크)] / loop-7 / blockstart-7
				// blockstart - 7 / current - 1 / [1] [2(링크)] / loop-8 / blockstart-8
				// blockstart - 8 / current - 1 / [1] [2(링크)] / loop-9 / blockstart-9
				// blockstart - 9 / current - 1 / [1] [2(링크)] / loop-10 / blockstart-10
				// 다음 마지막 생겨
			
				// 11~
				// 처음 이전 생겨
				// blockstart - 11 / current - 11 / [1] [2(링크)] / loop-2 / blockstart-12
				// blockstart - 12 / current - 12 / [1] [2(링크)] / loop-3 / blockstart-13
				// blockstart - 13 / current - 13 / [1] [2(링크)] / loop-4 / blockstart-14
				// blockstart - 14 / current - 14 / [1] [2(링크)] / loop-5 / blockstart-15
				
				// 14 
				// blockstart- 1 / current -3 / [1(링크)] / loop-2 / blockstart-2
				// blockStart - 2 / currnet -3 / [2(링크)] / loop-3 / blockstart-3
				
			}
			
			// 다음과 마지막 만들기		
			if(blockStart <= totalPage) {
				pageBar += "<li class='page-item'>" +
						   "	<a class='page-link' href='" +url+ "?searchWord=" +searchWord+ "&currentPageNo=" +blockStart+ "'aria-label='next'>" +
						   "		<i class='fa-solid fa-angle-right'></i>"+
						   "	</a>" + 
						   "</li>";
				
				pageBar += "<li class='page-item'>" +
						   "	<a class='page-link' href='" +url+ "?searchWord=" +searchWord+ "&currentPageNo=" +totalPage+ "' aria-label='super-next'>" +
						   "		<i class='fa-solid fa-angles-right'></i>"+
						   "	</a>" + 
						   "</li>";
			}

			pageBar +="</ul>";
	
			mav.addObject("pageBar", pageBar);
			mav.addObject("searchList", searchList);
			
			//System.out.println(searchList.toString());
			
		} catch (NullPointerException e) {
			String message = "조회된 검색 결과가 없습니다.";
			mav.addObject("message", message);
		}		
			
		// 검색어 유지용
		if(!"".equals(hashtag)) {
			searchWord = hashtag;
			paraMap.put("searchWord", searchWord);
		}
		if(!"".equals(searchWord)) { mav.addObject("paraMap", paraMap); }
		
		mav.addObject("totalCnt", totalCnt);
		mav.setViewName("board/main_search/searchPage.tiles1");
		
		return mav;
	}
	
	
}
