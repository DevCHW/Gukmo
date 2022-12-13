<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 검색바  CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/assembly/searchBar.css" />

<script type="text/javascript">

//js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

//field declaration
let status_click_cnt = 0; //회원상태옵션 클릭횟수(열고닫기위한용도)
let btn_search_menu = 0;  //검색옵션 클릭횟수(열고닫기위한용도)

$(document).ready(function(){
	let url = window.document.location.href;
	url = url.substr(27);
	let questionmarkIdx = url.indexOf("?");
	if(questionmarkIdx != -1){
	  url = url.substr(0,questionmarkIdx);
	}
    let html = "";
    switch (url) {
	    case "/admin/member/normal/list.do" :	//일반회원리스트일경우 검색옵션 넣기
	    	html = "<div class='my-2 text-center'>아이디</div>"+
			       "<div class='my-2 text-center'>닉네임</div>"+
			       "<div class='my-2 text-center'>학원명</div>";
	       	break;
	    case "/admin/member/academy/list.do" :	//교육기관회원 리스트일 경우 검색옵션 넣기
	    	html = "<div class='my-2 text-center'>아이디</div>"+
		           "<div class='my-2 text-center'>닉네임</div>"+
		           "<div class='my-2 text-center'>학원명</div>";
	      break;
	    case "/admin/advertisement/list.do" :	//광고리스트일 경우 검색옵션 넣기
	    	html = "<div class='my-2 text-center'>고객명</div>"+
	    		   "<div class='my-2 text-center'>고객번호</div>";
	      break;
	    case "/admin/report/list.do" :	//신고리스트일 경우 검색옵션 넣기
	        html = "<div class='my-2 text-center'>신고자</div>"+
	    		   "<div class='my-2 text-center'>신고자닉네임</div>"+
	    		   "<div class='my-2 text-center'>피신고자닉네임</div>";
	    break;
    }//end of switch-case--
    $("div#search_option").html(html);	//검색옵션 넣기
	
	
	//검색옵션 클릭시(열고닫기)
	$("div#btn_search_menu").click(function(){
	  btn_search_menu++;  //클릭횟수 증가
	  if(btn_search_menu%2==0){ //짝수번 클릭시
	    $("div#search_option").addClass("hidden");
	  } else{ //홀수번 클릭시
	    $("div#search_option").removeClass("hidden");
	  }
	});//end of Event--
	
	
	//광고 상태 선택옵션 선택버튼 클릭시 이벤트 (열고닫기)
	$("span#btn_status_option").click(function(){
	  status_click_cnt++; //클릭횟수 증가
	  if(status_click_cnt%2==0){  //짝수번 클릭시
	    $("div#advertisementStatusOption").addClass("hidden");
	  } else{ //홀수번 클릭시
	    $("div#advertisementStatusOption").removeClass("hidden");
	  }
	});//end of Event--
	
	
	
	//구분옵션 선택버튼 클릭시 이벤트 (열고닫기)
	$("span#btn_division_option").click(function(){
	  status_click_cnt++; //클릭횟수 증가
	  if(status_click_cnt%2==0){  //짝수번 클릭시
	    $("div#divisionOption").addClass("hidden");
	  } else{ //홀수번 클릭시
	    $("div#divisionOption").removeClass("hidden");
	  }
	});//end of Event--
	
	
	//검색옵션 하나 클릭시 이벤트(해당글자 굵게 하기),input hidden에 값 넣기
	$("div#search_option > div").click(function(e){
	  const target = $(e.currentTarget);
	  $("div#search_option > div").css("font-weight","");
	  target.css("font-weight","bold");
	  $("input#searchType").val(target.text());
	});//end of Event--
	
	
	//회원 상태옵션 하나 클릭시 이벤트(해당글자 굵게하기),input hidden에 값 넣기
	$("div#memberStatusOption > div").click(function(e){
	  const target = $(e.currentTarget);
	  $("div#memberStatusOption > div").css("font-weight","");
	  target.css("font-weight","bold");
	});//end of Event--
	
	
	//광고 상태옵션 하나 클릭시 이벤트(해당글자 굵게하기,),input hidden에 값 넣기
	$("div#advertisementStatusOption > div").click(function(){
	  const target = $(e.currentTarget);
	  $("div#advertisementStatusOption > div").css("font-weight","");
	  target.css("font-weight","bold");
	  
	});//end of Event--
	
	
	//광고 상태옵션 하나 클릭시 이벤트(해당글자 굵게하기,),input hidden에 값 넣기
	$("div#divisionOption > div").click(function(){
	  const target = $(e.currentTarget);
	  $("div#divisionOption > div").css("font-weight","");
	  target.css("font-weight","bold");
	  $("input#division").val(target.text())
	});//end of Event--
	
	
	
	//검색창에서 엔터 입력시 이벤트(검색버튼 클릭시키기)
    $("input#searchWord").keyup(function(e){
  	  if(e.keyCode == 13) {	//엔터를 쳤다면
  	    $("button#btn_search").trigger("click");	//검색버튼 클릭
  	  }
    });//end of Event--
    
    
    
    //검색버튼 클릭시 해당 페이지url로 값 전송
    $("button#btn_search").click(function(e){
   	  const frm = document.searchFrm;
	  frm.method = "GET";
	  
      if(url == '/admin/member/normal/list.do'){ //현재 페이지가 일반회원리스트일경우
        frm.action = getContextPath()+"/admin/member/normal/list.do";
      } else if(url == '/admin/member/academy/list.do'){ //현재 페이지가 교육기관회원리스트일경우
    	frm.action = getContextPath()+"/admin/member/academy/list.do";	
      } else if(url == '/admin/advertisement/list.do'){	//현재 페이지가 광고리스트일경우
    	frm.action = getContextPath()+"/admin/advertisement/list.do";
      }
      
      frm.submit();
    });//end of Event--
	
});//end of $(document).ready(function(){})--

</script>



<!-- 검색창 시작 -->
  <!-- 검색버튼 클릭시 전송할 값들 -->
  <form name="searchFrm">
    <div class="d-flex flex-column">
      <div class="searchBar d-flex justify-content-center">
        <div id="search_menu_box" class="d-flex justify-content-center align-items-center">
          <div id="btn_search_menu">
            <i class="fa-sharp fa-solid fa-bars"></i>
          </div>
          <!-- 검색옵션 시작(페이지에 따라서 자바스크립트로 넣음)-->
          <div id="search_option" class="hidden border px-2 py-2">
          </div>
          <!-- 검색옵션 끝 -->
        </div>
        
        <input type="text" id="searchWord" name="searchWord" class="pl-3" value="${requestScope.paraMap.searchWord}" placeholder="검색어를 입력해주세요"></input>
        <button type="button" id="btn_search">
          <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
        </button>
      </div>
    </div>
  	
  	<%-- <input type="hidden" id="division" name="division" value="${requestScope.division}"> --%>
  	<input type="hidden" id="searchType" name="searchType" value="${requestScope.searchType}">
  	<input type="hidden" id="status" name="status" value="${requestScope.status}">
  	<input type="hidden" id="sort" name="sort" value="${requestScope.sort}">
  </form>
<!-- 검색창 끝 -->