
let btn_division_ClickCnt = 0;

$(document).ready(function(){
	$("span#btn_division_option").click(function(){
		btn_division_ClickCnt++;
		if(btn_division_ClickCnt%2==0){  //짝수번 클릭시
		    $("div#divisionOption").addClass("hidden");
		  } else{ //홀수번 클릭시
		    $("div#divisionOption").removeClass("hidden");
		  }
	});//end of Event---
});//end of 