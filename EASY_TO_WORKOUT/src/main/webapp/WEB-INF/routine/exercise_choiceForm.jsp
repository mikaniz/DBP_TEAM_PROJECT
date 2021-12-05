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

#list {
	width: 697px;
	height: 400px;
	border: 1px solid;
	margin-top: 10px;
	overflow: scroll;
}

#listTable {
	border-collapse: collapse;
	width: 690px;
	table-layout: fixed;
}

#listItem {
	border-bottom: 1px solid;
	height: 50px;
}

#listTr {
	width: 690px;
	height: 40px;
	table-layout: fixed;
}

#exerciseChoice {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#etcButton{
	background-color: #90ABDA;
}

#searchButton {
	width: 100px; 
	height: 42px;
	background-color: #90ABDA;
}

#createButton {
	width: 400px; 
	height: 50px;	
	background-color: #90ABDA;
}

#exerciseChoiceButton, #backButton {
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
function exerciseChoice() {
	if(checkCount() == 0) {
		alert("운동을 선택해주세요.");
		createForm.routinePart.focus();
		return false;
	}
	
	choiceForm.submit();
}

function checkCount(){
	var chk = document.getElementsByName("checkExerciseId");
	var num = 0;

	for(i = 0; i < chk.length; i++) {
		if (chk[i].checked)
			num++;
	}

	return num;
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
		<!-- 운동 선택 부분 -->
		<div id="exerciseChoice">
			<h3 style="margin: 20px;">
					운동 선택&nbsp;&nbsp;
					<c:if test="${creationFailed}"><font color="red">${exception.getMessage()}</font></c:if>
			</h3>
			<hr>
			<!-- 검색창 -->
			<div id="search" style="width: 700px; height: 40px;">
				<form name="searchForm" method="POST" action="<c:url value='/exercise/find' />">
					<c:if test="${thisIsForChoice ne null}"><input type="hidden" name="thisIsForChoice" value='thisIsForChoice'></c:if>
					<c:if test="${thisIsForChoice eq null}"><input type="hidden" name="routineId" value='${routine.routineId}'></c:if>
					<input type="hidden" name="routineName" value='${routineName}'>  
					<input type="hidden" name="routinePart" value='${routinePart}'>
					<input type="hidden" name="routineTime" value='${routineTime}'>
					<input type="hidden" name="routineLevel" value='${routineLevel}'>
					<input type="hidden" name="routineType" value='${routineType}'>
					<c:forEach var="exercise" items="${exerciseList}">
						<input type="hidden" name="exerciseIdList" value="${exercise.exerciseId}">
					</c:forEach>
					<c:forEach var="sequence" items="${sequenceList}">
						<input type="hidden" name="sequence" value="${sequence}">
					</c:forEach>
					<c:forEach var="repetition" items="${repetitionList}">
						<input type="hidden" name="repetition" value="${repetition}">
					</c:forEach>
					<input type="text" name="searchExercise" placeholder="
						<c:choose>
							<c:when test="${findExerciseFailed}">${exception.getMessage()}</c:when>	
							<c:otherwise>운동명을 입력하세요</c:otherwise>
						</c:choose>
					" style="width: 580px; height: 37px;" autocomplete="off"> 
	    			<input id="searchButton" type="button"
						value="검색" onclick="search()">
				</form>
			</div>
			<!-- 전체 운동 목록 -->
			<form name="choiceForm" method="POST" action="<c:url value='/exercise/choice' />">
			<c:if test="${thisIsForChoice ne null}"><input type="hidden" name="thisIsForChoice" value='thisIsForChoice'></c:if>
			<c:if test="${thisIsForChoice eq null}"><input type="hidden" name="routineId" value='${routine.routineId}'></c:if>
			<div id="list">
				<div style="width: 670px; height: 30px;">
					<input type="hidden" name="routineName" value='${routineName}'>  
					<input type="hidden" name="routinePart" value='${routinePart}'>
					<input type="hidden" name="routineTime" value='${routineTime}'>
					<input type="hidden" name="routineLevel" value='${routineLevel}'>
					<input type="hidden" name="routineType" value='${routineType}'>
					<table id="listTable">
						<tr id="listItem">
							<th style="font-size: 13px;">선택</th>
							<th style="font-size: 13px;">운동명</th>
							<th style="font-size: 13px;">운동 부위</th>
							<th style="font-size: 13px;">순서</th>
							<th style="font-size: 13px;">반복</th>
						</tr>
						<c:forEach var="exercise" items="${exerciseList}">
						<tr id="listTr">
							<td>
								<input type="checkbox" name="checkExerciseId" value='${exercise.exerciseId}' />
							</td>
							<td>${exercise.name}</td>
							<td>${exercise.part}</td>
							<td>
								<input type="text" name="sequence" style="width: 15px; height: 15px; font-size: 12px;" autocomplete="off">
							</td>
							<td>
								<input type="text" name="repetition" style="width: 15px; height: 15px; font-size: 12px;" autocomplete="off">
							</td>
							<td>
								<a href="<c:url value='/exercise/detail'>
											<c:param name='exerciseId' value='${exercise.exerciseId}'/>
										</c:url>">
										<input id="etcButton" type="button" value="더보기">
								</a>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div style="text-align: center;">
					<input id="exerciseChoiceButton" type="button" value="운동 선택 완료"
						onclick="exerciseChoice()"> 
					<c:choose>
							<c:when test="${thisIsForChoice ne null}">
								<a href="<c:url value='/routine/create'>
											<c:param name='routineName' value='${routineName}' />
											<c:param name='routinePart' value='${routinePart}' />
											<c:param name='routineTime' value='${routineTime}' />
											<c:param name='routineLevel' value='${routineLevel}' />
											<c:param name='routineType' value='${routineType}' />
										</c:url>">
										<input id="backButton" type="button" value="돌아가기">
								</a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/routine/update'>
											<c:param name='thisIsForStorage' value='thisIsForStorage' />
											<c:param name='routineId' value='${routine.routineId}' />
											<c:param name='routineName' value='${routineName}' />
											<c:param name='routinePart' value='${routinePart}' />
											<c:param name='routineTime' value='${routineTime}' />
											<c:param name='routineLevel' value='${routineLevel}' />
											<c:param name='routineType' value='${routineType}' />
										</c:url>">
										<input id="backButton" type="button" value="돌아가기">
								</a>
							</c:otherwise>
					</c:choose>
			</div>
			</form>
		</div>
		</div>
	</div>
</body>
</html>