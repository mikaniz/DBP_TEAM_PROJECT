<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW routine_createForm</title>
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
	height: 400px;
	float: center;
	margin-left : 10px;
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
		theForm.routineName.focus();
		return false;
	}
	
	else if(checkCount() == 0) {
		alert("운동 부위를 선택해주세요.");
		theForm.routinePart.focus();
		return false;
	}
	
	else if(createForm.routineTime.value == "") {
		alert("소요시간을 입력해주세요.");
		theForm.routineTime.focus();
		return false;
	}
	
	else if(createForm.checkedExercise.value.length == 0) {
		alert("운동을 선택해주세요.");
		theForm.checkedExercise.focus();
		return false;
	}
	
	createForm.submit();
}

function checkCount(){
	var chk = document.getElementsByName("routinePart");
	var num = 0;

	for(i = 0; i < chk.length; i++) {
		if (chk[i].checked)
			num++;
	}

	return num;
}

function chooseExercise(targetUri) {
	createForm.action = targetUri;
	createForm.submit();
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
				<input type="hidden" name="thisIsForChoice" value='thisIsForChoice'>
				<table id="routineTable">
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">루틴명 :</td>
						<td><input type="text" name="routineName"
							style="width: 300px; height: 20px; font-size: 15px;" autocomplete="off"
							value="${routineName}"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">등록자 :</td>
						<td>${member.getId()}</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">운동 부위 :</td>
						<td>
							<input type="checkbox" name="routinePart" value="상체" 
								<c:if test="${routinePart.contains('상체')}">checked</c:if> />상체
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="하체"
								<c:if test="${routinePart.contains('하체')}">checked</c:if> />하체
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="복부"
								<c:if test="${routinePart.contains('복부')}">checked</c:if> />복부
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="어깨"
								<c:if test="${routinePart.contains('어깨')}">checked</c:if> />어깨
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" value="전신"
								<c:if test="${routinePart.contains('전신')}">checked</c:if> />전신
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">소요시간(분) :</td>
						<td><input type="text" name="routineTime" value="${routineTime}"
							style="width: 300px; height: 20px; font-size: 15px;" autocomplete="off"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">운동 강도 :</td>
						<td>
							<input type="radio" name="routineLevel" value="1" 
								<c:if test='${routineLevel eq null}'>checked</c:if>
								<c:if test='${routineLevel eq 1}'>checked</c:if>
							 />1
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="2"
								<c:if test='${routineLevel eq 2}'>checked</c:if>
							 />2
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="3"
								<c:if test='${routineLevel eq 3}'>checked</c:if>
							 />3
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="4"
								<c:if test='${routineLevel eq 4}'>checked</c:if>
							 />4
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" value="5"
								<c:if test='${routineLevel eq 5}'>checked</c:if>
							 />5
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">루틴 유형 :</td>
						<td>
							<input type="radio" name="routineType" value="0" 
								<c:if test='${routineType eq null}'>checked</c:if>
								<c:if test='${routineType eq 0}'>checked</c:if>
							/>공개
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="radio" name="routineType" value="1"
								<c:if test='${routineType eq 1}'>checked</c:if>
							 />개인
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">운동 선택 :</td>
						<td>
							<input id="exerciseChoiceButton" type="button" value="운동 선택하러 가기" 
									onclick="chooseExercise('<c:url value='/exercise/list' />')">
						</td>
					</tr>
					<tr id="routineTableTr">
							<td style="width: 130px; font-size: 15px;"> 선택된 운동 목록 :</td>
							<td>
								<c:forEach var="exercise" items="${exerciseList}">
									<input type="hidden" name="exerciseIdList" value="${exercise.exerciseId}">
								</c:forEach>
								<c:forEach var="sequence" items="${sequenceList}">
									<input type="hidden" name="sequence" value="${sequence}">
								</c:forEach>
								<c:forEach var="repetition" items="${repetitionList}">
									<input type="hidden" name="repetition" value="${repetition}">
								</c:forEach>
								<textarea name="checkedExercise" rows=6 cols=60 style="overflow: scroll; resize: none; margin-top: 10px;" readonly="readonly" disabled><c:forEach var="exercise" items="${exerciseList}">${exercise.name} (${exercise.part})<%= "\n\n" %></c:forEach></textarea>
							</td>
						</tr>
				</table>
				<div style="text-align: center; margin-left: 150px;">
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