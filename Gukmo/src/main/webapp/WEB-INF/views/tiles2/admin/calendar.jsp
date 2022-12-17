<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>   
    
    
<link href='<%=ctxPath %>/resources/css/hyunwoo/admin/fullcalendar.main.min.css' rel='stylesheet' />
<script src='<%=ctxPath %>/resources/js/hyunwoo/admin/fullcalendar.main.min.js'></script>
<script src='<%=ctxPath %>/resources/js/hyunwoo/admin/ko.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bPopup/0.11.0/jquery.bpopup.min.js"></script>
<%-- 직접만든 script --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/calendar.js" ></script>
    

<%-- Main Content --%>
<div id="content">
  <%-- Begin Page Content --%>
  <div class="container-fluid">
  	  <%-- Page Heading --%>
      <h1 class="h3 my-2 text-gray-800">국모 관리팀 일정</h1>
      <p style="margin:0; padding:0;">국비의모든것 관리팀 일정 페이지입니다. 구글캘린더와 연동하여 구글에서도 편집 가능합니다.</p>
      <a class="pt-2" style="font-size:12px;" href="https://calendar.google.com/calendar/embed?src=d638d8c280f4a55501b5e8b5860e96bd6fdd60dde6d8212421ff6a8e7f56169c%40group.calendar.google.com&ctz=Asia%2FSeoul">구글캘린더 바로가기 </a>
      <div class="card shadow mb-4 mt-4">
     	<div class="card-body">
     		<div id='calendar'></div>
			<div id='popup' style="width:50%; height:450px; display:none;"></div>
		</div>
	  </div>
  </div>
</div>