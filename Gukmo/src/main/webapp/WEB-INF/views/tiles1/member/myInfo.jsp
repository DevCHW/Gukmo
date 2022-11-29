<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script type="text/javascript">
	sessionStorage.setItem("username",'${sessionScope.user.username}');
	sessionStorage.setItem("nickname",'${sessionScope.user.nickname}');
</script>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/myInfo.css" />
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/myInfo.js" ></script>



  <div class="container my-4">
    <%-------------------- 사이드바 시작 ----------------------%>
        
    <%-- sidebar 호출 --%>
	<jsp:include page="/WEB-INF/views/tiles1/member/sidebar.jsp" />
        
        
    <%-------------------- 사이드바 끝 ----------------------%>

    <%-- 내정보 시작 --%>
    <div id="main">
      <h5>회원정보</h5>
      
      <%-- 이름 --%>
      <form name="myInfoFrm" enctype="multipart/form-data">
        <div id="section1" class="justify-content-between mb-5">
          <div id="input_box" class="d-flex flex-column">
            <label for="username" class="input_label">이름</label>
            <input type="text" id="username" name="username" class="border rounded pl-2" value='${sessionScope.user.username}' placeholder="이름을 입력해주세요">
            <%-- 닉네임 --%>
            <label for="nickname" class="input_label mt-3">닉네임</label>
            <input type="text" id="nickname" name="nickname" class="border rounded pl-2" value='${sessionScope.user.nickname}' placeholder="닉네임을 입력해주세요(10자이내)">
          </div>
          <%-- 프사 --%>
          <div class="d-flex align-items-baseline">
            <div id="profile_img_box" class="border">
              <c:if test="${sessionScope.user.kakao == '0' || fn:substring(sessionScope.user.profile_image,0,4) != 'http'}">
                <img id="profile_img" src="<%=ctxPath %>/resources/images/${sessionScope.user.profile_image}"/>
              </c:if>
               <c:if test="${sessionScope.user.kakao != '0' && fn:substring(sessionScope.user.profile_image,0,4) == 'http'}">
             	  <img id="profile_img" src="${sessionScope.user.profile_image}"/>
               </c:if>
              <div id="img_mask" class="justify-content-center align-items-center">
                <span style="color:white; font-weight:bold;">이미지 변경</span>
              </div>
            </div>
            <button type="button" id="btn_change" class="btn btn-white border rounded ml-3">변경</button>
          </div>
        </div>

        <hr>
        
        <div class="d-flex flex-column">
          <span class="guide_title">이메일 수신동의</span>
          <span class="guide_content">국비의모든것에서 주최하는 다양한 이벤트,정보성 뉴스레터 및 광고 수신여부를 설정할 수 있습니다</span>
        </div>
          

        <div class="d-flex justify-content-between align-items-center mt-4">
          <div class="d-flex flex-column">
            <span class="guide_title">뉴스레터 및 마케팅 메일 수신</span>
            <span class="guide_content">유저가 만드는 다양한 컨텐츠를 뉴스레터로 받습니다.</span>
          </div>

          <%-- 토글스위치 --%>
          <label class="switch">
            <input type="checkbox" id="email_agreement" name="email_agreement">
            <span class="slider round"></span>
          </label>
          <input type="hidden" id="email_acept" name="email_acept" value="${sessionScope.user.email_acept}"/>
          <input type="hidden" name="userid" value="${sessionScope.user.userid }"/>
          <input type="hidden" name="profile_image" value="${sessionScope.user.profile_image} "/>
          <input type="hidden" name="benickname" value="${sessionScope.user.nickname }"/>  
        </div>

        <hr>


        <div class="d-flex justify-content-end">
          <input type="file" id="profile_select" name="profile_image">
          <button type="button" id=btn_save class="btn btn-info rounded">저장</button>
        </div>
      </form>










    </div>
    <%-- 내정보 끝 --%>


  </div>
