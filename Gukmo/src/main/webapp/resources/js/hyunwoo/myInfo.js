$(document).ready(function(){
  $("input[type='checkbox']").click(function(){
    $("p").toggle();
  });

  $("div#profile_img_box").hover(function() {
    $("div#img_mask").fadeIn(200);
    $("div#img_mask").css("display","flex");
  }, function(){
    $("div#img_mask").fadeOut(200);
  });

  $("div#img_mask").click(function(){
    $("input#profile_select").trigger("click");
  });

});