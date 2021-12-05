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
<title>새 공지사항 쓰기</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  <h1>새 공지사항 쓰기 <small>Write a New Notice</small></h1>
</div>
	
	
	<form action="b00_insertOK.do" method = "post" enctype="multipart/form-data">
	<input type="hidden" id="writercheck" name="writercheck" value="${sessionScope.member_id}" >		
		<table class="table table-striped">
		<tbody>

			<tr>
				<th scope="cols">제목</th>
				<td><input type="text" class="form-control" placeholder="제목을 입력하세요" name="board00_title" value="dummy_title"></td>
			</tr>
			<tr>
				<th scope="cols">작성자</th>
				<td><input type="text" class="form-control" placeholder="Text input" name="board00_writer" value="${member.member_nickname}" readonly="readonly"></td>
			</tr>
			<tr>
				<th scope="cols">내용</th>
				<td><textarea class="form-control" placeholder="내용을 입력하세요" rows="15" cols="100%" name="board00_content">dummy_content</textarea></td>
			</tr>
		</tbody>
	</table>
		<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
		입력 완료</button>
	</form>

</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>