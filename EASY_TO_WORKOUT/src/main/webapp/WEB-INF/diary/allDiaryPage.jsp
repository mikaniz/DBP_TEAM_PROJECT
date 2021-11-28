<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW AllDiaryPage</title>
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

#etcButton {
	background-color: #90ABDA;
}

#searchButton {
	width: 100px; 
	height: 47px;
	background-color: #90ABDA;
}

#writeButton {
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
				<!-- 다이어리 작성 버튼 -->
				<input id="writeButton" type="button" value="다이어리 작성" 
					onclick="location.href='<c:url value='/diary/write' />'">
			</div>
		</div>

		<div style="float: right">
			<!-- 검색창 -->
			<div id="search" style="width: 700px; height: 50px;">
				<form name="searchForm" method="POST" action="<c:url value='/diary/all/find' />">
					<input type="text" name="searchAllDiary" placeholder="
						<c:choose>
							<c:when test="${findDiaryFailed}">${exception.getMessage()}</c:when>
							<c:otherwise>제목을 입력하세요.</c:otherwise>
						</c:choose>	
					" style="width: 580px; height: 42px;">
						<input id="searchButton" type="button" value="검색" onclick="search()">
				</form>
			</div>
			<!-- 전체 다이어리 목록 -->
			<div id="list">
				<div style="width: 670px; height: 30px;">
					<table id="listTable">
						<tr id="listItem">
							<th>제목</th>
							<th>날짜</th>
							<th>운동 시간</th>
							<th>작성자</th>
							<td><form name="sortForm" method="POST" action="<c:url value='/diary/all/list' />">
								<select name="sortDiary" style="width: 80px; height: 37px;" onchange="this.form.submit()">
									<option value="1" <c:if test="${checkedOne}">selected</c:if>>날짜순</option>
									<option value="2" <c:if test="${checkedTwo}">selected</c:if>>운동 시간순</option>
								</select>
							</form></td>
						</tr>
						<c:forEach var="diary" items="${diaryList}">
							<tr id="listTr">
								<td>${diary.title}</td>
								<td><fmt:formatDate value="${diary.date}" pattern="yyyy-MM-dd" /></td>
								<td>${diary.workTime}</td>
								<td>${diary.author}</td>
								<td>
									<a href="<c:url value='/diary/detail'>
												<c:param name='diaryId' value='${diary.diaryId}' />
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