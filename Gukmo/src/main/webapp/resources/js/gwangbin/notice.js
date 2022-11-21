// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


//전역변수
var obj = [];
var obj2 = [];



$(document).ready(function(){
	
	$("div#edit_delete_list").hide();
	$("div#comment_comment_window").css("display", "none");
	
	
	
	
	
	
	
	
	$("div#more_list").click(function(){
		$("div#edit_delete_list").toggle();		
	});
	
	$('span#like_button').click(function(){
		$('svg#heart').css({"color": "blue", "font-size": "20px"});
	});
	
	
	
	$("button#comment_comment").click(function(){
		$("div#comment_comment_window").toggle();
		nhn.husky.EZCreator.createInIFrame({
	        oAppRef: obj2,
	        elPlaceHolder: "comment",
	        sSkinURI: getContextPath()+"/resources/smarteditor/SmartEditor2Skin.html",
	        htParams : {
	        	fOnAppLoad: function() {
	        		$("ifream").css("width", "100%").css("height", "399px");
	        	},
	            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseToolbar : true,            
	            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseVerticalResizer : true,    
	            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseModeChanger : true,
	            
	           
	        }
	    
	    
	    });
	
	});
	
	
	
	
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "content",
        sSkinURI: getContextPath()+"/resources/smarteditor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
        }
    });
    
    
  //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj2,
        elPlaceHolder: "comment",
        sSkinURI: getContextPath()+"/resources/smarteditor/SmartEditor2Skin.html",
        htParams : {
        	fOnAppLoad: function() {
        		$("ifream").css("width", "100%").css("height", "399px");
        	},
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
            
           
        }
    
    
    });
    
    
    ///////////////// 본문 스마트 에디터 시작 ////////////////////////////////
    
	// 글쓰기 버튼
	$("button#btnWrite").click(function(){
		
		
		// id가 content인 textarea에 에디터에서 대입
        obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	    
		
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
		
	
	
	    var contentval = $("textarea#content").val();
	        
	 // 글내용 유효성 검사 하기 
     // alert(contentval); // content에  공백만 여러개를 입력하여 쓰기할 경우 알아보는것.
     // <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p> 이라고 나온다.
     
        contentval = contentval.replace(/&nbsp;/gi, ""); // 공백을 "" 으로 변환
     /*    
	         대상문자열.replace(/찾을 문자열/gi, "변경할 문자열");
	     ==> 여기서 꼭 알아야 될 점은 나누기(/)표시안에 넣는 찾을 문자열의 따옴표는 없어야 한다는 점입니다. 
	                  그리고 뒤의 gi는 다음을 의미합니다.
	
	 	 g : 전체 모든 문자열을 변경 global
	 	 i : 영문 대소문자를 무시, 모두 일치하는 패턴 검색 ignore
	*/ 
  //   alert(contentval);
  //   <p>             </p>
     
       contentval = contentval.substring(contentval.indexOf("<p>")+3);   // "             </p>"
       contentval = contentval.substring(0, contentval.indexOf("</p>")); // "             "
              
       if(contentval.trim().length == 0) {
    	   alert("글내용을 입력하세요!!");
           return;
       }
	 
	
	   // 글암호 유효성 검사
	   const pw = $("input#pw").val().trim();
	   if(pw == "") {
		  alert("글암호를 입력하세요!!");
		  return;
	   }
		
	   // 폼(form)을 전송(submit)
	   const frm = document.addFrm;
	   frm.method = "POST";
	   frm.action = getContextPath()+"/addEnd.action";
	   frm.submit();
	});
	
	
	///////////////// 본문 스마트 에디터 시작 ////////////////////////////////
	
	
	
	
	
	
 ///////////////// 댓글 스마트 에디터 시작 ////////////////////////////////
    
	// 글쓰기 버튼
	$("button#btnWrite").click(function(){
		
		
		// id가 content인 textarea에 에디터에서 대입
        obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	    
		
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
		
	
	
	    var contentval = $("textarea#content").val();
	        
	 // 글내용 유효성 검사 하기 
     // alert(contentval); // content에  공백만 여러개를 입력하여 쓰기할 경우 알아보는것.
     // <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p> 이라고 나온다.
     
        contentval = contentval.replace(/&nbsp;/gi, ""); // 공백을 "" 으로 변환
     /*    
	         대상문자열.replace(/찾을 문자열/gi, "변경할 문자열");
	     ==> 여기서 꼭 알아야 될 점은 나누기(/)표시안에 넣는 찾을 문자열의 따옴표는 없어야 한다는 점입니다. 
	                  그리고 뒤의 gi는 다음을 의미합니다.
	
	 	 g : 전체 모든 문자열을 변경 global
	 	 i : 영문 대소문자를 무시, 모두 일치하는 패턴 검색 ignore
	*/ 
  //   alert(contentval);
  //   <p>             </p>
     
       contentval = contentval.substring(contentval.indexOf("<p>")+3);   // "             </p>"
       contentval = contentval.substring(0, contentval.indexOf("</p>")); // "             "
              
       if(contentval.trim().length == 0) {
    	   alert("글내용을 입력하세요!!");
           return;
       }
	 
	
	   // 글암호 유효성 검사
	   const pw = $("input#pw").val().trim();
	   if(pw == "") {
		  alert("글암호를 입력하세요!!");
		  return;
	   }
		
	   // 폼(form)을 전송(submit)
	   const frm = document.addFrm;
	   frm.method = "POST";
	   frm.action = getContextPath()+"/addEnd.action";
	   frm.submit();
	});
	
	
	///////////////// 댓글 스마트 에디터 시작 ////////////////////////////////
	
	
});