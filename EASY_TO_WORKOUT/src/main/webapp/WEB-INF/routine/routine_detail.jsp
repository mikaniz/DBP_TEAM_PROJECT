<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW routine_details</title>
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

#routineInfoOutput {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#routineTable {
	border-collapse: collapse;
	width: 700px;
	height: 200px;
	float: center;
}

#routineTableTr {
	height: 20px;
	width: 700px;
	table-layout: fixed;
}

#routineUpdateButton, #routineDeleteButton, #backButton {
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
<script>
function askDelete() {
	if (window.confirm('루틴을 삭제하시겠습니까?')) {
		deleteForm.submit();
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
			<!-- 루틴 상세 정보 출력 부분  -->
			<div id="routineInfoOutput">
				<h3 style="margin: 20px;">루틴 상세 정보</h3>
				<hr>
				<table id="routineTable">
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">루틴명 :</td>
						<td style="text-align:left; font-size: 12px;">
							<p>${routine.rName}
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">등록자 :</td>
						<td style="text-align:left; font-size: 12px;">
							<p>${routine.routineCreater}
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">운동 부위 :</td>
						<td style="text-align:left; font-size: 12px;">
							<p>${routine.part}
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">소요시간 :</td>
						<td style="text-align:left; font-size: 12px;">
							<p>${routine.rTime}분
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">운동 강도 :</td>
						<td style="text-align:left; font-size: 12px;">
							<p>${routine.difficulty} (1~5)
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">루틴 유형 :</td>
						<td style="text-align:left; font-size: 12px;">
							<p><c:choose>
									<c:when test="${routine.rType == 0}">공개</c:when>
									<c:otherwise>개인</c:otherwise>
								</c:choose>
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">선택된 운동:</td>
						<td style="text-align:left; font-size: 12px;">
							<p><c:forEach var="exercise" items="${exerciseList}">
									<c:choose>
										<c:when test="${exercise == exerciseList[0]}">${exercise.name}</c:when>
										<c:otherwise>, ${exercise.name}</c:otherwise>
									</c:choose> 
								</c:forEach>
						</td>
					</tr>
					<tr id="routineTableTr">
						<td style="width: 130px; font-size: 14px;">운동 방법 :</td>
						<td style="text-align:left; font-size: 12px;">
							<textarea rows=8 cols=70
								style="overflow: scroll;resize: none; font-size: 12px;" readonly="readonly" disabled><c:forEach var="exercise" items="${exerciseList}">${exercise.name} : ${exercise.method}<%= "\n\n" %></c:forEach>
							</textarea></td>
					</tr>
				</table>
				<p>
				<div style="text-align: center;">
					<form name="deleteForm"  method="GET" action="<c:url value='/routine/list' />">
						<c:if test="${thisIsForUsage eq null}">
							<c:if test="${isCreater}">
								<a href="<c:url value='/routine/update'>
										<c:param name='routineId' value='${routine.routineId}' />
									</c:url>">
										<input id="routineUpdateButton" type="button" value="루틴 수정">
								</a>
								<input type="hidden" name="routineId" value="${routine.routineId}">
								<input type="hidden" name="thisIsForDel" value="${thisIsForDel}">
								<input id="routineDeleteButton" type="button" value="루틴 삭제" onclick="askDelete()">
							</c:if>
							<input id="backButton" type="button" value="돌아가기" 
								onclick="location.href='<c:url value='/routine/list' />'">
						</c:if>
						<c:if test="${thisIsForUsage ne null}">
							<input id="backButton" type="button" value="돌아가기" 
								onclick="history.back()">
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>