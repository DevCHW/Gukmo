
$(document).ready(function(){
	 // 변수선언
    const menu = $("div#slide_menu");
    const mask = $("div#slide_mask");
    const menu_open = $("div#btn_slide_menu_open");
    const menu_close = $("div#menu_close");
    
    // 메뉴열기버튼 클릭시
    menu_open.click(function(){
      mask.fadeIn(300);
      menu.animate({width:"toggle"},300);
    });
    
    // 메뉴닫기버튼 클릭시
    menu_close.click(function(){
      mask.fadeOut(300);
      menu.animate({width:"toggle"},300);
    });
    
    // 사이드바가 열려있을 때 , 어두운부분을 클릭시
    mask.click(function(){
      mask.fadeOut(300);
      menu.animate({width:"toggle"},300);
    });
     
     ///////////// 프로필 메뉴 토글 시작 ///////////
     $('.dropbtn').click(function(event){
         event.stopPropagation();
          $("#profile_dropContent").toggle();
          $("#alarm_dropContent").hide();
          $("#admin_dropContent").hide();
     });
	 
     $("#profile_dropContent").on("click", function (event) {
         event.stopPropagation();
     });
     ///////////// 프로필 메뉴 토글 끝 ////////////
     
     
     ///////////// 관리자 메뉴 토글 끝 ////////////
     $('.adminMenu').click(function(event){
         event.stopPropagation();
          $("#admin_dropContent").toggle();
          $("#profile_dropContent").hide();
          $("#alarm_dropContent").hide();
     });
	 
     $("#admin_dropContent").on("click", function (event) {
         event.stopPropagation();
     });
     ///////////// 관리자 메뉴 토글 끝 ////////////
     
     
}); // end of $(document).ready(function() ---------------------------------------

	////// 드롭다운박스 외부클릭시 닫히는 함수 ////////
	$(document).on("click", function () {
	    $("#alarm_dropContent").hide();
	    $("#profile_dropContent").hide();
	    $("#admin_dropContent").hide();
	});
	//////////////////////////////////////
	
	
	////// 드롭박스 href 링크로 이동할때 드롭박스 닫히게 하기 시작 ////////
	function member_controll() {
		$("#admin_dropContent").hide();
	}
	
	function academy_controll() {
		$("#admin_dropContent").hide();
	}
	
	
	
	function my_account() {
		$("#profile_dropContent").hide();
	}
	
	function my_info() {
		$("#profile_dropContent").hide();
	}
	
	function activity_details() {
		$("#profile_dropContent").hide();
	}
	////// 드롭박스 href 링크로 이동할때 드롭박스 닫히게 하기 끝 ////////
	
	
	/*  알림있을때 부분 jsp 완성되면 위와같이 알림리스트 클릭했을때 드롭박스 닫히게 수정하기 !!
	 
	function member_controll() {
		$("#admin_dropContent").hide();
	}
	
	*/
	
	
	// 구글 로그아웃 함수
	function signOut() {
	   if(gapi.auth2 != undefined){
	      let auth2 = gapi.auth2.getAuthInstance();
	      auth2.signOut().then(function () {
	      console.log('User signed out.');
	     });
	   }
	   
	   location.href= getContextPath()+"/logout.do";
	}


