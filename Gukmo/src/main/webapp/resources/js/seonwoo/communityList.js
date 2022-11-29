// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
	// 검색창에서 엔터를 쳤을 경우
	$("input#searchWord").keyup(e=>{
	     if(e.keyCode == 13) { 
	    	 getBoardList();
	     }
    });
	
	// 검색버튼 눌렀을 경우
	$("button#btn_search").click(function(){ 
		getBoardList();
	}); 
	
	// 정렬 눌렀을때 
	$("div#sort_option").on("click","span",	function(e){
		  
		let sortType = e.target.innerText;
		
		document.getElementById("current_sort").innerText = sortType;
		
		switch (sortType) {
		  case "최신순" :	
			  $("input#sortType").val("write_date");
			  break;
		  case "추천순" :
			  $("input#sortType").val("like_cnt");
			  break;
		  case "댓글순" :
			  $("input#sortType").val("comment_cnt");
			  break;
		  case "조회순" :
			  $("input#sortType").val("views");
			  break;
		}
		
		getBoardList();
	});
	
	
	
});// end of $(document).ready()


function getBoardList() {

	let url = window.document.location.href;
    url = url.substr(27);
	    
    if(url.indexOf("?") != -1){
      url = url.substr(0,url.indexOf("?"));
    }
	
	const searchWord = $("input#searchWord").val().trim();
	
	 // 검색폼을 전송
    const frm = document.searchFrm;
    frm.method = "GET";
    frm.action = getContextPath()+url;
    frm.submit();
	
}
	
	
	
	



 