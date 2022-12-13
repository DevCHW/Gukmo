<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/curriculumNew.css" />
<%-- Google reCAPTCHA v2 --%>
<script src="https://www.google.com/recaptcha/api.js"></script>
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/curriculumNew.js" ></script>

  <div class="container my-5">
    <%-- 교육과정 작성 폼 시작 --%>
    <form name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
	  
	  <%-- 등록용 닉네임 --%>
	  <input type="hidden" name="nickname" value="${sessionScope.user.nickname}" />
	  <input type="hidden" name="profile_image" value="${sessionScope.user.profile_image}" />
	  <input type="hidden" id="str_hashTag" name="str_hashTag" value=""/>



	  <%-- 학원명 --%>
      <div class="d-flex align-items-center my-3">
      
        <label for="academy_name" class="curriculum_label mt-3">학원명</label>
        <c:if test="${not empty sessionScope.user.academy_name }">
        	<input type="text" id="academy_name" name="academy_name" class="curriculum_input border rounded pl-2 w-100" value="${sessionScope.user.academy_name}" readonly>
        </c:if>
        <c:if test="${sessionScope.user.authority == '관리자'}">
        <input type="text" id="academy_name" name="academy_name" class="curriculum_input border rounded pl-2 w-100" placeholder="학원명을 입력해주세요!" maxlength="20">
        </c:if>
      </div>
      
      
      
      <%-- 과정명 --%>
      <div class="d-flex align-items-center my-3">
        <label for="subject" class="curriculum_label mt-3">교육과정명</label>
        <input type="text" id="subject" name="subject" class="curriculum_input border rounded pl-2 w-100" placeholder="교육과정명을 입력해주세요!" maxlength="50">
      </div>
      
      
      <%-- 과정기간 --%>
      <div class="d-flex align-items-center my-3">
        <label for="period" class="curriculum_label">과정기간</label>
        <input type="text" id="curriculum_start_date" name="curriculum_start_date" class="curriculum_input border rounded pl-2 w-100" placeholder="과정 시작일자" readonly>
        <span class="tilde text-center">&nbsp;~&nbsp;</span>
        <input type="text" id="curriculum_end_date" name="curriculum_end_date" class="curriculum_input border rounded pl-2 w-100" placeholder="과정 마감일자" readonly>
      </div>
      
      
      <%-- 모집기간 --%>
      <div class="d-flex align-items-center my-3">
        <label for="period" class="curriculum_label">모집기간</label>
        <input type="text" id="recruitment_start_date" name="recruitment_start_date" class="curriculum_input border rounded pl-2 w-100" placeholder="모집 시작일자" readonly>
        <span class="tilde text-center">&nbsp;~&nbsp;</span>
        <input type="text" id="recruitment_end_date" name="recruitment_end_date" class="curriculum_input border rounded pl-2 w-100" placeholder="모집 마감일자" readonly>
      </div>
      
      
      <%-- 모집인원 --%>
      <div class="d-flex align-items-center my-3">
        <label for="cnt_recruits" class="curriculum_label mt-3">모집인원(명)</label>
        <input type="text" id="cnt_recruits" name="cnt_recruits" class="curriculum_input border rounded pl-2 w-100" placeholder="모집인원을 입력해주세요!(명)" maxlength="4">
      </div>
      
      
      <%-- 신청URL --%>
      <div class="d-flex align-items-center my-3">
        <label for="join_url" class="curriculum_label mt-3">과정신청링크</label>
        <input type="text" id="join_url" name="join_url" class="curriculum_input border rounded pl-2 w-100" placeholder="과정을 신청할 수 있는 URL을 입력해주세요!">
      </div>
      
      
      <%-- 핵심기술 --%>
      <div class="d-flex align-items-center my-3">
        <label for="core_technology" class="curriculum_label mt-3">핵심기술</label>
        <input type="text" id="core_technology" name="core_technology" class="curriculum_input border rounded pl-2 w-100" placeholder="교육과정 중 핵심기술을 입력해주세요!">
      </div>
      
      
      <%-- content --%>
      <label for="content" class="curriculum_label my-3">과정소개글</label>
      <textarea name="content" id="content" class="px-2 py-2 w-100 border rounded" cols="30" rows="15" placeholder="이제 학원을 설명해주세요!"></textarea>
    
    
      <%-- hashtag --%>
      <label for="hashtag" class="curriculum_label my-3">태그</label>
      <div id="hashtag_box" class="border rounded pl-2">
        <ul id="hashtag_list" class="d-flex align-items-center">
          <input type="text" id="hashtag" name="hashtag" class="border-0 w-auto" placeholder="태그를 설정하세요(최대 5개)">
        </ul>
      </div>
    </form>
    <%-- 교육과정 작성 폼 끝 --%>
    
    <%-- Google reCAPTCHA --%>
    <div class="d-flex justify-content-center my-5">
      <div class="g-recaptcha" data-sitekey="6LdO7zkjAAAAAFk660Urlo0EbazNdIIW9aFnJXLH"></div>
    </div>

    <%-- 수정일 경우에는 등록 대신 수정버튼 태그라이브러리로 구현예정 --%>
    <div id="btn_wrapper" class="d-flex justify-content-end my-3">
      <button id="btn_write" type="button" class="btn border rounded">등록</button>
      <button id="btn_cancle" type="button" class="btn border rounded ml-3" onclick="javascript:history.back()">취소</button>
    </div>
  </div>