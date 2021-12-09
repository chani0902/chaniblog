<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&emsp;회원 정보 수정</a><br>
						</c:when>

						<c:when test="${sessionScope.member_id == 'admin'}">
							<a href="mv_ad_update.do?member_id=${vo2.member_id}" class="btn btn-info">
							<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&emsp;회원 상태 수정</a><br>
							<a href="mv_delete.do?member_id=${vo2.member_id}" class="btn btn-danger" style="margin-top:10px;">
							<span class="glyphicon glyphicon-erase" aria-hidden="true"></span>&emsp;회원 강제 탈퇴</a>
						</c:when>
	
					</c:choose>
				</td>
			</tr>
		</table>
	 
  	</div>
		<div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs nav-justified" role="tablist">
    <li role="presentation" class="active">
    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">
    	<b><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&emsp;회원 정보</b></a></li>
    <li role="presentation">
    	<a href="#mypost" aria-controls="mypost" id="bemypost" role="tab" data-toggle="tab">
    	<b><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&emsp;작성 글</b></a></li>
    <li role="presentation">
   	 	<a href="#mycomment" aria-controls="mycomment" role="tab" data-toggle="tab">
   	 	<b><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&emsp;작성 댓글</b></a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane fade in active" id="home">
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
    <div role="tabpanel" class="tab-pane fade" id="mypost">
		<table class="table table-hover">
			<thead>
				<tr class="info">
					<th width="10%" scope="cols">글 번호</th>
					<th width="50%" scope="cols">제목</th>
					<th scope="cols">작성자</th>
					<th scope="cols">조회수</th>
					<th scope="cols">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list2}">

					<tr>
						<td>${vo.board01_num}</td>
						<td><a
							href="b01_selectOne.do?board01_num=${vo.board01_num}">${vo.board01_title}</a>
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
	</div>
    <div role="tabpanel" class="tab-pane fade" id="mycomment">
		<div class="panel panel-info">
  			<div class="panel-heading"><h5>글 번호를 클릭하면 해당 글로 이동할 수 있습니다</h5></div>
 			 <div class="panel-body">
  			  <table class="table table-hover">
			<thead>
				<tr>
					<th width="10%" scope="cols">글 번호</th>
					<th  scope="cols">댓글</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list3}">
					<tr>
					<c:choose>
					<c:when test="${vo.board01_num != 0}">
						<td style="vertical-align:middle;">
						<h4><a href="b01_selectOne.do?board01_num=${vo.board01_num}">${vo.board01_num}</a></h4></td>
					</c:when>
					<c:when test="${vo.board01_num == 0}">
						<td style="vertical-align:middle;">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						삭제된 글</td>
					</c:when>
					</c:choose>	
						<td style="vertical-align:middle;"><button type="button" class="btn btn btn-success" disabled="disabled">
						<div style="white-space:pre;text-align:left;"><c:out value="${vo.reply01_content}" /></div>
						</button>
						&emsp;<small style="vertical-align:bottom;"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
						<fmt:formatDate value="${vo.reply01_regdate}"
								pattern="yyyy-MM-dd" />
						</small>
							</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
  			</div>
		</div>
		
	</div>

</div>
	
	</div>

</div>
</div>
	<c:choose>
	 <c:when test="${msg == false}">
 		<script>alert("회원 정보 수정에 실패했습니다");</script>
 	</c:when>
 
 	<c:when test="${msg == true}">
 		<script>alert("회원 정보 수정을 성공적으로 마쳤습니다 :)");</script>
 	</c:when>
	</c:choose>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>