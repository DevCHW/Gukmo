<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

 <!-- Bootstrap CSS -->
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 

  <!-- Font Awesome 5 Icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
  
  <!-- noto sans -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">

  <!-- Optional JavaScript -->
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script> 

  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seonwoo/report.css" />
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hasol/gukmo.css" />
	
  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/report.js" ></script>

  <div class="container mt-3" class="d-flex flex-column">
   <!-- 신고 폼 시작 -->
   <form name="reportFrm">
  
	  <div class="d-flex justify-content-between">
	  	<div></div>
	  	<h2>신고하기</h2>
	  	<button id="btn_close" class="btn rounded">X</button>
	  </div>
	
      <hr style="background-color: black; height: 1.2px;">
  
	  <div class="p-3 border-bottom d-flex flex-column"> 
		<table>
			<tbody>
				<tr>
				 <td>작성자</td>
				 <td><span id="nickname" class="input_signup rounded pl-2" >${requestScope.boardvo.nickname}</span></td>
				</tr>
				
				<tr>
				 <td>내 용</td>
				 <td><span id="subject" class="input_signup rounded pl-2" >${requestScope.boardvo.subject}</span></td>
				</tr>
			</tbody>
		</table>
	   </div>     
	     <!-- 등록용 닉네임 -->
		 <input type="hidden" name="fk_num" value="${requestScope.boardvo.board_num}" />
		 <input type="hidden" name="report_nickname" value="${sessionScope.user.nickname}" />
		 <input type="hidden" name="reported_nickname" value="${requestScope.boardvo.nickname}" />
  
  
  	 <div class="mt-3">
  	  <h5>사유선택</h5>
      <label for="simple_report_reason" class="community_label pr-2">구분</label>
      <select name="simple_report_reason" id="simple_report_reason" class="pl-2 border rounded">
	       <option value="">신고사유</option>
	       <option>스팸홍보/도배글입니다.</option>
	       <option>불법정보를 포함하고 있습니다.</option>
	       <option>청소년에게 유해한 내용입니다.</option>
	       <option>욕설/생명경시/혐오/차별적 표현입니다.</option>
	       <option>개인정보 노출 게시물입니다.</option>
	       <option>불쾌한 표현이 있습니다.</option>
	       <option>기타</option>
      </select>
        
      
    <div class="line">
        <label for="detail_report_reason" class="label_signup mt-3">신고에 필요한 정보를 입력하세요.</label>
    </div>
        <div>
          <textarea name="detail_report_reason" id="detail_report_reason" class="form-control" style="height:250px;"></textarea>
        </div>
	</div>
	<footer>
		  <button id="reportWrite" class="btn border rounded w-100 mt-3">신고하기</button>
	      <button id="btn_close" class="btn border rounded w-100 mt-3">취소</button>
	</footer>
	</form>
	
	 </div>
	  
  


      
 