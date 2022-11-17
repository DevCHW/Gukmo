<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/hyunwoo/css/myInfo.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/hyunwoo/js/myInfo.js" ></script>




  <div class="container mt-4">
    <%-- 사이드바 시작 --%>
    <div id="sidebar">
      <h5 id="sidebar_title">내 계정</h5>
      <ul>
        <li>회원정보</li>
        <li>계정</li>
        <li>활동 내역</li>
      </ul>
    </div>
    <%-- 사이드바 끝 --%>

    <%-- 내정보 시작 --%>
    <div id="main">
      <h5>회원정보</h5>
      
      <%-- 이름 --%>
      <form action="" method="post">
        <div id="section1" class="justify-content-between mb-5">
          <div id="input_box" class="d-flex flex-column">
            <label for="" class="input_label">이름</label>
            <input type="text" id="username" name="username" class="border rounded pl-2" placeholder="이름을 입력해주세요">
            <%-- 닉네임 --%>
            <label for="" class="input_label mt-3">닉네임</label>
            <input type="text" id="nickname" name="nickname" class="border rounded pl-2" placeholder="닉네임을 입력해주세요(10자이내)">
          </div>
          <%-- 프사 --%>
          <div class="d-flex align-items-baseline">
            <div id="profile_img_box" class="border">
              <img src="../images/user-solid.svg"/>
              <div id="img_mask" class="justify-content-center align-items-center">
                <span style="color:white; font-weight:bold;">이미지 변경</span>
              </div>
            </div>
            <button id="btn_change" class="btn btn-white border rounded ml-3">변경</button>
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
            <input type="checkbox">
            <span class="slider round"></span>
          </label>
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
