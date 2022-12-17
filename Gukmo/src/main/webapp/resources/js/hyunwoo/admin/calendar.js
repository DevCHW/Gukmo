document.addEventListener('DOMContentLoaded', function() {
var calendarEl = document.getElementById('calendar');
var calendar = new FullCalendar.Calendar(calendarEl, {
	  locale: "ko",
	  initialView: 'dayGridMonth',
	  headerToolbar: {
		left: 'prev,next today',
		center: 'title',
		right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
	  },
	  googleCalendarApiKey: 'AIzaSyBpC8_2VYQVtarfCqcjkVk-qcT9YKb_Z_o',
	  events: {
	  googleCalendarId: 'd638d8c280f4a55501b5e8b5860e96bd6fdd60dde6d8212421ff6a8e7f56169c@group.calendar.google.com',
	  className: 'gcal-event' // an option!
	  },
	  eventClick: function(info) {
		  let start_year = info.event.start.getUTCFullYear();
		  let start_month = info.event.start.getMonth() + 1;
		  let start_date = info.event.start.getUTCDate();
		  let start_hour = info.event.start.getHours();
		  let start_minute = info.event.start.getMinutes();
		  let start_second = info.event.start.getSeconds();
		  let end_hour = info.event.end.getHours();

		  let start = start_year + "년" + start_month + "월" + start_date + "일<br>" + start_hour + "시 ~ " + end_hour + "시";

		  let attends = "";
		  info.event.extendedProps.attachments.forEach(function(item) {
			  attends += "<button class='btn btn-info rounded' onclick=location.href='"+item.fileUrl+"'>첨부파일</button>";
		  });

		  if(info.event.extendedProps.description === undefined || info.event.extendedProps.description == ""
			  || typeOf(info.event.extendedProps.description) == "undefined") {
			  info.event.extendedProps.description = "내용이 없습니다.";
		  }
		  let contents = `
			<div class="m-auto w-100" style="height:100%;">
			  <div class="card shadow mb-4 h-100">
			  	<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
			  	  <h6 class="m-0 font-weight-bold text-primary">${info.event.title}</h6>
			  	</div>
			  	<div class="card-body">
			  	  <p>${start}</p>
			  	  ${info.event.extendedProps.description}
				  
			  	</div>
			  	<div class="card-footer">
			  	  ${attends}
			  	</div>
			  </div>
			</div>
		  `;
		  
		  $("#popup").html(contents);
		  $("#popup").bPopup({
			speed: 300,
			transition: 'fadeIn',
			transitionClose: 'fadeOut',
			position: [($(document).width()-500)/2, 30] //x, y
			  });
			  info.jsEvent.stopPropagation();
			  info.jsEvent.preventDefault();
		  }
	});
	calendar.render();
});