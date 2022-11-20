

$(document).ready(function(){
	
	
	// 검색창 select 기능 구현
    let select = $('.selectSearch_div select');
    
    select.change(function(){
    	
        let select_name = $(this).children('option:selected').text();
        $(this).siblings("label").text(select_name);
    });
 
});







// 배너 


/*// 검색창
function goSearch (){
	
	$.ajax(){
		url:"<%= ctxPath%>/"
	}
} */