// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
	viewCalendar();
	
	
});//end of $(document).ready(function(){})---

// 함수
function viewCalendar() {
	 $.ajax({
         url:getContextPath()+"/admin/viewCalendar.do", 
         data:{"userid": userid,
         	   "sort": sort,
         	   "fromDate": fromDate,
         	   "toDate": toDate},
         type:"POST",
         dataType:"JSON",
         success:function(json){ //활동내역을 가져오는데 성공했다면


     
         },//end of success
         //success 대신 error가 발생하면 실행될 코드 
         error: function(request,status,error){
           alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
         }
       });//end of $.ajax({})---
}



