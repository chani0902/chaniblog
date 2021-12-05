<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="resources/js/jquery-3.6.0.min.js"></script>
<title>회원 탈퇴</title>
</head>
<body>
	<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
		<h1>
			회원 탈퇴 <small>Delete Your Account</small>
		</h1>
	</div>
		
	<h4>ID : ${param.member_id}</h4>
	<br>
	<h4>이 계정을 정말로 탈퇴하시겠습니까?<br><br><small>탈퇴한 계정은 절대 복구할 수 없습니다</small></h4>
	<br>
	<br>
	<br>
	
	<br>
	<a href="mv_deleteOK.do?member_id=${param.member_id}" class="btn btn-danger">
	<span class="glyphicon glyphicon-erase" aria-hidden="true"></span>
	&nbsp;탈퇴</a>
	<a href="mv_selectOne.do?member_id=${param.member_id}" class="btn btn-primary">
	<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
	&nbsp;취소</a>
	</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>