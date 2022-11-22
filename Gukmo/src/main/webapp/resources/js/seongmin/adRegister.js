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

});//end of $(document).r eady(function(){})---

function adWrite() {
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





