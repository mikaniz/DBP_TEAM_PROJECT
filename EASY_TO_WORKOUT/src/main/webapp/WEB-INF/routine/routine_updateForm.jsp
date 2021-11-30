<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW routine_updateForm</title>
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
#routineInput {
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

#routineUpdateButton, #backButton {
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
function routineUpdateBtn_click() {
	if (updateForm.diaryTitle.value == "") {
		alert("제목을 입력하세요.");
		updateForm.diaryTitle.focus();
		return false;
	}
	if (updateForm.workTime.value == "") {
		alert("운동시간을 입력하세요.");
		updateForm.workTime.focus();
		return false;
	}
	if (updateForm.diaryContents.value == "") {
		alert("내용을 입력하세요.");
		updateForm.diaryContents.focus();
		return false;
	}
	updateForm.submit();
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
			<li><a href="<c:url value='/club/list' />">모임</a></li>
			<li><a href="<c:url value='/routine/list' />">루틴</a></li>
			<li><a href='#'>다이어리</a>
				<ul class="subMenu">
					<li><a href="<c:url value='/diary/my/list' />">MY 다이어리</a></li>
					<li><a href="<c:url value='/diary/all/list' />">전체 다이어리</a></li>
				</ul></li>
		</ul>
	</nav>

	<hr>
	<div class="container">
		<!-- 회원정보 -->
		<jsp:include page="/WEB-INF/member/memberInfo.jsp"/>

		<div style="float: right">
			<!-- 루틴 업데이트 항목 입력 부분  -->
			<div id="routineInput">
				<h3 style="margin: 20px;">루틴 정보 수정</h3>
				<hr>
				<form name="updateForm" method="POST" action="<c:url value='/routine/update' />">
				<input type="hidden" name="routineId" value="${routine.routineId}">
				<table id="routineTable">
					<tr id="routineTableTr">
						<td style="width: 130px;">루틴명 :</td>
						<td><input type="text" name="routineName" value="${routine.rName}"
							style="width: 300px; height: 20px; font-size: 15px;"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 15px;">등록자 :</td>
						<td style="text-align:left; font-size: 13px;">
							<p>${routine.routineCreater}
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">운동 부위 :</td>
						<td>
							<input type="checkbox" name="routinePart" 
								<c:if test="${routine.part.contains("상체")}">checked</c:if>
							value="상체" />상체
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" 
								<c:if test="${routine.part.contains("하체")}">checked</c:if>
							value="하체" />하체
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" 
								<c:if test="${routine.part.contains("복부")}">checked</c:if>
							value="복부" />복부
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" 
								<c:if test="${routine.part.contains("어깨")}">checked</c:if>
							value="어깨" />어깨
							&nbsp;&nbsp; 
							<input type="checkbox" name="routinePart" 
								<c:if test="${routine.part.contains("전신")}">checked</c:if>
							value="전신" />전신
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">소요시간 :</td>
						<td><input type="text" name="routineTime" value="${routine.rTime}"
							style="width: 300px; height: 20px; font-size: 15px;"></td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">운동 강도 :</td>
						<td>
							<input type="radio" name="routineLevel" 
								<c:if test="${routine.difficulty eq 1}">checked</c:if>
							value="1" />1
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" 
								<c:if test="${routine.difficulty eq 2}">checked</c:if>
							value="2" />2
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" 
								<c:if test="${routine.difficulty eq 3}">checked</c:if>
							value="3" />3
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" 
								<c:if test="${routine.difficulty eq 4}">checked</c:if>
							value="4" />4
							&nbsp;&nbsp; 
							<input type="radio" name="routineLevel" 
								<c:if test="${routine.difficulty eq 5}">checked</c:if>
							value="5" />5
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">루틴 유형 :</td>
						<td>
							<input type="radio" name="routineType" 
								<c:if test="${routine.rType eq 0}">checked</c:if>
							value="0" />전체
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="radio" name="routineType" 
								<c:if test="${routine.rType eq 1}">checked</c:if>
							value="1" />개인
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px;">운동 선택 :</td>
						<td>
							<div style="text-align: center; margin-left: 90px;">
								<!-- 검색창 -->
								<div id="search" style="width: 400px; height: 20px;">
										<input type="text" name="searchExercise" placeholder="
											<c:choose>
												<c:when test="${findExerciseFailed}">${exception.getMessage()}</c:when>
												<c:otherwise>운동명을 입력하세요</c:otherwise>
											</c:choose>
										" style="width: 330px; height: 22px;"> 
										<a href="<c:url value='/exercise/find'>
												<c:param name='exerciseName' value='${routine.routineId}'/>
											</c:url>">
	    									<input id="searchButton" type="button" value="검색">
										</a>
								</div>
								<!-- 전체 운동 목록 -->
								<div id="list">
									<div style="width: 380px; height: 20px;">
										<table id="listTable">
											<tr id="listItem">
												<th style="font-size: 13px;">운동명</th>
												<th style="font-size: 13px;">운동 부위</th>
											</tr>
											<c:forEach var="exercise" items="${exerciseList}">
												<tr id="listTr">
													<td>${exercise.name}</td>
													<td>${exercise.part}</td>
													<td><a href="<c:url value='/exercise/detail'>
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
						</div></td>
					</tr>
				</table>
				<div style="text-align: center; margin-left: 130px;">
					<input id="routineUpdateButton" type="button" value="루틴 수정"
						onclick="routineUpdateBtn_click()"> 
					<input id="backButton" type="button" value="돌아가기" 
						onclick="history.back()">
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>