// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

let btn_division_ClickCnt = 0;

$(document).ready(function(event){
	const pd = $("input#page_division").val();
	// alert(pd);
	
	if(pd == "") {
		$("span#ad_division").text("전체");
	}
	else if (pd == "메인") {
		$("span#ad_division").text("메인");
	}
	
	else {
		$("span#ad_division").text("게시판");
	}
	
	// 구분 한번 / 두번 클릭시
	$("span#btn_division_option").click(function(){
		btn_division_ClickCnt++;
		if(btn_division_ClickCnt%2==0){  //짝수번 클릭시
		    $("div#divisionOption").addClass("hidden");
		  } else{ //홀수번 클릭시
		    $("div#divisionOption").removeClass("hidden");
		  }
	});//end of Event---
	
	// 구분에서 전체 클릭시
	$("div#division_all").click(function(event) {
		const target = $(event.currentTarget);
		target.parent().prev().prev().text("전체");		
		$("div#divisionOption").addClass("hidden");
		$("input#division").val("");
		const type = $("input#division").val(); 
		getList_main(type);
	})
	
	// 구분에서 메인 클릭시
	$("div#division_main").click(function(event) {
		const target = $(event.currentTarget);
		target.parent().prev().prev().text("메인");
		$("div#divisionOption").addClass("hidden");
		$("input#division").val("메인");
		const type = $("input#division").val(); 
		getList_main(type);
	})
	
	// 구분에서 게시판 클릭시
	$("div#division_board").click(function(event) {
		const target = $(event.currentTarget);
		target.parent().prev().prev().text("게시판");		
		$("div#divisionOption").addClass("hidden");
		$("input#division").val("게시판");
		const type = $("input#division").val(); 
		getList_main(type);
	})
	
	// 상태에서 전체 클릭시
	$("div#status_all").click(function(event) {
		const target = $(event.currentTarget);
		target.parent().prev().prev().text("전체");		
		$("div#advertisementStatusOption").addClass("hidden");
		$("input#status").val("");
		const status = $("input#status").val(); 
		getList_status(status, pd);
	})
	
	// 상태에서 진행중 클릭시
	$("div#status_ing").click(function(event) {
		const target = $(event.currentTarget);
		target.parent().prev().prev().text("진행중");		
		$("div#advertisementStatusOption").addClass("hidden");
		$("input#status").val("0");
		const status = $("input#status").val(); 
		getList_status(status, pd);
	})
	
	// 상태에서 종료 클릭시
	$("div#status_end").click(function(event) {
		const target = $(event.currentTarget);
		target.parent().prev().prev().text("종료");		
		$("div#advertisementStatusOption").addClass("hidden");
		$("input#status").val("1");
		const status = $("input#status").val(); 
		getList_status(status, pd);
	})
	
	
	
	
});//end of 

// Function 

function getList_main(type) {
	
	var frm = document.divisionFrm;
	frm.action=getContextPath()+"/admin/advertisement/list.do?division="+type;
	frm.method="GET";
	frm.submit();
}

function getList_status(status, pd) {
	$("input#division").val(pd);
	$("input#status").val(status);
	
	var frm = document.divisionFrm;
	frm.action=getContextPath()+"/admin/advertisement/list.do?division="+division+"&status="+status;
	frm.method="GET";
	frm.submit();
	
}


