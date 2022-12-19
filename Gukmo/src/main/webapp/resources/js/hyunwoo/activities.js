// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

/**
 * js 파일은 전부 필드선언 - 이벤트(document.ready) - 함수선언 의 구성으로 이루어져 있음
 */

//== Field Declaration == //



//== Event Declaration == //
$(document).ready(function(){
	
	$("div#view_activities").css("color","#ffff00");
	$("div#view_activities").css("font-weight","bold");
	
	
	//네비바에서 활동내역 클릭시 이벤트
	$("div#view_activities").click(function(){
		$("div#view_activities").css("color","#ffff00");
		$("div#view_activities").css("font-weight","#ffff00");
		$("div#view_alarm").css("color","");
		$("div#view_alarm").css("font-weight","");
		
		$("div#alarm").hide();	//알람 감추기
		$("div#activities").show();	//활동내역 보이기
		
	});//end of Event--
	
	
	//네비바에서 알림 클릭시 이벤트
	$("div#view_alarm").click(function(){
		$("div#view_activities").css("color","");
		$("div#view_activities").css("font-weight","");
		$("div#view_alarm").css("color","#ffff00");
		$("div#view_alarm").css("font-weight","bold");
		
		$("div#activities").hide();	//활동내역 감추기
		$("div#alarm").show();	//알람 보이기
		viewAlarm(1);
	});//end of Event--
	
});//end of $(document).ready(function(){})--



//== Function Declaration ==//

function goDetailCategory(detail_category){
	switch (detail_category) {
	case '국비학원':
		location.href = getContextPath()+"/academy/academies.do";
		break;
	case '교육기관':
		location.href = getContextPath()+"/academy/curricula.do";
		break;
	case '자유게시판':
		location.href = getContextPath()+"/community/freeBoards.do";
		break;
	case 'QnA':
		location.href = getContextPath()+"/community/questions.do";
		break;
	case '스터디':
		location.href = getContextPath()+"/community/studies.do";
		break;
	case '취미모임':
		location.href = getContextPath()+"/community/hobbies.do";
		break;
	case '수강/취업후기':
		location.href = getContextPath()+"/community/reviews.do";
		break;
	case '공지사항':
		location.href = getContextPath()+"/notices.do";
		break;

	}//end of switch-case--
}



/**
 * 알림영역 보이게 만들기
 */
