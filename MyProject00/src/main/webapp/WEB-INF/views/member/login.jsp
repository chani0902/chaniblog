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
<title>로그인</title>
</head>

<body>
	<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
			<h1>
				로그인 <small>Login</small>
			</h1>
		</div>

		<form role="form" action="mv_loginOK.do" method="post">

			<table class="table table-hover">
				<tbody>

					<tr>
						<th scope="cols">ID</th>
						<td><input type="text" class="form-control" placeholder="ID"
							name="member_id" value="admin"></td>
					</tr>
					<tr>
						<th scope="cols">비밀번호</th>
						<td><input type="password" class="form-control"
							placeholder="Password" name="member_pw" value="hi123456"></td>
					</tr>
				</tbody>
			</table>
		<div>
			<input type="submit" value="로그인" class="btn btn-primary">
			<input type="button" value="회원 가입" onclick="location.href='mv_insert.do'" class="btn btn-primary">
		</div>
		<br>
		<div>
			<b><a href="/myproject/mv_findid.do">ID 또는 비밀번호를 잊으셨나요?</a></b>
		</div>
			<c:choose>
				<c:when test="${msg == '로그인 실패'}">
					<script>alert("로그인에 실패했습니다. ID 또는 비밀번호를 다시 한 번 확인해주세요.");</script>
				</c:when>
				 
 				<c:when test="${msg == '메일 인증 요청'}">
 					<script>alert("메일 인증이 완료되지 않았습니다! 관리자에게 문의해주세요");</script>
 				</c:when>
 				
 				<c:when test="${msg == '이용 정지 상태'}">
 					<script>alert("관리자에 의해 이용 정지된 상태입니다. 관리자에게 문의해주세요");</script>
 				</c:when>
 				
 				<c:when test="${msg == '메일 확인 요망'}">
 					<script>alert("비밀번호 변경 메일이 발송되었습니다. 확인해주세요.");</script>
 				</c:when>
 				
 				<c:when test="${msg == '변경 성공'}">
 					<script>alert("비밀번호 변경이 완료되었습니다. \n새로운 비밀번호로 로그인해주세요.");</script>
 				</c:when>

			</c:choose>

		</form>
	</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>

</html>