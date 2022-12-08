// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function (){

	 $.ajax({
	        url:getContextPath()+"/admin/chart/newBoardCnt.do",
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
	        	        text: '일자별 새 게시물 건수'
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
	
	
	
	
}); // end of ready ------------------
