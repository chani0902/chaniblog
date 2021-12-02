<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>회원 정보</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
  <h1>회원 정보 <small>Profile</small></h1>
</div>
	

	<table class="table table-bordered">
		<thead>
			
			
		</thead>
		<tbody>
			<tr>
				<th scope="cols" class="col-xs-6 col-sm-3">ID</th>
				<td>${vo2.member_id}</td>				
			</tr>
			<tr>
				<th scope="cols">닉네임</th>
				<td>${vo2.member_nickname}</td>		
			</tr>
			<tr>
				<th scope="cols">이메일</th>
				<td>${vo2.member_email}</td>	
			</tr>
			<tr>
				<th scope="cols">프로필 이미지</th>
				<td><img width = 50px src="resources/uploadimg/${vo2.member_img}" class="img-thumbnail"></td>	
			</tr>
		</tbody>
	</table>
		
		
	<%-- 	?member_num=${vo2.member_num}&member_id=${vo2.member_id}
					&member_pw=${vo2.member_pw}&member_nickname=${vo2.member_nickname}
					&member_email=${vo2.member_email}&member_img=${vo2.member_img} --%>
		
	<c:choose>
		<c:when test="${sessionScope.member_id == vo2.member_id}">
			<a href="mv_update.do?member_id=${vo2.member_id}" 
				class="btn btn-info">회원 정보 수정</a>
		</c:when>

		<c:when test="${sessionScope.member_id == 'admin'}">
			<a href="mv_delete.do?member_id=${vo2.member_id}" class="btn btn-danger">회원 강제 탈퇴</a>
		</c:when>
	
	</c:choose>
	
</div>

	<c:choose>
	 <c:when test="${msg == false}">
 		<script>alert("회원 정보 수정에 실패했습니다");</script>
 	</c:when>
 
 	<c:when test="${msg == true}">
 		<script>alert("회원 정보 수정을 성공적으로 마쳤습니다 :)");</script>
 	</c:when>
	</c:choose>

</body>
</html>