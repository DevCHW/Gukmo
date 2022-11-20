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
							    	<a href="<%=ctxPath %>/admin/memberManage_List.do">일반회원 관리</a>
							    	<a href="<%=ctxPath %>/admin/academyManage_List.do">학원회원 관리</a>
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
                    <c:if test="${not empty sessionScope.loginuser}">
                    	<div class="login">
							<i class="fa-regular fa-bookmark fa-lg"></i>
							<i class="fa-solid fa-bell fa-lg"></i>
		                	<a href="#" class="profile_image_box border">
						          <img src=""/>
						    </a>
                		</div>
                		
  
                	</c:if>
                </div>
            </nav>
        </nav>
        
    </haeder>    