<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<%
	String ctxPath = request.getContextPath();
%>

<!--  직접 만든 css -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/adDetail.css" />

<!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/adDetail.js?ver=1" ></script>
<body>


	<div id="container" class="container-fluid row mt-5">
	  <div class="col-2">
		  <jsp:include page="/WEB-INF/views/tiles1/admin/sidebar_admin.jsp" />
	  </div>
		
	  <div class="col-9 main">	  

	    <%----------------------------------- main 시작-------------------------------------%>
	      <%-- main_header --%>
	      <div class="main_header border rounded">
	        <%-- top --%>
	        <div class="d-flex">
	          <div class="d-flex w-100 justify-content-between py-4 px-3 align-items-center">
	            <div class="d-flex align-items-center">
	              <%-- 프사 --%>

	              <div class="ml-4 py-1 contents">

	                <div id="advertisement_num">광고 번호 : &nbsp;<span>${requestScope.adDetail.advertisement_num}</span></div>
	                <div id="division">광고 구분 : &nbsp;<span>${requestScope.adDetail.division}</span></div>
	                <div id="client_name">클라이언트 명 : &nbsp;<span>${requestScope.adDetail.client_name}</span></div>
	                <div id="client_phone">클라이언트 번호 : &nbsp;<span>${requestScope.adDetail.client_phone}</span></div>
	                <div id="client_phone">파일명 : &nbsp;<span>${requestScope.adDetail.file_name}</span></div>
	                <div id="url">URL : &nbsp;<span>${requestScope.adDetail.url}</span></div>
	                <div id="start_date">광고 게시 날짜 : &nbsp;<span>${requestScope.adDetail.start_date}</span></div>
	                <div id="period">광고 기간 : &nbsp;<span>${requestScope.adDetail.period}</span></div>
		            <div id="status">광고 상태 : &nbsp;
		              <span>
               			<c:if test="${requestScope.adDetail.status == 0}">
		                  	광고중
		                </c:if>
		                <c:if test="${requestScope.adDetail.status == 1}">
		                  	광고중 아님
		                </c:if>
		              </span>
		            </div>
	              </div>
	            </div>
	            <input type="hidden" id = "${requestScope.adDetail.advertisement_num}" name="advertisement_num" />
	          </div>
	        </div>
		   </div>	
	      </div>
	    </div>
	                





