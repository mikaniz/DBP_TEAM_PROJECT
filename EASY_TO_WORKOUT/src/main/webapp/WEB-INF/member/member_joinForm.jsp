<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW MemberJoinPage</title>
<script>
function join() {
	if (form.id.value == "") {
		alert("ID를 입력하세요.");
		form.id.focus();
		return false;
	}
	if (form.pw.value == "") {
		alert("비밀번호를 입력하세요.");
		form.pw.focus();
		return false;
	}
	if (form.pw2.value == "") {
		alert("비밀번호 확인을 입력하세요.");
		form.pw2.focus();
		return false;
	}
	if (form.pw.value != form.pw2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.pw2.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하세요.");
		form.name.focus();
		return false;
	}
	if (form.phone.value == "") {
		alert("전화번호를 입력하세요.");
		form.phone.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}\d{3,4}\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	if (form.email.value == "") {
		alert("이메일을 입력하세요.");
		form.email.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value) == false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	form.submit();
}
</script>
</head>

<body>
<form name="form" method="POST" action="<c:url value='/member/join' />">
	<!-- 운동하기 편하군&로고 -->
	<div style="text-align: center; margin: 20px 0 20px 0">
		<a href=""><img src="<c:url value='/images/logo.PNG' />"
			width=500px height=130px /></a>
	</div>
	<!-- 회원 정보 입력 -->
	<div style="text-align: left; margin-left: 400px;">
		<input type="text" name="id" placeholder="아이디" style="width: 450px; height: 30px; margin: 10px">
	</div>
	<div style="text-align: left; margin-left: 400px;">
		<input type="password" name="pw" placeholder="비밀번호" style="width: 450px; height: 30px; margin: 10px">
	</div>
	<div style="text-align: left; margin-left: 400px;">
		<input type="password" name="pw2" placeholder="비밀번호 확인" style="width: 450px; height: 30px; margin: 10px">
	</div>
	<div style="text-align: left; margin-left: 400px;">
		<input type="text" name="name" placeholder="이름" style="width: 210px; height: 30px; margin: 10px">
		<input type="text" name="phone" placeholder="전화번호" style="width: 210px; height: 30px; margin: 10px">
	</div>
	<div style="text-align: left; margin-left: 400px;">
		<input type="text" name="email" placeholder="이메일" style="width: 450px; height: 30px; margin: 10px">
	</div>
	<div style="text-align: center">
		<input type="button" onclick="join()" value="회원 가입" style="width: 190px; height: 50px; margin: 10px">
	</div>
	<!-- 예외 메시지 출력 -->
	<div style="text-align: center">
	<c:if test="${joinFailed}">
	  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	</c:if>
	</div>
</form>
</body>
</html>