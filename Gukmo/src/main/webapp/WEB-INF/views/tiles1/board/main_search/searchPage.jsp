<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath%>/resources/css/hasol/searchPage.css?after">

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hasol/searchPage.js" ></script>





<!-- contents 시작 -->

<div class="container d-flex flex-column align-items-center">
	

    <!-- 검새창 영역 -->
    <jsp:include page="/WEB-INF/views/tiles1/board/searchBar/main_searchBar.jsp" />
	
	
	<div class="div_searchList w-100 my-3 d-flex flex-column justify-content-start">
		<span class="my-2"> 검색 결과 총 ${requestScope.totalCnt} 건</span>
		
		<c:if test = "${requestScope.totalCnt eq '0'}">
			<div class="my-5 w-100 d-flex justify-content-around"> 조회된 결과가 없습니다. </div>
		</c:if>
		
		<c:if test = "${requestScope.totalCnt ne '0'}">
			<%-- 게시글 반복문 시작 --%>
			<c:forEach var="boardvo" items="${requestScope.searchList}" varStatus="status">
			
			<!-- 국비학원 카테고리일 경우 -->
			<c:if test="${boardvo.category eq '국비학원'}">
			
			<!-- 국비학원 - 교육과정일 경우 -->
			<c:if test="${boardvo.detail_category eq '교육과정'}">
			<div class="div_searchResult w-100 px-2 py-3 border-top">							
				<%-- 카테고리 영역 --%>
				<div class="d-flex align-items-center">
		            <a class="detail_category border rounded px-2" href="<%=ctxPath %>/academy/curricula.do"> 
		            	  ${boardvo.detail_category}
		        	</a>
		        </div>
		          
		        <%-- 글제목 --%>
		        <div class="subject my-2">
			        <a href="<%=ctxPath %>/academy/academy.do?boardNum=${boardvo.board_num}">
			          ${boardvo.subject}
			        </a>
			    </div>
	     
		        <%-- 학원이름 --%>
		        <div class="content d-flex flex-column">
		          <span class="mr-2 my-1">학원 명 : ${boardvo.curriculum.academy_name}</span>
	        	  <span class="mr-2 mb-1">교육 일정 : ${boardvo.curriculum.curriculum_start_date} ~ ${boardvo.curriculum.curriculum_end_date} </span>
	        	  <span class="mr-2">문의처: ${boardvo.curriculum.join_url}</span>
		        </div>          
	      	</div>
			</c:if>   
	
			<!-- 국비학원-학원 카테고리일 경우 -->
			<c:if test="${boardvo.detail_category eq '국비학원'}">
			<div class="div_searchResult d-flex w-100 px-2 py-3 border-top">			
			    <%-- 학원 사진 --%>
				<div class="academy_img_box mr-2" onclick="location.href='<%=ctxPath %>/academy/academy.do?boardNum=${boardvo.board_num}'">
					<img src="<%=ctxPath %>/resources/images/${boardvo.academy.academy_image}">
				</div>
				
				<div class="d-flex flex-column">     		
					<%-- 카테고리 정보 --%>
					<div class="d-flex align-items-center">
			            <%-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 변수 말고 아예 값 박아도 됨--%>
			            <a class="detail_category border rounded px-2" href="<%=ctxPath %>academy/academies.do">
			            	  ${boardvo.detail_category}
			            </a>
			        </div>  
			        
			        <%-- 글제목 --%>
			        <div class="subject my-2">
				        <a href="<%=ctxPath %>/academy/academy.do?boardNum=${boardvo.board_num}">
				          ${boardvo.subject}
				        </a>
			        </div>
		     
			        <%-- 학원주소 --%>
			        <div class="content">
			          <span class="mr-2 my-1">주소 : ${boardvo.academy.address}</span>
			          <span class="mr-2 mb-1">문의처 : ${boardvo.academy.phone}</span>
					  <a class="mr-2" href="${boardvo.academy.homepage}"> 홈페이지 : ${boardvo.academy.homepage}</a>
			        </div>
	       
		      	</div>
	    	</div>  
	
			</c:if>   
			</c:if>
			
	    	<!-- 일반 커뮤니티 게시글 -->
			<c:if test="${boardvo.category ne '국비학원'}">
			<div class="div_searchResult w-100 px-2 py-3 border-top">	
			<%-- 카테고리 영역 --%>
				<div class="d-flex align-items-bottom">
		            <c:if test="${boardvo.detail_category eq '자유게시판'}">
		            <a class="detail_category border rounded px-2" href="<%=ctxPath %>/community/freeBoards.do"> 
		            	  ${boardvo.detail_category}
		        	</a>
		        	</c:if>
		        	
		        	<c:if test="${boardvo.detail_category eq '스터디'}">
		            <a class="detail_category border rounded px-2" href="<%=ctxPath %>/community/studies.do"> 
		            	  ${boardvo.detail_category}
		        	</a>
		        	</c:if>
		        	
		        	<c:if test="${boardvo.detail_category eq 'QnA'}">
		            <a class="detail_category border rounded px-2" href="<%=ctxPath %>/community/questions.do"> 
		            	  ${boardvo.detail_category}
		        	</a>
		        	</c:if>
		        	
		        	<c:if test="${boardvo.detail_category eq '수강/취업후기'}">
		            <a class="detail_category border rounded px-2" href="<%=ctxPath %>/community/hobbies.do"> 
		            	  ${boardvo.detail_category}
		        	</a>
		        	</c:if>
		        	
		        	<c:if test="${boardvo.detail_category eq '공지사항'}">
		            <a class="detail_category border rounded px-2" href="<%=ctxPath %>/community/freeBoards.do"> 
		            	  ${boardvo.detail_category}
		        	</a>
		        	</c:if>
		        	
		        </div>  
				<div class="subject d-flex align-items-end my-2"> 
					<a href="<%= ctxPath%>/detail.do?boardNum=${boardvo.board_num}">${boardvo.subject}</a>
					<span class="write_date ml-3">${boardvo.write_date}</span>
				</div> 
				<div class="content my-1">
					<a href="<%= ctxPath%>/detail.do?boardNum=${boardvo.board_num}"> ${boardvo.content}
						<%-- <input id="hidden_content${status.index}" type="hidden" value="${boardvo.content}"></span> --%>
					</a>
				</div>	
				<div class="hashtag d-flex ">
					<c:forEach var="hashtags" items="${boardvo.hashtags}">
					<span class="mr-3">#${hashtags.hashtag}</span>
					</c:forEach>  
				</div>                                            
			</div>
			</c:if>
			</c:forEach>
			<%-- 게시글 반복문 끝 --%>
		</div>
		
		<nav>
			${requestScope.pageBar}
		</nav>

	</c:if>
</div>
