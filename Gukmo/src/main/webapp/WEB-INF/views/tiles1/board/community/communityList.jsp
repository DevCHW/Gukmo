<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/searchBar.css" />
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/navbar.css" />
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/boardList.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/boardList.js" ></script>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/communityList.js" ></script>

  <div class="container mt-4">
  
    <%-------------------- communityNavbar 시작 ----------------------%>
        
    <%-- communityNavbar 호출 --%>
	<jsp:include page="/WEB-INF/views/tiles1/board/navbar/communityNav.jsp" />
    <%-------------------- communityNavbar 끝 ----------------------%>

     <%-- 검색창 영역 --%>
     <form name="searchFrm">
	    <div class="searchBar d-flex mx-auto justify-content-center my-4">
	       <input type="text" id="searchWord" name="searchWord" class="pl-2" placeholder="검색할 내용을 입력해 주세요!" value="${requestScope.searchWord}"/>
	       <input type="hidden" id="sortType" name="sortType" /> <%-- form 태그내에 input 태그가 오로지 1개 뿐일경우에는 엔터를 했을 경우 검색이 되어지므로 이것을 방지하고자 만든것이다. --%>
	       <button type="button" id="btn_search">
	         <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
	       </button>
	    </div>
     </form>
    <%-- 검색바 끝 --%>


    
    <div id="nav" class="d-flex align-items-center py-2">
      <%-- 필터 시작 이곳에 자바스크립트로 필터 넣으세요. --%>
      <div id="filter_area" class="d-flex align-items-end">
        
      </div>
      <%-- filter_area --%>

      <c:set var="sortType" value="${requestScope.sortType}"/>
      <div class="d-flex ml-auto">
        <div id="mask"></div>
        <div id="sort" class="d-flex ml-3 border rounded justify-content-center align-items-center">
          <i class="fa-solid fa-arrow-down-short-wide"></i>
			<c:choose>
				<c:when test="${sortType == 'write_date'}"><span id=current_sort>최신순</span></c:when>
				<c:when test="${sortType == 'like_cnt'}"><span id=current_sort>추천순</span></c:when>
				<c:when test="${sortType == 'comment_cnt'}"><span id=current_sort>댓글순</span></c:when>
				<c:when test="${sortType eq 'views'}"><span id=current_sort>조회순</span></c:when>
				<c:otherwise><span id=current_sort>최신순</span></c:otherwise>
			</c:choose>
			
          <div id="sort_option" class="border rounded px-3 py-2">
            <span>최신순</span>
            <span>추천순</span>
            <span>댓글순</span>
            <span>조회순</span>
          </div>
        </div>
      </div>
    </div>
    <%-- 필터 끝 --%>

     <%------------------------------------- 게시판 리스트 시작 -------------------------------------%>

     <c:forEach var="boardvo" items="${requestScope.boardList}">
     <%-- 이 div가 반복문 시작 --%>
      <div class="border-top px-2 py-2">
        <div class="d-flex align-items-center my-2">
         <%-- 작성자 프로필사진 --%>
        <a href="#" class="writer_image_box border">
          <img src="<%=ctxPath %>/resources/images/${boardvo.profile_image}"/>
        </a>
        
     	<%-- 작성자 닉네임 --%>
        <%-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. --%>
        <a href="#" class="writer_nickname ml-2"> ${boardvo.nickname} </a>

         <%-- 작성자 활동점수 --%>
         <div class="writer_point ml-2">
           <i class="fa-solid fa-bolt"></i>
           <span>${boardvo.writer_point}</span>
         </div>

         <%-- 작성일자 --%>
         <div class="write_date ml-2">${boardvo.write_date}</div>
       </div>

       <%-- 글제목 --%>
       <a href="<%=ctxPath %>/detail.do?boardNum=${boardvo.board_num}" class="subject align-items-center my-2">
         ${boardvo.subject}
       </a>

       <div class="d-flex justify-content-between align-items-center my-2">
         <div class="d-flex align-items-center">
           <%-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 --%>
           <div class="detail_category border rounded px-2 py-1">${boardvo.detail_category}</div>
           <div class="hashtag ml-1">
             <%-- 해시태그 리스트 들어갈 곳--%>
             <%-- 해시태그리스트 반복문시작 --%>
             <c:forEach var="hashtag" items="${boardvo.hashtags}">
            	<a href="/board/main_search.do?searchWord=${hashtag.hashtag}" class="hashtag mx-1">#<span>${hashtag.hashtag}</span></a>
             </c:forEach>
             <%-- 해시태그리스트 반복문 끝--%>
           </div>
         </div>

         <%-- 조회수,댓글수,추천수 --%>
         <div class="board_info_box d-flex justify-content-end">
           <%-- 조회수 --%>
           <div>
             <i class="fa-solid fa-eye"></i>
             <span>${boardvo.views}</span>
           </div>

           <%-- 댓글수 --%>
           <div class="ml-2">
             <i class="fa-solid fa-comment-dots"></i>
             <span>${boardvo.comment_cnt}</span>
           </div>

           <%-- 추천수 --%>
           <div class="ml-2">
             <i class="fa-solid fa-heart"></i>
             <span>${boardvo.like_cnt}</span>
           </div>
         </div>
       </div>
     </div>
     </c:forEach>
    

         
     <%-- 이 div가 반복문 끝 --%>

     <%----------------------------------- 게시판 리스트 끝 -------------------------------------%>

     <div class="d-flex border-top pt-3 justify-content-between">
       <div id="total_cnt">
         <%-- 총 건수 변수 들어갈 곳--%>
         	총&nbsp;<span style="font-weight: bold">${requestScope.totalCount}&nbsp;</span>건
       </div>

       <button type="button" id="btn_write" class="btn border-0 rounded" onclick="location.href='<%=ctxPath%>/community/new.do?detailC=${requestScope.detail_category}'">
             글쓰기
       </button>
     </div>

     <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
      <nav aria-label="...">
      ${requestScope.pageBar}
      </nav>
     <%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
   </div>