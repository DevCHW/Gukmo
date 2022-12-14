<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 교육기관회원리스트 페이지입니다. --%>


<%-- Custom styles for this page --%>
<link href="<%=ctxPath %>/resources/css/hyunwoo/admin/dataTable/dataTables.bootstrap4.min.css" rel="stylesheet">
<%-- Latest compiled and minified CSS --%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
<%-- dataTableButton 
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.3.2/css/buttons.dataTables.min.css">
--%>
<%-- Latest compiled and minified JavaScript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
<%-- 직접만든 CSS --%>
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
<script src="<%=ctxPath %>/resources/js/hyunwoo/admin/member/academy/list.js"></script>


<%-- Main Content --%>
<div id="content">
	
    <%-- Begin Page Content --%>
    <div class="container-fluid">

        <%-- Page Heading --%>
        <h1 class="h3 mb-2 text-gray-800">교육기관회원내역</h1>
        <p class="mb-4">승인 대기중인 교육기관회원은 심사 후 승인 부탁드립니다.</p>

        <%-- DataTales Example --%>
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">교육기관회원내역</h6>
            </div>
            <%-- 검색영역(select박스,검색바) 시작 --%>
            <div class="d-flex justify-content-end align-items-center px-3 mt-3">
              
			  
              <%-- 검색조건 selectBOX --%>
              <select id="searchType" class="selectpicker mr-3 border rounded" data-style="btn-info" data-width="120px">
			      <option value="0">닉네임</option>
			      <option value="1">아이디</option>
			      <option value="2">학원명</option>
			      <option value="5">홈페이지</option>
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
		            <select id="status" class="selectpicker mr-4 mt-3 border rounded" data-style="btn-light border" data-width="120px">
		            	<option>상태선택</option>
		            	<option>활동</option>
						<option>정지</option>
					    <option>휴면</option>
					    <option>대기</option>
					</select>
				    
				    <%-- 검색조건을 가입일자를 눌렀을 시에 나올 selectBox --%>
					<div id="join_date_box" class="mr-3">
						<div style="font-weight:bold;">가입일자 입력</div>
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
                                <th>닉네임</th>
                                <th>아이디</th>
                                <th>학원명</th>
                                <th>가입일자</th>
                                <th>상태</th>
                                <th>홈페이지</th>
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












