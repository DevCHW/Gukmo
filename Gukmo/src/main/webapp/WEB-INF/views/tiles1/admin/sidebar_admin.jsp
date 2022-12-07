<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
 
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/seongmin/sidebar_admin.css" />

<script type="text/javascript">
  $(document).ready(function(){
	  
    let url = window.document.location.href;
    url = url.substr(27);
    switch (url) {
	  case "/admin/memberManage_List.do" :
		  $("li#li_member_List").css("font-weight","bold");
		  $("li#li_member_List").css("font-size","15px");
		  break;
	  case "/admin/academyManage_List.do" :
		  $("li#li_acaMember_List").css("font-weight","bold");
		  $("li#li_acaMember_List").css("font-size","15px");		
		  break;
	  case "/admin/adManage_List.do" :
		  $("li#li_ad_List").css("font-weight","bold");
		  $("li#li_ad_List").css("font-size","15px");
		  break;
	  case "/admin/reportManage_List.do" :
		  $("li#li_report_List").css("font-weight","bold");
		  $("li#li_report_List").css("font-size","15px");
		  break;
	  case "/admin/penalty_List.do" :
		  $("li#li_penalty_List").css("font-weight","bold");
		  $("li#li_penalty_List").css("font-size","15px");
		  break;
	}//end of switch-case---
  });
</script>


<%-- 사이드바 시작 --%>
<div id="sidebar mr-2">
  <h4 id="sidebar_title">관리자 메뉴</h4>
  <ul class="pl-3 pt-3">
    <li id="li_member_List" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/admin/memberManage_List.do'">일반회원 관리</li>
    <li id="li_acaMember_List" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/admin/academyManage_List.do'">학원회원 관리</li>
    <li id="li_ad_List" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/admin/adManage_List.do'">광고현황 관리</li>
    <li id="li_report_List" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/admin/reportManage_List.do'">신고현황 관리</li>
    <li id="li_penalty_List" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/admin/penalty_List.do'">정지현황 관리</li>
  </ul>
</div>
<%-- 사이드바 끝 --%>