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
	@RequestMapping(value="/member/getAlarmList.do", method= {RequestMethod.GET})
	public String getAlarmList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, String> paraMap) {
		
		System.out.println(" 오냐고용? ");
		
		HttpSession session = request.getSession();		
		MemberVO user= (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
		paraMap.put("alarm_nickname", nickname);
		
		String currentPageNo = paraMap.get("currentPageNo"); // 현재 보고 있는 페이지 번호
		
		if(currentPageNo == null) {
			currentPageNo = "1";
		}
		
		int sizePerPage = 10;
		
		int startRno = (( Integer.parseInt(currentPageNo) - 1) * sizePerPage) + 1;
		int endRno = startRno + sizePerPage - 1;
		
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		
	    JSONObject jsonObj = new JSONObject();
		
	    try {
			List<AlarmVO> alarmList = service.getAlarmList(paraMap);
				// System.out.println("searchList:" + searchList.toString() );
			jsonObj.put("alarmList", alarmList);

		} catch (NullPointerException e) {
			String message = "<p> 조회된 검색 결과가 없습니다. </p>";
			jsonObj.put("message", message);
		}		

		return jsonObj.toString();
	}
	
	
	// 모든 알람 페이지 조회
	@ResponseBody
	@RequestMapping(value="/member/getAlarmCount.do", method= {RequestMethod.GET}, produces="text/plain;charset=UTF-8")
	public String getAlarmPage(HttpServletRequest request,@RequestParam Map<String, String> paraMap) {
		
		// System.out.println("paraMap:" + paraMap);
		
		HttpSession session = request.getSession();
		MemberVO user= (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
		paraMap.put("alarm_nickname", nickname);
		paraMap.put("sizePerPage", paraMap.get("sizePerPage"));
		
		int totalPage = service.getTotalAlarmPage(paraMap);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totalPage", totalPage); 
		
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
