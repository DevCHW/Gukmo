
$(document).ready(function(){
   
	 $('.alarm_drop').click(function(event){
         event.stopPropagation();
          $("#alarm_dropContent").toggle();
     });
	 
     $("#alarm_dropContent").on("click", function (event) {
         event.stopPropagation();
     });
     
     $('.dropbtn').click(function(event){
         event.stopPropagation();
          $("#profile_dropContent").toggle();
     });
	 
     $("#profile_dropContent").on("click", function (event) {
         event.stopPropagation();
     });
     
     
     $('.adminMenu').click(function(event){
         event.stopPropagation();
          $("#admin_dropContent").toggle();
     });
	 
     $("#admin_dropContent").on("click", function (event) {
         event.stopPropagation();
     });
     
     
     
}); // end of $(document).ready(function() ---------------------------------------

$(document).on("click", function () {
    $("#alarm_dropContent").hide();
});

$(document).on("click", function () {
    $("#profile_dropContent").hide();
});

$(document).on("click", function () {
    $("#admin_dropContent").hide();
});




// --------- 드랍 버튼 함수 -----------



// 관리자 드랍 함수
function drop_admin() {
     document.getElementById("admin_dropContent").classList.toggle("show");
   }

//알람 드랍  함수: 외부 누르면 닫힘
window.onclick = function(event) {
  if (!event.target.matches('.alarm_drop')) {
    var dropdowns = document.getElementById('alarm_dropContent');
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}


// 프로필 드랍 함수: 외부 누르면 닫힘
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementById('profile_dropContent');
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

// 관리자 드랍 함수: 외부 누르면 닫힘
window.onclick = function(event) {
  if (!event.target.matches('.adminMenu')) {
    var dropdowns = document.getElementById('admin_dropContent');
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}