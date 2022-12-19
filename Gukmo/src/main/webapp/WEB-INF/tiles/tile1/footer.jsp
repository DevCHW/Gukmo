<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.net.InetAddress" %>

<%-- ======= #27. tile1 중 footer 페이지 만들기  ======= --%>
    
<%

	String ctxPath = request.getContextPath();
	// === 서버 IP 주소 알아오기(사용중인 IP주소가 유동IP 이라면 IP주소를 알아와야 한다.) ===
	InetAddress inet = InetAddress.getLocalHost(); 
	String serverIP = inet.getHostAddress();
	
  //System.out.println("serverIP : " + serverIP);
  // serverIP : 192.168.10.127
	
	// === 서버 포트번호 알아오기   ===
	int portnumber = request.getServerPort();
 // System.out.println("portnumber : " + portnumber);
 // portnumber : 9090
	
 	String serverName = serverIP + ":" + portnumber;
 // String serverName = "http://"+serverIP+":"+portnumber; 
 // System.out.println("serverName : " + serverName);
 // serverName : 192.168.10.127:9090
 
    session.setAttribute("serverName", serverName); 
 
 %>

  <!-- Position it -->
<!--   <div style="position: fixed; bottom: 0; right: 0; margin: 20px; z-index:2;">
	<div id="toastPopup"></div>
    Then put toasts within
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

  </div> -->



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
            GM
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
            <a href="#!" class="text-reset">회사소개</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="<%= ctxPath%>/notices.do" class="text-reset">공지사항</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="<%=ctxPath %>/bug/new.do" class="text-reset">버그제보</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="<%=ctxPath %>/advertisement/inquiry.do" class="text-reset">광고문의</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="<%=ctxPath %>/policy/privacy.do" class="text-reset">개인정보 처리방침</a>
          </p>
        </div>
        <div class="col-lg col-xl mx-auto pt-3">
          <p>
            <a href="<%=ctxPath %>/resources/TOS/TOStext.html" class="text-reset">서비스 이용약관</a>
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