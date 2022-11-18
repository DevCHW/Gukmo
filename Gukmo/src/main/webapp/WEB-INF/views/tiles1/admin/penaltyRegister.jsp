<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   
<%
	String ctxPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>정지 등록 페이지</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" type="text/css" href="bootstrap-4.6.0-dist/css/bootstrap.min.css" > 
  <!-- Font Awesome 5 Icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
  <!-- title Icon -->
  <link href="" rel="shortcut icon" type="image/x-icon">
  <!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/penaltyRegister.css" />
  
  <!-- Optional JavaScript -->
  <script type="text/javascript" src="jquery3.6.0/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>
  <!-- sweet alert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
  <!-- toastr css라이브러리 -->
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
  <!-- toastr cdn 라이브러리 둘다 제이쿼리 밑에 있어야함 -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>  

  <!-- JqueryUI JS,CSS -->
  <link rel="stylesheet" type="text/css" href="jquery-ui-1.13.1.custom/jquery-ui.min.css" />
  <script type="text/javascript" src="jquery-ui-1.13.1.custom/jquery-ui.min.js" ></script> 
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="js/penaltyRegister.js" ></script>
</head>
<body>

  <div id="penaltyRegister" class="d-flex flex-column mx-auto my-5">

    <div class="line my-4">
      신고에 필요한 정보를 입력하세요.
    </div>

    <!-- signupform 시작 -->
    <div id="signup_form">
      <form action="" class="d-flex flex-column">
        <!-- userid (unique,중복체크)-->
        <label for="userid" class="label_signup mt-3">닉네임</label>
        <input type="text" id="userid" name="userid" class="input_signup rounded pl-2" placeholder="">
        <p id="userid_error" class="error"></p>        
        <p id="userid_ok" class="ok">사용할 수 있는 아이디입니다.</p>

        <span class="pt-2 mr-2">
          <select id="simple_penalty_reason" class="mt-1">
            <option value="" selected>정지 사유를 선택하세요.</option>
            <option value="type1">욕설/비방</option>
            <option value="type2">허위글 게시</option>
            <option value="type3">정치적인 글</option>
            <option value="type4">기타 사유</option>
          </select>
        </span>
        <label for="detail_penalty_reason" class="label_signup mt-3">정지 사유를 상세하게 작성하세요.</label>

        <div style="">
          <textarea name="detail_penalty_reason" id="detail_penalty_reason" class="requiredInfo form-control" style="width:450px; height:250px;"></textarea>
        </div>

        <!-- 정지 시작일 -->
        <label for="" class="label_signup mt-3">정지 시작일</label>
        <input type="text" id="penalty_start_date" name="penalty_start_date" class="input_signup rounded pl-2" placeholder="오늘(2022-11-15)" readonly>


        <!-- nickname (unique,중복체크)-->
        <span class="pt-2 mr-2">
          <select id="penalty_period" name = "penalty_period" class="mt-1">
            <option value="" selected>정지 기간을 선택하세요.</option>
            <option value="type1">7 일</option>
            <option value="type2">30 일</option>
            <option value="type3">90 일</option>
            <option value="type4">1 년</option>
            <option value="type5">영구 정지</option>
            <option value="type6">기타</option>
          </select>
        </span>
        <div id="showPeriodInput"style="display:flex; margin-top:20px;">
          <div>
            <input type="text"/>
          </div>
          ~
          <div>
            <input type="text"/>
          </div>
        </div>
      </form>

      <div class="my-3">
        내용을 한번 더 확인하세요.
      </div>

      <button id="btn_signup" class="btn border rounded w-100 mt-3">정지 등록</button>
      <button id="btn_signup" class="btn border rounded w-100 mt-3">취소</button>




    </div>
    <!-- signupform 끝 -->


  </div>
</body>
</html>