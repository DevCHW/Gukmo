<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>


 <%-- 직접 만든 CSS --%>
 <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/signup.css" />
 
 <%-- 직접만든 javascript --%>
 <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/signup.js" ></script>
 
 
 
 <div id="signup" class="d-flex flex-column mx-auto my-5">
    <%-- 로고 이미지 --%>
    <div id="logo_img_box" class="m-auto">
      <img id="logo" src="" alt="로고 들어갈 곳">
    </div>


    <%-- SNS 회원가입 --%>
    <span>SNS회원가입</span>
    <div id="sns_signup" class="d-flex justify-content-between">
      <%-- kakao --%>
      <div id="kakao_signup" class="sns_signup d-flex justify-content-center align-items-center border rounded">
        <svg viewBox="0 0 22 21" fill="none" xmlns="http://www.w3.org/2000/svg"><g clip-path="url(#clip0_1394_6217)"><path d="M11 0C4.92473 0 0 4.04523 0 9.03544C0 12.2837 2.08683 15.1299 5.2182 16.7227C4.98798 17.6156 4.38507 19.9561 4.26426 20.457C4.11553 21.0787 4.48366 21.0705 4.72471 20.9029C4.9139 20.772 7.73926 18.7767 8.95819 17.9152C9.6198 18.017 10.3019 18.0709 11 18.0709C17.0753 18.0709 22 14.0251 22 9.03544C22 4.04582 17.0753 0 11 0Z" fill="#6B7280"></path><path d="M6.57195 6.37451H3.35566C3.05306 6.37451 2.80859 6.62911 2.80859 6.94293C2.80859 7.25674 3.05363 7.51134 3.35566 7.51134H4.40135V11.5151C4.40135 11.6608 4.45834 11.8041 4.55863 11.9077C4.65665 12.0107 4.79398 12.0699 4.93474 12.0699H5.05156C5.19175 12.0699 5.32851 12.0107 5.4271 11.9077C5.52739 11.8041 5.58438 11.6614 5.58438 11.5151V7.51134H6.57195C6.87454 7.51134 7.11958 7.25674 7.11958 6.94293C7.11958 6.62911 6.87454 6.37451 6.57195 6.37451Z" fill="white"></path><path d="M14.3034 11.0004H12.8075V6.9114C12.8075 6.57035 12.542 6.29443 12.2143 6.29443C11.8866 6.29443 11.6211 6.57035 11.6211 6.9114V11.319C11.6211 11.3533 11.6251 11.3853 11.6302 11.4179C11.6245 11.4498 11.6211 11.483 11.6211 11.5162C11.6211 11.8016 11.8428 12.0325 12.1174 12.0325H14.3034C14.5787 12.0325 14.7998 11.8016 14.7998 11.5162C14.7998 11.2308 14.5781 11.0004 14.3034 11.0004Z" fill="white"></path><path d="M19.0842 11.1881L17.4174 8.91329L18.9235 7.34778C19.1275 7.1364 19.1275 6.79239 18.9235 6.58042C18.7195 6.36845 18.389 6.36845 18.1844 6.58042L16.3198 8.51777V6.9114C16.3198 6.57035 16.0543 6.29443 15.7266 6.29443C15.3989 6.29443 15.1328 6.57035 15.1328 6.9114V11.4546C15.1328 11.7956 15.3984 12.0716 15.7266 12.0716C16.0548 12.0716 16.3198 11.7962 16.3198 11.4546V10.0531L16.6589 9.70138L18.2357 11.8513C18.4112 12.0946 18.7446 12.145 18.9788 11.9608C19.213 11.7779 19.2603 11.4321 19.0842 11.1881Z" fill="white"></path><path d="M11.1745 11.3357L9.57146 6.77887C9.57146 6.77887 9.56747 6.77532 9.56633 6.77355C9.46375 6.49585 9.168 6.29395 8.81525 6.29395C8.42376 6.29395 8.10008 6.54263 8.03568 6.86769C8.02486 6.89197 8.01403 6.91624 8.00491 6.94229L6.42868 11.3357C6.33124 11.624 6.47712 11.939 6.7535 12.0403C7.03045 12.1415 7.33419 11.9899 7.43163 11.7022L7.7046 10.8934H9.89856L10.1709 11.7022C10.2684 11.9899 10.5721 12.1415 10.8491 12.0403C11.126 11.939 11.2719 11.624 11.1745 11.3357ZM8.05278 9.86016L8.79018 7.67413C8.79873 7.67413 8.80557 7.6765 8.81411 7.6765L9.55094 9.86016H8.05335H8.05278Z" fill="white"></path></g><defs><clipPath id="clip0_1394_6217"><rect width="22" height="21" fill="white"></rect></clipPath></defs></svg>
      </div>
      
      <%-- naver --%>
      <div id="naver_signup" class="sns_signup d-flex justify-content-center align-items-center border rounded">
        <svg viewBox="-1 -1 17 16" fill="#6B7280" xmlns="http://www.w3.org/2000/svg"><path d="M9.88343 0V7.06382L5.13527 0H0V14H5.11657V6.93618L9.8666 14H15V0H9.88343Z" fill="#6B7280"></path></svg>
      </div>

      <%-- facebook --%>
      <div id="facebook_signup" class="sns_signup d-flex justify-content-center align-items-center border rounded">
        <svg class="h-5 w-5" aria-hidden="true" fill="#6B7280" viewBox="0 0 20 20"><path fill-rule="evenodd" d="M20 10c0-5.523-4.477-10-10-10S0 4.477 0 10c0 4.991 3.657 9.128 8.438 9.878v-6.987h-2.54V10h2.54V7.797c0-2.506 1.492-3.89 3.777-3.89 1.094 0 2.238.195 2.238.195v2.46h-1.26c-1.243 0-1.63.771-1.63 1.562V10h2.773l-.443 2.89h-2.33v6.988C16.343 19.128 20 14.991 20 10z" clip-rule="evenodd"></path></svg>
      </div>
    </div>
    <%-- SNS 회원가입 끝 --%>



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
          <button id="btn_email_certification" type="button" class="btn border rounded mt-2" data-toggle="modal" data-target="#email_certification" data-dismiss="modal">이메일 인증</button>
        </div>
        

        <%-- username --%>
        <label for="" class="label_signup mt-3">이름</label>
        <input type="text" id="username" name="username" class="input_signup rounded pl-2" placeholder="ex)홍길동">


        <%-- nickname (unique,중복체크)--%>
        <label for="nickname" class="label_signup mt-3">닉네임</label>
        <input type="text" id="nickname" name="nickname" class="input_signup rounded pl-2" placeholder="닉네임을 입력해주세요(10자이내)">
        <p id="nickname_error" class="error"></p>
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

      <div class="my-3">
        국비의모든것에서 주최하는 다양한 이벤트, 정보성 뉴스레터 및 광고 수신여부를 설정할 수 있습니다.
      </div>

      <%-- Google reCAPTCHA --%>
      <div class="g-recaptcha border" data-sitekey="6LfdNbgZAAAAAI3otR4Gh5yysu..." data-callback="recaptcha">
        로봇이아닙니다 넣을 자리
      </div>

      <button type="button" id="btn_signup" class="btn border rounded w-100 mt-3">회원가입</button>




    </div>
    <%-- signupform 끝 --%>

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
