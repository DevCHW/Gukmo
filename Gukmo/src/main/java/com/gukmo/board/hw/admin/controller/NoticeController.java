package com.gukmo.board.hw.admin.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gukmo.board.common.MyUtil;
import com.gukmo.board.hw.admin.service.InterNoticeService;
import com.gukmo.board.model.BoardVO;
import com.gukmo.board.model.MemberVO;

@Controller
public class NoticeController {
	@Autowired
	private InterNoticeService service;
	
	
	/**
	 * 관리자 공지사항작성페이지 매핑
	 */
	@RequestMapping(value="/admin/notice/new.do", method= {RequestMethod.GET})
	public String viewNoticeNew(HttpServletRequest request) {
		return "admin/notice/new.tiles2";
	}
	
	
	
	/**
	 * 관리자 공지사항작성완료 매핑
	 */
	@RequestMapping(value="/admin/notice/newEnd.do", method= {RequestMethod.POST})
	public String noticeNewEnd(MultipartHttpServletRequest mrequest,@RequestParam Map<String,Object> paraMap) {
		HttpSession session = mrequest.getSession();
		paraMap.put("content",MyUtil.secureCode((String)paraMap.get("content")));
		List<String> hashTags = Arrays.asList(String.valueOf(paraMap.get("str_hashTag")).split(","));
		paraMap.put("hashTags",hashTags);
		MemberVO user = (MemberVO)session.getAttribute("user");
		paraMap.put("userid",user.getUserid());
		
		boolean result = service.writeNotice(paraMap);
		
		String loc = mrequest.getContextPath()+"/admin/index.do";
		if(result) {
			mrequest.setAttribute("message", "공지사항 등록 성공!");
		}else {
			mrequest.setAttribute("message", "공지사항 등록에 실패하였습니다. 개발팀에 문의해주세요");
		}
		mrequest.setAttribute("loc", loc);
		return "msg";
	}
	
	/**
	 * 관리자 수정페이지 매핑
	 */
	@RequestMapping(value="/admin/notice/edit.do", method= {RequestMethod.GET})
	public String editNotice(@RequestParam String boardNum,HttpServletRequest request) {
		BoardVO notice = service.getNoticeOne(boardNum);
		request.setAttribute("notice", notice);
		return "admin/notice/new.tiles2";
	}
	
	
	/**
	 * 관리자 공지사항수정완료 매핑
	 */
	@RequestMapping(value="/admin/notice/editEnd.do", method= {RequestMethod.POST})
	public String editEndNotice(MultipartHttpServletRequest mrequest,@RequestParam Map<String,Object> paraMap) {
		paraMap.put("content",MyUtil.secureCode((String)paraMap.get("content")));
		List<String> hashTags = Arrays.asList(String.valueOf(paraMap.get("str_hashTag")).split(","));
		paraMap.put("hashTags",hashTags);
		
		boolean result = service.editNotice(paraMap);
		
		String loc = "";
		if(result) {
			mrequest.setAttribute("message", "공지사항이 성공적으로 수정되었습니다");
			loc = mrequest.getContextPath()+"/detail.do?boardNum="+paraMap.get("board_num");
		}else {
			mrequest.setAttribute("message", "공지사항 수정에 실패하였습니다. 개발팀에 문의해주세요");
			loc = mrequest.getContextPath()+"/admin/index.do";
		}
		mrequest.setAttribute("loc", loc);
		return "msg";
	}
	
	
	
	
	
	
	

}
