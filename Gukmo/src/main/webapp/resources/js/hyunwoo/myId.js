// js파일에서 contextPath를 알아내는 함수

function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

//타이머 관련 field
let time = 180;
let setTimer = "";
let send_email_click_cnt = 0;

//이메일 유효성검사 통과 체크필드
let email_ok = false;
//이메일 인증 완료 체크필드
let email_certification = true;

//이메일 인증코드
let certificationCode;

$(document).ready(function(){
  //이메인인증 버튼 비활성화
  $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
  $("button#btn_email_certification").css("background","#EBEBEB");
  $("button#btn_email_certification").css("color","white");
  
  
   //계정삭제버튼 클릭시 이벤트 처리하기
  $("button#btn_delete_member").click(function(){
    let check_length = $("input:checkbox[name='agreement']:checked").length;
      if(check_length < 1){
        alert("계정삭제 정책에 동의해주세요");
        return;
      }
      else if(check_length == 1){
	    let result = confirm('계정삭제를 하면 다시 복구할 수 없습니다. 그래도 진행하시겠습니까?');

        if(result) {
           //yes
  	      delete_member();
        } else {
           //no
        }
      }
  });// end of Event---
  
  
  //이메일 값이 변경시 이벤트
  $("input#email").keyup(e => {
    $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
    $("button#btn_email_certification").css("background","#EBEBEB");
    $("button#btn_email_certification").css("color","white");
    email_ok = false;
    let email = $("input#email").val();
    if(test_email(email)){ //이메일 유효성검사 통과시
      if(sessionStorage.getItem("email") == email){
	    $("input#email").css("border","");  //빨간색 테두리 없애기
	    $("div#email_error_area").css("justify-content","");
	    $("p#email_error").text("");
	    $("p#email_error").css("display","none");  //에러문구 없애기
	    $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
	    $("button#btn_email_certification").css("background","#EBEBEB");
	    $("button#btn_email_certification").css("color","white");
      }
      else{
  	    email_exist_check(email);	//이메일 중복검사
      }
    }; 
  });//end of Event-----
  
  
  
  //이메일 인증 버튼 클릭시 이벤트
  $("button#btn_email_certification").click(()=>{
    email_certification = false;
    $("button#btn_send_email").text("인증번호 전송");
    $("span#send_guide").html("");
    $("div#certification_area").css("display","none");
    send_email_click_cnt = 0;
    clearInterval(setTimer);  //타이머 정지
    time = 180;
  });// end of Event---
  
  
  
  //이메일 인증번호 전송 버튼 클릭시 이벤트처리
  $("button#btn_send_email").click(()=>{
    const email = $("input#email").val().trim();
    send_email_click_cnt++;

    $("div#certification_area").css("display","flex");
    $("input#input_certificationCode").focus();
    $("span#send_guide").html(`입력하신 이메일 ${email}로 <br>
                              	인증번호를 전송하였습니다.`);

    if($("button#btn_send_email").text() == "재전송"){  //재전송을 하였을 경우
      clearInterval(setTimer);  //타이머 정지
      time = 180;
      setTimer = setInterval(timer,1000);
      send_email(email);  //이메일 전송 메소드
      return;
    }
    $("button#btn_send_email").text("재전송");
    
    setTimer = setInterval(timer,1000);
    send_email(email);  //이메일 전송 메소드
    	
  });// end of Event---
  
  
  
  //이메일 인증완료버튼 클릭시 이벤트
  $("button#btn_certification_complete").click(()=>{
    if(!(send_email_click_cnt > 0)){ // 이메일 전송버튼을 누른적이 없다면
      alert("인증코드를 전송하세요")
    }
    else{ //이메일 전송버튼을 누른적이 있다면
      if(certificationCode == $("input#input_certificationCode").val()){  //이메일 인증성공시
        alert("이메일인증 성공!");
        email_certification = true;
        $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
        $("button#btn_email_certification").css("background","#EBEBEB");
        $("button#btn_email_certification").css("color","white");
      }
      else{ //이메일 인증실패시
        alert(`이메일인증 실패!<br>
              입력하신 인증번호를 다시한번 확인해주세요`);
        email_certification = false;
      }
    }
  });
  
});//end of $(document).ready(function(){})--



// == Function Declaration == //
/**
 * 이메일 유효성검사
 * @param 사용자가 입력한 이메일 값
 * @returns 유효성검사를 통과하면 true, 통과하지 못하면 false를 반환한다.
 */
