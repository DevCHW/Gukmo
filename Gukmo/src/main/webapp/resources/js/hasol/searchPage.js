// js파일에서 contextPath를 알아내는 함수
function getContextPath() {
	let hostIndex = location.href.indexOf(location.host) + location.host.length;
	let contextPath = location.href.substring(hostIndex, location.href.indexOf(
			'/', hostIndex + 1));
	return contextPath;
}

$(document).ready(function() {

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
	 
	// replace_content();

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

/**
 * argBody 안의 내용 중 지정 문자열 삭제 argBody : 삭제본문 ( ex : 가나다
 * 
 * <pre style="width:100px">
 * 안녕하세요
 * </pre> ) argStartSection : 삭제 시작 문자 ( ex : <pre ) argEndSection : 삭제 끝 문자 (
 * ex : ;"> ) argRemoveSection : 별도 replace 문자 ( ex :
 * 
 * </pre> )
 */

/*
 * function replace_content(){
 * 
 * var removeStyleAndImage = function(argBody, argStartSection, argEndSection,
 * argRemoveSection){
 * 
 * var bodyString = argBody;
 * 
 * var sectionChk = bodyString.match(new RegExp(argStartSection,'g'));
 * 
 * if(sectionChk != null){ for(var i=0; i < sectionChk.length; i++){ var tmpImg =
 * bodyString.substring(bodyString.indexOf(argStartSection),
 * (bodyString.indexOf(argEndSection)+(argEndSection.length))); bodyString =
 * bodyString.replace(tmpImg, '').replace(/<br>/gi, '').replace(/&nbsp;/gi, '
 * ').replace(/<p>/gi, '').replace(/<\/p>/gi, ' ').replace(new
 * RegExp(argRemoveSection,'gi'), ''); } }
 * 
 * return bodyString;
 *  }
 * 
 * each(function( index, element){} const contentval =
 * $("#hidden_content1").text() let content = "";
 * 
 * content = removeStyleAndImage(contentval, '<img src=', '">', ''); // 스마트에디터
 * 내부의 이미지 제거 content = removeStyleAndImage(contentval, '<pre', ';">', '</pre>'); //
 * 스마트에디터 내부의 스타일 제거 content = removeStyleAndImage(contentval, '<span', ';">', '</span>'); //
 * 스마트에디터 내부의 스타일 제거
 * 
 * console.log(content); $("input#replace_content").val(content);
 * 
 * }//end of method-----
 */