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
	
	
	//네비바에서 알림 클릭시
	$("div#view_alarm").click(function(){
		$("div#view_activities").css("color","");
		$("div#view_activities").css("font-weight","");
		$("div#view_alarm").css("color","#ffff00");
		$("div#view_alarm").css("font-weight","bold");
		
		$("div#activities").hide();	//활동내역 감추기
		$("div#alarm").show();	//알람 보이기
	});//end of Event--
	
	//네비바에서 활동내역 클릭시
	$("div#view_activities").click(function(){
		$("div#view_activities").css("color","#ffff00");
		$("div#view_activities").css("font-weight","#ffff00");
		$("div#view_alarm").css("color","");
		$("div#view_alarm").css("font-weight","");
		
		$("div#alarm").hide();	//알람 감추기
		$("div#activities").show();	//활동내역 보이기
		viewAlarm();
	})//end of Event--
	
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
function viewAlarm(){
	alert("viewAlarm() 호출. activities.js 파일에서 이 메소드 부분 코딩하면 됩니다 파일 맨아래보면있어유.");
	//html은 activities.jsp파일에서 div#alarm 안쪽에 코딩하면 됨 
	
}//end of method---






