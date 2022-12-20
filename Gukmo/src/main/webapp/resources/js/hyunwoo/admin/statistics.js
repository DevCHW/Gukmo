// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';
const today = new Date();   

const year = today.getFullYear(); // 년도
const month = today.getMonth() + 1;  // 월
const date = today.getDate();  // 날짜



$("document").ready(function(){
	//데이터 테이블 적용
	$("#dataTable-refer").DataTable({});
	
	//브라우저 차트
	let browserData = getBrowserData();
	getBrowserChart(browserData);
	
	//회원가입 수 차트
	let joinMemberChartData = getJoinMemberChartData();
	getJoinMemberChart(joinMemberChartData);
	
	//일반회원/교육기관회원 비중 차트
	let memberRateChartData = getMemberRateData();
	getMemberRateChart(memberRateChartData);
	
	//지난주 카테고리별 작성게시물건수 구하기
	let lastWeekCntCommunityData = getLastWeekCntCommunityData();
	getLastWeekCntCommunityChart(lastWeekCntCommunityData);
	
});//end of $("document").ready(function(){})--



// == function Declaration == //
function number_format(number, decimals, dec_point, thousands_sep) {
  // *     example: number_format(1234.56, 2, ',', ' ');
  // *     return: '1 234,56'
  number = (number + '').replace(',', '').replace(' ', '');
  var n = !isFinite(+number) ? 0 : +number,
    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
    s = '',
    toFixedFix = function(n, prec) {
      var k = Math.pow(10, prec);
      return '' + Math.round(n * k) / k;
    };
  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
  if (s[0].length > 3) {
    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
  }
  if ((s[1] || '').length < prec) {
    s[1] = s[1] || '';
    s[1] += new Array(prec - s[1].length + 1).join('0');
  }
  return s.join(dec);
}




/**
 * AreaChart 얻기
 * @param data
 */
function getJoinMemberChart(data){
	let today = new Date();
	let lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0).getDate();

	let dateLabel = new Array();
	for(let i=1;i<lastDate+1;i++){
		dateLabel.push(i);
	}
	console.log(dateLabel);
	// Area Chart Example
	var ctx = document.getElementById("joinMemberChart");
	var myLineChart = new Chart(ctx, {
	  type: 'line',
	  data: {
	    labels: dateLabel,
	    datasets: [{
	      label: "가입자 수",
	      lineTension: 0.3,
	      backgroundColor: "rgba(78, 115, 223, 0.05)",
	      borderColor: "rgba(78, 115, 223, 1)",
	      pointRadius: 3,
	      pointBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointBorderColor: "rgba(78, 115, 223, 1)",
	      pointHoverRadius: 3,
	      pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointHoverBorderColor: "rgba(78, 115, 223, 1)",
	      pointHitRadius: 10,
	      pointBorderWidth: 2,
	      data:data,
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    layout: {
	      padding: {
	        left: 10,
	        right: 25,
	        top: 25,
	        bottom: 0
	      }
	    },
	    scales: {
	      xAxes: [{
	        time: {
	          unit: 'date'
	        },
	        gridLines: {
	          display: false,
	          drawBorder: false
	        },
	        ticks: {
	          maxTicksLimit: 7
	        }
	      }],
	      yAxes: [{
	        ticks: {
	          maxTicksLimit: 5,
	          padding: 10,
	          // Include a dollar sign in the ticks
	          callback: function(value, index, values) {
	            return number_format(value)+ '명';
	          }
	        },
	        gridLines: {
	          color: "rgb(234, 236, 244)",
	          zeroLineColor: "rgb(234, 236, 244)",
	          drawBorder: false,
	          borderDash: [2],
	          zeroLineBorderDash: [2]
	        }
	      }],
	    },
	    legend: {
	      display: false
	    },
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      titleMarginBottom: 10,
	      titleFontColor: '#6e707e',
	      titleFontSize: 14,
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      intersect: false,
	      mode: 'index',
	      caretPadding: 10,
	      callbacks: {
	        label: function(tooltipItem, chart) {
	          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
	          return datasetLabel + ' : ' + number_format(tooltipItem.yLabel) + '명';
	        }
	      }
	    }
	  }
	});
}//end of method----



/**
 * 브라우저 차트 얻기
 */
