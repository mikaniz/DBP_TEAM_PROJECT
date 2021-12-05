<%@ page contentType="text/html; charset=utf-8" %>
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

#exerciseInfoOutput {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#exerciseTable {
	border-collapse: collapse;
	width: 700px;
	height: 350px;
	float: center;
}

#exerciseTableTr {
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

#createButton {
	width: 400px; 
	height: 50px;	
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
		<a href="<c:url value='/main' />"><img src="<c:url value='/images/logo.PNG' />" width=500px
			height=130px /></a> 
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
			<!-- 운동 상세 정보 출력 부분  -->
			<div id="exerciseInfoOutput">
				<h3 style="margin: 20px;">${exercise.name}</h3>
				<hr>
				<table id="exerciseTable">
					<tr id="exerciseTableTr">
						<td style="width: 130px; font-size: 18px;">운동 부위 :</td>
						<td style="text-align:left; font-size: 16px;">
							<p>${exercise.part}
						</td>
					</tr>
					<tr id="exerciseTableTr">
						<td style="width: 130px; font-size: 18px;">운동 방법 :</td>
						<td style="text-align:left;">
							<textarea rows=8 cols=60 style="overflow: scroll;resize: none; font-size: 16px;" readonly="readonly" disabled>${exercise.method}</textarea>
							</td>
					</tr>
				</table>
				<div style="text-align: center;"> 
					<input id="backButton" type="button" value="돌아가기" 
						onclick="history.back()">
				</div>
			</div>
		</div>
	</div>
</body>
</html>