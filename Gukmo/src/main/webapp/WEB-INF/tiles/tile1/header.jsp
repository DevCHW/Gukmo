<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet"
	href="<%=ctxPath%>/resources/css/hasol/header.css">

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath%>/resources/js/hasol/header.js"></script>
<script type="text/javascript">
	//로그인또는 로그아웃페이지로 가기 전 URL 구하기
	function getReturnUrl(){
	  let hostIndex = location.href.indexOf(location.host) + location.host.length;
	  let returnUrl = location.href.substring(location.href.indexOf('/',hostIndex+1));
	  return returnUrl;
	}
	//로그인페이지로 가는 메소드
	function goLoginPage(){
		let returnUrl = getReturnUrl();
		
		location.href="<%=ctxPath%>/login.do?returnUrl="+encodeURIComponent(returnUrl);
	}
	//로그아웃하기
	function goLogoutPage(){
		let returnUrl = getReturnUrl();
		
		location.href="<%=ctxPath%>/logout.do?returnUrl="+encodeURIComponent(returnUrl);
	}
</script>
<%-- 네비게이션 시작 --%>
<nav bar class="mainNav_bar w-100">
	<%-- 슬라이드 메뉴 시작 --%>
	<div id="slide_mask"></div>
     <div id="slide_menu">
       <div id="menu_close"><i id="btn_menu_close" class="fas fa-light fa-x"></i></div>
       <ul id="menu_list">
         <li class="border-bottom"><a href="<%=ctxPath%>/academy/academies.do">국비학원</a></li>
         <li class="border-bottom"><a href="<%=ctxPath%>/community/freeBoards.do">커뮤니티</a></li>
         <li class="border-bottom"><a href="<%=ctxPath%>/notices.do">공지사항</a></li>
         <c:if test="${sessionScope.user.authority eq '관리자'}">
         <li class="border-bottom"><a href="<%=ctxPath%>/admin/index.do">국모 관리</a></li>
         </c:if>
         <c:if test="${not empty sessionScope.user}">
         <li class="border-bottom"><a href="<%=ctxPath%>/member/myId.do">내 계정</a></li>
         <li class="border-bottom"><a href="<%=ctxPath %>/member/myInfo.do">내 정보</a></li>
         <li class="border-bottom"><a href="<%=ctxPath%>/member/activities.do">활동내역</a></li>
         </c:if>
         <c:if test="${empty sessionScope.user}">
         <li class="border-bottom"><a href="#" onclick="goLoginPage();">로그인</a></li>
         </c:if>
         <c:if test="${not empty sessionScope.user}">
         <li class="border-bottom"><a href="#" style="color:red;" onclick="goLogoutPage()">로그아웃</a></li>
         </c:if>
       </ul>
     </div>
     <%--슬라이드 메뉴 끝 --%>
     
     
	<nav class="navbar navbar-expand-lg bg-white mainNav w-100">

		<%-- 로고 및 메뉴 영역 --%>
		<div class="main_left">

			<%-- Brand/logo --%>
			<a class="navbar-brand"
			   href="<%=ctxPath%>/index.do" style="margin-right:52px;"> 
			   <img src="<%=ctxPath %>/resources/images/mainLogo.png" style="width:150px; height:43.75px;">
			</a>

			<%-- Links --%>
			<nav>
				<ul class="mainCate">
					<li><a class="nav-link" href="<%=ctxPath%>/academy/academies.do">국비학원</a></li>
					<li><a class="nav-link"
						href="<%=ctxPath%>/community/freeBoards.do">커뮤니티</a></li>
					<li><a class="nav-link" href="<%=ctxPath%>/notices.do">공지사항</a></li>

					<%-- 관리자로 로그인 했을 경우 추가 메뉴 --%>
					<c:if test="${sessionScope.user.authority eq '관리자'}">
						<li class="adminMenu">
							<a class="nav-link adminMenu" href="<%=ctxPath%>/admin/index.do">국모 관리</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
		

		<%-- login 메뉴 영역 --%>
		<div class="main_right">
			<%-- 슬라이드 메뉴 열기 버튼 --%>
			<div id="btn_slide_menu_open" class="px-3 py-2 border rounded">
				<i class="fa-solid fa-bars"></i>
			</div>
			
			<%-- 비로그인 시 --%>
			<c:if test="${empty sessionScope.user}">
				<div class="non-login">
					<button type="button" class="btn_login" id="login"
						onclick="goLoginPage();">로그인</button>
					<button type="button" class="btn_regist" id="regist"
						onclick="location.href='<%=ctxPath%>/TOS.do'">회원가입</button>
				</div>
			</c:if>
			
			<%-- 로그인 시 --%>
			<c:if test="${not empty sessionScope.user}">
				<div id="start_login" class="login justify-content-between align-items-center">

					<%-- 북마크(스크랩) --%>
					<a class="login_icon"> <i class="fa-regular fa-bookmark fa-lg"></i>
					</a>

					<%-- 알림 --%>
					<jsp:include page="/WEB-INF/views/tiles1/board/alarm/alarm.jsp" />
					
					<%-- 프로필 drop --%>
					<div class="dropdown">
						<div class="dropbtn">
							<c:if test="${fn:substring(sessionScope.user.profile_image,0,4) != 'http'}">
			                  <img src="<%=ctxPath %>/resources/images/${sessionScope.user.profile_image}" onclick="drop_profile()"/>
			                </c:if>
			                <c:if test="${fn:substring(sessionScope.user.profile_image,0,4) == 'http'}">
			             	   <img src="${sessionScope.user.profile_image}" onclick="drop_profile()"/>
			                </c:if>
						</div>
						<div id="profile_dropContent" class="dropdown-content2">
							<div class="px-1 py-1">
								<a href="<%=ctxPath%>/member/myId.do"> <i
									class="fa-solid fa-user"></i> 내 계정
								</a> <a href="<%=ctxPath%>/member/myInfo.do"> <i
									class="fa-solid fa-gear"></i> 내 정보
								</a> <a href="<%=ctxPath%>/member/activities.do"> <i
									class="fa-solid fa-gear"></i> 활동내역
								</a> <a id="btn_logout" href="#" onclick="goLogoutPage()">로그아웃</a>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</nav>
</nav>


<%-- scrollTop button 시작 --%>
<div id='scroll-to-top'>
    <span class='fa-stack fa-lg'>
        <i class='fa fa-circle fa-stack-2x circle'></i>
        <i class='fa fa-angle-double-up fa-stack-1x fa-inverse up-arrow'></i>
    </span>
</div>
<%-- scrollTop button 끝 --%>