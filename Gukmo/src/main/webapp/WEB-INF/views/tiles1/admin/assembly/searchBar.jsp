<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 검색바  CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/assembly/searchBar.css" />

<script type="text/javascript">
//검색옵션 클릭시
$("div#btn_search_menu").click(function(){
  btn_search_menu++;  //클릭횟수 증가
  if(btn_search_menu%2==0){ //짝수번 클릭시
    $("div#search_option").addClass("hidden");
  } else{ //홀수번 클릭시
    $("div#search_option").removeClass("hidden");
  }
});

//검색옵션 하나 클릭시 이벤트(해당글자 굵게 하기)
$("div#search_option > div").click(function(e){
  const target = $(e.currentTarget);
  $("div#search_option > div").css("font-weight","");
  target.css("font-weight","bold");
});


//회원 상태옵션 선택버튼 클릭시
$("span#btn_status_option").click(function(){
  status_click_cnt++; //클릭횟수 증가
  if(status_click_cnt%2==0){  //짝수번 클릭시
    $("div#memberStatusOption").addClass("hidden");
  } else{ //홀수번 클릭시
    $("div#memberStatusOption").removeClass("hidden");
  }
});

//회원 상태 하나 클릭시 이벤트(해당글자 굵게하기)
$("div#memberStatusOption > div").click(e=>{
  const target = $(e.currentTarget);
  $("div#memberStatusOption > div").css("font-weight","");
  target.css("font-weight","bold");
});

</script>

<!-- 검색창 시작 -->
  <div class="d-flex flex-column">
    <div class="searchBar d-flex justify-content-center">
      <div id="search_menu_box" class="d-flex justify-content-center align-items-center">
        <div id="btn_search_menu">
          <i class="fa-sharp fa-solid fa-bars"></i>
        </div>
        <!-- 검색옵션 시작-->
        <div id="search_option" class="hidden border px-2 py-2">
          <div class="my-1">아이디</div>
          <div class="my-1">닉네임</div>
          <div class="my-1">학원명</div>
        </div>
        <!-- 검색옵션 끝 -->
      </div>
      
      <input type="text" id="searchWord" class="pl-3" value="${requestScope.searchWord}" placeholder="검색어를 입력해주세요"></input>
      <button type="button" id="btn_search">
        <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
      </button>
    </div>
  </div>
<!-- 검색창 끝 -->