// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function(){
	
	$("div#big_location span").click(e=>{
	  const target = $(e.currentTarget);
	  const big_location = target.text();
	  
	  let html = getHtmlWithMyLocation(big_location);
	  
	  $("div#small_location2").html(html);
	  $("div#location2").html(`<i class="fa-solid fa-location-dot mr-2"></i>
	  						   ${big_location}`);
	  $("div#location3").html(`<i class="fa-solid fa-location-dot mr-2"></i>
		 					   <span>지역 소분류</span>`);
	});
	
	
	$(document).on("click", "div#small_location2 span", function(e){
	  const target = $(e.currentTarget);
	  const small_location = target.text();
	  $("div#location3").html(`<i class="fa-solid fa-location-dot mr-2"></i>
		   					  ${small_location}`);
	});
	
	
  
    // select 값 잡기
    const urlParams = new URL(location.href).searchParams;
    const detail_category = urlParams.get('detailC');
    $("#detail_category").val(detail_category).attr("selected","selected");
	
	//	==== 스마트 에디터 구현 시작 ==== //
	//전역변수
    var obj = [];
    
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
    // ==== 스마트 에디터 구현 끝 ==== //
	
    
    // ==== 해시태그 구현 시작 ==== //
      let hashtag = {};
      let counter = 0;

      // 태그를 추가한다.
      function addTag(value) {
    	hashtag[counter] = value; // 태그를 Object 안에 추가
        counter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 가 된다.
      }

      // 최종적으로 서버에 넘길때 tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
      function marginTag() {
        return Object.values(hashtag).filter(function (word) {
            return word !== "";
          });
      }

      
      $("input#hashtag").on("keyup", function (e) {
          let self = $(this);
          

          if(e.keyCode == 8 && $("input#hashtag").val() == ""){	//백스페이스를 눌렀을 때,인풋태그 값이 채워져있지 않다면 해시태그 지워주기
        	if($("li.tag-item").text() != ""){	//써놓은 해시태그가 있다면
        		let index = $("input#hashtag").prev().children("span.btn_hashtag_delete").attr("idx");
                hashtag[index] = "";
        		$("input#hashtag").prev().remove();
        		return;
        	} 
          }
          
          
          // input 에 focus 되있을 때 엔터 및 스페이스바 입력시 구동
          if (e.key === "Enter" || e.keyCode == 32) {

            let tagValue = self.val().trim(); // 값 가져오기
            
            //해시태그는 5개 이상 추가 불가능 합니다.
            if($("li.tag-item").length == 5){
            	alert("태그는 5개 이상 추가 불가합니다.");
            	//해시태그 쓴 값 비우기
            	$("input#hashtag").val("");
            	return;
            }
            
            // 값이 없으면 동작 안합니다.
            if (tagValue !== "") {

              // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
              let result = Object.values(hashtag).filter(function (word) {
                  return word === tagValue;
                });

              // 해시태그가 중복되었는지 확인
              if (result.length == 0) { 
                  $("<li class='d-flex align-items-center flex-nowrap mr-2 tag-item'>#"+tagValue+"<span class='btn_hashtag_delete mx-2' style='cursor:pointer; color:darkgray;' idx='"+counter+"'><i class='fa-solid fa-xmark'></i></span></li>").insertBefore("input#hashtag");
                  addTag(tagValue);
                  self.val("");
              } else {
                alert("태그값이 중복됩니다.");
              }
            }
            e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
          }
          
        });

      // 삭제 버튼
      // 삭제 버튼은 비동기적 생성이므로 document 최초 생성시가 아닌 검색을 통해 이벤트를 구현시킨다.
      $(document).on("click", ".btn_hashtag_delete", function (e) {
          let index = $(this).attr("idx");
          hashtag[index] = "";
          $(this).parent().remove();
        });
    // ==== 해시태그 구현 끝 ==== // 
      
      
      
    //학원이미지사진 선택버튼 클릭시
    $("button#btn_academy_image").click(function(){
      $("input#academy_image").trigger("click");
    });
    
    
  //이미지 업로드시
    $("input#academy_image").change(function(e){
  	  let files = e.target.files;
        let filesArr = Array.prototype.slice.call(files);

        let regExp = /(.*?)\/(jpg|jpeg|png|bmp)$/;

        filesArr.forEach(function(f) {
            if (!f.type.match(regExp)) {
                alert("확장자는 이미지 확장자만 가능합니다.");
                return;
            }

            sel_file = f;

            let reader = new FileReader();
            reader.onload = function(e) {
            	$("img#academy_image_preview").css("display","");
                $("img#profile_img").attr("src", e.target.result);
                $("img.dropbtn").attr("src", e.target.result);
            }
            reader.readAsDataURL(f);
       });
       
    });//end of Event --
    
    
    

  
	// 등록 버튼을 클릭했을시
    $("button#btn_write").click(function() {
    	
        var value = marginTag(); // return array
        value = value.join(',');
        $("#str_hashTag").val(value);
         //console.log(value);
         //console.log(typeof(value));
        
    	// ==== 스마트 에디터 구현 시작 ==== //
    	// id가 content인 textarea에 에디터에서 대입
    	obj.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
    	
    	// 카테고리 유효성 검사
    	
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
	    frm.action = getContextPath()+"/community/newEnd.do";
	    frm.submit();
	});
    
});// end of document




