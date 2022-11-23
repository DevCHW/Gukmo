
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
  
  //정렬옵션 클릭시 이벤트
  $("div#sort_option span").click(e=>{
    const target = $(e.currentTarget);
    let sort = target.text();
    
    switch (sort) {
	case "최신순":
		sort = "write_date";
		break;
	case "댓글순":
		sort = "comment_cnt";	
		break;
	case "추천순":
		sort = "like_cnt";
		break;
	case "조회순":
		sort = "views";
		break;
	}//end of switch-case---
    $("span#current_sort").text(sort);
    
    location.href="?page="+sessionStorage.getItem("page")+"&searchWord="+sessionStorage.getItem("searchWord")+"&sort="+sort;
  });
});


//Function Declaration
