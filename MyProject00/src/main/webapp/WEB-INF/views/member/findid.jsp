<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>ID 찾기</title>
</head>

<body>
	<div class="container">
	<jsp:include page="../top_menu.jsp"></jsp:include>
		<div class="page-header">
			<h1>
				ID 찾기 <small>Find your ID</small>
			</h1>
		</div>

		<form role="form" action="mv_findpw.do" method="post">

			<table class="table table-hover">
				<tbody>

					<tr>
						<th scope="cols">이메일</th>
						<td width="75%"><input type="text" class="form-control" placeholder="가입시 사용한 이메일을 입력해주세요"
							id="member_email" name="member_email">
							<br><p class="result" style="color:red"><span class="msg" ></span></p>
						</td>
						
					</tr>
				</tbody>
			</table>
			
			<div id="loginbtn" style="display:none">
			<a href="mv_login.do" type="button" class="btn btn-info">로그인 페이지로 이동</a>
			</div><br>
			<button type="button" class="findid btn btn-warning btn-space" disabled="disabled">아이디 찾기</button>
			<input type="button" value="비밀번호 찾기" onclick="location.href='mv_findpw.do'" class="btn btn-danger">
			
		<br>
			
		</form>
	</div>
	<script> 
$(".findid").click(function(){
 
 var query = {member_email : $("#member_email").val()};
 
 $.ajax({
  url : "/myproject/mv_findidOK.do",
  type : "post",
  data : query,
  success : function(data) {
  
   if(data == "null") {
	   alert("존재하지 않는 이메일입니다. 다시 한 번 확인해주세요.");
	   document.getElementById("loginbtn").style.display = "none";
   } else {
	   alert("아이디는 [" + data + "] 입니다. 로그인해주세요.");
	   document.getElementById("loginbtn").style.display = "block";
	   
	 
   }
  }
 });  // ajax 끝
 
});

$("#member_email").keyup(function(){
		$(".findid").removeAttr("disabled");
});
</script>
</body>

</html>