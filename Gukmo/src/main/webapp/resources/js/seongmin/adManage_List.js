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

  	// 해당 광고 클릭시
   $(document).on("click", ".adDetail", function(){
	   var advertisement_num = $(this).attr('id');
	   adDetail(advertisement_num);
   });
  
 
	$("input#searchWord").keyup(function(e){
		if(e.keyCode == 13) {
			
			// 검색어에 엔터를 했을 경우
			if($("select#searchType").val() == "" && $("input#searchWord").val() != "" ) {
				alert("소분류를 선택하세요.");
				return;
			}
			else {
				goSearch();
			}
		}
	});
	
	
}); //end of ready

  // 회원 리스트 클릭시
  function adDetail(advertisement_num) {
    location.href= "adDetail.do?advertisement_num="+advertisement_num;
  }//end of  function MemberDetail()

  function goSearch() {
	  
		if($("select#searchType").val() == "" && $("input#searchWord").val() != "" ) {
			alert("소분류를 선택하세요.");
			return;
		}
		else {
			const frm = document.searchFrm;
			frm.method = "GET";
			frm.action = getContextPath()+"/admin/adManage_List.do";
			frm.submit();
		}
  }
  
  function ad_insert() {
	location.href= "adRegister.do";
  }
//Function Declaration
  
  
  
