<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- ======= #28. tile2 중 sideinfo 페이지 만들기  ======= --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>
<script type="text/javascript">
//event
$(document).ready(function(){
	
  let url = window.document.location.href;
  url = url.substr(27);
  let questionmarkIdx = url.indexOf("?");
  if(questionmarkIdx != -1){
    url = url.substr(0,questionmarkIdx);
  }
  
  
  /*
  	// == url 정리 == //
  	[회원 관련 페이지]
  	/admin/member/normal/list.do -- 일반회원내역 페이지
  	/admin/member/academy/list.do -- 교육기관회원내역 페이지
  	/admin/member/detail.do -- 회원 상세페이지
  		
  	[광고 관련 페이지]
  	/admin/advertisement/list.do --광고내역 페이지
  	/admin/advertisement/detail.do --광고 상세페이지
  	/admin/advertisement/new.do -- 광고등록 페이지
  	/admin/advertisement/calendar.do -- 광고일정 페이지
  	
  	[신고 관련 페이지]
  	/admin/report/list.do --신고내역 페이지
  	/admin/report/detail.do --신고 상세페이지
  */
  
  
  //각 url별로 메뉴 활성화시키기
  switch (url) {
	  case "/admin/index.do" :	//관리자 메인페이지	
		  	$("li.dashboardMenu").addClass("active");	//대시보드메뉴 활성화
		    break;
		    
  	  // ====================== 회원관련   시작  ========================== //
	  case "/admin/member/normal/list.do" :							//일반회원내역 URL일 경우	
	  		$("li.memberMenu").children(":first").trigger("click");	//메뉴 클릭시키기
	  		$("li.memberMenu").addClass("active");					//회원메뉴 활성화
	  		$("a.normalMemberMenu").addClass("active")				//일반회원내역 메뉴 활성화
		    break;
		    
	  case "/admin/member/academy/list.do" :						//교육기관회원내역 URL일 경우
		    $("li.memberMenu").children(":first").trigger("click"); //메뉴 클릭시키기
		    $("li.memberMenu").addClass("active");					//회원메뉴 활성화
	  		$("a.acaMemberMenu").addClass("active");				//교육기관회원내역 메뉴 활성화
		    break;
	  		
	  case "/admin/member/detail.do" :								//회원상세 URL일 경우
		  	$("li.memberMenu").children(":first").trigger("click"); //메뉴 클릭시키기
		    $("li.memberMenu").addClass("active");					//회원메뉴 활성화
		    if("${requestScope.member.academy_name}" != ""){
		    	$("a.acaMemberMenu").addClass("active");			//교육기관회원내역 메뉴 활성화
		    } else {
		    	$("a.normalMemberMenu").addClass("active")			//일반회원내역 메뉴 활성화
		    }
		    break;
		    
	  case "/admin/report/list.do" :								//신고내역 URL일 경우
		  	$("li.memberMenu").children(":first").trigger("click"); //메뉴 클릭시키기
		    $("li.memberMenu").addClass("active");					//회원메뉴 활성화
	  		$("a.reportMenu").addClass("active");					//신고내역 메뉴 활성화
		    break;
	  // ====================== 회원관련   끝  ========================== //
	  	
	  	
	  
	  // ====================== 광고관련   시작  ========================== //
	  case "/admin/advertisement/list.do" :								   //광고내역 URL일 경우
		  	$("li.advertisementMenu").children(":first").trigger("click"); //메뉴 클릭시키기
		    $("li.advertisementMenu").addClass("active");				   //광고메뉴 활성화
	  		$("a.advertisementList").addClass("active");				   //광고내역메뉴 활성화
		    break;
	  		
	  case "/admin/advertisement/detail.do" :							   //광고상세 URL일 경우
		 	$("li.advertisementMenu").children(":first").trigger("click"); //메뉴 클릭시키기
		    $("li.advertisementMenu").addClass("active");				   //광고메뉴 활성화
	  		$("a.advertisementList").addClass("active");				   //광고내역메뉴 활성화
		    break;
	  		
	  case "/admin/advertisement/new.do" :								   //광고등록 URL일 경우
		  	$("li.advertisementMenu").children(":first").trigger("click"); //메뉴 클릭시키기
		    $("li.advertisementMenu").addClass("active");				   //광고메뉴 활성화
	  		$("a.advertisementNew").addClass("active");					   //광고등록메뉴 활성화
		    break;
	  		
	  case "/admin/advertisement/calendar.do" :							   //광고일정 URL일 경우
		  	$("li.advertisementMenu").children(":first").trigger("click"); //메뉴 클릭시키기
		    $("li.advertisementMenu").addClass("active");				   //광고메뉴 활성화
	  		$("a.advertisementCalendar").addClass("active");					   //광고일정메뉴 활성화
		    break;
		    
		 // ====================== 광고관련   끝  ========================== //
		 
		 
  }//end of switch-case---

});//end of $(document).ready(function(){})----
</script>


