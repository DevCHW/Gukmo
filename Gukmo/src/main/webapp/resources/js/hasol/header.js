
$(document).ready(function(){
   
});




// --------- 드랍 버튼 함수 -----------

// 알람 드랍  함수
function drop_alarm() {
  document.getElementById("alarm_dropContent").classList.toggle("show");
}

// 프로필 드랍 함수
function drop_profile() {
     document.getElementById("profile_dropContent").classList.toggle("show");
   }

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