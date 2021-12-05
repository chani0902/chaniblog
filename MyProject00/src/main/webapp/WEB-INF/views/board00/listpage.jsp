<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/list.css">
<link rel="stylesheet" href="resources/css/menu.css">
<link rel="stylesheet" href="resources/css/button.css">
<link rel="stylesheet" href="resources/css/form.css">
<title>전체 글 목록 + 페이지</title>

<script src="resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>전체 글 목록 + 페이지</h1>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<form action="b01_searchList.do">
		<select name="searchKey">
			<option value="board01_title">제목</option>
			<option value="board01_content">내용</option>
			<option value="board01_writer">작성자</option>
		</select> <input type="text" name="searchWord" value=""> <input
			type="submit" value="검색" class="myButton">

	</form>

	<table class="type11">
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
					<td><fmt:formatDate value="${vo.board01_regdate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<ul>
			<c:if test="${pageMaker.prev}">
				<li><a
					href="b01_listpage.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<li><a href="b01_listpage.do${pageMaker.makeQuery(idx)}">${idx}</a></li>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a
					href="b01_listpage.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
			</c:if>
		</ul>
	</div>
	<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>