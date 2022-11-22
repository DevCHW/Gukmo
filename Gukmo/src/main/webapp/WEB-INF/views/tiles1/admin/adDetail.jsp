<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<%
	String ctxPath = request.getContextPath();
%>

<!--  직접 만든 css -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/adDetail.css" />

<!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/adDetail.js?ver=1" ></script>
<body>

	<div id="memberManage_container" class="container-fluid row mt-5">
		<div class="col-1"></div>
		<div class="col-8">
				<table id="register_table" class="container register_table">
						<tr>
							<td colspan="4" id="register_text">광고 상세정보</td>
						</tr>
						<tr>
							<td colspan="4" id="necessary_index" class="text-right">
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline w-25 pt-2">
								광고 번호
							</td>
							<td id = "userid">
								${requestScope.adDetail.advertisement_num}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								광고 구분 
							</td>
							<td>
								${requestScope.adDetail.division}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								클라이언트 이름
							</td>
							<td>
								${requestScope.adDetail.client_name}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								클라이언트 번호
							</td>
							<td>
								${requestScope.adDetail.client_phone}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								파일명 
							</td>
							<td>
								${requestScope.adDetail.file_name}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								URL
							</td>
							<td>
								${requestScope.adDetail.url}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								광고 시작 날짜
							</td>
							<td>
								${requestScope.adDetail.start_date}
							</td>
						</tr>	
						
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								광고 기간 
							</td>
							<td>
								${requestScope.adDetail.period}일
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								광고 상태 
							</td>
							<td>						
				                <c:if test="${requestScope.adDetail.status == 0}">
				                  	광고중
				                </c:if>
				                <c:if test="${requestScope.adDetail.status == 1}">
				                  	광고중 아님
				                </c:if>
							</td>
						</tr>
						<input type="hidden" id = "${requestScope.adDetail.advertisement_num}" name="advertisement_num" />
					</table>		
<%-- 			<div class="">
			  <c:if test="${requestScope.memberDetail.status == '활동'}">
		        <button type="button" class = "memberBlock" id="${requestScope.memberDetail.userid}" >정지 등록</button>&nbsp;
		      </c:if>
			  <c:if test="${requestScope.memberDetail.status == '정지'}">
		        <button type="button" id="" class="block_recovery">정지 해제</button>&nbsp;
		      </c:if>
		      
			  <c:if test="${requestScope.memberDetail.status == '휴면'}">
	            <button type="button" id="" class="sleep_recovery">휴면 해제</button>
			  </c:if>
			</div> --%>
			
			<br><br>
			
			<div>
			  <button type="button" id="" onclick="">뒤로 가기</button>
			</div>		
			<br>		
		</div>
					
  </div>
      <br>

