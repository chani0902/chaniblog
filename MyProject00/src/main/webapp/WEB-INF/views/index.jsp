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

<title>My Project</title>
</head>
<body>

<div class="container">
<jsp:include page="top_menu.jsp"></jsp:include>
<!-- 사이트 이름 출력 부분 시작 -->
<c:if test="${member == null}">
	<div class="page-header">
		<h1>
			My Project <small>Community site</small>
		</h1>
	</div>
</c:if>
<c:if test="${member != null}">
	<div class="page-header">
		<h1>
			My Project <small>Community site :D   Have fun! [${member.member_nickname}]</small>
		</h1>
	</div>
</c:if>
<!-- 사이트 이름 출력 부분 끝 -->

<!-- alert 출력 부분 시작 -->
<c:choose>
 <c:when test="${msg == '로그인 성공'}">
 	<script>alert("${member.member_nickname}님 환영합니다 :D");</script>
 </c:when>
 
 <c:when test="${msg == '로그아웃 성공'}">
 	<script>alert("성공적으로 로그아웃이 되었습니다!");</script>
 </c:when>
 
 <c:when test="${msg == '회원 가입 성공'}">
 	<script>alert("메일 인증을 완료해주세요");</script>
 </c:when>
 
 <c:when test="${msg == '회원 탈퇴 성공'}">
 	<script>alert("그동안 이용해주셔서 감사합니다. (_ _)");</script>
 </c:when>
</c:choose>
<!-- alert 출력 부분 끝 -->
<table class="table">
	<tr>
		<td width="50%" rowspan="2">
		<img class="img-responsive" alt="Responsive image" src="resources/img/image.jpg">
		</td>
		<td width="50%">
		<div class="panel panel-info">
		<div class="panel-heading"><span class="glyphicon glyphicon-fire" aria-hidden="true"></span><b>  인기 게시물</b></div>
		<div class="panel-body">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="cols" width="12%">글 번호</th>
					<th scope="cols" width="39%">제목</th>
					<th scope="cols" width="20%">작성자</th>
					<th scope="cols" width="12%">조회수</th>
					<th scope="cols" width="20%">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list}">

					<tr>
						<td>${vo.board01_num}</td>
						<td><a
							href="b01_selectOne.do?board01_num=${vo.board01_num}&page=${scri.page}&perPageNum=${scri.perPageNum}&searchKey=${scri.searchKey}&searchWord=${scri.searchWord}">${vo.board01_title}</a>
							<c:if test="${vo.reply_cnt != 0}">
								<small><span class="label label-info"><c:out value="${vo.reply_cnt}"/></span></small>
							</c:if>	
						</td>
						<td>${vo.board01_writer}</td>
						<td>${vo.board01_viewcnt}</td>
						<td><fmt:formatDate value="${vo.board01_regdate}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
		</td>
	</tr>
	<tr>
		<td width="50%">
		<div class="panel panel-info">
		<div class="panel-heading"><span class="glyphicon glyphicon-user" aria-hidden="true"></span><b>  우수 활동 유저</b></div>
		<div class="panel-body">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="cols" width="40%">프로필 이미지</th>
					<th scope="cols" width="30%">닉네임</th>
					<th scope="cols" width="30%">포인트</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list2}">

					<tr>
						<td><img width="75px"
							src="resources/uploadimg/${vo.member_img}" class="img-thumbnail"></td>
						<td>${vo.member_nickname}</td>
						<td>${vo.member_point}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
		</td>
	</tr>
</table>

</div>

<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>