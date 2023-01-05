# Gukmo
쌍용강북교육센터 파이널프로젝트 3조

<Br>
<h3>🍀프로젝트 소개 🍀</h3>
- 프로젝트 이름: 국비의 모든 것(GUKMO)
- HTML, CSS, JAVASCRIPT, JAVA, OARCLE 등 프로그래밍 언어들을 활용하여 Spring Framework 의 MVC 패턴 기법을 적용한 커뮤니티 웹사이트
<br>

## 프로젝트 진행 기간
>  2022.11.4 ~ 2022.12.20
![프로젝트 일정](https://user-images.githubusercontent.com/100989788/209290095-53f1ec11-fd7a-48b0-afc9-cba1d3ce5cea.png)

## 🌈개발환경

- OS : Windows 10 , windows 11 <br>
- FW : Spring Framework <br>
- DB : Oracle18C
- WAS : Apache Tomcat 9.0.65
- Front-end : HTML5 / CSS / javascript / jQuery / AJAX / JSON
- Back-end : JDK 1.8 / JSP / JSTL / Mybatis
- Developer Tools : STS 3.9.12 / eXERD / SqlDeveloper 
- GitHub

<br>

### ⚡Tech Stack⚡

**Front-end**
<br><br>
<img src="https://img.shields.io/badge/HTML5-00599C?style=flat-square&logo=HTML5&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS-A8B9CC?style=flat-square&logo=C&logoColor=white"/>
<img src="https://img.shields.io/badge/javascript-F6C915?style=flat-square&logo=javascript&logoColor=white"/>
<img src="https://img.shields.io/badge/jQuery-red?style=flat-square&logo=jQuery&logoColor=white"/>
<img src="https://img.shields.io/badge/AJAX -black?style=flat-square&logo=AJAX &logoColor=white"/>

	 

**Back-end** 
<br><br>
<img src="https://img.shields.io/badge/JDK 1.8-important?style=flat-square&logo=JDK &logoColor=white"/>
<img src="https://img.shields.io/badge/JSP-yellowgreen?style=flat-square&logo=JSP&logoColor=white"/>
<img src="https://img.shields.io/badge/JSTL-blue?style=flat-square&logo=JSTL&logoColor=white"/>
<img src="https://img.shields.io/badge/Mybatis-brightgreen?style=flat-square&logo=JSTL&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring 5.3-green?style=flat-square&logo=JDK &logoColor=white"/>


<br>

### 🛠️ TOOL🛠️
<br><br>
<img src="https://img.shields.io/badge/STS 3.9.12-purple?style=flat-square&logo=STS&logoColor=white"/>
<img src="https://img.shields.io/badge/eXERD-brightgreen?style=flat-square&logo=eXERD&logoColor=white"/>
<img src="https://img.shields.io/badge/SqlDeveloper-9cf?style=flat-square&logo=SqlDeveloper &logoColor=white"/>
<img src="https://img.shields.io/badge/gitHub-blueviolet?style=flat-square&logo=gitHub&logoColor=white"/>


<br>


## 🧑‍🚀 팀원
```
김성민 - 관리자, 상세페이지
임선우 - 게시판, 상세페이지, 회원차트
조하솔 - 메인 화면, 알림, 전체 검색 기능
최현우 - 사용자 페이지 – 회원가입, 로그인, 마이페이지, 게시판, 계정찾기
       - 관리자 페이지 - 회원 / 신고 / 광고 / 버그 / 통계 / 대시보드
황광빈 - 상세페이지
```
<br>


## 사용한 API 

* Fullcalendar
* GoogleMail
* NAVER Smart Editor
* Chart.JS
* Kakao / Naver / Facebook / Google 로그인 API
* Google reCAPTCHA V2
* Google Calendar API
* DataTable


<Br>	
	
## 주요 기능 

```sh
- 로그인 기능
- 알림 기능
- 검색 기능
- 커뮤니티 기능
- 관리자 기능
- 마이페이지 기능
```
<br>
## 메인페이지
<br>

<br><br>

## 로그인
- 카카오, 네이버, 페이스북, 구글 아이디로 로그인 할 수 있고 해당 사이트 자체 아이디로도 로그인 할 수 있습니다.
![image](https://user-images.githubusercontent.com/112748454/210742774-d50eb7eb-5fbe-4f0d-abd7-96899af535ca.png)
	
<br><br>

## 회원가입
- 일반회원 가입 : 일반회원에 관한 정보를 입력하면 국비의모든것 일반회원이 될 수 있습니다.
- 교육기관회원 가입 : 일반회원가입에 필요한 정보 + 교육기관회원에 필요한 정보를 입력한 후에 회원가입을 하여 관리자에게 승인받은 후, 교육기관회원이 될 수 있습니다.
- Google reCAPTCHA v2를 이용하여 자동가입 방지를 적용하였습니다.
<br>
![image](https://user-images.githubusercontent.com/112748454/210743030-154a90a5-2278-44a4-b378-e03e69a9cf7e.png)
![image](https://user-images.githubusercontent.com/112748454/210743077-a55730ac-6379-4f8a-9373-858360657f18.png)
![image](https://user-images.githubusercontent.com/112748454/210743098-ac73dc37-cf9c-42e1-90b4-0b7cba10b7fb.png)
	
<br><br>



## 계정찾기
- 가입할 때 인증된 이메일을 입력하게되면, 비밀번호를 변경할 수 있는 페이지로 갈 수 있는 3분간 유효한 링크를 보내줍니다.
<br>
![image](https://user-images.githubusercontent.com/112748454/210743336-abfc1ce3-2b1b-411b-a870-d3525d35a90f.png)
	
<br><br>

## 마이페이지
- 이름/닉네임/이메일수신동의/프로필 이미지/이메일/비밀번호를 변경할 수 있고, 일반회원은 교육기관회원으로 전환 가능하며 사이트에서 활동내역/알림을 확인할 수 있습니다.
<br>
	
![국모마이페이지](https://user-images.githubusercontent.com/112748454/210744487-9032215c-82c4-44b8-bcf3-c9aa62c094d0.gif)

	
	
<br><br>	
## 글 상세페이지
- 글 제목 및 내용,카테고리,해시태그, 이전글 다음글 제목,좋아요,광고 표시
<br>
![image](https://user-images.githubusercontent.com/113486147/209841767-756f5a2e-c893-4a12-a57f-d8dca7f54bdb.png)
- 좋아요 여부에따라 아이콘모양을 다르게표시
- 좋아요 클릭시 카운트1증가, 좋아요 취소시 카운트1
![image](https://user-images.githubusercontent.com/113486147/209843593-ea6645d1-bb30-4729-9476-aa19fc0100d3.png)
![image](https://user-images.githubusercontent.com/113486147/209843633-2a378285-8cb5-421a-8378-817fa210aa9b.png)



- 댓글과 대댓글 내용 표시
- 본인의 댓글이라면 수정,삭제 가능
- 타인의 댓글 신고 가능
- 관리자로 로그인시 댓글 블라인드,블라인드 해제 가능
![image](https://user-images.githubusercontent.com/113486147/209842589-46834bc9-932c-4c2d-b136-89bc45be5b60.png)
![image](https://user-images.githubusercontent.com/113486147/209845264-bbbfdf84-affb-4f60-84b5-8c5d56e1a480.png)
![image](https://user-images.githubusercontent.com/113486147/209843035-ab9d70ed-0bb3-46bb-ac86-3bb49b5fc143.png)
![image](https://user-images.githubusercontent.com/113486147/209843088-1662f720-e33c-4d5d-a96a-b3e39b712d0a.png)

	









<br><br>

## 알람
- 읽지 않은 알람 개수 표시 및 읽지 않은 알람 리스트 
![image](https://user-images.githubusercontent.com/111223575/209939822-7d9feb69-6070-4c91-a591-de009e4b2463.png)

<br><br>



## 검색
- 학원 정보 / 과정 정보 / 게시판 별 노출 구성 변경 및 게시글 미리보기
![image](https://user-images.githubusercontent.com/111223575/209939662-4d0654bd-66c3-4ade-b2e3-89e8ef1ba69c.png)


<br><br>
## 관리자
- 회원관리 : 회원 리스트 노출, 회원 내역 excel, pdf, csv 파일로 다운로드 및 인쇄, 검색 및 정렬기능, 회원의 상세정보 조회, 검색기록, 게시물 및 신고내역 등 활동내역 차트화 
![녹화_2023_01_05_16_57_40_852](https://user-images.githubusercontent.com/113486287/210730400-720a3d5d-9675-4103-9a87-65200cbf33c7.gif)

- 광고관리 : 현재 사이트에서 광고 중인 리스트 노출, 광고 상세정보 조회. 풀캘린더 API를 이용하여 광고 관련 일정을 캘린더로 조회.
![광고 일정](https://user-images.githubusercontent.com/113486287/210051030-1537f6fe-cd16-4ace-8a22-ba97245f95ed.jpg)

<br><br>
-신고관리 : 현재 국모 사이트에 신고 리스트 노출, 해당 신고 관련 상세정보 조회, 신고한(신고받은) 사용자의 신고(피신고) 내역 조회, 신고 관련 게시글로 이동, 피신고자 정지 등록 
	
<br><br>
-각종 통계 : 유입도메인, 일자별 회원가입자 수, 일반회원/교육기관회원 비중, 카테고리별 게시물 건수 등 
![제목 없음](https://user-images.githubusercontent.com/113486287/210053044-3c7b1f10-550b-4373-b387-fb0815757d2d.jpg)
	
	
<br><br>
	

## ERD
<img width="788" alt="erd 최종" src="">
