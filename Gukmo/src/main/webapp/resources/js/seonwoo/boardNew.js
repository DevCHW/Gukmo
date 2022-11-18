// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
  
  $("input#hashtag").keyup(e=>{
    if(e.keyCode == 13) { // hashtag에서 엔터를 쳤을 경우
      $(`<li>${$("input#hashtag").val()}<span>x</span></li>`).insertBefore("input#hashtag")
      $("input#hashtag").val("");
    }
    if(e.keyCode == 8 || e.keyCode == 43){  //hashtag 에서 백스페이스나 DElETE 키를 누른 경우
   }
  });
    
  
	// 등록 버튼을 클릭했을시
    $("button#btn_write").click(function() {
    	
    	alert('설마');
    	
    	// 카테고리 유효성 검사
    	const detail_category = $("select#detail_category").val();
    	if(detail_category == "") {
    		alert("카테고리를 선택하세요!!");
			return;
    	}
    	
    	// 글제목 유효성 검사
		const subject = $("input#subject").val().trim();
		if(subject == "") {
			alert("글제목을 입력하세요!!");
			return;
		}
		
		// 글내용 유효성 검사(스마트 에디터 사용 안 할 경우)
		const content = $("textarea#content").val().trim();
		if(content == "") {
			alert("글내용을 입력하세요!!");
			return;
		}
		
//		// 글내용 유효성 검사(스마트 에디터용)
//		var contentval = $("textarea#content").val();
//		contentval = contentval.replace(/&nbsp;/gi, "");
//	
//	    contentval = contentval.substring(contentval.indexOf("<p>")+3);   // "             </p>"
//	    contentval = contentval.substring(0, contentval.indexOf("</p>")); // "             "
//	            
//	    if(contentval.trim().length == 0) {
//	  	  alert("글내용을 입력하세요!!");
//	      return;
//	    }
	    
	    // 폼을 전송
	    const frm = document.writerFrm;
	    frm.method = "POST";
	    frm.action = getContextPath()+"/newEnd.do";
	    frm.submit();
	});
    
});