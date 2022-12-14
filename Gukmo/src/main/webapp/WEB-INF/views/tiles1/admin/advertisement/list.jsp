<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 광고리스트 페이지입니다. --%>


<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/advertisement/list.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/advertisement/list.js" ></script>


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



	  <!------------------------ 광고리스트 영역 시작 ------------------------>
      <div class="nomalMemberList">
        <div id="title" class="d-flex justify-content-between py-3 border-top border-bottom">
          <!-- division -->
          <div id="divDivision" class="division text-center">
            <span id="ad_division">구분</span>
            <span id="btn_division_option">
              <i class="fa-solid fa-caret-down"></i>
            </span>
            <div id="divisionOption" class="hidden border rounded px-3 py-2">
              <div id="division_all" class="my-1">전체</div>
              <div id="division_main" class="my-1">메인</div>
              <div id="division_board" class="my-1">게시판</div>
	              <form name="divisionFrm">
	                <input type="hidden" id="division" name="division" value="" />
	                <input type="hidden" id="status" name="status" value="" />
	              </form>
            </div>
          </div>
          
          <!-- client_name -->
          <div class="client_name text-center">
            <div>고객명</div>
          </div>
          
          <!-- client_phone -->
          <div class="client_phone text-center">
            <div>고객연락처</div>
          </div>
          
          <!-- period -->
          <div class="period text-center">
            <div>기간</div>
          </div>
          
          <!-- status -->
          <div id="advertisementStatus" class="status text-center">
            <span>상태</span>
            <span id="btn_status_option">
              <i class="fa-solid fa-caret-down"></i>
            </span>
            <div id="advertisementStatusOption" class="hidden border rounded px-3 py-2">
              <div id="status_all" class="my-1">전체</div>
              <div id="status_ing" class="my-1">진행중</div>
              <div id="status_end" class="my-1">종료</div>
            </div>
          </div>
        </div>





        <!------- 광고리스트 반복문 시작 ------->
        <input type="hidden" id="page_division" value="${requestScope.division}"/>
        <input type="hidden" id="page_status" value="${requestScope.division}"/>
        <c:forEach var="advo" items="${requestScope.adList}" varStatus="status">
        <div class="list_box d-flex justify-content-between py-3 border-bottom" onclick="location.href='<%=ctxPath%>/admin/advertisement/detail.do?advertisement_num=${advo.advertisement_num}'">
          <!-- division -->
          <div class="advertisement_info division text-center page_div">
            <div>${advo.division}</div>
          </div>
          
          <!-- client_name -->
          <div class="advertisement_info client_name text-center">
            <div>${advo.client_name}</div>
          </div>
          
          <!-- client_phone -->
          <div class="advertisement_info client_phone text-center">
            <div>${advo.client_phone}</div>
          </div>
          
          
          <!-- period -->
          <div class="advertisement_info period text-center">
            <div class="m-auto date my-1">
              <span>${advo.start_date}</span>~<span>${advo.end_date}</span>
            </div>
            <div class="m-auto">${advo.period}일</div>
          </div>
          
          <!-- status -->
          <div class="advertisement_info status text-center">
            <div class="m-auto">
             <c:if test="${advo.status == 0}">
	           	진행중
	         </c:if>
	         <c:if test="${advo.status == 1}">
	           	종료
	         </c:if>
            </div>
          </div>
        </div>
        </c:forEach>
        <!-------- 광고리스트 반복문 끝 -------->
      </div>
      <!------------------------ 광고리스트 영역 끝 ------------------------>
	
	

      <!---------------------- 페이지 바 시작 -------------------------->
      
      <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/pagebar.jsp" />
      
      <!------------------------- 페이지 바 끝 ------------------------->
      
      <!-- 광고등록버튼 -->
 	  <div class="d-flex justify-content-end">
 	  	<button type="button" class="btn btn-light border rounded" onclick="location.href='<%=ctxPath %>/admin/advertisement/new.do'">
 	  		<i class="fa-sharp fa-solid fa-plus"></i><span>광고등록</span>
 	  	</button>
 	  </div>     
 	  <!-- 광고등록버튼 끝-->
      
      
    </div>
    <!----------------------------------(메인콘텐츠) 끝 ------------------------------------->

  </div>
