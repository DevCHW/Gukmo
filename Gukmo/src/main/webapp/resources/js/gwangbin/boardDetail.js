// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

// 답글모두숨기기 클릭횟수
let btn_comment_toggle_click_cnt = 0;

$(document).ready(function(){
	
	// === 스마트 에디터 구현 시작 === 
	//전역변수
    var obj = [];
    var obj2 = [];
    
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "write_comment",
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
    // === 스마트 에디터 구현 끝 === 
    
    
    
    $("button#go_comment").click(function(){
    	
    	alert("dd");
		
		// === 스마트 에디터 구현 시작 === 
		// id가 content인 textarea에 에디터에서 대입
        obj.getById["write_comment"].exec("UPDATE_CONTENTS_FIELD", []);
	    //- === 스마트 에디터 구현 끝 === 
		
		// 글제목 유효성 검사
		const write_comment = $("textarea#write_comment").val().trim();
		if(write_comment == "") {
			alert("글제목을 입력하세요!!");
			return;
		}
		
		
		
	
	
	    // === 글내용 유효성 검사(스마트 에디터 사용 할 경우) 시작 === 
	    var write_comment_val = $("textarea#write_comment").val();
	        
	 // 글내용 유효성 검사 하기 
     // alert(contentval); // content에  공백만 여러개를 입력하여 쓰기할 경우 알아보는것.
     // <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p> 이라고 나온다.
     
	    write_comment_val = write_comment_val.replace(/&nbsp;/gi, ""); // 공백을 "" 으로 변환
     /*    
	         대상문자열.replace(/찾을 문자열/gi, "변경할 문자열");
	     ==> 여기서 꼭 알아야 될 점은 나누기(/)표시안에 넣는 찾을 문자열의 따옴표는 없어야 한다는 점입니다. 
	                  그리고 뒤의 gi는 다음을 의미합니다.
	
	 	 g : 전체 모든 문자열을 변경 global
	 	 i : 영문 대소문자를 무시, 모두 일치하는 패턴 검색 ignore
	*/ 
  //   alert(contentval);
  //   <p>             </p>
     
	    write_comment_val = write_comment_val.substring(write_comment_val.indexOf("<p>")+3);   // "             </p>"
	    write_comment_val = write_comment_val.substring(0, write_comment_val.indexOf("</p>")); // "             "
              
       if(write_comment_val.trim().length == 0) {
    	   alert("글내용을 입력하세요!!");
           return;
       }
	   // === 글내용 유효성 검사(스마트 에디터 사용 할 경우) 끝 === 
	
		
	   // 폼(form)을 전송(submit)
	   const frm = document.addFrm;
	   frm.method = "POST";
	   frm.action = "<%= ctxPath%>/addEnd.action";
	   frm.submit();
	});

    //////////////////////////////////////////////////////////////////////////////////

  //게시글에 [...]클릭시 이벤트
  $("span#btn_more").click(()=>{
    $("div#mask").show();
    $("div#update_or_delete").fadeIn(200);
    $("div#update_or_delete").css("display","flex");
    $("div#update_or_delete").css("flex-direction","column");
  });//end of Event--



  //게시글에 [...]클릭후,마스크 클릭시 이벤트
  $("div#mask").click(()=>{
    $("div#update_or_delete").fadeOut(200);
    $("div#mask").hide();
  });//end of Event--
  
  
  


  //대댓글 쓰기 버튼 클릭시 이벤트
  $("div.btn_write_comment").click(e=>{
    const target = $(e.currentTarget);
    //대댓글 영역
    const bigCommentWriteArea = target.parent().parent().next().children(":first");
    if(target.text().trim() == "댓글쓰기"){ //댓글쓰기를 눌렀을 때
      bigCommentWriteArea.css("display","flex");
      target.text("댓글쓰기 취소");
    } else if(target.text().trim() == "댓글쓰기 취소"){ //댓글쓰기 취소를 눌렀을 때
      bigCommentWriteArea.css("display","none");
      target.text("댓글쓰기");
    }
  });//end of Event--




  //댓글쓰기 취소버튼 클릭시 이벤트
  $("button.btn_big_comment_close").click(e=>{
    const target = $(e.currentTarget);
    const btn_write_comment = target.parent().parent().parent().parent().prev().find("div.btn_write_comment");

    target.parent().parent().parent().hide(); //댓글쓰기 영역 숨기기
    btn_write_comment.text("댓글쓰기");
  });//end of Event--




  // 답글 보였다가 숨기기 버튼 클릭이벤트
  $("div.btn_comment_toggle").click(e=>{
    const target = $(e.currentTarget);
    const bigCommentArea = target.parent().parent().next().children("div.big_comment_area");

    if(btn_comment_toggle_click_cnt%2==0){
      target.next().show(); //'댓글 O개 보기' 보이기
      target.hide();        //'댓글모두숨기기' 숨기기
      bigCommentArea.hide();// 대댓글영역 숨기기
    } else{
      target.prev().show(); //'댓글 O개 보기' 보이기
      target.hide();        //'댓글모두숨기기' 숨기기
      bigCommentArea.show();// 대댓글영역 보이기
    }
    btn_comment_toggle_click_cnt++;
  });//end of Event--



  // 해시태그 클릭시 이벤트
  $("span.hashtag").click(e=>{
    const target = $(e.currentTarget);
    const hashtag = target.text();  //클릭한 해시태그 값 alert
    alert(target.text()); //클릭한 해시태그 값
  });
});//end of $(document).ready(function(){})---








//[...]클릭후, 삭제버튼 클릭시 이벤트
function del_board(board_num){
	  if(confirm('정말 삭제하시겠습니까?')) {
		  
		  alert("삭제백단");
		  
		  location.href="del_board.do?board_num="+board_num;
		  
		  
		  return true;
	  }  
			
		else 
			return false;
};//end of Event--