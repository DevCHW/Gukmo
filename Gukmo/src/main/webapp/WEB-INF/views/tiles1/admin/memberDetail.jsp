<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%
	String ctxPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원 상세정보</title>

  
</head>

<!--  직접 만든 css -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seongmin/memberDetail.css" />

<!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seongmin/memberDetail.js" ></script>
<body>

	<div id="memberManage_container" class="container-fluid row mt-5">
		<div class="col-1"></div>
		<div class="col-10">
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
							<td>
								${requestScope.mvo.userid}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								이름 
							</td>
							<td>
								${requestScope.mvo.name}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								주소 
							</td>
							<td>
								<span>${requestScope.mvo.address}</span>
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								휴대전화
							</td>
							<td>
								${requestScope.mvo.mobile }
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline pt-2">
								이메일 
							</td>
							<td>
								${requestScope.mvo.email}
							</td>
						</tr>
					</table>
					
					<hr style="border: none; background-color: black; height:1px;">
					
					<table id="register_table_2" class="container register_table mb-5">
						<tr>
							<td class="font-weight-bold align-baseline w-25">성별</td>
							<td class="align-middle">
								<c:if test="${requestScope.mvo.gender == '1'}">
									남
								</c:if>
								<c:if test="${requestScope.mvo.gender == '2'}">
									여
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline">생년월일</td>
							<td>
								${requestScope.mvo.birthday}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline">회원등급</td>
							<td>
								${requestScope.mvo.grade_code}
							</td>
						</tr>
						<tr>
							<td class="font-weight-bold align-baseline">가입일자</td>
							<td>
								${requestScope.mvo.registerday}
							</td>
						</tr>
            <tr>
              <button type="button" id="" onclick="block()">정지 등록</button>&nbsp;
              <button type="button" id="" onclick="block_recovery()">정지 해제</button>&nbsp;
              <button type="button" id="" onclick="sleep_recovery()">휴면 해제</button>
            </tr>
						<tr>
							<td colspan="2" class="pt-5 pb-5">
                <button type="button" id="cancel_btn" class="white" style="width:100%;" onclick="window.close()">닫기</button>
              </td>
						</tr>
					</table>
		</div>
		<div class="col-1"></div>

  </div>
      


</body>
</html>