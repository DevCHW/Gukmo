<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/navbar.css" />

<!-- 직접만든 javascript -->
<script type="text/javascript">
  $(document).ready(function(){
	  
    let url = window.document.location.href;
    url = url.substr(27);
    
    if(url.indexOf("?") != -1){
      url = url.substr(0,url.indexOf("?"));
    }
    
    switch (url) {
	  case "/academy/academies.do" :	//보고있는 페이지가 "국비학원"일 경우 .active 추가
		  $("div#academies").addClass("active");
		  break;
	  case "/academy/curricula.do" : //보고있는 페이지가 "교육과정"일 경우 .active 추가
		  $("div#curricula").addClass("active");
		  break;
	}//end of switch-case---
	
	
	$("div.detail-category").click(function(e){
		const target = $(e.currentTarget);
		const id = target.attr("id");
		switch (id) {
		  case "academies" :	
			  location.href="<%=ctxPath%>/academy/academies.do";
			  break;
		  case "curricula" :	
			  location.href="<%=ctxPath%>/academy/curricula.do";
			  break;
		}
	});//end of Event---
  });//end of $(document).ready(function(){})

</script>


<div id="category" class="d-flex justify-content-center align-content-center">
  <div id="academies" class="detail-category mx-2 px-2 py-1 rounded">국비학원</div>
  <div id="curricula" class="detail-category mx-2 px-2 py-1 rounded">교육과정</div>
</div>
