<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 버그리스트 페이지입니다. --%>
<%-- Custom styles for this page --%>
<link href="<%=ctxPath %>/resources/css/hyunwoo/admin/dataTable/dataTables.bootstrap4.min.css" rel="stylesheet">
<%-- 직접만든 CSS(어드민 리스트페이지 공통) --%>
<link rel="stylesheet" href="<%=ctxPath %>/resources/css/hyunwoo/admin/listPage.css">
<%-- dataTable bootstrap4 --%>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/dataTable/jquery.dataTables.min.js"></script>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/dataTable/dataTables.bootstrap4.min.js"></script>

<%-- 직접만든 javascript --%>
<script type="text/javascript">
	$("#dataTable").dataTable({});
</script>


<%-- Main Content --%>
<div id="content">
    <%-- Begin Page Content --%>
    <div class="container-fluid">

        <%-- Page Heading --%>
        <h1 class="h3 my-2 text-gray-800">버그제보 내역</h1>
        <p class="mb-4"></p>

        <%-- DataTales Example --%>
        <div class="card shadow my-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">버그제보 내역</h6>
            </div>
			  
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr style="background-color:#208EC9;">
                            	<th>번호</th>
                                <th>유저아이디</th>
                                <th>URL</th>
                                <th>버그내용</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                        	<%-- 버그리스트 반복문 시작 --%>
                        	<c:forEach var="bug" items="${requestScope.bugList}">
                            <tr class="tr-row" style="cursor:pointer;" onclick="location.href='<%=ctxPath%>/admin/bug/detail.do?bugNum=${bug.BUG_NUM}'">
                            	<td>${bug.BUG_NUM}</td>
                                <td>${bug.FK_USERID}</td>
                                <td>
                                <c:if test="${bug.URL.length() > 40}">
	                                	${bug.URL.substring(0,40)}...
	                                </c:if>
	                                <c:if test="${bug.URL.length() <= 40}">
	                                	${bug.URL}
	                                </c:if>
                                </td>
                                <td>
	                                <c:if test="${bug.CONTENT.length() > 15}">
	                                	${bug.CONTENT.substring(0,15)}...
	                                </c:if>
	                                <c:if test="${bug.CONTENT.length() <= 15}">
	                                	${bug.CONTENT}
	                                </c:if>
                                </td>
                            </tr>
                            </c:forEach>
                            <%-- 버그리스트 반복문 끝 --%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%-- /.container-fluid --%>

</div>
<%-- End of Main Content --%>












