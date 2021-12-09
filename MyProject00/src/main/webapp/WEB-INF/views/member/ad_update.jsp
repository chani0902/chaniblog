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
<title>회원 정보 수정</title>
</head>
<body>
<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  <h1>회원 상태 수정 <small>Modify Member's Profile</small></h1>
</div>

	<form action="mv_ad_updateOK.do" method="post" enctype="multipart/form-data">
	<div class="panel panel-success">
  
 	 <div class="panel-heading"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><b>&emsp;회원 정보 수정</b></div>
 	 <div class="panel-body">
 	 	<p>회원 포인트와 상태 수정이 가능합니다!</p>
 	 </div>
			<input type="hidden" name="member_num" value="${vo2.member_num}" >
			<input type="hidden" name="member_id" value="${vo2.member_id}" >
			<input type="hidden" name="member_email" value="${vo2.member_email}" >
			<input type="hidden" name="member_img" value="${vo2.member_img}" >
	
		<table class="table">
	
		<tbody>
			
			<tr>
				<th scope="cols">ID</th>
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
				<td><img width = 100px src="resources/uploadimg/${vo2.member_img}"></td>
			</tr>
			<tr>
				<th scope="cols">포인트</th>
				<td><input type="number" min="0" class="form-control" id="member_point" name="member_point" value="${vo2.member_point}"></td>
			</tr>
			<tr>
				<th scope="cols">회원 상태</th>
				<td>
				<c:choose>
					<c:when test="${vo2.authStatus == 0}">
						<input type="radio" id="authStatus" name="authStatus" value="0" checked="checked">
  						<label for="authStatus">메일 미인증</label>&emsp;
  						 <input type="radio" id="authStatus" name="authStatus" value="1">
  						<label for="authStatus">정상</label>&emsp;
  						<input type="radio" id="authStatus" name="authStatus" value="2">
  						<label for="authStatus">계정 정지</label>
					</c:when>
					<c:when test="${vo2.authStatus == 1}">
						<input type="radio" id="authStatus" name="authStatus" value="0">
  						<label for="authStatus">메일 미인증</label>&emsp;
  						 <input type="radio" id="authStatus" name="authStatus" value="1" checked="checked">
  						<label for="authStatus">정상</label>&emsp;
  						<input type="radio" id="authStatus" name="authStatus" value="2">
  						<label for="authStatus">계정 정지</label>
					</c:when>
					<c:when test="${vo2.authStatus == 2}">
						<input type="radio" id="authStatus" name="authStatus" value="0">
  						<label for="authStatus">메일 미인증</label>&emsp;
  						 <input type="radio" id="authStatus" name="authStatus" value="1">
  						<label for="authStatus">정상</label>&emsp;
  						<input type="radio" id="authStatus" name="authStatus" value="2" checked="checked">
  						<label for="authStatus">계정 정지</label>
					</c:when>		
				</c:choose>
  				</td>
			</tr>
		</tbody>
	</table>
	</div>
		<button type="submit" id="udsubmit" class="btn btn-success"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
		&nbsp;수정 완료</button>
	</form>
	
	
	
</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>