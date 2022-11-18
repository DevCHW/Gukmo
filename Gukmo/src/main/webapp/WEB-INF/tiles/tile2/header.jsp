<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath %>/resources/css/hasol/header.css">

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
                    	<div class="login">
							<a><i class="fa-regular fa-bookmark fa-lg"></i></a>
							<a><i class="fa-solid fa-bell fa-lg"></i></a>
		                	<a href="#" class="profile_image_box border">
						          <img src=""/>
						    </a>
						    <div class="absolute right-0 mt-2 w-40 space-y-2 rounded-lg border border-gray-500/30 bg-white p-3 shadow-lg dark:border-gray-500/70 dark:bg-gray-800 transform opacity-100 scale-100" aria-labelledby="headlessui-menu-button-10" id="edit_delete_list" role="menu" tabindex="0" data-headlessui-state="open" style="">
		                        <button class="text-gray-700 dark:text-gray-300 group flex items-center space-x-2 px-2" id="headlessui-menu-item-46" role="menuitem" tabindex="-1" data-headlessui-state="">
		                           <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-5 w-5">
		                              <path stroke-linecap="round" stroke-linejoin="round" d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10"></path></svg>
		                           <span class="font-medium">수정하기</span>
		                        </button>
		                        <button class="text-gray-700 dark:text-gray-300 group flex items-center space-x-2 px-2" id="headlessui-menu-item-47" role="menuitem" tabindex="-1" data-headlessui-state="">
		                           <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true" class="h-5 w-5">
		                              <path stroke-linecap="round" stroke-linejoin="round" d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"></path></svg>
		                           <span class="font-medium">삭제하기</span>
		                        </button>
                     		</div>
                		</div>
                	</c:if>
                	<!-- 여긴 학원 계정인데, 학원 계정 체크 어떻게 했는지 값 몰라서 일단 주석 처리함 -->
                	<%--
                	<c:if test="${sessionScope.loginuser == }">
                    	<div class="login_academy">
                    		<span> (학원 이름) 계정 접속 </span>
							<i class="fa-regular fa-bookmark fa-lg"></i>
							<i class="fa-solid fa-bell fa-lg"></i>
		                	<a href="#">
		                		<img src="" alt="profile">
		                	</a>
                		</div>
                     --%>
                </div>
                

            </nav>
        </nav>
    </haeder>    