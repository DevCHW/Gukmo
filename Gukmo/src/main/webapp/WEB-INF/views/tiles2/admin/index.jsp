<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 관리자 메인 페이지입니다.. --%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/hyunwoo/admin/index.css" />
<%-- 폰트 --%>
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<%-- 차트 플러그인 --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/Chart.min.js" ></script>
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/hyunwoo/admin/index.js" ></script>

    
    
    
    
    
    
    
<%-- Main Content --%>
<div id="content">
    <%-- Begin Page Content --%>
    <div class="container-fluid">

        <%-- Page Heading --%>
        <div class="d-sm-flex align-items-center justify-content-between my-4">
            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
        </div>


        <%-- Content Row --%>

        <div class="row">

            <%-- Area Chart --%>
            <div class="col-xl-8 col-lg-7">
                <div class="card shadow mb-4">
                    <%-- Card Header - Dropdown --%>
                    <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 id="area-chart-title" class="m-0 font-weight-bold text-primary"></h6>
                    </div>
                    <%-- Card Body --%>
                    <div class="card-body">
                        <div class="chart-area">
                            <canvas id="myAreaChart"></canvas>
                        </div>
                    </div>
                </div>
                
                <%-- 인기콘텐츠 --%>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">오늘의 베스트 콘텐츠 TOP3</h6>
                    </div>
                    <%-- 인기콘텐츠 card-body 시작 --%>
                    <div id="popular-content" class="card-body">
                    	<%-- 인기콘텐츠 반복문 시작 --%>
                    	<c:forEach var="popularBoard" items="${requestScope.popularBoardList}" varStatus="status">
                    	<div>
					      <%-- 글제목 --%>
					      <a id="popular-subject" href="<%=ctxPath %>/detail.do?boardNum=${popularBoard.board_num}" class="subject align-items-center mb-2 pl-1">
					        ${popularBoard.subject}
					      </a>
					
					      <div class="d-flex justify-content-between align-items-center my-1 pl-1">
					      	<div>
						        <c:if test="${popularBoard.detail_category eq '자유게시판'}">
							        <a class="detail_category rounded mr-2 px-1 py-1" href="<%=ctxPath %>/community/freeBoards.do">
							        	${popularBoard.detail_category}
							        </a>
						        </c:if>
						        <c:if test="${popularBoard.detail_category eq 'QnA'}">
							        <a class="detail_category rounded mr-2 px-1 py-1" href="<%=ctxPath %>/community/questions.do">
							        	${popularBoard.detail_category}
							        </a>
						        </c:if>
						        <c:if test="${popularBoard.detail_category eq '스터디'}">
							        <a class="detail_category rounded mr-2 px-1 py-1" href="<%=ctxPath %>/community/studies.do">
							        	${popularBoard.detail_category}
							        </a>
						        </c:if>
						        <c:if test="${popularBoard.detail_category eq '취미모임'}">
							        <a class="detail_category rounded mr-2 px-1 py-1" href="<%=ctxPath %>/community/hobbies.do">
							        	${popularBoard.detail_category}
							        </a>
						        </c:if>
						        <c:if test="${popularBoard.detail_category eq '수강/취업후기'}">
							        <a class="detail_category rounded mr-2 px-1 py-1" href="<%=ctxPath %>/community/reviews.do">
							        	${popularBoard.detail_category}
							        </a>
						        </c:if>
						        <c:if test="${popularBoard.detail_category eq '국비학원'}">
							        <a class="detail_category rounded mr-2 px-1 py-1" href="<%=ctxPath %>/academy/academies.do">
							        	${popularBoard.detail_category}
							        </a>
						        </c:if>
						        <c:if test="${popularBoard.detail_category eq '교육과정'}">
							        <a class="detail_category rounded mr-2 px-1 py-1" href="<%=ctxPath %>/academy/curricula.do">
							        	${popularBoard.detail_category}
							        </a>
						        </c:if>
						        <%-- 작성자 닉네임 --%>
						        <%-- 클릭하면 해당 유저의 활동내역 페이지로 이동하게 링크 거세요. --%>
						        <a href="<%=ctxPath %>/member/activityOther.do?nickname=${popularBoard.nickname}" class="writer_nickname mr-2">
						         	 ${popularBoard.nickname}
						        </a>
					        </div>
					        <%-- 조회수,댓글수,추천수 --%>
					        <div class="board_info_box d-flex justify-content-end">
					          <%-- 조회수 --%>
					          <div>
					            <span>조회수 ${popularBoard.views}</span>
					          </div>
					
					          <%-- 댓글수 --%>
					          <div class="ml-2">
					            <span>댓글수 ${popularBoard.comment_cnt}</span>
					          </div>
					
					          <%-- 추천수 --%>
					          <div class="ml-2">
					            <span>추천수 ${popularBoard.like_cnt}</span>
					          </div>
					        </div>
					      </div>
					    </div>
					    <c:if test="${status.index != requestScope.popularBoardList.size()-1}">
					    	<hr>
					    </c:if>
					    </c:forEach>
					    <%-- 인기콘텐츠 반복문 끝 --%>
					    
                    </div>
                    <%-- 인기콘텐츠 card-body 끝 --%>
                </div>
            </div>

            <%-- 사이트요약 --%>
            <div class="col-xl-4 col-lg-5">
                <div class="card shadow mb-4">
                    <%-- Card Header - Dropdown --%>
                    <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">사이트 요약</h6>
                    </div>
                    <%-- Card Body(신규회원,방문자,트래픽,주간평균방문자) --%>
                    <div class="card-body">
                    	
                    	<h4 class="small font-weight-bold">오늘 신규회원  : ${summaryMap.CNT_JOIN_MEMBER}명
                    		<c:if test="${member_percentage != 100}">
                    		<span class="float-right">${member_percentage}%</span>
                    		</c:if>
                    		<c:if test="${member_percentage == 100}">
                    		<span class="float-right">기록갱신!</span>
                    		</c:if>
                        </h4>
                        <div class="progress mb-4">
                            <div class="progress-bar bg-danger progress-bar-striped progress-bar-animated" role="progressbar" style="width: ${member_percentage}%"
                                aria-valuenow="${member_percentage}" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        
                        
                        <h4 class="small font-weight-bold">오늘 접속량 : ${summaryMap.CNT_VISIT}
                        	<c:if test="${visit_percentage != 100}">
                    		<span class="float-right">${visit_percentage}%</span>
                    		</c:if>
                    		<c:if test="${visit_percentage == 100}">
                    		<span class="float-right">기록갱신!</span>
                    		</c:if>
                        </h4>
                        <div class="progress mb-4">
                            <div class="progress-bar progress-bar-striped progress-bar-animated bg-warning" role="progressbar" style="width: ${visit_percentage}%"
                                aria-valuenow="${visit_percentage}" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        
                        
                        <h4 class="small font-weight-bold progress-bar-striped progress-bar-animated">오늘 작성된 게시물 : ${summaryMap.CNT_BOARD}개
                        	<c:if test="${board_percentage != 100}">
                    		<span class="float-right">${board_percentage}%</span>
                    		</c:if>
                    		<c:if test="${board_percentage == 100}">
                    		<span class="float-right">기록갱신!</span>
                    		</c:if>
                        </h4>
                        <div class="progress">
                            <div class="progress-bar bg-success progress-bar-striped progress-bar-animated" role="progressbar" style="width: ${board_percentage}%"
                                aria-valuenow="${board_percentage}" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </div>
                <%-- donut chart --%>
                <div class="card shadow mb-4">
                	<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                		<h6 id="pie-chart-title" class="m-0 font-weight-bold text-primary">오늘 현재 커뮤니티 활성 비율</h6>
                	</div>
                	<div class="card-body">
                		<div class="chart-pie pt-4">
                             <canvas id="myPieChart"></canvas>
                         </div>
                         <div class="mt-4 text-center small">
                            <span class="mr-2">
                                <i class="fas fa-circle text-primary"></i> 자유게시판
                            </span>
                            <span class="mr-2">
                                <i class="fas fa-circle text-success"></i> Q&A
                            </span>
                            <span class="mr-2">
                                <i class="fas fa-circle text-info"></i> 스터디
                            </span>
                            <span class="mr-2">
                                <i class="fas fa-circle text-warning"></i> 취미모임
                            </span>
                            <span class="mr-2">
                                <i class="fas fa-circle text-danger"></i> 수강/취업후기
                            </span>
                        </div>
                	</div>
                </div>
            </div>
        </div>
        
        <div class="row">
			        
        </div>
    </div>
    <%-- /.container-fluid --%>
    
    
</div>
<%-- End of Main Content --%>
