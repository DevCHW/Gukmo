<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>


 <%-- 직접 만든 CSS --%>
 <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/signup.css" />
 
 <%-- Google reCAPTCHA v2 --%>
 <script src="https://www.google.com/recaptcha/api.js"></script>
 <%-- 직접만든 javascript --%>
 <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/signup.js" ></script>
 
 
 
 <div id="signup" class="d-flex flex-column mx-auto my-5">
    <%-- 로고 이미지 --%>
    <div id="logo_img_box" class="m-auto" style="cursor:pointer;" onclick="location.href='<%=ctxPath%>/index.do'">
      <img id="logo" src="<%=ctxPath %>/resources/images/logo.png" class="rounded">
    </div>


    <div class="line my-4">
      회원가입에 필요한 기본정보를 입력해주세요
    </div>

    <%-- signupform 시작 --%>
    <div id="signup_form">
      <form name="signup_form" class="d-flex flex-column">
        <%-- userid (unique,중복체크)--%>
        <label for="userid" class="label_signup mt-3">아이디</label>
        <input type="text" id="userid" name="userid" class="input_signup rounded pl-2" placeholder="영문 또는 숫자 5~15자 아이디">
        <p id="userid_error" class="error"></p>
        <p id="userid_ok" class="ok">사용할 수 있는 아이디입니다.</p>

        <%-- passwd --%>
        <label for="passwd" class="label_signup mt-3">비밀번호</label>
        <input type="password" id="passwd" name="passwd" class="input_signup rounded pl-2" placeholder="소문자,특수문자를 포함한 8~15자 비밀번호">
        <p id="passwd_error" class="error">비밀번호는 소문자,특수문자를 포함한 8~15자이어야 합니다.</p>

        <%-- passwd_check --%>
        <label for="passwd_check" class="label_signup mt-3">비밀번호 확인</label>
        <input type="password" id="passwd_check" name="passwd_check" class="input_signup rounded pl-2" placeholder="비밀번호 확인">
        <p id="passwd_check_error" class="error">비밀번호가 일치하지 않습니다.</p>

        <%-- email (unique,중복체크)--%>
        <label for="email" class="label_signup mt-3">이메일</label>
        <input type="text" id="email" name="email" class="input_signup rounded pl-2" placeholder="example@google.com">
        <div id="email_error_area">
          <p id="email_error" class="error"><%-- 이미 가입된 이메일입니다. --%></p>
          <p id="email_ok" class="ok">사용할 수 있는 이메일입니다.</p>
          <button id="btn_email_certification" type="button" class="btn border rounded mt-2 ml-auto" data-toggle="modal" data-target="#email_certification" data-dismiss="modal">이메일 인증</button>
        </div>
        

        <%-- username --%>
        <label for="username" class="label_signup mt-3">이름</label>
        <input type="text" id="username" name="username" class="input_signup rounded pl-2" placeholder="ex)홍길동" maxlength="10">
		<p id="username_error" class="error">이름은 특수문자,영어,숫자를 제외한 2~10글자이어야 합니다.</p>

        <%-- nickname (unique,중복체크)--%>
        <label for="nickname" class="label_signup mt-3">닉네임</label>
        <input type="text" id="nickname" name="nickname" class="input_signup rounded pl-2" placeholder="닉네임을 입력해주세요(10자이내)" maxlength="10">
        <p id="nickname_error" class="error">닉네임 형식에 맞지 않습니다.</p>
        <p id="nickname_ok" class="ok">사용할 수 있는 닉네임입니다.</p>
		

        <%-- email 수신동의 --%>
        <div id="email_agreement" class="d-flex justify-content-between my-3">
          <span>이메일 수신동의</span>
          <%-- 토글스위치 --%>
          <label class="switch">
            <input type="checkbox" id="email_agreement" name="email_agreement">
            <span class="slider round"></span>
          </label>
        </div>
        <input type="hidden" id="email_acept" name="email_acept" value="0"/>
      </form>

      <div class="my-1">
        국비의모든것에서 주최하는 다양한 이벤트, 정보성 뉴스레터 및 광고 수신여부를 설정할 수 있습니다.
      </div>

      <%-- Google reCAPTCHA --%>
      <div class="d-flex justify-content-center my-2">
        <div class="g-recaptcha" data-sitekey="6LdO7zkjAAAAAFk660Urlo0EbazNdIIW9aFnJXLH"></div>
      </div>

      <button type="button" id="btn_signup" class="btn border rounded w-100 mt-3">회원가입</button>


    </div>
    <%-- signupform 끝 --%>
    
    <%-- 유효성검사 테스트 버튼 --%>
    <%--
    <button type="button" id="btn_test">유효성검사 테스트</button>
    --%>

  </div>


<%-- 이메일인증 Modal --%>
  <div class="modal fade" id="email_certification">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <%-- Modal header --%>
        <div class="modal-header">
          <h5 class="modal-title">이메일인증코드 발송</h5>
          <button type="button" class="close email_certificationClose" data-dismiss="modal">&times;</button>
        </div>
        
        <%-- Modal body --%>
        <div class="modal-body">
          <div class="d-flex flex-column mt-2">
            <div>
              <button id="btn_send_email" class="btn border rounded mr-1">인증번호 전송</button>
            </div>
            <span id="send_guide" class="mt-2"></span>
            <div id="certification_area" class="align-items-center">
              <input class="border rounded mt-2 pl-2" type="text" id="input_certificationCode" name="emailCertificationCode" placeholder="인증번호를 입력해주세요">
              <div id="div_timer" class="mt-3 ml-4" style="color:red; font-size:14px; font-weight:bold;"></div>
            </div>
          </div>
        </div>
        
        <%-- Modal footer --%>
        <div class="modal-footer">
          <button id="btn_certification_complete" type="button" class="btn border">인증완료</button>
          <button type="button" class="btn border email_certification_close" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
