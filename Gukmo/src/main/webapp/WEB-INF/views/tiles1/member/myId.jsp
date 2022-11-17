<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/hyunwoo/css/myId.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/hyunwoo/js/myId.js" ></script>


  <div class="container mt-4">
    <%-- 사이드바 시작 --%>
    <div id="sidebar">
      <h5 id="sidebar_title">내 계정</h5>
      <ul>
        <li>회원정보</li>
        <li>계정</li>
        <li>활동 내역</li>
      </ul>
    </div>
    <%-- 사이드바 끝 --%>
    <div id="main">

      <%-- email --%>
      <h5 class="guide_title">이메일 정보</h5>
      <div class="d-flex justify-content-between mb-2">
        <input type="text" id="email" name="email" class="border rounded" placeholder="이메일을 입력해주세요">
        <button type="button" class="btn border rounded">
          <%-- 이메일인증 모달 띄우기 --%>
          이메일인증
        </button>
      </div>
      <span class="guide_content">
        이메일 변경은 변경한 이메일로 인증 요청 메일이 발송되고 해당 이메일을 통해 인증을 정상적으로 완료한 후 최종적으로 반영됩니다.
      </span>
      <i class="fa-solid fa-envelope"></i>


      <hr>

      <h5 class="guide_title">계정삭제</h5>
      <div class="border rounded px-2 py-2">
        회원 탈퇴일로부터 계정과 닉네임을 포함한 계정 정보(아이디/이메일/닉네임)는<br>
        <a href="">개인정보 보호방침</a>에 따라 60일간 보관(잠김)되며, 60일 경과된 후에는 모든 개인 정보는 완전히 삭제되며 더 이상 복구할 수 없게 됩니다.<br>
        <br>
        작성된 게시물은 삭제되지 않으며, 익명처리 후 국비의모든것 으로 소유권이 귀속됩니다.
      </div>

      <div class="d-flex mt-3 justify-content-between align-items-center">
        <div class="d-flex align-items-center">
          <input type="checkbox" id="agreement" name="agreement" class="mr-1">
          <label for="agreement">계정 삭제에 관한 정책을 읽고 이에 동의합니다.</label>
        </div>
        <button type="button" class="btn btn-danger border rounded">계정삭제</button>
      </div>

    </div>

  </div>
