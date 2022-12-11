// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

// =========================== field Declaration ===========================//
let userid_ok = false;
let passwd_ok = false;
let email_ok = false;
let nickname_ok = false;
let recaptcha_ok = false;
let username_ok = false;
let email_certification = false;


// 타이머 관련 field
let time = 180;
let setTimer = "";
let send_email_click_cnt = 0;

// 이메일 인증코드
let certificationCode;

window.onload = function () {
    google.accounts.id.initialize({
      client_id: "1009243602481-q3hk5769gab0ucfqbsf3r1abj4cg8av5.apps.googleusercontent.com",
      callback: handleCredentialResponse
    });
   // google.accounts.id.prompt();
   // One Tap 기능을 사용하지 않기 때문에 주석처리하였다.
};

$(document).ready(function(){
	
  //가입하기 버튼 비활성화
  $("button#btn_signup").attr("disabled",true);
  $("button#btn_signup").css("background","#EBEBEB");
  $("button#btn_signup").css("color","white");

  //이메일인증 버튼 비활성화
  $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
  $("button#btn_email_certification").css("background","#EBEBEB");
  $("button#btn_email_certification").css("color","white");

  // =========================== Event Declaration ===========================//
  //아이디값 입력시 이벤트처리
  $("input#userid").keyup(e => {
    const userid = $("input#userid").val();
    userid_ok = false;
    $("p#userid_ok").css("display","none");
    if(e.keyCode == 9 && userid == ""){ //백스페이스로 전부 지웠다면
      $("input#userid").css("border","");  //빨간색 테두리 없애기
      $("p#userid_error").text("");
      $("p#userid_error").css("display","");  //에러문구 없애기
      $("label[for='userid']").css("color","");  //라벨 빨간색 없애기
    }
    if(test_userid(userid)){ //아이디 유효성 검사 통과시
      userid_exist_check(userid); //아이디 중복검사
    }
    test_all();
  });//end of Event---

  //비밀번호 값 입력시 이벤트처리
  $("input#passwd").keyup(e => {
    passwd_ok = false;
	let passwd = $("input#passwd").val().trim();
    let passwd_check = $("input#passwd_check").val().trim();
    if(test_passwd(passwd)&&passwd_check != ""){ //비밀번호 유효성검사통과시
      test_passwd_check(); //비밀번호 칸과 비밀번호확인칸 값이 같은지 확인
    };
    test_all(); 
  });//end of Event---

  
  
  
  //비밀번호 확인 값 입력시 이벤트처리
  $("input#passwd_check").keyup(e => {
    passwd_ok = false;
    test_passwd_check(); //비밀번호 칸과 비밀번호확인칸 값이 같은지 확인
    test_all();
  });//end of Event---

  
  
  
  //이메일 값 입력시 이벤트처리
  $("input#email").keyup(e => {
    $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
    $("button#btn_email_certification").css("background","#EBEBEB");
    $("button#btn_email_certification").css("color","white");
    $("p#email_ok").css("display","");
    email_ok = false;
    let email = $("input#email").val();
    if(test_email(email)){ //이메일 유효성검사 통과시
      email_exist_check(email);	//이메일 중복검사
    }; 
    test_all();
  });//end of Event---

  
  
  
  //이메일 인증 버튼 클릭시 이벤트
  $("button#btn_email_certification").click(()=>{
    $("button#btn_send_email").text("인증번호 전송");
    $("span#send_guide").html("");
    $("div#certification_area").css("display","none");
    send_email_click_cnt = 0;
    clearInterval(setTimer);  //타이머 정지
    time = 180;
    test_all();
  });//end of Event---
  

  //이메일 인증번호 전송 버튼 클릭시 이벤트처리
  $("button#btn_send_email").click(()=>{
    const email = $("input#email").val().trim();
    send_email_click_cnt++;

    $("div#certification_area").css("display","flex");
    $("input#input_certificationCode").focus();
    $("span#send_guide").html(`입력하신 이메일 <span style="font-weight:bold; font-size:14px; color:black;">${email}</span>(으)로 <br>
                              	인증번호를 전송하였습니다.`);

    if($("button#btn_send_email").text() == "재전송"){  //재전송을 하였을 경우
      clearInterval(setTimer);  //타이머 정지
      time = 180;
      setTimer = setInterval(timer,1000);
      sendCertificationCode(email);  //이메일인증코드 전송 메소드
      alert("인증코드를 재발송 하였습니다. 메일을 확인해주세요");
      return;
    }
    $("button#btn_send_email").text("재전송");
    setTimer = setInterval(timer,1000);
    sendCertificationCode(email);  //이메일인증코드 전송 메소드
  });//end of Event---

  
  
  
  //이메일 인증완료버튼 클릭시 이벤트
  $("button#btn_certification_complete").click(()=>{
	email_certification = false;
    if(!(send_email_click_cnt > 0)){ // 이메일 전송버튼을 누른적이 없다면
      alert("인증코드를 전송하세요")
    }
    else{ //이메일 전송버튼을 누른적이 있다면
      if(certificationCode == $("input#input_certificationCode").val()){  //이메일 인증성공시
    	email_certification = true;
        alert("이메일인증 성공!");
        $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
        $("button#btn_email_certification").css("background","#EBEBEB");
        $("button#btn_email_certification").css("color","white");
        clearInterval(setTimer);
        $("button[data-dismiss='modal']").trigger("click");	//이메일인증 모달창 닫기
        test_all();
      }
      else{ //이메일 인증실패시
        alert(`이메일인증 실패!<br>입력하신 인증번호를 다시한번 확인해주세요`);
        email_certification = false;
      }
    }
  });//end of Event---
  
  
  
  //이름 칸 값 입력시 이벤트
  $("input#username").keyup(()=>{
	const username = $("input#username").val();
	username_ok = false;
	
    if(test_username(username)){  //유효성검사 통과시
    	test_all();
    }
  });//end of Event---
  


  //닉네임 칸 값 입력시 이벤트
  $("input#nickname").keyup(()=>{
	
	const nickname = $("input#nickname").val();
	
    nickname_ok = false;
    $("p#nickname_ok").css("display","");
    if(test_nickname(nickname)){  //유효성검사 통과시
      nickname_exist_check(nickname);  //중복검사
    }
    test_all();
  });//end of Event---


  //가입하기 버튼 클릭시 이벤트
  $("button#btn_signup").click(()=>{
      reCAPTCHA();
      if(recaptcha_ok){	//자동가입방지를 통과했다면
	    const frm = document.signup_form;
		
        frm.action = getContextPath()+"/member/save.do";
        frm.method = "POST";
        frm.submit();
      }
      else{	//자동가입방지 봇을 통과하지 못했다면
    	alert("자동가입방지 봇 통과 후 가입해주세요");
      }
  });//end of Event---
  
  
  
  
  //이메일 수신동의 토글스위치 값 변경시 이벤트
  $("input#email_agreement").change(()=>{
    if($("input#email_agreement").is(":checked")){	//이메일 수신동의 체크시
    	$("input#email_acept").val("1");
    }else{	//이메일수신동의 체크해제시
    	$("input#email_acept").val("0");
    }
  });//end of Event---
  
  
  //카카오로그인 버튼 클릭시 이벤트
  $("div#kakao_login").click(()=>{
	kakaoLogin(); 	
  });//end of Event--
	
	
  // 네이버로그인 버튼 클릭시 이벤트
  $("div#naver_login").click(()=>{
	const url = viewNaverLoginFrm();
	location.href = url;
  });//end of Event--	
  
  
  
  
  //유효성검사 잘 개발하였는지 테스트
//  $("button#btn_test").click(()=>{
//	 console.log("");
//	 console.log("==========================");
//	 console.log("=========================="); 
//	 console.log("userid_ok : " + userid_ok); 
//	 console.log("passwd_ok : " + passwd_ok); 
//	 console.log("email_ok : " + email_ok); 
//	 console.log("nickname_ok : " + nickname_ok); 
//	 console.log("recaptcha_ok : " + recaptcha_ok); 
//	 console.log("username_ok : " + username_ok); 
//	 console.log("email_certification : " + email_certification); 
//	 console.log("==========================");
//	 console.log("==========================");
//	 console.log("");
//  });
  
  

});//end of $(document).ready(function(){})---






