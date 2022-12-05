<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<%
	String ctxPath = request.getContextPath();
%>

<script type="text/javascript">

	$(document).ready(function(){

		if( ${not empty requestScope.paraMap} ) {

			$("select#division").val("${requestScope.paraMap.memberStatus}");
			$("select#searchType").val("${requestScope.paraMap.searchType}");
			$("input#searchWord").val("${requestScope.paraMap.searchWord}");

		}
	}); // end of $(document).ready(function(){})------------------
	
</script>


  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/seongmin/penaltyDetail.css" />

  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/penaltyDetail.js" ></script>
	
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
	                <div id="penalty_num">정지 번호 : &nbsp;<span>${requestScope.penaltyDetail.penalty_num}</span></div>
	                <div id="userid"> 아이디 : &nbsp;<span>${requestScope.userid}</span></div>
	                <div id="nickname">닉네임 : &nbsp;<span>${requestScope.penaltyDetail.nickname}</span></div>
	                <div id="simple_penalty_reason">정지 사유: &nbsp;<span>${requestScope.penaltyDetail.simple_penalty_reason}</span></div>
	                <div id="detail_penalty_reason">정지 사유(상세): &nbsp;<span>${requestScope.penaltyDetail.detail_penalty_reason}</span></div>
	                <div id="penalty_start_date">정지 시작일: &nbsp;<span>${requestScope.penaltyDetail.penalty_start_date}</span></div>
	                <div id="penalty_period">정지 기간 : &nbsp;<span>${requestScope.penaltyDetail.penalty_period}</span></div>
	              </div>
	            </div>
	              <input type="hidden" name="nickname" id="${requestScope.penaltyDetail.nickname}" />
	              <input type="hidden" name="userid" id="${requestScope.userid}" />
				  <button type="button" id="" class="btn border rounded block_recovery">정지 해제</button>&nbsp;
	          </div>
	        </div>	      
	      </div>
	    </div>
	    <%----------------------------------- main 끝-------------------------------------%>
	  </div>

					
      <br>

