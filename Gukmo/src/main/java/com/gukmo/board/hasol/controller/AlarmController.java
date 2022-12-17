package com.gukmo.board.hasol.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.gukmo.board.model.MemberVO;

@Controller
public class AlarmController {

	@Autowired // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAlarmService service;

	// 모든 알람 개수
	@ResponseBody
	@RequestMapping(value="/showAlarmCnt.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String showAlarmCnt(HttpServletRequest request) {
	
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
		
		int result = service.showAlarmCnt(nickname);
		
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("alarmCnt" ,  result);

	    return jsonObj.toString();
	}
	
	
	// 모든 알람 개수
	@RequestMapping(value="/getAlarmList.do")
	public ModelAndView getAlarmList(ModelAndView mav, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO user= (MemberVO)session.getAttribute("user");
		String nickname = user.getNickname();
		
	    //1.해당 유저 알림데이터 전체 가져오기
	    List<AlarmVO> getAlarm = service.getAlarm(nickname);
	    //for(InformDTO informDTO :getInform) {
	        //System.out.println("getInform : "+informDTO.toString());  
	    //}
	    
	    return mav;
	}
	
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
