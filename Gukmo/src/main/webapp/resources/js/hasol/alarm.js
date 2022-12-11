// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}



$(document).ready(function(){
	
	$(".alarm_cnt").hide();
	//showAlarmCnt();
	
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
function showAlarmCnt(){
	
	location.href=getContextPath()+'/showAlarmCnt.do';

	/*$.ajax({
		url:getContextPath()+'/showAlarmCnt.do',
		dataType:"json",
		success:function(json){
			const noReadAlarmCnt = json.noReadAlarmCnt;
			
			if(noReadAlarmcnt != null) {
				$(".alarm_cnt").val(noReadAlarmCnt);
				$(".alarm_cnt").show();
			}
			
		},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});*/
	
}


// 알람 리스트 가져오기
function getAlarmList(){
	
	$.ajax({
		url:getContextPath()+'/getNotAlarmList.do',
		dataType:"json",
		success:function(json){

			let html = "";
			
			if(json.length>0) {
				
				$.each(json,function(index,item){					
					
					const subject = item.subject;
					const cmt_content = item.cmt_content;
					const isread = item.isread;
					
					// 글 제목이 13자 넘을 경우, 
					if(subject != null && subject.length > 13){
						subject = subject.substring(0, 13) + "...";
					}
					
					// 댓글이 13자 넘을 경우,
					if(comment != null && comment.length > 13){
						cmt_content = comment.substring(0,13) + "...";
					}
					
					// 확인한 적 있는 알림 표시
					if(isread.equals("y")){
						html += "<div class='alarm_content' style='background-color:#BFEBFD;'>";
					}
					else {
						html += "<div class='alarm_content'>";
					}
					html += "<div class='alarm_content'">
								"<div class='alarm_info'>" +
								"<span class='like'>"+ item.cmd +"</span>" +
								"<span>"+ item.alarm_date +"</span>" +
							"</div>";
					
					// 게시글에 댓글, 좋아요, 신고
					if(item.cmd == 'reply') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.alarm_board_num+ "' > [" +subject+ "] 글에 댓글이 달렸습니다.</a>" +
								"<input id='alarm_num' type='hidden' value='"+ item.alarm_num +"'>";
					}
					
					if(item.cmd == 'like'){
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.alarm_board_num+ "' > [" +subject+ "] 글이 좋아요를 받았습니다.</a>" +
								"<input id='alarm_num' type='hidden' value='"+ item.alarm_num +"'>";			
					}
					
					if(item.cmd == 'penalty') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.alarm_board_num+ "' > [" +subject+ "] 글에 신고가 접수되었습니다.</a>" +
								"<input id='alarm_num' type='hidden' value='"+ item.alarm_num +"'>";			
					}
					
					// 댓글에 댓글, 좋아요, 신고
					if(item.cmd == 'recomment') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.cmt_content+ "' > [" +cmt_content+ "] 댓글에 댓글이 달렸습니다.</a>" +
								"<input id='alarm_num' type='hidden' value='"+ item.alarm_num +"'>";			
					}
					
					if(item.cmd == 'commentLike') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.alarm_board_num+ "' > [" +cmt_content+ "] 댓글이 좋아요를 받았습니다.</a>" +
								"<input id='alarm_num' type='hidden' value='"+ item.alarm_num +"'>";		
					}
					
					if(item.cmd == 'commentPenalty') {
						html += "<a href='<%=ctxPath =%>/detail.do?boardNum=" +item.alarm_board_num+ "' > [" +cmt_content+ "] 댓글에 신고가 접수되었습니다.</a>" +
								"<input id='alarm_num' type='hidden' value='"+ item.alarm_num +"'>";			
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
function isread(){
	$("div.div_alarm_content").css.remove();
	
	$.ajax({
		url:getContextPath()+"/changeIsRead.do'",
		data:{"alarm_num" : $("input#alarm_num").val() },
			 // "alarm_board_num"			 
		dataType:"json",
		success:function(json){},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
	
}
