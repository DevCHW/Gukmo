package com.gukmo.board.hw.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.hw.admin.service.InterReportService;
import com.gukmo.board.model.DataTableDTO;
import com.gukmo.board.model.ReportVO;


@Controller
public class ReportController {
	@Autowired   // Type 에 따라 알아서 Bean 을 주입해준다.
	private InterReportService service;
	
	
	
	/**
	 * 신고접수처리하기(Board신고)
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/receiptBoard.do", method= {RequestMethod.POST})
	public String receiptBoard(@RequestParam String report_num, HttpServletRequest request) {
		
		boolean result = service.receiptReportBoard(report_num);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	/**
	 * 신고접수처리하기(Comment신고)
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/receiptComment.do", method= {RequestMethod.POST})
	public String receiptComment(@RequestParam String report_num, HttpServletRequest request) {
		
		boolean result = service.receiptReportComment(report_num);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 피신고자가 이미 정지회원인지 체크하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/memberStatusCheck.do", method= {RequestMethod.POST})
	public String memberStatusCheck(@RequestParam String nickname, HttpServletRequest request) {
		
		String status = service.memberStatusCheck(nickname);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 피신고자가 정지등록하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/penaltyRegister.do", method= {RequestMethod.POST})
	public String penaltyRegister(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		paraMap.put("penalty_period",paraMap.get("penalty_period").replace("일",""));
		boolean result = service.penaltyRegister(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	
	
	/**
	 * 신고내역페이지 매핑
	 */
	@RequestMapping(value="/admin/report/list.do", method= {RequestMethod.GET})
	public String viewAdvertisementList(HttpServletRequest request) {
		return "admin/report/list.tiles2";
	}
	
	
	/**
	 *  신고내역페이지 데이터 넘겨주기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/report/listSelect.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public DataTableDTO AdvertisementListSelect(DataTableDTO dto,@RequestBody MultiValueMap<String, String> formData) {
		//0->신고번호
		//1->신고분류
		//2->신고자 닉네임
		//3->피신고자 닉네임
		//4->사유
		//5->신고일자
		//6->접수여부
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    String report_num = formData.get("columns[0][search][value]").get(0);
	    String report_type = formData.get("columns[1][search][value]").get(0);
	    String report_nickname = formData.get("columns[2][search][value]").get(0);
	    String reported_nickname = formData.get("columns[3][search][value]").get(0);
	    String simple_report_reason = formData.get("columns[4][search][value]").get(0);
	    String report_date = formData.get("columns[5][search][value]").get(0);
	    String receipt = formData.get("columns[6][search][value]").get(0);
	    String sort = formData.get("order[0][column]").get(0);
	    String direction = formData.get("order[0][dir]").get(0);
	    String searchType = "";
	    String searchWord = "";
	    
	    if(report_num != null && !report_num.trim().isEmpty()) {
	    	searchType = "report_num";
	    	searchWord = report_num;
	    }
	    if(report_nickname != null && !report_nickname.trim().isEmpty()) {
	    	searchType = "report_nickname";
	    	searchWord = report_nickname;
	    }
	    if(reported_nickname != null && !reported_nickname.trim().isEmpty()) {
	    	searchType = "reported_nickname";
	    	searchWord = reported_nickname;
	    }
	    
	    switch (sort) {
			case "0":
				sort = "report_num";
				break;
			case "1":
				sort = "report_type";
				break;
			case "2":
				sort = "report_nickname";
				break;
			case "3":
				sort = "reported_nickname";
				break;
			case "4":
				sort = "simple_report_reason";
				break;
			case "5":
				sort = "report_date";
				break;
			case "6":
				sort = "receipt";
				break;
			default:
				sort = "report_num";
				break;
		}//end of switch-case--
	    
	    String start_date = "";
	    String end_date ="";
	    if(report_date != null && !report_date.trim().isEmpty()) {
	    	int commaIdx = report_date.indexOf(",");
	    	start_date = report_date.substring(0,commaIdx);
	    	end_date = report_date.substring(commaIdx+1);
	    }
	    
//	    //확인용
	    System.out.println("====================================\n");
	    System.out.println(formData);
	    System.out.println("searchType : " + searchType);
	    System.out.println("searchWord : " + searchWord);
	    System.out.println("start_date : " + start_date);
	    System.out.println("신고일자 시작날짜 조건 : " + start_date);
	    System.out.println("신고일자 끝일자 조건: " + end_date);
	    System.out.println("receipt : " + receipt);
	    System.out.println("startRno : " + startRno);
	    System.out.println("endRno : " + endRno);
	    System.out.println("정렬컬럼 : " + sort);
	    System.out.println("정렬방향 : " + direction);
	    System.out.println("====================================\n");
	    
	    Map<String,String> paraMap = new HashMap<>();
	    
	    paraMap.put("start_date",start_date);
	    paraMap.put("end_date",end_date);
	    paraMap.put("simple_report_reason",simple_report_reason);
	    paraMap.put("receipt",receipt);
	    paraMap.put("report_type",report_type);
	    paraMap.put("searchType",searchType);
	    paraMap.put("searchWord",searchWord);
	    paraMap.put("startRno",startRno);
	    paraMap.put("endRno",endRno);
	    paraMap.put("sort",sort);
	    paraMap.put("direction",direction);
	    
	    int total = (int)service.getTotalCntReport(paraMap);
		List<Map<String,String>> data = service.getReportList(paraMap);
	    
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		
//	         확인용
//	    System.out.println(dto);
	    return dto;
	}
	
	
	
	
	
	
	
	
}
