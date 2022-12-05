<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 페이지바 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/assembly/sideBar.css" />


<script type="text/javascript">
	// field declaration
	let li_member_list_click_cnt = 0;
	
	
	//event
	$(document).ready(function(){
	  let url = window.document.location.href;
	  url = url.substr(27);
	  switch (url) {
	  case "/admin/memberManage_List.do" :	//일반회원메뉴
	    $("li#li_member_List").css("font-weight","bold");
	    $("li#li_member_List").css("font-size","15px");
	    break;
	  case "/admin/academyManage_List.do" :	//학원회원메뉴
	    $("li#li_acaMember_List").css("font-weight","bold");
	    $("li#li_acaMember_List").css("font-size","15px");		
	    break;
	  case "/admin/adManage_List.do" :	//광고메뉴
	    $("li#li_ad_List").css("font-weight","bold");
	    $("li#li_ad_List").css("font-size","15px");
	    break;
	  case "/admin/reportManage_List.do" :	//신고내역메뉴
	    $("li#li_report_List").css("font-weight","bold");
	    $("li#li_report_List").css("font-size","15px");
	    break;
	  }//end of switch-case---
	
	  
	  //회원 클릭시 이벤트(열고닫기)
	  $("li#li_member_list").click(function(){
	    li_member_list_click_cnt++;
	    if(li_member_list_click_cnt%2==0){  //짝수번 클릭했다면
	      $("div#memberManageMenu").css("display","none");
	    } else{ //홀수번 클릭했다면
	      $("div#memberManageMenu").css("display","flex");
	    }
	  });//end of Event---
	
	});//end of $(document).ready(function(){})----
</script>




<div id="sidebar" class="pl-4 py-5">
  <h4 id="sidebar_title">관리자 메뉴</h4>
  <ul class="pl-3 pt-3">
    <li id="li_statistics">통계</li>
    <li id="li_member_list">회원</li>
    <div id="memberManageMenu" class="flex-column pl-4">
      <span id="nomalMemberManage" class="detail_menu">일반회원</span>
      <span id="academyMemberManage" class="detail_menu">교육기관회원</span>
    </div>
    <li id="li_advertisement_list">광고</li>
    <li id="li_report_list">신고내역</li>
  </ul>
</div>