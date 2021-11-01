<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW club_openForm</title>
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
function acceptRequestBtn_click() {
	theForm = document.openForm;
	
	if(theForm.clubName.value == "") alert("모임 이름을 입력해주세요.");
	else {
		alert("모임이 개설되었습니다.");
		location.href= "./club_detail.jsp"
	}
	
}
</script>

</head>

<body>
	<!-- 운동하기 편하군&로고 -->
	<div style="text-align: center">
		<a href="../mainPage.jsp"><img src="../images/logo.PNG"
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
			<h3 style="margin: 20px;">회원정보</h3>
			<table id="memberDataTable">
				<tr>
					<td><img src="../images/somsom.jpg" width=150px height=230px />
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

		<div style="float: right">
			<!-- 모임 개설 항목 입력 부분  -->
			<div id="clubInfoInput">
				<h3 style="margin: 20px;">모임 정보 입력</h3>
				<hr>
				<form name="openForm">
				<table id="clubTable">
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 이름 :</td>
						<td><input type="text" name="clubName"
							style="width: 300px; height: 20px; font-size: 15px;"></td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 유형 :</td>
						<td>
							<input type="radio" name="clubOpenCycle" value="정기적" checked />정기적
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="radio" name="clubOpenCycle" value="일시적" />일시적
						</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">초대 유형 :</td>
						<td>
							<input type="radio" name="clubSignUp" value="자유 가입" checked />자유 가입 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="radio" name="clubSignUp" value="초대 가입" />초대 가입
						</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 소개 :</td>
						<td><textarea name="clubIntro" rows=15 cols=60 style="resize: none;"></textarea></td>
					</tr>
				</table>
				<div style="text-align: center; margin-left: 130px;">
					<input id="clubAccpetButton" type="button" value="모임 승인 요청"
						onclick="acceptRequestBtn_click()"> 
					<input id="backButton" type="button" value="돌아가기" 
						onclick="location.href='./clubPage.jsp'">
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>