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
	   const frm = document.addWriteFrm;
	   frm.method = "POST";
	   frm.action = "<%= ctxPath%>/comment_write.do";
	   frm.submit();
	});

    //////////////////////////////////////////////////////////////////////////////////

  //게시글에 [...]클릭시 이벤트
  $("span#btn_more").click(()=>{
    $("div#mask").show();
    $("div#update_or_delete").fadeIn(200);
    $("div#update_or_delete").css("display","flex");
    $("div#update_or_delete").css("flex-direction","column");
    
    console.log("");
  });//end of Event--
  
  
  //댓글에 [...]클릭시 이벤트
  $("span#comment_btn_more").click(()=>{
    $("div#comment_mask").show();
    $("div#comment_update_or_delete").fadeIn(200);
    $("div#comment_update_or_delete").css("display","flex");
    $("div#comment_update_or_delete").css("flex-direction","column");
    
    console.log("");
  });//end of Event--
  




  //게시글에 [...]클릭후,마스크 클릭시 이벤트
  $("div#mask").click(()=>{
    $("div#update_or_delete").fadeOut(200);
    $("div#mask").hide();
  });//end of Event--
  
  //댓글에 [...]클릭후,마스크 클릭시 이벤트
  $("div#comment_mask").click(e=>{
	  
	  const target = $(e.currentTarget);
	  
    $("div#comment_update_or_delete").fadeOut(200);
    $("div#comment_mask").hide();
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
  
  
  
  
  
  
  
  
  //좋아요 버튼 클릭시 이벤트 잡기
  $("div#btn_like").click(e=>{
	const board_num = $("input#board_num").val();
	const userid = $("input#userid").val();
	
	const data = {board_num: board_num,userid: userid};
	
	likeClick(data);	//좋아요 클릭시 처리 메소드 호출
  });//end of Event----
  

  
  
  
  
});//end of $(document).ready(function(){})---



// == Function Declaration == //

function likeClick(data){
	$.ajax({ 
		url:getContextPath()+"/likeProcess.do", 
		type:'post',
		data:data,
		dataType:"json",
		success:function(json){	
			if(json.JavaData == 'login'){	//로그인 중이 아니라면
				
				location.href=getContextPath()+'/login.do';	//로그인페이지로 보내기
				
			} else if(json.JavaData == 'delete'){	//좋아요를 삭제하였다면
				alert("좋아요삭제함");
				
				$("span#like_icon").html("&#9825;");	//빈하트
				const like_cnt = parseInt($("span#like_cnt").text()) - 1;	//좋아요개수 1빼기
				
				$("span#like_cnt").html(like_cnt);	
				
			} else if(json.JavaData == 'insert'){	//좋아요를 추가하였다면
				alert("좋아요 추가함");
				
				$("span#like_icon").html(" &#x1F497;"); //꽉찬하트
				const like_cnt = parseInt($("span#like_cnt").text()) + 1;	//좋아요개수 1더하기
				$("span#like_cnt").html(like_cnt);
				
			} else{
				alert("좋아요 기능 오류");
			}
			
			
		},//end of success
		//success 대신 error가 발생하면 실행될 코드 
		error: function(request,error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	  });//end of $.ajax({})---
}









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


// 댓글작성 클릭시 첨부파일 여부 확인하는 이벤트
function goAddWrite() {
	  
	  const commentContent = $("input#commentContent").val().trim();
	  if(commentContent == "") {
		  alert("댓글 내용을 입력하세요!!");
		  return;
	  }
	  
	  if($("input#attach").val() == "") {
		// 첨부파일이 없는 댓글쓰기인 경우
		  goAddWrite_noAttach();  
	  }
	  else {
		// 첨부파일이 있는 댓글쓰기인 경우
		  goAddWrite_withAttach();
	  }
	  
}// end of function goAddWrite()--------------------------------------



//첨부파일이 없는 댓글쓰기
function goAddWrite_noAttach() {
	  
	
	
	  $.ajax({
		  url:"<%= request.getContextPath()%>/addComment.do",
		  data:{ "fk_userid":$("input#fk_userid").val() 
			    ,"nickname":$("input#nickname").val()
				,"content":$("input#commentContent").val()
				,"parentSeq":$("input#parentSeq").val() },
		/* 또는
		  data:queryString, 
		*/
		  type:"POST",
		  dataType:"JSON",
		  success:function(json){
			  // json ==>  {"n":1,"name":"서영학"}  또는 {"n":0,"name":"서영학"} 
			  const n = json.n;
			  if(n==0) {
				  alert(json.name + "님의 포인트는 300점을 초과할 수 없으므로 댓글쓰기가 불가합니다.");
			  }
			  else {
			   // goReadComment();  // 페이징 처리 안한 댓글 읽어오기
				  goViewComment(1); // 페이징 처리한 댓글 읽어오기
			  }
			  
			  $("input#commentContent").val("");
		  },
		  error: function(request, status, error){
			  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
	  });
	  
}// end of function goAddWrite_noAttach()---------------------




/*
function likeCheck() {
	
	
	const userid = $("input#userid").val();
	const boardNum = $("input#boardNum").text();
	
	if(userid == ""){
		
		if(confirm('로그인이 필요한 기능입니다. 로그인 페이지로 이동하시겠습니까?')) {
			  
			location.href = "login.do";
			  
			  return true;
		}  
				
	    else {
	    	
			  return false;
		}
		
	}
	else {
	
		$.ajax({
			url:getContextPath()+"/board/likeCheck.do",
			data:{"userid":userid
				 ,"boardNum":boardNum},  
			type:"post",
			dataType:"json",
	    //	async:true,   // async:true 가 비동기 방식을 말한다. async 을 생략하면 기본값이 비동기 방식인 async:true 이다.
	                      // async:false 가 동기 방식이다. 지도를 할때는 반드시 동기방식인 async:false 을 사용해야만 지도가 올바르게 나온다. 
	        success:function(json){
	        	
	        	if(json.resultSuccess=="true") {
	        		if(json.resultType == "add"){
						alert("좋아요 성공");
						$("span#like_cnt").text(json.count);
						$("span#like_icon").addClass('true');
					}else{
						alert("좋아요 해제");
						
						$("span#like_cnt").text(json.count);
						$("span#like_icon").removeClass('true');
					}
	        		
	        	}
	        	
	        	else {
	        		
	        	}
	        	
	        },
	        error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
		
		
		
		
	}
	

	
	
	
}

*/	