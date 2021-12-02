<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>전체 글 목록</title>

<script src="resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
	<h1>전체 글 목록</h1>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<form action="b01_searchList.do">
		<select name="searchKey">
			<option value="board01_title">제목</option>
			<option value="board01_content">내용</option>
			<option value="board01_writer">작성자</option>
		</select>
		<input type="text" name="searchWord" value="">
		<input type="submit" value="검색" class="myButton">
		
	</form>

	<table class="table">
		<thead>
			<tr>
				<th scope="cols">글 번호</th>
				<th scope="cols">제목</th>
				<th scope="cols">작성자</th>
				<th scope="cols">조회수</th>
				<th scope="cols">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list}">

				<tr>
					<td>${vo.board01_num}</td>
					<td><a href="b01_selectOne.do?board01_num=${vo.board01_num}">${vo.board01_title}</a></td>
					<td>${vo.board01_writer}</td>
					<td>${vo.board01_viewcnt}</td>
					<td>
						<fmt:formatDate value="${vo.board01_regdate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>