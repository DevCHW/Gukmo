<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/searchBar.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript">
  $(document).ready(function(){
	  let sort= "${requestScope.sort}";
	  switch (sort) {
		case "write_date":
			sort = "최신순";
			break;
		case "comment_cnt":
			sort = "댓글순";	
			break;
		case "like_cnt":
			sort = "추천순";
			break;
		case "views":
			sort = "조회순";
			break;
		default :
			sort = "최신순";
			break;
	  }//end of switch-case---
	  
	  //검색버튼 클릭이벤트
	  $("button#btn_search").click(function(){
		const keyword = $("input#searchWord").val();
		saveKeyword(keyword);
		location.href="?page=1&sort="+sort+"&searchWord="+keyword;
	  });
	  
	  //검색창에서 엔터눌렀을 시
	  $("input#searchWord").keydown(function(e){	
	    if(e.keyCode == 13){	//엔터를 했을 경우
		  $("button#btn_search").trigger("click");  
	    }
	  });
	 
  });//end of $(document).ready(function(){})--

  
  //Function Declaration
  /**
   * 검색하면 검색어 결과 저장하기.
   */
  function saveKeyword(keyword){
	  const data = {keyword:keyword,
			  		userid : '${sessionScope.user.userid}',
			  		detail_category : '${requestScope.detail_category}'}
	  $.ajax({
		type : 'GET',
		url : '<%=ctxPath%>/saveKeyword.do',
		data : data,
		dataType : 'json',
		async : false,
		success : function(data){
		},
		error: function(xhr, status, error){
			alert("로그인에 실패했습니다."+error);
		}
	  });//end of ajax
  }
</script>
    
<!-- 검색창 영역 -->
<form name="searchFrm" class="searchBar d-flex justify-content-center mt-5 col-9">
	<input type="text" id="searchWord" name="searchWord" placeholder="검색할 내용을 입력해 주세요!" value="" ></input>
	<button type="button" id="btn_search" onclick="goSearch()">
         <i class="fa-solid fa-magnifying-glass" style="color:#208EC9; font-size:20px;"></i>
       </button>
</form>

<!-- 해시태그 -->
<div class="hashtag col-3 mt-2 px-5 d-flex justify-content-between align-items-center">
	<c:forEach var="topHash" items="${requestScope.topHashList}">
	<div class="btn_hashtag border rounded px-2">
		<a id="hashtag">#${topHash.hashtag}</a>
	</div>
	</c:forEach>
</div>
   
   
   