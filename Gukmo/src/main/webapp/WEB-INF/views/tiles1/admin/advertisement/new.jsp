
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   
<%
	String ctxPath = request.getContextPath();
%>
  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/advertisement/new.css" />
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/advertisement/new.js" ></script>



  <div id="section" class="d-flex">
    <!---------------------------- 사이드바 호출 -------------------------------->
    <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/sidebar.jsp" />
    <!---------------------------- 사이드바 호출 --------------------------------------->



    <!---------------------------- 사이드바 오른쪽 컨텐츠(div#main) 시작 ------------------------------->
    <div id="main" class="py-5 px-4 w-100">
      <form name="advertisementNewFrm">
        <div id="input_box" class="d-flex flex-column m-auto">
          <!-- 광고분류 -->
          <label for="division" class="advertisement_label">광고분류</label>
          <select name="division" id="division" class="advertisement_input border rounded">
            <option>광고분류 선택</option>
            <option>메인</option>
            <option>게시판</option>
          </select>
          <!-- 고객명 -->
          <label for="client_name" class="advertisement_label">고객명</label>
          <input type="text" id="client_name" name="client_name" class="advertisement_input border rounded" placeholder="고객명을 입력해주세요">
          <p id="client_name_error" class="error mt-1">고객명을 입력해주세요</p>

          <!-- 고객연락처 -->
          <label for="client_phone" class="advertisement_label">고객연락처</label>
          <input type="text" id="client_phone" name="client_phone" class="advertisement_input border rounded" placeholder="고객연락처를 입력해주세요">
          <p id="client_phone_error" class="error mt-1">고객연락처는 숫자만 입력해주세요</p>

          <!-- 파일 -->
          <label for="orgfilename" class="advertisement_label">파일</label>
          <input type="file" id="orgfilename" name="orgfilename">

          <!-- 광고 이미지 미리보기 -->
          <div id="advertisement_img_box" class="border rounded mt-3">
            <img id="advertisement_img" src="" alt="광고이미지 미리보기">
          </div>

          <!-- URL주소 -->
          <label for="url" class="advertisement_label">URL</label>
          <input type="text" id="url" name="url" class="advertisement_input border rounded" placeholder="URL을 입력해주세요">
          <p id="url_error" class="error mt-1">유효한 URL을 입력해주세요</p>

          

          <!-- 게시날짜 datepicker-->
          <label for="date" class="advertisement_label">날짜</label>
          <div class="d-flex align-items-center">
            <input type="text" id="start_date" name="start_date" class="advertisement_input border rounded" placeholder="시작일을 입력해주세요" readonly>
            <span>&nbsp;~&nbsp;</span>
            <input type="text" id="end_date" name="end_date" class="advertisement_input border rounded" placeholder="종료일을 입력해주세요" readonly>
          </div>

          <div id="btn_area" class="d-flex justify-content-end mt-3">
            <button id="btn_add" type="button" class="btn btn-light border rounded">등록</button>
          </div>
        </div>

        
      </form>
    </div>
    <!---------------------------------- 사이드바 오른쪽 컨텐츠(div#main) 끝 ------------------------------------->
  </div>
 