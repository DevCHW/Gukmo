
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   
<%
	String ctxPath = request.getContextPath();
%>
  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/adRegister.css" />
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/adRegister.js?ver=1" ></script>

  <div id="penaltyRegister" class="d-flex flex-column mx-auto my-5">

    <div class="line my-4">
      광고 등록에 필요한 정보를 입력하세요.
    </div>

    <!-- signupform 시작 -->
    <div id="adForm">
      <form name="adForm" class="d-flex flex-column" enctype="multipart/form-data">
         <label for="division" class="label_signup mt-3">광고 분류</label>
        <span class="pt-2 mr-2">
          <select id= "division" name="division" class="mt-1">
            <option value="메인" selected>메인</option>
            <option value="게시판">게시판</option>
          </select>
        </span>
        
        <label for="client_name" class="label_signup mt-3">클라이언트 명</label>
        <input type="text" id="client_name" name="client_name" style="width:300px;" class="input_signup rounded pl-2" placeholder="" />
		<div id="client_name_error" class="error"></div>
		
        <label for="client_phone" class="label_signup mt-3">클라이언트 번호</label>
        <input type="text" id="client_phone" name="client_phone" style="width:300px;" class="input_signup rounded pl-2" placeholder="" />
		<div id="client_phone_error" class="error"></div>

        <label for="file_name" class="label_signup mt-3">파일명</label>
        <input type="file" id="attach" name="attach" style="border:none;" class="input_signup pl-2" placeholder="" />
		<div id="file_name_error" class="error"></div>

        <label for="url" class="label_signup mt-3">URL 주소</label>
        <input type="text" id="url" name="url" style="width:300px;" class="input_signup rounded pl-2" placeholder="" />
		<div id="url_error" class="error"></div>

        <label for="start_date" class="label_signup mt-3">게시 날짜</label>
        <input type="text" id="start_date" name="start_date" style="width:300px;" class="input_signup rounded pl-2" placeholder="오늘" readonly/>

        <span class="pt-2 mr-2">
          <select id="period" name = "period" class="mt-1" onchange="periodCheck()">
            <option value="" selected>광고 게시 기간을 선택하세요.</option>
            <option value="7">7 일</option>
            <option value="30">15 일</option>
            <option value="30">30 일</option>
            <option value="30">60 일</option>
            <option value="90">90 일</option>
            <option value="150">150 일</option>
            <option value="300">300 일</option>
          </select>
        </span>
 		<div id="period_error" class="error"></div>
      </form>

      <div class="my-3">
        내용을 한번 더 확인하세요.
      </div>
      <button id="penaltyWrite" class="btn border rounded w-100 mt-3" onclick="adWrite()">광고 등록</button>
      <button id="cancle" class="btn border rounded w-100 mt-3">취소</button>
    </div>
    <!-- signupform 끝 --> 
  </div>
 