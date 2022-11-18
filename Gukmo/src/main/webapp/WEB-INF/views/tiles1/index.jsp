<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath%>/resources/css/hasol/index.css?after">

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hasol/index.js" ></script>

<!-- contents 시작 -->

<!-- 배너 영역 -->
<div id="demo" class="carousel slide" data-ride="carousel" >

	  <!-- Indicators -->
	  <ul class="carousel-indicators">
	    <li data-target="#demo" data-slide-to="0" class="active"></li>
	    <li data-target="#demo" data-slide-to="1"></li>
	    <li data-target="#demo" data-slide-to="2"></li>
	  </ul>
	
	  <!-- The slideshow -->
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="<%= ctxPath %>/resources/images/main/fake_main_banner01.png" alt="">
	    </div>
	    <div class="carousel-item">
	      <img src="<%= ctxPath %>/resources/images/main/fake_main_banner02.png" alt="">
	    </div>
	    <div class="carousel-item">
	      <img src="<%= ctxPath %>/resources/images/main/fake_main_banner03.png" alt="">
	    </div>
	  </div>
	
	  <!-- Left and right controls -->
	  <a class="carousel-control-prev" href="#demo" data-slide="prev">
	    <span class="carousel-control-prev-icon"></span>
	  </a>
	  <a class="carousel-control-next" href="#demo" data-slide="next">
	    <span class="carousel-control-next-icon"></span>
	  </a>
	
</div>



<div class="container d-flex flex-column align-items-center">

    <!-- 검색창 영역 -->
	<div class="searchBar d-flex my-5 col-9">
		<div class="div_selectSearch">                 
		    <label for="selectSearch">학원명</label>                           
	        <select name="selectSearch" id="selectSearch">
	            <option value="academy_name" selected>학원명</option>
	            <option value="subject_name">과정명</option>
	            <option value="board">게시글</option>
	            <option value="hashtag">해시태그</option>
	        </select>
		</div>
		<input type="text" id="searchWord" placeholder="검색할 내용을 입력해 주세요!"></input>
		<button type="button" id="btn_search">
          <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
        </button>
	</div>


	<!-- 학원 정보 영역 -->
	<div class="div_academy d-flex justify-content-between align-items-center" >
		<i class="fa-solid fa-circle-left fa-3x icon_arrow"></i>
		
		<!-- 나중에 반복문 짜야함 -->
		<div class="div_card col-10">
			<div class="card_1 d-flex">
