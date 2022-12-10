<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 관리자 메인 페이지입니다. --%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/index.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/index.js" ></script>

<div id="section" class="d-flex">

  <%---------------------------- 사이드바 호출 --------------------------------%>
  <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/sidebar.jsp" />
  <%---------------------------- 사이드바 호출 ---------------------------------------%>

  <%---------------------------- (div#main) 시작 -------------------------------%>
  <div id="main" class="py-5 px-4 w-100">
  	<p>관리자 메인페이지 시작 !</p>
  </div>
  
  <%----------------------------------(div#main) 끝 -------------------------------------%>
  
</div>
