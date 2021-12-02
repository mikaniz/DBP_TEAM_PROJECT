<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ETW DIARY WRITE</title>
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

#diaryDetail {
	width: 700px;
	height: 600px;
	border: 1px solid;
}

#dairyTable {
	border-collapse: collapse;
	width: 700px;
	height: 450px;
	float: center;
}

#diaryTableTr {
	height: 45px;
	width: 700px;
	table-layout: fixed;
}

#diaryUpdateButton {
	width: 150px;
	height: 45px;
	text-align: center;
	margin: 10px;
	background-color: #90ABDA;
}

#diaryDeleteButton {
	width: 150px;
	height: 45px;
	text-align: center;
	margin: 10px;
	background-color: #90ABDA;
}

#backButton {
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
function diaryDeleteButton_click() {
	if (window.confirm("다이어리를 삭제하시겠습니까?")) {
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
			<!-- 다이어리 상세 정보  -->
			<div id="diaryDetail">
				<h3 style="margin: 20px;">다이어리 상세 정보</h3>
				<hr>
				<table id="dairyTable">
					<tr id="dairyTableTr">
						<td style="width: 130px; font-size: 15px;">제목 :</td>
						<td style="text-align:left; font-size: 13px;">
							<p>${diary.title}
						</td>
					</tr>
					<tr id="dairyTableTr">
						<td style="width: 130px; font-size: 15px;">작성자 :</td>
						<td style="text-align:left; font-size: 13px;">
							<p>${diary.author}
						</td>
					</tr>
					<tr id="dairyTableTr">
						<td style="width: 130px; font-size: 15px;">날짜 :</td>
						<td style="text-align:left; font-size: 13px;">
							<p><fmt:formatDate value="${diary.date}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					<tr id="dairyTableTr">
						<td style="width: 130px; font-size: 15px;">운동 시간 :</td>
						<td style="text-align:left; font-size: 13px;">
							<p>${diary.workTime} 분
						</td>
					</tr>
				 	<tr id="diaryTableTr">
						<td style="width: 130px; font-size: 15px;">공개 여부 :</td>
						<td style="text-align: left; font-size: 13px;">
							<c:choose>
								<c:when test="${diary.isPrivate eq 1}"><p>비공개</c:when>
								<c:otherwise><p>공개</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr id="diaryTableTr">
						<td style="width: 130px; font-size: 15px;">내용 :</td>
						<td colspan=2 style="text-align:left;">
							<textarea rows=13 cols=60
								style="resize: none; font-size; 14px;" readonly="readonly" disabled>
								${diary.contents}
							</textarea> 
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-left: 70px;">
					<form name="deleteForm" method="POST" action="<c:url value='/diary/detail' />">
						<c:if test="${isAuthor}">
							<a href="<c:url value='/diary/update'>
								<c:param name='diaryId' value='${diary.diaryId}' /></c:url>">
									<input id="diaryUpdateButton" type="button" value="다이어리 수정">
							</a>
							<input type="hidden" name="diaryId" value="${diary.diaryId}">
							<input type="hidden" name="thisIsForDel" value="thisIsForDel" />
							<input id="diaryDeleteButton" type="button" value="다이어리 삭제"
								onclick="diaryDeleteButton_click()">
						</c:if>
						<input id="backButton" type="button" value="돌아가기" 
							onclick="history.back()">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>