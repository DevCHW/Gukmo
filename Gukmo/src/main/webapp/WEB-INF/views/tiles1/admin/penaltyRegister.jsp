
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   
<%
	String ctxPath = request.getContextPath();
%>
  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/penaltyRegister.css" />
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/penaltyRegister.js?ver=1" ></script>

  <div id="penaltyRegister" class="d-flex flex-column mx-auto my-5">

    <div class="line my-4">
      신고에 필요한 정보를 입력하세요.
    </div>

    <!-- signupform 시작 -->
    <div id="penaltyForm">
      <form name="penaltyForm" class="d-flex flex-column">
        <label for="userid" class="label_signup mt-3">아이디</label>
        <input type="text" id="userid" name="userid" style="width:300px;" class="input_signup rounded pl-2" placeholder="" value=${requestScope.userid} readonly/>

        <label for="nickname" class="label_signup mt-3">닉네임</label>
        <input type="text" id="nickname" name="nickname" style="width:300px;"  class="input_signup rounded pl-2" placeholder="" value=${requestScope.nickname} readonly/>

         <label for="simple_penalty_reason" class="label_signup mt-3">정지 사유</label>
        <span class="pt-2 mr-2">
          <select id= "simple_penalty_reason" name="simple_penalty_reason" class="mt-1">
            <option value="욕설/비방" selected>욕설/비방</option>
            <option value="허위글 게시">허위글 게시</option>
            <option value="정치적인 글">정치적인 글</option>
            <option value="기타 사유">기타 사유</option>
          </select>
        </span>
        
        <label for="detail_penalty_reason" class="label_signup mt-3">정지 사유를 상세하게 작성하세요.</label>

        <div style="">
          <textarea name="detail_penalty_reason" id="detail_penalty_reason" class="requiredInfo form-control" style="width:450px; height:250px;"></textarea>
          <div id = "detail_penalty_reason_error"></div>
        </div>

        <!-- 정지 시작일 -->
        <label for="" class="label_signup mt-3">정지 시작일</label>
        <input type="text" id="penalty_start_date" name="penalty_start_date" style="width:300px;" class="input_signup rounded pl-2" placeholder="오늘(2022-11-15)" readonly >


        <!-- 정지 기간-->
        <span class="pt-2 mr-2">
          <select id="penalty_period" name = "penalty_period" class="mt-1">
            <option value="" selected>정지 기간을 선택하세요.</option>
            <option value="7">7 일</option>
            <option value="30">30 일</option>
            <option value="90">90 일</option>
            <option value="150">150 일</option>
            <option value="300">300 일</option>
          </select>
        </span>
        <div id = "penalty_period_error"></div>
      </form>

      <div class="my-3">
        내용을 한번 더 확인하세요.
      </div>
      <button id="penaltyWrite" class="btn border rounded w-100 mt-3" onclick="penaltyWrite()">정지 등록</button>
      <button id="cancle" class="btn border rounded w-100 mt-3">취소</button>
    </div>
    <!-- signupform 끝 --> 
  </div>
 