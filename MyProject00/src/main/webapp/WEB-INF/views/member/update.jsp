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
<title>회원 정보 수정</title>
</head>
<body>
<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  <h1>회원 정보 수정 <small>Modify your Profile</small></h1>
</div>

	
<!-- 	update.jsp에서  숨겨진 input으로 번호, 아이디, 이미지 이름까지 주고 이미지 파일 업로드에 기본값을 이미 설정된 이미지로 주고
		컨트롤러에서 updateOK 부분에 파일을 넣지 않더라도 update가 진행되게 설정하니 모든 문제가 해결되었다-->
	<form action="mv_updateOK.do" method="post" enctype="multipart/form-data">
	<div class="panel panel-success">
  
 	 <div class="panel-heading"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><b>&emsp;회원 정보 수정</b></div>
 	 <div class="panel-body">
 	 	<p>비밀번호 변경을 원하시면 새로운 비밀번호를 입력해주세요!</p>
 	 </div>
	
	
		<input type="hidden" name="member_num" value="${vo2.member_num}" >
		<input type="hidden" name="member_id" value="${vo2.member_id}" >
		<input type="hidden" name="member_email" value="${vo2.member_email}" >
		<input type="hidden" name="member_img" value="${vo2.member_img}" >
		<table class="table">
	
		<tbody>
			
			<tr>
				<th scope="cols">ID</th>
				<td>${vo2.member_id}</td>
			</tr>
			<tr>
				<th scope="cols">비밀번호</th>
				<td><input type="password" class="form-control" id="member_pw" name="member_pw" placeholder="비밀번호를 꼭 입력해주세요"></td>
			</tr>
			<tr>
				<th scope="cols">닉네임</th>
				<td><input type="text" class="form-control" name="member_nickname" value="${vo2.member_nickname}"></td>
			</tr>
			<tr>
				<th scope="cols">이메일</th>
				<td>${vo2.member_email}</td>
			</tr>
			<tr>
				<th scope="cols">프로필 이미지</th>
				<td><img width = 100px src="resources/uploadimg/${vo2.member_img}"></td>
			</tr>
			<tr>
				<th scope="cols">프로필 이미지 변경</th>
				<td><input type="file" class="form-control-file" class="img-thumbnail" name="multipartFile" value="resources/uploadimg/${vo2.member_img}"></td>
			</tr>
		</tbody>
	</table>
	</div>
	<c:choose>
						<c:when test="${sessionScope.member_id == 'admin'}">
							<button type="submit" id="udsubmit" class="btn btn-success" disabled="disabled"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
							&nbsp;수정 완료</button>
						</c:when>
						
						<c:when test="${sessionScope.member_id == vo2.member_id}">
							<button type="submit" id="udsubmit" class="btn btn-success" disabled="disabled"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
							&nbsp;수정 완료</button>
							<a href="mv_delete.do?member_id=${vo2.member_id}" class="btn btn-danger">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							&nbsp;회원 탈퇴</a>
						</c:when>
		
					</c:choose>	
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