function test_email(email){
  const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  if(!regExp.test(email)){  //이메일 유효성검사에 통과하지 못한다면
    $("input#email").css("border","solid 1px red");  //빨간색 테두리
    $("p#email_error").text("이메일 형식에 맞지 않습니다.");
    $("p#email_error").css("display","block");  //에러문구
    
    $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
    $("button#btn_email_certification").css("background","#EBEBEB");
    $("button#btn_email_certification").css("color","white");
    return false;
  }
  else{ //이메일 유효성검사를 통과한다면
    $("input#email").css("border","");  //빨간색 테두리 없애기
    $("div#email_error_area").css("justify-content","");
    $("p#email_error").text("");
    $("p#email_error").css("display","none");  //에러문구 없애기
    
    return true;
  }
}//end of method----





/**
 * 계정 삭제하기
 */
function delete_member(){
	$.ajax({ 
	    url:getContextPath()+"/member/delete.do", 
	    type:"post",
	    dataType:"JSON",
	    success:function(json){
	      if(json.result == 1){
	    	alert("계정삭제가 완료되었습니다!");
	    	location.href = getContextPath()+"/index.do";
	      }
	      else{
	        alert("계정삭제가 실패하였습니다. 다시 시도해주세요");
	      }
	      
	    },//end of success
	    //success 대신 error가 발생하면 실행될 코드 
	    error: function(request,status,error){
	      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	    }
	  });// end of $.ajax({})---
}//end of method--



/**
 * 이메일 인증번호 전송하기
 * @param 사용자가 입력한 email 값
 */
function send_email(email){
  $.ajax({ 
    url:getContextPath()+"/member/sendEmail.do", 
    data:{"email": email},
    type:"post",
    dataType:"json",
    success:function(json){
      if(json.sendMailSuccess){	//이메일 전송에 성공했다면
        certificationCode = json.certificationCode;
      }
      else{	//이메일 전송에 실패했다면
        $("span#send_guide").html("입력하신 이메일로 전송을 실패했습니다.입력하신 이메일을 다시한번 확인해주세요");
      }
    },//end of success
    
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method----


/**
 * 이메일 중복검사
 * @param 사용자가 입력한 이메일 값
 */
function email_exist_check(email){
  $.ajax({ 
    url:getContextPath()+"/member/emailExistCheck.do", 
    data:{"email": email},
    type:"post",
    dataType:"json",
    async:false,
    success:function(json){
      if(json.emailExist){	//이메일이 존재한다면
        $("input#email").css("border","solid 1px red");  //빨간색 테두리
        $("p#email_error").text("이미 가입된 이메일입니다.");
        $("p#email_error").css("display","block");  //에러문구
        
        $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
        $("button#btn_email_certification").css("background","#EBEBEB");
        $("button#btn_email_certification").css("color","white");
      }
      else{	//이메일이 존재하지 않는다면
        $("input#email").css("border","");  //빨간색 테두리 없애기
        $("p#email_error").text("");
        $("p#email_error").css("display","");  //에러문구 없애기
        $("p#email_ok").css("display","block");
        
        $("button#btn_email_certification").attr("disabled",false); //이메일인증 버튼 비활성화
        $("button#btn_email_certification").css("background","");
        $("button#btn_email_certification").css("color","");
        email_ok = true;
      }
    },//end of success
    
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });// end of $.ajax({})---
}//end of method----



/**
 * 타이머 함수 만들기
 */
const timer = function timer(){
  if(time<0){ // 타임이 0보다 작게된다면
    clearInterval(setTimer);
    alert("입력시간이 초과하였습니다. 이메일인증을 다시 진행해주세요");
    $("button#btn_certification_complete").attr("disabled",true);
    $("button#btn_certification_complete").css("background-color","#EBEBEB");
    $("button#btn_certification_complete").css("color","white");
    email_certification = false;
    agree_check();
  }
  else{ //타임이 0보다 크다면
    let minute = parseInt(time/60);
    minute = (minute+"").length<2? "0"+minute : minute; //삼항연산자로 분 자리맞춰주기
    let second = time%60;
    second = (second+"").length<2? "0"+second : second; //삼항연산자로 초 자리맞춰주기
    let text = `${minute} : ${second}`;
    $("div#div_timer").text(text);
    time--;
  }
}// end of timer-----