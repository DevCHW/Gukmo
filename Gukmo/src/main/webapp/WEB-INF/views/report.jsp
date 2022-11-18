<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

 <!-- Bootstrap CSS -->
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 

  <!-- Font Awesome 5 Icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
  
  <!-- noto sans -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">

  <!-- Optional JavaScript -->
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script> 

  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seonwoo/report.css" />
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hasol/gukmo.css" />
	
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/report.js" ></script>

  <div class="container" >
    <!-- 커뮤니티 작성 폼 시작 -->
    <form name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
	  
	  <div class="d-flex justify-content-between">
	  	<div></div>
	  	<h2>신고하기</h2>
	  	<button type="button" id="btn_close">X</button>
	  </div>

	  <hr class="mb-5" style="background-color: black; height: 1.2px;">
	  
	  <div class=""> 
		 <span>작성자 | </span>
		 <span>내 용   | </span>
	     <!-- 등록용 닉네임 -->
		 <input type="hidden" name="nickname" value="" />
		 <input type="hidden" name="subject" value="" />
	  </div>
	  
	  <hr class="mb-5" style="background-color: black; height: 1.2px;">
	  
	  
	  <div>
	  	사유선택
	  </div>
      <!-- category -->
      <label for="detail_category" class="community_label">구분</label>
      <select name="detail_category" id="detail_category" class="community_input pl-2 border rounded">
        <option value="">구분을 선택해주세요</option>
        <option>스터디</option>
        <option>자유게시판</option>
        <option>Q&A</option>
        <option>정보공유</option>
        <option>수강/취업 성공 후기</option>
      </select>

	<footer>
		<button type="button">신고하기</button>
	</footer>


  </div>