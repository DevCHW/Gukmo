<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>
<%-- 광고 상세보기 페이지입니다.. --%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/advertisement/detail.css" />
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/advertisement/detail.js" ></script>

<script type="text/javascript">
	sessionStorage.setItem("advertisement_num","${requestScope.adDetail.advertisement_num}")
    $(document).ready(function(){
      const statusArr = ['진행중','종료'];
      let currentStatus = "${requestScope.adDetail.status}";
      if("${requestScope.adDetail.status}" == '0'){
        currentStatus = '진행중';
      } else {
        currentStatus = '종료';
      }
      let html = "";
      //광고상태 select태그에 값 넣기
      for(let i = 0; i<statusArr.length ;i++){
        if(statusArr[i] != currentStatus){  //광고상태가 같지 않다면
          html += "<option>"+statusArr[i]+"</option>";
        } else{ //광고상태가 같다면 기본값으로 넣기
          html += "<option selected>"+statusArr[i]+"</option>";
        }
      }//end of for--
      $("select#select_status").html(html);
      html = "";
    });//end of $(document).ready(function(){})--
 </script>


<%-- Main Content --%>
<div id="content">
  <%---------------------------- 사이드바 오른쪽 컨텐츠(container-fluid) 시작 -------------------------------%>
  <%-- Begin Page Content --%>
  <div class="container-fluid">
  	  <%-- Page Heading --%>
      <h1 class="h3 my-2 text-gray-800">${requestScope.adDetail.client_name}고객 광고</h1>
      <p class="mb-4"></p>
      <%-- DataTales Example --%>
	  <div class="card shadow mb-4">
      	    <div class="card-body">
			  <div class="mb-2">광고이미지</div>
			  <%-- 광고이미지 if 구분이 게시판이라면 크기 잡기--%>
			  <c:if test="${requestScope.adDetail.division} == '게시판'">
			    <div id="advertisement_img_box">
			      <img id="advertisement_img" src="<%=ctxPath %>/resources/images/${requestScope.advertisement.fileName}"/>
			    </div>
			  </c:if>
			
			  <%-- 광고이미지 if 구분이 메인이라면 크기 잡기 --%>
			  <c:if test="${requestScope.adDetail.division == '메인'}">
			    <div id="advertisement_img_box">
			      <img id="advertisement_img" src="<%=ctxPath %>/resources/images/${requestScope.advertisement.fileName}"/>
			    </div>
			  </c:if>
			
			  <div id="advertisement_basic_info_title" class="d-flex my-3">
			    <div>
			      <%-- 광고번호--%>
			      <div class="basic_info_title py-3 border-right border-bottom">광고번호</div>
			
			      <%-- 광고구분 --%>
			      <div class="basic_info_title py-3 border-right border-bottom">구분</div>
			
			      <%-- 고객명 --%>
			      <div class="basic_info_title py-3 border-right border-bottom">고객명</div>
			
			      <%-- 고객연락처 --%>
			      <div class="basic_info_title py-3 border-right border-bottom">고객연락처</div>
			
			      <%-- 첨부파일 --%>
			      <div class="basic_info_title py-3 border-right border-bottom">첨부파일</div>
			
			      <%-- URL --%>
			      <div class="basic_info_title py-3 border-right border-bottom">URL</div>
			
			      <%-- 광고기간 --%>
			      <div class="basic_info_title py-3 border-right border-bottom">광고기간</div>
			      
			      <%-- 광고상태 --%>
			      <div class="basic_info_title py-3 border-right">상태</div>
			    </div>
			    
			
			
			
			    <form name="editAdvertisementFrm" class="w-100">
			      <div id="advertisement_basic_info_area">
			        <%-- 광고번호 --%>
			        <div class="py-3 px-3 border-bottom">${requestScope.adDetail.advertisement_num}</div>
			
			        <%-- 광고구분 --%>
			        <div class="py-3 px-3 border-bottom">${requestScope.adDetail.division}</div>
			
			        <%-- 고객명 --%>
			        <div class="py-3 px-3 border-bottom">${requestScope.adDetail.client_name}</div>
			
			        <%-- 고객연락처 --%>
			        <div class="py-3 px-3 border-bottom">${requestScope.adDetail.client_phone}</div>
			
			        <%-- 첨부파일(다운로드 링크) --%>
			        <div class="py-3 px-3 border-bottom">
			          <span id="attach_file" onclick="location.href='<%= ctxPath %>/admin/download.do?advertisement_num=${requestScope.adDetail.advertisement_num}'">${requestScope.adDetail.orgfilename}</span>
			        </div>
			
			        <%-- URL --%>
			        <div class="py-3 px-3 border-bottom">${requestScope.adDetail.url}</div>
			
			        <%-- 광고기간 --%>
			        <div class="py-3 px-3 border-bottom">
			          <span>${requestScope.adDetail.start_date}</span>
			          <span>&nbsp;~&nbsp;</span>
			          <span>${requestScope.adDetail.end_date}</span>
			          <span>(${requestScope.adDetail.period})일</span>
			        </div>
			
			        <div id="datepicker_area" class="py-3 px-3 border-bottom">
			          <input type="text" name = "start_date" id="start_date" value="${requestScope.adDetail.start_date}" class="pl-2 border rounded" readonly>
			          <span>&nbsp;~&nbsp;</span>
			          <input type="text" id="end_date" name="end_date" value="${requestScope.adDetail.end_date}" class="pl-2 border rounded" readonly>
			        </div>
			        
			        <%-- 광고상태 --%>
			        <div class="py-3 px-3">
			          <c:if test="${requestScope.adDetail.status == 0}">진행중</c:if>
			          <c:if test="${requestScope.adDetail.status == 1}">종료</c:if>
			        </div>
			        <div class="edit_select_area py-3 px-3">
			          <select name="status" id="select_status" class="border rounded"></select>
			        </div>
			      </div>
			      <input type="hidden" name = "advertisement_num" value="${requestScope.adDetail.advertisement_num}">
			    </form>
			  </div>
			    
			      
			
			  <div class="d-flex justify-content-end">
			  <button type="button" class="btn btn-light border rounded mr-3" onclick="javascript:history.back()">뒤로가기</button>
			    <button id="btn_edit_advertisement" type="button" class="btn btn-light border rounded mr-3">수정</button>
			    <button id="btn_delete" type="button" class="btn btn-light border rounded">삭제</button>
			    <button id="btn_edit_close" type="button" class="btn btn-light border rounded">취소</button>
			  </div>
			</div>
		</div>
	</div>
</div>
<%---------------------------------- (div#main) 끝 -------------------------------------%>


