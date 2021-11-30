<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW routine_createForm</title>
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

#list {
	width: 400px;
	height: 140px;
	border: 1px solid;
	margin-top: 10px;
	overflow: scroll;
}

#listTable {
	border-collapse: collapse;
	width: 400px;
	table-layout: fixed;
}

#listItem {
	border-bottom: 1px solid;
	height: 30px;
}

#listTr {
	width: 400px;
	height: 20px;
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

#routineCreateButton, #backButton {
	width: 150px;
	height: 45px;
	text-align: center;
	margin: 10px;
	background-color: #90ABDA;
}

#exerciseChoiceButton {
	width: 120px;
	height: 35px;
	text-align: center;
	font-size: 12px;
}

#searchButton {
	width: 50px; 
	height: 27px;
	background-color: #90ABDA;
}

th, td {
	text-align: center;
}
</style>

<script>
function routineCreate() {
	if(createForm.routineName.value == "") {
		alert("루틴명을 입력해주세요.");
		createForm.routineName.focus();
		return false;
	}
	if(createForm.routinePart.value == "") {
		alert("운동 부위를 선택해주세요.");
		createForm.routinePart.focus();
		return false;
	}
	if(createForm.routineTime.value == "") {
		alert("소요시간을 입력해주세요.");
		createForm.routineTime.focus();
		return false;
	}
	if(createForm.routineName.value == "") {
		alert("루틴명을 입력해주세요.");
		createForm.routineName.focus();
		return false;
	}
	if(createForm.routineName.value == "") {
		alert("루틴명을 입력해주세요.");
		createForm.routineName.focus();
		return false;
	}
	createForm.submit();
}

function search() {
	searchForm.submit();
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
			<!-- 루틴 등록 항목 입력 부분  -->
			<div id="routineInfoInput">
				<h3 style="margin: 20px;">
					루틴 정보 입력&nbsp;&nbsp;
					<c:if test="${creationFailed}"><font color="red">${exception.getMessage()}</font></c:if>
				</h3>
				<hr>
				<form name="createForm" method="POST" action="<c:url value='/routine/create' />">
				<table id="routineTable">
					<tr id="routineTableTr">
						<td style="width: 130px;">루틴명 :</td>
						<td><input type="text" name="routineName"
							style="width: 300px; height: 20px; font-size: 15px;"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">등록자 :</td>
						<td>${member.getId()}</td>
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
							<c:if test="${creationFailed}">value="${routine.rTime}"</c:if>
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
						<td style="width: 130px;">운동 선택 :</td>
						<td>
							<input id="exerciseChoiceButton" type="button" value="운동 선택하러 가기"
									onclick="location.href='<c:url value='/exercise/list' />'"> 
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-left: 130px;">
					<input id="routineCreateButton" type="button" value="루틴 등록"
						onclick="routineCreate()"> 
					<input id="backButton" type="button" value="돌아가기" 
						onclick="location.href='<c:url value='/routine/list' />'">
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>