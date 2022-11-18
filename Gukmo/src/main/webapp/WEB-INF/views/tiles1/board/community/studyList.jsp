<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
 
<!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/boardList.css" />

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/studyList.js" ></script>

  <div class="container mt-4">
    <div id="category" class="d-flex justify-content-center align-content-center">
      <div class="mx-2 px-2 py-1 rounded">자유게시판</div>
      <div class="mx-2 px-2 py-1 rounded">Q&A</div>
      <div class="mx-2 px-2 py-1 rounded">정보공유</div>
      <div class="mx-2 px-2 py-1 rounded">스터디</div>
      <div class="mx-2 px-2 py-1 rounded">취미모임</div>
      <div class="mx-2 px-2 py-1 rounded active">수강/취업성공후기</div>
    </div>

     <!-- 검색창 영역 -->
     <div class="searchBar d-flex mx-auto my-4">
        <input type="text" id="searchWord" class="pl-2" placeholder="검색할 내용을 입력해 주세요!"/>
        <button type="button" id="btn_search">
          <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
        </button>
     </div>
    <!-- 검색바 끝 -->


    <!-- navbar 시작 -->
    <div id="nav" class="d-flex align-items-center py-2">
      <!-- 필터 시작 이곳에 자바스크립트로 필터 넣으세요. -->
      <div id="filter_area" class="d-flex align-items-end">
        
      </div>
      <!-- filter_area -->

      
      <div class="d-flex ml-auto">
        <div id="btn_filter" class="d-flex justify-content-center align-items-center border rounded">
          <i class="fa-solid fa-filter"></i>
        </div>
  
        <div id="mask"></div>
        <div id="sort" class="d-flex ml-3 border rounded justify-content-center align-items-center">
          <i class="fa-solid fa-arrow-down-short-wide"></i>
          <span id=current_sort>최신순</span>
          <div id="sort_option" class="border rounded px-3 py-2">
            <span>최신순</span>
            <span>추천순</span>
            <span>댓글순</span>
            <span>조회순</span>
          </div>
        </div>
      </div>
    </div>
    <!-- 필터 끝 -->



    <!------------------------------------- 게시판 리스트 시작 ------------------------------------->

    <!-- 이 div가 반복문 시작 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          <i class="fa-solid fa-bolt"></i>
          <span>97</span>
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <div class="hashtag ml-1">
            <!-- 해시태그 리스트 들어갈 곳-->
            <!-- 해시태그리스트 반복문시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
            <!-- 해시태그리스트 반복문 끝-->
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>
    <!-- 이 div가 반복문 끝 -->



    <!------------------------ 더미데이터 시작 -------------------------->
    <!-- 1 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          97
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <!-- 해시태그 리스트 들어갈 곳-->
          <div class="hashtag ml-1">
            <!-- 반복분시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>

    <!-- 2 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          97
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <!-- 해시태그 리스트 들어갈 곳-->
          <div class="hashtag ml-1">
            <!-- 반복분시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>

    <!-- 3 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          97
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <!-- 해시태그 리스트 들어갈 곳-->
          <div class="hashtag ml-1">
            <!-- 반복분시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>

    <!-- 4 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          97
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <!-- 해시태그 리스트 들어갈 곳-->
          <div class="hashtag ml-1">
            <!-- 반복분시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>

    <!-- 5 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          97
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <!-- 해시태그 리스트 들어갈 곳-->
          <div class="hashtag ml-1">
            <!-- 반복분시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>

    <!-- 6 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          97
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <!-- 해시태그 리스트 들어갈 곳-->
          <div class="hashtag ml-1">
            <!-- 반복분시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>

    <!-- 7 -->
    <div class="border-top px-2 py-2">
      <div class="d-flex align-items-center my-2">
        <!-- 작성자 프로필사진 -->
        <a href="#" class="writer_image_box border">
          <img src=""/>
        </a>

        <!-- 작성자 닉네임 -->
        <!-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. -->
        <a href="#" class="writer_nickname ml-2">
          국모관리팀
        </a>

        <!-- 작성자 활동점수 -->
        <div class="writer_point ml-2">
          97
        </div>

        <!-- 작성일자 -->
        <div class="write_date ml-2">
          1일전
        </div>
      </div>

      <!-- 글제목 -->
      <a href="#" class="subject align-items-center my-2">
        [중요알림] 국비의모든것 v0.0.1 오픈 안내
      </a>

      <div class="d-flex justify-content-between align-items-center my-2">
        <div class="d-flex align-items-center">
          <!-- 게시판상세카테고리 클릭하면 해당 게시판으로 이동하게 하세요 -->
          <div class="detail_category border rounded px-2 py-1">
            공지사항
          </div>
          <!-- 해시태그 리스트 들어갈 곳-->
          <div class="hashtag ml-1">
            <!-- 반복분시작 -->
            <a href="#" class="hashtag mx-1">#<span>국모</span></a>
            <a href="#" class="hashtag mx-1">#<span>해시태그</span></a>
            <a href="#" class="hashtag mx-1">#<span>구현성공</span></a>
            <a href="#" class="hashtag mx-1">#<span>하시길</span></a>
            <a href="#" class="hashtag mx-1">#<span>기원합니다!</span></a>
          </div>
        </div>

        <!-- 조회수,댓글수,추천수 -->
        <div class="board_info_box d-flex justify-content-end">
          <!-- 조회수 -->
          <div>
            <i class="fa-solid fa-eye"></i>
            <span>40</span>
          </div>

          <!-- 댓글수 -->
          <div class="ml-2">
            <i class="fa-solid fa-comment-dots"></i>
            <span>20</span>
          </div>

          <!-- 추천수 -->
          <div class="ml-2">
            <i class="fa-solid fa-heart"></i>
            <span>30</span>
          </div>
        </div>

      </div>
    </div>
    <!------------------------ 더미데이터 끝 -------------------------->
    
    <!----------------------------------- 게시판 리스트 끝 ------------------------------------->

    <div class="d-flex border-top pt-3 justify-content-between">

      <div id="total_cnt">
        <!-- 총 건수 변수 들어갈 곳-->
        총&nbsp;<span style="font-weight:bold;">1257&nbsp;</span>건
      </div>

      <button type="button" id="btn_write" class="btn border-0 rounded">
        +교육기관 등록
      </button>
    </div>





    <!----------------------------------------------------------- 페이지 바 시작 --------------------------------------------->
    <nav aria-label="...">
      <ul class="my pagination pagination-md justify-content-center mt-5">
        <!-- 첫페이지로 이동버튼 -->
        <li class="page-item">
          <a class="page-link" href="#">
            <i class="fa-solid fa-angles-left"></i>
          </a>
        </li>
        
        
        <!-- 전페이지로 이동버튼 -->
        <li class="page-item">
          <a class="page-link" href="#">
            <i class="fa-solid fa-angle-left"></i>
          </a>
        </li>
        
        <!-- 페이지번호 시작-->
        <li class="page-item active" aria-current="page">
          <a class="page-link" href="#">1</a>
        
        <li class="page-item">
          <a class="page-link" href="#">2</a>
        </li>

        <li class="page-item">
          <a class="page-link" href="#">3</a>
        </li>

        <li class="page-item">
          <a class="page-link" href="#">4</a>
        </li>

        <li class="page-item">
          <a class="page-link" href="#">5</a>
        </li>
        <!-- 페이지번호 끝 -->
              
              
              
        <!-- 다음페이지로 이동버튼 -->
        <li class="page-item">
          <a class="page-link" href="#"><i class="fa-solid fa-angle-right"></i></a>
        </li>

        <!-- 맨 끝페이지로 이동버튼 -->
        <li class="page-item">
          <a class="page-link" href="#"><i class="fas fa-solid fa-angles-right"></i></a>
        </li>
      </ul>
    </nav>
		<!----------------------------------------------------------- 페이지 바 끝 --------------------------------------------->
  </div>


  



  
</body>
</html>