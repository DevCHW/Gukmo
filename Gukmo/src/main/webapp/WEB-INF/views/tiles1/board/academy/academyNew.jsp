<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/academyNew.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/academyNew.js" ></script>

  <div class="container">
    <%-- 커뮤니티 작성 폼 시작 --%>
    <form name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
	  
	  <%-- 등록용 닉네임 --%>
	  <input type="hidden" name="nickname" value="${sessionScope.user.nickname}" />
	  <input type="hidden" name="profile_image" value="${sessionScope.user.profile_image}" />
	  <input type="hidden" id="str_hashTag" name="str_hashTag" value=""/>
	  
      <%-- 지역선택 --%>
      <div class="d-flex align-items-center my-2">
        <label for="detail_category" class="academy_label">지역</label>
        <select name="" id="" class="academy_input pl-2 border rounded">
      	  <option>서울</option>
        </select>
      </div>


      <%-- 학원명 --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">학원명</label>
        <input type="text" id="subject" name="subject" class="academy_input border rounded pl-2" placeholder="제목을 입력하세요">
      </div>
      
      <%-- 대표자명 --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">대표자명</label>
        <input type="text" id="subject" name="subject" class="academy_input border rounded pl-2" placeholder="제목을 입력하세요">
      </div>
      
      <%-- 학원 주소 --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">학원 주소</label>
        <input type="text" id="subject" name="subject" class="academy_input border rounded pl-2" placeholder="제목을 입력하세요">
      </div>
      
      <%-- 관할 노동사무소 --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">관할 노동사무소</label>
        <input type="text" id="subject" name="subject" class="academy_input border rounded pl-2" placeholder="제목을 입력하세요">
      </div>
      
      <%-- 홈페이지URL --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">홈페이지 URL</label>
        <input type="text" id="subject" name="subject" class="academy_input border rounded pl-2" placeholder="제목을 입력하세요">
      </div>
      
      <%-- 학원이미지 --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">학원이미지</label>
        <button type="button" id="btn_academy_image" class="btn btn-info">학원이미지 선택</button>
        <img src="" id="academy_image_preview" alt="학원이미지"/>
        <input type="file" id="academy_image" name="academy_image">
      </div>

      
      
      <%-- content --%>
      <label for="content" class="academy_label mt-3">학원소개글</label>
      <textarea name="content" id="content" class="px-2 py-2 w-100 border rounded" cols="30" rows="15" placeholder="이제 학원을 설명해주세요!"></textarea>
    </form>
    <%-- 커뮤니티 작성 폼 끝 --%>
    
    <%-- hashtag --%>
    <label for="hashtag" class="academy_label mt-3">태그</label>
    <div id="hashtag_box" class="border rounded pl-2">
      <ul id="hashtag_list" class="d-flex align-items-center">
        <input type="text" id="hashtag" name="hashtag" class="border-0 w-auto" placeholder="태그를 설정하세요(최대 5개)">
      </ul>
    </div>

    <%-- 수정일 경우에는 등록 대신 수정버튼 태그라이브러리로 구현예정 --%>
    <div id="btn_wrapper" class="d-flex justify-content-end mt-3">
      <button id="btn_write" type="button" class="btn border rounded">등록</button>
      <button id="btn_cancle" type="button" class="btn border rounded ml-3" onclick="javascript:history.back()">취소</button>
    </div>
  </div>