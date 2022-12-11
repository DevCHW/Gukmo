<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seonwoo/boardNew.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/communityNew.js" ></script>

<script type="text/javascript">
  sessionStorage.setItem("detail_category","${requestScope.boardvo.detail_category}");
</script>



  <div class="container">
    <%-- 커뮤니티 작성 폼 시작 --%>
    <form name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
	  
	  <%-- 등록용 닉네임 --%>
	  <input type="hidden" name="nickname" value="${sessionScope.user.nickname}" />
	  <input type="hidden" name="profile_image" value="${sessionScope.user.profile_image}" />
	  <input type="hidden" name="orgin_hashTag" value="<c:forEach var="hashtags" items="${requestScope.boardvo.hashtags}">
	  ${hashtags.hashtag}
	  </c:forEach>" >
	  <input type="hidden" id="str_hashTag" name="str_hashTag" value=""/>
	  <input type="hidden" id="board_num" name="board_num" value="${requestScope.boardvo.board_num}"/>
	  
      <%-- category --%>
      <label for="detail_category" class="community_label">구분</label>
      <select name="detail_category" id="detail_category" class="community_input pl-2 border rounded">
        <option value="">구분을 선택해주세요</option>
        <option value="스터디">스터디</option>
        <option value="자유게시판">자유게시판</option>
        <option value="QnA">Q&A</option>
        <option value="정보공유">정보공유</option>
        <option value="수강/취업후기">수강/취업 후기</option>
      </select>


      <%-- subject --%>
      <label for="subject" class="community_label mt-3">제목</label>
      <input type="text" id="subject" name="subject" class="community_input border rounded pl-2" value="${requestScope.boardvo.subject}" placeholder="제목을 입력하세요">



      <%-- hashtag --%>
      <label for="hashtag" class="community_label mt-3">태그</label>
      <div id="hashtag_box" class="border rounded pl-2">
        <ul id="hashtag_list" class="d-flex align-items-center">
          <c:forEach var="hashtags" items="${requestScope.boardvo.hashtags}" varStatus="status">
          <li class='d-flex align-items-center flex-nowrap mr-2 tag-item'>#${hashtags.hashtag}<span class='btn_hashtag_delete mx-2' style='cursor:pointer; color:darkgray; idx='${status.count}'><i class='fa-solid fa-xmark'></i></span></li>
		  </c:forEach>
          <input type="text" id="hashtag" name="hashtag" class="border-0" placeholder="태그를 설정하세요(최대 5개)">
        </ul>
      </div>
      
      
      
      <%-- content --%>
      <label for="content" class="community_label mt-3">본문</label>
      <textarea name="content" id="content" class="px-2 py-2 border rounded" cols="30" rows="15">${requestScope.boardvo.content}</textarea>
    </form>
    <%-- 커뮤니티 작성 폼 끝 --%>

    <%-- 수정일 경우에는 등록 대신 수정버튼 태그라이브러리로 구현예정 --%>
    <div id="btn_wrapper" class="d-flex justify-content-end mt-3">
    <c:if test="${not empty requestScope.boardvo}" >
	  <button id="btn_modify" type="button" class="btn border rounded">수정</button>
    </c:if>
    <c:if test="${empty requestScope.boardvo}" >
      <button id="btn_write" type="button" class="btn border rounded">등록</button>
    </c:if>
      <button id="btn_cancle" type="button" class="btn border rounded ml-3" onclick="javascript:history.back()">취소</button>
    </div>
  </div>