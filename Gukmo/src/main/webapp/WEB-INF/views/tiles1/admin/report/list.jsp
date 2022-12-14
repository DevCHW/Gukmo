<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>
<%-- 신고리스트 페이지입니다. --%>


<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/report/list.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/report/list.js" ></script>


  <div id="section" class="d-flex">
  
  
    <%---------------------------- 사이드바 호출 --------------------------------%>
    
    <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/sidebar.jsp" />
    
    <%---------------------------- 사이드바 호출 ---------------------------------------%>


    <%---------------------------- (div#main) 시작 -------------------------------%>
    <div id="main" class="py-5 px-4 w-100">
    
      <%----------------- 검색창 호출 ------------------------%>
      <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/searchBar.jsp" />
      
      <%------------------------ 검색창 호출  ------------------------%>



      <%-- 검색결과 갯수 --%>
      <div id="total_cnt" class="my-2">${requestScope.totalCount}&nbsp;건</div>


	  <%------------------------ 신고리스트영역 시작 ------------------------%>
      <div class="reportList" >
      
        <%----- 타이틀 시작 -----%>
        <div id="title" class="d-flex justify-content-between py-3 border-top border-bottom">
          <div class="report_type text-center">
            <div>신고분류</div>
          </div>
          <div class="report_nickname text-center">
            <div>신고자 닉네임</div>
          </div>
          <div class="reported_nickname text-center">
            <div>피신고자 닉네임</div>
          </div>
          <div class="simple_report_reason text-center">
          	<div>사유</div>
          </div>
          <div class="report_date text-center">
          	<div>신고일자</div>
          </div>
          <div class="receipt text-center">
          	<div>접수 여부</div>
          </div>
        </div>
        <%----- 타이틀 끝 -----%>



        <%-- 신고리스트 반복문 시작 --%>
        <c:forEach var="report" items="${requestScope.reportList}" varStatus="status">
        <div class="list_box d-flex justify-content-between py-3 border-bottom" onclick="location.href='<%=ctxPath %>/admin/reportDetail.do?report_num=${report.report_num}&report_nickname=${report.report_nickname}&reported_nickname=${report.reported_nickname}'">
          <%-- report_type --%>
          <div class="report_info report_type text-center">
            <div>${report.report_type}</div>
          </div>
          
          <%-- report_nickname --%>
          <div class="report_info report_nickname text-center">
            <div>${report.report_nickname}</div>
          </div>
          
          <%-- reported_nickname --%>
          <div class="report_info reported_nickname text-center">
            <div class="m-auto">${report.reported_nickname}</div>
          </div>
          
          <%-- simple_report_reason --%>
          <div class="report_info simple_report_reason text-center">
            <div class="m-auto">${report.simple_report_reason}</div>
          </div>
          
          <%-- report_date --%>
          <div class="report_info report_date text-center">
            <div class="m-auto">${report.report_date}</div>
          </div>
          
          <%-- 접수 여부 --%>
          <div class="report_info receipt text-center">
            <div class="m-auto">
	            <c:if test='${report.receipt == 0}' >
	          	    접수 전
	          	</c:if>
	          	<c:if test='${report.receipt == 1}' >
	          	    접수 완료
	          	</c:if>
            </div>
          </div>
          
        </div>
        </c:forEach>
        <%-- 신고리스트 반복문 끝 --%>
        
        
      </div>
      <%------------------------ 신고리스트영역  끝 ------------------------%>






      <%---------------------- 페이지 바 시작 --------------------------%>
      
      <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/pagebar.jsp" />
      
      <%------------------------- 페이지 바 끝 -------------------------%>
    </div>
    <%----------------------------------(div#main) 끝 -------------------------------------%>

  </div>