<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW schedule_list</title>
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

#myClubScheduleTable {
	border-collapse: collapse;
	width: 400px;
	height: 100px;
	table-layout: fixed;
}

#list {
	width: 700px;
	height: 540px;
	border: 1px solid;
	margin-top: 10px;
	overflow: scroll;
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
		<a href="../mainPage.jsp"><img src="../images/logo.PNG"
			width=500px height=130px /></a>
	</div>

	<hr>
	<!-- 메뉴바 -->
	<nav class="menu">
		<ul class="mainMenu">
			<li><a href='../club/clubPage.jsp'>모임</a></li>
			<li><a href='../routine/routinePage.jsp'>루틴</a></li>
			<li><a href='#'>다이어리</a>
				<ul class="subMenu">
					<li><a href='#'>MY 다이어리</a></li>
					<li><a href='#'>전체 다이어리</a></li>
				</ul></li>
		</ul>
	</nav>

	<hr>
	<div class="container">
		<!-- 회원정보 -->
		<jsp:include page="/WEB-INF/member/memberInfo.jsp"/>
		
		<div style="float: right">
			<!-- 모임 스케줄 정보 출력 부분  -->
			<div id="scheduleInfo">
				<h3 style="margin: 20px;">모임 일정</h3>
				<hr>
				<table id="scheduleTable">
					<tr id="scheduleTableTr">
						<td style="width: 130px;">모임 일시 :</td>
						<td style="text-align:left;">
							<p>날짜 출력
						</td>
					</tr>
					<tr id="scheduleTableTr">
						<td style="width: 130px;">연락망 :</td>
						<td style="text-align:left;">
							<p>사용 연락망 출력
						</td>
					</tr>
					<tr id="scheduleTableTr">
						<td style="width: 130px;">공지사항 :</td>
						<td style="text-align:left;">
							<textarea rows=10 cols=60
								style="resize: none; font-size: 14px;" readonly="readonly" disabled>
								주의사항, 진행 방법 등 공지사항 출력
							</textarea></td>
					</tr>
					<tr id="scheduleTableTr">
						<td style="width: 130px;">진행할 루틴 :</td>
						<td style="text-align:left;">
							<div id="scheduleRoutine">
								<table id="scheduleRoutineTable">
									<tr id="scheduleRoutineTr">
										<td>루틴명1</td>
										<td>운동부위1</td>
										<td><input type='BUTTON' value="더보기" onclick=""></td>
									</tr>
									<tr id="scheduleRoutineTr">
										<td>루틴명2</td>
										<td>운동부위2</td>
										<td><input type='BUTTON' value="더보기" onclick=""></td>
									</tr>
									<tr id="scheduleRoutineTr">
										<td>루틴명3</td>
										<td>운동부위3</td>
										<td><input type='BUTTON' value="더보기" onclick=""></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<div style="text-align: center;">
					<input id="backButton" type="button" value="돌아가기"
						onclick="location.href='./club_details.jsp'">
				</div>
			</div>
		</div>
	</div>
</body>
</html>