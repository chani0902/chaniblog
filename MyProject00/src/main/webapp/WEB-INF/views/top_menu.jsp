<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/js/jquery-3.6.0.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<p></p>
<ul class="nav nav-pills">
	<li role="presentation" class="active nav-brand"><a
		href="index.do"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&emsp;DamSo</a></li>
	<li role="presentation"><a href="b00_listsearch.do">공지사항</a></li>
	<li role="presentation"><a href="b01_listsearch.do">자유 게시판</a></li>
	<li role="presentation"><a href="#about">About</a></li>
		<ul class="nav navbar-nav navbar-right">
	
	<c:choose>

		<c:when test="${member == null}">
			<li>
				<div class="btn-group">
					<button type="button" class="btn btn-info dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						접속하기 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="mv_login.do"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>&nbsp;로그인</a>
						
						<li><a href="mv_insert.do"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>&nbsp;회원 가입</a></li>
					</ul>
				</div>
			</li>
		</c:when>
		

		<c:when test="${member != null}">
			
			<li><div class="btn-group btn-group" role="group" aria-label="...">
  					<button type="button" class="btn btn-warning" disabled="disabled">
  					<span class="glyphicon glyphicon-flash" aria-hidden="true"></span>
  					${member_nickname}</button>
  					<button type="button" class="btn btn-success" disabled="disabled" style="margin-right:10px;"><small>접속중</small></button>
					</div></li>
			 
			<c:choose>
				<c:when test="${member_id == 'admin'}">
					<li style="margin-right:5px;">
						<div class="btn-group">
							<button type="button" class="btn btn-info dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false">
								회원 관리 메뉴 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="mv_selectAll.do">
								<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&nbsp;&nbsp;전체 회원 정보</a></li>
							</ul>
						</div>
					</li>
					<li>
						<div class="btn-group">
							<button type="button" class="btn btn-info dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false">
								<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;
								회원 메뉴 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="mv_selectOne.do?member_id=admin">
								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;
								관리자 계정 정보</a></li>
								<!-- <li><a href=#>
								<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>&nbsp;나의 활동 내역</a></li> -->
								<li><a href="mv_logout.do"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;로그아웃</a></li>
							</ul>
						</div>
					</li>

	
				</c:when>

				<c:otherwise>
					<li>
						<div class="btn-group">
							<button type="button" class="btn btn-info dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false">
								<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp;
								회원 메뉴 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="mv_selectOne.do?member_id=${member_id}">
								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;회원 정보</a></li>
								<!-- <li><a href=#>
								<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>&nbsp;나의 활동 내역</a></li> -->
								<li><a href="mv_logout.do"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;로그아웃</a></li>
							</ul>
						</div>
					</li>
					
				</c:otherwise>



			</c:choose>
			
					
					
		</c:when>

	</c:choose>
	
	
	</ul>
	
</ul>
<hr>