// =========================== Function Declaration =========================== //






/**
 * 아이디 유효성검사(5~15자)
 * @param 사용자가 입력한 userid 값
 * @returns 검사를 통과하면 true, 통과하지 못하면 false
 */
function test_userid(userid){
  const regExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  if(userid && (!(5 <= userid.length && userid.length <= 15) || regExp.test(userid))){ //검사를 통과하지 못했다면,
    $("input#userid").css("border","solid 1px red");  //빨간색 테두리
    $("p#userid_error").text("아이디는 영문또는 숫자인 5~15자이어야 합니다.");
    $("p#userid_error").css("display","block");  //에러문구
    $("label[for='userid']").css("color","red");  //라벨 빨간색
    return false;
  }
  else{ //검사를 통과하였다면
    $("input#userid").css("border","");  //빨간색 테두리 없애기
    $("p#userid_error").text("");
    $("p#userid_error").css("display","");  //에러문구 없애기
    $("label[for='userid']").css("color","");  //라벨 빨간색 없애기
    return true;
  }
}//end of method----



/**
 * 아이디 중복검사
 * @param 사용자가 입력한 아이디 값 
 */
function userid_exist_check(userid){
  $.ajax({ 
    url:getContextPath()+"/member/idExistCheck.do", 
    data:{"userid": userid},
    type:"post",
    dataType:"json",
    async:false,
    success:function(json){
      if(json.idExist){	//아이디가 존재한다면
        $("input#userid").css("border","solid 1px red");  //빨간색 테두리
        $("p#userid_error").text("이미 가입된 아이디입니다.");
        $("p#userid_error").css("display","block");  //에러문구
        $("label[for='userid']").css("color","red");  //라벨 빨간색
      }
      else{	//아이디가 존재하지 않는다면
        $("input#userid").css("border","");  //빨간색 테두리 없애기
        $("p#userid_error").text("");
        $("p#userid_error").css("display","");  //에러문구 없애기
        $("p#userid_ok").css("display","block");
        $("label[for='userid']").css("color","");  //라벨 빨간색 없애기
        userid_ok = true;
      }
    },//end of success
    
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });// end of $.ajax({})---
}//end of method----



