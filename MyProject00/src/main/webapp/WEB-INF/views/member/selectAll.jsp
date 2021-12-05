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
<title>전체 회원 목록</title>

<script src="resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
  <h1>전체 회원 목록 <small>All Account List</small></h1>
</div>
		
		

		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="cols">회원 번호</th>
					<th scope="cols">프로필 이미지</th>
					<th scope="cols">ID</th>
					<th scope="cols">닉네임</th>
					<th scope="cols">이메일</th>
					<th scope="cols">포인트</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list}">

					<tr>
						<td>${vo.member_num}</td>
						<td><img width="50px"
							src="resources/uploadimg/${vo.member_img}" class="img-thumbnail"></td>
						<td><a href="mv_selectOne.do?member_id=${vo.member_id}">${vo.member_id}</a></td>
						<td>${vo.member_nickname}</td>
						<td>${vo.member_email}</td>
						<td>${vo.member_point}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="search row">
			<div class="col-xs-2 col-sm-2">
		<form action="mv_searchList.do">
				<select name="searchKey" class="form-control">
					<option value="member_nickname">닉네임</option>
					<option value="member_id">ID</option>
				</select>
			</div>
				<div class="col-xs-10 col-sm-10">
				<div class="input-group">
						<input type="text" name="searchWord" value="" class="form-control">
						<span class="input-group-btn"> 
							<input type="submit" value="검색" class="btn btn-default">
						</span>
					</div>
				</div>
			</form>
		</div>
	</div>
	 <c:if test="${msg == '회원 강퇴 성공'}">
 	<script>alert("성공적으로 탈퇴시켰습니다!");</script>
 </c:if>
 <!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>