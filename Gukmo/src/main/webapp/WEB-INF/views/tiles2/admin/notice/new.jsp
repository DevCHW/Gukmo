<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/notice/new.css" />
<%-- Google reCAPTCHA v2 --%>
<script src="https://www.google.com/recaptcha/api.js"></script>
<%-- 네이버스마트에디터 --%>
<script type="text/javascript" src="<%= ctxPath%>/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script> 
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/notice/new.js" ></script>


<script type="text/javascript">
  sessionStorage.setItem("detail_category","${requestScope.boardvo.detail_category}");
</script>


<%-- Main Content --%>
<div id="Main">
  <%-- Begin Page Content --%>
  <div class="container-fluid">
  	  <%-- Page Heading --%>
      <h1 class="h3 my-2 text-gray-800">공지등록</h1>
      <p class="mb-4">국모 회원분들에게 공지할 내용을 작성해주세요!</p>
      
      <div class="card shadow mb-4">
      	<div class="card-body">
      		<%-- 공지사항 작성 폼 시작 --%>
		    <form name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
			  
			  <%-- 등록용 닉네임 --%>
			  <input type="hidden" name="nickname" value="${sessionScope.user.nickname}" />
			  <input type="hidden" name="profile_image" value="${sessionScope.user.profile_image}" />
			  <input type="hidden" name="orgin_hashTag" value="<c:forEach var="hashtags" items="${requestScope.notice.hashtags}">
			  ${hashtags.hashtag}
			  </c:forEach>" >
			  <input type="hidden" id="str_hashTag" name="str_hashTag" value=""/>
			  <input type="hidden" id="board_num" name="board_num" value="${requestScope.notice.board_num}"/>
			  <input type="hidden" id="must_read" name=must_read value="${requestScope.notice.must_read}"/>
			  <div class="d-flex align-items-center">
			  
			  	<c:if test="${requestScope.notice.must_read == '1'}">
			    	<input type="checkbox" id="must_read_checkbox" name="must_read_checkbox" checked/>
			  	</c:if>
			  	<c:if test="${requestScope.notice.must_read != '1'}">
			    	<input type="checkbox" id="must_read_checkbox" name="must_read_checkbox"/>
			  	</c:if>
			  	<label for="must_read_checkbox" class="ml-2 pt-2" style="font-weight:bold;">필독</label>
			  </div>
		      <%-- subject --%>
		      <label for="subject" class="community_label mt-2">제목</label>
		      <input type="text" id="subject" name="subject" class="community_input border rounded pl-2" value="${requestScope.notice.subject}" placeholder="제목을 입력하세요" maxlength="50">
		
		
		
		      <%-- hashtag --%>
		      <label for="hashtag" class="community_label mt-3">태그</label>
		      <div id="hashtag_box" class="border rounded pl-2">
		        <ul id="hashtag_list" class="d-flex align-items-center">
		          <c:forEach var="hashtags" items="${requestScope.notice.hashtags}" varStatus="status">
		          <li class='d-flex align-items-center flex-nowrap mr-2 tag-item'>#${hashtags.hashtag}<span class='btn_hashtag_delete mx-2' style='cursor:pointer; color:darkgray; idx='${status.count}'><i class='fa-solid fa-xmark'></i></span></li>
				  </c:forEach>
		          <input type="text" id="hashtag" name="hashtag" class="border-0" placeholder="태그를 설정하세요(최대 5개)"/>
		        </ul>
		      </div>
		      
		      
		      
		      <%-- content --%>
		      <label for="content" class="community_label mt-3">본문</label>
		      <textarea name="content" id="content" class="px-2 py-2 border rounded" cols="30" rows="15" placeholder="공지사항을 작성해주세요">${requestScope.notice.content}</textarea>
		    </form>
		    <%-- 공지사항 작성 폼 끝 --%>
		    
		    
		    <%-- Google reCAPTCHA --%>
		    <div class="d-flex justify-content-center my-5">
		      <div class="g-recaptcha" data-sitekey="6LdO7zkjAAAAAFk660Urlo0EbazNdIIW9aFnJXLH"></div>
		    </div>
		    
		    
		
		
		    <div id="btn_wrapper" class="d-flex justify-content-end mt-3">
		    <c:if test="${not empty requestScope.notice}" >
			  <button id="btn_modify" type="button" class="btn border rounded ml-3">수정</button>
		    </c:if>
		    <c:if test="${empty requestScope.notice}" >
		      <button id="btn_write" type="button" class="btn border rounded ml-3">등록</button>
		    </c:if>
		      <button id="btn_cancle" type="button" class="btn border rounded ml-3" onclick="javascript:history.back()">취소</button>
		    </div>
		  </div>
      	
      	</div>
      </div>
</div>
    