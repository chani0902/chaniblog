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
<title>${vo2.board01_title}</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  		<h1>${vo2.board01_title} </h1>
	</div>
	

	<form role="form" method="post" autocomplete="off">
		<input type="hidden" id="board01_num" name="board01_num" value="${vo2.board01_num}"	readonly="readonly">
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
				<td width="15%"><b>글 번호</b>&emsp;${vo2.board01_num}</td>
				<td><b>작성자</b>&emsp;${vo2.board01_writer}</td>
				
				<td width="15%"><b>조회수</b>&emsp;${vo2.board01_viewcnt}</td>
				
				<td width="30%"><b>작성일</b>&emsp;<fmt:formatDate value="${vo2.board01_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
			<td colspan="4" style="height:150px;">
			<div style="white-space:pre;"><c:out value="${vo2.board01_content}" /></div>
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

		<c:when test="${sessionScope.member_id == 'admin'}">
			<button id="delete_btn" class="btn btn-danger btn-space">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;삭제</button>
		</c:when>
	
	</c:choose>
	
	<!-- 관리자는 글 삭제까지 가능(수정 불가) -->

	<script>
		var formObj = $("form[role='form']");
		
		$("#list_btn").click(function(){
			self.location = "/myproject/b01_listsearch.do?page=${scri.page}&perPageNum=${scri.perPageNum}&searchKey=${scri.searchKey}&searchWord=${scri.searchWord}"
		});
		
		// 게시글 수정 버튼 클릭
		 $("#modity_btn").click(function(){
		  
		  formObj.attr("action", "/myproject/b01_update.do");
		  formObj.attr("method", "get");  
		  formObj.submit();     
		  
		 });
		 		 
		 // 게시글 삭제 버튼 클릭
		 $("#delete_btn").click(function(){
		  
		  formObj.attr("action", "/myproject/b01_delete.do");
		  formObj.attr("method", "get");  
		  formObj.submit();
		  
		 });
		 
		// 댓글 삭제 버튼 클릭
		 
	
	</script>
	<div>
	<hr>
<!-- 댓글을 패널에 감싸기(부트스트랩) -->
<div class="panel panel-info">
  <!-- Default panel contents -->
  <div class="panel-heading">
  <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>   <b>댓글</b>  <span style=color:purple>${vo2.reply_cnt}</span></div>
   		
  <!-- Table -->
   <table class="table table-striped">
  <c:forEach items="${repList}" var="repList">
  
 		<tbody>
			<tr>
			<c:choose>
				<c:when test="${vo2.writercheck == repList.writercheck}">
					<td width="40%"><b>작성자</b> : ${repList.reply01_writer} <span class="label label-primary">글쓴이</span></td>			
					<td width="30%"><b>작성일</b> : <fmt:formatDate value="${repList.reply01_regdate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
			<c:choose>
						<c:when test="${sessionScope.member_id == repList.writercheck}">
							<a role="button" id="rpupdate" class="btn btn-warning btn-space btn-xs" data-reply01_num="${repList.reply01_num}">
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;수정</a>
 							<button id="rpdelete" class="btn btn-danger btn-space btn-xs" data-reply01_num="${repList.reply01_num}">
 							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;삭제</button>
						</c:when>

						<c:when test="${sessionScope.member_id == 'admin'}">
							<button id="rpdelete" class="btn btn-danger btn-space btn-xs" data-reply01_num="${repList.reply01_num}">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;삭제</button>
						</c:when>
					</c:choose>
			</td>
				</c:when>
				<c:otherwise>
					<td width="40%"><b>작성자</b> : ${repList.reply01_writer}</td>			
					<td width="30%"><b>작성일</b> : <fmt:formatDate value="${repList.reply01_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
			<c:choose>
						<c:when test="${sessionScope.member_id == repList.writercheck}">
							<a role="button" id="rpupdate" class="btn btn-warning btn-space btn-xs" data-reply01_num="${repList.reply01_num}">
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;수정</a>
 							<button id="rpdelete" class="btn btn-danger btn-space btn-xs" data-reply01_num="${repList.reply01_num}">
 							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;삭제</button>
						</c:when>

						<c:when test="${sessionScope.member_id == 'admin'}">
							<button id="rpdelete" class="btn btn-danger btn-space btn-xs" data-reply01_num="${repList.reply01_num}">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;삭제</button>
						</c:when>
					</c:choose>
			</td>
						
				</c:otherwise>
				</c:choose>
		
				<script>
 				 $("#rpupdate").click(function(){
  				  self.location = "/myproject/r01_update.do?board01_num=${vo2.board01_num}"
  				  + "&page=${scri.page}"
   				  + "&perPageNum=${scri.perPageNum}"
   				  + "&searchKey=${scri.searchKey}"
   				  + "&searchWord=${scri.searchWord}"
   				  + "&reply01_num=" + $(this).attr("data-reply01_num");        
 				 });
 				
				  $("#rpdelete").click(function(){
  			      self.location = "/myproject/r01_delete.do?board01_num=${vo2.board01_num}"
  		 		   + "&page=${scri.page}"
   		   	 	   + "&perPageNum=${scri.perPageNum}"
  				   + "&searchKey=${scri.searchKey}"
   				   + "&searchWord=${scri.searchWord}"
  				   + "&reply01_num=" + $(this).attr("data-reply01_num"); 
 				 });       
				 </script>
			</tr>
			<tr>
			
			<td colspan="3"><div style="white-space:pre;"><c:out value="${repList.reply01_content}" /></div></td>
			</tr>			
		</tbody>
			
	</c:forEach>
	</table>
