<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 직접 만든 CSS -->
<link rel="stylesheet"
	href="<%=ctxPath%>/resources/css/hasol/header.css">

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath%>/resources/js/hasol/header.js"></script>

<!-- 네비게이션 시작 -->
<nav bar class="mainNav_bar w-100">
	<nav class="navbar navbar-expand-lg bg-white mainNav w-100">

		<!-- 로고 및 메뉴 영역 -->
		<div class="main_left">

			<!-- Brand/logo -->
			<a
				class="navbar-brand d-flex justify-content-start align-items-center"
				href="<%=ctxPath%>/index.do" style="width: 100px; font-size:22px; font-weight:bold; color:#14587D;"> <img src="<%=ctxPath %>/resources/images/logo.png"
				style="width: 35px;" class="rounded mr-2">GUKMO
			</a>

			<!-- Links -->
			<nav>
				<ul class="mainCate">
					<li><a class="nav-link" href="<%=ctxPath%>/academy/academies.do">국비학원</a></li>
					<li><a class="nav-link"
						href="<%=ctxPath%>/community/freeBoards.do">커뮤니티</a></li>
					<li><a class="nav-link" href="<%=ctxPath%>/notices.do">공지사항</a></li>

					<!-- 관리자로 로그인 했을 경우 추가 메뉴 -->
					<c:if test="${sessionScope.user.authority eq '관리자'}">
						<div class="dropdown">
							<div class="adminMenu">
								<a class="nav-link adminMenu" onclick="">관리자 메뉴</a>
							</div>
							<div id="admin_dropContent" class="dropdown-content2 mt-2">
								<a href="<%=ctxPath%>/admin/index.do">관리자메인페이지</a> 
								<a href="<%=ctxPath %>/admin/member/normal/list.do">일반회원 관리</a> 
								<a href="<%=ctxPath %>/admin/member/academy/list.do">학원회원 관리</a>
								<a href="<%=ctxPath %>/admin/advertisement/list.do">광고현황 관리</a>
								<a href="<%=ctxPath %>/admin/report/list.do">신고현황 관리</a>
							</div>
						</div>
					</c:if>
				</ul>
			</nav>
		</div>


		<!-- login 메뉴 영역 -->
		<div class="main_right">

			<!-- 비로그인 시 -->
			<c:if test="${empty sessionScope.user}">
				<div class="non-login">
					<button type="button" class="btn_login" id="login"
						onclick="location.href='<%=ctxPath%>/login.do'">로그인</button>
					<button type="button" class="btn_regist" id="regist"
						onclick="location.href='<%=ctxPath%>/signup.do'">회원가입</button>
				</div>
			</c:if>

			<!-- 로그인 시 -->
			<c:if test="${not empty sessionScope.user}">
				<div class="login d-flex justify-content-between align-items-center">

					<!-- 북마크(스크랩) -->
					<a class="login_icon"> <i class="fa-regular fa-bookmark fa-lg"></i>
					</a>

					<!-- 알림 -->
					<jsp:include page="/WEB-INF/views/tiles1/board/alarm/alarm.jsp" />
					<!-- 프로필 drop -->
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
								</a> <a href="<%=ctxPath%>/logout.do ">로그아웃</a>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</nav>
</nav>