<!DOCTYPE html>
<html lang='en'>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<script src='<%=ctxPath %>/resources/fullcalendar-6.0.0/dist/index.global.js'></script>
<script type="text/javascript">

$(document).ready(function(event){
	
	$(document).on("click",".fc-event-title-container",function() {
		var bool = confirm("해당 광고 상세보기 페이지로 이동하시겠습니까?");		
		if(bool){
			console.log("go");
		}

		else {
			return false;
		}
	});
	
	
	var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      locale:"ko",
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      selectable: true,
  	  events:function(info, successCallback, failureCallback) {
	   	  $.ajax({
	         url: "<%= ctxPath%>/admin/view_AdSchedule.do",
 			 type: "post",
	         dataType:"json",
	         success:function(json) {
	        	 var events = [];
	        	 if(json.length > 0){
	        		 $.each(json, function(index, item) {
          			   events.push({
                              title: item.title,
                              start: item.start,
                              end: item.end,
                              url: '<%= ctxPath%>/admin/advertisement/detail.do?advertisement_num='+item.advertisement_num
                 		}); // end of events.push({})---------           		
	        		 }) //end of $.each
	        	 }//end of if
	        	successCallback(events);
				
			 },// end of success			
			 error: function(request,error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			 }
			 
		  });// end of $.ajax({})---
		  
  	    } //end of function function(info, successCallback, failureCallback)
      
    });
      
    calendar.render();

});
		
function schedule_click() {
	
}	
	

</script>


<%-- Main Content --%>
<div id="content">
  <%-- Begin Page Content --%>
  <div class="container-fluid">
  	  <%-- Page Heading --%>
      <h1 class="h3 my-2 text-gray-800">광고일정</h1>
      <p class="mb-4">광고일정에 맞게 광고상태를 바꿔주세요.</p>
      
      <div class="card shadow mb-4">
     	<div class="card-body">

     		<div id='calendar'>
     			<div id="btn_add_schedule">
     				<button type="button" class="btn add_sche" onclick="">일정 추가</button>
     			</div>
     		</div>
		</div>
	  </div>
  </div>
</div>
</html>
