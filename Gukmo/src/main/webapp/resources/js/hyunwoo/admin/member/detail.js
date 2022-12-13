// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


/**
 * js 파일은 전부 필드선언 - 이벤트(document.ready) - 함수선언 의 구성으로 이루어져 있음
 */

// == Field Declaration == //
let btn_insert_penalty_modal_click = 0;


// == Event Declaration == //
$(document).ready(function(){
  
	
	// === 전체 datepicker 옵션 일괄 설정하기 ===  
	 //     한번의 설정으로 $("input#fromDate"), $('input#toDate')의 옵션을 모두 설정할 수 있다.
	$.datepicker.setDefaults({
       dateFormat: 'yy-mm-dd'  // Input Display Format 변경
      ,showOtherMonths: true   // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
      ,showMonthAfterYear:true // 년도 먼저 나오고, 뒤에 월 표시
      ,changeYear: true        // 콤보박스에서 년 선택 가능
      ,changeMonth: true       // 콤보박스에서 월 선택 가능                
      ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
      ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
      ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
      ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트             
  });
	
   
   // input 을 datepicker로 선언
   $("input#fromDate").datepicker();                    
   $("input#toDate").datepicker(); 


    //From의 초기값을 오늘 날짜로 설정
	$('input#fromDate').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
	$('input#fromDate2').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
		

    //To의 초기값을 3일후로 설정
    $('input#toDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
    $('input#toDate2').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
    
    // datepicker 날짜 범위 제한
    $('input#fromDate, input#toDate, input#fromDate2, input#toDate2').datepicker("option",'minDate', '-2Y'); 
		 $('input#fromDate, input#toDate, input#fromDate2, input#toDate2').datepicker("option",'maxDate', 'today');
		 
		 $('input#toDate, input#toDate2').datepicker("option",'onClose', function (selectedDate) {
			if(selectedDate.length==10)
         $("#fromDate, input#fromDate2").datepicker("option", "maxDate", selectedDate);
     	else
     		$("#fromDate, input#fromDate2").datepicker("option", "maxDate", max);
     });
    $('#fromDate, input#fromDate2').datepicker("option", "onClose", function (selectedDate) {
    	if(selectedDate.length==10)
            $("#toDate, input#toDate2").datepicker("option", "minDate", selectedDate);
        else
            $("#toDate, input#toDate2").datepicker("option", "minDate", min);
    });
    
    
  


  //네비게이션 바 클릭시 해당 메뉴 글자 색상 변경하기
  $("div#member_navbar > div").click(function(e){
    const target = $(e.currentTarget);
    const menu_name = target.text();

    //기존 것 다 지우기
    $("div#member_navbar > div").css("border-bottom","");
    $("div#member_navbar > div").css("font-size","");
    $("div#member_navbar > div").css("font-weight","");
    $("div#member_navbar > div").css("color","");

    target.css("border-bottom","solid 3px #208EC9");
    target.css("font-size","14px");
    target.css("font-weight","bold");
    target.css("color","#208EC9");

    //다른영역 전부 display:none 시키는 함수 호출
    memberDetailAreaClear();
    
    switch (menu_name) {
      case '활동내역':  //활동내역 메뉴 클릭시
        activities_nav(sessionStorage.getItem("userid"));
        break;
      case '검색기록':  //검색기록 메뉴 클릭시
        search_nav(sessionStorage.getItem("userid"));
      break;
      case '로그인기록':  //로그인기록 메뉴 클릭시
        login_record_nav(sessionStorage.getItem("userid"));
      break;
      case '작성게시물':  //작성게시물 메뉴 클릭시
        write_board_list_nav(sessionStorage.getItem("nickname"));
      break;
      case '신고내역':  //신고내역 메뉴 클릭시
        report_nav(sessionStorage.getItem("nickname"));
      break;
    }//end of switch-case

  });//end of Event--
  
  //기본값 활동내역 네비바 클릭시키기
  $("div#activities_nav").trigger("click");

  //정지사유등록 버튼 클릭시 클릭횟수 증가
  $("span#btn_insert_penalty_modal").click(()=>{btn_insert_penalty_modal_click++;})



  //회원정보수정을 정지로 선택하였으면 정지사유등록 모달버튼 보여주기
  $("select#select_status").change(function(){
    const editStatusVal = $("select#select_status").val();

    if(editStatusVal == '정지'){
      $("span#btn_insert_penalty_modal").show();
    } else{
      $("span#btn_insert_penalty_modal").hide();
    }
  });//end of Event--





  //회원정보수정 버튼 클릭시
  $("button#btn_editInfo").click(function(e){
	
    const target = $(e.currentTarget);
    const btnText = target.text();
    if(btnText == '수정'){  //수정버튼을 눌렀다면
      btn_insert_penalty_modal_click = 0;
      $("select#select_status").show();
      $("select#select_status").next().hide();
      $("select#select_authority").show();
      $("select#select_authority").next().hide();
      $("button#btn_editClose").show();
      target.text("완료");

    } else if (btnText == '완료'){  //완료버튼을 눌렀다면
      const status = $("select#select_status").val();
      const authority = $("select#select_authority").val();
      const userid = sessionStorage.getItem("userid");
      const editStatusVal = $("select#select_status").val();
      if(editStatusVal == '정지'){  //회원 상태를 정지로 변경하고자 했다면
    	if(sessionStorage.getItem("status") != '정지'){	//기존에 정지인 회원이 아니라면
    	  if(btn_insert_penalty_modal_click == 0){	//정지사유 등록 클릭을 한번도 하지 않았다면
    	    alert("정지사유를 등록해주세요.");
    	    return;
    	  } 
    	  const nickname = sessionStorage.getItem("nickname");
    	  penaltyRegister(nickname,status,authority,userid)
    	} else{	//기존에 정지인 회원이였다면
    	  editMemberInfo(status,authority,userid);
    	}
      } else{	//회원 상태를 정지로 변경하는게 아니라면
    	if(sessionStorage.getItem("status") == '정지'){	//기존에 정지인 회원이였다면
    	  deletePenalty(sessionStorage.getItem("nickname"),status,authority,userid);
    	} else{
    	  editMemberInfo(status,authority,userid);
    	}
      }
    }
  });//end of Event--




  //회원정보수정 취소 버튼 클릭시
  $("button#btn_editClose").click(function(){
    $("button#btn_editInfo").text("수정");
    $("select#select_status").hide();
    $("select#select_status").next().show();
    $("select#select_authority").hide();
    $("select#select_authority").next().show();
    $("button#btn_editClose").hide();
  });//end of Event--



  //정지등록 폼에서 기타사유 선택시 상세사유작성 textarea 보여주기
  $("select#simple_penalty_reason").change(function(){
    const simplePenaltyReasonVal = $("select#simple_penalty_reason").val();

    if(simplePenaltyReasonVal == '기타사유'){ //기타사유를 선택했다면
      $("div#detail_penalty_reason_area").css("display","flex");
      $("textarea[name='detail_penalty_reason']").focus();
    } else{
      $("div#detail_penalty_reason_area").css("display","none");
    }
  });//end of Event---

  
  //정렬버튼 클릭시
  $("div#sort").click(function(){
    $("div#mask").show();
    $("div#sort_option").fadeIn(200);
    $("div#sort_option").css("display","flex");
    $("div#sort_option").css("flex-direction","column");
  });

  //정렬버튼 클릭 후, 바깥쪽 아무데나 클릭시
  $("div#mask").click(function(){
    $("div#sort_option").fadeOut(200);
    $("div#mask").hide();
  });
  
  //정렬옵션 클릭시 이벤트
  $("div.acSort > div#sort_option span").click(e=>{
    const target = $(e.currentTarget);
    const sort = target.text();
    
    $("span#current_sort").text(sort);
    
    
    activitiesChart_nav(sessionStorage.getItem("userid"));
  }); 
  
  
  $("button.datepicker").click(function (){
	  activitiesChart_nav(sessionStorage.getItem("userid"));
  });
  
});//end of $(document).ready(function(){})-








// == Function Declaration == //
/**
 * 회원에게 이메일전송하기
 */
function sendEmail(email){
  const message = $("textarea#email_message").val();
  $.ajax({
    url:getContextPath()+"/admin/member/sendEmail.do", 
    data:{"email": email,
          "message":message},
    type:"post",
    dataType:"json",
    success:function(json){
      if(json.sendMailSuccess){	//이메일 전송에 성공했다면
        $("button.sendEmailModal_close").trigger("click");  //닫기버튼 클릭
        alert("이메일전송에 성공하였습니다.");
      } else {	//이메일 전송에 실패했다면
        alert("이메일전송에 실패하였습니다. 다시 시도해주세요");
      }
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---

}//end of method--




/**
 * 회원 정보 수정해주기
 */
function editMemberInfo(status,authority,userid){

  $.ajax({
    url:getContextPath()+"/admin/member/edit.do", 
    data:{"status": status,
          "authority":authority,
          "userid":userid},
    type:"post",
    dataType:"json",
    success:function(json){
      if(json.result){	//회원정보 수정에 성공했다면
        alert("회원정보 수정 성공!");
        location.reload()
      } else {	//회원정보 수정에 실패했다면
        alert("회원정보 수정 실패!");
      }
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--



/**
 * 정지내역등록하기
 */
function penaltyRegister(nickname,status,authority,userid){

  let queryString = $("form[name=penaltyRegisterFrm]").serialize();
  $("form[name=penaltyRegisterFrm]").ajaxForm({
    url : getContextPath()+"/admin/member/penalty/new.do", 
    data:queryString,
    enctype:"multipart/form-data",
    type:"POST",
    dataType:"JSON",
    success:function(json) {
      if(json.result){ //정지내역 등록에 성공했다면
        editMemberInfo(status,authority,userid)
      } else{ //정지내역 등록에 실패했다면
        alert("회원수정 실패하였습니다. 다시 시도해주세요");
      }
    },
    error: function(request, status, error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of ajax--
  $("form[name=penaltyRegisterFrm]").submit();

}//end of method---



/**
 * 기존에 정지인회원을 다른상태로 바꾸려고 하였다면 정지내역에서 지워주기
 */
function deletePenalty(nickname,status,authority,userid){
  $.ajax({
    url:getContextPath()+"/admin/member/penalty/delete.do", 
    data:{"nickname": nickname},
    type:"post",
    dataType:"json",
    async:false,
    success:function(json){
      if(json.result){	//회원정보 수정에 성공했다면
    	editMemberInfo(status,authority,userid)
      } else {	//회원정보 수정에 실패했다면
        alert("회원정보 수정에 실패하였습니다.다시 시도해주세요!");
      }
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}













/**
 * 회원 상세정보 네비게이션 바 클릭시 다 지우기
 */
function memberDetailAreaClear(){
  $("div.detail_info_area").css("display","none");
}//end of method--




/**
 * 네비게이션 바에서 활동내역 클릭시 실행될 함수
 */
function activities_nav(userid){
  $("div#member_activities").css("display","block");
  
  $.ajax({
    url:getContextPath()+"/admin/member/detail/activityList.do", 
    data:{"userid": userid},
    type:"get",
    dataType:"JSON",
    success:function(json){ //활동내역을 가져오는데 성공했다면

        var html = "<table>" +
	        			"<thead>" +
		        			"<tr>" +
			        			"<th>활동날짜</th>" +
			        			"<th>활동구분</th>" +
			        			"<th>상세카테고리</th>"+
			        			"<th>글번호</th>" +
			        			"<th>글제목</th>" +
		        			"</tr>"+
	        			"</thead>"+
        			"<tbody>";
        
        for(var i=0; i<json.length; i++) {
            var obj;
            
            html += "<tr>" +
	            		"<td>"+json[i].activity_date+"</td>" +
	            		"<td>"+json[i].division+"</td>" +
	            		"<td>"+json[i].detail_category+"</td>" +
	            		"<td>"+json[i].fk_board_num+"</td>" +
	            		"<td>"+json[i].subject+"</td>" +
            		"</tr>";             
        }// end of for------------------------------
        
               
        html +="</tbody>" +
        		"</table>";
        
        $("div#member_activities_area").html(html);
        
        activitiesChart_nav(userid);
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--


/**
 * 네비게이션 바에서 활동내역 클릭시 실행되는 차트 ajax
 */ 
function activitiesChart_nav (userid) {
	
	const sort = $("span#current_sort").text();
	
	if(sort != '일자별') {
		$('input#fromDate').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
		$('input#toDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
		$(".datepicker").hide();
	}
	else if(sort == '일자별') {
		$(".datepicker").show();
	}
	
	var fromDate = $("input#fromDate").val();
	var toDate = $("input#toDate").val();
	
	 $.ajax({
         url:getContextPath()+"/admin/member/detail/activityCntList.do", 
         data:{"userid": userid,
         	   "sort": sort,
         	   "fromDate": fromDate,
         	   "toDate": toDate},
         type:"get",
         dataType:"JSON",
         success:function(json){ //활동내역을 가져오는데 성공했다면

         	var dateArr = [];
         	var cntArr = [];
         	
	            for(var i=0; i<json.length; i++) {
	            	var obj;
	        	    var obj2;
	        	    
         		obj = Number(json[i].cnt);
         		obj2 = json[i].activity_date;
         	
	            	cntArr.push(obj); // 배열속에 객체를 넣기
	            	dateArr.push(obj2); // 배열속에 객체를 넣기
	            }// end of for------------------------------
	         
	            
     
		        Highcharts.chart('activityChart_container',  {
		            chart: {
		                type: 'line'
		            },
		            title: {
		                text: sort +' 활동내역 건수'
		            },
		            subtitle: {
		                text: 'Source: <a href="http://localhost:9090/board/admin/chart/activityCntList.do" target="_blank">GUKMO Activity</a>'
		            },
		            xAxis: {
		                categories: dateArr
		            },
		            yAxis: {
		                title: {
		                    text: '건'
		                }
		            },
		            plotOptions: {
		                line: {
		                    dataLabels: {
		                        enabled: true
		                    },
		                    enableMouseTracking: false
		                }
		            },
		            series: [{
		                name: '활동내역',
		                data: cntArr
		            }]
		        });
     
         },//end of success
         //success 대신 error가 발생하면 실행될 코드 
         error: function(request,status,error){
           alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
         }
       });//end of $.ajax({})---
     
}




/**
 * 네비게이션 바에서 검색기록 클릭시 실행될 함수
 */
function search_nav(userid){
  $("div#member_search").css("display","block");

  $.ajax({
    url:getContextPath()+"/admin/member/detail/searchCntList.do", 
    data:{"userid": userid},
    type:"get",
    dataType:"json",
    success:function(json){ //검색기록을 가져오는데 성공했다면

    	// 리스트용
    	var html = "<table>" +
	       			 "<thead>" +
	       			  "<tr>" +
		        			"<th>검색어</th>" +
		        			"<th>횟수</th>" +
	   			      "</tr>"+
	       			 "</thead>"+
			         "<tbody>";
    	// 차트용
     	var data = [];
     	
            for(var i=0; i<json.length; i++) {
            	
            	var obj;
        	    
            	obj = { 
         				name:json[i].key,
         				weight: Number(json[i].cnt)
         			  };
     	
            	data.push(obj); // 배열속에 객체를 넣기
            	
	            html += "<tr>" +
		            		"<td>"+json[i].key+"</td>" +
		            		"<td>"+Number(json[i].cnt)+"</td>" +
	         		    "</tr>"; 
			         
	     }// end of for------------------------------
	     
	     html +="</tbody>" +
	     		"</table>";
		     
	     $("div#many_keyword_area").html(html);
			            	
	            Highcharts.chart('searchChart_container', {
	                accessibility: {
	                    screenReaderSection: {
	                        beforeChartFormat: '<h5>{chartTitle}</h5>' +
	                            '<div>{chartSubtitle}</div>' +
	                            '<div>{chartLongdesc}</div>' +
	                            '<div>{viewTableButton}</div>'
	                    }
	                },
	                series: [{
	                    type: 'wordcloud',
	                    data,
	                    name: '해당 키워드 검색횟수'
	                }],
	                title: {
	                    text: '검색어 통계차트',
	                    align: 'left'
	                },
	                subtitle: {
	                    text: sessionStorage.getItem("nickname")+'님의 검색어 통계',
	                    align: 'left'
	                },
	                tooltip: {
	                    headerFormat: '<span style="font-size: 16px"><b>{point.key}</b></span><br>'
	                }
	            });

    	
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--




/**
 * 네비게이션 바에서 로그인기록 클릭시 실행될 함수
 */
function login_record_nav(userid){
	$("div#member_login_record").css("display","block");
	
	var fromDate = $("input#fromDate").val();
	var toDate = $("input#toDate").val();
	
  $.ajax({
    url:getContextPath()+"/admin/member/detail/loginRecordList.do", 
    data:{"userid": userid,
	 	   "fromDate": fromDate,
	 	   "toDate": toDate},
    type:"get",
    dataType:"json",
    success:function(json){ //작성게시물을 가져오는데 성공했다면

    	var html = "<table>" +
					"<thead>" +
						"<tr>" +
				    			"<th>로그인날짜</th>" +
				    			"<th>IP</th>" +
							"</tr>"+
						"</thead>"+
					"<tbody>";
	
		for(var i=0; i<json.length; i++) {
		var obj;
		
		html += "<tr>" +
				"<td>"+json[i].login_date+"</td>" +
				"<td>"+json[i].login_ip+"</td>" +
			"</tr>"; 
		
		}// end of for------------------------------
		
		
		html +="</tbody>" +
		"</table>";
		
		$("div#login_record_chart_area").html(html);
    	
    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--




/**
 * 네비게이션 바에서 작성게시물 클릭시 실행될 함수
 */
function write_board_list_nav(nickname){
  alert("작성게시물보여주기 메소드 호출");
  $("div#member_write_board_list").css("display","block");
  $.ajax({
    url:getContextPath()+"/작성게시물select할빽단url.do", 
    data:{"nickname": nickname},
    type:"get",
    dataType:"json",
    success:function(json){ //작성게시물을 가져오는데 성공했다면

    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--



/**
 * 네비게이션 바에서 신고내역 클릭시 실행될 함수
 */
function report_nav(nickname){
  alert("신고내역 보여주기 메소드 호출");
  $("div#member_report_list").css("display","block");

  $.ajax({
    url:getContextPath()+"/신고내역select할빽단url.do", 
    data:{"nickname": nickname},
    type:"get",
    dataType:"json",
    success:function(json){ //신고내역을 가져오는데 성공했다면

    },//end of success
    //success 대신 error가 발생하면 실행될 코드 
    error: function(request,status,error){
      alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
    }
  });//end of $.ajax({})---
}//end of method--









