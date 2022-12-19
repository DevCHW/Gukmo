<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<script type="text/javascript">
	$(document).ready(function(){
		$("button#btn_delete").click(function(){
			let bool = confirm("해당 버그제보를 삭제하시겠습니까?");
			
			if(bool){
				$.ajax({ 
				    url:"<%=ctxPath %>/admin/bug/delete.do",
				    data:{"bugNum":'${requestScope.bugMap.BUG_NUM}'},
				    type:"post",
				    dataType:"json",
				    success:function(res){
				    	if(res){
				    		alert("삭제가 완료되었습니다!");
				    		location.href = document.referrer;
				    	}else{
				    		alert("삭제실패! 다시 시도해주세요.");
				    	}
				    },//end of success
				    //success 대신 error가 발생하면 실행될 코드 
				    error: function(request,status,error){
				      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				    }
				  });// end of $.ajax({})---
			}
		});//end of Event--
		//뒤로가기 버튼 클릭시
		$("button#btn_close").click(function(){
			location.href = document.referrer;
		});//end of Event--
	});//end of $(document).ready(function(){})--
</script>

<%-- Main Content --%>
<div id="content">
    <%-- Begin Page Content --%>
    <div class="container-fluid">

        <%-- Page Heading --%>
        <h1 class="h3 my-2 text-gray-800">버그 상세보기</h1>
        <p class="mb-4">버그내용을 상세히 검토 후에 내역에서 삭제해주세요.</p>

        <%-- DataTales Example --%>
        <div class="card shadow my-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">버그제보 내역</h6>
            </div>
            <div class="card-body">
            	<div class="row border-bottom">
            		<div class="col-4 border-right py-3">버그번호</div>
            		<div class="col-8 py-3">${requestScope.bugMap.BUG_NUM}</div>
            	</div>
            	<div class="row border-bottom">
            		<div class="col-4 border-right py-3">제보자 아이디</div>
            		<div class="col-8 py-3">${requestScope.bugMap.FK_USERID}</div>
            	</div>
            	<div class="row">
            		<div class="col-4 border-right py-3">url</div>
            		<div class="col-8 py-3"><a href="${requestScope.bugMap.URL}">${requestScope.bugMap.URL}</a></div>
            	</div>
            	
            	<h4 class="mt-5">제보내용</h4>
            	<div class="row mt-3">
            		<div class="col-12">${requestScope.bugMap.CONTENT}</div>
            	</div>
            </div>
            
            <div class="py-4 d-flex justify-content-end border-top px-4">
            	<button type="button" id="btn_delete" class="btn btn-light">삭제</button>
            	<button type="button" id="btn_close" class="btn btn-light ml-3">뒤로가기</button>
            </div>
        </div>
   </div>
</div>