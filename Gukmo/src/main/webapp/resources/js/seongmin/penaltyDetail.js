// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
	   
	   var userid = $("input[name='userid']").attr('id');
	   var nickname = $("input[name='nickname']").attr('id');

	   $(document).on("click", ".block_recovery", function(){
		   block_recovery(userid, nickname);
	   });
		   		
		
	   
}); //end of ready

//Function Declaration




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
					  location.href= "penalty_List.do";
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



