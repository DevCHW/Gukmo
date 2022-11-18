<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/TOS.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo//TOS.js" ></script>

  <div class="container my-5">
    <div>
      <h5 class="title">이용약관</h5>
      <iframe class="border rounded mt-1 w-100" src="<%=ctxPath %>/resources/TOS/TOStext.html" frameborder="2"></iframe>
      <div class="d-flex align-items-center">
        <input class="ml-2" name="agreement" type="checkbox" id="TOS_agree">
        <label class="ml-2" for="TOS_agree">위 이용약관에 동의합니다.</label>
      </div>
    </div>


    <%-- guide --%>
    <div class="mt-3">
      <h5 class="title">개인정보 수집 및 이용에 대한 안내</h5>
      <iframe class="border rounded mt-1 w-100" src="<%=ctxPath %>/resources/TOS/guide.html" frameborder="2"></iframe>
      <div class="d-flex align-items-center">
        <input class="ml-2" name="agreement" type="checkbox" id="guide_agree">
        <label class="ml-2" for="guide_agree">위와 같이 개인정보 수집 및 이용에 동의합니다.</label>
      </div>
    </div>




    <%-- division--%>
    <div id="division_box" class="d-flex w-100 justify-content-between mt-4">
      <div id="memberSignup" class="px-2 py-2 border rounded">
        <div class="d-flex justify-content-center align-items-center">
          <div class="svg_box">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><%--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. --%><path d="M224 256c70.7 0 128-57.3 128-128S294.7 0 224 0S96 57.3 96 128s57.3 128 128 128zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/></svg>
          </div>
          <span class="link-title ml-2">일반회원 가입</span>
        </div>

        <ul class="d-flex flex-column align-items-center guide_text mt-2">
          <li>국비훈련에 대한 다양한 정보을 이용하실 수 있습니다</li>
          <li>교육과정에 관련된 Q&A를 이용하실 수 있습니다</li>
          <li>과년도 기출문제를 다운로드 받으실 수 있습니다</li>
          <li>교육과정에 정보를 이메일로 받아보실수 있습니다</li>
        </ul>
      </div>

      <div id="acaMemberSignup" class="px-2 py-2 border rounded">
        <div class="d-flex justify-content-center align-items-center">
          <div class="svg_box">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><%--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. --%><path d="M543.8 287.6c17 0 32-14 32-32.1c1-9-3-17-11-24L512 185V64c0-17.7-14.3-32-32-32H448c-17.7 0-32 14.3-32 32v36.7L309.5 7c-6-5-14-7-21-7s-15 1-22 8L10 231.5c-7 7-10 15-10 24c0 18 14 32.1 32 32.1h32v69.7c-.1 .9-.1 1.8-.1 2.8V472c0 22.1 17.9 40 40 40h16c1.2 0 2.4-.1 3.6-.2c1.5 .1 3 .2 4.5 .2H160h24c22.1 0 40-17.9 40-40V448 384c0-17.7 14.3-32 32-32h64c17.7 0 32 14.3 32 32v64 24c0 22.1 17.9 40 40 40h24 32.5c1.4 0 2.8 0 4.2-.1c1.1 .1 2.2 .1 3.3 .1h16c22.1 0 40-17.9 40-40V455.8c.3-2.6 .5-5.3 .5-8.1l-.7-160.2h32z"/></svg>
          </div>
          <span class="link-title ml-2">교육기관회원 가입</span>
        </div>

        <ul class="d-flex flex-column align-items-center guide_text mt-2">
          <li>교육기관 홍보를 하실 수 있습니다</li>
          <li>교육과정을 무료로 등록하실 수 있습니다</li>
          <li>과정문의에 대한 답변을 올리실 수 있습니다.</li>
          <li>과년도 기출문제를 다운로드 받으실 수 있습니다</li>
        </ul>
      </div>
    </div>
  </div>
