<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW schedule_usageForm</title>
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

#clubInfoInput {
	width: 700px;
	height: 540px;
	border: 1px solid;
	overflow: auto;
}

#clubTable {
	border-collapse: collapse;
	width: 700px;
	height: 360px;
	float: center;
	table-layout: fixed;
}

#clubTableTr {
	height: 20px;
	width: 700px;
}

#listItem {
	border-bottom: 1px solid;
	height: 40px;
	width: 700px;
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

th, td {
	text-align: center;
}
</style>
<script>
function chooseUsage() {
	if(openForm.checkRoutine.value == "") {
		alert("루틴을 선택해주세요.");
		theForm.checkRoutine.focus();
		return false;
	}
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
		</div>

		<div style="float: right">
			<!-- 모임 개설 항목 입력 부분  -->
			<form name="openForm" method="POST" action="<c:url value='/club/schedule/create' />">
				<div id="clubInfoInput">
					<input type="hidden" name="thisIsForUsage" value="thisIsForUsage">
					<table id="clubTable">
						<tr id="listItem">
							<td></td>
							<th>루틴명</th>
							<th>등록자</th>
							<th>운동부위</th>
							<td></td>
						</tr>
						<c:forEach var="routine" items="${routineList}">						
							<tr id="clubTableTr">
								<td><input type="checkbox" name="checkRoutine" value="${routine.routineId}"></td>
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
							</tr>
						</c:forEach>
					</table>					
				</div>
				<div style="text-align: center; margin-left: 30px;">
					<input id="chooseButton" type="button" value="루틴 선택 완료"
								onclick="chooseUsage()"> 
					<input id="chooseButton" type="button" value="돌아가기" 
								onclick="history.back()">
				</div>
			</form>
		</div>
		
	</div>
</body>
</html>