/**
 * 비밀번호 유효성검사(소문자,특수문자를 포함한 8~15자)
 * @param 사용자가 입력한 비밀번호 값
 * @returns 검사를 통과하면 true, 통과하지 못하면 false
 */
function test_passwd(passwd){
  const regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g; 
  
  if(!(regExp.test(passwd))){	//유효성검사를 통과못하면
    $("input#passwd").css("border","solid 1px red");  //빨간색 테두리(비밀번호 칸)
    $("p#passwd_error").css("display","block");  //에러문구(비밀번호 칸)
    $("label[for='passwd']").css("color","red");  //라벨 빨간색(비밀번호 칸)

    $("input#passwd_check").css("border","");  //빨간색 테두리 없애기(비밀번호 확인칸)
    $("p#passwd_check_error").css("display","");  //에러문구 없애기(비밀번호 확인칸)
    $("label[for='passwd_check']").css("color","");  //라벨 빨간색 없애기(비밀번호 확인칸)
    passwd_ok = false;
    return false;
  }
  else{ //유효성검사를 통과한다면 
    $("input#passwd").css("border","");  //빨간색 테두리없애기
    $("p#passwd_error").css("display","none");  //에러문구 없애기
    $("label[for='passwd']").css("color","");  //라벨 빨간색 없애기
    return true;
  }
}// end of method---



