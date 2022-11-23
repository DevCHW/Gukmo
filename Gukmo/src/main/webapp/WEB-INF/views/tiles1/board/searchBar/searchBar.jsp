<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/searchBar.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript">
  $(document).ready(function(){
	  let url = window.document.location.href;
	  url = url.substr(27);
	    
	  if(url.indexOf("?") != -1){
	    url = url.substr(0,url.indexOf("?"));
	  }
		
	  //검색버튼 클릭이벤트
	  $("button#btn_search").click(function(){
	    location.href="?page=1&sort=${requestScope.sort}&searchWord="+$("input#searchWord").val();
	  });
	  
	  //검색창에서 엔터눌렀을 시
	  $("input#searchWord").keydown(function(e){	
	    if(e.keyCode == 13){	//엔터를 했을 경우
		  $("button#btn_search").trigger("click");  
	    }
	  });
	 
  });//end of $(document).ready(function(){})--

</script>
    
    <!-- 검색창 영역 -->
   <div class="searchBar d-flex my-3 justify-content-center">
     <input type="text" id="searchWord" class="pl-3" placeholder="검색어를 입력해주세요"></input>
     <button type="button" id="btn_search">
       <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
     </button>
   </div>