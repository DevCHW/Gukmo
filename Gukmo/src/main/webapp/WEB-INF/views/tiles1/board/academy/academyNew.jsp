<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/academyNew.css" />
<%-- Google reCAPTCHA v2 --%>
<script src="https://www.google.com/recaptcha/api.js"></script>
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/academyNew.js" ></script>



  <div class="container my-5">
    <%-- 국비학원 작성 폼 시작 --%>
    <form name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
	  
	  <%-- 등록용 닉네임 --%>
	  <input type="hidden" name="nickname" value="${sessionScope.user.nickname}" />
	  <input type="hidden" name="profile_image" value="${sessionScope.user.profile_image}" />
	  <input type="hidden" id="str_hashTag" name="str_hashTag" value=""/>
	  <%-- 주소 --%>
      <input type="hidden" id="address" name="address" value="${board.academy.address}" >
      <input type="hidden" name="orgin_hashTag" value="<c:forEach var="hashtags" items="${requestScope.board.hashtags}">
			  ${hashtags.hashtag}</c:forEach>" >
	  <input type="hidden" id="board_num" name="board_num" value="${requestScope.board.board_num}"/>
	  
      

      <%-- 학원명 --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">학원명</label>
        <c:if test="${not empty sessionScope.user.academy_name}">
        	<input type="text" id="subject" name="subject" class="academy_input border rounded pl-2 w-100" value="${sessionScope.user.academy_name}" readonly>
        </c:if>
        <c:if test="${sessionScope.user.authority == '관리자'}">
        	<input type="text" id="subject" name="subject" class="academy_input border rounded pl-2 w-100" value="${requestScope.board.subject}" placeholder="학원명을 입력해주세요!" maxlength="50">
        </c:if>
      </div>
      
      <%-- 대표자명 --%>
      <div class="d-flex align-items-center my-2">
        <label for="representative_name" class="academy_label mt-3">대표자명</label>
        <input type="text" id="representative_name" name="representative_name" class="academy_input border rounded pl-2 w-100" value="${board.academy.representative_name}" placeholder="대표자명을 입력해주세요" maxlength="10">
      </div>
      
      
      <%-- 지역 선택 --%>
      
      <div class="d-flex">
        <label for="subject" class="academy_label mt-3">지역선택</label>
        <div data-toggle="dropdown">
           <div id="location2" class="d-flex border rounded justify-content-center align-items-center mr-3">
           	 <i class="fa-solid fa-location-dot mr-2"></i>
           	 <span id="address1">지역 대분류</span>
           </div>
        </div>
        
         <div id="big_location" class="dropdown-menu" class="border rounded px-3 py-2">
            <span class='dropdown-item big_location_element'>서울</span>
            <span class="dropdown-item big_location_element">부산</span>
            <span class="dropdown-item big_location_element">대구</span>
            <span class="dropdown-item big_location_element">인천</span>
            <span class="dropdown-item big_location_element">광주</span>
            <span class="dropdown-item big_location_element">대전</span>
            <span class="dropdown-item big_location_element">울산</span>
            <span class="dropdown-item big_location_element">세종</span>
            <span class="dropdown-item big_location_element">경기</span>
            <span class="dropdown-item big_location_element">강원</span>
            <span class="dropdown-item big_location_element">충북</span>
         </div>
         
         <div>
           <div id="abc" data-toggle="dropdown">
             <div id="location3" class="d-flex border rounded justify-content-center align-items-center">
             	 <i class="fa-solid fa-location-dot mr-2"></i>
             	 <span id="address2">지역 소분류</span>
             </div>
           </div>
           
           <div id="small_location2" class="dropdown-menu" class="border rounded px-3 py-2">
           </div>
         </div>
      </div>
      
      
      <%-- 학원 주소 --%>
      <div class="d-flex align-items-center my-2">
        <label for="address3" class="academy_label mt-3">상세주소</label>
        <input type="text" id="address3" name="address3" class="academy_input border rounded pl-2 w-100" placeholder="상세주소를 입력해주세요">
      </div>
      
      
      <%-- 관할 노동사무소 --%>
      <div class="d-flex align-items-center my-2">
        <label for="jurisdiction" class="academy_label mt-3">관할 노동사무소</label>
        <input type="text" id="jurisdiction" name="jurisdiction" value="${board.academy.jurisdiction}" class="academy_input border rounded pl-2 w-100" placeholder="관할 노동사무소를 입력해주세요">
      </div>
      
      
      <%-- 문의처 --%>
      <div class="d-flex align-items-center my-2">
        <label for="phone" class="academy_label mt-3">문의처</label>
        <input type="text" id="phone" name="phone" class="academy_input border rounded pl-2 w-100" value="${board.academy.phone}" placeholder="문의처를 입력해주세요">
      </div>
      
      <%-- 홈페이지URL --%>
      <div class="d-flex align-items-center my-2">
        <label for="homepage" class="academy_label mt-3">홈페이지 URL</label>
        <input type="text" id="homepage" name="homepage" class="academy_input border rounded pl-2 w-100" value="${board.academy.homepage}" placeholder="홈페이지 URL을 입력해주세요">
      </div>
      
      <%-- 학원이미지 --%>
      <div class="d-flex align-items-center my-2">
        <label for="subject" class="academy_label mt-3">학원이미지</label>
        <button type="button" id="btn_academy_image" class="btn btn-info">학원이미지 선택</button>
        <input type="file" id="academy_image" name="academy_image">
      </div>
      <c:if test="${not empty requestScope.board}">
      <img src="<%=ctxPath%>/resource/images/${board.academy.academy_image}" style="height:200px;" id="academy_image_preview"/>
      </c:if>
      
      
      
      <%-- content --%>
      <label for="content" class="academy_label mt-3">학원소개글</label>
      <textarea name="content" id="content" class="px-2 py-2 w-100 border rounded" value="${requestScope.board.subject}" cols="30" rows="15" placeholder="이제 학원을 설명해주세요!"></textarea>
    
    
      <%-- hashtag --%>
      <label for="hashtag" class="academy_label mt-3">태그</label>
      <div id="hashtag_box" class="border rounded pl-2">
        <ul id="hashtag_list" class="d-flex align-items-center">
          <c:forEach var="hashtags" items="${requestScope.notice.hashtags}" varStatus="status">
          <li class='d-flex align-items-center flex-nowrap mr-2 tag-item'>#${hashtags.hashtag}<span class='btn_hashtag_delete mx-2' style='cursor:pointer; color:darkgray; idx='${status.count}'><i class='fa-solid fa-xmark'></i></span></li>
		  </c:forEach>
          <input type="text" id="hashtag" name="hashtag" class="border-0 w-auto" placeholder="태그를 설정하세요(최대 5개)">
        </ul>
      </div>
    </form>
    <%-- 국비학원 작성 폼 끝 --%>
	
	<%-- Google reCAPTCHA --%>
    <div class="d-flex justify-content-center my-5">
      <div class="g-recaptcha" data-sitekey="6LdO7zkjAAAAAFk660Urlo0EbazNdIIW9aFnJXLH"></div>
    </div>
     
    <%-- 수정일 경우에는 등록 대신 수정버튼 태그라이브러리로 구현예정 --%>
    <div id="btn_wrapper" class="d-flex justify-content-end mt-3">
      <c:if test="${not empty requestScope.board}" >
      <button id="btn_edit" type="button" class="btn border rounded">수정</button>
      </c:if>
      <c:if test="${empty requestScope.board}" >
      <button id="btn_write" type="button" class="btn border rounded">등록</button>
      </c:if>
      <button id="btn_cancle" type="button" class="btn border rounded ml-3" onclick="javascript:history.back()">취소</button>
    </div>
  </div>