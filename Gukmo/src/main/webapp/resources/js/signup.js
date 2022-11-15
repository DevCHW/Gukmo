// =========================== field Declaration ===========================//
let userid_ok = false;
let passwd_ok = false;
let email_ok = false;
let username_ok = false;
let nickname_ok = false;
let recaptcha_ok = false;



$(document).ready(function(){
  //아이디값 입력시 이벤트처리
  $("input#userid").keyup(e => {
    const userid = $("input#userid").val();
    if(e.keyCode == 9 && userid == ""){ //백스페이스로 전부 지웠다면
      $("input#userid").css("border","");  //빨간색 테두리 없애기
      $("p#userid_error").text("");
      $("p#userid_error").css("display","");  //에러문구 없애기
      $("label[for='userid']").css("color","");  //라벨 빨간색 없애기
    }
    test_userid(userid);  //아이디 유효성 검사
  });

  //비밀번호 값 입력시 이벤트처리
  $("input#passwd").keyup(e => {
		let passwd = $("input#passwd").val().trim();
    let passwd_check = $("input#passwd_check").val().trim();
    if(test_passwd(passwd)&&passwd_check != ""){ //비밀번호 유효성검사통과시
      test_passwd_check(); //비밀번호 칸과 비밀번호확인칸 값이 같은지 확인
    };  
  });

  //비밀번호 확인 값 입력시 이벤트처리
  $("input#passwd_check").keyup(e => {
    test_passwd_check(); //비밀번호 칸과 비밀번호확인칸 값이 같은지 확인
  });

  //이메일 값 입력시 이벤트처리
  $("input#email").keyup(() => {
    test_email(); //이메일 유효성검사
  });


  //닉네임 칸 값 입력시 이벤트
});//end of $(document).r eady(function(){})---






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
    userid_ok = false;
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
function userid_duplicate_check(userid){
  
}



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
    passwd_ok = false;
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
 */
function test_email(email){
  const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  if(!regExp.test(email)){  //이메일 유효성검사에 통과하지 못한다면
    $("input#email").css("border","solid 1px red");  //빨간색 테두리
    $("p#email_error").css("display","block");  //에러문구
    $("label[for='email']").css("color","red");  //라벨 빨간색
  }
  else{
    $("input#email").css("border","");  //빨간색 테두리 없애기
    $("p#email_error").css("display","");  //에러문구 없애기
    $("label[for='email']").css("color","");  //라벨 빨간색 없애기
  }
}


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
    nickname_ok = false;
    return false;
  }
  else{ //유효성검사 통과시
    $("input#nickname").css("border","");  //빨간색 테두리 없애기
    $("p#nickname_error").css("display","");  //에러문구 없애기
    $("label[for='nickname']").css("color","");  //라벨 빨간색 없애기
    return true;
  }
}


/**
 * 닉네임 중복검사
 * @param 사용자가 입력한 닉네임 값
 */
function nickname_duplicateCheck(nickname){

}





/**
 * 모든 유효성 검사 통과시 회원가입 버튼을 활성화시키기
 */
function test_all(){
  if(!userid_ok || !passwd_ok || !email_ok || !username_ok || !nickname_ok || !recaptcha_ok) { // 유효성검사를 하나라도 통과하지 못했다면
    $("button#btn_signup").attr("disabled",true);
  }
  else{ //유효성 검사를 모두 통과하였다면
    $("button#btn_signup").attr("disabled",false);
  }
}