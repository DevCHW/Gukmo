<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

   
<%
	String ctxPath = request.getContextPath();
%>

    <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>일반회원 관리 페이지</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" type="text/css" href="bootstrap-4.6.0-dist/css/bootstrap.min.css" > 
  <!-- Font Awesome 5 Icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
  <!-- title Icon -->
  <link href="images/titleicon.png" rel="shortcut icon" type="image/x-icon">
  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/memberManage_List.css" />
  
  <!-- Optional JavaScript -->
  <script type="text/javascript" src="jquery3.6.0/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>
  <!-- sweet alert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
  <!-- toastr css라이브러리 -->
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
  <!-- toastr cdn 라이브러리 둘다 제이쿼리 밑에 있어야함 -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>  

  <!-- JqueryUI JS,CSS -->
  <link rel="stylesheet" type="text/css" href="jquery-ui-1.13.1.custom/jquery-ui.min.css" />
  <script type="text/javascript" src="jquery-ui-1.13.1.custom/jquery-ui.min.js" ></script> 
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/memberManage_List.js" ></script>
</head>

<body>


  <div class="container">
    
    <!-- navbar 시작 -->
    <div id="nav" class="d-flex align-items-center mt-2">
      <!-- 필터 시작 -->
      <div id="filter_area" class="d-flex align-items-center mt-2">
        
      </div>
      <!-- filter_area -->

      
      <div class="d-flex ml-auto">
        <div id="btn_filter" class="d-flex justify-content-center align-items-center border rounded">
          <i class="fa-solid fa-filter"></i>
        </div>
  
        <div id="mask"></div>
        <div id="sort" class="d-flex ml-3 border rounded justify-content-center align-items-center">
          <i class="fa-solid fa-arrow-down-short-wide"></i>
          <span>최신순</span>
          <div id="sort_option" class="border rounded pl-3 pt-2">
            <a href="#">최신순</a>
            <a href="#">인기순</a>
            <a href="#">박휘순</a>
            <a href="#">무순</a>
            <a href="#">커밍순</a>
            <a href="#">김삼순</a>
          </div>
        </div>
      </div>
      
    </div>
    <!-- 필터 끝 -->

    <hr>

    <h4 style="font-weight:bold;">일반 회원 관리</h4>

    <!------------------------------------- 학원 리스트 테이블 시작 ------------------------------------->
    <table class="table table-hover mt-2">
      <thead>
        <tr>
          <th><input type="checkbox"/></th>
          <th>아이디</th>
          <th>이름</th>
          <th>닉네임</th>
          <th>이메일</th>
          <th>가입일자</th>
        </tr>
      </thead>
      <tbody>
		<c:forEach var="membervo" items="${requestScope.memberList}" varStatus="status">
            <tr id = "">
              <td><input type="checkbox" id="chxAll"/></td>
              <td style="cursor:pointer" onclick="memberDetail()"><span>${membervo.userid}</span></td>
              <td style="cursor:pointer" onclick="memberDetail()"><span>${membervo.username}</span></td>
              <td style="cursor:pointer" onclick="memberDetail()"><span>${membervo.nickname}</span></td>
              <td style="cursor:pointer" onclick="memberDetail()"><span>${membervo.email}</span></td>
              <td style="cursor:pointer" onclick="memberDetail()"><span>${membervo.join_date}</span></td>
            </tr>    
          </c:forEach>
    </tbody>
    </table>
    <!----------------------------------- 학원 리스트 테이블 끝 ------------------------------------->

    <div class="d-flex justify-content-between">

      <div id="total_cnt">
        총&nbsp;<span style="font-weight:bold;">1257&nbsp;</span>건
        <!-- 총 건수 변수 들어갈 곳-->
      </div>

      <button type="button" id="btn_write" class="btn border-0 rounded">
        +교육기관 등록
      </button>
    </div>





    <!----------------------------------------------------------- 페이지 바 시작 --------------------------------------------->
    <nav aria-label="...">
		${requestScope.pageBar}
    </nav>
		<!----------------------------------------------------------- 페이지 바 끝 --------------------------------------------->
  </div>


  <!-- 검색바시작 -->
    <form name="searchFrm" style="margin-top: 20px;">
	    <div id="search_area" class="d-flex mx-auto my-5">
	      <div id="academy_search" class="d-flex m-auto rounded">
	        <div id="input_keyword">
	          <input type="text" id="searchWord" placeholder="교육기관 검색" id="keyword" class="pl-3" name="keyword">
	        </div>
	        <div id="search_btn">
	          <button type="button" class="btn btn-white" id="btn_search"><i class="fas fa-xl fa-thin fa-magnifying-glass"></i></button>
	        </div>
	      </div>
	    </div>
	</form>
    <!-- 검색바 끝 -->



  
</body>
</html>