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

#myClubScheduleTable {
	border-collapse: collapse;
	width: 400px;
	height: 100px;
	table-layout: fixed;
}

#list {
	width: 700px;
	height: 600px;
	border: 1px solid;
	overflow: scroll;
	position: relative;
}

#listTable {
	border-collapse: collapse;
	width: 700px;
	table-layout: fixed;
}

#listItem {
	border-bottom: 1px solid;
	height: 50px;
}

#listTr {
	width: 700px;
	height: 40px;
	table-layout: fixed;
}

#scheduleInfo {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#scheduleRoutineTable {
	border-collapse: collapse;
	width: 455px;
	table-layout:fixed;
}

#scheduleRoutine {
	width: 455px;
	height: 150px;
	overflow: scroll;
	border: 1px solid silver;
}

#scheduleRoutineTr {
	width: 455px;
	height: 40px;
	table-layout:fixed;
}

#scheduleTable {
	border-collapse: collapse;
	width: 700px;
	height: 450px;
	float: center;
}

#scheduleTableTr {
	height: 30px;
	width: 700px;
	table-layout: fixed;
}

#etcButton {
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
</head>

<body>
	<!-- 운동하기 편하군&로고 -->
	<div style="text-align: center">
		<a href="<c:url value='/main' />"><img src="<c:url value='/images/logo.PNG' />"
			width=500px height=130px /></a>
	</div>

	<hr>
	<!-- 메뉴바 -->
	<nav class="menu">
		<ul class="mainMenu">
			<li><a href='<c:url value='/club/list' />'>모임</a></li>
			<li><a href='<c:url value='/routine/list' />'>루틴</a></li>
			<li><a href='#'>다이어리</a>
				<ul class="subMenu">
					<li><a href='<c:url value='/diary/my/list' />'>MY 다이어리</a></li>
					<li><a href='<c:url value='/diary/all/list' />'>전체 다이어리</a></li>
				</ul></li>
		</ul>
	</nav>

	<hr>
	<div class="container">
		<!-- 회원정보 -->
		<jsp:include page="/WEB-INF/member/memberInfo.jsp"/>
		
		<div style="float: right">
			<!-- 전체 스케줄 목록 -->
			<div id="list">
				<table id="listTable">
					<tr id="listItem">
						<th>모임 이름</th>
						<th>날짜</th>
						<th colspan="2">접속 주소</th>
						<td></td>
					</tr>
					<c:forEach var="schedule" items="${scheduleList}">							
						<tr id="listTr">
							<td>${clubName}</td>
							<td>${schedule.creationDate}</td>
							<td colspan="2">${schedule.contactAddress}</td>
							<td>
								<a href="<c:url value='/club/schedule/view'>
										<c:param name='clubName' value='${clubName}' />
										<c:param name='clubId' value='${schedule.clubId}'/>
										<c:param name='scheduleId' value='${schedule.scheduleId}'/>
									</c:url>">
									<input id="etcButton" type="button" value="더보기">
								</a>
							</td>
						</tr>							
					</c:forEach>
				</table>
				<div style="text-align: center; margin-left: 250px; position: absolute; bottom:0;">
					<a href="<c:url value='/club/detail'>
							<c:param name='clubId' value='${clubId}'/>
						</c:url>">
						<input id="backButton" type="button" value="돌아가기">
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>