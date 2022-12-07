// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


/**
 * js 파일은 전부 필드선언 - 이벤트(document.ready) - 함수선언 의 구성으로 이루어져 있음
 */

// == Field Declaration == //



// == Event Declaration == //
$(document).ready(function(){
  


  //네비게이션 바 클릭시 해당 메뉴 글자 색상 변경하기
  $("div#member_navbar > div").click(function(e){
    const target = $(e.currentTarget);
    const menu_name = target.text();

    //기존 것 다 지우기
    $("div#member_navbar > div").css("border-bottom","");
    $("div#member_navbar > div").css("font-size","");
    $("div#member_navbar > div").css("font-weight","");
    $("div#member_navbar > div").css("color","");

    target.css("border-bottom","solid 3px #208EC9");
    target.css("font-size","16px");
    target.css("font-weight","bold");
    target.css("color","#208EC9");

    //다른영역 전부 display:none 시키는 함수 호출
    memberDetailAreaClear();
    
    switch (menu_name) {
      case '활동내역':  //활동내역 메뉴 클릭시
        activities_nav(sessionStorage.getItem("userid"));
        break;
      case '검색기록':  //검색기록 메뉴 클릭시
        search_nav(sessionStorage.getItem("userid"));
      break;
      case '로그인기록':  //로그인기록 메뉴 클릭시
        login_record_nav(sessionStorage.getItem("userid"));
      break;
      case '작성게시물':  //작성게시물 메뉴 클릭시
        write_board_list_nav(sessionStorage.getItem("nickname"));
      break;
      case '신고내역':  //신고내역 메뉴 클릭시
        report_nav(sessionStorage.getItem("nickname"));
      break;
    }//end of switch-case

  });//end of Event--





  //회원정보수정을 정지로 선택하였으면 정지사유등록 모달버튼 보여주기
  $("select#select_status").change(function(){
    const editStatusVal = $("select#select_status").val();

    if(editStatusVal == '정지'){
      $("span#btn_insert_penalty_modal").show();
    } else{
      $("span#btn_insert_penalty_modal").hide();
    }
  });//end of Event--





  //회원정보수정 버튼 클릭시
  $("button#btn_editInfo").click(function(e){
    const target = $(e.currentTarget);
    const btnText = target.text();
    if(btnText == '수정'){  //수정버튼을 눌렀다면

      $("select#select_status").show();
      $("select#select_status").next().hide();
      $("select#select_authority").show();
      $("select#select_authority").next().hide();
      $("button#btn_editClose").show();
      target.text("완료");

    } else if (btnText == '완료'){  //완료버튼을 눌렀다면
      const status = $("select#select_status").val();
      const authority = $("select#select_authority").val();
      const userid = sessionStorage.getItem("userid");
      const editStatusVal = $("select#select_status").val();
      if(editStatusVal == '정지'){  //회원 상태를 정지로 변경하고자 했다면
        const nickname = sessionStorage.getItem("nickname");
        penaltyRegister(nickname,status,authority,userid)
      } else{
        //기본 회원정보 수정메소드 호출
        editMemberInfo(status,authority,userid);
      }
    }
  });//end of Event--




  //회원정보수정 취소 버튼 클릭시
  $("button#btn_editClose").click(function(){
    $("button#btn_editInfo").text("수정");
    $("select#select_status").hide();
    $("select#select_status").next().show();
    $("select#select_authority").hide();
    $("select#select_authority").next().show();
    $("button#btn_editClose").hide();
  });//end of Event--



  //정지등록 폼에서 기타사유 선택시 상세사유작성 textarea 보여주기
  $("select#simple_penalty_reason").change(function(){
    const simplePenaltyReasonVal = $("select#simple_penalty_reason").val();

    if(simplePenaltyReasonVal == '기타사유'){ //기타사유를 선택했다면
      $("div#detail_penalty_reason_area").css("display","flex");
      $("textarea[name='detail_penalty_reason']").focus();
    } else{
      $("div#detail_penalty_reason_area").css("display","none");
    }
  });//end of Event---



});//end of $(document).ready(function(){})-








// == Function Declaration == //
/**
 * 회원에게 이메일전송하기
 */
