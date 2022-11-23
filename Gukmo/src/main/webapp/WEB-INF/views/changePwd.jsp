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
  <title>비밀번호 변경</title>
  <%-- Bootstrap CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 
  
  <%-- Optional JavaScript --%>
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>

  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/changePwd.css" />
	
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/changePwd.js" ></script>
</head>
<body>
    
    
   <div id="changePwdFrm" class="mx-auto my-5">
   
   <div id="logo_img_box" class="m-auto">
     <img id="logo" src="" alt="로고 들어갈 곳">
   </div>

   <div id="about_us" class="my-3">
     <h2 class="text-center">계정분실회원 비밀번호 변경</h2>
     <p id="about_content" class="text-center">
       잊어버리지 않을만한 비밀번호로 변경해주세요. <br>
       이 페이지는 3분후 만료됩니다.
     </p>
   </div>
   
   <div id="div_timer" class="mx-auto my-3 text-center" style="color:red; font-size:14px; font-weight:bold;"></div>

   <form name="changePwdFrm">
     <div id="input_userid" class="d-flex flex-column my-2">
       <label for="userid">회원 아이디</label>
       <input type="text" id="userid" name="userid" class="pl-2 rounded" value="${requestScope.userid}" placeholder="아이디를 입력해주세요" readonly/>
     </div>
     
     <div id="input_passwd" class="d-flex flex-column my-2">
       <label for="passwd">비밀번호</label>
       <input type="password" id="passwd" name="passwd" class="pl-2 rounded" placeholder="소문자,특수문자를 포함한 8~15자 비밀번호">
       <p id="passwd_error" class="error">비밀번호는 소문자,특수문자를 포함한 8~15자이어야 합니다.</p>
     </div>


     <div id="input_passwd_check" class="d-flex flex-column my-2">
       
       <label for="passwd_check">비밀번호 확인</label>
       <input type="password" id="passwd_check" name="passwd_check" class="pl-2 rounded" placeholder="비밀번호 확인">
       <p id="passwd_check_error" class="error">비밀번호가 일치하지 않습니다.</p>
     </div>

     <div>
       <button type="button" id="btn_update_passwd" class="btn border rounded w-100 mt-3">비밀번호 변경</button>
     </div>
   </form>
 
 </div>
 
 
 </body>
</html>