package com.gukmo.board.hw.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.admin.service.InterAdvertisementService;
import com.gukmo.board.model.DataTableDTO;

@Controller
public class AdvertisementController {
	
	@Autowired
	private InterAdvertisementService service;
	
	/**
	 * 광고내역페이지 매핑
	 */
	@RequestMapping(value="/admin/advertisement/list.do", method= {RequestMethod.GET})
	public String viewAdvertisementList(HttpServletRequest request) {
		return "admin/advertisement/list.tiles2";
	}
	
	
	/**
	 *  광고내역페이지 데이터 넘겨주기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/advertisement/listSelect.do", method= {RequestMethod.POST})  // 오로지 GET 방식만 허락하는 것임.
	public DataTableDTO AdvertisementListSelect(DataTableDTO dto,@RequestBody MultiValueMap<String, String> formData) {
		//0->광고번호
		//1->구분
		//2->고객명
		//3->고객연락처
		//4->기간
		//5->상태
		int draw = Integer.parseInt(formData.get("draw").get(0));
	    int start = Integer.parseInt(formData.get("start").get(0));
	    int length = Integer.parseInt(formData.get("length").get(0));
	    
	    String startRno = start+1+"";
	    String endRno = start+length+"";
	    String advertisement_num = formData.get("columns[0][search][value]").get(0);
	    String division = formData.get("columns[1][search][value]").get(0);
	    String client_name = formData.get("columns[2][search][value]").get(0);
	    String client_phone = formData.get("columns[3][search][value]").get(0);
	    String period = formData.get("columns[4][search][value]").get(0);
	    String status = formData.get("columns[5][search][value]").get(0);
	    String sort = formData.get("order[0][column]").get(0);
	    String direction = formData.get("order[0][dir]").get(0);
	    String searchType = "";
	    String searchWord = "";
	    
	    if(advertisement_num != null && !advertisement_num.trim().isEmpty()) {
	    	searchType = "advertisement_num";
	    	searchWord = advertisement_num;
	    }
	    if(client_name != null && !client_name.trim().isEmpty()) {
	    	searchType = "client_name";
	    	searchWord = client_name;
	    }
	    if(client_phone != null && !client_phone.trim().isEmpty()) {
	    	searchType = "client_phone";
	    	searchWord = client_phone;
	    }
	    if(status != null && !status.trim().isEmpty()) {
	    	status = "진행중".equals(status)?"1":"0";
	    }
	    
	    switch (sort) {
			case "0":
				sort = "advertisement_num";
				break;
			case "1":
				sort = "division";
				break;
			case "2":
				sort = "client_name";
				break;
			case "3":
				sort = "client_phone";
				break;
			case "4":
				sort = "period";
				break;
			case "5":
				sort = "status";
				break;
			default:
				sort = "start_date";
				break;
		}
	    String start_date = "";
	    String end_date ="";
	    if(period != null && !period.trim().isEmpty()) {
	    	int commaIdx = period.indexOf(",");
	    	start_date = period.substring(0,commaIdx);
	    	end_date = period.substring(commaIdx+1);
	    }
	    
//	    //확인용
//	    System.out.println("====================================\n");
//	    System.out.println(formData);
//	    System.out.println("searchType : " + searchType);
//	    System.out.println("searchWord : " + searchWord);
//	    System.out.println("start_date : " + start_date);
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
	    paraMap.put("division",division);
	    paraMap.put("searchType",searchType);
	    paraMap.put("searchWord",searchWord);
	    paraMap.put("startRno",startRno);
	    paraMap.put("endRno",endRno);
	    paraMap.put("sort",sort);
	    paraMap.put("direction",direction);
	    
	    int total = (int)service.getTotalCntAdvertisement(paraMap);
		List<Map<String,String>> data = service.getAdvertisementList(paraMap);
	    
	    dto.setDraw(draw);
	    dto.setRecordsFiltered(total);
	    dto.setRecordsTotal(total);
	    dto.setData(data);
		
//	         확인용
//	    System.out.println(dto);
	    return dto;
	}
	
	/**
	 * 광고삭제하기
	 */
	@ResponseBody
	@RequestMapping(value="/admin/advertisement/delete.do", method= {RequestMethod.POST})
	public boolean deleteAdvertisement(@RequestParam String advertisement_num) {
		return service.deleteAdvertisement(advertisement_num);
	}
	
	
	
	
}
