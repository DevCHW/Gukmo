<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	String ctxPath = request.getContextPath();
%>   

<!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/boardList.css" />

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/boardNew.js" ></script>


<script>
 <%-- $(document).ready(function() {
	//	==== 스마트 에디터 구현 시작 ==== //
	//전역변수
    var obj = [];
    
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "content",
        sSkinURI: "<%=ctxPath%>/resources/smarteditor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
        }
    });
    // ==== 스마트 에디터 구현 끝 ==== //
	    
	// 등록 버튼을 클릭했을시
	    $("button#btn_write").click(function() {
	    	
	    	// ==== 스마트 에디터 구현 시작 ==== //
	    	// id가 content인 textarea에 에디터에서 대입
	    	obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	    	
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
			
			// 글내용 유효성 검사(스마트 에디터용)
			var contentval = $("textarea#content").val();
			contentval = contentval.replace(/&nbsp;/gi, "");
		
		    contentval = contentval.substring(contentval.indexOf("<p>")+3);   // "             </p>"
		    contentval = contentval.substring(0, contentval.indexOf("</p>")); // "             "
		            
		    if(contentval.trim().length == 0) {
		  	  alert("글내용을 입력하세요!!");
		      return;
		    }
		    
		    // 폼을 전송
		    const frm = document.writerFrm;
		    frm.method = "POST";
		    frm.action = "<%=ctxPath%>/newEnd.do";
		    frm.submit();
	    }); 
	       
	 
	 
	 
 }); --%>

</script>

  <div class="container">
    <!-- 커뮤니티 작성 폼 시작 -->
    <form action="communityNewEnd" name="writerFrm" class="d-flex flex-column" enctype="multipart/form-data">
	  
	  <!-- 등록용 닉네임 -->
	  <input type="text" name="nickname" value="${sessionScope.user.nickname}" />
	  
	  
	  
      <!-- category -->
      <label for="detail_category" class="community_label">구분</label>
      <select name="detail_category" id="detail_category" class="community_input pl-2 border rounded">
        <option value="">구분을 선택해주세요</option>
        <option>스터디</option>
        <option>자유게시판</option>
        <option>Q&A</option>
        <option>정보공유</option>
        <option>수강/취업 성공 후기</option>
      </select>


      <!-- subject -->
      <label for="subject" class="community_label mt-3">제목</label>
      <input type="text" id="subject" name="subject" class="community_input border rounded pl-2" placeholder="제목을 입력하세요">



      <!-- hashtag -->
      <label for="hashtag" class="community_label mt-3">태그</label>
      <div id="hashtag_box" class="border rounded pl-2">
        <ul id="hashtag_list" class="d-flex align-items-center">
          <input type="text" id="hashtag" name="hashtag" class="border-0" placeholder="태그를 설정하세요(최대 5개)">
        </ul>
      </div>

      <!-- content -->
      <label for="content" class="community_label mt-3">본문</label>
      <textarea name="content" id="content" class="px-2 py-2 border rounded" cols="30" rows="15" placeholder="추후 네이버스마트에디터 구현예정,input,select,textarea태그에 박스쉐도우 넣기"></textarea>
    </form>
    <!-- 커뮤니티 작성 폼 끝 -->

    <!-- 수정일 경우에는 등록 대신 수정버튼 태그라이브러리로 구현예정 -->
    <div id="btn_wrapper" class="d-flex justify-content-end mt-3">
      <button id="btn_write" type="button" class="btn border rounded">등록</button>
      <button id="btn_cancle" type="button" class="btn border rounded ml-3">취소</button>
    </div>
  </div>