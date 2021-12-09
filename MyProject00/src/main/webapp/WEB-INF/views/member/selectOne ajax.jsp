<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

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
							<a href="mv_delete.do?member_id=${vo2.member_id}" class="btn btn-danger">
							<span class="glyphicon glyphicon-erase" aria-hidden="true"></span>&emsp;회원 강제 탈퇴</a>
						</c:when>
	
					</c:choose>
				</td>
			</tr>
		</table>
	 
  	</div>
		<div role="tabpanel">

<script>

$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
	e.target // 현재 설정된 tab
	e.relatedTarget // 이전에 설정된 탭
	});
	
	// 다른 메뉴가 선택되어 active가 remove 되기 전 이벤트
	$('a[data-toggle="tab"]').on('hide.bs.tab', function (e) {
	e.target // 현재 설정된 tab
	e.relatedTarget // 이전에 설정된 탭
	});


function selectOne() {
	
	let methodType = "get";
	let url = "/mv_selectOne.do2";
	let params = {member_id:'${vo2.member_id}'};
	let dType = "json";
	let successed_runFn = function(responseData,status) {
		console.log(status);
		console.log(responseData); // JSONArray object
		
		let rows = '';
		
		let obj = responseData;
		
		rows += '<tr>';
		rows += '<th style="vertical-align:middle;" scope="cols" class="info" width="25%">ID</th>';
		rows += '<td style="vertical-align:middle;">${vo2.member_id}</td>';				
		rows += '</tr>';
		rows += '<tr>';
		rows += '<th style="vertical-align:middle;" scope="cols" class="warning">닉네임</th>';
		rows += '<td style="vertical-align:middle;">${vo2.member_nickname}</td>		';
		rows += '</tr>';
		rows += '<tr>';
		rows += '<th style="vertical-align:middle;" scope="cols" class="info">이메일</th>';
		rows += '<td style="vertical-align:middle;">${vo2.member_email}</td>	';
		rows += '</tr>';
		rows += '<tr>';
		rows += '<th style="vertical-align:middle;" scope="cols" class="warning">포인트</th>';
		rows += '<td style="vertical-align:middle;">${vo2.member_point}</td>	';
		rows += '</tr>';
		rows += '<tr>';
		rows += '<th style="vertical-align:middle;" scope="cols" class="info">프로필 이미지</th>';
		rows += '<td style="vertical-align:middle;"><img width = 75px src="resources/uploadimg/${vo2.member_img}" class="img-thumbnail"></td>	';
		rows += '</tr>';	
		rows += '<tr>';
		rows += '<th style="vertical-align:middle;" scope="cols" class="warning">가입일</th>';
		rows += '<td style="vertical-align:middle;"><fmt:formatDate value="${vo2.member_regdate}"	pattern="yyyy-MM-dd HH:mm:ss" /></td>';
		rows += '</tr>';
	
		$("#selectOne_body").html(rows);
	}
	
	$.ajax({
		type : methodType,
		url : url,
		data : params,
		dataType : dType,
		success : successed_runFn
	});

};



</script>

  <!-- Nav tabs -->
  <ul class="nav nav-tabs nav-justified" role="tablist">
    <li role="presentation" class="active">
    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab" data-load="true">
    	<b><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&emsp;홈</b></a></li>
    <li role="presentation">
    	<a href="#myinfo" aria-controls="myinfo" role="tab" data-toggle="tab" data-load="false">
    	<b><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&emsp;회원 정보</b></a></li>
    <li role="presentation">
    	<a href="#mypost" aria-controls="mypost" role="tab" data-toggle="tab" data-load="false">
    	<b><span class="glyphicon glyphicon-list" aria-hidden="true"></span>&emsp;작성 글</b></a></li>
    <li role="presentation">
   	 	<a href="#mycomment" aria-controls="mycomment" role="tab" data-toggle="tab" data-load="false">
   	 	<b><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&emsp;작성 댓글</b></a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane fade in active" id="home">
		홈화면
	</div>
    <div role="tabpanel" class="tab-pane fade" id="myinfo">
		<table class="table">
		<tbody id="selectOne_body">
			
		</tbody>
	</table>
	</div>
    <div role="tabpanel" class="tab-pane fade" id="mypost">
		<table class="table table-hover">
			
			</tbody>
			</table>
	</div>
    <div role="tabpanel" class="tab-pane fade" id="mycomment">
		<div class="panel panel-info">
  			<div class="panel-heading"><h5>글 번호를 클릭하면 해당 글로 이동할 수 있습니다</h5></div>
 			 <div class="panel-body">
  			  <table class="table table-hover">
			<tbody>
				
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