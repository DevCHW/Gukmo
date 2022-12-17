<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- === #25. tiles 를 사용하는 레이아웃2 페이지 만들기 === --%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
	String ctxPath = request.getContextPath();
%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>국모 관리자님 반갑습니다!</title>

  <%-- Required meta tags --%>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
  
  <%-- Bootstrap CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 

  <%-- Font Awesome 5 Icons --%>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
  
  <%-- 공통 CSS --%>
  <link rel="stylesheet" href="<%=ctxPath %>/resources/css/hyunwoo/admin/admin.css">
  
  <%-- title Icon --%>
  <link href="<%=ctxPath %>/resources/images/titleicon.png" rel="shortcut icon" type="image/x-icon">
  
  <%-- Optional JavaScript --%>
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script> 
  
  <%--  ===== 스피너 및 datepicker 를 사용하기 위해  jquery-ui 사용하기 ===== --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/jquery-ui-1.13.1.custom/jquery-ui.css" />
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery-ui-1.13.1.custom/jquery-ui.js"></script>

  <%-- *** ajax로 파일을 업로드할때 가장 널리 사용하는 방법 ==> ajaxForm *** --%>
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery/jquery.form.min.js"></script>
  
  <%-- Core plugin JavaScript--%>
  <script src="<%=ctxPath %>/resources/js/hyunwoo/admin/jquery.easing.min.js"></script>
  
  <%-- 공통 JS --%>
  <link rel="stylesheet" href="<%=ctxPath %>/resources/js/hyunwoo/admin/admin.js">
  
  

</head>
<body>
	<%-- Page Wrapper --%>
   	<div id="wrapper">
   	
    	<div id="mysidebar">
			<tiles:insertAttribute name="sidebar" />
		</div>
	
		<div id="content-wrapper" class="d-flex flex-column">
			<tiles:insertAttribute name="content" />
		</div>
	</div>
	
	
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/admin.js" ></script>
</body>
</html>