<%-- 				<c:forEach var="curriculumVO" item="${requestScope.curriculumList}" varStatus="status">
				
					<div class="card col-3">
					  <div class="card-body" onclick="location.href='#'">
					    <h4 class="card-title">쌍용강북교육센터</h4>
					    <p class="card-text">빅데이터 전문가 과정</p>
					    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
					    <p class="card-link">D-1</p>
					  </div>
					</div>
				
				</c:forEach> --%>
				<div class="card col-3">
				  <div class="card-body" onclick="location.href='#'">
				    <h4 class="card-title">쌍용강북교육센터</h4>
				    <p class="card-text">빅데이터 전문가 과정</p>
				    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
				    <p class="card-link">D-1</p>
				  </div>
				</div>
				<div class="card col-3">
				  <div class="card-body" onclick="location.href='#'">
				    <h4 class="card-title">쌍용강북교육센터</h4>
				    <p class="card-text">빅데이터 전문가 과정</p>
				    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
				    <p class="card-link">D-1</p>
				  </div>
				</div>
				<div class="card col-3">
				  <div class="card-body" onclick="location.href='#'">
				    <h4 class="card-title">쌍용강북교육센터</h4>
				    <p class="card-text">빅데이터 전문가 과정</p>
				    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
				    <p class="card-link">D-1</p>
				  </div>
				</div>
			</div>	
			<div class="card_2 d-flex">
				<div class="card col-3">
				  <div class="card-body" onclick="location.href='#'">
				    <h4 class="card-title">쌍용강북교육센터</h4>
				    <p class="card-text">빅데이터 전문가 과정</p>
				    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
				    <p class="card-link">D-1</p>
				  </div>
				</div>
				<div class="card col-3">
				  <div class="card-body" onclick="location.href='#'">
				    <h4 class="card-title">쌍용강북교육센터</h4>
				    <p class="card-text">빅데이터 전문가 과정</p>
				    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
				    <p class="card-link">D-1</p>
				  </div>
				</div>
				<div class="card col-3">
				  <div class="card-body" onclick="location.href='#'">
				    <h4 class="card-title">쌍용강북교육센터</h4>
				    <p class="card-text">빅데이터 전문가 과정</p>
				    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
				    <p class="card-link">D-1</p>
				  </div>
				</div>
				<div class="card col-3">
				  <div class="card-body" onclick="location.href='#'">
				    <h4 class="card-title">쌍용강북교육센터</h4>
				    <p class="card-text">빅데이터 전문가 과정</p>
				    <p class="card-sub-text">2022-05-31 ~ 2022-12-21</p>
				    <p class="card-link">D-1</p>
				  </div>
				</div>
			</div>
		</div>
		
		<a><i class="fa-solid fa-circle-right fa-3x icon_arrow" ></i></a>
	</div>
	
	
	<!-- 인기검색어 / 게시판 정보 영역  -->
	<div class="div_infoBoard d-flex mt-4">
		
		<!-- 인기 검색어 영역 -->
		<div class="div_searchRank">
			<div class="div2_searchRank d-flex flex-column">
				<div class="title_searchRank d-flex justify-content-start align-items-center">
					<p>&#x1F451;</p>
					<p style="font-size:15px; margin-left:3px; color:#208EC9;">인기 검색어 </p>
				</div>
				<div class="list_searchRank d-flex flex-column px-3">
					<a href="#"> 검색어 1 </a>
					<a href="#"> 검색어 1 </a>
					<a href="#"> 검색어 1 </a>
					<a href="#"> 검색어 1 </a>
					<a href="#"> 검색어 1 </a>
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
						<a href="#" style="font-size:15px; margin-left:3px; color:white;">QnA </a>
					</div>
					<!-- 테이블 리스트 -->
					<div class="list_board d-flex flex-column">
						<%-- <c:forEach var="boardvo" items="${requestScope.boardList}"> --%>
				        <!-- 이 div가 반복문 시작 -->
				        <!-- 게시글 1개 영역 -->
					    <div class="border-bottom py-3 px-3">
					    	<!-- 작성 정보 -->
					      	<div class="d-flex align-items-center mb-1">
						        <!-- 작성자 프로필사진 -->
						        <a href="#" class="writer_image_box border">
						          <img src=""/>
						        </a>
						
						        <!-- 작성자 닉네임 -->
						        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
						        <a href="#" class="writer_nickname ml-2">
						         	 국모관리팀
						        </a>
						
						        <!-- 작성자 활동점수 -->
						        <div class="writer_point text-align-center ml-2">
						          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
						          <span>97</span>
						        </div>
						
						        <!-- 작성일자 -->
						        <div class="write_date ml-2">
						          1일전
						        </div>
					        
					        	<!-- 조회수,댓글수,추천수 -->
					         	<div class="board_info_box d-flex justify-content-end align-items-center">
						            <!-- 조회수 -->
						            <div>
						            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
						             	<span>40</span>
						           	</div>
						
						           	<!-- 댓글수 -->
						           	<div class="ml-2">
						             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
						             	<span>20</span>
						           	</div>
						
						           	<!-- 추천수 -->
						           	<div class="ml-2">
						             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
						             	<span>30</span>
						           	</div>
				         		</div>
				       		</div>
	
					       <!-- 글제목 -->
					       <a href="#" class="subject align-items-center my-4">
					         [중요알림] 국비의모든것 v0.0.1 오픈 안내
					       </a>
						</div>
						<!-- 이 div가 반복문 끝 -->
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
					<%-- <c:forEach var="boardvo" items="${requestScope.boardList}"> --%>
			        <!-- 이 div가 반복문 시작 -->
			        <!-- 게시글 1개 영역 -->
				    <div class="border-bottom py-3 px-3">
				    	<!-- 작성 정보 -->
				      	<div class="d-flex align-items-center mb-1">
					        <!-- 작성자 프로필사진 -->
					        <a href="#" class="writer_image_box border">
					          <img src=""/>
					        </a>
					
					        <!-- 작성자 닉네임 -->
					        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
					        <a href="#" class="writer_nickname ml-2">
					         	 국모관리팀
					        </a>
					
					        <!-- 작성자 활동점수 -->
					        <div class="writer_point text-align-center ml-2">
					          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
					          <span>97</span>
					        </div>
					
					        <!-- 작성일자 -->
					        <div class="write_date ml-2">
					          1일전
					        </div>
				        
				        	<!-- 조회수,댓글수,추천수 -->
				         	<div class="board_info_box d-flex justify-content-end align-items-center">
					            <!-- 조회수 -->
					            <div>
					            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
					             	<span>40</span>
					           	</div>
					
					           	<!-- 댓글수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
					             	<span>20</span>
					           	</div>
					
					           	<!-- 추천수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
					             	<span>30</span>
					           	</div>
			         		</div>
			       		</div>

				       <!-- 글제목 -->
				       <a href="#" class="subject align-items-center my-4">
				         [중요알림] 국비의모든것 v0.0.1 오픈 안내
				       </a>
					</div>
					<!-- 이 div가 반복문 끝 -->
				</div>			
			</div>
			
			<!-- 자유게시판 영역 -->			
			<div class="div_tbl_board d-flex flex-column">	
				<!-- 테이블 제목 -->
				<div class="title_board d-flex justify-content-start align-items-center">
				
					<p style="font-size:15px; font-weight: 500; margin: 0;" >&#x1F4AC;</p>
					<a href="#" style="font-size:15px; margin-left:3px; color:white;"> 자유게시판 </a>
				</div>
				<!-- 테이블 리스트 -->
				<div class="list_board d-flex flex-column">
					<%-- <c:forEach var="boardvo" items="${requestScope.boardList}"> --%>
			        <!-- 이 div가 반복문 시작 -->
			        <!-- 게시글 1개 영역 -->
				    <div class="border-bottom py-3 px-3">
				    	<!-- 작성 정보 -->
				      	<div class="d-flex align-items-center mb-1">
					        <!-- 작성자 프로필사진 -->
					        <a href="#" class="writer_image_box border">
					          <img src=""/>
					        </a>
					
					        <!-- 작성자 닉네임 -->
					        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
					        <a href="#" class="writer_nickname ml-2">
					         	 국모관리팀
					        </a>
					
					        <!-- 작성자 활동점수 -->
					        <div class="writer_point text-align-center ml-2">
					          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
					          <span>97</span>
					        </div>
					
					        <!-- 작성일자 -->
					        <div class="write_date ml-2">
					          1일전
					        </div>
				        
				        	<!-- 조회수,댓글수,추천수 -->
				         	<div class="board_info_box d-flex justify-content-end align-items-center">
					            <!-- 조회수 -->
					            <div>
					            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
					             	<span>40</span>
					           	</div>
					
					           	<!-- 댓글수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
					             	<span>20</span>
					           	</div>
					
					           	<!-- 추천수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
					             	<span>30</span>
					           	</div>
			         		</div>
			       		</div>

				       <!-- 글제목 -->
				       <a href="#" class="subject align-items-center my-4">
				         [중요알림] 국비의모든것 v0.0.1 오픈 안내
				       </a>
					</div>
					<!-- 이 div가 반복문 끝 -->
				</div>	
			
			</div>	
			
			<!-- 후기 영역 -->
			<div class="div_tbl_board d-flex flex-column">	
				<!-- 테이블 제목 -->
				<div class="title_board d-flex justify-content-start align-items-center">
					<p style="font-size:15px; font-weight: 500; margin: 0;" >&#x1F44D;</p>
					<a href="#" style="font-size:15px; margin-left:3px; color:white;"> 후기/정보공유 </a>
				</div>
				<!-- 테이블 리스트 -->
				<div class="list_board d-flex flex-column">
					<%-- <c:forEach var="boardvo" items="${requestScope.boardList}"> --%>
			        <!-- 이 div가 반복문 시작 -->
			        <!-- 게시글 1개 영역 -->
				    <div class="border-bottom py-3 px-3">
				    	<!-- 작성 정보 -->
				      	<div class="d-flex align-items-center mb-1">
					        <!-- 작성자 프로필사진 -->
					        <a href="#" class="writer_image_box border">
					          <img src=""/>
					        </a>
					
					        <!-- 작성자 닉네임 -->
					        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
					        <a href="#" class="writer_nickname ml-2">
					         	 국모관리팀
					        </a>
					
					        <!-- 작성자 활동점수 -->
					        <div class="writer_point text-align-center ml-2">
					          <i class="fa-solid fa-bolt" style="font-size:8px;"></i>
					          <span>97</span>
					        </div>
					
					        <!-- 작성일자 -->
					        <div class="write_date ml-2">
					          1일전
					        </div>
				        
				        	<!-- 조회수,댓글수,추천수 -->
				         	<div class="board_info_box d-flex justify-content-end align-items-center">
					            <!-- 조회수 -->
					            <div>
					            	<i class="fa-solid fa-eye"style="font-size:10px;"></i>
					             	<span>40</span>
					           	</div>
					
					           	<!-- 댓글수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-comment-dots" style="font-size:10px;"></i>
					             	<span>20</span>
					           	</div>
					
					           	<!-- 추천수 -->
					           	<div class="ml-2">
					             	<i class="fa-solid fa-heart" style="font-size:10px;"></i>
					             	<span>30</span>
					           	</div>
			         		</div>
			       		</div>

				       <!-- 글제목 -->
				       <a href="#" class="subject align-items-center my-4">
				         [중요알림] 국비의모든것 v0.0.1 오픈 안내
				       </a>
					</div>
					<!-- 이 div가 반복문 끝 -->
				</div>
			</div>
		</div>
	</div>

</div>
