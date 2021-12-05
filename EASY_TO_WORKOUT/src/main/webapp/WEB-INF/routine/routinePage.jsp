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
				<form name="searchForm" method="POST" action="<c:url value='/routine/find' />">
					<input type="text" name="searchRoutine" placeholder=
					"<c:choose>
					<c:when test="${findRoutineFailed}">
								 ${exception.getMessage()}</c:when>	 			<c:otherwise>운동부위를 입력하세요(상체/하체/복부/어깨/전신)</c:otherwise>
					</c:choose>"
					 style="width: 580px; height: 42px;" autocomplete="off"> 
	    			<input id="searchButton" type="button"
						value="검색" onclick="search()">
				</form>
			</div>
			<!-- 전체 루틴 목록 -->
			<div id="list">
				<div style="width: 670px; height: 30px;">
					<table id="listTable">
						<tr id="listItem">
							<th>루틴명</th>
							<th>등록자</th>
							<th>운동부위</th>
							<td><form name="sortForm" method="POST" action="<c:url value='/routine/list' />">
								<select name="sortRoutine" style="width: 80px; height: 37px;" onchange="this.form.submit()">
									<option value="1" 
										<c:if test="${checkedOne}">selected</c:if>>전체</option>
									<option value="2"
										<c:if test="${checkedTwo}">selected</c:if>>공개</option>
									<option value="3"
										<c:if test="${checkedThree}">selected</c:if>>개인</option>
								</select>
							</form></td>
						</tr>
						<c:forEach var="routine" items="${routineList}">
							<tr id="listTr">
								<td>${routine.rName}</td>
								<td>${routine.routineCreater}</td>
								<td>${routine.part}</td>
								<td>
									<a href="<c:url value='/routine/detail'>
											<c:param name='routineId' value='${routine.routineId}'/>
										</c:url>">
										<input id="etcButton" type="button" value="더보기">
									</a>
								</td>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>