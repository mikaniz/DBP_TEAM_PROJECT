<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW club_detials</title>
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

#clubInfoOutput {
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

#scheduleDetailButton {
	width: 100px; 
	height: 35px;
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
		<a href="../../mainPage.jsp"><img src="../images/logo.PNG"
			width=500px height=130px /></a>
	</div>
	<!-- 돌아가기 버튼 -->

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
		<!-- 회원정보 틀 -->
		<div
			style="width: 400px; height: 600px; border: 1px solid; float: left; margin-right: 10px;">
			<div style="height: 530px;">
				<h3 style="margin: 20px;">회원정보</h3>
				<table id="memberDataTable">
					<tr>
						<td><img src="../../images/somsom.jpg" width=150px height=230px />
						</td>
						<td>이름 : 김동덕
							<p /> 등급 : 새싹
							<p /> <br> <a href='#'>회원정보 수정</a>
							<p>
								<a href='#'>로그아웃</a>
						</td>
					</tr>
				</table>
				<br>
				<hr>
				<article>
					<h4 style="margin: 20px;">내 모임 목록</h4>
					<ul>
						<li><a href='#'>투현진</a></li>
						<li><a href='#'>ETW</a></li>
					</ul>
				</article>
			</div>
		</div>

		<div style="float: right">
			<!-- 모임 상세 정보 출력 부분  -->
			<div id="clubInfoOutput">
				<h3 style="margin: 20px;">모임 상세 정보</h3>
				<hr>
				<table id="clubTable">
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 이름 :</td>
						<td style="text-align:left;">
							<p>모임 이름 출력
						</td>
						<td >
						<input id="scheduleDetailButton" type="button" value="일정 보기"
							onclick="location.href='./schedule_list.jsp'">
					</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 유형 :</td>
						<td colspan=2 style="text-align:left;">
							<p>정기적 or 일시적
						</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">초대 유형 :</td>
						<td colspan=2 style="text-align:left;">
							<p>초대 or 자유
						</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 소개 :</td>
						<td colspan=2 style="text-align:left;">
							<textarea rows=13 cols=60
								style="resize: none; font-size: 14px;" readonly="readonly" disabled>
								개설자 소개 및 모임 진행 방법, 공지사항, 규칙 등
							</textarea></td>
					</tr>
				</table>
				<div style="text-align: center;">
					<input id="backButton" type="button" value="돌아가기"
						onclick="location.href='./clubPage.jsp'">
				</div>
			</div>
		</div>
	</div>
</body>
</html>