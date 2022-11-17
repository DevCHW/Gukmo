<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/boardList.css" />

<!-- 스마트 에디터  -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/boardNew.js" ></script>

  <div class="container">
    <!-- 커뮤니티 작성 폼 시작 -->
    <form action="communityNewEnd" name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
	  <!-- 등록용 닉네임 -->
	  
	  <!--  
	  		<input type="hidden" name="fk_userid" value="${sessionScope.loginuser.nickname}" />
	  -->
	  
      <!-- category -->
      <label for="detail_category" class="community_label">구분</label>
      <select name="detail_category" id="detail_category" class="community_input pl-2 border rounded">
        <option value="">구분을 선택해주세요</option>
        <option>스터디</option>
        <option>자유게시판</option>
        <option>Q&A</option>
        <option>정보공유</option>
        <option>수강/취업 성공 후기</option>
      </select>


      <!-- subject -->
      <label for="subject" class="community_label mt-3">제목</label>
      <input type="text" id="subject" name="subject" class="community_input border rounded pl-2" placeholder="제목을 입력하세요">



      <!-- hashtag -->
      <label for="hashtag" class="community_label mt-3">태그</label>
      <div id="hashtag_box" class="border rounded pl-2">
        <ul id="hashtag_list" class="d-flex align-items-center">
          <input type="text" id="hashtag" name="hashtag" class="border-0" placeholder="태그를 설정하세요(최대 5개)">
        </ul>
      </div>

      <!-- content -->
      <label for="content" class="community_label mt-3">본문</label>
      <textarea name="content" id="content" class="px-2 py-2 border rounded" cols="30" rows="15" placeholder="추후 네이버스마트에디터 구현예정,input,select,textarea태그에 박스쉐도우 넣기"></textarea>
    </form>
    <!-- 커뮤니티 작성 폼 끝 -->

    <!-- 수정일 경우에는 등록 대신 수정버튼 태그라이브러리로 구현예정 -->
    <div id="btn_wrapper" class="d-flex justify-content-end mt-3">
      <button id="btn_write" type="button" class="btn border rounded">등록</button>
      <button id="btn_cancle" type="button" class="btn border rounded ml-3">취소</button>
    </div>
  </div>