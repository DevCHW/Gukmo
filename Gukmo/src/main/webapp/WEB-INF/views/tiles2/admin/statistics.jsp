<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/statistics.css" />
<%-- 폰트 --%>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<%-- 차트 플러그인 --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/Chart.min.js" ></script>
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/statistics.js" ></script>
 <%-- Custom styles for this page --%>
<link href="<%=ctxPath %>/resources/css/hyunwoo/admin/dataTable/dataTables.bootstrap4.min.css" rel="stylesheet">
<%-- dataTable bootstrap4 --%>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/dataTable/jquery.dataTables.min.js"></script>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/dataTable/dataTables.bootstrap4.min.js"></script>
    
    
<%-- Main Content --%>
<div id="content">
  <%-- Begin Page Content --%>
  <div class="container-fluid">
  	  <%-- Page Heading --%>
      <h1 class="h3 my-2 text-gray-800">통계</h1>
      <div class="row">
      	<div class="col-md-12 col-lg-6">
	      <div class="card shadow mb-4">
	      	<div class="card-header d-flex justify-content-between">
	      	  <h6 class="m-0 font-weight-bold text-primary">요약</h6>
	      	</div>
	      	
	      	<div class="card-body">
	      		<div class="row border-bottom py-2">
	      			<div class="col-6 pl-2 border-right">총 회원 수</div>
	      			<div class="col-6 pl-2"><span style="font-weight:bold;">${requestScope.dataMap.CNT_MEMBER}</span><span>명</span></div>
	      		</div>
	      		
	      		<div class="row border-bottom py-2">
	      			<div class="col-6 pl-2 border-right">커뮤니티 작성 게시물 수</div>
	      			<div class="col-6 pl-2"><span style="font-weight:bold;">${requestScope.dataMap.CNT_COMMUNITY}</span><span>건</span></div>
	      		</div>
	      		
	      		<div class="row border-bottom py-2">
	      			<div class="col-6 pl-2 border-right">등록된 교육기관</div>
	      			<div class="col-6 pl-2"><span style="font-weight:bold;">${requestScope.dataMap.CNT_ACADEMY}</span><span>곳</span></div>
	      		</div>
	      		
	      		<div class="row border-bottom py-2">
	      			<div class="col-6 pl-2 border-right">등록된 교육과정</div>
	      			<div class="col-6 pl-2"><span style="font-weight:bold;">${requestScope.dataMap.CNT_CURRICULUM}</span><span>건</span></div>
	      		</div>
	      		
	      		<div class="row border-bottom py-2">
	      			<div class="col-6 pl-2 border-right">하루평균 게시물 작성 수</div>
	      			<div class="col-6 pl-2"><span style="font-weight:bold;">${requestScope.dataMap.AVG_JOIN_MEMBER}</span><span>명</span></div>
	      		</div>
	      		
	      		<div class="row border-bottom py-2">
	      			<div class="col-6 pl-2 border-right">하루평균 회원가입 수</div>
	      			<div class="col-6 pl-2"><span style="font-weight:bold;">${requestScope.dataMap.AVG_WRITE_BOARD}</span><span>개</span></div>
	      		</div>
	      		
	      		<div class="row pt-2">
	      			<div class="col-6 pl-2 border-right">하루평균 접속량</div>
	      			<div class="col-6 pl-2"><span style="font-weight:bold;">${requestScope.dataMap.AVG_VISIT}</span></div>
	      		</div>
	      	</div>
	      </div>
	    </div>
	    <div class="col-md-12 col-lg-6">
	      <div class="card shadow mb-4">
	      	<div class="card-header">
	      	  <h6 class="m-0 font-weight-bold text-primary">유입도메인</h6>
	      	</div>
	      	<div class="card-body">
	      		<table class="table table-hover border-bottom">
				 <thead>
				   <tr>
				      <th class="text-center">유입도메인</th>
				      <th class="text-center">유입횟수</th>
				   </tr>
				 </thead>
				 <tbody>
				   <c:forEach var="visit" items="${requestScope.visitMap}">
				   <tr style="cursor:pointer;" onclick="location.href='${visit.VISIT_REFER}'">
				      <td>
				      	<c:if test="${visit.VISIT_REFER.length() > 35}">
				      		${visit.VISIT_REFER.substring(0,35)}
				      	</c:if>
				      	<c:if test="${visit.VISIT_REFER.length() <= 35}">
				      		${visit.VISIT_REFER}
				      	</c:if>
				      </td>
				      <td>${visit.VISIT_CNT}</td>
				   </tr>
				   </c:forEach>
				 </tbody> 
				</table>
				<div class="d-flex justify-content-between align-items-center py-2 my-1">
					<div class="mt-1 pl-2" style="font-size:16px; font-weight:bold;">총 ${requestScope.totalVisit}개의 유입 경로</div>
					<div id="btn_more" style="color:#208EC9; cursor:pointer;" data-toggle="modal" data-target="#moreModal">더보기</div>
				</div>
	      	</div>
	      </div>
	     </div>
      </div>
      <%-- Content Row --%>
      <div class="row">
      	<%-- Chart1 시작--%>
        <div class="col-md-12 col-lg-6">
	      <div class="card shadow mb-4">
	      	<div class="card-header">
	      		<%-- 차트제목 --%>
	      		<h6 class="m-0 font-weight-bold text-primary">이번달 일자별 회원가입 수</h6>
	      	</div>
	      	<div class="card-body">
	      		<div class="chart-area">
                    <canvas id="joinMemberChart"></canvas>
                </div>
	      	</div>
	      </div>
	    </div>
	    <%-- Chart1 끝 --%>
	    
	    
	    <%-- Chart2 시작--%>
        <div class="col-md-12 col-lg-6">
	      <div class="card shadow mb-4">
	      	<div class="card-header">
	      		<%-- 차트제목 --%>
	      		<h6 class="m-0 font-weight-bold text-primary">접속브라우저 비중</h6>
	      	</div>
	      	<div class="card-body">
	      		<div class="chart-pie pt-4">
                    <canvas id="BrowserChart"></canvas>
                </div>
	      		<div class="mt-4 text-center small">
                   <span class="mr-2">
                       <i class="fas fa-circle text-success"></i> Chrome
                   </span>
                   <span class="mr-2">
                       <i class="fas fa-circle text-primary"></i> Edge
                   </span>
                   <span class="mr-2">
                       <i class="fas fa-circle text-info"></i> Firefox
                   </span>
                   <span class="mr-2">
                       <i class="fas fa-circle text-warning"></i> IE
                   </span>
                   <span class="mr-2">
                       <i class="fas fa-circle text-danger"></i> Safari
                   </span>
                   <span class="mr-2">
                       <i class="fas fa-circle text-gray"></i> Opera
                   </span>
               </div>
	      		
	      	</div>
	      </div>
	    </div>
	    <%-- Chart2 끝 --%>
	  </div>
	  <%-- End of Row --%>
	  
	  
	  
	  <%------------------------------------ --%>
	  
	  <%-- Content Row --%>
      <div class="row">
      	<%-- Chart3 시작--%>
        <div class="col-md-12 col-lg-6">
	      <div class="card shadow mb-4">
	      	<div class="card-header">
	      		<%-- 차트제목 --%>
	      		<h6 class="m-0 font-weight-bold text-primary">일반회원/교육기관회원 비중</h6>
	      	</div>
	      	<div class="card-body">
	      		<div class="chart-pie pt-4">
                    <canvas id="memberRateChart"></canvas>
                </div>
                <div class="mt-4 text-center small">
                   <span class="mr-2">
                       <i class="fas fa-circle text-danger"></i> 일반회원
                   </span>
                   <span class="mr-2">
                       <i class="fas fa-circle text-warning"></i> 교육기관회원
                   </span>
                </div>
	      	</div>
	      </div>
	    </div>
	    <%-- Chart3 끝 --%>
	    
	    
	    <%-- Chart4 시작--%>
        <div class="col-md-12 col-lg-6">
	      <div class="card shadow mb-4">
	      	<div class="card-header">
	      		<%-- 차트제목 --%>
	      		<h6 class="m-0 font-weight-bold text-primary">차트4제목</h6>
	      	</div>
	      	<div class="card-body">
	      	
	      		
	      	</div>
	      </div>
	    </div>
	    <%-- Chart4 끝 --%>
	  </div>
	  <%-- End of Row --%>
   </div>
</div>


<%--Modal --%>
<div class="modal fade" id="moreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
		<h4 class="modal-title" id="myModalLabel">유입경로</h4>
		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
      </div>
      <div class="modal-body">
		<%-- 데이터테이블 유입경로 --%>
        <table class="table table-bordered mt-3" id="dataTable-refer" width="100%">
           <thead>
               <tr>
                 <th>유입도메인</th>
                 <th>유입횟수</th>
               </tr>
           </thead>
           <tbody>
           	   <c:forEach var="visit" items="${requestScope.totalVisitMap}">
				   <tr style="cursor:pointer;" onclick="location.href='${visit.VISIT_REFER}'">
				      <td>
				      	<c:if test="${visit.VISIT_REFER.length() > 35}">
				      		${visit.VISIT_REFER.substring(0,35)}
				      	</c:if>
				      	<c:if test="${visit.VISIT_REFER.length() <= 35}">
				      		${visit.VISIT_REFER}
				      	</c:if>
				      </td>
				      <td>${visit.VISIT_CNT}</td>
				   </tr>
			   </c:forEach>
           </tbody>
        </table>
      </div>
      <div class="modal-footer">
		<button type="button" class="btn btn-light border rounded" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
    
    
    
