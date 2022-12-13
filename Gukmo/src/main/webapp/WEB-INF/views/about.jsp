<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>




<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0 shrink-to-fit=no">
  <title>국비의모든것 - Give everyone an opportunity to learn</title>
  <%-- Bootstrap CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 
  <%-- Font Awesome 5 Icons --%>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
  <%-- title Icon --%>
  <link href="<%=ctxPath %>/resources/images/titleicon.png" rel="shortcut icon" type="image/x-icon">
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/about.css" />
  
  <%-- Optional JavaScript --%>
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/resources/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>

  <%-- fullpage 라이브러리 --%>
  <link href="<%= ctxPath%>/resources/fullpagelib/jquery.fullPage.css" rel="stylesheet"> 
  <script type="text/javascript" src="<%= ctxPath%>/resources/jquery.fullPage.js"></script>
  
  <%-- TypeIt 라이브러리 --%>
  <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
  
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/about.js" ></script>
  
</head>

<%--
	목차 : section 1 : 국모 메인
		 section 2 : 국모 개발 목적(TypeIt 애니메이션)
		 section 3 : 국모 개발 목적(글)
		 section 4 : 국모 개발자 소개(TypeIt 애니메이션)
		 section 5 : 조하솔 소개
		 section 6 : 임선우 소개
		 section 7 : 김성민 소개
		 section 8 : 황광빈 소개
		 section 9 : 최현우 소개
		 section 10 : 마지막
 --%>

