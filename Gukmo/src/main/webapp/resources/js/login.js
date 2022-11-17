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
			url:getContextPath()+"/member/idExistCheck.do",
			type:"POST",
			data:{"userid":userid},
		    dataType:"JSON",
		    success:function(json){	
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
		    		    		if(userid != 'admin'){	//관리자로 로그인하지 않았다면
		    		    			user_status(userid);	//유저의 상태체크
		    		    		}
		    		    		else{
		    		    			login();
		    		    		}
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
 * user의 상태 체크
 * @param 사용자가 입력한 userid
 * @returns
 */
function user_status(userid){
	$.ajax({	
		url:getContextPath()+"/statusCheck.do",
		type:"POST",
		data:{"userid":userid},
	    dataType:"JSON",
	    success:function(json){	
	    	switch (json.status) {
				case '활동':
					login();
					break;
					
				case '정지':
					alert("정지된 회원입니다.");
					break;
					
				case '휴면':
					alert("로그인한지 1년이상 지나서 휴면회원으로 전환되었습니다. 휴면을 푸시겠습니까?confirm창 띄운 후 이동 버튼 누르면 휴면 풀어주는 페이지로이동시키기");
					break;
					
				case '대기':
					alert("승인대기중인 회원입니다.")
					break;
				
				case '비밀번호 변경 권장':
					alert("비밀번호 변경한지 3개월 이상 지났습니다 변경을 권장합니다. 비밀번호를 변경하시겠습니까? confirm창 띄운 후 변경 버튼 누르면 비밀번호 변경할 수 있는 페이지로 이동시키기");
					break;
			}//end of switch-case---
	    },
	    error:function(request, status, error){
		    alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});//end of $.ajax({})--
	    	
}//end of method----



/**
 * 로그인완료처리하기
 */
function login(){
	const frm = document.login_form;
	
	frm.action = getContextPath()+"/login.do";
	frm.method = "POST";
	frm.submit();
}

