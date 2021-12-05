<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>EASY TO WORK OUT</title>
<style>
@import url(//font.elice.io/EliceDigitalBaeum.css);
body {
    font-family: "Elice Digital Baeum",sans-serif;
}
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

#clubInfoInput {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#clubTable {
	border-collapse: collapse;
	width: 700px;
	height: 450px;
	float: center;
}

#clubTableTr {
	height: 45px;
	width: 700px;
	table-layout: fixed;
}

#clubAccpetButton {
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
function scheduleCreate() {
	if(scheduleForm.contactAddress.value == "") {
		alert("접속 주소를 입력해주세요.");
		theForm.contactAddress.focus();
		return false;
	}
	else if (scheduleForm.creationDate.value == "") {
		alert("접속 날짜를 선택해주세요.");
		theForm.creationDate.focus();
		return false;
	}
	else if (scheduleForm.checkedRoutine.value.length == 0) {
		alert("루틴을 선택해주세요.");
		theForm.creationDate.focus();
		return false;
	}
	scheduleForm.submit();
}

function chooseRoutine(targetUri) {
	scheduleForm.action = targetUri;
	scheduleForm.submit();
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
			<li><a href='<c:url value='/club/list' />'>모임</a></li>
			<li><a href='<c:url value='/routine/list' />'>루틴</a></li>
			<li><a href='#'>다이어리</a>
				<ul class="subMenu">
					<li><a href='<c:url value='/diary/list' />'>MY 다이어리</a></li>
					<li><a href='<c:url value='/diary/list' />'>전체 다이어리</a></li>
				</ul></li>
		</ul>
	</nav>

	<hr>
	<div class="container">
		<!-- 회원정보 -->
		<jsp:include page="/WEB-INF/member/memberInfo.jsp"/>

		<div style="float: right">
			<!-- 모임 개설 항목 입력 부분  -->
			<div id="clubInfoInput">
				<h3 style="margin: 20px;">
					모임 정보 입력 &nbsp;&nbsp;
					<c:if test="${openClubFailed}"><font color="red">${exception.getMessage()}</font></c:if>
				</h3>
				<hr>
				<form name="scheduleForm" method="POST" action="<c:url value='/club/schedule/create' />">
					<input type="hidden" name="clubId" value="${clubId}">
					<table id="clubTable">
						<tr id="clubTableTr">
							<td style="width: 130px;">스케줄 날짜 :</td>
							<td>
								<input type="datetime-local" name="creationDate" value="<c:if test="${creationDate ne null}">${creationDate}</c:if>" />
							</td>
						</tr>
						<tr id="clubTableTr">
							<td style="width: 130px;">접속 주소 :</td>
							<td>
								<input type="text" name="contactAddress" value="<c:if test="${contactAddress ne null}">${contactAddress}</c:if>"
									autocomplete="off" style="width: 300px; height: 20px; font-size: 15px;">
							</td>
						</tr>
						<tr id="clubTableTr">
							<td style="width: 130px;">루틴 선택 : </td>
							<td>
								<c:if test="${routineNotSelected eq 'routineNotSelected' }">
									<script>
										alert('루틴이 선택되지 않았습니다.');
										document.location.href='<c:url value='/club/schedule/usage' />';
									</script>
								</c:if>
								<input type="button" value="선택하기" 
									onclick="chooseRoutine('<c:url value='/club/schedule/usage' />')">
							</td>
						</tr>
						<tr id="clubTableTr">
							<td style="width: 130px;">선택 루틴 목록 : </td>
							<td>
							<c:forEach var="routine" items="${routineList}">
								<input type="hidden" name="routineIdList" value="${routine.routineId}">
							</c:forEach>
								<textarea name="checkedRoutine" rows=4 cols=60 style="overflow: scroll; resize: none;"  readonly="readonly" disabled><c:forEach var="routine" items="${routineList}">${routine.rName} (${routine.part})<%= "\n" %></c:forEach></textarea>
							</td>
						</tr>
						<tr id="clubTableTr">
							<td style="width: 130px;">공지 사항 :</td>
							<td>
								<textarea name="notice" autocomplete="off" rows=10 cols=60 style="resize: none;"><c:if test="${notice ne null}">${notice}</c:if></textarea>
							</td>
						</tr>
					</table>
					<div style="text-align: center; margin-left: 130px;">
						<input id="clubAccpetButton" type="button" value="스케줄 등록"
							onclick="scheduleCreate()"> 
						<input id="backButton" type="button" value="돌아가기" 
							onclick="location.href='<c:url value='/club/list' />'">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>