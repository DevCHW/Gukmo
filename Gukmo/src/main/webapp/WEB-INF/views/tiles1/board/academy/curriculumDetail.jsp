<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   


<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/curriculumDetail.css" />

<div id="curriculum-detail-box" class="border mt-4">
      <%-- 교육기관 --%>
      <div class="curriculum_row border-bottom">
        <div class="curriculum_title px-2 py-2">교육기관</div>
        <div class="curriculum_content w-100 px-2 py-2">${board.curriculum.academy_name}</div>
      </div>

      <%-- 핵심기술 --%>
      <div class="curriculum_row border-bottom">
        <div class="curriculum_title px-2 py-2">핵심기술</div>
        <div class="curriculum_content w-100 px-2 py-2">${board.curriculum.core_technology}</div>
      </div>

      <%-- 과정기간 --%>
      <div class="curriculum_row border-bottom">
        <div class="curriculum_title px-2 py-2">과정기간</div>
        <div class="curriculum_content w-100 px-2 py-2">${board.curriculum.curriculum_start_date} ~ ${board.curriculum.curriculum_end_date}(${board.curriculum.curriculum_period}일)</div>
      </div>
      <%-- 모집기간 --%>
      <div class="curriculum_row border-bottom">
        <div class="curriculum_title px-2 py-2">모집기간</div>
        <div class="curriculum_content w-100 px-2 py-2">${board.curriculum.recruitment_start_date} ~ ${board.curriculum.recruitment_end_date}(${board.curriculum.recruitment_period}일)</div>
      </div>
      <%-- 모집인원 --%>
      <div class="curriculum_row border-bottom">
        <div class="curriculum_title px-2 py-2">모집인원</div>
        <div class="curriculum_content w-100 px-2 py-2">${board.curriculum.cnt_recruits}명</div>
      </div>
      <%-- 신청링크 --%>
      <div class="curriculum_row">
        <div class="curriculum_title px-2 py-2">신청링크</div>
        <div class="curriculum_content w-100 px-2 py-2"><a href="${board.curriculum.join_url}">${board.curriculum.join_url}</a></div>
      </div>
    </div>