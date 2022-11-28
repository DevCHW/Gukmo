<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/myId.css" />

<script type="text/javascript">
  sessionStorage.setItem("email",'${sessionScope.user.email}');
</script>
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/myId.js" ></script>


  <div class="container my-4">
  
    <%-------------------- 사이드바 시작 ----------------------%>
        
    <%-- sidebar 호출 --%>
	<jsp:include page="/WEB-INF/views/tiles1/member/sidebar.jsp" />
        
        
    <%-------------------- 사이드바 끝 ----------------------%>
    
    
    <div id="main">

      <%-- email --%>
      <h5 class="guide_title">이메일 정보</h5>
      <div class="d-flex justify-content-between mb-2">
        <input type="text" id="email" name="email" class="border rounded pl-2" value="${sessionScope.user.email}" placeholder="이메일을 입력해주세요"/>
        <button type="button" id="btn_email_certification" class="btn border rounded" data-toggle="modal" data-target="#email_certification" data-dismiss="modal" disabled>
          <%-- 이메일인증 모달 띄우기 --%>
          	이메일인증
        </button>
      </div>
      <p id="email_error" class="error"></p>
      <span class="guide_content">
        	이메일 변경은 변경한 이메일로 인증 요청 메일이 발송되고 해당 이메일을 통해 인증을 정상적으로 완료한 후 최종적으로 반영됩니다.
      </span>
      <i class="fa-solid fa-envelope"></i>


      <hr>

      <h5 class="guide_title">계정삭제</h5>
      <div class="border rounded px-2 py-2">
       	 회원 탈퇴일로부터 계정과 닉네임을 포함한 계정 정보(아이디/이메일/닉네임)는<br>
        <a href="<%=ctxPath %>/policy/privacy.do">개인정보 보호방침</a>에 따라 60일간 보관(잠김)되며, 60일 경과된 후에는 모든 개인 정보는 완전히 삭제되며 더 이상 복구할 수 없게 됩니다.<br>
        <br>
                   작성된 게시물은 삭제되지 않으며, 익명처리 후 국비의모든것 으로 소유권이 귀속됩니다.
      </div>

      <div class="d-flex mt-3 justify-content-between align-items-center">
        <div class="d-flex align-items-center">
          <input type="checkbox" id="agreement" name="agreement" class="mr-1">
          <label for="agreement">계정 삭제에 관한 정책을 읽고 이에 동의합니다.</label>
        </div>
        <button type="button" id="btn_delete_member" class="btn btn-danger border rounded">계정삭제</button>
      </div>

    </div>

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
