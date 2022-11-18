<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
 
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/hyunwoo/sidebar.css" />

    
<%-- 사이드바 시작 --%>
<div id="sidebar">
  <h5 id="sidebar_title">내 계정</h5>
  <ul class="pl-3 pt-3">
    <li>회원정보</li>
    <li>계정</li>
    <li>활동 내역</li>
  </ul>
</div>
<%-- 사이드바 끝 --%>