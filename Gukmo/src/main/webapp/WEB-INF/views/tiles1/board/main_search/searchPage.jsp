<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath%>/resources/css/hasol/searchPage.css?after">

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hasol/searchPage.js" ></script>





<!-- contents 시작 -->

<div class="container d-flex flex-column align-items-center">
	
    <!-- 검색창 영역 -->
	<form name="searchFrm" class="searchBar d-flex justify-content-center mt-5 col-9">
		<input type="text" id="searchWord" name="searchWord" placeholder="검색할 내용을 입력해 주세요!" ></input>
		<button type="button" id="btn_search">
          <i class="fa-solid fa-magnifying-glass" style="color:#208EC9; font-size:20px;"></i>
        </button>
	</form>
	
	<!-- 해시태그 -->
	<div class="hashtag col-7 mt-1 d-flex justify-content-center">
		<a href ="#" class="first_hashtag">#해시태그</a>
		<a href ="#">#해시태그</a>
		<a href ="#">#해시태그</a>
	</div>
	
	<div class="div_searchList w-100 mt-3 d-flex flex-column justify-content-start">
		<c:forEach var="boardvo" items="${requestScope.searchList}">
		<div class="div_searchResult my-3">
			<div class="subject"> 
				<a href="<%= ctxPath%>/detail.do?board_num=${boardvo.board_num}">${boardvo.subject}</a>
				<span class="write_date">${boardvo.write_date}</span>
			</div> 
			<div class="content">
				<a href="<%= ctxPath%>/detail.do?board_num=${boardvo.board_num}">${boardvo.content}</a>
			</div>	
			<div class="hashtag">
				<c:forEach var="hashtags" items="${boardvo.hashtags}">
				<span>#${hashtags.hashtag}</span>
			</c:forEach>  
			</div>                                            
		</div>
		</c:forEach>
	</div>
	

	
	<nav>
		${requestScope.pageBar}
	</nav>
</div>
