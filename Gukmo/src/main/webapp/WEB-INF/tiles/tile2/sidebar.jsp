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
  		
  	[광고 관련 페이지]
  	/admin/advertisement/list.do --광고내역 페이지
  	/admin/advertisement/new.do -- 광고등록 페이지
  	
  	[신고 관련 페이지]
  	/admin/report/list.do --신고내역 페이지
  */
  
  
  //각 url별로 메뉴 활성화시키기
  switch (url) {
	  case "/admin/index.do" :	
		  	$("li.dashboardMenu").addClass("active");	//대시보드메뉴 활성화
		    break;
		    
	  case "/admin/member/normal/list.do" :				//일반회원내역메뉴URL일 경우	
	  		$("li.memberMenu").addClass("active");		//회원관리메뉴 활성화
	  		$("a.normalMemberMenu").addClass("active")	//일반회원내역 메뉴 활성화
		    break;
		    
	  case "/admin/member/academy/list.do" :			//교육기관회원내역메뉴URL일 경우
		    $("li.memberMenu").addClass("active");		//회원관리메뉴 활성화
	  		$("a.AcaMemberMenu").addClass("active")		//일반회원내역 메뉴 활성화
		    break;
	    
	  case "/admin/advertisement/list.do" :					//광고내역메뉴URL일 경우
		    $("li.advertisementMenu").addClass("active");	//광고메뉴 활성화
	  		$("a.advertisementList").addClass("active");	//광고내역메뉴
		    break;
	  		
	  case "/admin/advertisement/new.do" :						//광고등록메뉴URL일 경우
		    $("li.advertisementMenu").addClass("active");		//회원관리메뉴 활성화
	  		$("a.advertisementNew").addClass("active");			//광고등록메뉴
		    break;
	    
	  case "/admin/report/list.do" :				//신고내역메뉴URL일 경우
			$("li.reportMenu").addClass("active");	//신고메뉴 활성화
			$("a.reportList").addClass("active");	//신고내역 메뉴 활성화
		    break;
		    
	  case "/admin/report/접수전.do" :				//신고내역메뉴(접수전)URL일 경우
	  		$("li.reportMenu").addClass("active");	//신고메뉴 활성화
	  		$("a.reportList").addClass("active");	//신고내역(접수전)메뉴활성화
		    break;
		    
	  case "/admin/report/접수완료.do" :				//신고내역메뉴(접수완료)URL일 경우
	  		$("li.reportMenu").addClass("active");	//신고메뉴 활성화
	  		$("a.reportList").addClass("active");	//신고내역(접수완료)메뉴활성화
		    break;
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
                <a class="collapse-item AcaMemberMenu" href="<%=ctxPath %>/admin/member/academy/list.do">교육기관회원 내역</a>
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
            </div>
        </div>
    </li>
	<%-------------------------------- 광고관리 영역 끝--------------------------------------%>
	
	
	
	
	
	
	
	<%-------------------------------- 신고관리 영역 시작--------------------------------------%>
    <%-- Divider --%>
    <hr class="sidebar-divider">

    <%-- Heading --%>
    <div class="sidebar-heading">Report</div>

    <%-- Nav Item - Report Collapse Menu --%>
    <li class="nav-item reportMenu">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
            aria-expanded="true" aria-controls="collapsePages">
            <i class="fas fa-fw fa-folder"></i>
            <span>신고관리</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Report</h6>
                <a class="collapse-item reportList" href="<%=ctxPath %>/admin/report/list.do">접수전내역</a>
                <a class="collapse-item" href="<%=ctxPath %>/admin/report/접수전.do">접수완료</a>
                <a class="collapse-item" href="<%=ctxPath %>/admin/report/접수전.do">전체</a>
            </div>
        </div>
    </li>
	<%-------------------------------- 신고관리 영역 끝--------------------------------------%>
	
	
	
	
	
	
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






  