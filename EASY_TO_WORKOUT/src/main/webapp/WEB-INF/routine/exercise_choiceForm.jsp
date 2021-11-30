<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW exercise_choiceForm</title>
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

#etcButton{
	background-color: #90ABDA;
}

#searchButton {
	width: 100px; 
	height: 47px;
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
			<!-- 검색창 -->
			<div id="search" style="width: 700px; height: 50px;">
				<form name="searchForm" method="POST" action="<c:url value='/exercise/find' />">
					<input type="text" name="searchExercise" placeholder="
						<c:choose>
							<c:when test="${findExerciseFailed}">${exception.getMessage()}</c:when>	
							<c:otherwise>운동명을 입력하세요</c:otherwise>
						</c:choose>
					" style="width: 580px; height: 42px;"> 
	    			<input id="searchButton" type="button"
						value="검색" onclick="search()">
				</form>
			</div>
			<!-- 전체 운동 목록 -->
			<div id="list">
				<div style="width: 670px; height: 30px;">
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
								<input type="checkbox" name="choice" value='${exercise.exerciseId}' />
							</td>
							<td>${exercise.name}</td>
							<td>${exercise.part}</td>
							<td>
								<input type="text" name="exerciseSequence" style="width: 15px; height: 15px; font-size: 12px;">
							</td>
							<td>
								<input type="text" name="exerciseRepetition" style="width: 15px; height: 15px; font-size: 12px;">
							</td>
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
		</div>
	</div>
</body>
</html>