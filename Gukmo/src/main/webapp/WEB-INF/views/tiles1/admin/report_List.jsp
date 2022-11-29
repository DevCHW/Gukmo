<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<%
	String ctxPath = request.getContextPath();
%>



<script type="text/javascript">

	$(document).ready(function(){

		if( ${not empty requestScope.paraMap} ) {

			$("select#report_type").val("${requestScope.paraMap.memberStatus}");
			$("select#searchType").val("${requestScope.paraMap.searchType}");
			$("input#searchWord").val("${requestScope.paraMap.searchWord}");

		}
	}); // end of $(document).ready(function(){})------------------
	
</script>

  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/report_List.css" />
  
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/report_List.js?ver=1" ></script>



	<div id="container" class="container-fluid row mt-5">
	  <div class="col-2">
		  <jsp:include page="/WEB-INF/views/tiles1/admin/sidebar_admin.jsp" />
	  </div>
		
	  <div class="col-9">	  

	    <h4 style="font-weight:bold;">신고 현황 관리</h4>
	
	    <!------------------------------------- 학원 리스트 테이블 시작 ------------------------------------->
	    <table class="table table-hover mt-2">
	      <thead>
	        <tr>
	          <th>신고 분류</th>
	          <th>신고자 닉네임</th>
	          <th>피신고자 닉네임</th>
	          <th>신고 사유</th>
	          <th>신고 사유 상세</th>
	          <th>신고 날짜</th>
	        </tr>
	      </thead>
	        <tbody>
			  <c:forEach var="rvo" items="${requestScope.reportList}" varStatus="status">
	            <tr class = "reportDetail" id="${rvo.report_num}">
	              <td style="cursor:pointer"><span>${rvo.report_type}</span></td>
	              <td style="cursor:pointer" ><span>${rvo.report_nickname}</span></td>
	              <td style="cursor:pointer" ><span>${rvo.reported_nickname}</span></td>
	              <td style="cursor:pointer" ><span>${rvo.simple_report_reason}</span></td>
	              <td style="cursor:pointer"><span>asdf</span></td>
	              <td style="cursor:pointer"><span>${rvo.report_date}</span></td>
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
	
	      <button type="button" id="" class="btn border-0 rounded" onclick="">
	        + 광고 등록
	      </button>
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
	    	<select class = "mx-2" name="report_type" id="report_type" style="height:40px;">
	           <option value="" selected>신고 분류</option>
	           <option value="게시글">게시글</option>
	           <option value="댓글">댓글</option>
	        </select>
	    	<select class = "mx-2" name="searchType" id="searchType" style="height:40px;">
	           <option value="report_nickname" selected>신고자</option>
	           <option value="reported_nickname">신고 받은 자</option>
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


