// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function (){
	
	
	let url = window.document.location.href;
    url = url.substr(27);
	    
    if(url.indexOf("?") != -1){
      url = url.substr(0,url.indexOf("?"));
    }
    
    
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
         //$('input#fromDate').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
   		 $('input#fromDate').datepicker('setDate', '-1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
   		
	
         //To의 초기값을 3일후로 설정
         $('input#toDate').datepicker('setDate', '-1D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
         
         // datepicker 날짜 범위 제한
         $('input#fromDate, input#toDate').datepicker("option",'minDate', '-2Y'); 
   		 $('input#fromDate, input#toDate').datepicker("option",'maxDate', '-1D');
   		 
   		 $('input#toDate').datepicker("option",'onClose', function (selectedDate) {
   			if(selectedDate.length==10)
              $("#fromDate").datepicker("option", "maxDate", selectedDate);
          	else
          		$("#fromDate").datepicker("option", "maxDate", max);
          });
         $('#fromDate').datepicker("option", "onClose", function (selectedDate) {
         	if(selectedDate.length==10)
                 $("#toDate").datepicker("option", "minDate", selectedDate);
             else
                 $("#toDate").datepicker("option", "minDate", min);
         });
   
         
         goSearch();
	
         
         
         // 검색 버튼 눌렀을 시
		$("button#search").click(function() {
			goSearch();
		});
		
		
		function goSearch() {
			
			var fromDate = $("input#fromDate").val();
			var toDate = $("input#toDate").val();
			
			$.ajax({
			        url:getContextPath()+"/admin/chart/newBoardCnt.do",
			        data:{"fromDate": fromDate,
			        	  "toDate": toDate},
			        dataType:"JSON",
			        success:function(json){
			        	
			            var resultArr = [];
			            
			            for(var i=0; i<json.length; i++) {
			                var obj=[];
			                
			                obj.push(json[i].write_date);
			                obj.push(Number(json[i].cnt));
			                
			                resultArr.push(obj); // 배열속에 객체를 넣기
			            }// end of for------------------------------
			            
			        	Highcharts.chart('chart_container', {
			        	    chart: {
			        	        type: 'column'
			        	    },
			        	    title: {
			        	        text: '일자별 게시물 건수'
			        	    },
			        	    subtitle: {
			        	        text: 'Source: <a href="http://localhost:9090/board/admin/chart/newBoardCnt.do" target="_blank">Board</a>'
			        	    },
			        	    xAxis: {
			        	        type: 'category',
			        	        labels: {
			        	            rotation: -45,
			        	            style: {
			        	                fontSize: '13px',
			        	                fontFamily: 'Verdana, sans-serif'
			        	            }
			        	        }
			        	    },
			        	    yAxis: {
			        	        min: 0,
			        	        title: {
			        	            text: '게시물 (건)'
			        	        }
			        	    },
			        	    legend: {
			        	        enabled: false
			        	    },
			        	    tooltip: {
			        	        pointFormat: '게시물: <b>{point.y} 건</b>'
			        	    },
			        	    series: [{
			        	        name: '게시물 건수',
			        	        data: resultArr,
			        	        dataLabels: {
			        	            enabled: true,
			        	            rotation: -90,
			        	            color: '#FFFFFF',
			        	            align: 'right',
			        	            format: '{point.y}', // one decimal
			        	            y: 10, // 10 pixels down from the top
			        	            style: {
			        	                fontSize: '13px',
			        	                fontFamily: 'Verdana, sans-serif'
			        	            }
			        	        }
			        	    }]
			        	});
			        },
			        error: function(request, status, error){
			        	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				}
			 }); 
			
		}
	
		
		
		
}); // end of ready ------------------