</script>
<%-- Sidebar --%>
<ul class="navbar-nav sidebar sidebar-dark accordion" id="accordionSidebar">
	<%-------------------------------- 대시보드 영역 시작 --------------------------------------%>
    <%-- Sidebar - Brand --%>
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=ctxPath %>/index.do">
        <img src="<%=ctxPath %>/resources/images/titleicon.png" style="width:60px; height:60px;">
        <div class="sidebar-brand-text mx-3">ADMIN MENU</div>
    </a>

    <%-- Divider --%>
    <hr class="sidebar-divider my-0">

    <%-- Nav Item - Dashboard --%>
    <li class="nav-item dashboardMenu">
        <a class="nav-link" href="<%=ctxPath %>/admin/index.do">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>대시보드</span></a>
    </li>
    <%-------------------------------- 대시보드 영역 끝 --------------------------------------%>




   <%-------------------------------- 회원관리 영역 시작 --------------------------------------%>
    <%-- Divider --%>
    <hr class="sidebar-divider">

    <%-- Heading --%>
    <div class="sidebar-heading">Member</div>

    <%-- Nav Item - Member Collapse Menu --%>
    <li class="nav-item memberMenu">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
            aria-expanded="true" aria-controls="collapseTwo">
            <%-- 회원관련 아이콘 --%>
            <i class="fas fa-fw fa-cog"></i>
            <span>회원관리</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Member</h6>
                <a class="collapse-item normalMemberMenu" href="<%=ctxPath %>/admin/member/normal/list.do">일반회원 내역</a>
                <a class="collapse-item acaMemberMenu" href="<%=ctxPath %>/admin/member/academy/list.do">교육기관회원 내역</a>
                <a class="collapse-item reportMenu" href="<%=ctxPath %>/admin/report/list.do">신고 내역</a>
            </div>
        </div>
    </li>
    
    <%-------------------------------- 회원관리 영역 끝--------------------------------------%>






	<%-------------------------------- 광고관리 영역 시작--------------------------------------%>
	<%-- Divider --%>
    <hr class="sidebar-divider">

    <%-- Heading --%>
    <div class="sidebar-heading">Advertisement</div>
    <%-- Nav Item - Advertisement Collapse Menu --%>
    <li class="nav-item advertisementMenu">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
            aria-expanded="true" aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-wrench"></i>
            <span>광고관리</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
            data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Advertisement</h6>
                <a class="collapse-item advertisementList" href="<%=ctxPath %>/admin/advertisement/list.do">광고내역</a>
                <a class="collapse-item advertisementNew" href="<%=ctxPath %>/admin/advertisement/new.do">광고등록</a>
                <a class="collapse-item advertisementCalendar" href="<%=ctxPath %>/admin/advertisement/calendar.do">광고일정</a>
            </div>
        </div>
    </li>
	<%-------------------------------- 광고관리 영역 끝--------------------------------------%>
	
	
	
	
	
	
	
	
	<%-------------------------------- 토글버튼(왼쪽으로 살짝닫기) 영역 시작--------------------------------------%>
    <%-- Divider --%>
    <hr class="sidebar-divider d-none d-md-block">

    <%-- Sidebar Toggler (Sidebar) --%>
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
    
    <%-------------------------------- 토글버튼(왼쪽으로 살짝닫기) 영역 끝--------------------------------------%>






</ul>
<%-- End of Sidebar --%>






  