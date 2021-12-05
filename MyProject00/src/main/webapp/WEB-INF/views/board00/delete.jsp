<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>공지사항 삭제</title>
</head>
<body>
<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  		<h1>공지사항 삭제 <small>Delete your Notice</small></h1>
	</div>

	
	<form action="b00_deleteOK.do" method="post">
	<input type="hidden" name="board00_num" value="${vo2.board00_num}" >
		<input type="hidden" name="board00_writer" value="${vo2.board00_writer}" >
		<input type="hidden" name="board00_viewcnt" value="${vo2.board00_viewcnt}" >
		<input type="hidden" id="page" name="page" value="${scri.page}"
			readonly="readonly"> <input type="hidden" id="perPageNum"
			name="perPageNum" value="${scri.perPageNum}" readonly="readonly">
		<input type="hidden" id="searchKey" name="searchKey"
			value="${scri.searchKey}" readonly="readonly"> <input
			type="hidden" id="searchWord" name="searchWord"
			value="${scri.searchWord}" readonly="readonly">

	<h4>공지사항 번호 : ${vo2.board00_num}</h4>
	<br>
	<h4>정말 삭제하시겠습니까?<br><br><small>삭제된 공지사항은 복구할 수 없습니다</small></h4>
	<br>
	<br>
	<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
	&nbsp;삭제</button>
	<a href="/myproject/b00_selectOne.do?board00_num=${vo2.board00_num}&page=${scri.page}&perPageNum=${scri.perPageNum}&searchKey=${scri.searchKey}&searchWord=${scri.searchWord}" class="btn btn-primary"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>&nbsp;취소</a>
	</form>
</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>	
</body>
</html>