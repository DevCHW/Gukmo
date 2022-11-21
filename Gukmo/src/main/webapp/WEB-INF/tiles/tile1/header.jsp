<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String ctxPath = request.getContextPath();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 직접 만든 CSS -->
<link rel="stylesheet" href="<%=ctxPath %>/resources/css/hasol/header.css">

<!-- 직접만든 javascript -->
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hasol/header.js" ></script>


<<<<<<< HEAD
                    <!-- Links -->
                    <nav style="width:70%;">
                        <ul class="my mainCate">
                            <li>
                                <a class="nav-link" href="#">국비학원</a>
                            </li>
                            <li>
                                <a class="nav-link" href="<%=ctxPath %>/community/freeBoards.do">커뮤니티</a>
                            </li>
                            <li>
                                <a class="nav-link" href="#">공지사항</a>
                            </li>
		                    <c:if test="${sessionScope.user.userid eq 'admin'}">
	                        <div class="dropdown">
	                        	<div class="adminMenu" >
	                           		<a class="nav-link" onclick="drop_content3()">관리자 메뉴</a>	                                									
							  	</div>
							  	<div id="myDropdown3" class="dropdown-content2 mt-2">
							    	<a href="<%=ctxPath %>/admin/memberManage_List.do">일반회원 관리</a>
							    	<a href="<%=ctxPath %>/admin/academyManage_List.do">학원회원 관리</a>
							  	</div>
							</div>	
	                    	
		                    </c:if>
                        </ul>
                    </nav>
                </div>
                
                <!-- login -->
                <div class="main_right">
                    <c:if test="${empty sessionScope.user}">
	                    <div class="non-login">
	                        <button type="button" class="btn_login" id="login" onclick="location.href='<%=ctxPath %>/login.do'">로그인</button>
	                        <button type="button" class="btn_regist" id="regist" onclick="location.href='<%=ctxPath %>/signup.do'">회원가입</button>
	                    </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.loginuser}">
                    	<div class="login">
							<i class="fa-regular fa-bookmark fa-lg"></i>
							<i class="fa-solid fa-bell fa-lg"></i>
		                	<a href="#" class="profile_image_box border">
						          <img src=""/>
						    </a>
                		</div>
                		
  
                	</c:if>
                </div>
            </nav>
        </nav>
        
    </haeder>    
=======
<!-- 네비게이션 시작 -->
<nav bar class="mainNav_bar">
   <nav class="navbar navbar-expand-lg bg-white mainNav" >
      
      <!-- 로고 및 메뉴 영역 -->
         <div class="main_left" >
         
            <!-- Brand/logo -->
         <a class="navbar-brand d-flex justify-content-start align-items-center" href="#" style="width:100px; ">
            <img src="" alt="logo" >
         </a>

         <!-- Links -->
         <nav>
            <ul class="mainCate">
               <li>
                  <a class="nav-link" href="#">국비학원</a>
               </li>
                     <li>
                         <a class="nav-link" href="<%=ctxPath %>/community/freeBoards.do">커뮤니티</a>
                     </li>
                     <li>
                         <a class="nav-link" href="#">공지사항</a>
                     </li>
                     
                     <!-- 관리자로 로그인 했을 경우 추가 메뉴 -->
                   <c:if test="${sessionScope.user.userid eq 'admin'}">
                   <li class="dropdown">
                      <div class="adminMenu" >
                        <a class="nav-link adminMenu" onclick="drop_admin()">관리자 메뉴</a>                                                              
                  </div>
                  <div id="admin_dropContent" class="dropdown-content2 mt-2">
                     <a href="<%=ctxPath %>admin/memberManage_List.do">g 메뉴</a>
                     <a href="#" id="admin_link">관리자 메뉴</a>
                  </div>
                 </li>   
                       </c:if>
                 </ul>
             </nav>
         </div>
      
      
      <!-- login 메뉴 영역 -->
      <div class="main_right">
         
            <!-- 비로그인 시 -->
         <c:if test="${empty sessionScope.user}">
         <div class="non-login">
            <button type="button" class="btn_login" id="login" onclick="location.href='<%=ctxPath %>/login.do'">로그인</button>
                <button type="button" class="btn_regist" id="regist" onclick="location.href='<%=ctxPath %>/signup.do'">회원가입</button>
            </div>
            </c:if>
          
         <!-- 로그인 시 -->
         <c:if test="${not empty sessionScope.user}">
           <div class="login d-flex justify-content-between align-items-center">
              
              <!-- 북마크(스크랩) -->
            <a class="login_icon">
               <i class="fa-regular fa-bookmark fa-lg"></i>
            </a>
            
            <!-- 알림 -->      
            <div class="dropdown">
               <a class="login_icon alarm_drop">
                  <i class="fa-solid fa-bell fa-lg alarm_drop" onclick="drop_alarm()"></i>
               </a>
               <div id="alarm_dropContent" class="dropdown-content1 mt-1">
                  <a href="#">알림</a>
                  <div class="d-flex px-3 py-2">
                     <!-- 알림 내용이 없을 경우 -->
   <%--                <c:if test="${not empty sessionScope.userAlram}"> --%>
                     <p> 받으신 알림이 없습니다. </p>
   <%--                </c:if> --%>
                     
                     <!-- 알림 내용이 있을 경우 (반복문) -->
   <%--                <c:if test="${empty sessionScope.userAlram}">
                     <a href="#"> 받으신 알림이 없습니다. </a>
                     </c:if> --%>
                   </div>
                 </div>
            </div>
   
            <!-- 프로필 drop -->
                <div class="dropdown">
                   <div class="dropbtn"> 
                    <img src="<%= ctxPath%>/resources/images/user.PNG" class="dropbtn" onclick="drop_profile()">
               </div>
               <div id="profile_dropContent" class="dropdown-content2">
                  <div class="px-1 py-1">
                        <a href="<%= ctxPath%>/member/myId.do">
                        <i class="fa-solid fa-user"></i>  
                        내 계정
                     </a>
                       <a href="<%= ctxPath%>/member/myInfo.do">
                          <i class="fa-solid fa-gear"></i>
                          내 정보
                       </a>
                       <a href="<%= ctxPath%> /member/activities.do">
                          <i class="fa-solid fa-gear"></i>
                          활동내역
                       </a>
                       <a href="<%= ctxPath%>/logout.do ">로그아웃</a>
                  </div>
               </div>
            </div>
            </div>
             </c:if>
        </div>
    </nav>
</nav>
     
>>>>>>> refs/remotes/origin/johs
