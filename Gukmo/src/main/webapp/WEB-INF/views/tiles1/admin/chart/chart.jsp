<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 차트 페이지입니다. --%>


<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seonwoo/chart.css" />


<script src="<%= ctxPath%>/resources/Highcharts-10.3.1/code/highcharts.js"></script>
<script src="<%= ctxPath%>/resources/Highcharts-10.3.1/code/modules/exporting.js"></script>
<script src="<%= ctxPath%>/resources/Highcharts-10.3.1/code/modules/export-data.js"></script>
<script src="<%= ctxPath%>/resources/Highcharts-10.3.1/code/modules/accessibility.js"></script>

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/chart.js" ></script>

<script type="text/javascript" >


</script>
  <div id="section" class="d-flex">
  
  
    <%---------------------------- 사이드바 호출 -------------------------------%>
    
    <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/sidebar.jsp" />
    
    <%---------------------------- 사이드바 호출 --------------------------------------%>


    <div id="main" class="py-5 px-4 w-100">
    
   
   	  <%------------------------ 차트영역 시작 -----------------------%>
   	  
	<figure class="highcharts-figure">
	    <div id="chart_container"></div>
	</figure>
   	  
   	  
   	  
   	  <%------------------------ 차트영역 끝 -----------------------%>


      
    </div>

  </div>