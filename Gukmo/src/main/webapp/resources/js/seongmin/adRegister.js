// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

var name_check = false;
var phone_check = false;
var file_check = false;
var url_check = false;
var period_check = false;

$(document).ready(function(){
	
	//클라이언트 명 유효성 검사
	$("input#client_name").keyup(e => {
		name_check = false;
		
		if($("input#client_name").val().trim() == "") {
			$("input#client_name").focus();
		    $("div#client_name_error").text("클라이언트 명을 입력하세요.");
		    $("div#client_name_error").css('color','red');
			name_check = false;
		    return false;
		}
		
		else if ($("input#client_name").val().trim().length > 10) {
		    $("div#client_name_error").text("클라이언트 명은 10자 이내로 입력하세요.");			
		    $("div#client_name_error").css('color','red');
			name_check = false;
		    return false;
		}
		
		else {
		    $("div#client_name_error").text("");
		    $("div#client_name_error").css('color','');		
			name_check = true;
		}	
	});
	//클라이언트 명 유효성 검사 끝 ===========
	
	// 클라이언트 번호 유효성 검사
	$("input#client_phone").keyup(e=>{
		const regExp = /[0-9]/;
		phone_check = false;
		const client_phone = $("input#client_phone").val();
		
		if($("input#client_phone").val().trim() == "") {
			$("input#client_phone").focus();
		    $("div#client_phone_error").text("클라이언트 번호를 입력하세요.");
		    $("div#client_phone_error").css('color','red');
			phone_check = false;
		    return false;
		}
		
		else if (!regExp.test(client_phone)) {
		    $("div#client_phone_error").text("클라이언트 번호는 숫자로만 입력하세요.");
		    $("div#client_phone_error").css('color','red');
			phone_check = false;
		    return false;			
		}
		
		else {
		    $("div#client_phone_error").text("");
		    $("div#client_phone_error").css('color','');		
			phone_check = true;
		}
	});
		//클라이언트 번호 유효성 검사 끝 ===========
		
	// 첨부파일 유효성 검사
/*	$("input#attach").focus(e=>{
		file_check = false;
		console.log($("input#attach").val());
		if($("input#attach").val() == "") {
		    $("div#file_name_error").text("파일을 선택하세요.");
		    $("div#file_name_error").css('color','red');
			file_check = false;
		    return false;
		}
		else {
		    $("div#file_name_error").text("");
		    $("div#file_name_error").css('color','');		
			file_check = true;
		}

	});             */
	// 첨부파일 유효성 검사 끝================
	
	// url 유효성 검사 시작
	$("input#url").keyup(e=>{
		url_check = false;
		
		if($("input#url").val().trim() == "") {
			$("input#url").focus();
		    $("div#url_error").text("URL 주소를 입력하세요.");
		    $("div#url_error").css('color','red');
			url_check = false;
		    return false;
		}
		else {
		    $("div#url_error").text("");
		    $("div#url_error").css('color','');		
			url_check = true;
		}
					
	});



}); //end of document.ready

function periodCheck() {
	if($("select#period").val() == "") {
	    $("div#period_error").text("광고 게시 기간을 선택하세요.");
	    $("div#period_error").css('color','red');
	    period_check = false;
	    return false;
	}
	else {
	    $("div#period_error").text("");
	    $("div#period_error").css('color','');		
	    period_check = true;
	}
} //end of function periodCheck()

function adWrite() {

	
	if(!name_check) {
	    $("div#client_name_error").text("클라이언트 명을 입력하세요.");
	    $("div#client_name_error").css('color','red');
		$("input#client_name").focus();	
		return false;
	}
	if(!phone_check ) {
		$("input#client_phone").focus();	
		return false;
	}
	if(!url_check) {
		$("input#url").focus();
	    $("div#url_error").text("URL 주소를 입력하세요.");
	    $("div#url_error").css('color','red');
		return false;
	}
	

	file_check = false;
	console.log($("input#attach").val());
	if($("input#attach").val() == "") {
	    $("div#file_name_error").text("파일을 선택하세요.");
	    $("div#file_name_error").css('color','red');
		file_check = false;
	    return false;
	}
	else {
	    $("div#file_name_error").text("");
	    $("div#file_name_error").css('color','');		
		file_check = true;
	}
	
	if(!file_check) {
	    $("div#file_name_error").text("파일을 선택하세요.");
	    $("div#file_name_error").css('color','red');
	    return false;
	}
	
	if(!period_check) {
	    $("div#period_error").text("광고 게시 기간을 선택하세요.");
	    $("div#period_error").css('color','red');		
		return false;
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





