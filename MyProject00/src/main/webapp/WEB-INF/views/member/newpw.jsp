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
<title>새 비밀번호 설정</title>
</head>
<body>
<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  <h1>새 비밀번호 설정 <small>Set a new Password</small></h1>
</div>

	
	<form action="mv_newpwOK.do" method="post">
		<input type="hidden" name="member_id" value="${vo2.member_id}" >

		<table class="table table-bordered">
	
		<tbody>
			
			<tr class="active">
				<th scope="cols">ID</th>
				<td> ${vo2.member_id}</td>
			</tr>
			<tr>
				<th scope="cols">비밀번호</th>
				<td><input type="password" class="form-control" id="member_pw" name="member_pw" placeholder="변경할 비밀번호를 입력하세요"></td>
			</tr>

		</tbody>
	</table>
		<input type="submit" id="udsubmit" value="변경 완료" class="btn btn-success" disabled="disabled">
	</form>
	<!-- 비밀번호를 새로 입력해야 수정 완료를 클릭할 수 있게 함 -->
	<script type="text/javascript">
		$(function(){
			$("#member_pw").on('input',function(){
				if($("#member_pw").val() == '')
					$("#udsubmit").attr("disabled", "disabled");
				else
					$("#udsubmit").removeAttr("disabled");
			});
		})
	
	</script>
	
</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>