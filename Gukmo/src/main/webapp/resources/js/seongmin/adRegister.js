// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
  
	
	
  $("#showPeriodInput").hide();

  $('select[name="penalty_period"]').change(function() {
    var result = $("select[name=penalty_period]").val();
    if (result == 'type6') {
      $('#showPeriodInput').show();
      alert("데이터 피커 등록해서 날짜 선택하도록");
    } else {
      $("#showPeriodInput").hide();

    }

  }); 

  
});//end of $(document).ready(function(){})---

function adWrite() {
	
	// 클라이언트명 입력하지 않았을 때
	if($("input#client_name").val().trim() == "") {
		$("input#client_name").focus();
	    $("div#client_name_error").text("클라이언트 명을 입력하세요.");
	    $("div#client_name_error").css('color','red');
	    return false;
	}
	
	else {
	    $("div#client_name_error").text("");
	    $("div#client_name_error").css('color','');		
	}
		
	// 클라이언트 번호 입력하지 않았을 때
	if($("input#client_phone").val().trim() == "") {
		$("input#client_phone").focus();
	    $("div#client_phone_error").text("클라이언트 번호를 입력하세요.");
	    $("div#client_phone_error").css('color','red');
	    return false;
	}
	else {
	    $("div#client_phone_error").text("");
	    $("div#client_phone_error").css('color','');		
	}
		
	// 파일명 입력하지 않았을 때
	if($("input#file_name").val().trim() == "") {
		$("input#file_name").focus();
	    $("div#file_name_error").text("파일명을 입력하세요.");
	    $("div#file_name_error").css('color','red');
	    return false;
	}
	else {
	    $("div#file_name_error").text("");
	    $("div#file_name_error").css('color','');		
	}
		
	// url 주소 입력하지 않았을 때
	if($("input#url").val().trim() == "") {
		$("input#url").focus();
	    $("div#url_error").text("URL 주소를 입력하세요.");
	    $("div#url_error").css('color','red');
	    return false;
	}
	else {
	    $("div#url_error").text("");
	    $("div#url_error").css('color','');		
	}
		
	// 광고기간 선택하지 않았을 때
	if($("select#period").val() == "") {
	    $("div#period_error").text("광고 게시 기간을 선택하세요.");
	    $("div#period_error").css('color','red');
	    return false;
	}
	else {
	    $("div#period_error").text("");
	    $("div#period_error").css('color','');		
	}
	
			
	
	var bool = confirm("광고를 등록하시겠습니까?");
	if(bool) {
		alert("광고 등록이 완료되었습니다.");
		var frm = document.adForm;
		frm.method = "post";
		frm.action = getContextPath()+"/admin/adRegisterResult.do";
		frm.submit();
	}
	else {
		return;
	}
		
}


// =========================== Function Declaration =========================== //






