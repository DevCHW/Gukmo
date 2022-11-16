<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath%>/resources/css/hasol/index.css?after">

<!-- contents 시작 -->
<div class="container">
	
	<!-- 배너 영역 -->
	
    <!-- 검색창 영역 -->
    <div class="searchBar d-flex my-3">
        <div id="div1">                 
            <div id="div2">                            
                <select name="selectSearch" class="select_search">
                    <option value="academy_name">학원명</option>
                    <option value="subject_name">과정명</option>
                    <option value="board">게시글</option>
                    <!-- <option value="academy_name">해시태그</option> -->			
                </select>
            </div>
        </div>
        <input type="text" class="form-control" id="searchWord" placeholder="검색할 내용을 입력해 주세요!">
    </div>
	<!-- 학원 정보 영역 -->

	<!-- 게시판 정보 영역 -->

</div>
