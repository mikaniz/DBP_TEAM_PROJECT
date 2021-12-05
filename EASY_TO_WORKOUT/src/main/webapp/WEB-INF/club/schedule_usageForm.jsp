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
	width: 700px;
	height: 540px;
	border: 1px solid;
	margin-top: 10px;
	overflow: scroll;
	position: relative;
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

#chooseButton {
	width: 150px;
	height: 45px;
	text-align: center;
	margin: 10px;
	background-color: #90ABDA;
}

#searchButton {
	width: 100px; 
	height: 47px;
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

function chooseUsage() {
	openForm.submit();
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
				<form name="searchForm" method="POST" action="<c:url value='/routine/find' />">
					<input type="hidden" name="thisIsForUsage" value="thisIsForUsage">
					<input type="hidden" name="clubId" value="${clubId}">
					<input type="hidden" name="creationDate" value="${creationDate}">
					<input type="hidden" name="contactAddress" value="${contactAddress}">
					<input type="hidden" name="notice" value="${notice}">
					<input type="text" name="searchRoutine" placeholder="
						<c:choose>
							<c:when test="${findRoutineFailed}">${exception.getMessage()}</c:when>
							<c:otherwise>루틴명을 입력하세요</c:otherwise>
						</c:choose>
					" autocomplete="off" style="width: 580px; height: 42px;"> 
	    			<input id="searchButton" type="button"
						value="검색" onclick="search()">
				</form>
			</div>
			<!-- 전체 루틴 목록 -->
			<div id="list">
				<form name="openForm" method="POST" action="<c:url value='/club/schedule/create' />">
						<input type="hidden" name="thisIsForUsage" value="thisIsForUsage">
						<input type="hidden" name="clubId" value="${clubId}">
						<input type="hidden" name="creationDate" value="${creationDate}">
						<input type="hidden" name="contactAddress" value="${contactAddress}">
						<input type="hidden" name="notice" value="${notice}">
						<table id="listTable">
							<tr id="listItem">
								<td></td>
								<th>루틴명 </th>
								<th>등록자</th>
								<th>운동부위</th>
								<td></td>
							</tr>
							<c:forEach var="routine" items="${routineList}">						
								<tr id="listTr">
									<td><input type="checkbox" name="checkRoutineId" value="${routine.routineId}"></td>
									<td>${routine.rName}</td>
									<td>${routine.routineCreater}</td>
									<td>${routine.part}</td>
									<td>
										<a href="<c:url value='/routine/detail'>
												<c:param name='routineId' value='${routine.routineId}'/>
												<c:param name='thisIsForUsage' value='thisIsForUsage' />
											</c:url>">
											<input id="etcButton" type="button" value="더보기">
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>					
					
					<div style="text-align: center; margin-left: 170px; position: absolute; bottom:0;">
						<input id="chooseButton" type="button" value="루틴 선택 완료"
									onclick="chooseUsage()"> 
						<input id="chooseButton" type="button" value="돌아가기" 
									onclick="location.href='<c:url value='/club/schedule/create' />'">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>