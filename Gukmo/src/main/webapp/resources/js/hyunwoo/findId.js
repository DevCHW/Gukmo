// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}



$(document).ready(function(){
  //취소 버튼 클릭시 이벤트
  $("button#btn_close").click(function(){
    location.href = getContextPath()+"/login.do"
  });//end of Event--

  //계정 찾기 버튼 클릭시 이벤트
  $("button#btn_findMyId").click(function(){
	const email = $("input#email").val();
	
	if(email_exist_check(email)){	//이메일이 존재한다면
	  $("div#findMyIdError").hide();
	  send_email(email);	//계정찾기 이메일 보내기
	}
	else{	//존재하지 않는 이메일이라면
	  $("div#findMyIdError").show();
	}

  });//end of Event--


});//end of $(document).ready(function(){})---


//Function Declaration---
/**
 * 이메일 중복검사
 * @param 사용자가 입력한 이메일 값
 * @returns 이메일이 존재하면 true, 이메일이 존재하지 않는다면 false 반환
 */
function email_exist_check(email){
  let email_exist = false;
  $.ajax({ 
    url:getContextPath()+"/member/emailExistAndSnsCheck.do", 
    data:{"email": email},
    type:"post",
    dataType:"json",
    async:false,
    success:function(json){
      if(json.result){	//이메일이 존재한다면
        email_exist = true;
      }
      else{	//이메일이 존재하지 않는다면
        email_exist = false;
      }
    },//end of success
    
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });// end of $.ajax({})---
  return email_exist;
}//end of method----


/**
 * 계정찾기 이메일 보내기
 */
function send_email(email){
	$.ajax({ 
	  url:getContextPath()+"/sendEmailByMyId.do", 
	  data:{"email": email},
	  type:"post",
	  dataType:"json",
	  success:function(json){
	  	if(json.sendMailSuccess){	//이메일 전송에 성공했다면
	  	  alert("계정찾기 이메일 전송하였습니다.");
	  	}
	  	else{	//이메일 전송에 실패했다면
	  	  alert("이메일 전송에 실패하였습니다. 다시 시도해주세요");
	  	}
	  },//end of success
	  
	  //success 대신 error가 발생하면 실행될 코드 
	  error: function(request,status,error){
	  	toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	  }
    });//end of $.ajax({})---
}//end of method---


