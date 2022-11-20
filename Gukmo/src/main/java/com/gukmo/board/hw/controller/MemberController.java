package com.gukmo.board.hw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gukmo.board.hw.repository.InterMemberDAO;
import com.gukmo.board.hw.service.InterMemberService;
import com.gukmo.board.model.ActivityVO;
import com.gukmo.board.model.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private InterMemberService service;
	
	@Autowired
	private InterMemberDAO dao;
	
	
	//============================================================================ //
	//============================= 회원가입 관련 시작 ================================== //
	//============================================================================ //
	/**
	 * 이용약관페이지 url매핑
	 */
	@RequestMapping(value="/TOS.do", method= {RequestMethod.GET})
	public String viewTOS(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "member/TOS.tiles1";
			// /WEB-INF/views/tiles1/member/TOS.jsp 페이지.
		}
		return "redirect:/index.do";
	}
	
	
	/**
	 * 회원가입페이지 url매핑
	 */
	@RequestMapping(value="/signup.do", method= {RequestMethod.GET})
	public String viewSignup(HttpServletRequest request) {
		return "member/signup.tiles1";
		// /WEB-INF/views/tiles1/member/signup.jsp 페이지.
	}
	
	
	/**
	 * 회원아이디가 존재하는지 여부 검사
	 * @return 회원아이디가 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/idExistCheck.do", method= {RequestMethod.POST})
	public String idExistCheck(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		
		boolean idExist = true;
		if("admin".equals(userid)) {
			idExist = true;
		}
		else {
			idExist = dao.idExistCheck(userid);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("idExist", idExist);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 가입된 닉네임이 존재하는지 여부 검사
	 * @return 가입된 닉네임이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/nicknameExistCheck.do", method= {RequestMethod.POST})
	public String nicknameExistCheck(HttpServletRequest request) {
		String nickname = request.getParameter("nickname");
		boolean nicknameExist = dao.nicknameExistCheck(nickname);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("nicknameExist", nicknameExist);
		
		return jsonObj.toString();
	}
	
	/**
	 * 가입된 이메일이 존재하는지 여부 검사
	 * @return 가입된 이메일이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/emailExistCheck.do", method= {RequestMethod.POST})
	public String emailExistCheck(HttpServletRequest request) {
		String email = request.getParameter("email");
		boolean emailExist = dao.emailExistCheck(email);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("emailExist", emailExist);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 회원가입 하기
	 */
	@RequestMapping(value="/member/save.do", method= {RequestMethod.POST})
	public String saveMember(MemberVO input_member,HttpServletRequest request) throws Throwable {
		if(input_member.getAcademy_name() != null) {	//교육기관회원 가입인경우
			System.out.println("교육기관회원 가입입니다.");
		}
		else {	//일반회원 가입인 경우
			service.saveNormalMember(input_member);
			
		}
		String message = "회원가입 완료!";
		String loc = request.getContextPath()+"/login.do";
		
		request.setAttribute("message", message);
		request.setAttribute("loc", loc);
		
		return "msg";
	}
	
	
	//=========================================================================== //
	//============================= 회원가입 관련 끝=================================== //
	//=========================================================================== //
	
	
	
	
	
	
	
	//============================================================================== //
	//============================= 마이페이지 관련 시작=================================== //
	//============================================================================== //
	
	/**
	 * 활동내역 페이지 GET요청시 페이지 보여주기(페이징처리)
	 */
	@RequestMapping(value="/member/activities.do", method= {RequestMethod.GET})
	public String viewActivities(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "redirect:/index.do";
		}
		
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		String userid = user.getUserid();
		
		Map<String, String> paraMap = new HashMap<>();
		String str_page = request.getParameter("page");
		String searchWord = request.getParameter("searchWord");
		paraMap.put("searchWord", searchWord);
		paraMap.put("userid",userid);
		
		
		int totalCount = 0;           // 총 게시물 건수
		int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		int page = 0;    			  // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		int totalPage = 0;            // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		int startRno = 0; 			  // 시작 행번호
		int endRno = 0;   			  // 끝 행번호
		
		// 총 게시물 건수(totalCount)
		totalCount = service.getTotalActivities(paraMap);
		
		//총 페이지 수
		totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		
		//총 페이지 수
		totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );
		
		if(str_page == null) {	//쿼리스트링에 페이지가 없다면
			 // 게시판에 보여지는 초기화면 
			 page = 1;
		 }
		 else {
			 try {
				 page = Integer.parseInt(str_page);
				 if( page < 1) {	//페이지가 1페이지보다 작은경우
					 page = 1;
				 }
				 else if(page > totalPage) { //페이지가 총페이지보다 큰 경우
					 page = totalPage;
				 }
			 } catch(NumberFormatException e) {	//페이지번호에 글자를 써서 들어올 경우 오류방지
				 page = 1;
			 }//end of try-catch
		 
		 }
		
		startRno = ((page - 1) * sizePerPage) + 1;
		endRno = startRno + sizePerPage - 1;
		 
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
		
		
		List<ActivityVO> activities = service.getActivities(userid);
		// 페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
		
		// 검색대상 컬럼과 검색어를 뷰단 페이지에서 유지시키기 위한 조건
		if(!"".equals(searchWord) ) {
			request.setAttribute("paraMap", paraMap);
		}
		
		// 페이지바 만들기 
		int blockSize = 5;
		// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수이다.
		
		int loop = 1;
		
		int pageNo = ((page - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<ul class='my pagination pagination-md justify-content-center mt-5'>";
		String url = "member/activities.do";
		
		// === [<<][<] 만들기 === //
		if(pageNo != 1) {
			//[<<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page=1'>" + 
					   "    <i class='fa-solid fa-angles-left'></i>" + 
					   "  </a>" + 
					   "</li>";
			//[<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+(pageNo-1)+"'>" + 
					   "    <i class='fa-solid fa-angle-left'></i>" + 
					   "  </a>" + 
					   "</li>"; 
		}
		
		while( !(loop > blockSize || pageNo > totalPage) ) {
			
			if(pageNo == page) {	//페이지번호가 현재페이지번호와 같다면 .active
				pageBar += "<li class='page-item active' aria-current='page'>" + 
						   "  <a class='page-link' href='#'>"+pageNo+"</a>" + 
						   "</li>";  
			}
			
			else {	//페이지번호가 현재페이지번호랑 다르다면 .active 뺌
				pageBar += "<li class='page-item'>" + 
						   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+pageNo+"'>"+pageNo+"</a>" + 
						   "</li>";        
			}
			
			loop++;
			pageNo++;
		}// end of while--------------------------
		
		// === [>][>>] 만들기 === //
		if( pageNo <= totalPage) {
			//[>]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+pageNo+"'>"+
					   "    <i class='fa-solid fa-angle-right'></i>"+
					   "  </a>" + 
					   "</li>";
			
			//[>>] 
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?searchWord="+searchWord+"&page="+totalPage+"'>"+
					   "    <i class='fas fa-solid fa-angles-right'></i>"+
					   "  </a>" + 
					   "</li>";
		}
		
		pageBar += "</ul>";
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("activities",activities);
		
		return "member/activities.tiles1";
		// /WEB-INF/views/tiles1/member/activities.jsp 페이지.
	}
	
	
	
	/**
	 * 내계정 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/member/myId.do", method= {RequestMethod.GET})
	public String viewMyId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "redirect:/index.do";
		}
		
		
		return "member/myId.tiles1";
		// /WEB-INF/views/tiles1/member/myId.tiles1.jsp 페이지.
	}
	
	
	
	/**
	 * 내정보 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/member/myInfo.do", method= {RequestMethod.GET})
	public String viewMyInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {	//로그인중인 회원이 없다면
			return "redirect:/index.do";
		}
		return "/member/myInfo.tiles1";
		// /WEB-INF/views/tiles1/member/myInfo.jsp 페이지.
	}
	
	/**
	 * 개인정보보호방침 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/policy/privacy.do", method= {RequestMethod.GET})
	public String viewPrivacyPolicy(HttpServletRequest request) {
		return "/policy/privacy_policy.tiles1";
		// /WEB-INF/views/tiles1/policy/privacy_policy.jsp 페이지.
	}
	
	/**
	 * 계정삭제하기(POST가 맞는지 GET이 맞는지 고민해볼 것.)
	 */
	@ResponseBody
	@RequestMapping(value="/member/delete.do", method= {RequestMethod.POST})
	public String memberDelete(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		String userid = user.getUserid();
		int result = service.memberDelete(userid);
		
		if(result == 1) {
			session.removeAttribute("user");
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
		// /WEB-INF/views/tiles1/policy/privacy_policy.jsp 페이지.
	}
	
	//============================================================================== //
	//============================= 마이페이지 관련 끝=================================== //
	//============================================================================== //
	
	
	
      
}
	
	
	
	
	
	
	
	

