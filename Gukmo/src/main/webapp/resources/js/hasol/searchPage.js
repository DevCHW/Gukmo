// js파일에서 contextPath를 알아내는 함수
function getContextPath() {
	let hostIndex = location.href.indexOf(location.host) + location.host.length;
	let contextPath = location.href.substring(hostIndex, location.href.indexOf(
			'/', hostIndex + 1));
	return contextPath;
}

$(document).ready(function() {

	
	//////// 내용물 변경을 위한 값!!! ////////
	 let category = $(".detail_category").text();
	 let size = $("#length").text();
	 
	 for (var i = 0; i < size; i++) {
		 $("#show_content").removeClass();
		 let show_content = $('#hidden_content'+i+'').text();
		 $('#show_content'+i+'').addClass("show_content"+i);
		 $('#hidden_content'+i+'').hide();
		 
		 if(category != '교육과정' || category != '국비학원'){
			 $('.show_content'+i+'').text(show_content);
		 }
	    }
	 $("#length").hide();	 
	 ////////내용물 변경을 위한 값!!! ////////


	 
	// 검색어 엔터 이벤트
	$("input#searchWord").keydown(function(e) {
		if (e.keyCode == 13) {
			goSearch();
		}
	});

	// 게시글 밑 해시태그 클릭 시 input
	$("a.search_hashtag").click(function(e) {
		const $target = $(e.target);
		let hashtag = $target.text().substr(1);
		$("input#searchWord").val(hashtag);
		$("input#hashtag").val(hashtag);

		goSearch();
		saveKeyword();
	});

});

// 검색
function goSearch() {
	const frm = document.searchFrm;
	frm.method = "GET";
	frm.action = getContextPath() + "/main_search.do"
	frm.submit();
}

function saveKeyword() {

	const data = {
		keyword : $("input#searchWord").val(),
		userid : '${sessionScope.user.userid}'
	}
	$.ajax({
		type : 'GET',
		url : '<%=ctxPath%>/saveKeyword.do',
		data : data,
		dataType : 'json',
		async : false,
		success : function(data) {
		},
		error : function(xhr, status, error) {
			alert("검색에 실패했습니다." + error);
		}
	});// end of ajax
}
