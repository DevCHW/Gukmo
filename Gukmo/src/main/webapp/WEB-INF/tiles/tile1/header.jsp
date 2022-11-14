<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.net.InetAddress" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 상단 네비게이션 시작 -->
	<haeder>
        <nav bar class="mainNav_bar">
            <nav class="navbar navbar-expand-sm bg-white mainNav" >
                <!-- Brand/logo -->
                <div class="main_left" style="width:60%;">
                    <a class="navbar-brand" href="#" style="width:30%; ">
                        <img src="" alt="logo">
                    </a>

                    <!-- Links -->
                    <nav style="width:80%;">
                        <ul class="mainCate">
                            <li>
                                <a class="nav-link" href="#">국비학원</a>
                            </li>
                            <li>
                                <a class="nav-link" href="#">커뮤니티</a>
                            </li>
                            <li>
                                <a class="nav-link non-border" href="#">공지사항</a>
                            </li>
                        </ul>
                    </nav>
                </div>
                
                <!-- login -->
                <div class="main_right" style="width:20%; text-align: center;">
                    <div>
                        <button type="button" class="btn_login" id="login" style="background-color: white;">로그인</button>
                        <button type="button" class="btn_regist" id="regist" style="background-color: #208EC9; color:white;">회원가입</button>
                    </div>
                </div>
            </nav>
        </nav>
    </haeder>    