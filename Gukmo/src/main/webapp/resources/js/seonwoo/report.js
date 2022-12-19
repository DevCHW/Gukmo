// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
	
	$("button#reportWrite").click(function() {
		const simple_report_reason = $("select#simple_report_reason").val();
		const detail_report_reason = $("textarea#detail_report_reason").val().trim();
	  	
		if(simple_report_reason == "" || (simple_report_reason == "기타" && detail_report_reason == "")) {
	  		alert("사유를 선택하세요!!");
			return;
	  	}
	  	
		const frm = document.reportFrm;
		frm.method = "POST";
		frm.action = getContextPath()+"/community/reportEnd.do";
		frm.submit();
	})

	
});// end of document---

