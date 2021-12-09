<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>회원 가입 완료</title>
</head>

<body>
	<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
			<h1>
				회원 가입 완료 <small>Register Success</small>
			</h1>
			<hr>
			<img class="img-responsive" alt="Responsive image" src="resources/img/welcome2.jpg">
			<br>
			
			<h3>환영합니다! ${param.member_id}님</h3>
			<br>
			<h5>
			<p>DamSo - community site</p>
			<br>
			<p>회원가입이 정상적으로 이루어 졌습니다.</p>
			<br>
			<p>로그인 하시면 홈페이지 내의 모든 서비스를 이용하실 수 있습니다.</p></h5>
			<br>
			<a href="mv_login.do" type="button" class="btn btn-info">로그인 페이지로 이동</a>
			
		</div>

		
		

		
	</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>