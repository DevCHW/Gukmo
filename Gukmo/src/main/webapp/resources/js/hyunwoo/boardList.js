
$(document).ready(function(){
  // filter버튼 클릭횟수 count
  let filter_click_cnt = 0;
  
  //정렬버튼 클릭시
  $("div#sort").click(function(){
    $("div#mask").show();
    $("div#sort_option").fadeIn(200);
    $("div#sort_option").css("display","flex");
    $("div#sort_option").css("flex-direction","column");
  });

  //정렬버튼 클릭 후, 바깥쪽 아무데나 클릭시
  $("div#mask").click(function(){
    $("div#sort_option").fadeOut(200);
    $("div#mask").hide();
  });
  
});


//Function Declaration
