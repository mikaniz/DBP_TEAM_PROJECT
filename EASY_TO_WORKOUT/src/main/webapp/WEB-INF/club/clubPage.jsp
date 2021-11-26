<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW ClubPage</title>
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

#myClubScheduleTable {
	border-collapse: collapse;
	width: 400px;
	height: 100px;
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

#etcButton, #joinButton {
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
		<a href="<c:url value='/main' />"><img src="<c:url value='/images/logo.PNG' />"
			width=500px height=130px /></a>
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
		<!-- 회원정보 틀 -->
		<div style="width: 400px; height: 600px; border: 1px solid; float: left; margin-right: 10px;">
			<div style="height: 530px;">
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
				<br><hr>
				<article>
					<h4 style="margin: 20px;">내 모임 목록</h4>
					<ul>
						<li><a href='#'>투현진</a></li>
						<li><a href='#'>ETW</a></li>
					</ul>
				</article>
			</div>
			<div style="height: 50px;">
				<!-- 모임 개설 버튼 -->
				<input id="createButton" type="button" value="모임 개설" onclick="location.href='<c:url value='/club/open/form' />'">
			</div>
		</div>

		<div style="float: right">
			<!-- 검색창 -->
			<div id="search" style="width: 700px; height: 50px;">
				<form name="searchForm" method="POST" action="<c:url value='/club/find' />">
					<input type="text" name="searchClub" placeholder="
						<c:choose>
							<c:when test="${findClubFailed}">${exception.getMessage()}</c:when>
							<c:otherwise>모임을 입력하세요</c:otherwise>
						</c:choose>
					" style="width: 580px; height: 42px;"> 
	    			<input id="searchButton" type="button"
						value="검색" onclick="search()">
				</form>
			</div>
			<!-- 전체 모임 목록 -->
			<div id="list">
				<div style="width: 670px; height: 30px;">
					<table id="listTable">
						<tr id="listItem">
							<th>모임명</th>
							<th>개설자</th>
							<th>인원 수</th>
							<td><form name="sortForm" method="POST" action="<c:url value='/club/list' />">
								<select name="sortClub" style="width: 80px; height: 37px;" onchange="this.form.submit()">
									<option value="0" selected>전체</option>
									<option value="1" 
										<c:if test="${checkedOne}">selected</c:if>>이름순</option>
									<option value="2"
										<c:if test="${checkedTwo}">selected</c:if>>회원순</option>
									<option value="3"
										<c:if test="${checkedThree}">selected</c:if>>자유가입</option>
								</select>
							</form></td>
						</tr>
						<c:forEach var="club" items="${clubList}">							
							<tr id="listTr">
								<td>${club.clubName}</td>
								<td>${club.clubMaster}</td>
								<td>${club.countClub}</td>
								<td>
									<a href="<c:url value='/club/detail'>
											<c:param name='clubId' value='${club.clubId}'/>
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