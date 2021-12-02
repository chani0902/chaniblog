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
<title>작성 댓글 수정</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  <h1>작성 댓글 수정 <small>Edit your Comment</small></h1>
</div>
	
	
	<form action="r01_updateOK.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="board01_num" value="${vo.board01_num}" >
		<input type="hidden" name="reply01_num" value="${vo.reply01_num}" >
		<input type="hidden" name="writercheck" value="${vo.writercheck}" >
		<input type="hidden" id="page" name="page" value="${scri.page}"
			readonly="readonly"> <input type="hidden" id="perPageNum"
			name="perPageNum" value="${scri.perPageNum}" readonly="readonly">
		<input type="hidden" id="searchKey" name="searchKey"
			value="${scri.searchKey}" readonly="readonly"> <input
			type="hidden" id="searchWord" name="searchWord"
			value="${scri.searchWord}" readonly="readonly">
		<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="cols">댓글 번호</th>
				<th scope="cols">작성자</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td>${vo.reply01_num}</td>
				<td>${vo.reply01_writer}</td>
			</tr>
			<tr>
			<td colspan="2"><textarea class="form-control" rows="5" cols="100%" name="reply01_content">${vo.reply01_content}</textarea></td>
			</tr>
		</tbody>
	</table>
	
		<button type="submit" class="btn btn-success">수정</button> <a href="/myproject/b01_selectOne.do?board01_num=${vo.board01_num}&page=${scri.page}&perPageNum=${scri.perPageNum}&searchKey=${scri.searchKey}&searchWord=${scri.searchWord}" class="btn btn-primary">취소</a>

   
	</form>
</div>	
</body>
</html>