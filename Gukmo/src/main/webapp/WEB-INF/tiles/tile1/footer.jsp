<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>

<%-- ======= #27. tile1 중 footer 페이지 만들기  ======= --%>
<script type="text/javascript">

let socket = null;

$(document).ready(function (){
	 
	   connectWs();
});

function connectWs(){
	
	const url = window.location.host; // 웹브라우저의 주소창의 포트까지 가져옴
	//alert("결과값 url : " + url);
	 // 결과값 url : 211.238.142.40:9090
	 
    const pathname = window.location.pathname; // 최초 '/' 부터 오른쪽에 있는 모든 경로
	//alert("결과값 pathname : " + pathname);
	// 결과값 pathname : /board/chatting/multichat.action  /board/index.do
 
    const appCtx = pathname.substring(0, pathname.lastIndexOf("/") ); 
	//alert("결과값 appCtx : " + appCtx);
	// 결과값 appCtx : /board/chatting

    const root = url + appCtx;
	//alert(root)
	
    const wsUrl = "ws://" + root + "/alarm.do";
    // alert(wsUrl)
    // ws://localhost:9090/board/alarm.do
    
	const ws = new WebSocket(wsUrl);
	socket = ws;

	ws.onopen = function() {
		alert("웹소켓 연결됨!!");
		console.log('info: connection opened.');   
  };

 ws.onmessage = function(evt) {
	 	let data = evt.data;
	   	console.log("ReceivMessage : " + data + "\n");

/* 	   	$.ajax({
			url : '/mentor/member/countAlarm',
			type : 'POST',
			dataType: 'text',
			success : function(data) {
				if(data == '0'){
				}else{
					$('#alarmCountSpan').addClass('bell-badge-danger bell-badge')
					$('#alarmCountSpan').text(data);
				}
			},
			error : function(err){
				alert('err');
			}
	   	}); */

	   	let html = "<div class='toast' role='alert' aria-live='assertive' aria-atomic='true' data-animation='true' data-delay='5000'  style='width:300px;'>" +
	      		   		"<div class='toast-header'>" +
	        				"<img src='...' class='rounded mr-2' alt='...'>" +
	        				"<strong class='mr-auto'>Bootstrap</strong>" +
	        				"<button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>" +
	          					"<span aria-hidden='true'>&times;</span>" +
	        				"</button>" +
	      				"</div>" +
	      				"<div class='toast-body w-100'>" +                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        "</div>"+
	    			"</div>";
	   	
	   	$("div#toastPopup").append(html);
	   	
	   	// 푸시 알림
	   	$('.toast').toast('show');
	   	
	   	$('#myToast').on('hidden.bs.toast', function () {
	   		$('.toast').toast('dispose');
	   	})
 };

 ws.onclose = function() {
   	console.log('connect close');
   	/* setTimeout(function(){conntectWs();} , 1000); */
 };

 ws.onerror = function (err) {console.log('Errors : ' , err);};

}


</script>

  <!-- Position it -->
  <div style="position: fixed; bottom: 0; right: 0; margin: 20px; z-index:2;">
	<div id="toastPopup"></div>
    <!-- Then put toasts within -->
    <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="false" data-animation="true" data-delay="3000"  style="width:300px;">
      <div class="toast-header">
        <img src="..." class="rounded mr-2" alt="...">
        <strong class="mr-auto">Bootstrap</strong>
        <small class="text-muted">just now</small>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="toast-body w-100">
        See? Just like this.
      </div>
    </div>

  </div>



<!-- Footer -->
<footer class="text-center text-lg-start text-muted border-top">
  <!-- Section: Links  -->
  <section class="">
    <div class="container text-center text-md-start mt-2">
      <!-- Grid row -->
      <div class="row mt-1">
        <!-- Grid column -->
        <div class="col-md col-lg col-xl mx-auto">
          <!-- Content -->
          <h2 class="text-uppercase fw-bold">
            국모
          </h2>
          <!-- Section: Social media -->
           
              <div>
                <a href="" class="me-4 text-reset">
                  <i class="fab fa-facebook-f"></i>
                </a>
                <a href="" class="me-4 text-reset">
                  <i class="fab fa-instagram"></i>
                </a>
                <a href="" class="me-4 text-reset">
                  <i class="fab fa-github"></i>
                </a>
                <a href="mailto:info@gukmo.kr" class="me-4 text-reset">
                  <i class="fas fa-envelope me-3"></i>
                </a>
              </div>
    
            <!-- Section: Social media -->
        </div>

        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="<%=ctxPath %>/about.do" class="text-reset">회사소개</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="#!" class="text-reset">공지사항</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="#!" class="text-reset">버그제보</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="#!" class="text-reset">광고문의</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="#!" class="text-reset">개인정보 처리방침</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="#!" class="text-reset">서비스 이용약관</a>
          </p>
        </div>
        <!-- Grid column -->
      </div>
      <!-- Grid row -->
    </div>
  </section>
  <!-- Section: Links  -->

  <div class="text-center" >
    <p style="margin:0;">상호명: (주)국모 | 사업자등록번호 : 123-45-67890 |   문의 :
      <a
        href="mailto:info@okky.kr"
        class="text-gray-700 hover:text-blue-500 dark:text-gray-300 dark:hover:text-blue-200"
        >info@gukmo.kr</a
      ></p>
    <p>
      통신판매업신고번호 : 제 2022-서울마포-123호
    </p>
  </div>

  <!-- Copyright -->
  <div class="text-center p-3 bg-light">
    © 2022 
    (주)국모, Inc. All rights reserved.
  </div>
  <!-- Copyright -->
</footer>
<!-- Footer -->