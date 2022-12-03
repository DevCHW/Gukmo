<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String ctxPath = request.getContextPath();
%>

  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/academyDetail.css" />
  
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/academyDetail.js" ></script>






  <div class="container my-5"> 

    <div class="line my-4">
      <div>${requestScope.academy.category}&nbsp;</div><span>/</span><div>&nbsp;${requestScope.academy.detail_category}</div>   <%-- ${requestScope.board.detail_category} --%>
    </div>



    <%-- 글 상세보기 페이지 머리부분 시작(작성자 프로필이미지, 작성자 닉네임, 활동점수, 작성일자, 조회수, 신고버튼, 수정,삭제버튼) --%>
    <div id="detail_header" class="d-flex justify-content-between align-items-center">

      <div id="writer_profile_image_box">
        <%-- 글 작성자 프로필이미지 --%>
        <img src="<%=ctxPath %>/resources/images/user.PNG">
      </div>


      <div id="writer_profile_body" class="d-flex flex-column w-100 px-2 py-1">
        <%-- 작성자 닉네임 들어가면 해당 유저의 활동내역을 볼수 있는 페이지로 이동--%>
        <div id="board_writer_nickname" class="pl-2">${requestScope.academy.nickname}</div>

        <%-- 활동점수,작성일자,조회수 영역--%>
        <div class="d-flex">
          <%-- 글 작성자 활동점수 --%>
          <div id="board_writer_point" class="mx-2">
            <i class="fa-solid fa-bolt"></i>
            <span>${requestScope.academy.writer_point}</span>
          </div>

          <span>·</span>

          <%-- 글 작성일자(형식은 약 ?분전, ?시간전, ?일전, ?년전) --%>
          <div id="board_write_date" class="mx-2">
            ${requestScope.academy.write_date }
          </div>

          <span>·</span>

          <%-- 글 조회수 --%>
          <div id="board_views" class="mx-2">
            <i class="fa-solid fa-eye"></i>
            <span>${requestScope.academy.views}</span>
          </div>
        </div>
      </div>
      <%-- 신고버튼, 수정or삭제버튼 --%>
      <div id="report_edit_delete_area" class="d-flex justify-content-between align-items-center">
        <span id="btn_report">&#x1F6A8;</span>
        <div id="mask"></div>
        <span id="btn_more" class="border rounded px-2 py-1">&#8230;
          <div id="update_or_delete" class="border rounded px-3 py-2">
            <span>수정하기</span>
            <span id="board_delete" onclick="del_board(${academy.board_num})">삭제하기</span>
          </div>
        </span>
      </div>
    </div>
    <%--------------------------------- 글 상세보기 페이지 머리부분-------------------------%>



    <%-------------------- 글 본문 시작 ------------------%>
    <div id="content_area" class="d-flex flex-column py-2">
      <div id="subject" class="mt-3">
        <h2>${requestScope.academy.subject}</h2>
      </div>

	  <%-- 글내용 --%>
      <div id="content" class="mt-3">${requestScope.academy.content} </div>

      <div class="d-flex justify-content-between mt-4">

        	<%-- 해시태그리스트 반복문시작 --%>
            <c:forEach var="hashtag" items="${academy.hashtags}">
            <a href="#" class="hashtag mx-1">#<span>${hashtag.hashtag}</span></a>
            </c:forEach>
            <%-- 해시태그리스트 반복문 끝--%>

        <div id="board_like">
          <%-- 게시글 좋아요 아이콘, 눌렀을경우 &#x1F497; 안눌렀을경우 &#9825;--%>
          <span>&#x1F497;</span>
          <%-- 게시글 좋아요 갯수 --%>
          <span>${requestScope.academy.like_cnt }</span>
        </div>
      </div>
    </div>
    <%-------------------- 글 본문 끝 ------------------%>




    <%---------------------- 이전글,다음글 영역 시작 ----------------------%>
    <div id="previous_next_area" class="px-3 d-flex justify-content-center align-items-center rounded mt-4">
      <div class="w-100">
        <%-- 이전글 a태그 href에 ?num=이전글번호--%>
        <div id="previous" class="my-2">
          <span>이전글 |</span>
          <a href="?num=1">${requestScope.board.previoussubject}</a>
        </div>
  
        <%-- 다음글 a태그 href에 ?num=다음글번호--%>
        <div id="next" class="my-2">
          <span>다음글 |</span>
          <a href="#">${requestScope.board.nextsubject}</a>
        </div>
      </div>
    </div>
    <%---------------------- 이전글,다음글 영역 끝 ----------------------%>
    







    <%---------------------- 광고 영역 시작 ----------------------%>
    <div id="advertisement_box" class="mt-4">
      <img src="<%= ctxPath%>/resources/images/학원광고이미지1.PNG"/>
    </div>
    <%---------------------- 광고 영역 끝 ----------------------%>


    <hr>




    <%---------------------- 댓글쓰기 영역 시작 ----------------------%>
    <%-- 댓글 총 갯수, 숫자부분에 넣으면 됨 --%>
    <div id="total_comment_cnt" class="my-3">
      <span id="total_comment">${requestScope.academy.comment_cnt}</span><span>개의 댓글</span>
    </div>

    
    <div id="write_comment_area" class="border rounded px-4 py-4">
      <div class="d-flex w-100">
        <div class="login_user_profile_img_box">
          <%-- 로그인되어있는 유저 프로필 이미지 --%>
          <img src="<%=ctxPath %>/resources/images/user.PNG"/>
        </div>
  
        <div class="ml-3 w-100">
          <div class="mb-1">내용</div>
          <textarea name="write_comment" id="write_comment" class="pl-2 py-2" rows="5"></textarea>
  
          <div class="d-flex justify-content-end mt-2">
            <button class="btn btn-info" id="go_comment">댓글 쓰기</button>
          </div>
        </div>
      </div>
    </div>
    <%---------------------- 댓글쓰기 영역 끝 ----------------------%>





    <%---------------------- 댓글리스트 영역(반복문) 시작 ----------------------%>

    <%-- 댓글 반복문 시작 --%>
    <div class="comment_area pb-4">
      <div class="comment px-3 py-4">
        <%-- 댓글작성자의 프로필이미지, 활동점수, 댓글작성일자 --%>
        <div class="comment_writer_info d-flex justify-content-between align-items-center">
          <div class="comment_writer_profile_img_box mr-2">
            <img src="<%=ctxPath %>/resources/images/user.PNG">
          </div>

          <div class="d-flex flex-column w-100">
            <div class="comment_writer_nickname">
              댓글작성자 닉네임
            </div>
  
            <div class="mt-1">
              <%-- 댓글작성자 활동점수 --%>
              <span class="mr-2">
                <i class="fa-solid fa-bolt"></i>
                <span>0</span>
              </span>

              <span>·</span>
              <%-- 댓글작성일자 --%>
              <span class="ml-2">
                1시간 전
              </span>
            </div>
          </div>

          <%-- 댓글 좋아요버튼 --%>
          <div class="comment_like">
            <%-- 댓글 좋아요 아이콘, 눌렀을경우 &#x1F497; 안눌렀을경우 &#9825;--%>
            <span>&#x1F497;</span>
            <%-- 댓글 좋아요 갯수 --%>
            <span>2</span>
          </div>
        </div>

        <%-- 댓글내용 --%>
        <div class="comment_content my-3">
          댓글내용 들어갈 곳
        </div>

        
        <div class="d-flex">
          <%-- 대댓글이 있는 경우일경우 보이기 시작 대댓글 없는경우 안보여야 함--%>
          <div class="btn_comment_toggle big_comment_hide mr-3">
            <span><i class="fa-solid fa-chevron-up"></i>&nbsp;댓글 모두 숨기기</span>
          </div>

          <%-- 대댓글갯수  --%>
          <div class="btn_comment_toggle big_comment_show mr-3">
            <span><i class="fa-solid fa-chevron-down"></i>&nbsp;댓글&nbsp;<span>1</span>개 보기</span>
          </div>

          <%-- 대댓글이 있는 경우일경우 보이기 끝 대댓글 없는경우 안보여야 함--%>

          <%-- 댓글쓰기 --%>
          <div class="btn_write_comment">
            댓글쓰기
          </div>
        </div>
      </div>
      



      
      <%--------------------------------------------------- 대댓글 영역 시작 ---------------------------------------%>
      <div class="d-flex flex-column align-items-end">



        <%--------------------------------- 대댓글 쓰기영역 시작 ------------------------------%>
        <div class="big_comment_write_area pl-4">
          <div class="login_user_profile_img_box">
            <%-- 로그인되어있는 유저 프로필 이미지 --%>
            <img src="<%=ctxPath %>/resources/images/user.PNG"/>
          </div>
    
          <div class="ml-3 w-100">
            <div class="mb-1">내용</div>
            <textarea name="write_comment2" id="write_comment2" class="pl-2 py-2" rows="5"></textarea>
    
            <div class="d-flex justify-content-end mt-2">
              <button class="btn_big_comment_close btn btn-light border rounded mr-3">취소</button>
              <button class="btn_big_comment_write btn btn-info">댓글 쓰기</button>
            </div>
          </div>
        </div>

        <%----------------------------------- 대댓글 쓰기영역 끝 -----------------------------------%>




      <%----------------------------------- 대댓글리스트(반복문) 시작 -----------------------------------%>
        <div class="big_comment_area pl-4 pt-4">
          <%-- 대댓글작성자의 프로필이미지, 활동점수, 댓글작성일자 --%>
          <div class="big_comment_writer_info d-flex justify-content-between align-items-center">
            <div class="big_comment_writer_profile_img_box mr-3">
              <img src="<%=ctxPath %>/resources/images/user.PNG">
            </div>

            <div class="d-flex flex-column w-100">
              <div class="big_comment_writer_nickname">
                대댓글작성자 닉네임
              </div>
    
              <div class="mt-1">
                <%-- 대댓글작성자 활동점수 --%>
                <span class="mr-2">
                  <i class="fa-solid fa-bolt"></i>
                  <span>0</span>
                </span>

                <span>·</span>
                <%-- 대댓글작성일자 --%>
                <span class="ml-2">
                  1시간 전
                </span>
              </div>
            </div>

            <%-- 대댓글 좋아요버튼 --%>
            <div class="big_comment_like">
              <%-- 댓글 좋아요 아이콘, 눌렀을경우 &#x1F497; 안눌렀을경우 &#9825;--%>
              <span>&#x1F497;</span>
              <%-- 댓글 좋아요 갯수 --%>
              <span>2</span>
            </div>
          </div>

          <%-- 대댓글내용 --%>
          <div class="comment_content my-3">
            대댓글 내용 들어갈 곳
          </div>

          
          <div class="d-flex">
            <%-- 대댓글쓰기 --%>
            <div class="btn_write_big_comment">
              댓글쓰기
            </div>
          </div>
        </div>
      <%----------------------------------- 대댓글리스트(반복문) 끝 -----------------------------------%>

      </div>
      <%----------------------------------------- 대댓글 영역 끝 ------------------------------------------%>

      

    </div>
    
  </div>
  <%--------------------댓글 반복문 끝 --------------------%>






