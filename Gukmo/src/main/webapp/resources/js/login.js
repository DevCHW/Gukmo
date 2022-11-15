// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}




$(document).ready(function(){
	$("input#userid").keydown(function(e){	//아이디에서 값 입력시 이벤트
	  if(e.keyCode == 13){	//엔터를 했을 경우
		$("button#btn_login").trigger("click");  
	  }
	});
	
	$("input#passwd").keydown(function(e){
	  if(e.keyCode == 13){	//엔터를 했을 경우
	    $("button#btn_login").trigger("click");  
	  }
	});
	
	
	//로그인버튼 클릭시 이벤트
	$("button#btn_login").click(function(){
	  const userid = $("input#userid").val().trim();
	  const passwd = $("input#passwd").val().trim();
	  if(userid == ""){	//아이디 값이 비어있다면
		  $("span#passwd_error").css("display","none");
		  $("span#userid_error").text("아이디를 입력해주세요");
	      $("span#userid_error").css("display","block");
	      return;
	  }
	  else if(passwd == ""){	//비밀번호 값이 비어있다면
		  $("span#userid_error").css("display","none");
		  $("span#passwd_error").text("비밀번호를 입력해주세요");
		  $("span#passwd_error").css("display","block");
		  return;
	  }
	  else{	//아이디도 입력하고 비밀번호도 입력했다면
		  $("span#userid_error").text("");	//아이디 에러 지우기
		  $("span#passwd_error").text("");	//비밀번호 에러 지우기
		  $("span#userid_error").css("display","none");
		  $("span#passwd_error").css("display","none");
		  $.ajax({	//아이디존재여부 검사
			url:getContextPath()+"/idExist.do",
			type:"POST",
			data:{"userid":userid},
		    dataType:"JSON",
		    success:function(json){	
		    	alert(json.idExist);
		    	if(!json.idExist){	//존재하지 않는 아이디라면
		    		$("span#userid_error").text("존재하지 않는 아이디입니다.");
		    		$("span#userid_error").css("display","block");
		    		return;
		    	}
		    	else{	//존재하는 아이디라면
		    		$.ajax({	
		    			url:getContextPath()+"/userExist.do",
		    			type:"POST",
		    			data:{"userid":userid
		    				 ,"passwd":passwd},
		    		    dataType:"JSON",
		    		    success:function(json){	
		    		    	if(json.userExist){ //로그인이 성공이라면
		    		    		alert("로그인성공");
		    		    		login();
		    		    		return;
		    		    	}
		    		    	else{	//로그인이 실패했다면
		    		    		$("span#userid_error").text("");
		    		    		$("span#userid_error").css("display","none");
		    		    		$("span#passwd_error").text("아이디 또는 비밀번호를 올바르게 입력해주세요.");
		    		    		$("span#passwd_error").css("display","block");
		    		    		return;
		    		    	}
		    		    },
		    			error: function(request, status, error){
		    			    alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		    			}
		    		});//end of ajax
		    	}//end of else--
			
			},
			error: function(request, status, error){
			    alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});//end of ajax
	  }
	});
  
});//end of $(document).ready(function(){})

//Function Declaration
/**
 * 로그인처리를 하는 메소드
 */
function login(){
	const frm = document.login_form;
	
	frm.action = getContextPath()+"login_complete.do";
	frm.method = "POST";
	frm.submit();
}