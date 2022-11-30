<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

   
<%
	String ctxPath = request.getContextPath();
%>

<script type="text/javascript">

	$(document).ready(function(){

		
		if( ${not empty requestScope.paraMap} ) {

			$("select#memberStatus").val("${requestScope.paraMap.memberStatus}");
			$("select#searchType").val("${requestScope.paraMap.searchType}");
			$("input#searchWord").val("${requestScope.paraMap.searchWord}");

		}
	}); // end of $(document).ready(function(){})------------------
	
</script>


  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/memberManage_List.css" />
  
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/memberManage_List.js?ver=1" ></script>

	<div id="container" class="container-fluid row mt-5">
	  <div class="col-2">
		  <jsp:include page="/WEB-INF/views/tiles1/admin/sidebar_admin.jsp" />
	  </div>
		
	  <div class="col-9">	  

		
		    <h4 style="font-weight:bold;">일반 회원 관리</h4>
		
		    <!------------------------------------- 학원 리스트 테이블 시작 ------------------------------------->
		    <table class="table table-hover mt-2">
		      <thead>
		        <tr>
		          <th>아이디</th>
		          <th>이름</th>
		          <th>닉네임</th>
		          <th>이메일</th>
		          <th>가입일자</th>
		          <th>계정 상태</th>
		          
		        </tr>
		      </thead>
		      <tbody>
				<c:forEach var="membervo" items="${requestScope.memberList}" varStatus="status">
		            <tr class = "memberDetail" id="${membervo.userid}">
		              <td style="cursor:pointer">
			              <span id="">
			              	<c:if test="${membervo.userid.length() >= 10}" >
			              		${membervo.userid.substring(0, 10)}...
			              	</c:if>
			              	<c:if test="${membervo.userid.length() < 10}" >
			              		${membervo.userid}
			              	</c:if>
			              </span>
		              </td>
		              <td style="cursor:pointer"><span>${membervo.username}</span></td>
		              <td style="cursor:pointer"><span>${membervo.nickname}</span></td>
		              <td style="cursor:pointer"><span>${membervo.email}</span></td>
		              <td style="cursor:pointer"><span>${membervo.join_date}</span></td>
		              <td style="cursor:pointer"><span>${membervo.status}</span></td>
		            </tr>    
		          </c:forEach>
		    </tbody>
		    </table>
		    <!----------------------------------- 학원 리스트 테이블 끝 ------------------------------------->
		
		    <div class="d-flex justify-content-between">
		
		      <div id="total_cnt">
		        총&nbsp;<span style="font-weight:bold;">${requestScope.totalCount}&nbsp;</span>건
		        <!-- 총 건수 변수 들어갈 곳-->
		      </div>
		
		    </div>
		
		
		
		
		
		    <!----------------------------------------------------------- 페이지 바 시작 --------------------------------------------->
		    <nav aria-label="...">
				${requestScope.pageBar}
		    </nav>
				<!----------------------------------------------------------- 페이지 바 끝 --------------------------------------------->
		  </div>
		</div>

  <!-- 검색바시작 -->
    <form name="searchFrm" style="margin-top: 20px;">    	
	    <div id="search_area" class="d-flex mr-3" style="justify-content:center">
	       <div class= "d-flex rounded">
	    	<select class = "mx-2" name="memberStatus" id="memberStatus" style="height:40px;">
	           <option value="" selected>계정 상태</option>
	           <option value="활동">활동</option>
	           <option value="정지">정지</option>
	           <option value="휴면">휴면</option>
	        </select>
	    	<select class = "mx-2" name="searchType" id="searchType" style="height:40px;">
	           <option value="" selected>소분류</option>
	           <option value="fk_userid">아이디</option>
	           <option value="username">이름</option>
	           <option value="nickname">닉네임</option>
	        </select>
    	  </div>
	      <div id="academy_search" class="d-flex rounded">
	        <div id="input_keyword">
	          <input type="text" id="searchWord" placeholder="검색" class="pl-3" name="searchWord" autocomplete="off">
        	  <input type="text" style="display: none;" /> <%-- form 태그내에 input 태그가 오로지 1개 뿐일경우에는 엔터를 했을 경우 검색이 되어지므로 이것을 방지하고자 만든것이다. --%> 
	        </div>
	        <div id="search_btn">
	          <button type="button" class="btn btn-white" id="btn_search" onclick="goSearch()"><i class="fas fa-xl fa-thin fa-magnifying-glass"></i></button>
	        </div>
	      </div>
	    </div>
	</form>
	<br><br>
    <!-- 검색바 끝 -->


