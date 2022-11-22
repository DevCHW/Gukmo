<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/hyunwoo/findId.css" />
  
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/findId.js" ></script>


  <div id="findIdFrm" class="mx-auto my-5 py-5">
    <div id="logo_img_box" class="m-auto">
      <img id="logo" src="" alt="로고 들어갈 곳">
    </div>

    <div class="my-3">
      <h2 class="text-center">계정찾기</h2>
      <p id="about_content" class="text-center">회원 가입시 입력하신 이메일 주소를 입력하시면,<br>
        해당 이메일로 아이디 및 비밀번호 변경 링크를 보내드립니다.</p>
    </div>

    <form name="findmyIdFrm" class="d-flex flex-column mt-5">
      <label for="email">이메일 주소</label>
      <input type="text" id="email" name="email" class="pl-2 border rounded" placeholder="이메일을 입력해주세요"s/>
    </form>

    <div class="d-flex justify-content-between mt-3">
      <button id="btn_close" class="btn btn-light border-0">취소</button>
      <button id="btn_findMyId" class="border-0 rounded">계정찾기</button>
    </div>

    <div id="findMyIdError" class="my-3 px-3 py-3 rounded">
      <div class="m-auto text-center">
        <p><i class="fa-solid fa-info"></i>&nbsp;계정찾기 실패!</p>
        <p>&nbsp;해당 이메일 주소를 찾을 수 없습니다.</p>
      </div>
    </div>
  </div>
