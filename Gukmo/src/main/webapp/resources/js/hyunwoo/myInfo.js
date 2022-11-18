$(document).ready(function(){
	
  $("input#username").val(sessionStorage.getItem("user").username);
  $("input#nickname").val(sessionStorage.getItem("user").nickname);

  //프로필이미지박스 hover시,마우스 아웃시
  $("div#profile_img_box").hover(function() {
    $("div#img_mask").fadeIn(200);
    $("div#img_mask").css("display","flex");
  }, function(){
    $("div#img_mask").fadeOut(200);
  });

  //마스크 클릭시
  $("div#img_mask").click(function(){
    $("input#profile_select").trigger("click");
  });

});// end of $(document).ready(function(){})--