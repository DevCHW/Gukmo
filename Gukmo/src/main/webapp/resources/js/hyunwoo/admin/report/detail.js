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

//신고자가 신고한 내역보기 버튼 클릭시
  $("span#btn_view_list_report").click(function(){
    getHtmlReportList(sessionStorage.getItem("report_nickname"));
  });//end of Event--
	

  //피신고자가 신고당한 내역보기 버튼 클릭시
  $("span#btn_view_list_reported").click(function(){
    getHtmlReportedList(sessionStorage.getItem("reported_nickname"));
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


  
  //피신고자 정지등록 버튼 클릭시
  $("button#btn_insert_penalty").click(function(){
	memberStatusCheck(sessionStorage.getItem("reported_nickname"))
  });


});//end of $(document).ready(function(){})--


// == Function Declaration == //

/**
 * 신고자가 신고한 내역 html 넣어주기
 * @param {*} nickname
 */
function getHtmlReportList(nickname){
  let html="";
  $.ajax({
    url:getContextPath()+"/admin/get_reportList.do", 
    data:{"nickname": nickname},
    type:"post",
    dataType:"json",
    success:function(json){
      let html = "";
      html += `<div class="d-flex justify-content-center border-bottom">`;
      html += `  <div class="d-flex flex-column text-center">`;
      html += `    <span style="width:155px;" class="py-2 px-3">피신고자닉네임</span>`;
      html += `  </div>`;
      html += `  <div class="d-flex flex-column text-center">`
      html += `    <span style="width:155px;" class="py-2 px-3">사유</span>`
      html += `  </div>`
      html += `  <div class="d-flex flex-column text-center">`
      html += `    <span style="width:155px;" class="py-2 px-3">신고일자</span>`
      html += `  </div>`;
      html += `</div>`;
      $.each(json, function(index, item){
        html += `<div class="d-flex justify-content-center my-2">`;
        html += `  <div class="d-flex flex-column text-center">`;
        html += `    <span style="width:155px;" class="py-2 px-3">${item.reported_nickname}</span>`;
        html += `  </div>`;
        html += `  <div class="d-flex flex-column text-center">`
        html += `    <span style="width:155px;" class="py-2 px-3">${item.simple_report_reason}</span>`
        html += `  </div>`
        html += `  <div class="d-flex flex-column text-center">`
        html += `    <span style="width:155px;" class="py-2 px-3">${item.report_date}</span>`
        html += `  </div>`
        html += `</div>`;
      });// end of for--
      

      $("div#list_report").html(html);
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method---






/**
 * 피신고자가 신고당한 내역 html 넣어주기
 * @param {*} nickname 
 * @return html을 반환한다.
 */
function getHtmlReportedList(nickname){
  $.ajax({
    url:getContextPath()+"/admin/get_reportedList.do", 
    data:{"nickname": nickname},
    type:"POST",
    dataType:"json",
    success:function(json){
      let html = "";
      html += `<div class="d-flex justify-content-center border-bottom">`;
      html += `  <div class="d-flex flex-column text-center">`;
      html += `    <span style="width:155px;" class="py-2 px-3">신고자닉네임</span>`;
      html += `  </div>`;
      html += `  <div class="d-flex flex-column text-center">`
      html += `    <span style="width:155px;" class="py-2 px-3">사유</span>`
      html += `  </div>`
      html += `  <div class="d-flex flex-column text-center">`
      html += `    <span style="width:155px;" class="py-2 px-3">신고일자</span>`
      html += `  </div>`;
      html += `</div>`;
      $.each(json, function(index, item){
        html += `<div class="d-flex justify-content-center my-2">`;
        html += `  <div class="d-flex flex-column text-center">`;
        html += `    <span style="width:155px;" class="py-2 px-3">${item.report_nickname}</span>`;
        html += `  </div>`;
        html += `  <div class="d-flex flex-column text-center">`
        html += `    <span style="width:155px;" class="py-2 px-3">${item.simple_report_reason}</span>`
        html += `  </div>`
        html += `  <div class="d-flex flex-column text-center">`
        html += `    <span style="width:155px;" class="py-2 px-3">${item.report_date}</span>`
        html += `  </div>`;
        html += `</div>`;
      });// end of for--

      $("div#list_reported").html(html);
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method---




/**
 * 신고내역 접수처리하기
 * @param {} report_num(신고번호) 
 */
function receipt(report_num){
  let url = "";
  if( $("span#span_report_type").text() == '게시글' ){
	url = getContextPath()+"/admin/report/receiptBoard.do";
  } else if ($("span#span_report_type").text() == '댓글'){
	url = getContextPath()+"/admin/report/receiptComment.do";
  }
  $.ajax({
	url:url, 
    data:{"report_num":report_num},
    type:"post",
    dataType:"json",
    success:function(json){
      if(json.result){	//신고내역 접수에 성공했다면
        alert("신고내역 접수 성공!");
        location.reload();
      } else {	//신고내역 접수에 실패했다면
        alert("신고내역 접수 실패!");
        location.reload();
      }
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--





/**
 * 피신고자가 이미 정지회원인지 체크하기
 */
function memberStatusCheck(nickname){
  $.ajax({
    url:getContextPath()+"/admin/report/memberStatusCheck.do", 
    data:{"nickname":nickname},
    type:"post",
    async:false,
    dataType:"json",
    success:function(json){
      if(json.status == '정지'){
    	 alert('해당회원은 이미 정지상태입니다!');
    	 $("button.insert_penalty_modal_close").trigger("click");
    	 return;
      }else{
    	 penaltyRegister();
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
function penaltyRegister(){

  let queryString = $("form[name=penaltyRegisterFrm]").serialize();
  $("form[name=penaltyRegisterFrm]").ajaxForm({
    url : getContextPath()+"/admin/report/penaltyRegister.do", 
    data:queryString,
    enctype:"multipart/form-data",
    type:"POST",
    dataType:"JSON",
    success:function(json) {
      if(json.result){ //정지내역 등록에 성공했다면
    	  alert('회원 정지에 성공하였습니다!');
    	  $("button.insert_penalty_modal_close").trigger("click");
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