function viewAlarm(currentPageNo){
	
	// console.log("와?");
	// console.log(currentPageNo);
	
	$.ajax({
		url:getContextPath()+"/member/getAlarmList.do",
		data: {"currentPageNo" : currentPageNo,
			   "checkTab" : "alarm" },
		type:"GET",
		dataType:"json",
		success:function(json){		
			
			console.log(json.alarmList);
			
			let html = "";
			const message = json.message;

			if(message != null){ // 조회된 값이 없다는 문구가 있을 경우
				$("div#alarmList").html(message);
			}
			else if(message == null && json.alarmList.length > 0 ){ // 정상적으로 알람 값이 조회됐을 경우
				
				$.each(json.alarmList, function(index, item){
					
					let content = item.content;
					
					// 글 제목이 13자 넘을 경우, 
					if(content != null && content.length > 10){
						content = content.substring(0, 10) + "...";
					}
					
					
					if(item.isread == 'y') {
						html += "<div class='activity_box border-top border-bottom px-2 py-4' style='background-color:#FAFAFA;'>";	
					}
					else {
						html += "<div class='activity_box border-top border-bottom px-2 py-4'>";	
					}
					
					html += "<div class='activity_title alarm_title'>"+
    							"<div class='d-flex w-100 justify-content-between align-items-center'>";
					
					
					// 게시글에 댓글, 좋아요, 신고
					if(item.cmd == 'reply') { 
	            		html += "<div class='d-flex align-items-center'>" +
	            				"	<div class='detail_category border rounded-pill px-2 py-1'>댓글</div>"+
		                	 		"	<div class='activity_content alarm_content ml-2' onClick='goRead("+item.url_num+","+item.alarmno+")'>";
     					
	            		// 읽은 알람일 경우 디자인 
	            		if(item.isread =='y') {
     						html += "<span style='font-weight:500;'> [" +content+ "] </span>";
     					}
     					else{
     						html += "<span style='color:black; font-weight:500;'> [" +content+ "] </span>";
     					}
	                 					
	                 	html += "<span>글에 댓글이 달렸습니다.</span>" +
	                 			"</div>";				
	         			
					}
					
					if(item.cmd == 'like') { 
	            		html += "<div class='d-flex align-items-center'>" +
        						"	<div class='detail_category border rounded-pill px-2 py-1'>좋아요</div>"+
            	 				"		<div class='activity_content alarm_content ml-2' onClick='goRead("+item.url_num+","+item.alarmno+")'>";
				
			    		// 읽은 알람일 경우 디자인 
			    		if(item.isread =='y') {
								html += "<span style='font-weight:500;'> [" +content+ "] </span>";
							}
							else{
								html += "<span style='color:black; font-weight:500;'> [" +content+ "] </span>";
							}
			         					
			         	html += "<span>글이 좋아요를 받았습니다.</span>"+				
			         			"</div>";
					}
					
					if(item.cmd == 'penalty') { 
	            		html += "<div class='d-flex align-items-center'>" +
								"	<div class='detail_category border rounded-pill px-2 py-1'>신고</div>"+
            	 				"		<div class='activity_content alarm_content ml-2' onClick='goRead("+item.url_num+","+item.alarmno+")' >";
				
			    		// 읽은 알람일 경우 디자인 
			    		if(item.isread =='y') {
								html += "<span style='font-weight:500;'> [" +content+ "] </span>";
							}
							else{
								html += "<span style='color:black; font-weight:500;'> [" +content+ "] </span>";
							}
			         					
			         	html += "<span>글에 신고가 접수되었습니다.</span>" +
			         			"</div>";
	         			
					}			

	
										
					// 댓글에 댓글, 좋아요, 신고
					if(item.cmd == 'recomment') {
	            		html += "<div class='d-flex align-items-center'>" +
								"	<div class='detail_category border rounded-pill px-2 py-1'>댓글</div>"+
								"		<div class='activity_content alarm_content ml-2' onClick='goRead("+item.url_num+","+item.alarmno+")' >";
				
			    		// 읽은 알람일 경우 디자인 
			    		if(item.isread =='y') {
								html += "<span style='font-weight:500;'> [" +content+ "] </span>";
							}
							else{
								html += "<span style='color:black; font-weight:500;'> [" +content+ "] </span>";
							}
			         					
			         	html += "<span> 댓글에 댓글이 달렸습니다.</span>" +
			         			"</div>";
			         			
					}	
										
					if(item.cmd == 'cmtLike' || item.cmd == 'cmt_cmtLike') {
	            		html += "<div class='d-flex align-items-center'>" +
								"	<div class='detail_category border rounded-pill px-2 py-1'>좋아요</div>"+
    	 						"		<div class='activity_content alarm_content ml-2' onClick='goRead("+item.url_num+","+item.alarmno+")' >";
				
			    		// 읽은 알람일 경우 디자인 
			    		if(item.isread =='y') {
								html += "<span style='font-weight:500;'> [" +content+ "] </span> ";
							}
							else{
								html += "<span style='color:black; font-weight:500;'> [" +content+ "] </span>";
							}
			         					
			         	html += "<span> 댓글이 좋아요를 받았습니다.</span>" +
	         					"</div>";			
	         						
					}	
					
					if(item.cmd == 'cmtPenalty') {
	            		html += "<div class='d-flex align-items-center'>" +
								"	<div class='detail_category border rounded-pill px-2 py-1'>좋아요</div>"+
    	 						"		<div class='activity_content alarm_content ml-2' onClick='goRead("+item.url_num+","+item.alarmno+")' >";
				
			    		// 읽은 알람일 경우 디자인 
			    		if(item.isread =='y') {
								html += "<span style='font-weight:500;'> [" +content+ "] </span>";
							}
							else{
								html += "<span style='color:black; font-weight:500;'> [" +content+ "] </span>";
							}
			         					
			         	html += "<span>댓글에 신고가 접수되었습니다.</span>" +
     							"</div>";	
					
			         			
					}	
					
					html += "</div>";
										
		            // 활동일자
					html += 			"<div class='activity_date d-flex justify-content-end'>"+item.alarm_date+"</div>"+
		            				"</div>" +
		            			"</div>" +
		            			"<input id='alarmno' type='hidden' value='"+ item.alarmno +"'/>" +
		            		"</div>";
				});
				
			}
		    
			$("div#alarmList").html(html);

			console.log(html);
			makeAlarmPageBar(currentPageNo);
			
		},
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
}
	
	


// 알람 페이지 알아오기
function makeAlarmPageBar(currentPageNo) {
		
	  $.ajax({
		  url:getContextPath()+'/member/getAlarmCount.do',
		  data:{ "sizePerPage":"10",
			     "checkTab" : "alarm"},
	      type:"GET",
	      dataType:"JSON",
	      success:function(json){
	  
			  if(json.totalPage > 0) {
	    		  
				  const totalPage = json.totalPage;
	    		  
	    		  const blockSize = 10;

	    		  let loop = 1;

	    		  if(typeof currentPageNo == "string") {
	    			   currentPageNo = Number(currentPageNo);
	    		  }	
	    		   
	    		  let blockStart = Math.floor( (currentPageNo - 1)/blockSize ) * blockSize + 1;
	    		   
	    		  let pageBarHTML = "<ul class='pagination'>";
	    		   
	    		  if(blockStart != 1) {
					// 처음
	    			pageBarHTML  += "<li class='page-item'>" +
							   "	<a class='page-link' href='javascript:viewAlarm(\"1\")' aria-label='super_previous'>" +
							   "		<i class='fa-solid fa-angles-left'></i>"+
							   "	</a>" + 
							   "</li>";
					// 이전
	    			pageBarHTML  += "<li class='page-item'>" +
							   "	<a class='page-link' href='javascript:viewAlarm(\""+(blockStart-1)+ "\")' aria-label='previous'>" +
							   "		<i class='fa-solid fa-angle-left'></i>"+
							   "	</a>" + 
							   "</li>";
					}
					
					while (!(loop > blockSize || blockStart > totalPage)) {
						
						if(blockStart == currentPageNo) {
							// 현재 보는 페이지와 블록 시작 페이지가 같을 경우 (선택된 경우)
							pageBarHTML  += "<li class='page-item disabled'>" +
									   "	<a class='page-link'>" +blockStart+ "</a>"+
									   "</li>";
						}
						else {
							// 현재 보는 페이지와 블록 시작 페이지가 다른경우(선택되지 않은 경우)
							pageBarHTML  += "<li class='page-item'>" +
									   "	<a class='page-link' href='javascript:viewAlarm(\""+blockStart+ "\")'>" +blockStart+ "</a>"+
									   "</li>";
						}
						
						loop++;
						blockStart++;
					}
					
					// 다음과 마지막 만들기		
					if(blockStart <= totalPage) {
						pageBarHTML  += "<li class='page-item'>" +
								   "	<a class='page-link' href='javascript:viewAlarm(\""+blockStart+ "\")' aria-label='next'>" +
								   "		<i class='fa-solid fa-angle-right'></i>"+
								   "	</a>" + 
								   "</li>";
						
						pageBarHTML  += "<li class='page-item'>" +
								   "	<a class='page-link' href='javascript:viewAlarm(\""+totalPage+ "\")' aria-label='super-next'>" +
								   "		<i class='fa-solid fa-angles-right'></i>"+
								   "	</a>" + 
								   "</li>" ;
					}
	
					pageBarHTML +="</ul>" +
							
					$("nav#pageBar").html(pageBarHTML);
				  }
		  },
	      error: function(request, status, error){
			  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
	  });
	
}//end of method---



//읽음 컬럼 값 변경
function goRead(url_num, alarmno){

	// console.log("하하");
	//alert("alarmno:" + alarmno);
	//alert("url_num:" + url_num);
	$.ajax({
		url:getContextPath()+"/changeIsRead.do",
		type:"post",
		data:{"alarmno" : alarmno, // alarmno 이라 수정 필요!
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
	

	
}//end of method---