/**
 * 비밀번호 인풋값과 비밀번호확인 인풋값 같은지 확인
 */
function test_passwd_check(){
  const input_passwd = $("input#passwd").val();
  const input_passwd_check = $("input#passwd_check").val();
  
  if(input_passwd != input_passwd_check){ //값이 다르다면
    $("input#passwd_check").css("border","solid 1px red");  //빨간색 테두리
    $("p#passwd_check_error").css("display","block");  //에러문구
    $("label[for='passwd_check']").css("color","red");  //라벨 빨간색
  }
  else{ //값이 같다면
    $("input#passwd_check").css("border","");  //빨간색 테두리 없애기
    $("p#passwd_check_error").css("display","");  //에러문구 없애기
    $("label[for='passwd_check']").css("color","");  //라벨 빨간색 없애기
    passwd_ok = true;
  }
}// end of method---



/**
 * 이메일 유효성검사
 * @param 사용자가 입력한 이메일 값
 * @returns 유효성검사를 통과하면 true, 통과하지 못하면 false를 반환한다.
 */
function test_email(email){
  const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  if(!regExp.test(email)){  //이메일 유효성검사에 통과하지 못한다면
    $("input#email").css("border","solid 1px red");  //빨간색 테두리
    $("div#email_error_area").css("justify-content","space-between");
    $("p#email_error").text("이메일 형식에 맞지 않습니다.");
    $("p#email_error").css("display","block");  //에러문구
    $("label[for='email']").css("color","red");  //라벨 빨간색
    
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
    $("label[for='email']").css("color","");  //라벨 빨간색 없애기
    
    return true;
  }
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
        $("label[for='email']").css("color","red");  //라벨 빨간색
        
        $("button#btn_email_certification").attr("disabled",true); //이메일인증 버튼 비활성화
        $("button#btn_email_certification").css("background","#EBEBEB");
        $("button#btn_email_certification").css("color","white");
      }
      else{	//이메일이 존재하지 않는다면
        $("input#email").css("border","");  //빨간색 테두리 없애기
        $("p#email_error").text("");
        $("p#email_error").css("display","none");  //에러문구 없애기
        $("label[for='email']").css("color","");  //라벨 빨간색 없애기
        $("p#email_ok").css("display","block");
        
        $("button#btn_email_certification").attr("disabled",false); //이메일인증 버튼 비활성화
        $("button#btn_email_certification").css("background","");
        $("button#btn_email_certification").css("color","");
        email_ok = true;
      }
    },//end of success
    
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });// end of $.ajax({})---
}//end of method----



/**
 * 이메일 인증번호 전송하기
 * @param 사용자가 입력한 email 값
 */
function sendCertificationCode(email){
	$.ajax({ 
	  url:getContextPath()+"/sendEmailCertificationCode.do", 
	  data:{"email": email},
	  type:"post",
	  dataType:"json",
	  success:function(json){
	    if(json.sendMailSuccess){	//이메일 전송에 성공했다면
	      certificationCode = json.certificationCode;
	    } else {	//이메일 전송에 실패했다면
	      $("span#send_guide").html("입력하신 이메일로 전송을 실패했습니다.입력하신 이메일을 다시한번 확인해주세요");
	    }
	  },//end of success
	  //success 대신 error가 발생하면 실행될 코드 
	  error: function(request,status,error){
	  	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	  }
    });//end of $.ajax({})---
}//end of method---







/**
 * 닉네임 유효성검사(10자이내)
 * @param 사용자가 입력한 닉네임 값 
 * @returns 유효성검사를 통과하면 true, 통과하지 못하면 false를 반환
 */
