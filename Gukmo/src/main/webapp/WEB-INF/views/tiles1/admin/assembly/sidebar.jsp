<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 페이지바 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/assembly/sidebar.css" />


<script type="text/javascript">
	
	
	//event
	$(document).ready(function(){
	  let url = window.document.location.href;
	  url = url.substr(27);
	  switch (url) {
	  case "/admin/member/normal/list.do" :	//일반회원메뉴
	    $("li#memberManageMenu_box").show();
	    $("p#nomalMemberManage").css("font-weight","bold");
	    $("p#nomalMemberManage").css("font-size","15px");
	    $("p#nomalMemberManage").css("background-color","#f0f0f5");
	    break;
	    
	  case "/admin/member/academy/list.do" :	//교육기관회원메뉴
	    $("li#memberManageMenu_box").show();
        $("p#academyMemberManage").css("font-weight","bold");
        $("p#academyMemberManage").css("font-size","15px");
        $("p#academyMemberManage").css("background-color","#f0f0f5");		
	    break;
	    
	  case "/admin/advertisement/list.do" :	//광고메뉴
	    $("li#li_ad_List").css("font-weight","bold");
	    $("li#li_ad_List").css("font-size","15px");
	    $("li#li_ad_List").css("background-color","#f0f0f5");
	    break;
	    
	  case "/admin/report/list.do" :	//신고내역메뉴
		$("li#reportManageMenu_box").show();
	    $("p#all_receipt").css("font-weight","bold");
	    $("p#all_receipt").css("font-size","15px");
	    $("p#all_receipt").css("background-color","#f0f0f5");
	    break;
	  }//end of switch-case---
	  
	  
	  //회원 클릭시 이벤트(열고닫기)
	  $("li.detail_menu_view").click(function(e){
	    const target = $(e.currentTarget);
	    
	    target.next().toggle();
	  });//end of Event---
	
	
	});//end of $(document).ready(function(){})----
</script>




<div id="sidebar" class="pl-4 py-5">
  <h4 id="sidebar_title">관리자 메뉴</h4>
  <ul class="pl-3 pt-3">
    <%-- 통계메뉴 --%>
    <li id="li_statistics" class="menu" onclick="">통계</li>
    <%-- 회원메뉴 --%>
    <li id="li_member_list" class="detail_menu_view menu">회원</li>
    <li id="memberManageMenu_box" class="detail_menu_box pl-4">
      <p id="nomalMemberManage" class="detail_menu" onclick="location.href='<%=ctxPath %>/admin/member/normal/list.do'">일반회원</p>
      <p id="academyMemberManage" class="detail_menu" onclick="location.href='<%=ctxPath %>/admin/member/academy/list.do'">교육기관회원</p>
    </li>
    <%-- 광고메뉴 --%>
    <li id="li_ad_List" class="menu" onclick="location.href='<%=ctxPath %>/admin/advertisement/list.do'">광고</li>
    <%-- 신고메뉴 --%>
    <li id="li_report_list" class="detail_menu_view menu">신고내역</li>
    <li id="reportManageMenu_box" class="detail_menu_box pl-4">
      <p id="all_receipt" class="detail_menu" onclick="location.href='<%=ctxPath %>/admin/report/list.do'">전체</p>
      <p id="before_receipt" class="detail_menu" onclick="location.href=''">접수전</p>
      <p id="after_receipt" class="detail_menu" onclick="location.href=''">접수완료</p>
    </li>
  </ul>
</div>