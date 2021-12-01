<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW MemberLoginPage</title>
<script>
function login() {
	if (form.loginId.value == "") {
		alert("ID를 입력하세요.");
		form.loginId.focus();
		return false;
	}
	if (form.loginPw.value == "") {
		alert("비밀번호를 입력하세요.");
		form.loginPw.focus();
		return false;
	}
	form.submit();
}
</script>
</head>

<body>
<form name="form" method="POST" action="<c:url value='/member/login' />">
	<!-- 운동하기 편하군&로고 -->
	<div style="text-align: center; margin: 80px 0 20px 0">
		<a href=""><img src="<c:url value='/images/logo.PNG' />"
			width=500px height=130px /></a>
	</div>
	<div style="text-align: center">
		<input type="text" name="loginId" placeholder="아이디" style="width: 400px; height: 50px; margin: 10px">
	</div>
	<div style="text-align: center">
		<input type="password" name="loginPw" placeholder="비밀번호" style="width: 400px; height: 50px; margin: 10px">
	</div>
	<div style="text-align: center">
		<input type="button" onclick="login()" value="로그인" style="width: 190px; height: 50px; margin: 10px">
		<a href="<c:url value='/member/join/form' />">
			<input type="button" value="회원 가입" style="width: 190px; height: 50px; margin: 10px">
		</a>
	</div>
	<div style="text-align: center">
		<c:if test="${loginFailed}">
		  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
		</c:if>
	</div>
</form>
</body>
</html>