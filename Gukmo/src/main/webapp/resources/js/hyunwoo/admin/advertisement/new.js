// == Field Declaration == //
let division_ok = false;
let client_name_ok = false;
let client_phone_ok = false;
let orgfilename_ok = false;
let url_ok = false;
let date_ok = false;


//datapicker 설정
$.datepicker.setDefaults({
  dateFormat: 'yy-mm',
  prevText: '이전 달',
  nextText: '다음 달',
  monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
  monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
  dayNames: ['일', '월', '화', '수', '목', '금', '토'],
  dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
  dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
  showMonthAfterYear: true,
  yearSuffix: '년'
});


// == Event Declaration == //
$(document).ready(function(){
  //Datepicker 적용
  $("#start_date, #end_date").datepicker({
    dateFormat: 'yy-mm-dd'
  });//end of Event--


  //이미지 업로드시 미리보기보여주기
  $("input#orgfilename").change(function(e){
    let files = e.target.files;
    let filesArr = Array.prototype.slice.call(files);

    let regExp = /(.*?)\/(jpg|jpeg|png|bmp)$/;

    filesArr.forEach(function(f) {
        if (!f.type.match(regExp)) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }

        sel_file = f;

        let reader = new FileReader();
        reader.onload = function(e) {
            $("img#advertisement_img").attr("src", e.target.result);
            $("img.dropbtn").attr("src", e.target.result);
        }
        reader.readAsDataURL(f);
    });
  });//end of Event --






  // 등록 버튼 클릭시 유효성 검사하기
  $("button#btn_add").click(function(){
    const division = $("select#division").val();
    const client_name = $("input#client_name").val();
    const client_phone = $("input#client_phone").val();
    const orgfilename = $("input#orgfilename").val();
    const url = $("input#url").val();
    test_division(division);
    test_client_name(client_name);
    test_client_phone(client_phone);
    test_url(url);
    test_file(orgfilename);
    test_date();

    if(!division_ok){
      alert('광고분류를 선택하세요!');
      $("select#division").trigger("click");
      return;
    } else if(!client_name_ok){
      $("input#client_name").focus();
      $("p#client_name_error").show();
      return;
    } else if(!client_phone_ok){
      $("p#client_phone_error").show();
      $("input#client_phone").focus();
      return;
    } else if(!url_ok){
      $("p#url_error").show();
      $("input#url").focus();
      return;
    } else if(!orgfilename_ok){
      alert("파일을 선택해주세요!");
      return;
    } 
    if(division_ok&& client_name_ok&& client_phone_ok && url_ok && orgfilename_ok && date_ok){
      addAdvertisement();
    }
  });//end of Event--
});//end of $(document).ready(function(){})--



// == Function Declaration == //
/**
 * 구분 유효성검사
 * @param {} division 
 */
function test_division(division){
  division_ok = false;
  if(division == '광고분류 선택'){
    division_ok = false;
  } else{
    division_ok = true;
  }
}//end of method

/**
 * 고객명 유효성검사
 * @param {*} client_name 
 */
function test_client_name(client_name){
  client_name_ok = false;
  if(client_name.trim() == ""){
    client_name_ok = false;
    return;
  } else if(client_name.length >= 10){
    alert("고객명은 10자이내로 입력해주세요");
    client_name_ok = false;
    return;
  } else {
    $("p#client_name_error").hide();
    client_name_ok = true;
  }

}//end of method--



/**
 * 고객연락처 유효성검사
 * @param {*} client_phone 
 */
function test_client_phone(client_phone){
  client_phone_ok = false;
  const regExp = /[0-9]/;
  if(!regExp.test(client_phone)){ //연락처 유효성검사에 통과하지 못한다면
    client_phone_ok = false;
    return;
  } else{ //연락처 유효성검사 통과시
    $("p#client_phone_error").hide();
    client_phone_ok = true;
  }
}//end of mehtod--




/**
 * url 유효성검사
 * @param {} url 
 */
function test_url(url){
  url_ok = false;
  const regExp = /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
  if(!regExp.test(url)){  //url유효성검사를 통과하지 못한다면
    url_ok = false;
  } else{ //url유효성검사를 통과한다면
    $("p#url_error").hide();
    url_ok = true;
  }
}//end of method--




/**
 * 파일을 올렸는지 검사
 */
function test_file(orgfilename){
  orgfilename_ok = false;
  if(orgfilename.trim() == ""){
    
    orgfilename_ok = false;
  } else{
    orgfilename_ok = true;
  }
}//end of method--



/**
 * 날짜 유효성검사
 */
function test_date(){
  date_ok = false;

  let start_date = $("input#start_date").val();
  let end_date = $("input#end_date").val();

  start_date = start_date.replace(/-/g,'');
  end_date = end_date.replace(/-/g,'');
  
  const dateGap = end_date - start_date;
  
  if(!isNaN(dateGap)){  //정상적인 날짜 입력시
    if(dateGap > 0){  //통과
      date_ok = true;
    } else if(dateGap == 0){
      alert("광고 시작일은 종료일과 같을 수 없습니다.")
      date_ok = false;
    } else{
      alert("광고 종료일자는 시작일자보다 나중이어야 합니다.");
      date_ok = false;
    }
  } else{
    alert("Error! 날짜가 아닌 값을 선택하셨습니다!");
    date_ok = false;
  }
}//end of method--



/**
 * 광고등록하기
 */
function addAdvertisement(){
  let bool = confirm("광고를 등록하시겠습니까?");
	if(bool) {  //Yes
		let frm = document.advertisementNewFrm;
		frm.method = "post";
		frm.action = getContextPath()+"/admin/adRegisterResult.do";
		frm.submit();
	} 
}//end of method---

