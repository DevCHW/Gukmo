<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath %>/resources/css/hasol/header.css">

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hasol/header.js" ></script>

<!-- 상단 네비게이션 시작 -->
	<haeder>
        <nav bar class="mainNav_bar ">
            <nav class="navbar navbar-expand-lg bg-white mainNav" >
                <!-- Brand/logo -->
                <div class="main_left" >
                    <!-- 이미지 가운데 정렬부터 시작 --><a class="my navbar-brand d-flex justify-content-center" href="#" style="width:20%; ">
                        <img src="" alt="logo" >
                    </a>

                    <!-- Links -->
                    <nav style="width:70%;">
                        <ul class="my mainCate">
                            <li>
                                <a class="nav-link" href="#">국비학원</a>
                            </li>
                            <li>
                                <a class="nav-link" href="<%=ctxPath %>/community/freeBoards.do">커뮤니티</a>
                            </li>
                            <li>
                                <a class="nav-link" href="#">공지사항</a>
                            </li>
		                    <c:if test="${sessionScope.user.userid eq 'admin'}">
	                        <div class="dropdown">
	                        	<div class="adminMenu" >
	                           		<a class="nav-link" onclick="drop_content3()">관리자 메뉴</a>	                                									
							  	</div>
							  	<div id="myDropdown3" class="dropdown-content2 mt-2">
							    	<a href="<%=ctxPath %>/admin/memberManage_List.do">g 메뉴</a>
							    	<a href="#" id="admin_link">관리자 메뉴</a>
							  	</div>
							</div>	
	                    	
		                    </c:if>
                        </ul>
                    </nav>
                </div>
                
                <!-- login -->
                <div class="main_right">
                    <c:if test="${empty sessionScope.user}">
	                    <div class="non-login">
	                        <button type="button" class="btn_login" id="login" onclick="location.href='<%=ctxPath %>/login.do'">로그인</button>
	                        <button type="button" class="btn_regist" id="regist" onclick="location.href='<%=ctxPath %>/signup.do'">회원가입</button>
	                    </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.user}">
                    	<div class="login d-flex align-items-center">
							<a class="my_icon">
								<i class="fa-regular fa-bookmark fa-lg"></i>
							</a>

							<div class="dropdown">
								<a class="my_icon"><i class="fa-solid fa-bell fa-lg" class="my_icon" onclick="drop_content1()"></i></a>
							  	<div id="myDropdown1" class="dropdown-content1 mt-1">
							  		<a href="#">알림</a>
<%-- 								    <c:if test="${empty sessionScope.userAlram}">
								    	<p> 받으신 알림이 없습니다. </p>
								    </c:if> --%>
<%-- 								    <c:if test="${not empty sessionScope.userAlram}"> --%>
							  		<div class="d-flex justify-content-center align-items-center">
								    	<p> 받으신 알림이 없습니다. </p>
<%-- 								    </c:if> --%>
							    	</div>
							  </div>
							</div>
							
              				<div class="dropdown">
              				  <div class="dropbtn"> 
								  <p class="dropbtn">
								  	<img src="<%= ctxPath%>/resources/images/user.PNG" class="dropbtn" onclick="drop_content2()">
								  </p>
							  </div>
							  <div id="myDropdown2" class="dropdown-content2">
							  	<div class="px-1 py-1">
								    <a href="<%= ctxPath%>/member/myId.do">
								  		<i class="fa-solid fa-user"></i>  
								  		내 계정
								  	</a>
								    <a href="<%= ctxPath%>/member/myInfo.do">
								    	<i class="fa-solid fa-gear"></i>
								    	내 정보
								    </a>
								    <a href="<%= ctxPath%> /member/activities.do">
								    	<i class="fa-solid fa-gear"></i>
								    	활동내역
								    </a>
								    <a href="<%= ctxPath%>/logout.do ">로그아웃</a>
							    </div>
							  </div>
							</div>
                		</div>
                		
  
                	</c:if>
                </div>
            </nav>
        </nav>
        
    </haeder>    