<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

#routineInfoInput {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#routineTable {
	border-collapse: collapse;
	width: 700px;
	height: 450px;
	float: center;
}

#routineTableTr {
	height: 45px;
	width: 700px;
	table-layout: fixed;
}

#routineCreateButton {
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
function createRequestBtn_click() {
	theForm = document.openForm;
	
	if(theForm.routineName.value == "") alert("루틴명을 입력해주세요.");
	else {
		alert("루틴이 등록되었습니다.\n 루틴 목록에서 확인이 가능합니다.");
	}
	
}
</script>

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
			<li><a href='<c:url value='/club' />'>모임</a></li>
			<li><a href='<c:url value='/routine' />'>루틴</a></li>
			<li><a href='#'>다이어리</a>
				<ul class="subMenu">
					<li><a href='<c:url value='/diary/my' />'>MY 다이어리</a></li>
					<li><a href='<c:url value='/diary/all' />'>전체 다이어리</a></li>
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
					<td><img src="<c:url value='/images/somsom.jpg' />" width=150px height=230px />
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
			<!-- 루틴 등록 항목 입력 부분  -->
			<div id="routineInfoInput">
				<h3 style="margin: 20px;">루틴 정보 입력</h3>
				<hr>
				<form name="openForm">
				<table id="routineTable">
					<tr id="routineTableTr">
						<td style="width: 130px;">루틴명 :</td>
						<td><input type="text" name="routineName"
							style="width: 300px; height: 20px; font-size: 15px;"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">등록자 :</td>
						<td><input type="text" name="routineCreator"
							style="width: 300px; height: 20px; font-size: 15px;"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">운동 부위 :</td>
						<td>
							<input type="checkbox" name="routinePart" value="상체" />상체
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="하체" />하체
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="복부" />복부
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="어깨" />어깨
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="전신" />전신
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">소요시간 :</td>
						<td><input type="text" name="routineTime"
							style="width: 300px; height: 20px; font-size: 15px;"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">운동 강도 :</td>
						<td>
							<input type="radio" name="routineLevel" value="1" checked />1
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="2" />2
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="3" />3
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="4" />4
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="5" />5
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">루틴 유형 :</td>
						<td>
							<input type="radio" name="routineType" value="0" checked />전체
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="radio" name="routineType" value="1" />개인
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">운동 방법 :</td>
						<td><textarea name="routineMethod" rows=10 cols=60 style="resize: none;"></textarea></td>
					</tr>
				</table>
				<div style="text-align: center; margin-left: 130px;">
					<input id="routineCreateButton" type="button" value="루틴 등록"
						onclick="createRequestBtn_click()"> 
					<input id="backButton" type="button" value="돌아가기" 
						onclick="location.href='<c:url value='/routine' />'">
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>