<body>
  <div class="header d-flex justify-content-between align-items-center">
    <div>
      <a href="<%= ctxPath%>/index.do"><h1 id="logo">GUKMO</h1></a>
    </div>
    <ul class="sns_list list-unstyled d-flex">
      <li class="sns_list mr-3" style="cursor:pointer;" onclick="window.open('https://www.facebook.com/')" target="_blank"><i class="fa-brands fa-facebook fa-2x"></i></li>
      <li class="sns_list" style="cursor:pointer;" onclick="window.open('https://www.instagram.com/')" target="_blank"><i class="fa-brands fa-2x fa-instagram"></i></li>
    </ul>
  </div>



  
  <main id="fullpage">
  
    <%-- 시작 section1 --%>
    <div class="section">
      <h1 id="project_dream" class="text-center" style="font-weight:bold">
      	<img src="<%=ctxPath%>/resources/images/mainLogo.png"/>
      </h1>
    </div>
    <%-- 시작 section1 --%>
    
    
    
    <%-- 국모개발목적 section2 시작 --%>
    <div id="develop_reason_section" class="section">
      <h1 id="develop_reason" class="text-center" style="font-weight:bold">
       	"국비의 모든것"은 왜 만들어졌나요?
      </h1>
    </div>
    <%-- 국모개발목적 section2 끝 --%>
    
    
    
    <%-- 드림개발목적 section3 시작 --%>
    <div id="develop_reason_section2" class="section">
    
      <div id="develop_reason_text" class="clipText w-75">
        <h1 style="font-weight:bold;">Why was GukMo created?</h1>
        <p>국모 개발 이유1</p>
        <p>국모 개발 이유2</p>
        <p>국모 개발 이유3</p>
        <p>국모 개발 이유4</p>
      </div>
    </div>
    <%-- 국모 개발목적 section3 끝 --%>
    
    
    
    
    <%-- 국모개발자소개 section4 시작 --%>
    <div id="developer_introduce_section" class="section">
      <h1 id="developer_introduce" class="text-center" style="font-weight:bold">국비의모든것 개발자 소개 </h1>
    </div>
    <%-- 국모개발자소개 section4 끝 --%>
    
    
    
    
    <%-------------------------------------- 조하솔 section5 시작 ------------------------------------%>
    <div id="hasol" class="section">
      <div id="hasol_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="hasol_profile_image" class="profile_image">
            <div class="profile_image_box">
              <%--하솔누나 프사 --%>
              <img src="<%=ctxPath %>/resources/images/about/최현우.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">조하솔</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="hasol_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
              	하솔누나 자기소개 부분
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="hasol_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath %>/resources/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/oracle.png"/></li>
                  <%-- Spring icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/oracle.png"/></li>
                  <%-- Python icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/oracle.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="hasol_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>하솔누나 번호</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>하솔누나 깃주소</span>
                  </li>
                </ul>
              </div>
            </div>
            
            
            
          </div>
        </div>
        
        
        <div id="hasol_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
            <p>하솔누나 프로젝트 후기</p>
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 조하솔 section5 끝 ------------------------------------%>
    
    
    
    
    
    
    
    <%-------------------------------------- 임선우 section6 시작 ------------------------------------%>
    <div id="sunwoo" class="section">
      <div id="sunwoo_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="sunwoo_profile_image" class="profile_image">
            <div class="profile_image_box">
              <%-- 선우누나 프사 --%>
              <img src="<%=ctxPath %>/resources/images/about/장진영.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">임선우</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="sunwoo_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
              	<p>선우누나 자기소개 부분</p>
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="sunwoo_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath %>/resources/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/oracle.png"/></li>
                  <%-- Spring icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/spring.png"/></li>
                  <%-- Python icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/python.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="sunwoo_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>선우누나 번호</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>선우누나 깃주소</span>
                  </li>
                </ul>
              </div>
            </div>
            
            
            
          </div>
        </div>
        
        
        <div id="sunwoo_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
          	<p>선우누나프로젝트 후기 부분 시작</p>
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 임선우 section6 끝 ------------------------------------%>
    
    
    
    
    
    
    
    
    
    <%-------------------------------------- 김성민 section7 시작 ------------------------------------%>
    <div id="sungmin" class="section">
      <div id="sungmin_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="sungmin_profile_image" class="profile_image">
            <div class="profile_image_box">
              <%-- 성민이형 프사 --%>
              <img src="<%=ctxPath %>/resources/images/about/조상운.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">김성민</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="sungmin_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
              <p>성민이형 자기소개 글 부분</p>
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="sungmin_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath %>/resources/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/oracle.png"/></li>
                  <%-- Spring icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/spring.png"/></li>
                  <%-- Python icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/python.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="sungmin_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>성민이형 전화번호</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>성민이형 깃주소</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        
        
        <div id="sungmin_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content">
          	<p>성민이형 프로젝트 후기</p>
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 김성민 section7 끝 ------------------------------------%>
    
    
    
    
    
    
    
    
    
    
    <%-------------------------------------- 황광빈 section8 시작 ------------------------------------%>
    <div id="hgb" class="section">
      <div id="hgb_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="hgb_profile_image" class="profile_image">
            <div class="profile_image_box">
              <%-- 광빈이형 프사 --%>
              <img src="<%=ctxPath %>/resources/images/about/황광빈.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">황광빈</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="hgb_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
              	<p>광빈이형 자기소개</p>
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="hgb_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath %>/resources/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/oracle.png"/></li>
                  <%-- Spring icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/spring.png"/></li>
                  <%-- Python icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/python.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="hgb_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>010-9020-9305</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>https://github.com/GGWANGBIN</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        
        
        <div id="hgb_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
          	<p>광빈이형 프로젝트 후기</p>
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 황광빈 section8 끝 ------------------------------------%>
    
    
    
    
    
    
    
    
    
    
    <%-------------------------------------- 최현우 section9 시작 ------------------------------------%>
    <div id="hyunwoo" class="section">
      <div id="hyunwoo_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="hyunwoo_profile_image" class="profile_image">
            <div class="profile_image_box">
              <img src="<%=ctxPath %>/resources/images/about/김진석.png"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">최현우</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="hyunwoo_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
                <p style="font-weight:bold; font-style:italic">"최현우 자기소개 부분"</p>
				<p></p>
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="hyunwoo_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath %>/resources/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/oracle.png"/></li>
                  <%-- Spring icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/spring.png"/></li>
                  <%-- Python icon --%>
                  <li class="ml-3"><img src="<%=ctxPath %>/resources/images/about/python.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="hyunwoo_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span> 010-7423-9713</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>https://github.com/hyunwoocastle</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        
        
        <div id="hyunwoo_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
          	최현우 프로젝트 후기
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 최현우 section9 끝 ------------------------------------%>
    
    
    
    
    
    
    
    <%-- 끝 section10 시작--%>
    <div id="end_section" class="section">
      <h1 id="section_end" class="text-center" style="font-weight:bold">
        끝까지 봐주신 여러분 다들 사랑합니..
      </h1>
    </div>
    <%-- 끝 section10 끝 --%>
    
    
    
  </main>
</body>
</html>

