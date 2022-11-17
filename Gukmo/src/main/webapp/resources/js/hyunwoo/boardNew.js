$(document).ready(function(){
  
  $("input#hashtag").keyup(e=>{
    if(e.keyCode == 13) { // hashtag에서 엔터를 쳤을 경우
      $(`<li>${$("input#hashtag").val()}<span>x</span></li>`).insertBefore("input#hashtag")
      $("input#hashtag").val("");
    }
    if(e.keyCode == 8 || e.keyCode == 43){  //hashtag 에서 백스페이스나 DElETE 키를 누른 경우
    }
  });
});