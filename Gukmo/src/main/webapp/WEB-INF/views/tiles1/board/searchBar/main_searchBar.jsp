<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hasol/main_searchBar.css" />


<script type="text/javascript">
$(document).ready(function(){
	
	// 검색어 엔터 이벤트
	$("input#searchWord").keydown(function(e){
		if(e.keyCode == 13){
			goSearch();
			saveKeyword();
		}
	});
	
	// 해시태그 클릭 시 자동 검색되도록 
	$("a#hashtag").click(function(e){
		const $target = $(e.target);
		let hashtag = $target.text().substr(1);
		console.log(hashtag);
		$("input#searchWord").val(hashtag);
		$("input#hashtag").val(hashtag);
	
		goSearch();
		saveKeyword(hashtag);
	});
	
	
	// 검색 순위 클릭 시 자동 검색되도록
	$("a#keyword").click(function(e){
		const $target = $(e.target);
		let keyword = $target.text();
		console.log(keyword);
		$("input#searchWord").val(keyword);

		goSearch();
		saveKeyword(keyword);
	});
});

//검색
function goSearch (){	
	const frm = document.searchFrm;
	frm.method="GET";
	frm.action= getContextPath()+"/main_search.do"
	frm.submit();	
}


function saveKeyword(keyword){
	
	  const data = {keyword:$("input#searchWord").val(),
			  		userid : '${sessionScope.user.userid}'}
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
	<input type="text" id="searchWord" name="searchWord" placeholder="검색할 내용을 입력해 주세요!" value="${paraMap.searchWord}"></input>
	<input type="hidden" id="hashtag" name="hashtag" value="" />
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
