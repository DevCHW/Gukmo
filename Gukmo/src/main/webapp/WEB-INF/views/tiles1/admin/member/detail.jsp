<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>
    
    
<script type="text/javascript">
    //상세정보 메뉴바를 위해서 세션스토리지에 userid값,nickname값 저장하기
    sessionStorage.setItem("userid","${requestScope.member.userid}");
    sessionStorage.setItem("nickname","${requestScope.member.nickname}");
    sessionStorage.setItem("status","${requestScope.member.status}");
    
    $(document).ready(function(){
      let html = "";
      const statusArr = ['활동','정지','휴면','대기'];
      const currentStatus = "${requestScope.member.status}";
      const authorityArr = ['일반회원','관리자'];
      const currentAuthority = "${requestScope.member.authority}";
      
      //회원상태 select태그에 값 넣기
      for(let i = 0; i<statusArr.length ;i++){
        if(statusArr[i] != currentStatus){  //회원상태가 같지 않다면
          html += "<option>"+statusArr[i]+"</option>";
        } else{ //회원 상태가 같다면 기본값으로 넣기
          html += "<option selected>"+statusArr[i]+"</option>";
        }
      }//end of for--
      $("select#select_status").html(html);
      
      html = ""; // html 초기화
      //회원권한 select태그에 값 넣기
      for(let i = 0; i<authorityArr.length ;i++){
        if(authorityArr[i] != currentAuthority){  //회원상태가 같지 않다면
          html += "<option>"+authorityArr[i]+"</option>";
        } else{ //회원 상태가 같다면 기본값으로 넣기
          html += "<option selected>"+authorityArr[i]+"</option>";
        }
      }//end of for--
      $("select#select_authority").html(html);

    });//end of $(document).ready(function(){})--
