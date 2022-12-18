// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}



$(document).ready(function(){
	
	getNotReadAlarm_count();
	
	///////////// 알림 토글 시작 /////////////////	
	$('.alarm_drop').click(function(e){
	    e.stopPropagation();
	     $("#alarm_dropContent").toggle();
	     $("#profile_dropContent").hide();
	     $("#admin_dropContent").hide();
	     
	     getNotReadAlarmList();
	});

	$("#alarm_dropContent").on("click", function (event) {
	    event.stopPropagation();
	});     
	///////////// 알림 토글 끝 /////////////////

	// 각 알림을 읽었을 경우

});


// 읽지 않은 알람 카운트 보여주기
function getNotReadAlarm_count(){
	
	$.ajax({
		url:getContextPath()+"/getNotReadAlarm_count.do",
		type:"POST",
		dataType:"json",
		success:function(json){
			const notReadAlarmCnt = json.notReadAlarmCnt;
			// console.log(notReadAlarmCnt)
			if(notReadAlarmCnt == null || notReadAlarmCnt == '0') {
				// 헤더 그 자체
				$("span#alarm_cnt").hide();
				
				// 알림 창 안		
				$("span#alarm_cnt2").hide();
			}
			else {
				// 헤더 그 자체
				$("span#alarm_cnt").text(notReadAlarmCnt);
				$("span#alarm_cnt").show();
				
				// 알림 창 안
				$("span#alarm_cnt2").text(notReadAlarmCnt);
				$("span#alarm_cnt2").show();
				
			}	
		},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
	
}



// 알람 리스트 가져오기
function getNotReadAlarmList(){
	
	// console.log("와?");
	
	$.ajax({
		url:getContextPath()+'/getNotReadAlarmList.do',
		type:"POST",
		dataType:"json",
		success:function(json){

			console.log(json);
			let html = "";
			
			if(json.notReadAlarmList.length>0) {
				// console.log("여긴 와야하는 디 ");
				$.each(json.notReadAlarmList,function(index,item){					
					
					// console.log("여기는?");
					let content = item.content;
					let isread = item.isread;
					
					// 글 제목이 13자 넘을 경우, 
					if(content != null && content.length > 10){
						content = content.substring(0, 10) + "...";
					}

					html += "<div class='alarm_content' onClick='goRead("+item.url_num+")'>" +							
								"<div class='alarm_info'>";
					
					// 게시글에 댓글, 좋아요, 신고
					if(item.cmd == 'reply') {
						html += 	"<span class='reply'>댓글</span>" +
									"<span>"+ item.alarm_date +"</span>" +
								"</div>"+
								"<p class='alarm_text' id="+item.url_num+"> [" +content+ "] 글에 댓글이 달렸습니다.</p>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";
						// console.log("여기는:");
								
					}
					
					if(item.cmd == 'like'){
						html +=  	"<span class='like'>좋아요</span>" +
									"<span>"+ item.alarm_date +"</span>" +
								"</div>"+
								"<p class='alarm_text' id="+item.url_num+"> [" +content+ "] 글이 좋아요를 받았습니다.</p>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}
					
					if(item.cmd == 'penalty') {
						html +=   	"<span class='penalty'>신고</span>" +
									"<span>"+ item.alarm_date +"</span>" +
								"</div>"+
								"<p class='alarm_text' id="+item.url_num+"> [" +content+ "] 글에 신고가 접수되었습니다.</p>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}
					
					// 댓글에 댓글, 좋아요, 신고
					if(item.cmd == 'recomment') {
						html +=  	"<span class='reply'>댓글</span>" +
									"<span>"+ item.alarm_date +"</span>" +
								"</div>"+
								"<p class='alarm_text' id="+item.url_num+"> [" +content+ "] 댓글에 댓글이 달렸습니다.</p>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}
					
					if(item.cmd == 'cmtLike' || item.cmd == 'cmt_cmtLike') {
						html +=   	"<span class='like'>좋아요</span>" +
									"<span>"+ item.alarm_date +"</span>" +
								"</div>"+
								"<p class='alarm_text' id="+item.url_num+">  [" +content+ "] 댓글이 좋아요를 받았습니다.</p>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";		
					}
					
					if(item.cmd == 'cmtPenalty') {
						html += 	"<span class='penalty'>신고</span>" +
									"<span>"+ item.alarm_date +"</span>" +
								"</div>"+
								"<p class='alarm_text' id="+item.url_num+"> [" +content+ "] 댓글에 신고가 접수되었습니다.</p>" +
								"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'>";			
					}

					html += "</div>";
					
				});
			}
			else{
				html="<p style='margin:auto;'> 조회된 최신 알람이 없습니다. </p>"
			}
			
			//console.log(html);
			//console.log("흠냐링");
			
			$("div.div_alarm_content").html(html);

		},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
	
}

// 읽음 컬럼 값 변경
function goRead(url_num){

	// console.log("하하");
	// console.log("url_num:" + url_num);
	$.ajax({
		url:getContextPath()+"/changeIsRead.do",
		type:"post",
		data:{"alarmno" : $("input#alarmno").val(), // alarmno 이라 수정 필요!
			  "url_num" : url_num
		},
		dataType:"json",
		success:function(json){
			if(json){
				location.href = getContextPath()+'/detail.do?boardNum='+ url_num;
			}else{
				alert("업데이트에 실패하였습니다 다시 시도해주세요.");
			}
		
		},
		error: function(request,error){	
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
	

	
}
