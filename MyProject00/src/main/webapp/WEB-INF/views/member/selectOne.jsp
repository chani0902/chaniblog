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
<script src="resources/js/jquery-3.6.0.min.js"></script>
<title>회원 정보</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
  <h1>회원 정보 <small>Profile</small></h1>
</div>
	<div class="panel panel-success">
  
 	 <div class="panel-heading"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span><b>&emsp;회원 정보와 활동</b></div>
 	 <div class="panel-body">

		<table class="table">
			<tr>
				<td width="30%">
					<img width = 100px src="resources/uploadimg/${vo2.member_img}" class="img-circle" style="vertical-align:middle;">
					 <span style="margin-top:20px;margin-left:15px;font-size:25px;font-weight:bold;" >
					 ${vo2.member_nickname}
					 </span>
				</td>
				<td width="40%" style="vertical-align:middle;text-align:center;">
					<span style="font-size:35px;font-weight:bold;" >
					 ${vo2.member_point}
					 </span><br>
					 <span style="font-size:20px;">points</span>
				</td>
				<td style="vertical-align:middle;text-align:right;">
					<c:choose>
						<c:when test="${sessionScope.member_id == vo2.member_id}">
							<a href="mv_update.do?member_id=${vo2.member_id}" class="btn btn-info">
							회원 정보 수정</a><br>
						</c:when>

						<c:when test="${sessionScope.member_id == 'admin'}">
							<a href="mv_delete.do?member_id=${vo2.member_id}" class="btn btn-danger">
							회원 강제 탈퇴</a>
						</c:when>
	
					</c:choose>
				</td>
			</tr>
		</table>
	 
  	</div>
		<div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs nav-justified" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><b>회원 정보</b></a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"><b>작성 글</b></a></li>
    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab"><b>작성 댓글</b></a></li>
    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab"><b>settings</b></a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
		<table class="table">
		<thead>
		</thead>
		<tbody>
			<tr>
				<th style="vertical-align:middle;" scope="cols" class="info" width="25%">ID</th>
				<td style="vertical-align:middle;">${vo2.member_id}</td>				
			</tr>
			<tr>
				<th style="vertical-align:middle;" scope="cols" class="warning">닉네임</th>
				<td style="vertical-align:middle;">${vo2.member_nickname}</td>		
			</tr>
			<tr>
				<th style="vertical-align:middle;" scope="cols" class="info">이메일</th>
				<td style="vertical-align:middle;">${vo2.member_email}</td>	
			</tr>
			<tr>
				<th style="vertical-align:middle;" scope="cols" class="warning">포인트</th>
				<td style="vertical-align:middle;">${vo2.member_point}</td>	
			</tr>
			<tr>
				<th style="vertical-align:middle;" scope="cols" class="info">프로필 이미지</th>
				<td style="vertical-align:middle;"><img width = 75px src="resources/uploadimg/${vo2.member_img}" class="img-thumbnail"></td>	
			</tr>
			<tr>
				<th style="vertical-align:middle;" scope="cols" class="warning">가입일</th>
				<td style="vertical-align:middle;"><fmt:formatDate value="${vo2.member_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>	
			</tr>
		</tbody>
	</table>
	</div>
    <div role="tabpanel" class="tab-pane" id="profile">...</div>
    <div role="tabpanel" class="tab-pane" id="messages">...</div>
    <div role="tabpanel" class="tab-pane" id="settings">...</div>
  </div>

</div>
	
	</div>
		
		
	<%-- 	?member_num=${vo2.member_num}&member_id=${vo2.member_id}
					&member_pw=${vo2.member_pw}&member_nickname=${vo2.member_nickname}
					&member_email=${vo2.member_email}&member_img=${vo2.member_img} --%>
		
	
	

</div>
	<c:choose>
	 <c:when test="${msg == false}">
 		<script>alert("회원 정보 수정에 실패했습니다");</script>
 	</c:when>
 
 	<c:when test="${msg == true}">
 		<script>alert("회원 정보 수정을 성공적으로 마쳤습니다 :)");</script>
 	</c:when>
	</c:choose>

</body>
</html>