</div>
</div>

<!-- 댓글 부분 -->
<div>
	<section class="replyForm">
	<form role="form" method="post" autocomplete="off">
	<input type="hidden" id="board01_num" name="board01_num" value="${vo2.board01_num}" readonly="readonly"/>
	<input type="hidden" id="page" name="page" value="${scri.page}" readonly="readonly" />
 	<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}" readonly="readonly" />
 	<input type="hidden" id="searchKey" name="searchKey" value="${scri.searchKey}" readonly="readonly" />
 	<input type="hidden" id="searchWord" name="searchWord" value="${scri.searchWord}" readonly="readonly" />
 	<input type="hidden" id="writercheck" name="writercheck" value="${sessionScope.member_id}" readonly="readonly" />
	
<!-- 댓글 작성 부분 -->
	<label for="reply01_writer">작성자</label><input type="text" id="reply01_writer" name="reply01_writer" class="form-control" value="${sessionScope.member_nickname}" readonly="readonly"/><p></p>
 	<p><label for="reply01_content">댓글 내용</label><textarea id="reply01_content" name="reply01_content" placeholder="댓글 내용을 입력하세요" class="form-control" rows="3"></textarea></p>
 	<p>
	<button type="button" class="repSubmit btn btn-success">
	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;작성</button>
	<script>
  var formObj = $(".replyForm form[role='form']");
        
  $(".repSubmit").click(function(){
   formObj.attr("action", "/myproject/rp_insert.do");
   formObj.submit();
  });
  
  </script>
	</p>
	</form>
	
	</section>
</div>
</div>
<c:choose>
			<c:when test="${msg == '댓글 수정 성공'}">
				<script>
					alert("댓글 수정을 성공적으로 마쳤습니다!");
				</script>
			</c:when>
			
			<c:when test="${msg == '댓글 수정 실패'}">
				<script>
					alert("댓글이 정상적으로 수정되지 않았습니다. 다시 시도해주세요.");
				</script>
			</c:when>
			
			<c:when test="${msg == '댓글 삭제 성공'}">
				<script>
					alert("댓글이 성공적으로 삭제되었습니다.");
				</script>
			</c:when>
			
			<c:when test="${msg == '댓글 삭제 실패'}">
				<script>
					alert("댓글이 정상적으로 삭제되지 않았습니다. 다시 시도해주세요.");
				</script>
			</c:when>

		</c:choose>
		<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>