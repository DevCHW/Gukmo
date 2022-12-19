// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}//end of method---


let nickname_ok = false;
let username_ok = false;
let image_ok = false;

$(document).ready(function(){
	$("button#btn_save").attr("disabled",true);
    $("button#btn_save").css("background","#EBEBEB");
    $("button#btn_save").css("color","white");
    $("button#btn_save").css("border","none");
    
	if($("input#email_acept").val() == 1){
		$("span.slider").trigger("click");
	}
	
//  $("input#username").val(sessionStorage.getItem("username"));
//  $("input#nickname").val(sessionStorage.getItem("nickname"));

  //프로필이미지박스 hover시,마우스 아웃시
  $("div#profile_img_box").hover(function() {
    $("div#img_mask").fadeIn(200);
    $("div#img_mask").css("display","flex");
  }, function(){
    $("div#img_mask").fadeOut(200);
  });
  
  
  
  //프로필사진 변경버튼 클릭시
  $("button#btn_change").click(function(){
    $("input#profile_select").trigger("click");
  });

  //마스크 클릭시
  $("div#img_mask").click(function(){
    $("input#profile_select").trigger("click");
  });
  
  //이미지 미리보기
  let sel_file;
  
  //이미지 업로드시
  $("input#profile_select").change(function(e){
	  let files = e.target.files;
      let filesArr = Array.prototype.slice.call(files);
      let regExp = /(.*?)\/(jpg|jpeg|png|bmp)$/;

      filesArr.forEach(function(f) {
          if (!f.type.match(regExp)) {
              alert("확장자는 이미지 확장자만 가능합니다.");
              image_ok = false;
              return;
          }
          sel_file = f;
          image_ok = true;
          let reader = new FileReader();
          reader.onload = function(e) {
              $("img#profile_img").attr("src", e.target.result);
              $("img.dropbtn").attr("src", e.target.result);
          }
          reader.readAsDataURL(f);
          test_all();
     });
     
  });//end of Event --
  
  
  
  //이메일 수신동의 토글스위치 값 변경시 이벤트
  $("input#email_agreement").change(()=>{
    if($("input#email_agreement").is(":checked")){	//이메일 수신동의 체크시
        $("input#email_acept").val("1");
    }else{	//이메일수신동의 체크해제시
    	$("input#email_acept").val("0");
    }
  });//end of Event--
  
  
//회원정보수정 저장버튼 클릭시 이벤트
  $("button#btn_save").click(function(){
	saveMember();
  });//end of Event--
  
  
  
  
  
  //닉네임 칸 값 입력시 이벤트
  $("input#nickname").keyup(()=>{
	nickname_ok = false;
	const nickname = $("input#nickname").val();
	if(nickname == sessionStorage.getItem("nickname")){
		$("p#nickname_error").text("기존 닉네임하고 같습니다.");
		$("input#nickname").css("border","solid 1px red");  //빨간색 테두리
        $("p#nickname_error").css("display","block");  //에러문구
        $("label[for='nickname']").css("color","red");  //라벨 빨간색
        $("p#nickname_ok").css("display","none");  //에러문구(비밀번호 칸)
        test_all();
		return;
	} else{
		$("p#nickname_error").text("닉네임 형식에 맞지 않습니다.")
	}
    
    $("p#nickname_ok").css("display","");
    if(test_nickname(nickname)){  //유효성검사 통과시
      nickname_exist_check(nickname);  //중복검사
    }
    test_all();
  });//end of Event---
  
  
  //이름 칸 값 입력시 이벤트
  $("input#username").keyup(()=>{
	const username = $("input#username").val();
	username_ok = false;
	
	if(username == sessionStorage.getItem("username")){
		$("input#username").css("border","solid 1px red");  //빨간색 테두리(비밀번호 칸)
		$("p#username_error").text("기존이름하고 같습니다.");
	    $("p#username_error").css("display","block");  //에러문구(비밀번호 칸)
	    $("label[for='username']").css("color","red");  //라벨 빨간색(비밀번호 칸)
	    test_all();
	    return;
	} else{
		$("p#username_error").css("display","none");  //에러문구(비밀번호 칸)
		$("p#username_error").text("이름은 특수문자,영어,숫자를 제외한 2~10글자이어야 합니다.");
	}
	
    if(test_username(username)){  //유효성검사 통과시
    	test_all();
    }
  });//end of Event---
  
  
  

});// end of $(document).ready(function(){})--


//Function Declaration
/**
 * 정보수정한 회원 수정해주기
 */
function saveMember(){
    
    let queryString = $("form[name=myInfoFrm]").serialize();
    $("form[name=myInfoFrm]").ajaxForm({
         url : getContextPath()+"/member/infoChange.do",
    	 data:queryString,
 		 enctype:"multipart/form-data",
 		 type:"POST",
 		 dataType:"JSON",
         success:function(json) {
           console.log(json);
    	   if(json.result == 1){
    	     alert("회원정보 수정 성공!");
    	     location.reload();
    	   }
    	   else{
    	     alert("회원정보 수정 실패!");
    	   }
           console.log(json.result);
       },
       error: function(request, status, error){
		  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	   }
   });
    $("form[name=myInfoFrm]").submit();
}//end of method--



/**
 * 닉네임 유효성검사(10자이내)
 * @param 사용자가 입력한 닉네임 값 
 * @returns 유효성검사를 통과하면 true, 통과하지 못하면 false를 반환
 */
function test_nickname(nickname){
  nickname = nickname + "";
  const regExp = /^[가-힣]{2,15}$/;
  if(nickname == "" || !regExp.test(nickname) || nickname.length < 2){ //유효성검사를 통과하지 못할시
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


/**
 * 모든 유효성 검사 통과시 회원가입 버튼을 활성화시키기
 */

function test_all(){
  if(!nickname_ok && !username_ok && !image_ok) { // 유효성검사를 통과하지 못했다면
    $("button#btn_save").attr("disabled",true);
    $("button#btn_save").css("background","#EBEBEB");
    $("button#btn_save").css("color","white");
    $("button#btn_save").css("border","none");
  }
  else{ //유효성 검사를 하나라도 통과하였다면
    $("button#btn_save").attr("disabled",false);
    $("button#btn_save").css("background","");
    $("button#btn_save").css("color","");
    $("button#btn_save").css("border","");
  }
}//end of method----



