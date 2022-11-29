// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
  
  $("#showPeriodInput").hide();

  $('select[name="penalty_period"]').change(function() {
	if($("select#penalty_period").val() == "") {
	    $("div#penalty_period_error").text("정지 기간을 선택하세요.");
	    $("div#penalty_period_error").css('color','red');
	    return false;
	}
	
	else {
	    $("div#penalty_period_error").text("");
	    $("div#penalty_period_error").css('color','');		
	}
  }); 

});//end of $(document).r eady(function(){})---


function penaltyWrite() {
	
	if($("textarea#detail_penalty_reason").val().trim() == "") {
		$("textarea#detail_penalty_reason").focus();
	    $("div#detail_penalty_reason_error").text("상세한 정지 사유를 입력하세요.");
	    $("div#detail_penalty_reason_error").css('color','red');
	    return false;
	}
	else {
	    $("div#detail_penalty_reason_error").text("");
	    $("div#detail_penalty_reason_error").css('color','');		
	}
	
	if($("select#penalty_period").val() == "") {
	    $("div#penalty_period_error").text("정지 기간을 선택하세요.");
	    $("div#penalty_period_error").css('color','red');
	    return false;
	}
	
	else {
	    $("div#penalty_period_error").text("");
	    $("div#penalty_period_error").css('color','');		
	}
	
	
	
	var bool = confirm("정지 등록하시겠습니까?");
	if(bool) {
		alert("정지 등록이 완료되었습니다.");
		var frm = document.penaltyForm;
		frm.method = "post";
		frm.action = getContextPath()+"/admin/penaltyRegisterResult.do";
		frm.submit();
	}
	else {
		return;
	}
}


// =========================== Function Declaration =========================== //






