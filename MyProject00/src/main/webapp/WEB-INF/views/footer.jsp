<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/js/jquery-3.6.0.min.js"></script>
 <!-- 합쳐지고 최소화된 최신 CSS -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
 -->
<!-- 부가적인 테마 -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js">
$(function() {
	 
	  $("#topBtn").click(function() {
	    $('html, body').animate({
	      scrollTop : 0
	    }, 200);
	    return false;
	  });
	});
</script>
 
<hr>
<div class="container">
<ul class="nav nav-phills nav-justified">
	<li role="presentation"><a href="b00_listsearch.do">공지사항</a></li>
	<li role="presentation"><a href="#qna">문의사항</a></li>
	<li role="presentation"><a href="#qna">이용약관</a></li>
	<li role="presentation"><a id="topBtn" href="#">TOP</a></li>
	
</ul>
</div>