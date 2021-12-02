<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>자유 게시판</title>

<script src="resources/js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
			<h1>
				자유 게시판 <small>Free Board</small>
			</h1>
		</div>
		
		<a href="b01_insert.do" class="btn btn-info pull-right">새 글 쓰기</a>

		<table class="table table-hover">
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
						<td><a
							href="b01_selectOne.do?board01_num=${vo.board01_num}&page=${scri.page}&perPageNum=${scri.perPageNum}&searchKey=${scri.searchKey}&searchWord=${scri.searchWord}">${vo.board01_title}</a>
							<c:if test="${vo.reply_cnt != 0}">
								<small><span class="label label-info"><c:out value="${vo.reply_cnt}"/></span></small>
							</c:if>
							</td>
						<td>${vo.board01_writer}</td>
						<td>${vo.board01_viewcnt}</td>
						<td><fmt:formatDate value="${vo.board01_regdate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>






		<div class="search row">
			<div class="col-xs-2 col-sm-2">
				<select name="searchKey" class="form-control">
					<option value="n"
						<c:out value="${scri.searchKey == null ? 'selected' : ''}"/>>--선택--</option>
					<option value="t"
						<c:out value="${scri.searchKey eq 't' ? 'selected' : ''}"/>>제목</option>
					<option value="c"
						<c:out value="${scri.searchKey eq 'c' ? 'selected' : ''}"/>>내용</option>
					<option value="w"
						<c:out value="${scri.searchKey eq 'w' ? 'selected' : ''}"/>>작성자</option>
				</select>
			</div>

			<div class="col-xs-10 col-sm-10">
				<div class="input-group">
					<input type="text" name="searchWord" id="keywordInput"
						value="${scri.searchWord}" class="form-control" /> <span
						class="input-group-btn">
						<button id="searchBtn" class="btn btn-default">검색</button>
					</span>
				</div>
			</div>
			<script>
				$(function() {
					$('#searchBtn').click(
							function() {
								self.location = "b01_listsearch.do"
										+ '${pageMaker.makeQuery(1)}'
										+ "&searchKey="
										+ $("select option:selected").val()
										+ "&searchWord="
										+ encodeURIComponent($('#keywordInput')
												.val());
							});
				});
			</script>
		</div>

		<c:choose>
			<c:when test="${msg == '글 작성 성공'}">
				<script>
					alert("글이 성공적으로 등록되었습니다!");
				</script>
			</c:when>
			<c:when test="${msg == '글 수정 성공'}">
				<script>
					alert("글 수정을 성공적으로 마쳤습니다!");
				</script>
			</c:when>
			<c:when test="${msg == '글 삭제 성공'}">
				<script>
					alert("작성 글이 삭제되었습니다.");
				</script>
			</c:when>

		</c:choose>

		<div class="col-md-offset-3">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li><a
						href="b01_listsearch.do${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
				</c:if>

				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
					var="idx">
					<li
						<c:out value="${pageMaker.cri.page == idx ? 'class=active' : '' }"/>>
						<a href="b01_listsearch.do${pageMaker.makeSearch(idx)}">${idx}</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li><a
						href="b01_listsearch.do${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>