function test_nickname(nickname){
  nickname = nickname + "";
  if(nickname == "" || !(nickname.length < 10) ){ //유효성검사를 통과하지 못할시
    $("input#nickname").css("border","solid 1px red");  //빨간색 테두리
    $("p#nickname_error").css("display","block");  //에러문구
    $("label[for='nickname']").css("color","red");  //라벨 빨간색
    return false;
  }
  else{ //유효성검사 통과시
    $("input#nickname").css("border","");  //빨간색 테두리 없애기
    $("p#nickname_error").css("display","");  //에러문구 없애기
    $("label[for='nickname']").css("color","");  //라벨 빨간색 없애기
    return true;
  }
}//end of method----


/**
 * 닉네임 중복검사
 * @param 사용자가 입력한 닉네임 값
 */
function nickname_exist_check(nickname){
  $.ajax({ 
    url:getContextPath()+"/member/nicknameExistCheck.do", 
    data:{"nickname": nickname},
    type:"post",
    dataType:"json",
    async:false,
    success:function(json){
      if(json.nicknameExist){	//닉네임이 존재한다면
        $("input#nickname").css("border","solid 1px red");  //빨간색 테두리
        $("p#nickname_error").text("이미 존재하는 닉네임입니다.");
        $("p#nickname_error").css("display","block");  //에러문구
        $("label[for='nickname']").css("color","red");  //라벨 빨간색
      }
      else{	//닉네임이 존재하지 않는다면
        $("input#nickname").css("border","");  //빨간색 테두리 없애기
        $("p#nickname_error").text("");
        $("p#nickname_error").css("display","");  //에러문구 없애기
        $("label[for='nickname']").css("color","");  //라벨 빨간색 없애기
        $("p#nickname_ok").css("display","block");
        nickname_ok = true;
      }
    },//end of success
    
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });// end of $.ajax({})---
}//end of method----



/**
 * 유저이름 유효성검사(특수문자,영어,숫자 사용 금지2~10자)
 * @param 사용자가 입력한 유저이름 값
 * @returns 검사를 통과하면 true, 통과하지 못하면 false
 */
function test_username(username){
  const regExp = /^[가-힣]{2,15}$/; 
  
  if(!(regExp.test(username))){	//유효성검사를 통과못하면
    $("input#username").css("border","solid 1px red");  //빨간색 테두리(비밀번호 칸)
    $("p#username_error").css("display","block");  //에러문구(비밀번호 칸)
    $("label[for='username']").css("color","red");  //라벨 빨간색(비밀번호 칸)
    username_ok = false;
    return false;
  }
  else{ //유효성검사를 통과한다면 
    $("input#username").css("border","");  //빨간색 테두리없애기
    $("p#username_error").css("display","none");  //에러문구 없애기
    $("label[for='username']").css("color","");  //라벨 빨간색 없애기
    username_ok = true;
    return true;
  }
}// end of method---






