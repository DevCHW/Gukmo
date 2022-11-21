<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>

  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/login.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/login.js" ></script>
  
  
  <form name="login_form" id="login_form">
  
    <div id="login" class="d-flex flex-column mx-auto my-5">

      <div id="logo_img_box" class="m-auto" style="cursor:pointer;" onclick="location.href='<%=ctxPath%>/index.do'">
        <img id="logo" src="" alt="로고 들어갈 곳">
      </div>

      <div id="about_us" class="my-3">
        <h2 class="text-center">국비의모든것에 오신것을 환영합니다.</h2>
        <p id="about_content" class="text-center">국비의모든것은 소프트웨어 개발자를 꿈꾸는 비전공자들을 위한 <br/>국비지원 학원 정보공유 플랫폼입니다.</p>
      </div>
    
      <%-- sns_login --%>
      <span>SNS로그인</span>
      <div id="sns_login" class="d-flex justify-content-between">
        <%-- kakao --%>
        <div id="kakao_login" class="sns_login d-flex justify-content-center align-items-center border rounded">
          <svg viewBox="0 0 22 21" fill="none" xmlns="http://www.w3.org/2000/svg" style=""><g clip-path="url(#clip0_1394_6217)"><path d="M11 0C4.92473 0 0 4.04523 0 9.03544C0 12.2837 2.08683 15.1299 5.2182 16.7227C4.98798 17.6156 4.38507 19.9561 4.26426 20.457C4.11553 21.0787 4.48366 21.0705 4.72471 20.9029C4.9139 20.772 7.73926 18.7767 8.95819 17.9152C9.6198 18.017 10.3019 18.0709 11 18.0709C17.0753 18.0709 22 14.0251 22 9.03544C22 4.04582 17.0753 0 11 0Z" fill="#6B7280" style=""></path><path d="M6.57195 6.37451H3.35566C3.05306 6.37451 2.80859 6.62911 2.80859 6.94293C2.80859 7.25674 3.05363 7.51134 3.35566 7.51134H4.40135V11.5151C4.40135 11.6608 4.45834 11.8041 4.55863 11.9077C4.65665 12.0107 4.79398 12.0699 4.93474 12.0699H5.05156C5.19175 12.0699 5.32851 12.0107 5.4271 11.9077C5.52739 11.8041 5.58438 11.6614 5.58438 11.5151V7.51134H6.57195C6.87454 7.51134 7.11958 7.25674 7.11958 6.94293C7.11958 6.62911 6.87454 6.37451 6.57195 6.37451Z" fill="white"></path><path d="M14.3034 11.0004H12.8075V6.9114C12.8075 6.57035 12.542 6.29443 12.2143 6.29443C11.8866 6.29443 11.6211 6.57035 11.6211 6.9114V11.319C11.6211 11.3533 11.6251 11.3853 11.6302 11.4179C11.6245 11.4498 11.6211 11.483 11.6211 11.5162C11.6211 11.8016 11.8428 12.0325 12.1174 12.0325H14.3034C14.5787 12.0325 14.7998 11.8016 14.7998 11.5162C14.7998 11.2308 14.5781 11.0004 14.3034 11.0004Z" fill="white"></path><path d="M19.0842 11.1881L17.4174 8.91329L18.9235 7.34778C19.1275 7.1364 19.1275 6.79239 18.9235 6.58042C18.7195 6.36845 18.389 6.36845 18.1844 6.58042L16.3198 8.51777V6.9114C16.3198 6.57035 16.0543 6.29443 15.7266 6.29443C15.3989 6.29443 15.1328 6.57035 15.1328 6.9114V11.4546C15.1328 11.7956 15.3984 12.0716 15.7266 12.0716C16.0548 12.0716 16.3198 11.7962 16.3198 11.4546V10.0531L16.6589 9.70138L18.2357 11.8513C18.4112 12.0946 18.7446 12.145 18.9788 11.9608C19.213 11.7779 19.2603 11.4321 19.0842 11.1881Z" fill="white"></path><path d="M11.1745 11.3357L9.57146 6.77887C9.57146 6.77887 9.56747 6.77532 9.56633 6.77355C9.46375 6.49585 9.168 6.29395 8.81525 6.29395C8.42376 6.29395 8.10008 6.54263 8.03568 6.86769C8.02486 6.89197 8.01403 6.91624 8.00491 6.94229L6.42868 11.3357C6.33124 11.624 6.47712 11.939 6.7535 12.0403C7.03045 12.1415 7.33419 11.9899 7.43163 11.7022L7.7046 10.8934H9.89856L10.1709 11.7022C10.2684 11.9899 10.5721 12.1415 10.8491 12.0403C11.126 11.939 11.2719 11.624 11.1745 11.3357ZM8.05278 9.86016L8.79018 7.67413C8.79873 7.67413 8.80557 7.6765 8.81411 7.6765L9.55094 9.86016H8.05335H8.05278Z" fill="white" style=""></path></g><defs><clipPath id="clip0_1394_6217"><rect width="22" height="21" fill="white"></rect></clipPath></defs></svg>
        </div>
      
        <%-- naver --%>
        <div id="naver_login" class="sns_login d-flex justify-content-center align-items-center border rounded">
          <svg viewBox="-1 -1 17 16" fill="#6B7280" xmlns="http://www.w3.org/2000/svg"><path d="M9.88343 0V7.06382L5.13527 0H0V14H5.11657V6.93618L9.8666 14H15V0H9.88343Z" fill="#6B7280"></path></svg>
        </div>

        <%-- facebook --%>
        <div id="facebook_login" class="sns_login d-flex justify-content-center align-items-center border rounded">
          <svg class="h-5 w-5" aria-hidden="true" fill="#6B7280" viewBox="0 0 20 20" style=""><path fill-rule="evenodd" d="M20 10c0-5.523-4.477-10-10-10S0 4.477 0 10c0 4.991 3.657 9.128 8.438 9.878v-6.987h-2.54V10h2.54V7.797c0-2.506 1.492-3.89 3.777-3.89 1.094 0 2.238.195 2.238.195v2.46h-1.26c-1.243 0-1.63.771-1.63 1.562V10h2.773l-.443 2.89h-2.33v6.988C16.343 19.128 20 14.991 20 10z" clip-rule="evenodd" style=""></path></svg>
        </div>
      </div>


      <%-- login_input --%>
      <div class="line my-4">
        	국비의모든것 아이디로 로그인
      </div>
      <%-- Id --%>
      <div id="input_id" class="d-flex flex-column">
        <label for="userid">아이디</label>
        <input type="text" id="userid" name="userid" class="input_login border rounded pl-2" placeholder="아이디를 입력해주세요"/>
        <span id="userid_error" class="login_error pl-1 mt-2">아이디를 입력해주세요.</span>
      </div>


      <%-- Password --%>
      <div id="input_password" class="d-flex flex-column mt-3">
        <label for="passwd">비밀번호</label>
        <input type="password" id="passwd" name="passwd" class="input_login border rounded pl-2" placeholder="비밀번호를 입력해주세요"/>
        <span id="passwd_error" class="login_error pl-1 mt-2">비밀번호를 입력해주세요.</span>
      </div>


      <%-- findId --%>
      <div id="find_id" class="ml-auto my-4" onclick="location.href='<%=ctxPath%>/member/findId.do'">계정찾기</div>

      <%-- btn_login --%>
      <button id="btn_login" type="button" class="btn border rounded">로그인</button>
      <div class="mx-auto my-4">아직 회원이 아니신가요?&nbsp;<span id="btn_signup">회원가입</span></div>
    </div>
  </form>