//Function Declaration





function getHtmlWithMyLocation(Big_location){
	let html="";
	switch (Big_location) {
	case "서울":
		html = "<span class='dropdown-item'>강남구</span>"+
			   "<span class='dropdown-item'>강동구</span>"+
			   "<span class='dropdown-item'>강북구</span>"+
			   "<span class='dropdown-item'>강서구</span>"+
			   "<span class='dropdown-item'>관악구</span>"+
			   "<span class='dropdown-item'>광진구</span>"+
			   "<span class='dropdown-item'>구로구</span>"+
			   "<span class='dropdown-item'>노원구</span>"+
			   "<span class='dropdown-item'>도봉구</span>"+
			   "<span class='dropdown-item'>동대문구</span>"+
			   "<span class='dropdown-item'>동작구</span>"+
			   "<span class='dropdown-item'>마포구</span>"+
			   "<span class='dropdown-item'>서대문구</span>"+
			   "<span class='dropdown-item'>서초구</span>"+
			   "<span class='dropdown-item'>성동구</span>"+
			   "<span class='dropdown-item'>성북구</span>"+
			   "<span class='dropdown-item'>송파구</span>"+
			   "<span class='dropdown-item'>양천구</span>"+
			   "<span class='dropdown-item'>영등포구</span>"+
			   "<span class='dropdown-item'>용산구</span>"+
			   "<span class='dropdown-item'>은평구</span>"+
			   "<span class='dropdown-item'>종로구</span>"+
			   "<span class='dropdown-item'>중구</span>"+
			   "<span class='dropdown-item'>중랑구</span>";
		break;
	case "부산":
		html = "<span class='dropdown-item'>강서구</span>"+
			   "<span class='dropdown-item'>금정구</span>"+
			   "<span class='dropdown-item'>기장군</span>"+
			   "<span class='dropdown-item'>남구</span>"+
			   "<span class='dropdown-item'>동구</span>"+
			   "<span class='dropdown-item'>동래구</span>"+
			   "<span class='dropdown-item'>부산진구</span>"+
			   "<span class='dropdown-item'>북구</span>"+
			   "<span class='dropdown-item'>사상구</span>"+
			   "<span class='dropdown-item'>사하구</span>"+
			   "<span class='dropdown-item'>서구</span>"+
			   "<span class='dropdown-item'>수영구</span>"+
			   "<span class='dropdown-item'>연제구</span>"+
			   "<span class='dropdown-item'>영도구</span>"+
			   "<span class='dropdown-item'>중구</span>"+
			   "<span class='dropdown-item'>해운대구</span>";
		break;
	case "대구":
		html = "<span class='dropdown-item'>남구</span>"+
			   "<span class='dropdown-item'>달서구</span>"+
			   "<span class='dropdown-item'>달성군</span>"+
			   "<span class='dropdown-item'>동구</span>"+
			   "<span class='dropdown-item'>북구</span>"+
			   "<span class='dropdown-item'>서구</span>"+
			   "<span class='dropdown-item'>수성구</span>"+
			   "<span class='dropdown-item'>중구</span>";
		break;
	case "인천":
		html = "<span class='dropdown-item'>강화군</span>"+
			   "<span class='dropdown-item'>계양군</span>"+
			   "<span class='dropdown-item'>남동구</span>"+
			   "<span class='dropdown-item'>동구</span>"+
			   "<span class='dropdown-item'>미추홀구</span>"+
			   "<span class='dropdown-item'>부평구</span>"+
			   "<span class='dropdown-item'>서구</span>"+
			   "<span class='dropdown-item'>연수구</span>"+
			   "<span class='dropdown-item'>옹진군</span>"+
			   "<span class='dropdown-item'>중구</span>";
		break;
	case "광주":
		html = "<span class='dropdown-item'>광산구</span>"+
			   "<span class='dropdown-item'>남구</span>"+
			   "<span class='dropdown-item'>동구</span>"+
			   "<span class='dropdown-item'>북구</span>"+
			   "<span class='dropdown-item'>서구</span>";
		break;
	case "대전":
		html = "<span class='dropdown-item'>대덕구</span>"+
			   "<span class='dropdown-item'>동구</span>"+
			   "<span class='dropdown-item'>서구</span>"+
			   "<span class='dropdown-item'>유성구</span>"+
			   "<span class='dropdown-item'>중구</span>";
		break;
	case "울산":
		html = "<span class='dropdown-item'>남구</span>"+
			   "<span class='dropdown-item'>동구</span>"+
			   "<span class='dropdown-item'>북구</span>"+
			   "<span class='dropdown-item'>울주군</span>"+
			   "<span class='dropdown-item'>중구</span>";
		break;
	case "세종":
		html = "<span class='dropdown-item'>세종</span>";
		break;
	case "경기":
		html = "<span class='dropdown-item'>가평군</span>"+
			   "<span class='dropdown-item'>고양시</span>"+
			   "<span class='dropdown-item'>고양시 덕양구</span>"+
			   "<span class='dropdown-item'>고양시 일산동구</span>"+
			   "<span class='dropdown-item'>고양시 일산서구</span>"+
			   "<span class='dropdown-item'>과천시</span>"+
			   "<span class='dropdown-item'>광명시</span>"+
			   "<span class='dropdown-item'>광주시</span>"+
			   "<span class='dropdown-item'>구리시</span>"+
			   "<span class='dropdown-item'>군포시</span>"+
			   "<span class='dropdown-item'>김포시</span>"+
			   "<span class='dropdown-item'>남양주시</span>"+
			   "<span class='dropdown-item'>동두천시</span>"+
			   "<span class='dropdown-item'>부천시</span>"+
			   "<span class='dropdown-item'>성남시</span>"+
			   "<span class='dropdown-item'>성남시 분당구</span>"+
			   "<span class='dropdown-item'>성남시 수정구</span>"+
			   "<span class='dropdown-item'>성남시 중원구</span>"+
			   "<span class='dropdown-item'>수원시</span>"+
			   "<span class='dropdown-item'>수원시 권선구</span>"+
			   "<span class='dropdown-item'>수원시 영통구</span>"+
			   "<span class='dropdown-item'>수원시 장안구</span>"+
			   "<span class='dropdown-item'>수원시 팔달구</span>"+
			   "<span class='dropdown-item'>시흥시</span>"+
			   "<span class='dropdown-item'>안산시</span>"+
			   "<span class='dropdown-item'>안산시 단원구</span>"+
			   "<span class='dropdown-item'>안산시 상록구</span>"+
			   "<span class='dropdown-item'>안성시</span>"+
			   "<span class='dropdown-item'>안양시</span>"+
			   "<span class='dropdown-item'>안양시 동안구</span>"+
			   "<span class='dropdown-item'>안양시 만안구</span>"+
			   "<span class='dropdown-item'>양주시</span>"+
			   "<span class='dropdown-item'>양평군</span>"+
			   "<span class='dropdown-item'>여주시</span>"+
			   "<span class='dropdown-item'>연천군</span>"+
			   "<span class='dropdown-item'>오산시</span>"+
			   "<span class='dropdown-item'>용인시</span>"+
			   "<span class='dropdown-item'>용인시 기흥구</span>"+
			   "<span class='dropdown-item'>용인시 수지구</span>"+
			   "<span class='dropdown-item'>용인시 처인구</span>"+
			   "<span class='dropdown-item'>의왕시</span>"+
			   "<span class='dropdown-item'>의정부시</span>"+
			   "<span class='dropdown-item'>이천시</span>"+
			   "<span class='dropdown-item'>파주시</span>"+
			   "<span class='dropdown-item'>평택시</span>"+
			   "<span class='dropdown-item'>포천군</span>"+
			   "<span class='dropdown-item'>포천시</span>"+
			   "<span class='dropdown-item'>하남시</span>"+
			   "<span class='dropdown-item'>화성시</span>";
		break;
	case "강원":
		html = "<span class='dropdown-item'>강릉시</span>"+
			   "<span class='dropdown-item'>고성군</span>"+
			   "<span class='dropdown-item'>동해시</span>"+
			   "<span class='dropdown-item'>삼척시</span>"+
			   "<span class='dropdown-item'>속초시</span>"+
			   "<span class='dropdown-item'>양구군</span>"+
			   "<span class='dropdown-item'>양양군</span>"+
			   "<span class='dropdown-item'>영월군</span>"+
			   "<span class='dropdown-item'>원주시</span>"+
			   "<span class='dropdown-item'>인제군</span>"+
			   "<span class='dropdown-item'>정선군</span>"+
			   "<span class='dropdown-item'>철원군</span>"+
			   "<span class='dropdown-item'>춘천시</span>"+
			   "<span class='dropdown-item'>태백시</span>"+
			   "<span class='dropdown-item'>평창군</span>"+
			   "<span class='dropdown-item'>흥천군</span>"+
			   "<span class='dropdown-item'>화천군</span>"+
			   "<span class='dropdown-item'>횡성군</span>";
		break;
	case "충북":
		html = "<span class='dropdown-item'>괴산군</span>"+
			   "<span class='dropdown-item'>단양군</span>"+
			   "<span class='dropdown-item'>보은군</span>"+
			   "<span class='dropdown-item'>영동군</span>"+
			   "<span class='dropdown-item'>옥천군</span>"+
			   "<span class='dropdown-item'>음성군</span>"+
			   "<span class='dropdown-item'>제천시</span>"+
			   "<span class='dropdown-item'>증평군</span>"+
			   "<span class='dropdown-item'>진천군</span>"+
			   "<span class='dropdown-item'>청원군</span>"+
			   "<span class='dropdown-item'>청주시</span>"+
			   "<span class='dropdown-item'>청주시 상당구</span>"+
			   "<span class='dropdown-item'>청주시 서원구</span>"+
			   "<span class='dropdown-item'>청주시 청원구</span>"+
			   "<span class='dropdown-item'>청주시 흥덕구</span>"+
			   "<span class='dropdown-item'>충주시</span>";
		break;
		
	case "충남":
		html = "<span class='dropdown-item'>계룡시</span>"+
			   "<span class='dropdown-item'>공주시<span>"+
			   "<span class='dropdown-item'>금산군<span>"+
			   "<span class='dropdown-item'>논산시</span>"+
			   "<span class='dropdown-item'>당진시</span>"+
			   "<span class='dropdown-item'>보령시</span>"+
			   "<span class='dropdown-item'>부여군</span>"+
			   "<span class='dropdown-item'>서산시</span>"+
			   "<span class='dropdown-item'>서천군</span>"+
			   "<span class='dropdown-item'>아산시</span>"+
			   "<span class='dropdown-item'>연기군</span>"+
			   "<span class='dropdown-item'>예산군</span>"+
			   "<span class='dropdown-item'>천안시</span>"+
			   "<span class='dropdown-item'>천안시 동남구</span>"+
			   "<span class='dropdown-item'>천안시 서북구</span>"+
			   "<span class='dropdown-item'>청양군</span>"+
			   "<span class='dropdown-item'>태안군</span>"+
			   "<span class='dropdown-item'>흥성군</span>";
		break;
	case "전북":
		html = "<span class='dropdown-item'>고창군</span>"+
			   "<span class='dropdown-item'>군산시</span>"+
			   "<span class='dropdown-item'>김제시</span>"+
			   "<span class='dropdown-item'>남원시</span>"+
			   "<span class='dropdown-item'>무주군</span>"+
			   "<span class='dropdown-item'>부안군</span>"+
			   "<span class='dropdown-item'>순창군</span>"+
			   "<span class='dropdown-item'>완주군</span>"+
			   "<span class='dropdown-item'>익산시</span>"+
			   "<span class='dropdown-item'>임실군</span>"+
			   "<span class='dropdown-item'>장수군</span>"+
			   "<span class='dropdown-item'>전주시</span>"+
			   "<span class='dropdown-item'>전주시 덕진구</span>"+
			   "<span class='dropdown-item'>전주시 완산구</span>"+
			   "<span class='dropdown-item'>정읍시</span>"+
			   "<span class='dropdown-item'>진안군</span>";
		break;
		
	case "전남":
		html = "<span class='dropdown-item'>강진군</span>"+
			   "<span class='dropdown-item'>고흥군</span>"+
			   "<span class='dropdown-item'>곡성군</span>"+
			   "<span class='dropdown-item'>광양시</span>"+
			   "<span class='dropdown-item'>구례군</span>"+
			   "<span class='dropdown-item'>나주시</span>"+
			   "<span class='dropdown-item'>담양군</span>"+
			   "<span class='dropdown-item'>목포시</span>"+
			   "<span class='dropdown-item'>무안군</span>"+
			   "<span class='dropdown-item'>보성군</span>"+
			   "<span class='dropdown-item'>순천시</span>"+
			   "<span class='dropdown-item'>신안군</span>"+
			   "<span class='dropdown-item'>여수시</span>"+
			   "<span class='dropdown-item'>영광군</span>"+
			   "<span class='dropdown-item'>영암군</span>"+
			   "<span class='dropdown-item'>장성군</span>"+
			   "<span class='dropdown-item'>장흥군</span>"+
			   "<span class='dropdown-item'>진도군</span>"+
			   "<span class='dropdown-item'>함평군</span>"+
			   "<span class='dropdown-item'>해남군</span>"+
			   "<span class='dropdown-item'>화순근</span>";
		break;
	case "경북":
		html = "<span class='dropdown-item'>경산시</span>"+
			   "<span class='dropdown-item'>경주시</span>"+
			   "<span class='dropdown-item'>고령군</span>"+
			   "<span class='dropdown-item'>구미시</span>"+
			   "<span class='dropdown-item'>군위군</span>"+
			   "<span class='dropdown-item'>김천시</span>"+
			   "<span class='dropdown-item'>문경시</span>"+
			   "<span class='dropdown-item'>봉화군</span>"+
			   "<span class='dropdown-item'>상주기</span>"+
			   "<span class='dropdown-item'>성주군</span>"+
			   "<span class='dropdown-item'>안동시</span>"+
			   "<span class='dropdown-item'>영덕군</span>"+
			   "<span class='dropdown-item'>영양군</span>"+
			   "<span class='dropdown-item'>영주시</span>"+
			   "<span class='dropdown-item'>예천군</span>"+
			   "<span class='dropdown-item'>울릉군</span>"+
			   "<span class='dropdown-item'>울진군</span>"+
			   "<span class='dropdown-item'>의성군</span>"+
			   "<span class='dropdown-item'>청도군</span>"+
			   "<span class='dropdown-item'>청송군</span>"+
			   "<span class='dropdown-item'>칠곡군</span>"+
			   "<span class='dropdown-item'>포항시</span>"+
			   "<span class='dropdown-item'>포항시 남구</span>"+
			   "<span class='dropdown-item'>포항시 북구</span>";
		break;
	case "경남":
		html = "<span class='dropdown-item'>거제시</span>"+
			   "<span class='dropdown-item'>거창군</span>"+
			   "<span class='dropdown-item'>고성군</span>"+
			   "<span class='dropdown-item'>김해시</span>"+
			   "<span class='dropdown-item'>남해군</span>"+
			   "<span class='dropdown-item'>밀양시</span>"+
			   "<span class='dropdown-item'>사천시</span>"+
			   "<span class='dropdown-item'>산청군</span>"+
			   "<span class='dropdown-item'>양산시</span>"+
			   "<span class='dropdown-item'>의령군</span>"+
			   "<span class='dropdown-item'>진주시</span>"+
			   "<span class='dropdown-item'>창녕군</span>"+
			   "<span class='dropdown-item'>창원시</span>"+
			   "<span class='dropdown-item'>창원시 마산합포구</span>"+
			   "<span class='dropdown-item'>창원시 마산회원구</span>"+
			   "<span class='dropdown-item'>창원시 성산구</span>"+
			   "<span class='dropdown-item'>창원시 의창구</span>"+
			   "<span class='dropdown-item'>창원시 진해구</span>"+
			   "<span class='dropdown-item'>통영시</span>"+
			   "<span class='dropdown-item'>하동군</span>"+
			   "<span class='dropdown-item'>함안군</span>"+
			   "<span class='dropdown-item'>함양군</span>"+
			   "<span class='dropdown-item'>합천군</span>";
		break;
	case "제주":
		html = "<span class='dropdown-item'>서귀포시</span>"+
			   "<span class='dropdown-item'>제주시<span>";
		break;
	}
	
	return html;
}