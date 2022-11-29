// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
	   var nickname = $("input[name='reported_nickname']").attr('id');
	   var userid = $("input[name='reportedId']").attr('id');
	   
	   
	   $(document).on("click", ".memberBlock", function(){
		   block(nickname, userid);
	   });

	   
}); //end of ready

//Function Declaration


function block(nickname, userid) {
  var bool = confirm("정지 등록 페이지로 이동하시겠습니까?");
  if( bool == true) {
    location.href="penaltyRegister.do?userid="+userid+"&nickname="+nickname;
  } //end of if

  else {
    return false;
  }
} //end of block()




