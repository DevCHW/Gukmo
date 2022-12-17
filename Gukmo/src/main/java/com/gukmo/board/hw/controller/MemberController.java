package com.gukmo.board.hw.controller;

import java.io.File;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gukmo.board.common.FileManager;
import com.gukmo.board.common.RecaptchaConfig;
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
	
	@Autowired
	private FileManager fileManager;
	
	
	
	
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
	 * 교육기관회원가입페이지 url매핑
	 */
	@RequestMapping(value="/acaSignup.do", method= {RequestMethod.GET})
	public String viewAcaSignup(HttpServletRequest request) {
		return "member/acaSignup.tiles1";
		// /WEB-INF/views/tiles1/member/signup.jsp 페이지.
	}
	
	
	/**
	 * 회원아이디가 존재하는지 여부 검사
	 * @return 회원아이디가 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/idExistCheck.do", method= {RequestMethod.POST})
	public String idExistCheck(@RequestParam String userid, HttpServletRequest request) {
		boolean idExist = true;
		idExist = dao.idExistCheck(userid);
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
	public String nicknameExistCheck(@RequestParam String nickname, HttpServletRequest request) {
		
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
	public String emailExistCheck(@RequestParam String email, HttpServletRequest request) {
		boolean emailExist = dao.emailExistCheck(email);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("emailExist", emailExist);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 가입된 교육기관명이 존재하는지 여부 검사
	 * @return 가입된 교육기관명이 존재하면 true, 존재하지 않는다면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/academyNameExistCheck.do", method= {RequestMethod.POST})
	public String academyNameExistCheck(@RequestParam String academyName, HttpServletRequest request) {
		boolean academyNameExist = dao.academyNameExistCheck(academyName);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("academyNameExist", academyNameExist);
		
		return jsonObj.toString();
	}
	
	
	
	
	/**
	 * 회원가입 하기
	 */
	@RequestMapping(value="/member/save.do", method= {RequestMethod.POST})
	public String saveMember(@RequestParam Map<String,String> paraMap, MemberVO input_member,HttpServletRequest request) throws Throwable {
		//프로필사진 기본프사로 지정
		paraMap.put("profile_image","user.PNG");
		
		//확인용 회원가입 시도한 회원정보
		System.out.println("회원가입 시도한 회원정보 :" + paraMap);
		
		if(paraMap.get("academy_name") != null && !"".equals(paraMap.get("academy_name"))) {	//교육기관회원 가입인경우
			service.saveAcademyMember(paraMap);
		} else {	//일반회원 가입인 경우
			service.saveNormalMember(paraMap);
		}
		
		String message = "회원가입 완료!";
		String loc = request.getContextPath()+"/login.do";
		
		request.setAttribute("message", message);
		request.setAttribute("loc", loc);
		
		return "msg";
	}
	
	
	
	/**
	 * reCAPTCHA(로봇이아닙니다) 인증하기
	 * @return 인증 성공시 0 실패시 1 에러시 -1
	 */
	@ResponseBody
	@RequestMapping(value = "/member/verifyRecaptcha.do", method = RequestMethod.POST)
	public int VerifyRecaptcha(HttpServletRequest request) {
	    RecaptchaConfig.setSecretKey("6LdO7zkjAAAAACYEyYOQ0PquIol3BtmcbcGY9PFI");
	    String gRecaptchaResponse = request.getParameter("recaptcha");
	    try {
	       if(RecaptchaConfig.verify(gRecaptchaResponse))
	          return 0; // 성공
	       else return 1; // 실패
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1; //에러
	    }
	}
	
	
	
	/**
	 * 회원가입이메일 인증코드 전송
	 */
	@ResponseBody
	@RequestMapping(value="/sendEmailCertificationCode.do", method= {RequestMethod.POST})
	public String sendEmailCertificationCode(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		//인증코드 발송
		String jsonObj = service.sendEmailCertificationCode(email,request);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 일반회원에서 교육기관회원으로 전환하기
	 */
	@ResponseBody
	@RequestMapping(value="/member/ChangeAcaMember.do", method= {RequestMethod.POST})
	public String ChangeAcaMember(HttpServletRequest request,@RequestParam Map<String,String> paraMap) {
		//확인용
//		System.out.println("교육기관회원으로 전환 정보 : "+paraMap);
		
		boolean result = service.ChangeAcaMember(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result",result);
		return jsonObj.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//=========================================================================== //
	//============================= 회원가입 관련 끝=================================== //
	//=========================================================================== //
	
	
	
	
	
	
	
	//============================================================================== //
	//============================= 마이페이지 관련 시작=================================== //
	//============================================================================== //
	
	
	
	
	
	
	
	/**
	 * 계정찾기 페이지 GET 요청시 매핑
	 */
	@RequestMapping(value="/member/findId.do", method= {RequestMethod.GET})
	public String viewfindId(HttpServletRequest request) {
		return "member/findId.tiles1";
		// /WEB-INF/views/tiles1/member/findId.jsp 페이지.
	}
	
	
	
	/**
	 * 다른사람활동내역보여주기
	 */
	@RequestMapping(value="/member/activityOther.do", method= {RequestMethod.GET})
	public String viewActivityOther(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		String str_page = request.getParameter("page");
		String nickname = paraMap.get("nickname");
		int totalCount = 0;           // 총 게시물 건수
		int sizePerPage = 10;         // 한 페이지당 보여줄 게시물 건수 
		int page = 0;    			  // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		int totalPage = 0;            // 총 페이지수(웹브라우저상에서 보여줄 총 페이지 개수, 페이지바)
		int startRno = 0; 			  // 시작 행번호
		int endRno = 0;   			  // 끝 행번호
		
		// 총 게시물 건수(totalCount)
		totalCount = service.getTotalActivityOther(paraMap);
		
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
		
		
		List<ActivityVO> activities = service.getActivityOther(paraMap);
		// 페이징 처리한 글목록 가져오기(검색이 있든지, 검색이 없든지 모두 다 포함한 것)
		
		
		// 페이지바 만들기 
		int blockSize = 5;
		// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 개수이다.
		
		int loop = 1;
		
		int pageNo = ((page - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<ul class='my pagination pagination-md justify-content-center mt-5'>";
		String url = request.getContextPath()+"/member/activityOther.do";
		
		// === [<<][<] 만들기 === //
		if(pageNo != 1) {
			//[<<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?page=1&nickname="+nickname+"'>" + 
					   "    <i class='fa-solid fa-angles-left'></i>" + 
					   "  </a>" + 
					   "</li>";
			//[<]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?page="+(pageNo-1)+"&nickname="+nickname+"'>" + 
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
						   "  <a class='page-link' href='"+url+"?page="+pageNo+"&nickname="+nickname+"'>"+pageNo+"</a>" + 
						   "</li>";        
			}
			
			loop++;
			pageNo++;
		}// end of while--------------------------
		
		// === [>][>>] 만들기 === //
		if( pageNo <= totalPage) {
			//[>]
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?page="+pageNo+"&nickname="+nickname+"'>"+
					   "    <i class='fa-solid fa-angle-right'></i>"+
					   "  </a>" + 
					   "</li>";
			
			//[>>] 
			pageBar += "<li class='page-item'>" + 
					   "  <a class='page-link' href='"+url+"?page="+totalPage+"&nickname="+nickname+"'>"+
					   "    <i class='fas fa-solid fa-angles-right'></i>"+
					   "  </a>" + 
					   "</li>";
		}
		
		pageBar += "</ul>";
		
		Map<String,String> memberMap = service.getProfileByNickname(paraMap);
		
		request.setAttribute("memberMap", memberMap);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("activities",activities);
		
		return "member/activityOther.tiles1";
	}
	
	
	
	/**
	 * 활동내역 페이지 GET요청시 페이지 보여주기(페이징처리)
	 */
	@RequestMapping(value="/member/activities.do", method= {RequestMethod.GET})
	public String requiredLogin_viewActivities(HttpServletRequest request,HttpServletResponse response) {
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
		
		
		List<ActivityVO> activities = service.getActivities(paraMap);
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
		String url = request.getContextPath()+"/member/activities.do";
		
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
	public String requiredLogin_viewMyId(HttpServletRequest request,HttpServletResponse response) {
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
		MemberVO user = (MemberVO)session.getAttribute("user");
		String imageDivision = user.getProfile_image().substring(0, 4);
		request.setAttribute("imageDivision", imageDivision);
		return "/member/myInfo.tiles1";
		// /WEB-INF/views/tiles1/member/myInfo.jsp 페이지.
	}
	
	/**
	 * 개인정보보호방침 페이지 GET요청시 페이지 보여주기
	 */
	@RequestMapping(value="/policy/privacy.do", method= {RequestMethod.GET})
	public String requiredLogin_viewPrivacyPolicy(HttpServletRequest request,HttpServletResponse response) {
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
	}
	
	
	
	/**
	 * 계정찾기이메일 전송
	 */
	@ResponseBody
	@RequestMapping(value="/sendEmailByMyId.do", method= {RequestMethod.POST})
	public String sendEmailByMyId(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String jsonObj = service.sendEmailByMyId(email,request);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 계정찾기이후 이메일에서 계정찾기 링크를 클릭하면 나오는 비밀번호 변경페이지 매핑
	 */
	@RequestMapping(value="/changePwd.do", method= {RequestMethod.GET})
	public String viewChangePwd(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		try {
			if(session != null) {	//세션이 null이 아니라면
				if(request.getParameter("uuid").equals((String) session.getAttribute("uuid").toString())) {	//uuid가 http 헤더에 있는것과 세션에 있는값이 같다면,
					String email = request.getParameter("email");
					String userid = service.getMyID(email);	//이메일 값으로 유저아이디 알아내기
					
					request.setAttribute("userid", userid);
					return "changePwd";
				} else { 	//uuid가 url에 있는것과 http헤더에 있는것이 다르다면
					String message = "계정을 찾을 수 있는 시간을 초과하였습니다. 계정찾기를 다시 시도해주세요";
					String loc = "javascript:history.go(-1)";
					
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
				}
			} else {	//세션이 null 이라면
				String message = "계정을 찾을 수 있는 시간을 초과하였습니다. 계정찾기를 다시 시도해주세요";
				String loc = "javascript:history.go(-1)";
				
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
			}
			return "msg"; 
		} catch(NullPointerException e) {
			String message = "계정을 찾을 수 있는 시간을 초과하였습니다. 계정찾기를 다시 시도해주세요";
			String loc = "javascript:history.go(-1)";
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			return "msg";
		}//end of try-catch--
			
		
		// /WEB-INF/views/tiles1/member/changePwd.jsp 페이지.
	}
	
	
		
		
	/**
	 * 계정찾기 비밀번호 변경 해주기
	 */
	@RequestMapping(value="/member/editPasswd.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	public String editPasswd(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		
		int result = service.editPasswd(paraMap);	//DB에 가서 비밀번호 변경하기.
		String message ="";
		String loc = "";
		if(result == 1) {
			message = "비밀번호 수정이 완료되었습니다!";
			loc = request.getContextPath()+"/index.do";
		}
		else {
			message = "비밀번호 변경에 실패하였습니다. 다시 시도해주세요!";
			loc = request.getContextPath()+"/index.do";
		}
		request.setAttribute("message", message);
		request.setAttribute("loc", loc);
		return "msg"; 
	}
	
	
	
	
	/**
	 * 내정보 변경 해주기
	 */
	@ResponseBody
	@RequestMapping(value="member/infoChange.do", method= {RequestMethod.POST})
	public String editMyInfo(MemberVO member,MultipartFile profile_image, MultipartHttpServletRequest mrequest) {
		int result = 0;
		
		HttpSession session = mrequest.getSession();
		Map<String,String> paraMap = new HashMap<>();
		if( profile_image!=null && !profile_image.isEmpty() ) { //프로필이미지를 첨부하였을 경우
			
			
			String root = session.getServletContext().getRealPath("/");
			
			String path = root+"resources"+ File.separator +"images";
			
			String newFileName = "";
			// WAS(톰캣)의 디스크에 저장될 파일명 
			byte[] bytes = null;
			// 첨부파일의 내용물을 담는 변수
			try {
				// 첨부파일의 내용물을 읽어오기
				bytes = profile_image.getBytes();
				
				//유저가 업로드한 프로필이미지명
				String realFileName = profile_image.getOriginalFilename();
				// 첨부되어진 파일을 업로드 하도록 하는 것이다. 
				newFileName = fileManager.doFileUpload(bytes, realFileName, path);
				
				
				MemberVO loginUser = (MemberVO)session.getAttribute("user");
				paraMap.put("path",path);
				paraMap.put("newFileName",newFileName);
				paraMap.put("profile_image",loginUser.getProfile_image());
				paraMap.put("before_nickname",mrequest.getParameter("before_nickname"));
				
				
				//프로필이미지 첨부가 있는경우 회원정보 수정
				result = service.editMyInfo(member,paraMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {	//프로필이미지 첨부가 없는경우 회원정보 수정
			result = service.editMyInfoWithOutNoFile(member);
		}
		
		session.removeAttribute("user");
		MemberVO user = service.getUser(member.getUserid());
		session.setAttribute("user", user);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 내가쓴 게시물 목록 가져오기
	 */
	@ResponseBody
	@RequestMapping(value="member/myBoard.do", method= {RequestMethod.GET})
	public String editMyInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("user");
		String nickname = loginUser.getNickname();
		
		ActivityVO activities = service.getActivitiesByBoard(nickname);	// 내가 글작성한 게시물 활동내역 가져오기
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("activities", activities);
		return jsonObj.toString();
	}
	
	
	
	
	
	/**
	 * 이메일 변경시 업데이트해주기
	 */
	@ResponseBody
	@RequestMapping(value="/member/editEmail.do", method= {RequestMethod.POST})
	public String editEmail(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		paraMap.put("userid",user.getUserid());
		
		boolean editEmailSuccess = service.editEmail(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("editEmailSuccess", editEmailSuccess);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 비밀번호 변경시 업데이트해주기
	 */
	@ResponseBody
	@RequestMapping(value="/member/editPasswdWithUserid.do", method= {RequestMethod.POST})
	public String editPasswdWithUserid(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		//로그인되어있는 userid 값 넣어주기
		paraMap.put("userid",user.getUserid());
		
		boolean editPasswdSuccess = service.editPasswdWithUserid(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("editPasswdSuccess", editPasswdSuccess);
		
		return jsonObj.toString();
	}
	
	
	/**
	 * 기존비밀번호와 같은지 확인하기
	 */
	@ResponseBody
	@RequestMapping(value="/member/samePasswdCheck.do", method= {RequestMethod.POST})
	public String samePasswdCheck(@RequestParam Map<String,String> paraMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		paraMap.put("userid",user.getUserid());
		
		boolean samePasswd = service.samePasswdCheck(paraMap);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("samePasswd", samePasswd);
		
		return jsonObj.toString();
	}
	
	
	
	/**
	 * 가입된 이메일이 존재하는지 여부 검사 + 소셜연동회원인지 검사
	 * @return 가입된 이메일이 존재하고 소셜연동회원이 아니라면 true, 존재하지 않는이메일이거나 소셜연동회원이라면 false를 반환한다.
	 */
	@ResponseBody
	@RequestMapping(value="/member/emailExistAndSnsCheck.do", method= {RequestMethod.POST})
	public String emailExistAndSnsCheck(@RequestParam String email, HttpServletRequest request) {
		boolean result = service.emailExistAndSnsCheck(email);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		return jsonObj.toString();
	}
	
	
	
	
	
	//============================================================================== //
	//============================= 마이페이지 관련 끝=================================== //
	//============================================================================== //
	
	
	
      
}

































	
	
	
	
	
	
	
	

