



$(document).ready(function(){
  
  $("#showPeriodInput").hide();

  $('select[name="penalty_period"]').change(function() {
    var result = $("select[name=penalty_period]").val();
    if (result == 'type6') {
      $('#showPeriodInput').show();
      alert("데이터 피커 등록해서 날짜 선택하도록");
    } else {
      $("#showPeriodInput").hide();

}


  }); 

});//end of $(document).r eady(function(){})---




// =========================== Function Declaration =========================== //






