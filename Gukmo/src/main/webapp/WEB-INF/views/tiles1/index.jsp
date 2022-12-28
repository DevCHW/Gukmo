<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath%>/resources/css/hasol/index.css?after">

<!-- contents 시작 -->

<!-- 배너 영역 -->

<!-- Indicators -->
 <c:if test="${not empty requestScope.advertisementList}">
	
	<div id="demo" class="carousel slide" data-ride="carousel" >
     
     <%--  The slideshow --%>
     <div class="carousel-inner" style="height:230px;">              
       
     <div class="carousel-item active">
     	<c:forEach end="0" var="advertisementList" items="${requestScope.advertisementList}" varStatus="status">
     	<a href="${advertisementList.url}">            		
              <img src="<%=ctxPath %>/resources/images/${advertisementList.filename}" style="cursor: pointer; width: 100%; height:230px;">                       
         </a>
         </c:forEach>
     </div>       
       
     <c:forEach begin="1" var="advertisementList" items="${requestScope.advertisementList}" varStatus="status">
  	 <div class="carousel-item">                      
          <a href="${advertisementList.url}">
           <img src="<%=ctxPath %>/resources/images/${advertisementList.filename}" style="cursor: pointer; width: 100%; height:230px;">
          </a>                  
     </div>       
	 </c:forEach>
     
       
     </div>   
     
     <a class="carousel-control-prev" href="#demo" data-slide="prev">
       <span class="carousel-control-prev-icon"></span>
     </a>
     <a class="carousel-control-next" href="#demo" data-slide="next">
       <span class="carousel-control-next-icon"></span>
     </a>
   
</div>	
</c:if>

<c:if test="${empty requestScope.advertisementList}">
	<div id="advertisement_box" style="width: 100%; height:230px; cursor: pointer;" onclick="location.href='https://kfq.or.kr/_KR/Default.aspx'">
      <img src="<%= ctxPath%>/resources/images/학원광고이미지1.PNG" style="width: 100%; height:230px;">
    </div>
</c:if>



