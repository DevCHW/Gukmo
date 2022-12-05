<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<%
	String ctxPath = request.getContextPath();
%>

<script type="text/javascript">

	$(document).ready(function(){

		if( ${not empty requestScope.paraMap} ) {

			$("select#division").val("${requestScope.paraMap.memberStatus}");
			$("select#searchType").val("${requestScope.paraMap.searchType}");
			$("input#searchWord").val("${requestScope.paraMap.searchWord}");

		}
	}); // end of $(document).ready(function(){})------------------
	
</script>


  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/seongmin/memberDetail.css" />

  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/memberDetail.js" ></script>
	
	<div id="container" class="container-fluid row mt-5">
	  <div class="col-2">
		  <jsp:include page="/WEB-INF/views/tiles1/admin/sidebar_admin.jsp" />
	  </div>
		
	  <div class="col-9 main">	  

	    <%----------------------------------- main 시작-------------------------------------%>
	      <%-- main_header --%>
	      <div class="main_header border rounded">
	        <%-- top --%>
	        <div class="d-flex">
	          <div class="d-flex w-100 justify-content-between py-4 px-3 align-items-center">
	            <div class="d-flex align-items-center">
	              <%-- 프사 --%>
	              <div id="profile_img_box" class="border">
	                <img src="<%=ctxPath %>/resources/images/${sessionScope.user.profile_image}"/>
	              </div>
	      
	              <div class="ml-4 py-1 contents">
	                <h3 id="user_nickname">${requestScope.memberDetail.nickname}</h3>
	                <br>
	                <div id="userid">아이디 : &nbsp;<span>${requestScope.memberDetail.userid}</span></div>
	                <div id="username">이름 : &nbsp;<span>${requestScope.memberDetail.username}</span></div>
	                <div id="email">이메일 주소 : &nbsp;<span>${requestScope.memberDetail.email}</span></div>
	                <div id="point">활동점수 : &nbsp;<span>${requestScope.memberDetail.point}</span></div>
	                <div id="email_acept">이메일 수신 동의 여부 : &nbsp;
		                <span>
		                	<c:if test="${requestScope.memberDetail.email_acept == 0}">
		                		거부
							</c:if>
		                	<c:if test="${requestScope.memberDetail.email_acept == 1}">
		                		동의
							</c:if>
		                </span>
	                </div>
	                <div id="status">계정 상태 : &nbsp;<span>${requestScope.memberDetail.status}</span></div>
	                <div id="join_date">가입 일자 : &nbsp;<span>${requestScope.memberDetail.join_date}</span></div>
	              </div>
	            </div>
	              <input type="hidden" name="nickname" id="${requestScope.memberDetail.nickname}" />
	
				  <c:if test="${requestScope.memberDetail.status == '활동'}">
			        <button type="button" class = "btn border rounded memberBlock" id="${requestScope.memberDetail.userid}" >정지 등록</button>&nbsp;
			      </c:if>
				  <c:if test="${requestScope.memberDetail.status == '정지'}">
			        <button type="button" id="" class="btn border rounded block_recovery">정지 해제</button>&nbsp;
			      </c:if>
			      
				  <c:if test="${requestScope.memberDetail.status == '휴면'}">
		            <button type="button" class = "btn border rounded memberBlock" id="${requestScope.memberDetail.userid}" >정지 등록</button>&nbsp;
		            <button type="button" id="" class="btn border rounded sleep_recovery">휴면 해제</button>
				  </c:if>           
	          </div>
	        </div>
	        
	        <%-- bottom --%>
	        <div id="navbar" class="d-flex justify-content-center border-top">
	          <div id="actList_btn" class="filter mx-4 py-3">
				활동내역		    
	          </div>
	          <div class="filter mx-4 py-3">
	            	게시물
	          </div>
	        </div>
	          <div id = "actList" class="tab-pane">
	           	<table class="table table-hover mt-2" style="width:100%;">
			      <thead>
			        <tr>
			          <th>활동 구분</th>
			          <th>게시글 번호</th>
			          <th>게시글 제목</th>
			          <th>영역</th>
			          <th>활동 일자</th>			          
			        </tr>
			      </thead>
			      <tbody>
					<c:forEach var="avo" items="${requestScope.actList}" varStatus="status">
			            <tr class = "goView" id="${avo.fk_board_num}">
			              <td style="cursor:pointer" ><span>${avo.division}</span></td>
			              <td style="cursor:pointer" ><span>${avo.fk_board_num}</span></td>
			              <td style="cursor:pointer" ><span>${avo.subject}</span></td>
			              <td style="cursor:pointer" ><span>${avo.detail_category}</span></td>
			              <td style="cursor:pointer" ><span>${avo.activity_date}</span></td>
			            </tr>    
			          </c:forEach>
			      </tbody>
				</table>
		  	  <!----------------------------------------------------------- 페이지 바 시작 --------------------------------------------->				
				<nav aria-label="...">
					${requestScope.pageBar}
			    </nav>
		   	  <!----------------------------------------------------------- 페이지 바 끝 --------------------------------------------->
				
		      <%-- 검색바시작 --%>
			    <form name="searchFrm" style="margin-top: 20px;">    	
	              <input type="hidden" name="userid" id="${requestScope.memberDetail.userid}" />
				    <div id="search_area" class="d-flex mr-3" style="justify-content:center">
				       <div class= "d-flex rounded">
				    	<select class = "mx-2" name="division" id="division" style="height:40px;">
				           <option value="" selected>활동 구분</option>
				           <option value="게시글작성">게시글작성</option>
				           <option value="댓글작성">댓글작성</option>
				           <option value="게시글좋아요">게시글좋아요</option>
				           <option value="댓글좋아요">댓글좋아요</option>
				        </select>
				    	<select class = "mx-2" name="searchType" id="searchType" style="height:40px;">
				           <option value="" selected>소분류</option>
				           <option value="detail_category">활동 영역</option>
				           <option value="subject" >게시글 제목</option>
				        </select>
			    	  </div>
				      <div id="academy_search" class="d-flex rounded">
				        <div id="input_keyword">
				          <input type="text" id="searchWord" placeholder="검색" class="pl-3" name="searchWord" autocomplete="off">
			        	  <input type="text" style="display: none;" /> <%-- form 태그내에 input 태그가 오로지 1개 뿐일경우에는 엔터를 했을 경우 검색이 되어지므로 이것을 방지하고자 만든것이다. --%> 
				        </div>
				        <div id="search_btn">
				          <button type="button" class="btn btn-white" id="btn_search" onclick="goSearch('${requestScope.memberDetail.userid}')"><i class="fas fa-xl fa-thin fa-magnifying-glass"></i></button>
				        </div>
				      </div>
				    </div>
				</form>
			  <%-- 검색바 끝 --%>
			</div>	
	      </div>
	    </div>
	    <%----------------------------------- main 끝-------------------------------------%>
	  </div>
	 

			
			<div class="tab-content" id="nav-tabContent">
			  <div class="tab-pane fade show active" id="nav-memberDetail" role="tabpanel" aria-labelledby="nav-memberDetail-tab" tabindex="0">
				<br>
			  </div>			  
			</div>
			  <div class="tab-pane fade show" id="nav-activity" role="tabpanel" aria-labelledby="nav-activity-tab" tabindex="0">
			    <!------------------------------------- 활동내역 리스트 테이블 시작 ------------------------------------->

				<br><br>
			    <!-- 검색바 끝 -->
			    
    
			  </div>
			  

					
      <br>

