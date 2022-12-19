// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}



// Fucntion Declaration --------------------------------

// 배너 
/*
function func_openBanner() {
	
	$.ajax({
		url: getContextPath()+"/open_banner.do",
		dataType: "json",
		success: function(json) {
			
			let html = "";
			
			// Indicators
			html += "<ul class='carousel-indicators'>" +
						"<li data-target='#demo' data-slide-to='0' class='active'></li>";
			
			$.each(json, function(index, item){
				html += "<li data-target='#demo' data-slide-to='"+(index+1)+"'></li>";
			});
			
			html +="</ul>";
			
			
			// 슬라이드 배너
			html += "<div class='carousel-inner'>";
			
			$.each(json, function(index, item){
				html+= "<div class='carousel-item'>"+
							"<a href="+item.url+">" +
					   			"<img src='getContextPath()/resources/images/main/"+item.file_name+"' alt='banner_"+item.advertisement_num+"'>"+
					   		"</a>"+
					   "</div>";
				
				if(item.advertisement_num == "1"){
					$('div.carousel-item').addClass('active');
				}
			});
			
			html += "</div>";
			
			// Left and right controls			
			html += "<a class='carousel-control-prev' href='#demo' data-slide='prev'>" +
						"<span class='carousel-control-prev-icon'></span>"+
					"</a>"+
					"<a class='carousel-control-next' href='#demo' data-slide='next'>"+
						"<span class='carousel-control-next-icon'></span>"+
					"</a>";
			
			$("div#demo").html(html);
			
		},
		error: function(request, status, error){
			   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
	});
}

*/





