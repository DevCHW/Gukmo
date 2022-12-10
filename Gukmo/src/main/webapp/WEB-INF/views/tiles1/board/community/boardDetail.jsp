<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
   String ctxPath = request.getContextPath();
%>

  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/gwangbin/boardDetail.css" />
  
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/gwangbin/boardDetail.js" ></script>






  <div class="container my-5"> 

    <div class="line my-4">
      <div>${requestScope.board.category}&nbsp;</div><span>/</span><div>&nbsp;${requestScope.board.detail_category}</div>   <%-- ${requestScope.board.detail_category} --%>
    </div>



    <%-- 글 상세보기 페이지 머리부분(작성자 프로필이미지, 작성자 닉네임, 활동점수, 작성일자, 조회수, 신고버튼, 수정,삭제버튼) --%>
    <div id="detail_header" class="d-flex justify-content-between align-items-center">

      <div id="writer_profile_image_box">
        <%-- 글 작성자 프로필이미지 --%>
        <c:if test="${requestScope.board.profile_image.substring(0,4) != 'http'}">
              <img src="<%=ctxPath %>/resources/images/${requestScope.board.profile_image}">
            </c:if>
            <c:if test="${requestScope.board.profile_image.substring(0,4) == 'http'}">
              <img src="${requestScope.board.profile_image}">
            </c:if>
      </div>


      <div id="writer_profile_body" class="d-flex flex-column w-100 px-2 py-1">
        <%-- 작성자 닉네임 들어가면 해당 유저의 활동내역을 볼수 있는 페이지로 이동--%>
        <div id="board_writer_nickname" class="pl-2">${requestScope.board.nickname}</div>

        <%-- 활동점수,작성일자,조회수 영역--%>
        <div class="d-flex">
          <%-- 활동점수 --%>
          
          <div id="board_writer_point" class="mx-2">
            <i class="fa-solid fa-bolt"></i>
            <c:if test="${empty requestScope.board.writer_point}">
              <span>0</span>
            </c:if>
            <c:if test="${not empty requestScope.board.writer_point}">
              <span>${requestScope.board.writer_point}</span>
            </c:if>
          </div>

          <span>·</span>

          <%-- 작성일자(형식은 약 ?분전, ?시간전, ?일전, ?년전) --%>
          <div id="board_write_date" class="mx-2">
            ${requestScope.board.write_date}
          </div>

          <span>·</span>

          <%-- 조회수 --%>
          <div id="board_views" class="mx-2">
            <i class="fa-solid fa-eye"></i>
            <span>${requestScope.board.views}</span>
          </div>
        </div>
      </div>
      <%-- 신고버튼, 수정or삭제버튼 --%>
      <div id="report_edit_delete_area" class="d-flex justify-content-between align-items-center">
        <c:if test="${sessionScope.user.nickname != requestScope.board.nickname}">
          <span id="btn_report" class="ml-auto" onclick="openReport()">&#x1F6A8;</span>
        </c:if>
        <c:if test="${sessionScope.user.nickname == requestScope.board.nickname}">
          <span id="btn_report" onclick="openReport()">&#x1F6A8;</span>
        </c:if>
        <div id="mask"></div>
        <c:if test="${sessionScope.user.nickname == requestScope.board.nickname}">
          <span id="btn_more" class="border rounded px-2 py-1">&#8230;
            <div id="update_or_delete" class="border rounded px-3 py-2">
              <span onclick="location.href='<%=ctxPath %>/community/modify.do?boardNum=${board.board_num}'">수정하기</span>
              <span id="board_delete" onclick="location.href='<%=ctxPath %>/community/del.do?boardNum=${board.board_num}'">삭제하기</span>
            </div>
          </span>
        </c:if>
       
        
        
      </div>
    </div>
    <%-- 글 상세보기 페이지 머리부분 --%>



    <%-------------------- 글 본문 시작 ------------------%>
    <div id="content_area" class="d-flex flex-column py-2">
      <div id="subject" class="mt-3">
        <h2>${requestScope.board.subject}</h2>
      </div>


     <%-- 글내용 --%>
      <div id="content" class="mt-3">${requestScope.board.content} </div>

      <div class="d-flex justify-content-between mt-4">
         <div>
             <%-- 해시태그리스트 반복문시작 --%>
              <c:forEach var="hashtag" items="${board.hashtags}">
                <a href="#" class="hashtag mx-1">#<span>${hashtag.hashtag}</span></a>
              </c:forEach>
              <%-- 해시태그리스트 반복문 끝--%>
         </div>
            
            
             <%-- 좋아요 아이콘, 눌렀을경우 &#x1F497; 안눌렀을경우 &#9825;--%>
             <div class="ml-auto">
             <c:if test="${empty sessionScope.user || like == null}">
                 <div type="button" id="btn_like">
                   <span id="like_icon">&#9825;</span>
                   <span id="like_cnt">${board.like_cnt}</span>
                 </div>
             </c:if>
             
             <c:if test="${not empty sessionScope.user && like == 1}">
                 <div type="button" id="btn_like">
                   <span id="like_icon">&#x1F497;</span>
                   <span id="like_cnt">${board.like_cnt}</span>
                 </div>
             </c:if>
             </div>
                
          
        
            <input id="userid"  type="hidden" name="userid" value="${sessionScope.user.userid}"  />      
            <input id="board_num"  type="hidden" name="board_num" value="${board.board_num}"  />      
            <input id="nickname"  type="hidden" name="nickname" value="${sessionScope.user.nickname}" />
            <input id="board_subject"  type="hidden" name="board_subject" value="${board.subject}" />
            <input id="detail_category"  type="hidden" name="detail_category" value="${requestScope.board.detail_category}" />
        
        
        
      </div>
    </div>
    <%-------------------- 글 본문 끝 ------------------%>

    <%---------------------- 이전글,다음글 영역 시작 ----------------------%>
    <div id="previous_next_area" class="px-3 d-flex justify-content-center align-items-center rounded mt-4">
      <div class="w-100">
      
        <c:if test="${not empty requestScope.board.previousseq}">
        <%-- 이전글 a태그 href에 ?num=이전글번호--%>
        <div id="previous" class="my-2">
          <span>이전글 |</span>
          <a href="detail.do?boardNum=${requestScope.board.previousseq}">${requestScope.board.previoussubject}</a>
        </div>
        </c:if>
        
        <c:if test="${empty requestScope.board.previousseq}">
        <%-- 이전글 a태그 href에 ?num=이전글번호--%>
        <div id="previous" class="my-2">
          <span>이전글 |</span>
          <a id="no_previous">이전글이 없습니다</a>
        </div>
        </c:if>
  
        <c:if test="${not empty requestScope.board.nextseq}">
        <%-- 다음글 a태그 href에 ?num=다음글번호--%>
        <div id="next" class="my-2">
          <span>다음글 |</span>
          <a href="detail.do?boardNum=${requestScope.board.nextseq}">${requestScope.board.nextsubject}</a>
        </div>
        </c:if>
        
        <c:if test="${empty requestScope.board.nextseq}">
        <%-- 다음글 a태그 href에 ?num=다음글번호--%>
        <div id="next" class="my-2">
          <span>다음글 |</span>
          <a id="no_next">다음글이 없습니다</a>
        </div>
        </c:if>
        
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
      <span id="total_comment">${board.comment_cnt}</span><span>개의 댓글</span>
    </div>

    <form name="addWriteFrm" id="addWriteFrm">
    <div id="write_comment_area" class="border rounded px-4 py-4">
      <div class="d-flex w-100">
        <div class="login_user_profile_img_box">
          <%-- 로그인되어있는 유저 프로필 이미지 --%>
          <img src="<%=ctxPath %>/resources/images/user.PNG"/>
        </div>
            <input id="userid" type="hidden" name="userid" value="${sessionScope.user.userid}"  />		
            <input id="cmt_board_num"  type="hidden" name="cmt_board_num" value="${requestScope.board.board_num}"  />		
            <input id="nickname"  type="hidden" name="nickname" value="${sessionScope.user.nickname}"  />		
            <input id="parent_write_nickname" type="hidden" name="parent_write_nickname" value="${requestScope.board.nickname}"  />            
        <div class="ml-3 w-100">
          <div class="mb-1">내용</div>
          <textarea id="content" name="content" class="pl-2 py-2" rows="5"></textarea>
  
          <div class="d-flex justify-content-end mt-2">
            <button type="button" class="btn btn-info" id="go_comment" onclick="goAddComment()">댓글 쓰기</button>
          </div>
        </div>
     
      </div>
    </div>
    </form>   
    <%---------------------- 댓글쓰기 영역 끝 ----------------------%>





    <%---------------------- 댓글리스트 영역(반복문) 시작 ----------------------%>

    <%-- 댓글 반복문 시작 --%>
    <c:forEach var="bcommentList" items="${requestScope.basic_commentList}" varStatus="status">
    <div class="comment_area pb-4">
      <div class="comment px-3 py-4" id="dd">
        <%-- 댓글작성자의 프로필이미지, 활동점수, 댓글작성일자 --%>
        <div class="d-flex justify-content-between align-items-center comment_writer_info">
          <div class="comment_writer_profile_img_box mr-2">
            <img src="<%=ctxPath %>/resources/images/user.PNG">
          </div>
          <input type="hidden" id="" name="fk_comment_num"/>
          <div class="d-flex flex-column w-100 asdf1">
            <div class="comment_writer_nickname" id ="${bcommentList.comment_num}">
              	${bcommentList.nickname}
            </div>
  			
            <div class="mt-1">
              <%-- 댓글작성자 활동점수 --%>
              <span class="mr-2">
                <i class="fa-solid fa-bolt"></i>
                <span>${bcommentList.point}</span>
              </span>

              <span>·</span>
              <%-- 댓글작성일자 --%>
              <span class="ml-2">
                ${bcommentList.write_date}
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
          <%-- 댓글 신고,수정,삭제 시작 --%>
          <div id="" class="d-flex justify-content-between align-items-center comment_edit_delete_area">
	        <span class="comment_btn_report">&#x1F6A8;</span>
	        <div class="comment_mask"></div>
	        <span id="" class="border rounded px-2 py-1 comment_btn_more">&#8230;
          		<div id="" class="border rounded px-3 py-2 comment_update_or_delete">
	            	<span class="comment_edit">수정하기</span>
	            	<span class="comment_delete">삭제하기</span>
          		</div>
          	</span>
      	   </div>
      	   <%-- 댓글 신고,수정,삭제 끝 --%>
        </div>

        <%-- 수정할 댓글 내용 --%>
        <div class="my-3 basic_comment_content" id="${bcommentList.content}">
			<div class = "detail_comment" >${bcommentList.content}</div>
 	        <div class="ml-3 w-100 comment_edit" id="">
	          <textarea class="content3" class="pl-2 py-2 content3" rows="5"></textarea>  
	          <input type="hidden" id="c_num" class="c_num" name="c_num" />
	          <div class="d-flex justify-content-end mt-2">
	            <button type="button" class="btn btn-info edit_comment">댓글 수정</button>
	          </div>
	        </div> 
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
          <c:if test="${sessionScope.user.nickname != null}">
          <div class="btn_write_comment">
            댓글쓰기
          </div>
          </c:if>
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
            <div class="mb-1">${sessionScope.user.nickname}</div>
    		<textarea id="" class="pl-2 py-2 content2" name="content2" rows="5"></textarea>   		
            <div class="d-flex justify-content-end mt-2" id = "asdf1">
              <button type="button" class="btn_big_comment_close btn btn-light border rounded mr-3">취소</button>
              <button type="button" class="btn_big_comment_write btn btn-info">댓글 쓰기</button>
              <input type="hidden" class="fk_com_num" value="${bcommentList.comment_num}" />
            </div>
          </div>
          
        </div>

        <%----------------------------------- 대댓글 쓰기영역 끝 -----------------------------------%>


      <%----------------------------------- 대댓글리스트(반복문) 시작 -----------------------------------%>     
      <c:forEach var="spcial_commentList" items="${requestScope.special_commentList}">
		<c:if test="${bcommentList.comment_num == spcial_commentList.fk_comment_num}">
	        <div class="big_comment_area pl-4 pt-4" id="">
	          <%-- 대댓글작성자의 프로필이미지, 활동점수, 댓글작성일자 --%>
	          <div class="big_comment_writer_info d-flex justify-content-between align-items-center">
	            <div class="big_comment_writer_profile_img_box mr-3">
	              <img src="<%=ctxPath %>/resources/images/user.PNG">
	            </div>
	 
	            <div class="d-flex flex-column w-100">
	              <div class="big_comment_writer_nickname">
              		${spcial_commentList.nickname}
	              </div>	    
	              <div class="mt-1">
	                <%-- 대댓글작성자 활동점수 --%>
	                <span class="mr-2">
	                  <i class="fa-solid fa-bolt"></i>
	                  <span>${spcial_commentList.point}</span>
	                </span>
	
	                <span>·</span>
	                <%-- 대댓글작성일자 --%>
	                <span class="ml-2">
              			${spcial_commentList.write_date}
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
	            <%-- 대댓글 신고,수정,삭제 시작 --%>
	          <div id="" class="d-flex justify-content-between align-items-center comment_edit_delete_area">
		        <span class="comment_btn_report">&#x1F6A8;</span>
		        <div class="comment_mask"></div>
		        <span id="" class="border rounded px-2 py-1 comment_btn_more">&#8230;
	          		<div id="" class="border rounded px-3 py-2 comment_update_or_delete">
			            <input id="comment_of_comment_nickname" type="hidden" value="${spcial_commentList.nickname}"  />      
			            <input id="comment_of_comment_num"  type="hidden" value="${spcial_commentList.comment_num}" />
		            	<span class="comment_edit2">수정하기</span>
		            	<span class="comment_delete2">삭제하기</span>
	          		</div>
	          	</span>
	      	   </div>
      	   <%-- 댓글 신고,수정,삭제 끝 --%>
	          </div>
	
	
	
	
		          <%-- 대댓글내용 --%>
		       <div class="my-3 special_comment_content" id="${spcial_commentList.content}">
		          <div class="detail_comment_of_comment my-3" id="">
	              	${spcial_commentList.content}
		          </div>
		          <div class="ml-3 w-100 c_of_comment_edit" id="">
			          <textarea class="content4" class="pl-2 py-2 content4" rows="3"></textarea>  
			          <input type="hidden" id="c_of_c_num" class="" name="" />
			          <div class="d-flex justify-content-end mt-2">
			          	<button type="button" class="btn btn-info edit_comment_of_comment">댓글 수정</button>
			          </div>
			      </div> 
	           </div>
	          
	        </div>
	        
        </c:if>    
       </c:forEach>
      <%----------------------------------- 대댓글리스트(반복문) 끝 -----------------------------------%>

      </div>
      <%----------------------------------------- 대댓글 영역 끝 ------------------------------------------%>

      

    </div>
    </c:forEach>
    <%--------------------댓글 반복문 끝 --------------------%>
    
  </div>
   
  






