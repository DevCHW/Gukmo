<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/curriculumList.css" />

<script type="text/javascript">
  sessionStorage.setItem("page","${requestScope.page}");
  sessionStorage.setItem("searchWord","${requestScope.searchWord}");
  sessionStorage.setItem("sort","${requestScope.sort}");
</script>

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/curriculumList.js" ></script>

  <div class="container mt-4">
  
    <%-------------------- communityNavbar 시작 ----------------------%>
    
    <%-- communityNavbar 호출 --%>
	<jsp:include page="/WEB-INF/views/tiles1/board/navbar/academyNav.jsp" />
        
    <%-------------------- communityNavbar 끝 ----------------------%>



     <%-------------------- 검색창 영역 시작 ----------------------%>
     
     <%-- 검색창 호출 --%>
     <jsp:include page="/WEB-INF/views/tiles1/board/searchBar/searchBar.jsp" />
     
     <%-------------------- 검색창 영역 끝 ----------------------%>


    
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
    <%-- 필터 끝 --%>
    
    
    
    	 <%------------------------------------- 필독 공지사항 리스트 시작 -------------------------------------%>
	 <c:forEach var="notice" items="${requestScope.mustReadNotice}">
     <%-- 이 div가 반복문 시작 --%>
      <div class="border-top px-2 py-2" style="background-color:#F0F6FA;">
        <div class="d-flex align-items-center my-2" style="font-size:15px;">
         <%-- 작성자 프로필사진 --%>
        <a href="<%=ctxPath %>/member/activityOther.do?nickname=${notice.nickname}" class="writer_image_box border">
          <c:if test="${notice.profile_image.substring(0,4) != 'http'}">
            <img src="<%=ctxPath %>/resources/images/${notice.profile_image}">
          </c:if>
          <c:if test="${notice.profile_image.substring(0,4) == 'http'}">
            <img src="${notice.profile_image}">
          </c:if>
        </a>
        
     	<%-- 작성자 닉네임 --%>
        <%-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. --%>
        <a href="<%=ctxPath %>/member/activityOther.do?nickname=${notice.nickname}" class="writer_nickname ml-2"> ${notice.nickname} </a>

         <%-- 작성자 활동점수 --%>
         <div class="writer_point ml-2">
           <i class="fa-solid fa-bolt"></i>
           <span>${notice.writer_point}</span>
         </div>

         <%-- 작성일자 --%>
         <div class="write_date ml-2">${notice.write_date}</div>
       </div>

       <%-- 글제목 --%>
       <a href="<%=ctxPath %>/detail.do?boardNum=${notice.board_num}" class="subject align-items-center my-2" style="font-size:17px; font-weight:bold;">
         ${notice.subject}
       </a>

       <div class="d-flex justify-content-between align-items-center my-2">
         <div class="d-flex align-items-center">
           <%-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 --%>
           <div class="detail_category border rounded px-2 py-1">${notice.detail_category}</div>
           <div class="hashtag ml-1">
             <%-- 해시태그 리스트 들어갈 곳--%>
             <%-- 해시태그리스트 반복문시작 --%>
             <c:forEach var="hashtag" items="${notice.hashtags}">
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
     <%-- 이 div가 반복문 끝 --%>
	 <%------------------------------------- 필독 공지사항 리스트 끝 -------------------------------------%>



    <%------------------------------------- 게시판 리스트 시작 -------------------------------------%>
	
    <%-- 게시글 반복문 시작 --%>
    <c:forEach var="boardvo" items="${requestScope.curriculumList}">
    
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <%-- 작성자 프로필사진 --%>
        <a href="#" class="writer_image_box border">
          <img src="<%=ctxPath %>/resources/images/${boardvo.profile_image}"/>
        </a>

        <%-- 작성자 닉네임 --%>
        <%-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. --%>
        <a href="#" class="writer_nickname ml-2">
         	 ${boardvo.nickname }
        </a>

        <%-- 작성자 활동점수 --%>
        <div class="writer_point ml-2">
          <i class="fa-solid fa-bolt"></i>
          <span>${boardvo.writer_point}</span>
        </div>

        <%-- 작성일자 --%>
        <div class="write_date ml-2">
          ${boardvo.write_date}
        </div>
      </div>
      
      
      <%-- 학원명 
      <div class="academy_name my-1"></div>
	  --%>
	  
      <%-- 교육과정명 --%>
      <a href="<%=ctxPath %>/detail.do?boardNum=${boardvo.board_num} " class="subject align-items-center my-2">
        [${boardvo.curriculum.academy_name}]&nbsp;&nbsp;${boardvo.subject}
      </a>
      <c:if test="${boardvo.dday < 10}">
      	<span class="ml-2 text-danger" style="font-size:17px; font-weight:bold;">[D-${boardvo.dday}] 마감임박!</span>
      </c:if>
      <c:if test="${boardvo.dday >= 10}">
      	<span class="ml-2 text-info" style="font-size:17px; font-weight:bold;">[D-${boardvo.dday}]</span>
      </c:if>
      
      
      <%-- 교육과정 기간,핵심기술 --%>
      <div class="d-flex my-2">
        <%-- 교육과정 기간 --%>
	    <span class="period mr-2">
	             교육기간&nbsp;:&nbsp;${boardvo.curriculum.curriculum_start_date}<span>&nbsp;~&nbsp;</span>${boardvo.curriculum.curriculum_end_date}
	    </span>
        <%-- 핵심기술 --%>
	    <span class="core_technology">핵심기술&nbsp;:&nbsp;${boardvo.curriculum.core_technology}</span>
      </div>
      
      <%-- 모집인원 --%>
	  <div class="core_technology">
	         모집인원&nbsp;:&nbsp;${boardvo.curriculum.cnt_recruits}명
	  </div>
      

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <%-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 변수 말고 아예 값 박아도 됨--%>
          <div class="detail_category border rounded px-2 py-1" onclick="location.href='<%=ctxPath %>/academy/curricula.do'">
          	  ${boardvo.detail_category}
          </div>
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
    
    <%-- 게시글 반복문 끝 --%>
    
    
    
    <%-- 게시글이 없다면 --%>
    <c:if test="${fn:length(requestScope.curriculumList) == 0}">
      <div class="d-flex justify-content-center align-items-center border-top" style="height:300px;">
      	<div style="font-size:25px; font-weight:bold;">게시물이 없습니다.</div>
      </div>
    </c:if>
    
    
    
    <%----------------------------------- 게시판 리스트 끝 -------------------------------------%>

    <div class="d-flex border-top pt-3 justify-content-between">

	  <%-- 총 건수 변수 들어갈 곳 --%>
      <div id="total_cnt">총&nbsp;<span style="font-weight:bold;">${requestScope.totalCount}&nbsp;</span>건</div>

	  <c:if test="${not empty sessionScope.user.academy_name || sessionScope.user.authority == '관리자'}">
        <button type="button" id="btn_write" class="btn border-0 rounded" onclick="location.href='<%=ctxPath%>/academy/curriculum/new.do'">
          <i class="fa-sharp fa-solid fa-plus"></i><span>교육과정&nbsp;등록</span>
        </button>
      </c:if>
    </div>




    <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
    <nav aria-label="...">
      ${requestScope.pageBar}
    </nav>
	<%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
	
  </div>
