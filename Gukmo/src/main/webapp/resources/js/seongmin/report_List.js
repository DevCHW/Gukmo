// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
  // filter버튼 클릭횟수 count
  let filter_click_cnt = 0;


  $("div#sort").click(function(){
    $("div#mask").show();
    $("div#sort_option").fadeIn(300);
    $("div#sort_option").css("display","flex");
    $("div#sort_option").css("flex-direction","column");
  });

  $("div#mask").click(function(){
    $("div#sort_option").fadeOut(300);
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

  $(document).on("click", "input#chxAll", function(){
    var bool = $(this).is(":checked");
    
  }) //end of 전체선택 클릭시

  	// 해당 신고내역 클릭시
   $(document).on("click", ".reportDetail", function(){
	   var report_num = $(this).attr('id');
	   reportDetail(report_num);
   });
  
 
	$("input#searchWord").keyup(function(e){
		if(e.keyCode == 13) {
			// 검색어에 엔터를 했을 경우
			goSearch();
		}
	});
	
	
}); //end of ready

  // 회원 리스트 클릭시
  function reportDetail(report_num) {
    location.href= "reportDetail.do?report_num="+report_num;
  }//end of  function reportDetail(report_num)

  
  function goSearch() {	  
		const frm = document.searchFrm;
		frm.method = "GET";
		frm.action = getContextPath()+"/admin/reportManage_List.do";
		frm.submit();
  }
  

//Function Declaration
  
  
  
