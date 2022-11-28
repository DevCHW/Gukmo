// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
	   var userid = $("input[name='userid']").attr('id');
	   var nickname = $("input[name='nickname']").attr('id');

	   $(document).on("click", ".memberBlock", function(){
		   block(userid, nickname);
	   });
	   
	   $(document).on("click", ".block_recovery", function(){
		   block_recovery(userid, nickname);
	   });
	   
	   $(document).on("click", ".Regi_agree", function(){
		   Regi_agree(userid, nickname);
	   });
	   

}); //end of ready

//Function Declaration


function block(userid, nickname) {
  var bool = confirm("정지 등록 페이지로 이동하시겠습니까?");
  if( bool == true) {
    location.href="penaltyRegister.do?userid="+userid+"&nickname="+nickname;
  } //end of if

  else {
    return false;
  }
} //end of block()


function block_recovery(userid, nickname) {
	  var bool = confirm("정지 해제하시겠습니까?");
	  if( bool == true) {
		  $.ajax({
			  url:getContextPath()+"/admin/block_recovery.do",
			  data:{"userid":userid
				   ,"nickname":nickname},
				   
			  type:"POST",
			  dataType:"JSON",
			  success:function(json){
				  const n = json.n;
				  if(n==0) {
					  alert("어허");
				  }
				  else {
					  alert("정지가 해제되었습니다.");
					  location.href= "aca_memberDetail.do?userid="+userid;
				  }
			  },
			  error: function(request, status, error){
				  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			  }
		  });

	  }//end of if

	  else {
	    return false;
	  }
	} //end of block_recovery()


function sleep_recovery(userid, nickname) {
	  var bool = confirm("휴면 해제하시겠습니까?");
	  if( bool == true) {
		  $.ajax({
			  url:getContextPath()+"/admin/sleep_recovery.do",
			  data:{"userid":userid
				   ,"nickname":nickname},
				   
			  type:"POST",
			  dataType:"JSON",
			  success:function(json){
				  const n = json.n;
				  if(n==0) {
					  alert("어허");
				  }
				  else {
					  alert("휴면이 해제되었습니다.");
					  location.href= "aca_memberDetail.do?userid="+userid;
				  }
			  },
			  error: function(request, status, error){
				  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			  }
		  });

	  }//end of if

	  else {
	    return false;
	  }
	} //end of sleep_recovery()


function Regi_agree(userid, nickname) {
	  var bool = confirm("회원가입을 승인하시겠습니까?");
	  if( bool == true) {
		  $.ajax({
			  url:getContextPath()+"/admin/Regi_agree.do",
			  data:{"userid":userid
				   ,"nickname":nickname},
				   
			  type:"POST",
			  dataType:"JSON",
			  success:function(json){
				  const n = json.n;
				  if(n==0) {
					  alert("어허");
				  }
				  else {
					  alert("회원가입 요청이 승인되었습니다.");
					  location.href= "aca_memberDetail.do?userid="+userid;
				  }
			  },
			  error: function(request, status, error){
				  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			  }
		  });

	  }//end of if

	  else {
	    return false;
	  }
	} //end of sleep_recovery()
