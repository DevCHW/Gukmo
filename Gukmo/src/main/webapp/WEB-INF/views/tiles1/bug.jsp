<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   
<style type="text/css">
	input#url:placeholder,
	textarea#content:placeholder{
		font-size:small;
	}
	input,textarea{
		outline:none;
	}
</style>


<script type="text/javascript">
	$(document).ready(function(){
		$("input#url").focus();
		//버그 등록 클릭시
		$("button#btn_write").click(function(){
			const url = $("input#url").val();
			const content = $("textarea#content").val();
			
			if(url == ""){
				alert("버그를 발견 URL을 적어주세요!");
				$("input#url").focus();
				return;
			}
			if(content == ""){
				alert("버그 내용을 입력해주세요!");
				$("textarea#content").focus();
				return;
			}
			// 모두 통과시
			let bool = confirm("해당 내용으로 버그를 제보하시겠습니까?");
			if(bool){
				const frm = document.writeFrm;
				
				frm.action = "<%=ctxPath %>/bug/newEnd.do";
				frm.method = "POST";
				frm.submit();
			}
		});//end of Event--
		
		//취소 버튼 클릭시
		$("button#btn_close").click(function(){
			history.back();
		});//end of Event--
	});//end of $(document).ready(function(){})--
</script>
    


<div class="container mx-auto my-5">
	<form name="writeFrm">
		<div class="d-flex flex-column my-3">
			<label for="url">버그 발견 URL</label>
			<input type="text" id="url" name="url" placeholder="버그를 발견한 페이지의 URL을 적어주세요" class="border rounded pl-2" style="height:40px;">
		</div>
		
		<div class="my-3">
			<label for="content">버그 내용을 적어주세요</label>
			<textarea name="content" id="content" class="px-2 py-2 border rounded" cols="30" rows="15" style="width:100%;margin:0;" placeholder="버그 내용을 적어주세요"></textarea>
		</div>
		
		<div id="btn_area" class="d-flex justify-content-end mt-3">
			<button type="button" id="btn_write" class="btn btn-light border rounded">제보하기</button>
			<button type="button" id="btn_close" class="btn btn-light border rounded ml-3">취소</button>
		</div>
		<input type="hidden" name="userid" value="${sessionScope.user.userid}">
	</form>
</div>