//타이머 함수 만들기
const timer = function timer(){
  if(time<0){ // 타임이 0보다 작게된다면
    clearInterval(setTimer);
    alert("입력시간이 초과하였습니다. 이메일인증을 다시 진행해주세요");
    $("button#btn_certification_complete").attr("disabled",true);
    $("button#btn_certification_complete").css("background-color","#EBEBEB");
    $("button#btn_certification_complete").css("color","white");
    email_certification = false;
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


/**
 * 모든 유효성 검사 통과시 회원가입 버튼을 활성화시키기
 */

function test_all(){
  if(!userid_ok || !passwd_ok || !email_ok || !nickname_ok ||
     !email_certification || !username_ok) { // 유효성검사를 하나라도 통과하지 못했다면
    $("button#btn_signup").attr("disabled",true);
    $("button#btn_signup").css("background","#EBEBEB");
    $("button#btn_signup").css("color","white");
  }
  else{ //유효성 검사를 모두 통과하였다면
    $("button#btn_signup").attr("disabled",false);
    $("button#btn_signup").css("background","");
    $("button#btn_signup").css("color","");
  }
}//end of method----



/**
 * reCAPTCHA v2 사용하기
 * @returns
 */
function reCAPTCHA(){
	$.ajax({
        url: getContextPath()+'/member/verifyRecaptcha.do',
        type: 'post',
        data: {
            recaptcha: $("#g-recaptcha-response").val()
        },
        async:false,
        success: function(data) {
            switch (data) {
                case 0:
                    console.log("자동 가입 방지 봇 통과");
                    recaptcha_ok = true;
            		break;
                case 1:
                    console.log("자동 가입 방지 봇 통과 후 시도해주세요");
                    recaptcha_ok = false;
                    break;
                default:
                    console.log("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다. [Error bot Code : " + Number(data) + "]");
                	recaptcha_ok = false;
               		break;
            }
        }
    });//end of $.ajax({})
	
}//end of method-----








/**
 * 카카오로그인 객체 보내기
 * @param response
 * @returns
 */
function kakaoLoginPro(response){
  const userInfo = {userid:response.id,email:response.kakao_account.email,profile_image:response.properties.profile_image,nickname:response.properties.nickname,email_acept:'0'
		  		 ,username:response.properties.nickname,flag:'kakao'}
  $.ajax({
	type : 'POST',
	url : getContextPath()+'/kakaoLoginPro.do',
	data : userInfo,
	dataType : 'json',
	success : function(data){
		console.log(data)
		if(data.JavaData == "YES"){
			alert("이미 카카오톡으로 가입된 회원입니다.");
		}else if(data.JavaData == "register"){// 회원가입을 해야하는경우
			userSnsRegisterPro(userInfo);	//소셜로그인 회원가입 메소드
		}else{
			alert("로그인에 실패했습니다");
		}
		
	},
	error: function(xhr, status, error){
		alert("로그인에 실패했습니다."+error);
	}
  });//end of ajax
}//end of method---




/**
 * 카카오로그인창 띄우기
 */
function kakaoLogin(){
	Kakao.Auth.login({
		success: function (response) {
		Kakao.API.request({
			url: '/v2/user/me',
			success: function (response) {
				console.log(response);
				kakaoLoginPro(response);
			},
			fail: function (error) {
				console.log(error)
			},
		})
	},
		fail: function (error) {
			console.log(error)
		},
	});
}//end of method--



/**
 * 소셜로그인 회원가입시키기
 */
function userSnsRegisterPro(userInfo){
  $.ajax({
	type : 'POST',
	url : getContextPath()+'/userSnsRegisterPro.do',
	data : userInfo,
	dataType : 'json',
	success : function(data){
		console.log(data)
		if(data.JavaData == "YES"){
			alert("회원가입 성공!");
			location.href=getContextPath()+'/login.do';
		}else{
			alert("소셜로그인 회원가입에 실패했습니다.");
		}
		
	},
	error: function(xhr, status, error){
		alert("소셜로그인 회원가입에 실패했습니다."+error);
	}
  });//end of ajax
}//end of method---



/**
 * 네이버 로그인 폼 띄우기
 * @return 네이버로그인 폼 URL 을 반환한다.
 */
function viewNaverLoginFrm(){
	let url=getContextPath()+"/signup.do";
	$.ajax({
		type : 'get',
		url : getContextPath()+'/naverLogin.do',
		dataType : 'json',
		async:false,
		success : function(json){
			url = json.naverUrl;
		},
		error: function(status, error){
			alert("네이버회원가입이 현재 불안정한 상태입니다. 다시 시도해주세요"+error);
		}
	  });//end of ajax
	return url;
}//end of method--




//구글로그인 핸들러
function handleCredentialResponse(response) {
	const responsePayload = parseJwt(response.credential);
//	  console.log("ID: " + responsePayload.sub);
//    console.log('Full Name: ' + responsePayload.name);
//    console.log('Given Name: ' + responsePayload.given_name);
//    console.log('Family Name: ' + responsePayload.family_name);
//    console.log("Image URL: " + responsePayload.picture);
//    console.log("Email: " + responsePayload.email);
    
    const userInfo = {userid:responsePayload.sub,
    				  email:responsePayload.email,
    				  profile_image:responsePayload.picture,
    				  nickname:responsePayload.given_name,
    				  email_acept:'0',
    				  username:responsePayload.name,
    				  flag:'google'}
    $.ajax({
		type : 'post',
		url : getContextPath()+'/googleLoginPro.do',
		data : userInfo,
		dataType : 'json',
		async:false,
		success : function(data){
		  console.log(data)
		  if(data.JavaData == "YES"){
		    alert("이미 가입된  구글계정 입니다.")
				
		  }else if(data.JavaData == "register"){// 회원가입을 해야하는경우
			 userSnsRegisterPro(userInfo);	//소셜로그인 회원가입 메소드
		  }else{
		    alert("로그인에 실패했습니다");
		  }
		},
		error: function(xhr, status, error){
			alert("로그인에 실패했습니다."+error);
		}
	});//end of ajax
}//end of method--



/**
 * 구글로그인 유저정보JWT 파싱하기
 * @param token
 */
function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};








// 페이스북 로그인

//기존 로그인 상태를 가져오기 위해 Facebook에 대한 호출
function statusChangeCallback(res){
	statusChangeCallback(response);
}

function fnFbCustomLogin(){
	FB.login(function(response) {
		if (response.status === 'connected') {
			console.log(response);
			FB.api('/me', 'get', {fields: 'name,email,picture'}, function(response) {
				let fb_data = jQuery.parseJSON(JSON.stringify(response));
				
				//가져온 데이터 콘솔출력
//				console.log(fb_data);
				
				const userInfo = {userid:fb_data.id
								 ,email:fb_data.email
								 ,profile_image:fb_data.picture.data.url
								 ,nickname:fb_data.name
								 ,email_acept:'0'
			  		 			 ,username:fb_data.name
			  		 			 ,flag:'facebook'};
				//페이스북 로그인 처리 메소드 호출
				facebookLoginPro(userInfo);
			})
		} else if (response.status === 'not_authorized') {
			// 사람은 Facebook에 로그인했지만 앱에는 로그인하지 않았습니다.
			alert('앱에 로그인해야 이용가능한 기능입니다.');
		} else {
			// 그 사람은 Facebook에 로그인하지 않았으므로이 앱에 로그인했는지 여부는 확실하지 않습니다.
			alert('페이스북에 로그인해야 이용가능한 기능입니다.');
		}
	}, {scope: 'public_profile,email'});
}

window.fbAsyncInit = function() {
	FB.init({
		appId      : '5826169730780578', // 내 앱 ID를 입력한다.
		cookie     : true,
		xfbml      : true,
		version    : 'v10.0'
	});
	FB.AppEvents.logPageView();   
};



/**
 * 페이스북로그인 유저정보 보내기
 * @param userInfo
 * @returns
 */
function facebookLoginPro(userInfo){
  $.ajax({
	type : 'POST',
	url : getContextPath()+'/facebookLoginPro.do',
	data : userInfo,
	dataType : 'json',
	success : function(data){
		console.log(data)
		if(data.JavaData == "YES"){
			alert("이미 가입되어있는 페이스북 계정입니다.");
		}else if(data.JavaData == "register"){// 회원가입을 해야하는경우
			userSnsRegisterPro(userInfo);	//소셜로그인 회원가입 메소드
		}else{
			alert("로그인에 실패했습니다");
		}
		
	},
	error: function(xhr, status, error){
		alert("로그인에 실패했습니다."+error);
	}
  });//end of ajax
}//end of method---




