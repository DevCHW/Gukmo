<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<%
	String ctxPath = request.getContextPath();
%>

<!--  직접 만든 css -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/memberDetail.css" />

<!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/memberDetail.js?ver=1" ></script>
<body>

	<div id="memberManage_container" class="container-fluid row mt-5">
		<div class="col-1"></div>
		<div class="col-8">
				<table id="register_table" class="container register_table">
						<tr>
							<td colspan="4" id="register_text">회원 상세정보</td>
						</tr>
						<tr>
							<td colspan="4" id="necessary_index" class="text-right">
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline w-25 pt-2">
								아이디
							</td>
							<td id = "userid">
								${requestScope.memberDetail.userid}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								이름 
							</td>
							<td>
								${requestScope.memberDetail.username}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								닉네임
							</td>
							<td>
								${requestScope.memberDetail.nickname}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								이메일 주소
							</td>
							<td>
								${requestScope.memberDetail.email}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								포인트 
							</td>
							<td>
								${requestScope.memberDetail.point}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								이메일 수신 동의 여부
							</td>
							<c:if test="${requestScope.memberDetail.email_acept == 0}">
							<td>
								수신 거부
							</td>
							</c:if>
							<c:if test="${requestScope.memberDetail.email_acept == 1}">
							<td>
								수신 동의
							</td>
							</c:if>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								계정 상태
							</td>
							<td>
								${requestScope.memberDetail.status}
							</td>
						</tr>	
						
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								가입일자 
							</td>
							<td>
								${requestScope.memberDetail.join_date}
							</td>
						</tr>
						<input type="hidden" id = "${requestScope.memberDetail.userid}" name="userid" />
						<input type="hidden" id = "${requestScope.memberDetail.nickname}" name="nickname" />
					</table>		
			<div class="">
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
			
			<br><br>
			
			<div>
			  <button type="button" id="" onclick="">뒤로 가기</button>
			</div>		
			<br>		
		</div>
					
  </div>
      <br>

