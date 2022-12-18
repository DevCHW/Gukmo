package com.gukmo.board.hw.admin.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.admin.repository.InterAdminMemberDAO;
import com.gukmo.board.hw.admin.service.InterAdminMemberService;
import com.gukmo.board.model.DataTableDTO;
import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;



@Controller
public class AdminMemberController {
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterAdminMemberService service;
	
	@Autowired
	private InterAdminMemberDAO dao;
	
	
	/**
	 * 회원상세보기 페이지 매핑
	 */
	@RequestMapping(value="/admin/member/detail.do", method= {RequestMethod.GET})
	public String viewMemberDetail(@RequestParam String userid, HttpServletRequest request) {
		MemberVO member = service.getUser(userid);
		
		if("정지".equals(member.getStatus())) {	//만약 회원의 상태가 "정지" 라면
			PenaltyVO penalty = service.getPenalty(member.getNickname());	//정지내역 구하기
			request.setAttribute("penalty", penalty);
		}
		
		request.setAttribute("member", member);
		return "admin/member/detail.tiles2";
	}
	
	
	
	
	
	
	
	/**
	 * 회원정보수정하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/edit.do", method= {RequestMethod.POST})
	public String editMember(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		
		boolean result = service.editMember(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 정지내역 insert하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/penalty/new.do", method= {RequestMethod.POST})
	public String insertPenalty(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		paraMap.put("penalty_period",paraMap.get("penalty_period").replace("일",""));
		boolean result = service.penaltyNew(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 정지내역 delete하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/penalty/delete.do", method= {RequestMethod.POST})
	public String deletePenalty(@RequestParam String nickname, HttpServletRequest request) {
		boolean result = service.penaltyDelete(nickname);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	


	
	/**
	 * 이메일 전송하기
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/sendEmail.do", method= {RequestMethod.POST})
	public String deletePenalty(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		boolean sendMailSuccess = service.sendEmail(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sendMailSuccess", sendMailSuccess);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 교육기관회원내역페이지 매핑
	 */
	@RequestMapping(value="/admin/member/academy/list.do", method= {RequestMethod.GET})
	public String viewAcaMember(HttpServletRequest request) {
		return "admin/member/academy/list.tiles2";
	}
	
