<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<style type="text/css">

	.btn-space{
	margin-right : 4px;
	}

</style>
<title>${vo2.board00_title}</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  		<h1>${vo2.board00_title} </h1>
	</div>
	

	<form role="form" method="post" autocomplete="off">
		<input type="hidden" id="board00_num" name="board00_num" value="${vo2.board00_num}"	readonly="readonly">
		<input type="hidden" id="page" name="page" value="${scri.page}"
			readonly="readonly"> <input type="hidden" id="perPageNum"
			name="perPageNum" value="${scri.perPageNum}" readonly="readonly">
		<input type="hidden" id="searchKey" name="searchKey"
			value="${scri.searchKey}" readonly="readonly"> <input
			type="hidden" id="searchWord" name="searchWord"
			value="${scri.searchWord}" readonly="readonly">
	</form>

	<table class="table table-bordered">
		
		<tbody>

			<tr class="active">
				<td width="15%"><b>공지사항 번호</b>&emsp;${vo2.board00_num}</td>
				<td><b>작성자</b>&emsp;${vo2.board00_writer}</td>
				
				<td width="15%"><b>조회수</b>&emsp;${vo2.board00_viewcnt}</td>
				
				<td width="30%"><b>작성일</b>&emsp;<fmt:formatDate value="${vo2.board00_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
			<td colspan="4" style="height:150px;">
			<div style="white-space:pre;"><c:out value="${vo2.board00_content}" /></div>
			</td>
			
			</tr>
		</tbody>
	</table>
		<button id="list_btn" class="btn btn-primary btn-space">
		<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>&nbsp;목록</button>

	<c:choose>
		<c:when test="${sessionScope.member_id == vo2.writercheck}">
			<button id="modity_btn" class="btn btn-warning btn-space">
			<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;수정</button>
 			<button id="delete_btn" class="btn btn-danger btn-space">
 			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;삭제</button>
		</c:when>
	
	</c:choose>
	
	<!-- 관리자는 글 삭제까지 가능(수정 불가) -->

	<script>
		var formObj = $("form[role='form']");
		
		$("#list_btn").click(function(){
			self.location = "/myproject/b00_listsearch.do?page=${scri.page}&perPageNum=${scri.perPageNum}&searchKey=${scri.searchKey}&searchWord=${scri.searchWord}"
		});
		
		// 게시글 수정 버튼 클릭
		 $("#modity_btn").click(function(){
		  
		  formObj.attr("action", "/myproject/b00_update.do");
		  formObj.attr("method", "get");  
		  formObj.submit();     
		  
		 });
		 		 
		 // 게시글 삭제 버튼 클릭
		 $("#delete_btn").click(function(){
		  
		  formObj.attr("action", "/myproject/b00_delete.do");
		  formObj.attr("method", "get");  
		  formObj.submit();
		  
		 });
		 	
	</script>
	<div>
	<hr>

</div>
</div>			
		<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>