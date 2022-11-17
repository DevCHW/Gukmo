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
			<div class="selectSearch_div">                 
			    <label for="selectSearch">학원명</label>                           
		        <select name="selectSearch" id="selectSearch">
		            <option value="academy_name" selected>학원명</option>
		            <option value="subject_name">과정명</option>
		            <option value="board">게시글</option>
		            <!-- <option value="academy_name">해시태그</option> -->			
		        </select>
			</div>
			<input type="text" id="searchWord" placeholder="검색할 내용을 입력해 주세요!"></input>
		</div>


	<!-- 학원 정보 영역 -->
	<div class="div_academy d-flex justify-content-between align-items-center" >
		<i class="fa-solid fa-circle-left fa-3x" style="color:#208EC9;"></i>
		
		<!-- 나중에 반복문 짜야함 -->
		<div class="div_card col-10">
			<div class="card_1 d-flex">
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
		
		<i class="fa-solid fa-circle-right fa-3x" style="color:#208EC9;"></i>
	</div>
	
	
	<!-- 게시판 정보 영역 -->
	<div class="div_board d-flex">
		<div class="search_rank col-3"></div>
		<div class="div_qna"></div>
		<div class="div_study"></div>
	</div>
</div>
