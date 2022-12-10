// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

// 답글모두숨기기 클릭횟수
let btn_comment_toggle_click_cnt = 0;

$(document).ready(function(){
		    
  $("div.comment_edit").hide();
  
  //대댓 보여주는 함수 
  // viewCommentOfComment();
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
  
  
  /////////////////////////// 댓글 관련 //////////////////////
  
  //댓글에서 ... 버튼 클릭후 수정하기 버튼 클릭시 
  $("span.comment_edit").click(function(e) {
	  const comment_num = $(this).parent().parent().parent().parent().find("div.comment_writer_nickname").attr('id');
	  const comment_writer_nickname = document.getElementById(comment_num).innerText;
	  const login_nickname = $("input#nickname").val();
	  const content = $(this).parent().parent().parent().parent().parent().find("div.basic_comment_content").attr('id');
	  
	  
	  if(comment_writer_nickname == login_nickname) {
		  $("div.detail_comment").hide();
		  $("div.comment_edit").show();		  
		  $("textarea#content3").val(content);
		  $("input#c_num").val(comment_num);
	  }
	  else {
		  alert("댓글 수정은 본인만 가능합니다.");
		  return false;
	  }  
  });

  // 댓글 수정하기 버튼 클릭시
  $("button.edit_comment").click(e => {
	  const comment_num = $("input#c_num").val();
	  const content3 = $("textarea#content3").val();
	  // alert(comment_num);
	  // alert(content3);
	  
	  comment_edit(content3, comment_num);
	  
  })//end of  $("#edit_comment").click(e =>

  
  
  //댓글 삭제하기 버튼 클릭시 
  $("span.comment_delete").click(function(e) {
	  const comment_num = $(this).parent().parent().parent().parent().find("div.comment_writer_nickname").attr('id');
	  const comment_writer_nickname = document.getElementById(comment_num).innerText;
	  const login_nickname = $("input#nickname").val();
	  // alert(login_nickname);

	  
	  if(comment_writer_nickname == login_nickname) {

		  if(confirm('정말 삭제하시겠습니까?')) {
			  comment_delete(comment_num);
		  }  			
			else 
				return false;
	  }
	  else {
		  alert("댓글 삭제는 본인만 가능합니다.");
		  return false;
	  }
	  
  }); 
  
  
  


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
  
  
  //댓글 좋아요 버튼 클릭시 이벤트 잡기
  $("div#comment_btn_like").click(e=>{
	const comment_num = $("input#comment_num").val();
	const userid = $("input#userid").val();
	
	const comment_data = {comment_num: comment_num,userid: userid};
	
	comment_likeClick(comment_data);	//좋아요 클릭시 처리 메소드 호출
  });//end of Event----
  

  
  
  
  
});//end of $(document).ready(function(){})---



// == Function Declaration == //

//댓글작성
function goAddComment() {
	
		  goAddWrite_noAttach();
	  
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

//첨부파일이 없는 댓글쓰기
function goAddWrite_noAttach() {
	
	const cmt_board_num = $("input#cmt_board_num").val();
	const nickname = $("input#nickname").val();
	const parent_write_nickname = $("input#parent_write_nickname").val();
	const content = $("textarea#content").val();
	
	  $.ajax({
		  url:getContextPath()+"/addComment.do",
		  data:{ "cmt_board_num":cmt_board_num
				,"nickname":nickname
				,"parent_write_nickname":parent_write_nickname
				,"content":content},
		/* 또는
		  data:queryString, 
		*/
		  type:"POST",
		  dataType:"JSON",
		  success:function(json){
			  // json ==> {"n":1,"name":"서영학"}  또는 {"n":0,"name":"서영학"} 
			  const n = json.n;
			  if(n==0) {
				  alert("댓글 작성 실패");
			  }
			  else {
			   // goReadComment();  // 페이징 처리 안한 댓글 읽어오기
				  alert("댓글 작성 성공");
				  $("textarea#content").val("");
				  window.location.reload();
			  }
			  
			  // $("input#commentContent").val("");
		  },
		  error: function(request, status, error){
			  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
	  });

}// end of function goAddWrite_noAttach()---------------------

//댓글 삭제
function comment_delete(comment_num, comment_writer_nickname) {
	
	  $.ajax({
		  url:getContextPath()+"/commentDelete.do",
		  data:{ "comment_num":comment_num
				,"nickname":comment_writer_nickname},
		  type:"POST",
		  dataType:"JSON",		  
		  success:function(json){
			  const n = json.n;
			  if(n==0) {
				  alert("댓글 삭제 불가능(관리자에게 문의)");
			  }
			  else {
			   // goReadComment();  // 페이징 처리 안한 댓글 읽어오기
				  alert("댓글을 삭제하였습니다.");
				  window.location.reload();
			  }
			  
			  // $("input#commentContent").val("");
		  },
		  error: function(request, status, error){
			  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
	  });
	
}

//댓글 수정
function comment_edit(content3, comment_num) {
	
	  $.ajax({
		  url:getContextPath()+"/commentEdit.do",
		  data:{"comment_num":comment_num
			  , "content":content3},
		  type:"POST",
		  dataType:"JSON",		  
		  success:function(json){
			  const n = json.n;
			  if(n==0) {
				  alert("댓글 수정 불가능(관리자에게 문의)");
			  }
			  else {
			   // goReadComment();  // 페이징 처리 안한 댓글 읽어오기
				  alert("댓글 수정 완료.");
				  window.location.reload();
			  }			  
		  },
		  error: function(request, status, error){
			  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		  }
	  });
	
	
}

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

function comment_likeClick(data){
	$.ajax({ 
		url:getContextPath()+"/comment_likeProcess.do", 
		type:'post',
		data:data,
		dataType:"json",
		success:function(json){	
			if(json.JavaData == 'login'){	//로그인 중이 아니라면
				
				location.href=getContextPath()+'/login.do';	//로그인페이지로 보내기
				
			} else if(json.JavaData == 'delete'){	//좋아요를 삭제하였다면
				alert("좋아요삭제함");
				
				$("span#comment_like_icon").html("&#9825;");	//빈하트
				const comment_like_cnt = parseInt($("span#comment_like_cnt").text()) - 1;	//좋아요개수 1빼기
				
				$("span#comment_like_cnt").html(comment_like_cnt);	
				
			} else if(json.JavaData == 'insert'){	//좋아요를 추가하였다면
				alert("좋아요 추가함");
				
				$("span#comment_like_icon").html(" &#x1F497;"); //꽉찬하트
				const comment_like_cnt = parseInt($("span#comment_like_cnt").text()) + 1;	//좋아요개수 1더하기
				$("span#comment_like_cnt").html(comment_like_cnt);
				
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

function openReport()
{
	
	// 신고 버튼
	var openWin;
	const board_num = $("input#board_num").val();
	
    // window.name = "부모창 이름"; 
    window.name = "boardDetail";
    // window.open("open할 window", "자식창 이름", "팝업창 옵션");
    openWin = window.open("/board/community/report.do?boardNum="+board_num,
            "reportForm", "width=576, height=700, left=500, top= 20");    
}





