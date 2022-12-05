<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>
    
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/member/academy/list.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/member/academy/list.js" ></script>




  <div id="section" class="d-flex">
  
  
    <!---------------------------- 사이드바 호출 -------------------------------->
    
    <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/sidebar.jsp" />
    
    <!---------------------------- 사이드바 호출 --------------------------------------->


    <!---------------------------- (메인콘텐츠) 시작 ------------------------------->
    <div id="main" class="py-5 px-4 w-100">
    
      <!----------------- 검색창 호출 시작 ------------------------>
      
      <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/searchBar.jsp" />
      
      <!------------------------ 검색창 호출 끝 ------------------------>

      <!-- 검색결과 갯수 -->
      <div id="total_cnt" class="my-2">${requestScope.totalCount}&nbsp;건</div>



	  <!------------------------ 교육기관회원리스트영역 시작 ------------------------>
      <div class="nomalMemberList">
        <div id="title" class="d-flex justify-content-between py-3 border-top border-bottom">
          <div class="userid text-center">
            <div>아이디</div>
          </div>
          <div class="nickname text-center">
            <div>닉네임</div>
          </div>
          <div class="academy_name text-center">
            <div>학원명</div>
          </div>
          <div class="join_date text-center">
            <div>가입일자</div>
          </div>
          <div id="spanMemberStatus" class="status text-center">
            <span>상태</span>
            <span id="btn_status_option">
              <i class="fa-solid fa-caret-down"></i>
            </span>
            <div id="memberStatusOption" class="hidden border rounded px-3 py-2">
              <div class="my-1">전체</div>
              <div class="my-1">활동</div>
              <div class="my-1">정지</div>
              <div class="my-1">휴면</div>
              <div class="my-1">대기</div>
            </div>
          </div>
        </div>

        <!-- 교육기관회원 반복문 시작 -->
        <c:forEach var="acamembervo" items="${requestScope.academymemberList}" varStatus="status">
        <div class="list_box d-flex justify-content-between py-3 border-bottom">
          <!-- userid -->
          <div class="member_info userid text-center">
            <div>
              <c:if test="${acamembervo.userid.length() >= 12}" >
           	  	${acamembervo.userid.substring(0, 11)}...
           	  </c:if>
           	  <c:if test="${acamembervo.userid.length() < 12}" >
           	  	${acamembervo.userid}
           	  </c:if>
            </div>
          </div>
          
          <!-- nickname -->
          <div class="member_info nickname text-center">
            <div>${membervo.nickname}</div>
          </div>
          
          <!-- academy_name -->
          <div class="member_info academy_name text-center">
            <div>${membervo.academy_name}</div>
          </div>
          
          <!-- join_date -->
          <div class="member_info join_date text-center">
            <div class="m-auto">${membervo.join_date}</div>
          </div>
          
          <!-- status -->
          <div class="member_info status text-center">
            <div class="m-auto">${membervo.status}</div>
          </div>
        </div>
        </c:forEach>
        <!-- 교육기관회원 반복문 끝 -->
        
      </div>
      <!------------------------ 교육기관회원리스트 영역 끝 ------------------------>



      <!---------------------- 페이지 바 시작 -------------------------->
      
      <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/pageBar.jsp" />
      
      <!------------------------- 페이지 바 끝 ------------------------->
      
    </div>
    <!----------------------------------(메인콘텐츠) 끝 ------------------------------------->

  </div>
