<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
/* 자바 스크립트 비번 확인 함수 선언 */
	function passConfirm() {
	var password = document.getElementById('member_pw');
	var password2 = document.getElementById('member_pw check');
	var confirmMsg = document.getElementById('confirmMsg');
	var correctColor = "green";
	var wrongColor = "red";
	
	/* 패스워드 입력란이 비어있을 때 설정 추가해야함 */
	if(password.value == ''){
		confirmMsg.style.color = wrongColor;
		confirmMsg.innerHTML = "비밀번호를 입력해주세요!";
		$("#submit").attr("disabled", "disabled");
	} else if(password.value == password2.value) {
		confirmMsg.style.color = correctColor;
		confirmMsg.innerHTML = "비밀번호가 일치합니다. 나머지 정보를 입력해주세요!";	
	} else {
		confirmMsg.style.color = wrongColor;
		confirmMsg.innerHTML = "비밀번호가 일치하지 않습니다";
		$("#submit").attr("disabled", "disabled");
	}
}

/* 공백 입력 불가 함수 */
function noSpaceForm(obj) { // 공백사용못하게
	var str_space = /\s/;  // 공백체크
	if(str_space.exec(obj.value)) { //공백 체크
	   alert("해당 항목에는 공백을 사용할수 없습니다.\n\n공백은 자동으로 제거 됩니다.");
	   obj.focus();
	   obj.value = obj.value.replace(' ',''); // 공백제거
	   return false;
	  }
	 // onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"
}
	
</script>

<style type="text/css">

	.btn-space {
	margin-right : 4px;
	margin-top : 4px;
	margin-bottom : 4px;
	}


</style>

<title>회원 가입 페이지</title>
</head>
<body>
<div class="container">
<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="page-header">
  <h1>회원 가입 <small>Create your Account</small></h1>
</div>
	
	
	<form role="form" action="mv_insertOK.do" method = "post" enctype="multipart/form-data">
		
		<table class="table table-hover">
		<tbody>

			<tr>
				<th scope="cols">ID</th>
				<td width="75%">
				<input type="text" class="form-control" placeholder="ID" id="member_id" name="member_id" value="dummy_id" onkeyup="noSpaceForm(this);">
				
				<button type="button" class="idcheck btn btn-default btn-space">아이디 중복 확인</button>
				<p class="result"><span class="msg"></span></p>
				</td>
				
				
			</tr>
			<tr>
				<th scope="cols">비밀번호</th>
				<td><input type="password" class="form-control" placeholder="Password" id="member_pw" name="member_pw" value="dummy_pw"></td>
			</tr>
			<tr>
				<th scope="cols">비밀번호 확인</th>
				<td><input type="password" class="form-control" placeholder="Password Confirm" id="member_pw check" name="member_pw check" value="dummy_pw" onkeyup="passConfirm()"></td>
			</tr>
			<tr>
				<th></th>
				<td>
				<span id="confirmMsg"></span>
				</td>
			</tr>
			<tr>
				<th scope="cols">닉네임</th>
				<td><input type="text" class="form-control" placeholder="Nickname" name="member_nickname" value="dummy_nick" onkeyup="noSpaceForm(this);"></td>
			</tr>
			<tr>
				<th scope="cols">이메일</th>
				<td><input type="email" class="form-control" placeholder="Email" id="member_email" name="member_email" value="dummy_email" >
				<button type="button" class="mailcheck btn btn-default btn-space">메일 중복 확인</button>
				<p class="result2"><span class="msg2"></span></p>
				</td>
			</tr>
			<tr>
				<th scope="cols">프로필 이미지</th>
				<td><input type="file" class="form-control-file" name="multipartFile" value="" ><small>[가로 제한 길이: 150px, 세로 제한 길이: 150px] 로 변경되어 저장됩니다. 참고하여 이미지를 등록해주세요.</small></td>
			</tr>
		</tbody>
	</table>
		<button type="submit" id="submit" class="btn btn-success" disabled="disabled">
		<span class="glyphicon glyphicon-check" aria-hidden="true"></span>
		&nbsp;입력 완료</button>
	</form>
	
	
</div>

<script> 
$(".idcheck").click(function(){
 
 var query = {member_id : $("#member_id").val()};
 
 $.ajax({
  url : "/myproject/mv_idcheck.do",
  type : "post",
  data : query,
  success : function(data) {
  
   if(data == 1) {
    alert("이미 사용 중인 ID는 사용할 수 없습니다.");
    $("#submit").attr("disabled", "disabled");
   } else {
	 alert("사용 가능한 ID 입니다! 나머지 정보를 입력해주세요.");
	 $(".result .msg").text("");
   }
  }
 });  // ajax 끝
});

 $("#member_id").keyup(function(){
	$(".result .msg").text("아이디 중복 확인을 꼭 해주세요!");
	$(".result .msg").attr("style", "color:#f00");
	$("#submit").attr("disabled", "disabled");
}); 
/* alert으로 하면 한 글자씩 입력할때마다 경고가 떠서 별로다.. */

</script>
<script>
$(".mailcheck").click(function(){
	 
	 var query = {member_email : $("#member_email").val()};
	 
	 $.ajax({
	  url : "/myproject/mv_mailcheck.do",
	  type : "post",
	  data : query,
	  success : function(data) {
	  
	   if(data == 1) {
	    alert("이미 사용 중인 메일입니다.");
	    $("#submit").attr("disabled", "disabled");
	   } else {
		 alert("사용 가능한 메일입니다! 나머지 정보를 입력해주세요.");
		 $(".result2 .msg2").text("");
		 $("#submit").removeAttr("disabled");
	   }
	  }
	 });  // ajax 끝
	});

	 $("#member_email").keyup(function(){
		$(".result2 .msg2").text("메일 중복 확인을 꼭 해주세요!");
		$(".result2 .msg2").attr("style", "color:#f00");
		$("#submit").attr("disabled", "disabled");
	}); 
</script>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>