function getBrowserChart(data){
	var ctx = document.getElementById("BrowserChart");
	var myPieChart = new Chart(ctx, {
	  type: 'doughnut',
	  data: {
	    labels: ["Chrome", "Edge", "Firefox", "IE", "Safari","Opera"],
	    datasets: [{
	      data: data,
	      backgroundColor: ['#1cc88a', '#4e73df', '#36b9cc', '#f6c23e', '#e74a3b','#858796'],
	      hoverBackgroundColor: ['#17a673', '#2e59d9', '#2c9faf', '#f4b619' ,'#e02d1b'],
	      hoverBorderColor: "rgba(234, 236, 244, 1)",
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      caretPadding: 10,
	    },
	    legend: {
	      display: false
	    },
	    cutoutPercentage: 80,
	  },
	});
}//end of method--




/**
 * 일반회원/교육기관회원 비중 차트얻기
 */
function getMemberRateChart(data){
	var ctx = document.getElementById("memberRateChart");
	var myPieChart = new Chart(ctx, {
	  type: 'doughnut',
	  data: {
	    labels: ["일반회원", "교육기관회원"],
	    datasets: [{
	      data: data,
	      backgroundColor: ['#e74a3b', '#f6c23e'],
	      hoverBackgroundColor: ['#e02d1b', '#f4b619'],
	      hoverBorderColor: "rgba(234, 236, 244, 1)",
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      caretPadding: 10,
	    },
	    legend: {
	      display: false
	    },
	    cutoutPercentage: 80,
	  },
	});
}//end of method--

/**
 * 지난주 작성게시물건수 데이터 얻기
 */
function getLastWeekCntCommunityData(){
	let cntData = [];
	$.ajax({
		type : 'get',
		url:getContextPath()+"/admin/getLastWeekCntCommunityData.do",
		async:false,
		dataType : 'json',
		success : function(res){
			cntData.push(...res);
		},//end of success
		error: function(xhr, status, error){
			alert(status+":"+error);
		}
	  });//end of ajax
	console.log("cntData : " + cntData);
	return cntData;
}//end of method--



function getLastWeekCntCommunityChart(data){
	var ctx = document.getElementById("lastWeekCntCommunityChart");
	var lastWeekCntCommunityChart = new Chart(ctx, {
		  type: 'bar',
		  data: {
		    labels: ["자유게시판", "Q&A", "스터디", "취미모임", "수강/취업후기"],
		    datasets: [{
		      label: "Revenue",
		      backgroundColor: "#4e73df",
		      hoverBackgroundColor: "#2e59d9",
		      borderColor: "#4e73df",
		      data: data,
		    }],
		  },
		  options: {
		    maintainAspectRatio: false,
		    layout: {
		      padding: {
		        left: 10,
		        right: 25,
		        top: 25,
		        bottom: 0
		      }
		    },
		    scales: {
		      xAxes: [{
		        time: {
		          unit: 'month'
		        },
		        gridLines: {
		          display: false,
		          drawBorder: false
		        },
		        ticks: {
		          maxTicksLimit: 6
		        },
		        maxBarThickness: 25,
		      }],
		      yAxes: [{
		        ticks: {
		          min: 0,
		          max: Math.max.apply(null, data),
		          maxTicksLimit: 5,
		          padding: 10,
		          // Include a dollar sign in the ticks
		          callback: function(value, index, values) {
		            return number_format(value)+"건";
		          }
		        },
		        gridLines: {
		          color: "rgb(234, 236, 244)",
		          zeroLineColor: "rgb(234, 236, 244)",
		          drawBorder: false,
		          borderDash: [2],
		          zeroLineBorderDash: [2]
		        }
		      }],
		    },
		    legend: {
		      display: false
		    },
		    tooltips: {
		      titleMarginBottom: 10,
		      titleFontColor: '#6e707e',
		      titleFontSize: 14,
		      backgroundColor: "rgb(255,255,255)",
		      bodyFontColor: "#858796",
		      borderColor: '#dddfeb',
		      borderWidth: 1,
		      xPadding: 15,
		      yPadding: 15,
		      displayColors: false,
		      caretPadding: 10,
		      callbacks: {
		        label: function(tooltipItem, chart) {
		          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
		          return datasetLabel + ':' + number_format(tooltipItem.yLabel) + '건';
		        }
		      }
		    },
		  }
		});
}//end of method ---


/**
 * 브라우저 비중 데이터 얻기
 */
function getBrowserData(){
	let browserData = [];
	$.ajax({
		type : 'get',
		url:getContextPath()+"/admin/getBrowserData.do",
		async:false,
		dataType : 'json',
		success : function(res){
			browserData.push(...res);
		},//end of success
		error: function(xhr, status, error){
			alert(status+":"+error);
		}
	  });//end of ajax
	return browserData;
}//end of method--




/**
 * 브라우저 비중 데이터 얻기
 */
function getMemberRateData(){
	let memberRateData = [];
	$.ajax({
		type : 'get',
		url:getContextPath()+"/admin/getMemberRateData.do",
		async:false,
		dataType : 'json',
		success : function(res){
			memberRateData.push(...res);
		},//end of success
		error: function(xhr, status, error){
			alert(status+":"+error);
		}
	  });//end of ajax
	return memberRateData;
}//end of method--



/**
 * 이번달 일자별 회원가입 수 구하기
 */
function getJoinMemberChartData(){
	let joinMemberData = [];
	$.ajax({
		type : 'get',
		url:getContextPath()+"/admin/getJoinMemberData.do",
		async:false,
		dataType : 'json',
		success : function(res){
			joinMemberData.push(...res);
		},//end of success
		error: function(xhr, status, error){
			alert(status+":"+error);
		}
	  });//end of ajax
	console.log(joinMemberData);
	return joinMemberData;
}//end of method--


