<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/hyunwoo/activities.css" />
  
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/activities.js" ></script>


  <div class="container my-4">
  	<%-------------------- 사이드바 시작 ----------------------%>
        
    <%-- sidebar 호출 --%>
	<jsp:include page="/WEB-INF/views/tiles1/member/sidebar.jsp" />
        
        
    <%-------------------- 사이드바 끝 ----------------------%>
    
    <div id="main">
      <%-- main_header --%>
      <div class="main_header border rounded">
        <%-- top --%>
        <div class="d-flex">
          <div class="d-flex w-100 justify-content-between py-4 px-3 align-items-center">
            <div class="d-flex align-items-center">
              <%-- 프사 --%>
              <div id="profile_img_box" class="border">
              	<c:if test="${fn:substring(sessionScope.user.profile_image,0,4) != 'http'}">
                  <img src="<%=ctxPath %>/resources/images/${sessionScope.user.profile_image}"/>
                </c:if>
                <c:if test="${fn:substring(sessionScope.user.profile_image,0,4) == 'http'}">
             	   <img src="${sessionScope.user.profile_image}"/>
                </c:if>
              </div>
      
              <div class="ml-4 py-1">
                <h4 id="user_nickname">${sessionScope.user.nickname}</h4>
                <div id="point">활동점수&nbsp;<span>${sessionScope.user.point}</span></div>
              </div>
            </div>
  
            <button type="button" id="btn_go_myId" class="btn border rounded" onclick="location.href='<%=ctxPath %>/member/myId.do'">
              	나의 계정
            </button>
          </div>
        </div>

        <%-- bottom --%>
        <div id="navbar" class="d-flex justify-content-center border-top">
          <div class="filter mx-4 py-3">
            	활동내역
          </div>
          <div class="filter mx-4 py-3">
            	게시물
          </div>
        </div>
      </div>


      <%-- 활동내역 시작 --%>
      <div id="activities" class="mt-4">
      
        <c:forEach var="activity" items="${requestScope.activities}">
          <%-- 1 --%>
          <div class="activity_box border-top border-bottom py-4">
            <div class="activity_title align-items-center">
              <div class="d-flex align-items-center">
              
                <%-- 디테일 카테고리에 따라 링크 넣어줄 것.커서 포인터,링크걸기 --%>
                <div class="detail_category border rounded-pill px-2 py-2">
               		${activity.detail_category}
                </div>
                
                <c:if test="${activity.division eq '댓글작성'}">
                <div class="activity_content ml-2">
                 	<span>${activity.nickname}</span>
                 	<span>님의 게시물에 댓글을 달았습니다.</span>
                </div>
                </c:if>
                
                <c:if test="${activity.division eq '게시글작성'}">
                <div class="activity_content ml-2">
                 	에 글을 작성하였습니다.
                </div>
                </c:if>
                
                <c:if test="${activity.division eq '게시글좋아요'}">
                <div class="activity_content ml-2">
                 	<span>${activity.nickname}</span>
                 	<span>님의 게시물을 추천하였습니다.</span>
                </div>
                </c:if>
                
                <c:if test="${activity.division eq '댓글좋아요'}">
                <div class="activity_content ml-2">
                 	<span>${activity.nickname}</span>
                 	<span>님의 게시물에 달린 댓글을 추천하였습니다.</span>
                </div>
                </c:if>
                
              </div>
              <div class="activity_date">
                	${activity.activity_date}
              </div>
            </div>
            <div class="board mt-2">
              	${activity.subject}
            </div>
          </div>
        </c:forEach>
      </div>
    </div>

    <%----------------------------------- main 끝-------------------------------------%>
  </div>


  <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
  <nav aria-label="...">
    ${requestScope.pageBar}
  </nav>
  <%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>


  <%-- 검색바시작 --%>
  <div id="search_area" class="d-flex mx-auto my-5">
    <div id="academy_search" class="d-flex m-auto rounded">
      <div id="input_keyword">
        <input type="text" id="searchWord" placeholder="활동내역 검색" id="keyword" class="pl-3" name="keyword">
      </div>
      <div id="search_btn">
        <button type="button" class="btn btn-white" id="btn_search"><i class="fas fa-xl fa-thin fa-magnifying-glass"></i></button>
      </div>
    </div>
  </div>
  <%-- 검색바 끝 --%>


</body>
</html>