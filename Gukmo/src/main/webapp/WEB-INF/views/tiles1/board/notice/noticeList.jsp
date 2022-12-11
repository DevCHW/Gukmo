<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/boardList.css" />

<script type="text/javascript">
  sessionStorage.setItem("page","${requestScope.page}");
  sessionStorage.setItem("searchWord","${requestScope.searchWord}");
  sessionStorage.setItem("sort","${requestScope.sort}");
</script>

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/boardList.js" ></script>

  <div class="container mt-4">
  
    <%-------------------- communityNavbar 시작 ----------------------%>
    
    <%-- communityNavbar 호출 --%>
	<jsp:include page="/WEB-INF/views/tiles1/board/navbar/noticeNav.jsp" />
        
    <%-------------------- communityNavbar 끝 ----------------------%>



     <%-------------------- 검색창 영역 시작 ----------------------%>
     
     <%-- 검색창 호출 --%>
     <jsp:include page="/WEB-INF/views/tiles1/board/searchBar/searchBar.jsp" />
     
     <%-------------------- 검색창 영역 끝 ----------------------%>


    
    
    <%-- 정렬버튼 --%>
    <div id="sort_area" class="d-flex align-items-center py-2">
      <div class="d-flex ml-auto">
        <div id="mask"></div>
        <div id="sort" class="d-flex border rounded justify-content-center align-items-center">
          <i class="fa-solid fa-arrow-down-short-wide"></i>
          <span id=current_sort>${requestScope.sort}</span>
          <div id="sort_option" class="border rounded px-3 py-2">
            <span>최신순</span>
            <span>추천순</span>
            <span>댓글순</span>
            <span>조회순</span>
          </div>
        </div>
      </div>
    </div>
    <%-- 정렬버튼 --%>



    <%------------------------------------- 게시판 리스트 시작 -------------------------------------%>
	
    <%-- 게시글 반복문 시작 --%>
    <c:forEach var="notice" items="${requestScope.noticeList}">
    
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <%-- 작성자 프로필사진 --%>
        <a href="#" class="writer_image_box border">
          <img src="<%=ctxPath %>/resources/images/${notice.profile_image}"/>
        </a>

        <%-- 작성자 닉네임 --%>
        <%-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. --%>
        <a href="#" class="writer_nickname ml-2">
         	 ${notice.nickname }
        </a>

        <%-- 작성자 활동점수 --%>
        <div class="writer_point ml-2">
          <i class="fa-solid fa-bolt"></i>
          <span>${notice.writer_point}</span>
        </div>

        <%-- 작성일자 --%>
        <div class="write_date ml-2">
          ${notice.write_date}
        </div>
      </div>

      <%-- 글제목 --%>
      <a href="<%=ctxPath %>/detail.do?boardNum=${notice.board_num}" class="subject align-items-center my-2">
        ${notice.subject}
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <%-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 변수 말고 아예 값 박아도 됨--%>
          <div class="detail_category border rounded px-2 py-1">
          	  ${notice.detail_category}
          </div>
          <div class="hashtag ml-1">
            <%-- 해시태그 리스트 들어갈 곳--%>
            <%-- 해시태그리스트 반복문시작 --%>
            <c:forEach var="hashtag" items="${notice.hashtags}">
            <a href="#" class="hashtag mx-1">#<span>${hashtag.hashtag}</span></a>
            </c:forEach>
            <%-- 해시태그리스트 반복문 끝--%>
          </div>
        </div>

        <%-- 조회수,댓글수,추천수 --%>
        <div class="board_info_box d-flex justify-content-end">
          <%-- 조회수 --%>
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>${notice.views}</span>
          </div>

          <%-- 댓글수 --%>
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>${notice.comment_cnt}</span>
          </div>

          <%-- 추천수 --%>
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>${notice.like_cnt}</span>
          </div>
        </div>
      </div>
    </div>
    </c:forEach>
    
    <%-- 게시글 반복문 끝 --%>
    
    
    
    <%-- 게시글이 없다면 --%>
    <c:if test="${fn:length(requestScope.noticeList) == 0}">
      <div class="d-flex justify-content-center align-items-center border-top" style="height:300px;">
      	<div style="font-size:25px; font-weight:bold;">게시물이 없습니다.</div>
      </div>
    </c:if>
    
    
    
    <%----------------------------------- 게시판 리스트 끝 -------------------------------------%>

    <div class="d-flex border-top pt-3 justify-content-between">

      <div id="total_cnt">
        <%-- 총 건수 변수 들어갈 곳--%>
        총&nbsp;<span style="font-weight:bold;">${requestScope.totalCount}&nbsp;</span>건
      </div>
		
	  <c:if test="${sessionScope.user.userid == 'admin'}">
        <button type="button" id="btn_write" class="btn border-0 rounded" onclick="location.href='<%=ctxPath%>/community/new.do'">
          <i class="fa-sharp fa-solid fa-plus"></i><span>공지사항 작성</span>
        </button>
      </c:if>
    </div>




    <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
    <nav aria-label="...">
      ${requestScope.pageBar}
    </nav>
	<%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
	
  </div>