function sendEmail(email){
  const message = $("textarea#email_message").val();
  alert("이메일전송버튼 클릭,이메일전송메소드 호출 해당 회원 이메일 : "+email + "메세지 : " +message);
  $.ajax({
    url:getContextPath()+"/이메일전송빽단url.do", 
    data:{"email": email,
          "message":message},
    type:"post",
    dataType:"json",
    success:function(json){
      if(json.sendMailSuccess){	//이메일 전송에 성공했다면
        $("button.sendEmailModal_close").trigger("click");  //닫기버튼 클릭
        alert("이메일전송에 성공하였습니다.");
      } else {	//이메일 전송에 실패했다면
        alert("이메일전송에 실패하였습니다. 다시 시도해주세요");
      }
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---

}//end of method--




/**
 * 회원 정보 수정해주기
 */
function editMemberInfo(status,authority,userid){
  alert("회원정보수정메소드 호출 수정할 값 => 회원상태 : "+status+ "권한 : "+ authority);

  $.ajax({
    url:getContextPath()+"/회원정보수정할빽단url.do", 
    data:{"status": email,
          "authority":message,
          "userid":userid},
    type:"post",
    dataType:"json",
    success:function(json){
      if(json.editMemberInfoSuccess){	//회원정보 수정에 성공했다면
        alert("회원정보 수정 성공!");
      } else {	//회원정보 수정에 실패했다면
        alert("회원정보 수정 성공!");
      }
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--



/**
 * 정지내역등록하기
 */
function penaltyRegister(nickname,status,authority,userid){
  alert("정지회원등록하기 메소드 호출");

  //모든 값 잘 입력했나 유효성검사 진행 이후, 정지내역 등록해주기


  //정지내역 등록 진행(동기방식으로 진행해야하기때문에 async:false)
  let queryString = $("form[name=penaltyRegisterFrm]").serialize();
  $("form[name=penaltyRegisterFrm]").ajaxForm({
    url : getContextPath()+"/정지내역등록빽단url.do", 
    data:queryString,
    enctype:"multipart/form-data",
    type:"POST",
    async:false,
    dataType:"JSON",
    success:function(json) {
      if(json.result == 1){ //정지내역 등록에 성공했다면
        editMemberInfo(status,authority,userid)
      } else{ //정지내역 등록에 실패했다면
        alert("회원수정 실패하였습니다. 다시 시도해주세요");
      }
    },
    error: function(request, status, error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of ajax--
  $("form[name=penaltyRegisterFrm]").submit();

}//end of method---













/**
 * 회원 상세정보 네비게이션 바 클릭시 다 지우기
 */
function memberDetailAreaClear(){
  $("div.detail_info_area").css("display","none");
}//end of method--




/**
 * 네비게이션 바에서 활동내역 클릭시 실행될 함수
 */
function activities_nav(userid){
  alert("활동내역보여주기 메소드 호출");
  $("div#member_activities").css("display","block");

  $.ajax({
    url:getContextPath()+"/활동내역select할빽단url.do", 
    data:{"userid": userid},
    type:"get",
    dataType:"json",
    success:function(json){ //활동내역을 가져오는데 성공했다면

    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--



/**
 * 네비게이션 바에서 검색기록 클릭시 실행될 함수
 */
function search_nav(userid){
  alert("검색기록보여주기 메소드 호출");
  $("div#member_search").css("display","block");

  $.ajax({
    url:getContextPath()+"/검색기록select할빽단url.do", 
    data:{"userid": userid},
    type:"get",
    dataType:"json",
    success:function(json){ //검색기록을 가져오는데 성공했다면

    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--




/**
 * 네비게이션 바에서 로그인기록 클릭시 실행될 함수
 */
function login_record_nav(userid){
  alert("로그인기록보여주기 메소드 호출");
  $("div#member_login_record").css("display","block");
  $.ajax({
    url:getContextPath()+"/로그인기록select할빽단url.do", 
    data:{"userid": userid},
    type:"get",
    dataType:"json",
    success:function(json){ //작성게시물을 가져오는데 성공했다면

    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--




/**
 * 네비게이션 바에서 작성게시물 클릭시 실행될 함수
 */
function write_board_list_nav(nickname){
  alert("작성게시물보여주기 메소드 호출");
  $("div#member_write_board_list").css("display","block");
  $.ajax({
    url:getContextPath()+"/작성게시물select할빽단url.do", 
    data:{"nickname": nickname},
    type:"get",
    dataType:"json",
    success:function(json){ //작성게시물을 가져오는데 성공했다면

    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--



/**
 * 네비게이션 바에서 신고내역 클릭시 실행될 함수
 */
function report_nav(nickname){
  alert("신고내역 보여주기 메소드 호출");
  $("div#member_report_list").css("display","block");

  $.ajax({
    url:getContextPath()+"/신고내역select할빽단url.do", 
    data:{"nickname": nickname},
    type:"get",
    dataType:"json",
    success:function(json){ //신고내역을 가져오는데 성공했다면

    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--









