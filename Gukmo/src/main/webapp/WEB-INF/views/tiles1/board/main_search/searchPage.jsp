<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<H2> 검색 결과 </H2>

<c:forEach var="boardvo" items="${requestScope.searchList}">
<div> ${boardvo.subject}</div> 
<div> ${boardvo.content}</div>

	<c:forEach var="hashtags" items="${boardvo.hashtags}">
	<div> ${hashtags.hashtag}</div> 
	</c:forEach>                                             
</c:forEach>

<nav>
	${requestScope.pageBar}
</nav>