<%@page contentType="text/html; charset=utf-8" %>
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

#clubInfoOutput {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#clubTable {
	border-collapse: collapse;
	width: 700px;
	height: 450px;
	float: center;
}

#clubTableTr {
	height: 30px;
	width: 700px;
	table-layout: fixed;
}

#backButton {
	width: 150px;
	height: 45px;
	text-align: center;
	margin: 10px;
	background-color: #90ABDA;
}

#scheduleDetailButton {
	width: 100px; 
	height: 35px;
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
	if (window.confirm('모임을 삭제하시겠습니까?')) {
		deleteForm.submit();
	}
}

function joinClub() {
	if (window.confirm('모임에 가입하시겠습니까?')) {
		joinForm.submit();
	}
}

function askOut() {
	if (window.confirm('모임을 탈퇴하시겠습니까?')) {
		deleteForm.submit();
	}
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
		<!-- 회원정보 -->
		<jsp:include page="/WEB-INF/member/memberInfo.jsp"/>

		<div style="float: right">
			<!-- 모임 상세 정보 출력 부분  -->
			<div id="clubInfoOutput">
				<h3 style="margin: 20px;">
					모임 상세 정보 &nbsp; &nbsp;
				</h3>
				<hr>
				<table id="clubTable">
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 이름 :</td>
						<td style="text-align:left;">
							<p>${club.clubName}
						</td>
						<td >
						<a href="<c:url value='/club/schedule/list'>
										<c:param name='clubId' value='${club.clubId}'/>
										<c:param name='clubName' value='${club.clubName}' />
									</c:url>">
								<input id="scheduleDetailButton" type="button" value="일정 보기">
						</a>
					</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 유형 :</td>
						<td colspan=2 style="text-align:left;">
							<p>
							<c:choose>
								<c:when test="${club.openCycle eq '0'}">정기적</c:when>
								<c:otherwise>일시적</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">초대 유형 :</td>
						<td colspan=2 style="text-align:left;">
							<p>
							<c:choose>
								<c:when test="${club.signUp eq '0'}">초대 가입</c:when>
								<c:otherwise>자유 가입</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr id="clubTableTr">
						<td style="width: 130px;">모임 소개 :</td>
						<td colspan=2 style="text-align:left;">
							<textarea rows=13 cols=60
								style="resize: none; font-size: 14px;" readonly="readonly" disabled>
								${club.clubIntro}
							</textarea></td>
					</tr>
				</table>
				<div style="text-align: center;">
					<form name="deleteForm"  method="GET" action="<c:url value='/club/detail' />">
						<c:if test="${isMaster eq '1'}">							
								<input type="hidden" name="thisIsForDel" value="thisIsForDel">
								<input type="hidden" name="clubId" value="${club.clubId}">
								<input id="backButton" type="button" value="모임 삭제" onclick="askDelete()">							
						</c:if>
						<c:if test="${isInClub eq '1' }">
							<c:if test="${isMaster eq '0'}">	
								<input type="hidden" name="thisIsForOut" value="thisIsForOut">
								<input type="hidden" name="clubId" value="${club.clubId}">
								<input id="backButton" type="button" value="모임 탈퇴" onclick="askOut()">
							</c:if>	
						</c:if>
						<input id="backButton" type="button" value="돌아가기"
							onclick="location.href='<c:url value='/club/list' />'">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>