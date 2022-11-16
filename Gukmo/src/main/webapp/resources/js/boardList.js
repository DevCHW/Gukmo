
$(document).ready(function(){
  // filter버튼 클릭횟수 count
  let filter_click_cnt = 0;


  $("div#sort").click(function(){
    $("div#mask").show();
    $("div#sort_option").fadeIn(200);
    $("div#sort_option").css("display","flex");
    $("div#sort_option").css("flex-direction","column");
  });

  $("div#mask").click(function(){
    $("div#sort_option").fadeOut(200);
    $("div#mask").hide();
  });

  $("#btn_filter").click(function(){
    filter_click_cnt++;
    let html="";
    if(filter_click_cnt%2==1){  //filter버튼 click한 횟수가 홀수라면
      html = `<div class="d-flex">
                <!-- 지역대분류 필터 -->
                <div class="mx-2">
                  <label for="big_location">지역대분류</label>
                  <select name="big_location" id="big_location">
                    <option value="">전체</option>
                  </select>
                </div>

                <!-- 지역소분류 필터 -->
                <div class="mx-2">
                  <label for="small_location">지역소분류</label>
                  <select name="small_location" id="small_location">
                    <option value="">전체</option>
                  </select>
                </div>
              </div>`;
    }
    else{   ////filter버튼 click한 횟수가 짝수라면
      html = "";
    }
    $("#filter_area").html(html);
  });
});


//Function Declaration