</script> 
    
    
    
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/member/detail.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/member/detail.js" ></script>
    




    <!---------------------------- 사이드바 오른쪽 컨텐츠(div#main) 시작 ------------------------------->
    <div id="main" class="container py-5 px-4 w-100">
      <!-- 회원 기본정보 박스 시작 -->
      <div id="member_basic_info_box">
        <div id="id_image_box" class="align-items-center my-3">
          <!-- profile_image border속성 지워야 함-->
          <div id="profile_img_box" class="mr-3 border">
            <c:if test="${requestScope.member.profile_image.substring(0,4) != 'http'}">
              <img src="<%=ctxPath %>/resources/images/${requestScope.member.profile_image}">
            </c:if>
            <c:if test="${requestScope.member.profile_image.substring(0,4) == 'http'}">
              <img src="${requestScope.member.profile_image}">
            </c:if>
          </div>
          <!-- nickname -->
          <div id="nickname">
            ${requestScope.member.nickname} 회원 정보
          </div>
        </div>

        <div class="wrap_box">
          <div>
            <!-- 이메일수신동의일경우 나타내기 -->
            <c:if test="${requestScope.member.email_acept == 1}">
              <button id="btn_send_email" type="button" class="btn btn-light border rounded" data-toggle="modal" data-target="#sendEmailModal" data-dismiss="modal">이메일 전송하기</button>
            </c:if>
          </div>

          <hr>

          <!-- userid -->
          <div id="userid" class="member_basic_info py-3">
            <span class="info_title">아이디</span>
            <span>${requestScope.member.userid}</span>
          </div>

          <!-- username -->
          <div id="username" class="member_basic_info py-3">
            <span class="info_title">회원이름</span>
            <span>${requestScope.member.username}</span>
          </div>

          <!-- email -->
          <div id="email" class="member_basic_info py-3">
            <span class="info_title">이메일</span>
            <span>${requestScope.member.email}</span>
          </div>
          

          <!-- point -->
          <div id="point" class="member_basic_info py-3">
            <span class="info_title">활동점수</span>
            <span>${requestScope.member.point}</span>
          </div>
          

          <!-- email_acept if문으로 표현 0:동의 1:비동의-->
          <div id="email_acept" class="member_basic_info py-3">
            <span class="info_title">이메일수신동의여부</span>
            <span>
              <c:if test="${requestScope.member.email_acept == 0}">거부</c:if>
	          <c:if test="${requestScope.member.email_acept == 1}">동의</c:if>
            </span>
          </div>



          <!-- status -->
          <div id="status" class="member_basic_info py-3">
            <span class="info_title">상태</span>
            <select name="status" id="select_status" class="border rounded">
            </select>
            <span class="mr-2">${requestScope.member.status}</span>
            <span id="btn_insert_penalty_modal" data-toggle="modal" data-target="#insert_penalty_modal" data-dismiss="modal">정지사유등록</span>
            <!-- 회원상태가 정지일경우 정지사유보기 버튼 -->
            <c:if test="${requestScope.member.status == '정지'}">
              <span id="view_reason" data-toggle="modal" data-target="#penalty_reason" data-dismiss="modal">정지사유보기</span>
            </c:if>
          </div>


		  <!-- 만약 member 에 academy_name 이 비어있지 않다면 -->
		  <c:if test="${not empty requestScope.member.academy_name}">
            <!-- academy_name -->
            <div id="academy_name" class="member_basic_info py-3">
              <span class="info_title">교육기관명</span>
              <span>${requestScope.member.academy_name}</span>
            </div>
            
            <!-- company_num -->
            <div id="company_num" class="member_basic_info py-3">
              <span class="info_title">사업자등록번호</span>
              <span>${requestScope.member.company_num}</span>
            </div>
            <!-- homepage -->
            <div id="homepage" class="member_basic_info py-3">
              <span class="info_title">홈페이지URL</span>
              <span>${requestScope.member.homepage}</span>
            </div>
            
            <!-- tel -->
            <div id="tel" class="member_basic_info py-3">
              <span class="info_title">교육기관 전화번호</span>
              <span>${requestScope.member.tel}</span>
            </div>
          </c:if>

          


          <!-- join_date -->
          <div id="join_date" class="member_basic_info py-3">
            <span class="info_title">가입일자</span>
            <span>${requestScope.member.join_date}</span>
          </div>


          <!-- authority -->
          <div id="authority" class="member_basic_info d-flex align-items-center py-3">
            <span class="info_title">권한</span>
            <select name="authority" id="select_authority" class="border rounded">
            </select>
            <span>${requestScope.member.authority}</span>
          </div>

          <!-- 소셜계정 연동여부 -->
          <div id="sns_login" class="member_basic_info d-flex align-items-center py-3">
            <span class="info_title">
              SNS 연동여부
            </span>
            <c:if test="${requestScope.member.kakao == '1'}">
              <div class="sns_image mx-1">
                <img src="<%=ctxPath %>/resources/images/login/카카오로그인.PNG" class="rounded">
              </div>
            </c:if>
            <c:if test="${requestScope.member.naver == '1'}">
              <div class="sns_image mx-1">
                <img src="<%=ctxPath %>/resources/images/login/네이버로그인.png" class="rounded">
              </div>
            </c:if>
            <c:if test="${requestScope.member.google == '1'}">
              <div class="sns_image mx-1">
                <img src="<%=ctxPath %>/resources/images/login/구글로그인.PNG" class="border rounded">
              </div>
            </c:if>
            <c:if test="${requestScope.member.facebook == '1'}">
              <div class="sns_image mx-1">
                <img src="<%=ctxPath %>/resources/images/login/페이스북로그인.svg" class="rounded">
              </div>
            </c:if>
            <c:if test="${requestScope.member.facebook != '1' && requestScope.member.google != '1' && 
            			requestScope.member.naver != '1' && requestScope.member.kakao != '1'}">
            	<span>SNS연동회원 X</span>
            </c:if>
          </div>
        </div>
      </div>
      <!-- 회원 기본정보 박스 끝 -->
      <div class="d-flex justify-content-end">
      	<button type="button" class="btn btn-light border rounded mr-3" onclick="javascript:history.back()">뒤로가기</button>
        <button id="btn_editInfo" type="button" class="btn btn-light border rounded mr-3">수정</button>
        <button id="btn_editClose" type="button" class="btn btn-light border rounded">취소</button>
      </div>

      <hr>


      <!-- 회원관련 데이터 네비게이션 바 클릭시 전부 ajax로 html 태그 넣기 -->
      <div id="member_navbar" class="d-flex justify-content-center align-items-center my-2 py-2">
        <div id="activities_nav" class="px-3 py-2">활동내역</div>
        <div id="search_nav" class="px-3 py-2">검색기록</div>
        <div id="login_record_nav" class="px-3 py-2">로그인기록</div>
        <div id="write_board_list_nav" class="px-3 py-2">작성게시물</div>
        <div id="report_nav" class="px-3 py-2">신고내역</div>
      </div>
      <!-- 회원관련 데이터 네비게이션 바 클릭시 전부 ajax로 html 태그 넣기 -->





      <!-- 회원의 활동내역 시작 꺾은선그래프로 일자별,월별,년도별 활동횟수를 나타낼 수 있음 -->
      <div id="member_activities" class="detail_info_area my-3">
        <div id="member_activities_chart_area" class="border rounded">
          활동내역차트영역
        </div>

        <div id="member_activities_area" class="border rounded">
          활동내역차트영역
        </div>
      </div>
      <!-- 회원의 활동내역 끝 꺾은선그래프로 일자별,월별,년도별 활동횟수를 나타낼 수 있음 -->






      <!-- 회원의 검색 기록 시작: 꺾은선그래프로 일자별,월별,년도별 검색횟수를 나타낼 수 있음,가장많이검색한 검색어 10가지 보여주기 -->
      <div id="member_search" class="detail_info_area my-3">
        <!-- 회원이 많이검색한 검색어 반복문 시작 -->
        <div id="many_keyword_area">
          회원이 많이검색한 검색어영역
        </div>
        <!-- 회원이 많이검색한 검색어 반복문 끝 -->


        <!-- 검색어 차트영역 시작 -->
        <div id="member_search_chart_area" class="border rounded my-3">
          검색어 차트영역
        </div>
        <!-- 검색어 차트영역 끝 -->
      </div>
      <!-- 회원의 검색 기록 끝: 꺾은선그래프로 일자별,월별,년도별 검색횟수를 나타낼 수 있음,가장많이검색한 검색어 10가지 보여주기 -->





      <!-- 회원의 로그인 기록 시작: 꺾은선그래프로 일자별,월별,년도별 로그인횟수를 나타낼 수 있음 -->
      <div id="member_login_record" class="detail_info_area my-3">
        <div id="login_record_chart_area" class="border rounded">
          로그인기록차트영역
        </div>

      </div>
      <!-- 회원의 로그인 기록 끝: 꺾은선그래프로 일자별,월별,년도별 로그인횟수를 나타낼 수 있음 -->



      <!-- 작성한 게시물 내역 시작 -->
      <div id="member_write_board_list" class="detail_info_area my-3">
        <div id="write_board_list_area" class="border rounded">
          작성한 게시물 내역 영역
        </div>
      </div>
      <!-- 작성한 게시물 내역 끝-->




      <!-- 신고내역 시작 -->
      <div id="member_report_list" class="detail_info_area my-3">
        <div id="report_list_area" class="border rounded">
          신고한내역 영역
        </div>

        <div id="reported_list_area" class="border rounded">
          신고당한내역 영역
        </div>
      </div>
      <!-- 신고내역 끝 -->

    </div>
    
    <!---------------------------------- 사이드바 오른쪽 컨텐츠(div#main) 끝 ------------------------------------->




  <!------------------------------------------------- modal시작 -------------------------------->

  <!---------------- 정지 사유보기 모달 시작 ------------------>
  <div class="modal fade" id="penalty_reason">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h5 class="modal-title">${requestScope.member.nickname}회원 정지사유</h5>
          <button type="button" class="close email_certificationClose" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="m-auto">
            <!-- 정지번호 -->
            <div class="my-2">
              <div class="penalty_reason">정지번호</div>
              <div>${requestScope.penalty.penalty_num}</div>
            </div>
  
            <!-- 정지간단사유 -->
            <div class="my-2">
              <div class="penalty_reason">정지사유</div>
              <div>${requestScope.penalty.simple_penalty_reason}</div>
            </div>
  
            <!-- 정지상세사유 -->
            <div class="my-2">
              <div class="penalty_reason">상세</div>
              <div id="penalty_detail_reason">
                <p>${requestScope.penalty.detail_penalty_reason}</p>
              </div>
            </div>
  
            <!-- 정지기간 -->
            <div class="my-2">
              <div class="penalty_reason">정지 기간</div>
              <div class="d-flex">
                <span>${requestScope.penalty.penalty_start_date}</span>
                <span>&nbsp;~&nbsp;</span>
                <span>${requestScope.penalty.penalty_end_date}</span>
                <span>(${requestScope.penalty.penalty_period}일)</span>
              </div>
            </div>
          </div>

        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn border penalty_reason_close" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
  <!--------------------- 정지 사유보기 모달 끝 ------------------->








  <!------------- 이메일 보내기 모달 시작 ---------------->
  <div class="modal fade" id="sendEmailModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h5 class="modal-title">이메일 발송하기</h5>
          <button type="button" class="close email_certificationClose" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body d-flex flex-column">
          <label for="email_message">메세지</label>
          <textarea name="email_message" id="email_message" cols="30" rows="10" class="px-2 py-2 border rounded" placeholder="회원에게 전송할 메세지를 입력해주세요."></textarea>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn border" onclick="sendEmail('${requestScope.member.email}')">전송</button>
          <button type="button" class="btn border sendEmailModal_close" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- 이메일 보내기 모달 끝 -->



  <!-- 정지등록 폼 모달 시작 -->
  <div class="modal fade" id="insert_penalty_modal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h5 class="modal-title">${requestScope.member.nickname} 정지사유등록하기</h5>
          <button type="button" class="close email_certificationClose" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body d-flex flex-column">
          <form name="penaltyRegisterFrm">

            <div class="d-flex flex-column py-3 px-3">

              <!-- 닉네임 -->
              <label for="nickname"></label>
              <input type="text" id="nickname" name="nickname" value="${requestScope.member.nickname}">

              <!-- 정지간단사유 -->
              <label for="simple_penalty_reason">정지사유</label>
              <select name="simple_penalty_reason" id="simple_penalty_reason">
                <option>욕설/비방</option>
                <option>허위글게시</option>
                <option>정치적인글</option>
                <option>기타사유</option>
              </select>

              <!-- 정지상세사유 -->
              <div id="detail_penalty_reason_area" class="flex-column">
                <label for="detail_penalty_reason">상세사유 작성</label>
                <textarea name="detail_penalty_reason" id="detail_penalty_reason" cols="30" rows="10" placeholder="정지 사유를 상세하게 작성하세요." class="border rounded px-2 py-2"></textarea>
              </div>

              <!-- 정지기간 -->
              <label for="penalty_period">정지기간</label>
              <select name="penalty_period" id="penalty_period">
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
          <button type="button" class="btn border insert_penalty_modal_close" data-dismiss="modal">저장</button>
        </div>
      </div>
      
    </div>
  </div>
  <!--------------------- 정지등록 폼 모달 끝 ------------------------->





  <!------------------------------------------------- modal끝 -------------------------------->