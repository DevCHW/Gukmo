<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

		
	  <br>
      <hr>
      <br>
	  
	  <h5 class="guide_title">비밀번호 변경</h5>
	  <div class="px-2 py-2">
	    <div class="d-flex flex-column w-50">
	      <%-- 비밀번호 입력란 --%>
	      <label for="passwd" class="label_update">비밀번호</label>
	      <input type="password" id="passwd" name="passwd" class="input_update_passwd rounded pl-2 w-100" placeholder="소문자,특수문자를 포함한 8~15자 비밀번호">
	      <p id="passwd_error" class="error">비밀번호는 소문자,특수문자를 포함한 8~15자이어야 합니다.</p>
	      
	      <%-- 비밀번호 확인란 --%>
	      <label for="passwd_check" class="label_update mt-3">비밀번호 확인</label>
	      <input type="password" id="passwd_check" name="passwd_check" class="input_update_passwd rounded pl-2 w-100" placeholder="비밀번호 확인">
	      <p id="passwd_check_error" class="error">비밀번호가 일치하지 않습니다.</p>
	    </div>
	    
	    <div class="d-flex justify-content-between align-items-center">
	      <span class="guide_content">
	      	<i class="fa-solid fa-circle-info"></i>
        	비밀번호는 3개월마다 1번씩 변경하는것을 권장합니다.
      	  </span>
	      <button type="button" id="btn_edit_passwd" class="btn border rounded" disabled>비밀번호 변경</button>
	    </div>
	  </div>
	  
	  <br>
      <hr>
      <br>
      
      <%-- 교육기관회원이 아닌 일반회원일 경우에만 --%>
      <c:if test="${empty sessionScope.user.academy_name}">
      <h5 class="guide_title">교육기관 회원으로 전환</h5>
      
      <div class="px-2 py-2">
      	<form name="changeAcademyFrm">
      	
	      <div class="d-flex flex-column w-50">
	        <%-- 교육기관명 입력란 --%>
	        <label for="academy_name" class="label_update">교육기관명</label>
	        <input type="text" id="academy_name" name="academy_name" class="input_academy_member rounded pl-2 w-100" placeholder="교육기관명을 입력해주세요">
	        <p id="academy_name_error" class="error">올바른 교육기관명을 입력해주세요</p>
	        
	        <%-- 사업자등록번호 --%>
	        <label for="company_num" class="label_update mt-3">사업자등록번호(xx-xxx-xxxxx)</label>
	        <div class="d-flex">
	          <input type="text" id="input_company_num1" class="input_academy_member input_company_num rounded pl-2 w-25" placeholder="숫자3자리" maxlength="3">
	          <span>&nbsp;-&nbsp;</span>
	          <input type="text" id="input_company_num2" class="input_academy_member input_company_num rounded pl-2 w-25" placeholder="숫자2자리" maxlength="2">
	          <span>&nbsp;-&nbsp;</span>
	          <input type="text" id="input_company_num3" class="input_academy_member input_company_num rounded pl-2 w-25" placeholder="숫자5자리" maxlength="5">
	        </div>
	        
	        <p id="company_num_error" class="error">유효한 사업자등록번호를 입력해주세요</p>
	        
	        <%-- 교육기관 전화번호 --%>
	        <label for="tel" class="label_update mt-3">기관 전화번호</label>
	        <input type="text" id="tel" name="tel" class="input_academy_member rounded pl-2 w-100" placeholder="기관 전화번호를 입력해주세요(- 제외)">
	        <p id="company_num_error" class="error">올바른 전화번호 입력해주세요</p>
	        
	        <%-- 기관 홈페이지 url --%>
	        <label for="homepage" class="label_update mt-3">기관 홈페이지 URL</label>
	        <input type="text" id="homepage" name="homepage" class="input_academy_member rounded pl-2 w-100" placeholder="비밀번호 확인">
	        <p id="company_num_error" class="error">올바른 URL을 입력해주세요.</p>
	      </div>
	      <input type="hidden" id="company_num" name="company_num"/>
	      <input type="hidden" name="userid" value="${sessionScope.user.userid}"/>
	    </form>
	    
	    <div class="d-flex justify-content-between align-items-center">
	      <span class="guide_content">
	      	<i class="fa-solid fa-circle-info"></i>
        	교육기관 회원으로 전환 시 관리자가 승인할 때 까지 "대기"상태가 되며 로그인이 안됩니다.<br>
        	&nbsp;&nbsp;&nbsp;&nbsp;승인에는 1~2일정도 소요될 수 있습니다.
      	  </span>
	      <button id="btn_changeAcaMember" class="btn btn-light border rounded" onclick="changeAcaMember()">전환</button>
	    </div>
	  </div>
	  <div class="d-flex justify-content-end">
      	
      </div>
	  </c:if>
      
	  
		
	  <br>
      <hr>
      <br>

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
