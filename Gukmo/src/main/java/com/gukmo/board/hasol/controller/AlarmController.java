package com.gukmo.board.hasol.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hasol.service.InterAlarmService;
import com.gukmo.board.model.AlarmVO;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.MemberVO;

@Controller
public class AlarmController {

	@Autowired // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAlarmService service;

	// 모든 알람 개수
	@ResponseBody
	@RequestMapping(value="/getAlarmList.do", method= {RequestMethod.GET})
	public String getAlarmList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, String> paraMap) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "redirect:/index.do";
		}
		
		MemberVO user= (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
		
		int totalCnt = 0; // 총 게시물 건 수
		int sizePerpage = 10; // 한 페이지 당 보여줄 게시글 건 수
		int currentPageNo = 0; // 현재 보고 있는 페이지 번호
		int totalPage = 0; // 총 페이지 수(== 페이징 바)
		
		int startRno = 0; // 시작 행 번호
		int endRno = 0; // 끝 행 번호
		
		String str_currentPageNo = paraMap.get("currentPageNo");
		
	    JSONObject jsonObj = new JSONObject();
		
		try {			
			// 총 게시물 건 수(totalCnt)
			totalCnt = service.getTotalAlarmCnt(nickname);
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

					
						
						// List<BoardVO> searchList = service.getSearchList(paraMap);
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
									   "	<a class='page-link' href='" +url+ "?currentPageNo=1' aria-label='super_previous'>" +
									   "		<i class='fa-solid fa-angles-left'></i>"+
									   "	</a>" + 
									   "</li>";
							// 이전
							pageBar += "<li class='page-item'>" +
									   "	<a class='page-link' href='" +url+ "?currentPageNo=" +(blockStart-1)+ "' aria-label='previous'>" +
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
										   "	<a class='page-link' href='" +url+ "?currentPageNo=" +blockStart+ "'>" +blockStart+ "</a>"+
										   "</li>";
							}
							
							loop++;
							blockStart++;
						}
						
						// 다음과 마지막 만들기		
						if(blockStart <= totalPage) {
							pageBar += "<li class='page-item'>" +
									   "	<a class='page-link' href='" +url+ "?currentPageNo=" +blockStart+ "'aria-label='next'>" +
									   "		<i class='fa-solid fa-angle-right'></i>"+
									   "	</a>" + 
									   "</li>";
							
							pageBar += "<li class='page-item'>" +
									   "	<a class='page-link' href='" +url+ "?currentPageNo=" +totalPage+ "' aria-label='super-next'>" +
									   "		<i class='fa-solid fa-angles-right'></i>"+
									   "	</a>" + 
									   "</li>";
						}

						pageBar +="</ul>";
						

						jsonObj.put("pageBar", pageBar);

					} catch (NullPointerException e) {
						String message = "조회된 검색 결과가 없습니다.";
						jsonObj.put("message", message);
					}		
	    


		return jsonObj.toString();
	}
	
	// 읽지 않은 알람 카운트
	@ResponseBody
	@RequestMapping(value="/getNotReadAlarm_count.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String getNotReadAlarm_count(HttpServletRequest request) {
		
		System.out.println(" 흠");
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
		
		int notReadAlarmCnt = service.getNotReadAlarm_count(nickname);
		System.out.println(notReadAlarmCnt);
		
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("notReadAlarmCnt" ,  notReadAlarmCnt);
		return jsonObj.toString();
	}
	
	
	// 읽지 않은 알람 리스트
	@ResponseBody
	@RequestMapping(value="/getNotReadAlarmList.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String getNotAlarmList(HttpServletRequest request) {
	
		System.out.println("히히");
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
			
	    // 읽지 않은 알림 데이터 전체 가져오기
	    List<AlarmVO> notReadAlarmList = service.getNotReadAlarmList(nickname);
	    // System.out.println(notReadAlarmList);
	    
	    JSONObject jsonObj = new JSONObject();	    
	    jsonObj.put("notReadAlarmList" ,  notReadAlarmList);
		return jsonObj.toString();
	}
	

	// 읽음으로 변경 
	@ResponseBody
	@RequestMapping(value="/changeIsRead.do", method= {RequestMethod.POST})
	public boolean changeIsread(HttpServletRequest request, @RequestParam Map<String,String> paraMap) {
		System.out.println("paraMap:" + paraMap);
		
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
		
		paraMap.put("alarm_nickname", nickname);
		
		// 읽음 컬럼값 변경하기
		int result = service.changeIsRead(paraMap);
		return result>0?true:false;
	}
}
