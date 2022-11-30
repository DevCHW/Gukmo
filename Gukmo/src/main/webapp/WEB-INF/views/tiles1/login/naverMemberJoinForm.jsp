<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<script type="text/javascript">
	/**
	 * 네이버로그인 회원가입시키기
	 */
	function userSnsRegisterPro(){
	   let queryString = $("form[name=naverMemberJoinFrm]").serialize();
	    $("form[name=naverMemberJoinFrm]").ajaxForm({
	         url : "<%=ctxPath %>/userSnsRegisterPro.do",
	    	 data:queryString,
	 		 type:"POST",
	 		 dataType:"JSON",
	         success:function(data) {
	        	if(data.JavaData == "YES"){
	 				$("input#userid").val(data.userid);
	 				login();
	 			}else{
	 				alert("로그인에 실패했습니다");
	 				location.href="<%=ctxPath %>/login.do";
	 			}
	       },
	       error: function(xhr, status, error){
				alert("로그인에 실패했습니다."+error);
		   }
	   });
	   $("form[name=naverMemberJoinFrm]").submit();
	  
	}//end of method---
	
	
	/**
	 * 로그인완료처리하기
	 */
	function login(){
		const frm = document.naverMemberJoinFrm;
		
		frm.action = "<%=ctxPath %>/login.do";
		frm.method = "POST";
		frm.submit();
	}//end of method--


	$(document).ready(function(){
		userSnsRegisterPro();
	});//end of
	
	
	
	
</script>






<form name="naverMemberJoinFrm">
	<input type="hidden" id="userid" name="userid" value="${requestScope.userid}"/>
	<input type="hidden" name="username" value="${requestScope.username}"/>
	<input type="hidden" name="email" value="${requestScope.email}"/>
	<input type="hidden" name="profile_image" value="${requestScope.profile_image}"/>
	<input type="hidden" name="nickname" value="${requestScope.username}"/>
	<input type="hidden" name="flag" value="naver"/>
	<input type="hidden" name="email_acept" value="0"/>
</form>