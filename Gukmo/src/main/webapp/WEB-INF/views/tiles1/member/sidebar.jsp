<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
 
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/hyunwoo/sidebar.css" />
<script type="text/javascript">
  $(document).ready(function(){
	  
    let url = window.document.location.href;
    url = url.substr(27);
    switch (url) {
	  case "/member/myInfo.do" :
		  $("li#li_myInfo").css("font-weight","bold");
		  $("li#li_myInfo").css("font-size","15px");
		  break;
	  case "/member/myId.do" :
		  $("li#li_myId").css("font-weight","bold");
		  $("li#li_myId").css("font-size","15px");
		
		  break;
	  case "/member/activities.do" :
		  $("li#li_activities").css("font-weight","bold");
		  $("li#li_activities").css("font-size","15px");
		  break;
	}//end of switch-case---
  });
</script>


<%-- 사이드바 시작 --%>
<div id="sidebar">
  <h5 id="sidebar_title">내 계정</h5>
  <ul class="pl-3 pt-3">
    <li id="li_myInfo" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/member/myInfo.do'">회원정보</li>
    <li id="li_myId" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/member/myId.do'">계정</li>
    <li id="li_activities" style="cursor:pointer;" onclick="location.href='<%=ctxPath %>/member/activities.do'">활동 내역</li>
  </ul>
</div>
<%-- 사이드바 끝 --%>