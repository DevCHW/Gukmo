<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<%
	String ctxPath = request.getContextPath();
%>

<!--  직접 만든 css -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/reportDetail.css" />

<!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/reportDetail.js?ver=1" ></script>
<body>

	<div id="memberManage_container" class="container-fluid row mt-5">
		<div class="col-1"></div>
		<div class="col-8">
				<table id="register_table" class="container register_table">
						<tr>
							<td colspan="4" id="register_text">신고내역 상세</td>
						</tr>
						<tr>
							<td colspan="4" id="necessary_index" class="text-right">
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline w-25 pt-2">
								신고번호
							</td>
							<td id = "userid">
								${requestScope.reportDetail.report_num}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								신고 분류 
							</td>
							<td>
								${requestScope.reportDetail.report_type}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								신고 글 번호
							</td>
							<td>
								${requestScope.reportDetail.fk_num}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								신고자
							</td>
							<td>
								${requestScope.reportDetail.report_nickname}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								신고 받은 자 
							</td>
							<td>
								${requestScope.reportDetail.reported_nickname}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								신고 사유
							</td>
							<td>
								${requestScope.reportDetail.simple_report_reason}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								신고 사유 상세
							</td>
							<td>
								${requestScope.reportDetail.detail_report_reason}
							</td>
						</tr>	
						
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								신고 날짜 
							</td>
							<td>
								${requestScope.reportDetail.report_date}
							</td>
						</tr>
						<input type="hidden" id = "${requestScope.reportDetail.report_num}" name="report_num" />
						<input type="hidden" id = "${requestScope.memberDetail.fk_num}" name="fk_num" />
					</table>		
<%-- 			<div class="">
			  <c:if test="${requestScope.memberDetail.status == '활동'}">
		        <button type="button" class = "memberBlock" id="${requestScope.memberDetail.userid}" >정지 등록</button>&nbsp;
		      </c:if>
			  <c:if test="${requestScope.memberDetail.status == '정지'}">
		        <button type="button" id="" class="block_recovery">정지 해제</button>&nbsp;
		      </c:if>
		      
			  <c:if test="${requestScope.memberDetail.status == '휴면'}">
	            <button type="button" class = "memberBlock" id="${requestScope.memberDetail.userid}" >정지 등록</button>&nbsp;
	            <button type="button" id="" class="sleep_recovery">휴면 해제</button>
			  </c:if>
			</div>
 --%>			
			<br><br>
			
			<div>
			  <button type="button" id="" onclick="">뒤로 가기</button>
			</div>		
			<br>		
		</div>
					
  </div>
      <br>

