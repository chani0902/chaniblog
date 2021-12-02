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
<title>새 글 쓰기</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  <h1>새 글 쓰기 <small>Write a New Post</small></h1>
</div>
	
	
	<form action="b01_insertOK.do" method = "post" enctype="multipart/form-data">
	<input type="hidden" id="writercheck" name="writercheck" value="${sessionScope.member_id}" >		
		<table class="table table-striped">
		<tbody>

			<tr>
				<th scope="cols">제목</th>
				<td><input type="text" class="form-control" placeholder="제목을 입력하세요" name="board01_title" value="dummy_title"></td>
			</tr>
			<tr>
				<th scope="cols">작성자</th>
				<td><input type="text" class="form-control" placeholder="Text input" name="board01_writer" value="${member.member_nickname}" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="cols">내용</th>
				<td><textarea class="form-control" placeholder="내용을 입력하세요" rows="5" cols="50" name="board01_content">dummy_content</textarea></td>
			</tr>
		</tbody>
	</table>
		<input type="submit" value="입력 완료" class="btn btn-success">
	</form>

</div>
</body>
</html>