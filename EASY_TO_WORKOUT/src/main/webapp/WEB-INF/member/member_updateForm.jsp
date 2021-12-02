<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW MEMBER UPDATE</title>
<style>
.menu {
	height: 50px;
	background-color: #213963;
	text-align: center;
}

.menu ul {
	display: inline-block;
	list-style: none;
	margin: 0;
}

.menu a {
	text-decoration: none;
	color: white;
}

.menu li:hover>a {
	color: #919191;
}

.menu li:hover>.subMenu li {
	display: block;
	background-color: #90ABDA;
}

.mainMenu li {
	float: left;
	width: 400px;
	height: 50px;
	line-height: 50px;
	text-align: center;
}

.subMenu li {
	float: none;
	display: none;
	position: relative;
	width: 300px;
	margin-left: 10px;
	z-index: 1;
}

.container {
	display: flex;
	justify-content: center;
}

#memberDataTable {
	border-collapse: collapse;
	width: 400px;
	height: 230px;
	table-layout: fixed;
}

#memberInput {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#memberTable {
	border-collapse: collapse;
	width: 700px;
	height: 450px;
	float: center;
}

#memberTableTr {
	height: 45px;
	width: 700px;
	table-layout: fixed;
}

#memberUpdateButton {
	width: 150px;
	height: 45px;
	text-align: center;
	margin: 10px;
	background-color: #90ABDA;
}

#backButton {
	width: 150px;
	height: 45px;
	text-align: center;
	margin: 10px;
	background-color: #90ABDA;
}

th, td {
	text-align: center;
}
</style>

<script>
function memberUpdateBtn_click() {
	if (updateForm.pw.value == "") {
		alert("비밀번호를 입력하세요.");
		updateForm.pw.focus();
		return false;
	}
	if (updateForm.updatePw.value == "") {
		alert("비밀번호를 입력하세요.");
		updateForm.updatePw.focus();
		return false;
	}
	if (updateForm.updatePw2.value == "") {
		alert("비밀번호 확인을 입력하세요.");
		updateForm.updatePw2.focus();
		return false;
	}
	if (updateForm.updatePw.value != updateForm.updatePw2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		updateForm.pw2.focus();
		return false;
	}
	if (updateForm.phone.value == "") {
		alert("전화번호를 입력하세요.");
		updateForm.phone.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}\d{3,4}\d{4}$/;
	if(phoneExp.test(updateForm.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		updateForm.phone.focus();
		return false;
	}
	if (updateForm.email.value == "") {
		alert("이메일을 입력하세요.");
		updateForm.email.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(updateForm.email.value) == false) {
		alert("이메일 형식이 올바르지 않습니다.");
		updateForm.email.focus();
		return false;
	}
	updateForm.submit();
}
</script>

</head>

<body>
	<!-- 운동하기 편하군&로고 -->
	<div style="text-align: center">
		<a href="<c:url value='/main' />"><img src="<c:url value='/images/logo.PNG' />"
			width=500px height=130px /></a>
	</div>
	<!-- 돌아가기 버튼 -->

	<hr>
	<!-- 메뉴바 -->
	<nav class="menu">
		<ul class="mainMenu">
			<li><a href="<c:url value='/club/list' />">모임</a></li>
			<li><a href="<c:url value='/routine/list' />">루틴</a></li>
			<li><a href='#'>다이어리</a>
				<ul class="subMenu">
					<li><a href="<c:url value='/diary/my/list' />">MY 다이어리</a></li>
					<li><a href="<c:url value='/diary/all/list' />">전체 다이어리</a></li>
				</ul></li>
		</ul>
	</nav>

	<hr>
	<div class="container">
		<!-- 회원정보 -->
		<jsp:include page="/WEB-INF/member/memberInfo.jsp"/>

		<div style="float: right">
			<!-- 다이어리 작성 부분  -->
			<div id="memberInput">
				<h3 style="margin: 20px;">회원정보 수정&emsp;
					<c:if test="${updateFailed}">
					  <font color="red"><c:out value="${exception.getMessage()}" /></font><br>
					</c:if>
				</h3>
				<hr>
				<form name="updateForm" method="POST" action="<c:url value='/member/update' />">
					<input type="hidden" name="id" value="${loginMember.id}">
					<table id="memberTable">
						<tr id="memberTableTr">
							<td style="width: 130px;">아이디 :</td>
							<td style="text-align:left; font-size: 13px;">
								<p>${loginMember.id}
							</td>
						</tr>
						<tr id="memberTableTr">
							<td style="width: 130px;">이름 :</td>
							<td style="text-align:left; font-size: 13px;">
								<p>${loginMember.name}
							</td>
						</tr>
						<tr id="memberTableTr">
							<td style="width: 130px;">등급 :</td>
							<td style="text-align:left; font-size: 13px;">
								<p><c:choose>
									<c:when test="${loginMember.grade eq 'green'}">새싹</c:when>
									<c:otherwise>마스터</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr id="memberTableTr">
							<td style="width: 130px;">현재 비밀번호 :</td>
							<td><input type="password" name="pw"
								style="width: 500px; height: 20px; font-size: 15px;"></td>
						</tr>
						<tr id="memberTableTr">
							<td style="width: 130px;">수정 비밀번호 :</td>
							<td><input type="password" name="updatePw"
								style="width: 500px; height: 20px; font-size: 15px;"></td>
						</tr>
						<tr id="memberTableTr">
							<td style="width: 130px;">수정 비밀번호 확인 :</td>
							<td><input type="password" name="updatePw2"
								style="width: 500px; height: 20px; font-size: 15px;"></td>
						</tr>
						<tr id="memberTableTr">
							<td style="width: 130px;">전화번호 :</td>
							<td><input type="text" name="phone" value="${loginMember.phone}"
								style="width: 500px; height: 20px; font-size: 15px;"></td>
						</tr>
						<tr id="memberTableTr">
							<td style="width: 130px;">이메일 :</td>
							<td><input type="text" name="email" value="${loginMember.email}"
								style="width: 500px; height: 20px; font-size: 15px;"></td>
						</tr>
					</table>
					<div style="text-align: center; margin-left: 130px;">
						<input id="memberUpdateButton" type="button" value="회원정보 수정"
							onclick="memberUpdateBtn_click()"> 
						<input id="backButton" type="button" value="돌아가기" 
							onclick="history.back()">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>