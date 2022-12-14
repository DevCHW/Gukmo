<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	String ctxPath = request.getContextPath();
%>

<%-- 차트 페이지입니다. --%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/resources/css/seonwoo/chart.css" />

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/resources/js/seonwoo/chart.js" ></script>


<div id="section" class="d-flex">
  
  


    <div id="main" class="py-5 px-4 w-100">
    
    
     <%------------------------ 차트영역 시작 -----------------------%>
	<figure class="highcharts-figure">
	    <div id="container"></div>
	    <p class="highcharts-description">
	        Chart showing how an HTML table can be used as the data source for the
	        chart using the Highcharts data module. The chart is built by
	        referencing the existing HTML data table in the page. Several common
	        data source types are available, including CSV and Google Spreadsheet.
	    </p>
	
	    <table id="datatable">
	        <thead>
	            <tr>
	                <th></th>
	                <th>Boys</th>
	                <th>Girls</th>
	            </tr>
	        </thead>
	        <tbody>
	            <tr>
	                <th>2016</th>
	                <td>30 386</td>
	                <td>28 504</td>
	            </tr>
	            <tr>
	                <th>2017</th>
	                <td>29 173</td>
	                <td>27 460</td>
	            </tr>
	            <tr>
	                <th>2018</th>
	                <td>28 430</td>
	                <td>26 690</td>
	            </tr>
	            <tr>
	                <th>2019</th>
	                <td>28 042</td>
	                <td>26 453</td>
	            </tr>
	            <tr>
	                <th>2020</th>
	                <td>27 063</td>
	                <td>25 916</td>
	            </tr>
	            <tr>
	                <th>2021</th>
	                <td>28 684</td>
	                <td>27 376</td>
	            </tr>
	        </tbody>
	    </table>
	</figure>

   	  
   	  <%------------------------ 차트영역 끝 -----------------------%>

      
    </div>

  </div>