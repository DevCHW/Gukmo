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