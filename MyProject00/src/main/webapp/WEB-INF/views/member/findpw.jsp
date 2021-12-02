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
<title>비밀번호 찾기</title>
</head>

<body>
	<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
			<h1>
				비밀번호 찾기 <small>Find your Password</small>
			</h1>
		</div>

		<form role="form" action="mv_findpwOK.do" method="post">

			<table class="table table-hover">
				<tbody>

					<tr>
						<th scope="cols">ID</th>
						<td width="75%"><input type="text" class="form-control" placeholder="ID를 입력해주세요"
							id="member_id" name="member_id">
						</td>
						
					</tr>
					<tr>
						<th scope="cols">이메일</th>
						<td width="75%"><input type="text" class="form-control" placeholder="이메일을 입력해주세요"
							id="member_email" name="member_email">
						</td>
						
					</tr>
				</tbody>
			</table>

			<input type="submit" value="비밀번호 찾기" class="btn btn-primary">
		<c:choose>
				<c:when test="${msg == '재확인 요청'}">
					<script>alert("입력하신 정보와 일치하는 회원이 존재하지 않습니다.\n \n다시 한 번 정확히 입력해주세요.");</script>
				</c:when>

			</c:choose>	
		</form>
	</div>

</body>

</html>