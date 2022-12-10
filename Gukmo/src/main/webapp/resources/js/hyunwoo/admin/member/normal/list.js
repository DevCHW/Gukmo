
let memberStatusClickCnt = 0;
$(document).ready(function(){
	$("span#btn_status_option").click(function(){
		memberStatusClickCnt++;
		if(memberStatusClickCnt%2==0){  //짝수번 클릭시
		    $("div#memberStatusOption").addClass("hidden");
		  } else{ //홀수번 클릭시
		    $("div#memberStatusOption").removeClass("hidden");
		  }
	});//end of Event---
});//end of 