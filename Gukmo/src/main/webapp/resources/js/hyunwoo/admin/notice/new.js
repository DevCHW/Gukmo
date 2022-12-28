// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


let recaptcha_ok = false;

$(document).ready(function(){
	//이메일 수신동의 토글스위치 값 변경시 이벤트
	$("input#must_read_checkbox").change(()=>{
	  if($("input#must_read_checkbox").is(":checked")){	//이메일 수신동의 체크시
        $("input#must_read").val("1");
      }else{	//이메일수신동의 체크해제시
        $("input#must_read").val("0");
      }
    });//end of Event---
	
	
    // select 값 잡기
    const urlParams = new URL(location.href).searchParams;
    let detail_category = urlParams.get('detailC');
    
    if(detail_category == null) { // 수정일때
    	detail_category = sessionStorage.getItem("detail_category");
    }
    
    $("#detail_category").val(detail_category).attr("selected","selected");
	
	//	==== 스마트 에디터 구현 시작 ==== //
	//전역변수
    var obj = [];
    
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "content",
        sSkinURI: getContextPath()+"/resources/smarteditor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
        }
    });
    // ==== 스마트 에디터 구현 끝 ==== //
	
    
    // ==== 해시태그 구현 시작 ==== //
      var hashtag = {};
      var counter = 0;

      // 태그를 추가한다.
      function addTag(value) {
    	hashtag[counter] = value; // 태그를 Object 안에 추가
        counter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 가 된다.
      }

      function marginTag() {
          return Object.values(hashtag).filter(function (word) {
              return word !== "";
            });
        }
      
      // 최종적으로 서버에 넘길때 tag 안에 있는 값을 만들어서 넘긴다.
      function strTag() {
        return Object.values(hashtag).join(',');
      }

      
      $("#hashtag").on("keyup", function (e) {
          var self = $(this);
          
          if(e.keyCode == 8 && $("input#hashtag").val() == ""){	//백스페이스를 눌렀을 때,인풋태그 값이 채워져있지 않다면 해시태그 지워주기
          	if($("li.tag-item").text() != ""){	//써놓은 해시태그가 있다면
          		let index = $("input#hashtag").prev().children("span.btn_hashtag_delete").attr("idx");
                hashtag[index] = "";
          		$("input#hashtag").prev().remove();
          		return;
          	} 
          }
          
          // input 에 focus 되있을 때 엔터 및 스페이스바 입력시 구동
          if (e.key === "Enter" || e.keyCode == 32) {

            var tagValue = self.val().trim(); // 값 가져오기
            
            //해시태그는 5개 이상 추가 불가능 합니다.
            if($("li.tag-item").length == 5){
            	alert("태그는 5개 이상 추가 불가합니다.");
            	//해시태그 쓴 값 비우기
            	$("input#hashtag").val("");
            	return;
            }
            
            // 값이 없으면 동작 안합니다.
            if (tagValue !== "") {

              // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
              var result = Object.values(hashtag).filter(function (word) {
                  return word === tagValue;
                })

             // 해시태그가 중복되었는지 확인
            if (result.length == 0) { 
                    $("<li class='d-flex align-items-center flex-nowrap mr-2 tag-item'>#"+tagValue+"<span class='btn_hashtag_delete mx-2' style='cursor:pointer; color:darkgray;' idx='"+counter+"'><i class='fa-solid fa-xmark'></i></span></li>").insertBefore("input#hashtag");
                    addTag(tagValue);
                    self.val("");
                } else {
                  alert("태그값이 중복됩니다.");
                }
              }
            e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
          }
          
          
          
          
          
        });

      // 삭제 버튼
      // 삭제 버튼은 비동기적 생성이므로 document 최초 생성시가 아닌 검색을 통해 이벤트를 구현시킨다.
      $(document).on("click", ".btn_hashtag_delete", function (e) {
          let index = $(this).attr("idx");
          hashtag[index] = "";
          $(this).parent().remove();
        });
   
    // ==== 해시태그 구현 끝 ==== // 
      let flag = false;
     
      
      function frm_check(){
    	  flag = false;
    	  
    	  let values = "";
    	  $("li.tag-item").each(function( index, element) {
    		  let value = $(this).text().substr(1);
    		  values += value+ ",";
		   });
    	  
    	  values = values.slice(0, -1);
    	  $("#str_hashTag").val(values);
		   // console.log(values);
		   

          
      	// ==== 스마트 에디터 구현 시작 ==== //
      	// id가 content인 textarea에 에디터에서 대입
      	obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
      	
      	
      	// 글제목 유효성 검사
  		const subject = $("input#subject").val().trim();
  		if(subject == "") {
  			alert("글제목을 입력하세요!!");
  			return;
  		}
  		
  		if($("textarea#content").val().length == 0) {
	  	  alert("글내용을 입력하세요!!");
	  	  return;
	    }
    	    
  		
  		// 글내용 유효성 검사(스마트 에디터용)
  		let contentval = $("textarea#content").val();
  		contentval = contentval.replace(/&nbsp;/gi, "");
  	    contentval = contentval.substring(contentval.indexOf("<p>")+3);   // "             </p>"
  	    contentval = contentval.substring(0, contentval.indexOf("</p>")); // "             "
  	            
  	    
  	    reCAPTCHA();
	    if(!recaptcha_ok){
	    	alert("매크로방지 봇 통과 후 진행해주세요");
	    	return;
	    }
  	    
  	    flag = true;
  	}
     
      
      
      
	// 등록 버튼을 클릭했을시
    $("button#btn_write").click(function() {
    	frm_check();
	    // 폼을 전송
    	if(flag){
    		const frm = document.writerFrm;
    		frm.method = "POST";
    		frm.action = getContextPath()+"/admin/notice/newEnd.do";
    		frm.submit();
    	}
	});
    
    
	// 수정 버튼을 클릭했을시
    $("button#btn_modify").click(function() {
    	frm_check();
    	if(flag){
		    // 폼을 전송
    		const frm = document.writerFrm;
    		frm.method = "POST";
		    frm.action = getContextPath()+"/admin/notice/editEnd.do";
		    frm.submit();
    	}
	});
    
});// end of document




//Function Declaration

/**
 * reCAPTCHA v2 사용하기
 * @returns
 */
function reCAPTCHA(){
	$.ajax({
        url: getContextPath()+'/member/verifyRecaptcha.do',
        type: 'post',
        data: {
            recaptcha: $("#g-recaptcha-response").val()
        },
        async:false,
        success: function(data) {
            switch (data) {
                case 0:
                    recaptcha_ok = true;
            		break;
                case 1:
                    recaptcha_ok = false;
                    break;
                default:
                	recaptcha_ok = false;
               		break;
            }
        }
    });//end of $.ajax({})
	
}//end of method-----


	