// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}//end of method---

$(document).ready(function(){
	
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
              return;
          }

          sel_file = f;

          let reader = new FileReader();
          reader.onload = function(e) {
              $("img#profile_img").attr("src", e.target.result);
              $("img.dropbtn").attr("src", e.target.result);
          }
          reader.readAsDataURL(f);
     });
     
  });//end of Event --
  
  
  $("button#btn_save").click(function(){
	saveMember();
  });//end of Event--
  
  //이메일 수신동의 토글스위치 값 변경시 이벤트
  $("input#email_agreement").change(()=>{
    if($("input#email_agreement").is(":checked")){	//이메일 수신동의 체크시
        $("input#email_acept").val("1");
    }else{	//이메일수신동의 체크해제시
    	$("input#email_acept").val("0");
    }
  });//end of Event--

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
    	   if(json.result == 1){
    	     alert("회원정보 수정 성공!");
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



