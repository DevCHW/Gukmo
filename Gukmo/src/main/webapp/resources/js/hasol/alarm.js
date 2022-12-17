// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}



$(document).ready(function(){
	
	getNotReadAlarm_count();
	
	///////////// 알림 토글 시작 /////////////////	
	$('.alarm_drop').click(function(event){
	    event.stopPropagation();
	     $("#alarm_dropContent").toggle();
	     $("#profile_dropContent").hide();
	     $("#admin_dropContent").hide();
	     
	     getAlarmList();
	});

	$("#alarm_dropContent").on("click", function (event) {
	    event.stopPropagation();
	});     
	///////////// 알림 토글 끝 /////////////////

});


// 읽지 않은 알람 카운트 보여주기
function getNotReadAlarm_count(){
	console.log("와?");
	
	$.ajax({
		url:getContextPath()+"/getNotReadAlarm_count.do",
		dataType:"json",
		success:function(json){
			/*const notReadAlarmCnt = json.notReadAlarmCnt;
			console.log(notReadAlarmCnt)
			if(notReadAlarmCnt != null || notReadAlarmCnt == '0') {
				$(".alarm_cnt").hide();
			}
			else {
				$(".alarm_cnt").val(notReadAlarmCnt);
				$(".alarm_cnt").show();
			}	*/		
		},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
	
}



// 알람 리스트 가져오기
function getNotAlarmList(){
	
	$.ajax({
		url:getContextPath()+'/getNotReadAlarmList.do',
		dataType:"json",
		success:function(json){

			let html = "";
			
			if(json.length>0) {
				
				$.each(json,function(index,item){					
					
					const content = item.content;
					const isread = item.isread;
					
					// 글 제목이 13자 넘을 경우, 
					if(content != null && content.length > 13){
						content = content.substring(0, 13) + "...";
					}

					html += "<div class='alarm_content'">
								"<div class='alarm_info'>" +
								"<span class='like'>"+ item.cmd +"</span>" +
								"<span>"+ item.alarm_date +"</span>" +
							"</div>";
					
					// 게시글에 댓글, 좋아요, 신고
					if(item.cmd == 'reply') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.url_num+ "' > [" +content+ "] 글에 댓글이 달렸습니다.</a>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";
					}
					
					if(item.cmd == 'like'){
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.url_num+ "' > [" +content+ "] 글이 좋아요를 받았습니다.</a>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}
					
					if(item.cmd == 'penalty') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.url_num+ "' > [" +content+ "] 글에 신고가 접수되었습니다.</a>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}
					
					// 댓글에 댓글, 좋아요, 신고
					if(item.cmd == 'recomment') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.url_num+ "' > [" +content+ "] 댓글에 댓글이 달렸습니다.</a>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}
					
					if(item.cmd == 'commentLike') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.url_num+ "' > [" +cmt_content+ "] 댓글이 좋아요를 받았습니다.</a>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";		
					}
					
					if(item.cmd == 'commentPenalty') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.url_num+ "' > [" +cmt_content+ "] 댓글에 신고가 접수되었습니다.</a>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}

					html += "</div>";
					
				});
			}
			
			$("div.div_alarm_content").html(html);

		},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
	
}

// 읽음 컬럼 값 변경
function isread(e){
	event.currentTarget.remove();
	location.href= getContextPath()+"/changeIsRead.do'";

/*	$.ajax({
		url:getContextPath()+"/changeIsRead.do'",
		data:{"alarmno" : $("input#alarmno").val() }, // alarm_num 이라 수정 필요!	 
		dataType:"json",
		success:function(json){},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});*/
	
}
