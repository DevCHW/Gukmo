<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath %>/resources/css/header.css">

<!-- 상단 네비게이션 시작 -->
	<haeder>
        <nav bar class="mainNav_bar">
            <nav class="navbar navbar-expand-sm bg-white mainNav" >
                <!-- Brand/logo -->
                <div class="main_left">
                    <a class="my navbar-brand" href="#" style="width:20%; ">
                        <img src="" alt="logo">
                    </a>

                    <!-- Links -->
                    <nav style="width:70%;">
                        <ul class="my mainCate">
                            <li>
                                <a class="nav-link" href="#">국비학원</a>
                            </li>
                            <li>
                                <a class="nav-link" href="#">커뮤니티</a>
                            </li>
                            <li>
                                <a class="nav-link" href="#">공지사항</a>
                            </li>
                        </ul>
                    </nav>
                </div>
                
                <!-- login -->
                <div class="main_right">
                    <c:if test="${empty sessionScope.loginuser}">
	                    <div class="non-login">
	                        <button type="button" class="btn_login" id="login" style="background-color: white;">로그인</button>
	                        <button type="button" class="btn_regist" id="regist" style="background-color: #208EC9; color:white;">회원가입</button>
	                    </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.loginuser}">
                    	<div class="login">
							<i class="fa-regular fa-bookmark fa-lg"></i>
							<i class="fa-solid fa-bell fa-lg"></i>
		                	<a href="#">
		                		<img src="" alt="profile">
		                	</a>
                		</div>
                	</c:if>
                	<!-- 여긴 학원 계정인데, 학원 계정 체크 어떻게 했는지 값 몰라서 일단 주석 처리함 -->
                	<!--  
                	<%-- <c:if test="${sessionScope.loginuser==}"> --%>
                    	<div class="login_academy">
                    		<span>으로 로그인 </span>
							<i class="fa-regular fa-bookmark fa-lg"></i>
							<i class="fa-solid fa-bell fa-lg"></i>
		                	<a href="#">
		                		<img src="" alt="profile">
		                	</a>
                		</div>
                	<%-- </c:if> --%>
                	-->
                    
                </div>
                

            </nav>
        </nav>
    </haeder>    