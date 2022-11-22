<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/searchBar.css" />

<%-- 직접만든 javascript --%>
<script type="text/javascript">


</script>
    
<div class="searchBar d-flex mx-auto my-4">
    <input type="text" id="searchWord" class="pl-2" placeholder="검색할 내용을 입력해 주세요!"/>
    <button type="button" id="btn_search">
      <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
    </button>
 </div>