<div class="container d-flex flex-column align-items-center">

    <%-- 검새창 영역 --%>
    <jsp:include page="/WEB-INF/views/tiles1/board/searchBar/main_searchBar.jsp" />


	<%-- 학원 정보 영역 --%>
	<div class="div_academy mt-2 d-flex flex-column align-items-center" >
		
		<div class="academy_title w-100 px-2 mt-4 mb-1 d-flex justify-content-between">
			<span>현재 모집 중인 과정</span>
			<a href="<%=ctxPath %>/academy/curricula.do"> 더 보기 </a>
		</div>
		
		<%-- 배너 영역 --%>
		<div id="div_academy_content" class="carousel slide w-100" data-ride="carousel" >
		   		     
			<div class="carousel-inner academy-carousel px-2">
			
				<c:if test="${empty requestScope.curriList1}">
				${html}
				</c:if>
				
				<!-- 첫번째! -->
		        <c:if test="${not empty requestScope.curriList1}">		        
		        <div class="carousel-item active">
					<div class="d-flex flex-wrap justify-content-start">
					<c:forEach var="curriList1" items="${requestScope.curriList1}">
						<div class="card">
						  <div class="card-body" onclick="location.href='<%=ctxPath %>/detail.do?boardNum=${curriList1.board_num}'">
						    <h4 class="card-title">${curriList1.curriculum.academy_name}</h4>
						    <p class="card-text">${curriList1.subject}</p>
						    <p class="card-sub-text">${curriList1.curriculum.curriculum_start_date} ~ ${curriList1.curriculum.curriculum_end_date}</p>
						    <p class="card-link">D-${curriList1.dday}</p>
						  </div>
						</div>
					</c:forEach>
					</div>
				</div>
				</c:if>
				
				<c:if test="${not empty requestScope.curriList2}">
		        <div class="carousel-item">
					<div class="d-flex flex-wrap justify-content-start">
					<c:forEach var="curriList2" items="${requestScope.curriList2}">
						<div class="card">
						  <div class="card-body" onclick="location.href='<%=ctxPath %>/detail.do?boardNum=${curriList2.board_num}'">
						    <h4 class="card-title">${curriList2.curriculum.academy_name}</h4>
						    <p class="card-text">${curriList2.subject}</p>
						    <p class="card-sub-text">${curriList2.curriculum.curriculum_start_date} ~ ${curriList2.curriculum.curriculum_end_date}</p>
						    <p class="card-link">D-${curriList2.dday}</p>
						  </div>
						</div>
					</c:forEach>	
					</div>
				</div>
				</c:if>
		        
				<c:if test="${not empty requestScope.curriList3}">
		        <div class="carousel-item">
					<div class="d-flex flex-wrap justify-content-start">
					<c:forEach var="curriList3" items="${requestScope.curriList3}">
						<div class="card">
						  <div class="card-body" onclick="location.href='<%=ctxPath %>detail.do?boardNum=${curriList3.board_num}'">
						    <h4 class="card-title">${curriList3.curriculum.academy_name}</h4>
						    <p class="card-text">${curriList3.subject}</p>
						    <p class="card-sub-text">${curriList3.curriculum.curriculum_start_date} ~ ${curriList3.curriculum.curriculum_end_date}</p>
						    <p class="card-link">D-${curriList3.dday}</p>
						  </div>
						</div>
					</c:forEach>	
					</div>
				</div>
				</c:if>
				
		     </div>
		   
		     <!-- Left and right controls -->
		     <a class="carousel-control-prev icon_prev" href="#div_academy_content" data-slide="prev">
		       <i class="fa-solid fa-circle-left fa-7x icon_arrow"></i>
		     </a>
		     <a class="carousel-control-next icon_next" href="#div_academy_content" data-slide="next">
		       <i class="fa-solid fa-circle-right fa-7x icon_arrow"></i>
		     </a>
		   
		</div>
		
	
	
	<!-- 인기검색어 / 게시판 정보 영역  -->
	<div class="div_infoBoard mt-2 mb-5 w-100 d-flex ">
		
		<!-- 인기 검색어 영역 -->
		<div class="div_searchRank col-3" style="padding:0 0 0 2px;">
			<div class="div2_searchRank d-flex flex-column">
				<div class="title_searchRank d-flex justify-content-start align-items-center">
					<p>&#x1F451;</p>
					<p style="font-size:15px; margin-left:3px; color:#208EC9;">인기 검색어 </p>
				</div>
				<div class="list_searchRank d-flex flex-column px-3">
					<c:forEach var="topSearch" items="${requestScope.topSearchList}">
					<a id="keyword">${topSearch.keyword}</a>
					</c:forEach>
				</div>
			</div>
		</div>
		
		
		<!-- 게시판 정보영역 -->
		<div class="div_board d-flex flex-wrap">
			<!-- Qna 영역 -->			
			<div class="div_tbl_board d-flex flex-column">	
					<!-- 테이블 제목 -->
					<div class="title_board d-flex justify-content-start align-items-center">
						<p style="font-size:15px; font-weight: 500; margin: 0;" >&#x1F635;</p>
						<a href="<%= ctxPath%>/community/questions.do" style="font-size:15px; margin-left:3px; color:white;">QnA </a>
					</div>
					<!-- 테이블 리스트 -->
					<div class="list_board d-flex flex-column">
						<c:if test="${empty requestScope.qnaBoardList}">
						 <span id="no_write_list">등록된 글이 없습니다.</span>
						</c:if>
						<!-- 게시글 1개 영역 -->
						<c:forEach var="boardvo" items="${requestScope.qnaBoardList}">
					    <div class="div_boardList py-2 px-2">
					    	<!-- 작성 정보 -->
					      	<div class="div_writerInfo d-flex justify-content-between align-items-center mb-1">
						        <div class="d-flex" style="width:250px;">
							        <!-- 작성자 프로필사진 -->
							        <div class="writer_image_box">
							          <img src="<%= ctxPath%>/resources/images/${boardvo.profile_image}"/>
							        </div>
						
							        <!-- 작성자 닉네임 -->
							        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
							        <a href="<%=ctxPath %>/member/activityOther.do?nickname=${boardvo.nickname}" class="writer_nickname ml-2">
							         	 ${boardvo.nickname}
							        </a>
						
							        <!-- 작성자 활동점수 -->
							        <div class="writer_point text-align-center ml-2">
							          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
							          <span>${boardvo.writer_point}</span>
							        </div>
							
							        <!-- 작성일자 -->
							        <div class="write_date ml-2"> ${boardvo.write_date}</div>
					        	</div>
					        	<!-- 조회수,댓글수,추천수 -->
					         	<div class="board_info_box d-flex justify-content-end align-items-center">
						            <!-- 조회수 -->
						            <div>
						            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
						             	<span>${boardvo.views}</span>
						           	</div>
						
						           	<!-- 댓글수 -->
						           	<div class="ml-2">
						             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
						             	<span>${boardvo.comment_cnt}</span>
						           	</div>
						
						           	<!-- 추천수 -->
						           	<div class="ml-2">
						             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
						             	<span>${boardvo.like_cnt}</span>
						           	</div>
				         		</div>
				       		</div>
	
					       <!-- 글제목 -->
					       <a href="<%= ctxPath%>/detail.do?boardNum=${boardvo.board_num}" class="subject align-items-center my-1">
					         ${boardvo.subject}
					       </a>
						</div>
						</c:forEach>
					</div>
				</div>	
	
			<!-- study 영역 -->	
			<div class="div_tbl_board d-flex flex-column">	
				<!-- 테이블 제목 -->
				<div class="title_board d-flex justify-content-start align-items-center">
					<p style="font-size:15px; font-weight: 500; margin: 0;" >&#x1F64B;</p>
					<a href="<%=ctxPath %>/community/studies.do" style="font-size:15px; margin-left:3px; color:white;"> 스터디모집 </a>
				</div>
				<!-- 테이블 리스트 -->
				<div class="list_board d-flex flex-column">
					<c:if test="${empty requestScope.studyBoardList}">
						 <span id="no_write_list">등록된 글이 없습니다.</span>
					</c:if>
					<!-- 게시글 1개 영역 -->
					<c:forEach var="boardvo" items="${requestScope.studyBoardList}">
				    <div class="div_boardList py-2 px-2">
				    	<!-- 작성 정보 -->
				      	<div class="div_writerInfo d-flex justify-content-between align-items-center mb-1">
					        <div class="d-flex" style="width:250px;">
						        <!-- 작성자 프로필사진 -->
						        <div class="writer_image_box">
						          <img src="<%= ctxPath%>/resources/images/${boardvo.profile_image}"/>
						        </div>
					
						        <!-- 작성자 닉네임 -->
						        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
						        <a href="<%=ctxPath %>/member/activityOther.do?nickname=${boardvo.nickname}" class="writer_nickname ml-2">
						         	 ${boardvo.nickname}
						        </a>
					
						        <!-- 작성자 활동점수 -->
						        <div class="writer_point text-align-center ml-2">
						          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
						          <span>${boardvo.writer_point}</span>
						        </div>
						
						        <!-- 작성일자 -->
						        <div class="write_date ml-2"> ${boardvo.write_date}</div>
				        	</div>
				        	<!-- 조회수,댓글수,추천수 -->
				         	<div class="board_info_box d-flex justify-content-end align-items-center">
					            <!-- 조회수 -->
					            <div>
					            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
					             	<span>${boardvo.views}</span>
					           	</div>
					
					           	<!-- 댓글수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
					             	<span>${boardvo.comment_cnt}</span>
					           	</div>
					
					           	<!-- 추천수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
					             	<span>${boardvo.like_cnt}</span>
					           	</div>
			         		</div>
			       		</div>

				        <!-- 글제목 -->
				        <a href="<%= ctxPath%>/detail.do?boardNum=${boardvo.board_num}" class="subject align-items-center my-1">
				          ${boardvo.subject}
				        </a>
					</div>
					</c:forEach>
				</div>			
			</div>
			
			<!-- 자유게시판 영역 -->			
			<div class="div_tbl_board d-flex flex-column">	
				<!-- 테이블 제목 -->
				<div class="title_board d-flex justify-content-start align-items-center">
				
					<p style="font-size:15px; font-weight: 500; margin: 0;" >&#x1F4AC;</p>
					<a href="<%= ctxPath%>/community/freeBoards.do" style="font-size:15px; margin-left:3px; color:white;"> 자유게시판 </a>
				</div>
				<!-- 테이블 리스트 -->
				<div class="list_board d-flex flex-column">
				    <c:if test="${empty requestScope.freeBoardList}">
					  <span id="no_write_list">등록된 글이 없습니다.</span>
					</c:if>
					<!-- 게시글 1개 영역 -->
					<c:forEach var="boardvo" items="${requestScope.freeBoardList}">
				    <div class="div_boardList py-2 px-2">
				    	<!-- 작성 정보 -->
				      	<div class="div_writerInfo d-flex justify-content-between align-items-center mb-1">
					        <div class="d-flex" style="width:250px;">
						        <!-- 작성자 프로필사진 -->
						        <div class="writer_image_box">
						          <img src="<%= ctxPath%>/resources/images/${boardvo.profile_image}"/>
						        </div>
					
						        <!-- 작성자 닉네임 -->
						        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
						        <a href="<%=ctxPath %>/member/activityOther.do?nickname=${boardvo.nickname}" class="writer_nickname ml-2">
						         	 ${boardvo.nickname}
						        </a>
					
						        <!-- 작성자 활동점수 -->
						        <div class="writer_point text-align-center ml-2">
						          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
						          <span>${boardvo.writer_point}</span>
						        </div>
						
						        <!-- 작성일자 -->
						        <div class="write_date ml-2"> ${boardvo.write_date}</div>
				        	</div>
				        	<!-- 조회수,댓글수,추천수 -->
				         	<div class="board_info_box d-flex justify-content-end align-items-center">
					            <!-- 조회수 -->
					            <div>
					            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
					             	<span>${boardvo.views}</span>
					           	</div>
					
					           	<!-- 댓글수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
					             	<span>${boardvo.comment_cnt}</span>
					           	</div>
					
					           	<!-- 추천수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
					             	<span>${boardvo.like_cnt}</span>
					           	</div>
			         		</div>
			       		</div>

				        <!-- 글제목 -->
				        <a href="<%= ctxPath%>/detail.do?boardNum=${boardvo.board_num}" class="subject align-items-center my-1">
				          ${boardvo.subject}
				        </a>
					</div>
					</c:forEach>
				</div>	
			
			</div>	
			
			<!-- 후기 영역 -->
			<div class="div_tbl_board d-flex flex-column">	
				<!-- 테이블 제목 -->
				<div class="title_board d-flex justify-content-start align-items-center">
					<p style="font-size:15px; font-weight: 500; margin: 0;" >&#x1F44D;</p>
					<a href="<%= ctxPath%>/community/reviews.do" style="font-size:15px; margin-left:3px; color:white;"> 후기/정보공유 </a>
				</div>
				<!-- 테이블 리스트 -->
				<div class="list_board d-flex flex-column">
				    <c:if test="${empty requestScope.reviewBoardList}">
					  <span id="no_write_list">등록된 글이 없습니다.</span>
					</c:if>
					<!-- 게시글 1개 영역 -->
					<c:forEach var="boardvo" items="${requestScope.reviewBoardList}">
				    <div class="div_boardList py-2 px-2">
				    	<!-- 작성 정보 -->
				      	<div class="div_writerInfo d-flex justify-content-between align-items-center mb-1">
					        <div class="d-flex" style="width:250px;">
						        <!-- 작성자 프로필사진 -->
						        <div class="writer_image_box">
						          <img src="<%= ctxPath%>/resources/images/${boardvo.profile_image}"/>
						        </div>
					
						        <!-- 작성자 닉네임 -->
						        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
						        <a href="<%=ctxPath %>/member/activityOther.do?nickname=${boardvo.nickname}" class="writer_nickname ml-2">
						         	 ${boardvo.nickname}
						        </a>
					
						        <!-- 작성자 활동점수 -->
						        <div class="writer_point text-align-center ml-2">
						          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
						          <span>${boardvo.writer_point}</span>
						        </div>
						
						        <!-- 작성일자 -->
						        <div class="write_date ml-2"> ${boardvo.write_date}</div>
				        	</div>
				        	<!-- 조회수,댓글수,추천수 -->
				         	<div class="board_info_box d-flex justify-content-end align-items-center">
					            <!-- 조회수 -->
					            <div>
					            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
					             	<span>${boardvo.views}</span>
					           	</div>
					
					           	<!-- 댓글수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
					             	<span>${boardvo.comment_cnt}</span>
					           	</div>
					
					           	<!-- 추천수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
					             	<span>${boardvo.like_cnt}</span>
					           	</div>
			         		</div>
			       		</div>
				        <!-- 글제목 -->
				        <a href="<%= ctxPath%>/detail.do?boardNum=${boardvo.board_num}" class="subject align-items-center my-1">
				          ${boardvo.subject}
				        </a>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
