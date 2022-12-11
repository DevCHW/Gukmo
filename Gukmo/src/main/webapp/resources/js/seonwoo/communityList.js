// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
	//정렬버튼 클릭시
	  $("div#sort").click(function(){
	    $("div#mask").show();
	    $("div#sort_option").fadeIn(200);
	    $("div#sort_option").css("display","flex");
	    $("div#sort_option").css("flex-direction","column");
	  });

	  //정렬버튼 클릭 후, 바깥쪽 아무데나 클릭시
	  $("div#mask").click(function(){
	    $("div#sort_option").fadeOut(200);
	    $("div#mask").hide();
	  });
	  
	  //정렬옵션 클릭시 이벤트
	  $("div#sort_option span").click(e=>{
	    const target = $(e.currentTarget);
	    const sort = target.text();
	    
	    location.href="?page="+sessionStorage.getItem("page")+"&searchWord="+sessionStorage.getItem("searchWord")+"&sort="+sort;
	  });
	  
	
	
});// end of $(document).ready()


	
	
	



 