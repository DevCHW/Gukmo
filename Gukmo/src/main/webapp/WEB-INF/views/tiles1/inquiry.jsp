<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>  


<div class="container mx-auto my-5">
	<%-- title --%>
	<div class="my-4">
		<h4>비전공 개발자들을 위한 특화된 커뮤니티</h4>
		<ul>
			<li class="my-2">총 회원수 <span style="font-weight:bold; font-size:15px;">${requestScope.cntMember}</span>명</li>
			<li class="my-2">개발자 대상 최대의 홍보 효과를 경험하세요</li>
		</ul>
	</div>
	
	<%-- 국모 광고 이용방법 --%>
	<div class="my-4">
		<h4>GUKMO 광고 이용방법</h4>
		<img src="<%=ctxPath%>/resources/images/광고문의.PNG"/>
	</div>
	
	<%-- 광고문의 --%>
	<div class="my-4">
		<h4>광고문의</h4>
		<ul>
			<li class="my-2">전화번호 : 02-1234-5678</li>
			<li class="my-2">이메일 : ad@gukmo.kr</li>
		</ul>
	</div>
</div>