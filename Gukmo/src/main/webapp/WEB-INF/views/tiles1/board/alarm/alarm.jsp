<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath%>/resources/js/hasol/alarm.js"></script>



<div class="dropdown">
	<a class="login_icon alarm_drop"> 
	<i class="fa-solid fa-bell fa-lg alarm_drop" onclick="drop_alarm()"></i>
	<span class="alarm_cnt"></span>
	</a>
	<div id="alarm_dropContent" class="dropdown-content1 mt-1">
		<a href="#">알림</a>
		<div class="div_alarm_content px-3 d-flex flex-column ">
			<!-- 알림 내용이 없을 경우 -->
			<!-- <p>받으신 알림이 없습니다.</p> -->

			<!-- 알림 내용이 있을 경우 (반복문) -->
			<div class="alarm_content"
				onclick="location.href='<%=ctxPath%>/community/questions.do'">
				<div class="alarm_info">
					<span class="like">좋아요 </span> <span>11:53</span>
				</div>
				<p class="alarm_text">[안녕하세요. 질문이...] 글이 좋아요(1)를 받았습니다.</p>
			</div>

			<div class="alarm_content"
				onclick="location.href='<%=ctxPath%>/community/questions.do'">
				<div class="alarm_info">
					<span class="reple"> 댓글 </span> <span>11:53</span>
				</div>
				<p class="alarm_text">[와 그거 정말 좋은 기능...] 댓글에 댓글(4)이 달렸습니다.
				</p>
			</div>

			<div class="alarm_content"
				onclick="location.href='<%=ctxPath%>/community/questions.do'">
				<div class="alarm_info">
					<span class="declare"> 신고 </span> <span>2022.11.22</span>
				</div>
				<p class="alarm_text">[암튼 좀 짜증나네요.] 글에 신고(1)가 접수되었습니다.</p>
			</div>

		</div>
	</div>
</div>