	/**
	 *  교육기관회원관리 데이터 넘겨주기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/academy/listSelect.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public DataTableDTO acaMemberList(DataTableDTO dto,@RequestBody MultiValueMap<String, String> formData) {
		//0->닉네임
		//1->아이디
		//2->학원명
		//3->가입일자
		//4->상태
		//5->홈페이지
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    String nickname = formData.get("columns[0][search][value]").get(0);
	    String userid = formData.get("columns[1][search][value]").get(0);
	    String academy_name = formData.get("columns[2][search][value]").get(0);
	    String join_date = formData.get("columns[3][search][value]").get(0);
	    String status = formData.get("columns[4][search][value]").get(0);
	    String homepage = formData.get("columns[5][search][value]").get(0);
	    String sort = formData.get("order[0][column]").get(0);
	    String direction = formData.get("order[0][dir]").get(0);
	    String searchType = "";
	    String searchWord = "";
	    
	    if(nickname != null && !nickname.trim().isEmpty()) {
	    	searchType = "nickname";
	    	searchWord = nickname;
	    }
	    if(userid != null && !userid.trim().isEmpty()) {
	    	searchType = "userid";
	    	searchWord = userid;
	    }
	    if(academy_name != null && !academy_name.trim().isEmpty()) {
	    	searchType = "academy_name";
	    	searchWord = academy_name;
	    }
	    if(homepage != null && !homepage.trim().isEmpty()) {
	    	searchType = "homepage";
	    	searchWord = homepage;
	    }
	    
	    switch (sort) {
			case "0":
				sort = "nickname";
				break;
			case "1":
				sort = "userid";
				break;
			case "2":
				sort = "academy_name";
				break;
			case "3":
				sort = "join_date";
				break;
			case "4":
				sort = "status";
				break;
			case "5":
				sort = "homepage";
				break;
			default:
				sort = "join_date";
				break;
		}
	    String start_date = "";
	    String end_date ="";
	    if(join_date != null && !join_date.trim().isEmpty()) {
	    	int commaIdx = join_date.indexOf(",");
	    	start_date = join_date.substring(0,commaIdx);
	    	end_date = join_date.substring(commaIdx+1);
	    }
	    
	    //확인용
//	    System.out.println("====================================\n");
//	    System.out.println(formData);
//	    System.out.println("searchType : " + searchType);
//	    System.out.println("searchWord : " + searchWord);
//	    System.out.println("join_date : " + join_date);
//	    System.out.println("가입일자 시작날짜 조건 : " + start_date);
//	    System.out.println("가입일자 끝일자 조건: " + end_date);
//	    System.out.println("status : " + status);
//	    System.out.println("startRno : " + startRno);
//	    System.out.println("endRno : " + endRno);
//	    System.out.println("정렬컬럼 : " + sort);
//	    System.out.println("정렬방향 : " + direction);
//	    System.out.println("====================================\n");
	    
	    Map<String,String> paraMap = new HashMap<>();
	    
	    paraMap.put("start_date",start_date);
	    paraMap.put("end_date",end_date);
	    paraMap.put("status",status);
	    paraMap.put("searchType",searchType);
	    paraMap.put("searchWord",searchWord);
	    paraMap.put("startRno",startRno);
	    paraMap.put("endRno",endRno);
	    paraMap.put("sort",sort);
	    paraMap.put("direction",direction);
	    
	    int total = (int)service.getTotalCntAcaMember(paraMap);
		List<Map<String,String>> data = service.getAcaMemberList(paraMap);
	    
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		
//	         확인용
//	    System.out.println(dto);
	    return dto;
	}
	
	
	/**
	 * 일반회원내역페이지 매핑
	 */
	@RequestMapping(value="/admin/member/normal/list.do", method= {RequestMethod.GET})
	public String viewNormalMember(HttpServletRequest request) {
		return "admin/member/normal/list.tiles2";
	}
	
	
	/**
	 *  일반회원관리 데이터 넘겨주기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/normal/listSelect.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public DataTableDTO MemberManageList(DataTableDTO dto,@RequestBody MultiValueMap<String, String> formData) {
		//0->닉네임
		//1->아이디
		//2->이메일
		//3->가입일자
		//4->상태
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    String nickname = formData.get("columns[0][search][value]").get(0);
	    String userid = formData.get("columns[1][search][value]").get(0);
	    String email = formData.get("columns[2][search][value]").get(0);
	    String join_date = formData.get("columns[3][search][value]").get(0);
	    String status = formData.get("columns[4][search][value]").get(0);
	    String sort = formData.get("order[0][column]").get(0);
	    String direction = formData.get("order[0][dir]").get(0);
	    String searchType = "";
	    String searchWord = "";
	    
	    if(nickname != null && !nickname.trim().isEmpty()) {
	    	searchType = "nickname";
	    	searchWord = nickname;
	    }
	    if(userid != null && !userid.trim().isEmpty()) {
	    	searchType = "userid";
	    	searchWord = userid;
	    }
	    if(email != null && !email.trim().isEmpty()) {
	    	searchType = "email";
	    	searchWord = email;
	    }
	    
	    switch (sort) {
			case "0":
				sort = "nickname";
				break;
			case "1":
				sort = "userid";
				break;
			case "2":
				sort = "email";
				break;
			case "3":
				sort = "join_date";
				break;
			case "4":
				sort = "status";
				break;
			default:
				sort = "join_date";
				break;
		}
	    String start_date = "";
	    String end_date ="";
	    if(join_date != null && !join_date.trim().isEmpty()) {
	    	int commaIdx = join_date.indexOf(",");
	    	start_date = join_date.substring(0,commaIdx);
	    	end_date = join_date.substring(commaIdx+1);
	    }
	    
	    //확인용
//	    System.out.println("====================================\n");
//	    System.out.println(formData);
//	    System.out.println("searchType : " + searchType);
//	    System.out.println("searchWord : " + searchWord);
//	    System.out.println("join_date : " + join_date);
//	    System.out.println("가입일자 시작날짜 조건 : " + start_date);
//	    System.out.println("가입일자 끝일자 조건: " + end_date);
//	    System.out.println("status : " + status);
//	    System.out.println("startRno : " + startRno);
//	    System.out.println("endRno : " + endRno);
//	    System.out.println("정렬컬럼 : " + sort);
//	    System.out.println("정렬방향 : " + direction);
//	    System.out.println("====================================\n");
	    
	    Map<String,String> paraMap = new HashMap<>();
	    
	    paraMap.put("start_date",start_date);
	    paraMap.put("end_date",end_date);
	    paraMap.put("status",status);
	    paraMap.put("searchType",searchType);
	    paraMap.put("searchWord",searchWord);
	    paraMap.put("startRno",startRno);
	    paraMap.put("endRno",endRno);
	    paraMap.put("sort",sort);
	    paraMap.put("direction",direction);
	    
	    int total = (int)service.getTotalCntNormalMember(paraMap);
		List<Map<String,String>> data = service.getNormalMemberList(paraMap);
	    
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		
//			         확인용
//			    System.out.println(dto);
	    return dto;
	}
	
	
	/**
	 * 회원의 검색어 데이터 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/getSearchData.do", method= {RequestMethod.POST})
	public DataTableDTO getSearchData(DataTableDTO dto,String userid,@RequestBody MultiValueMap<String, String> formData) {
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    //확인용
//	    System.out.println("startRno : " + startRno);
//	    System.out.println("endRno : " + endRno);
	    
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("startRno",startRno);
		paraMap.put("endRno",endRno);
		paraMap.put("userid",userid);
		int total = (int)service.getTotalSearchCnt(paraMap);
		List<Map<String, String>> data = service.getSearchData(paraMap);
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		return dto;
	}
	
	
	
	
	/**
	 * 회원이 작성한 게시물 데이터 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/getWriteBoardData.do", method= {RequestMethod.POST})
	public DataTableDTO getWriteBoardData(DataTableDTO dto,String nickname,@RequestBody MultiValueMap<String, String> formData) {
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    //확인용
//		    System.out.println("startRno : " + startRno);
//		    System.out.println("endRno : " + endRno);
	    
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("startRno",startRno);
		paraMap.put("endRno",endRno);
		paraMap.put("nickname",nickname);
		int total = (int)dao.getTotalWriteBoard(paraMap);
		List<Map<String, String>> data = dao.getWriteBoardData(paraMap);
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		return dto;
	}
	
	
	
	/**
	 * 회원 로그인 기록 데이터 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/getLoginRecordData.do", method= {RequestMethod.POST})
	public DataTableDTO getLoginRecordData(DataTableDTO dto,String userid,@RequestBody MultiValueMap<String, String> formData) {
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    //확인용
//		    System.out.println("startRno : " + startRno);
//		    System.out.println("endRno : " + endRno);
	    
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("startRno",startRno);
		paraMap.put("endRno",endRno);
		paraMap.put("userid",userid);
		int total = (int)dao.getTotalLoginRecord(paraMap);
		List<Map<String, String>> data = dao.getLoginRecordData(paraMap);
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		return dto;
	}
	
	
	
	/**
	 * 회원이 신고당한 내역 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/getReportData.do", method= {RequestMethod.POST})
	public DataTableDTO getReportData(DataTableDTO dto,String nickname,@RequestBody MultiValueMap<String, String> formData) {
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    //확인용
//		    System.out.println("startRno : " + startRno);
//		    System.out.println("endRno : " + endRno);
	    
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("startRno",startRno);
		paraMap.put("endRno",endRno);
		paraMap.put("nickname",nickname);
		int total = (int)dao.getTotalReportData(paraMap);
		List<Map<String, String>> data = dao.getReportData(paraMap);
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		return dto;
	}
	
	
	/**
	 * 회원 로그인 기록 데이터 얻기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/member/getReportedData.do", method= {RequestMethod.POST})
	public DataTableDTO getReportedData(DataTableDTO dto,String nickname,@RequestBody MultiValueMap<String, String> formData) {
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    //확인용
//		    System.out.println("startRno : " + startRno);
//		    System.out.println("endRno : " + endRno);
	    
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("startRno",startRno);
		paraMap.put("endRno",endRno);
		paraMap.put("nickname",nickname);
		int total = (int)dao.getTotalReportedData(paraMap);
		List<Map<String, String>> data = dao.getReportedData(paraMap);
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		return dto;
	}
	
	
	
	
	
	
	
	
	
	
		
		

		

 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
