<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 신고 상세보기 페이지입니다. --%>



<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/report/detail.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/report/detail.js" ></script>

<script type="text/javascript">
    sessionStorage.setItem("report_nickname","${requestScope.reportDetail.report_nickname}");
    sessionStorage.setItem("reported_nickname","${requestScope.reportDetail.reported_nickname}");
</script>

  <div id="section" class="d-flex">
    
    <%---------------------------- 사이드바 호출 --------------------------------%>
    
    <jsp:include page="/WEB-INF/views/tiles1/admin/assembly/sidebar.jsp" />
    
    <%---------------------------- 사이드바 호출 ---------------------------------------%>


    <!---------------------------- 사이드바 오른쪽 컨텐츠(div#main) 시작 ------------------------------->
    <div id="main" class="py-5 px-4 w-100">

      <!-- 신고상세보기시작 -->
      <div id="report_detail_box">
        <!-- 신고번호 -->
        <div id="report_num">
          <span class="report_info_title py-3">신고번호</span>
          <span class="py-3">${requestScope.reportDetail.report_num}</span>
        </div>

        <!-- 신고분류 -->
        <div id="report_type">
          <span class="report_info_title py-3">신고분류</span>
          <span class="py-3">${requestScope.reportDetail.report_type}</span>
        </div>

        <!-- 신고 글번호 -->
        <div id="report_num">
          <span class="report_info_title py-3">신고 글번호</span>
          <span class="py-3">${requestScope.reportDetail.fk_num}</span>
        </div>

        <!-- 신고자 닉네임 -->
        <div id="reported_nickname">
          <span class="report_info_title py-3">신고자 닉네임</span>
          <span class="py-3">${requestScope.reportDetail.report_nickname}</span>
        </div>

        <!-- 피신고자 닉네임 -->
        <div>
          <span class="report_info_title py-3">피신고자 닉네임</span>
          <span class="py-3">${requestScope.reportDetail.reported_nickname}</span>
        </div>

        <!-- 신고사유 -->
        <div id="simple_report_reason">
          <span class="report_info_title py-3">신고사유</span>
          <span class="py-3">${requestScope.reportDetail.simple_report_reason}</span>
        </div>
  
        <!-- 신고사유가 기타사유인경우 상세보기 -->
        <div id="detail_report_reason">
          <span class="report_info_title py-3">상세사유</span>
          <span class="py-3">${requestScope.reportDetail.detail_report_reason}</span>
        </div>
  
        <!-- 신고날짜 -->
        <div id="report_date">
          <span class="report_info_title py-3">신고날짜</span>
          <span class="py-3">${requestScope.reportDetail.report_date}</span>
        </div>
      </div>
      <!-- 신고상세보기 끝 -->

      <!-- 횟수 -->
      <div class="cnt_area d-flex flex-column mt-3">
        <!-- 신고횟수 -->
        <div class="mt-2">${requestScope.reportDetail.report_nickname} 회원이 신고한 횟수 : <span>리퀘스트스코프 데이터</span> 건</div>
        <!-- 신고횟수가 0건 이상이라면 -->
        <span id="btn_view_list_report" data-toggle="modal" data-target="#reportListModal" data-dismiss="modal">내역보기</span>
        <!-- 신고당한횟수 -->
        <div class="mt-2">${requestScope.reportDetail.reported_nickname} 회원이 신고당한횟수 : <span>리퀘스트스코프 데이터</span>건</div>
        <!-- 신고당한횟수가 0건 이상이라면 -->
        <span id="btn_view_list_reported" data-toggle="modal" data-target="#reportedListModal" data-dismiss="modal">내역보기</span>
      </div>

      <div id="div_btn_area" class="d-flex justify-content-end mt-3">
        <button type="button" class="btn btn-light border rounded mr-3" onclick="location.href='<%= ctxPath%>/detail.do?boardNum=${requestScope.reportDetail.fk_num}'">해당게시물로 이동</button>
        <button type="button" id="btn_receipt" class="btn btn-light border rounded mr-3" onclick="receipt('${requestScope.reportDtail.report_num}')">접수처리</button>
        <button type="button" id="insert_penalty_modal_open" class="btn btn-light border rounded" data-toggle="modal" data-target="#insert_penalty_modal" data-dismiss="modal">피신고자 정지</button>
      </div>


      

    </div>
    <!---------------------------------- 사이드바 오른쪽 컨텐츠(div#main) 끝 ------------------------------------->


  </div>






  <!------------------------------------- Modal 시작------------------------------>
  <!-- 신고자가 신고한내역 모달 시작 -->
  <div class="modal fade" id="reportListModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h5 class="modal-title">${requestScope.reportDetail.report_nickname} 신고내역</h5>
          <button type="button" class="close reportListModalClose" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div id="list_report" class="modal-body d-flex flex-column">
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn border reportListModal_close" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- 신고자가 신고한내역 모달 끝 -->





  <!-- 신고자가 신고당한내역 모달 시작 -->
  <div class="modal fade" id="reportedListModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <button type="button" class="close reportedListModalClose" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div id="list_reported" class="modal-body d-flex flex-column">
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn border reportedListModalclose" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- 신고자가 신고한내역 모달 끝 -->





  <!-- 정지등록 폼 모달 시작 -->
  <div class="modal fade" id="insert_penalty_modal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h5 class="modal-title">${requestScope.reportDetail.reported_nickname} 회원 정지</h5>
          <button type="button" class="close insert_penalty_modalClose" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body d-flex flex-column">
          <form name="penaltyRegisterFrm">

            <div class="d-flex flex-column py-3 px-3">

              <!-- 닉네임 -->
              <label for="nickname">닉네임</label>
              <input type="text" id="nickname" name="nickname" class="border rounded pl-2" value="${requestScope.reportDetail.reported_nickname}" readonly>

              <!-- 정지간단사유 -->
              <label for="simple_penalty_reason" class="mt-2">정지사유</label>
              <select class="border rounded" name="simple_penalty_reason" id="simple_penalty_reason">
                <option>욕설/비방</option>
                <option>허위글게시</option>
                <option>정치적인글</option>
                <option>기타사유</option>
              </select>

              <!-- 정지상세사유 -->
              <div id="detail_penalty_reason_area" class="flex-column mt-2">
                <label for="detail_penalty_reason">상세사유 작성</label>
                <textarea name="detail_penalty_reason" id="detail_penalty_reason" cols="30" rows="10" placeholder="정지 사유를 상세하게 작성하세요." class="border rounded px-2 py-2"></textarea>
              </div>

              <!-- 정지기간 -->
              <label for="penalty_period" class="mt-2">정지기간</label>
              <select class="border rounded" name="penalty_period" id="penalty_period">
                <option>7일</option>
                <option>30일</option>
                <option>90일</option>
                <option>150일</option>
                <option>300일</option>
              </select>

            </div>
          </form>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-light border rounded">정지등록</button>
          <button type="button" class="btn border insert_penalty_modal_close" data-dismiss="modal">저장</button>
        </div>
      </div>
      
    </div>
  </div>
  <!------------------------------------- Modal 끝 ------------------------------>
  