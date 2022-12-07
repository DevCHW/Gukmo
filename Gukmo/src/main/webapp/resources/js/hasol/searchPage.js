// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}



$(document).ready(function(){
	
	// 검색어 엔터 이벤트
	$("input#searchWord").keydown(function(e){
		if(e.keyCode == 13){
			goSearch();
		}
	});
	
	$("a#hashtag").click(function(e){
		const $target = $(e.target);
		let hashtag = $target.text();
		$("input#searchWord").val(hashtag);
		
		goSearch();
	});

 
});



//검색
function goSearch (){
	
	const frm = document.searchFrm;
	frm.method="GET";
	frm.action= getContextPath()+"/main_search.do"
	frm.submit();
	
}
