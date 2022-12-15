<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/hyunwoo/activityOther.css" />
  
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/activityOther.js" ></script>


  <div class="container my-4">
    <div id="main">
      <%-- main_header --%>
      <div class="main_header border rounded">
        <%-- top --%>
        <div class="d-flex">
          <div class="d-flex w-100 justify-content-between py-4 px-3 align-items-center">
            <div class="d-flex align-items-center">
              <%-- 프사 --%>
              <div id="profile_img_box" class="border">
              	<c:if test="${fn:substring(requestScope.memberMap.PROFILE_IMAGE,0,4) != 'http'}">
                  <img src="<%=ctxPath %>/resources/images/${requestScope.memberMap.PROFILE_IMAGE}"/>
                </c:if>
                <c:if test="${fn:substring(requestScope.memberMap.PROFILE_IMAGE,0,4) == 'http'}">
             	   <img src="${requestScope.memberMap.PROFILE_IMAGE}"/>
                </c:if>
              </div>
      
              <div class="ml-4 py-1">
                <h4 id="user_nickname">${requestScope.memberMap.NICKNAME}</h4>
                <div id="point">활동점수&nbsp;<span>${requestScope.memberMap.POINT}</span></div>
              </div>
            </div>
  
            <button type="button" id="btn_go_myId" class="btn border rounded" onclick="location.href='<%=ctxPath %>/member/myId.do'">나의 계정</button>
          </div>
        </div>

        <%-- bottom --%>
        <div id="navbar" class="d-flex justify-content-center border-top">
          <div class="filter mx-4 py-3">활동내역</div>
        </div>
      </div>


      <%-- 활동내역 시작 --%>
      <div id="activities" class="mt-4">
      
        <c:forEach var="activity" items="${requestScope.activities}">
          <%-- 1 --%>
          <div class="activity_box border-top border-bottom py-4">
            <div class="activity_title align-items-center">
              <div class="d-flex align-items-center">
              
                <%-- 활동이일어난곳의 디테일카테고리--%>
                <div class="detail_category border rounded-pill px-2 py-2" onclick="goDetailCategory(${activity.detail_category})">${activity.detail_category}</div>
                
                <c:if test="${activity.division eq '댓글작성'}">
                <div class="activity_content ml-2">
                 	<span>${activity.nickname}</span>
                 	<span>님의 게시물에 댓글을 달았습니다.</span>
                </div>
                </c:if>
                
                <c:if test="${activity.division eq '게시글작성'}">
                <div class="activity_content ml-2">에 글을 작성하였습니다.</div>
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
              <%-- 활동일자 --%>
              <div class="activity_date">${activity.activity_date}</div>
            </div>
            <%-- 활동이일어난 글제목 --%>
            <div class="activity_subject board mt-2" onclick="location.href='<%=ctxPath%>/detail.do?boardNum=${activity.fk_board_num}'">${activity.subject}</div>
          </div>
        </c:forEach>
      </div>
      
      
      <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
      <nav aria-label="...">
        ${requestScope.pageBar}
      </nav>
      <%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
    </div>
    
    

    <%----------------------------------- main 끝-------------------------------------%>
  </div>


  




</body>
</html>