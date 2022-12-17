<!DOCTYPE html>
<html lang='en'>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<script src='<%=ctxPath %>/resources/fullcalendar-6.0.0/dist/index.global.js'></script>
<script>

    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
          headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
          },
          locale:"ko",
          navLinks: true, // can click day/week names to navigate views
          businessHours: true, // display business hours
          editable: true,
          selectable: true,
          events: [
            {
              title: 'Business Lunch',
              start: '2022-12-03T13:00:00',
              constraint: 'businessHours'
            },
            {
              title: 'Meeting',
              start: '2022-12-13T11:00:00',
              constraint: 'availableForMeeting', // defined below
              color: '#257e4a'
            },
            {
              title: 'Conference',
              start: '2022-12-18',
              end: '2022-12-22'
            },
            {
              title: 'Party',
              start: '2022-12-29T20:00:00'
            },

            // areas where "Meeting" must be dropped
            {
              groupId: 'availableForMeeting',
              start: '2022-12-11T10:00:00',
              end: '2022-12-11T16:00:00',
              display: 'background'
            },
            {
              groupId: 'availableForMeeting',
              start: '2022-12-13T10:00:00',
              end: '2022-12-13T16:00:00',
              display: 'background'
            },

            // red areas where no events can be dropped
            {
              start: '2022-12-24',
              end: '2022-12-28',
              overlap: false,
              display: 'background',
              color: '#ff9f89'
            },
            {
              start: '2022-12-06',
              end: '2022-12-09',
              overlap: false,
              display: 'background',
              color: '#ff9f89'
            }
          ]
        });

        calendar.render();
      });

</script>


<%-- Main Content --%>
<div id="content">
  <%-- Begin Page Content --%>
  <div class="container-fluid">
  	  <%-- Page Heading --%>
      <h1 class="h3 my-2 text-gray-800">일정 관리 </h1>
      <p class="mb-4">일정 관리</p>
      
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