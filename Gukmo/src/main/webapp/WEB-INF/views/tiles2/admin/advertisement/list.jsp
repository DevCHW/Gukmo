<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 광고리스트 페이지입니다. --%>


<%-- Custom styles for this page --%>
<link href="<%=ctxPath %>/resources/css/hyunwoo/admin/dataTable/dataTables.bootstrap4.min.css" rel="stylesheet">
<%-- Latest compiled and minified CSS --%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
<%-- Latest compiled and minified JavaScript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
<%-- 직접만든 CSS(어드민 리스트페이지 공통) --%>
<link rel="stylesheet" href="<%=ctxPath %>/resources/css/hyunwoo/admin/listPage.css">
<%-- Core plugin JavaScript--%>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/jquery.easing.min.js"></script>
<%-- dataTable bootstrap4 --%>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/dataTable/jquery.dataTables.min.js"></script>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/dataTable/dataTables.bootstrap4.min.js"></script>
<%-- dataTables button js --%>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script> 
<%-- dataTables button jszip --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script> 
<%-- dataTables pdfmake --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script> 
<%-- dataTables pdfmake vfs_fonts--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script> 
<%-- dataTables html5 button --%>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script> 
<%-- dataTables print button --%>
<script src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>
<%-- 직접만든 javascript --%>
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/advertisement/list.js"></script>


<%-- Main Content --%>
<div id="content">
	
    <%-- Begin Page Content --%>
    <div class="container-fluid">

        <%-- Page Heading --%>
        <h1 class="h3 my-2 text-gray-800">광고내역</h1>
        <p class="mb-4"></p>

        <%-- DataTales Example --%>
        <div class="card shadow my-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">광고내역</h6>
            </div>
            <%-- 검색영역(select박스,검색바) 시작 --%>
            <div class="d-flex justify-content-end align-items-center px-3 mt-3">
              
			  
              <%-- 검색조건 selectBOX --%>
              <select id="searchType" class="selectpicker mr-3 border rounded" data-style="btn-info" data-width="120px">
              	  <option value="0">광고번호</option>
			      <option value="2">고객명</option>
			      <option value="3">고객연락처</option>
			  </select>
			  
			  <%-- 검색바 시작 --%>
			  <div class="searchBar d-flex justify-content-center mr-3">
			    <input type="text" id="searchWord" name="searchWord" class="pl-3" placeholder="검색어를 입력해주세요"></input>
			    <button type="button" id="btn_search">
			      <i class="fa-solid fa-magnifying-glass" style="color:#208EC9;"></i>
			    </button>
			  </div>
			  <%-- 검색바 끝 --%>
			  
			  <%-- 필터버튼 --%>
			  <div id="btn_filter" class="border rounded py-2" style="cursor:pointer;">
			    <i class="fa-solid fa-filter"></i>
			  </div>
			</div>
			<%-- 검색영역(select박스,검색바) 끝 --%>
			
			
			  <%-- 필터영역 시작 --%>
			  <div class="d-flex justify-content-end align-items-center pt-3">
				<div id="filter_area" class="align-items-center">
					<%-- 필터버튼클릭시 나올 상태 selectBox --%>
		            <select id="division" class="selectpicker mr-4 mt-3 border rounded" data-style="btn-light border" data-width="120px">
		            	<option>구분선택</option>
		            	<option>메인</option>
						<option>게시판</option>
					</select>
					
					
					<%-- 필터버튼클릭시 나올 상태 selectBox --%>
		            <select id="status" class="selectpicker mr-4 mt-3 border rounded" data-style="btn-light border" data-width="120px">
		            	<option>상태선택</option>
		            	<option>진행중</option>
						<option>종료</option>
					</select>
				    
				    <%-- 기간 범위 선택 --%>
					<div id="join_date_box" class="mr-3">
						<div style="font-weight:bold;">기간 범위 입력</div>
						<div class="mt-1">
					  	  <input type="text" id="start_date" name="start_date" style="width: 100px; height:30px; outline:none;" class="border rounded pl-2" placeholder="검색시작일자" readonly>
					      <span>&nbsp;부터&nbsp;</span>
					      <input type="text" id="end_date" name="end_date" style="width: 100px; height:30px; outline:none;" class="border rounded pl-2" readonly>
					      <span>&nbsp;까지&nbsp;</span>
						</div>
					</div>
	             </div>
			  </div>
			  <%-- 필터영역 끝 --%>
			  
			  
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                            	<th>광고번호</th>
                                <th>구분</th>
                                <th>고객명</th>
                                <th>고객연락처</th>
                                <th>기간</th>
                                <th>상태</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <%-- /.container-fluid --%>

</div>
<%-- End of Main Content --%>












