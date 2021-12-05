<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="width: 400px; height: 600px; border: 1px solid; float: left; margin-right: 10px;">
	<div style="height: 530px;">
		<h3 style="margin: 20px;">회원정보</h3>
		<table id="memberDataTable">
			<tr>
				<td><img src="<c:url value='/images/somsom.jpg' />" width=150px height=230px />
				</td>
				<td>아이디 : ${loginMember.id}
					<p>이름 : ${loginMember.name}
					<p /> 등급 : <c:choose>
						<c:when test="${loginMember.grade eq 'green'}">새싹</c:when>
						<c:otherwise>마스터</c:otherwise>
					</c:choose>
					<p /> <br> <a href="<c:url value='/member/update' />">회원정보 수정</a>
					<p>
						<a href="<c:url value='/member/logout' />">로그아웃</a>
				</td>
			</tr>
		</table>
		<br><hr>
		<c:if test="${infoIsMaster}">
			<article>
				<h4 style="margin: 20px;">개설 모임 목록</h4>
				<ul>
					<c:forEach var="club" items="${masterClubList}">
						<li><a href="<c:url value='/club/detail'>
										<c:param name='clubId' value='${club.clubId}' />
									</c:url>">${club.clubName}</a></li>
					</c:forEach>
				</ul>
			</article>
		</c:if>
		<article>
			<h4 style="margin: 20px;">내 모임 목록</h4>
			<ul>
				<c:forEach var="club" items="${myClubList}">
					<li><a href="<c:url value='/club/detail'>
									<c:param name='clubId' value='${club.clubId}' />
								</c:url>">${club.clubName}</a></li>
				</c:forEach>
			</ul>
		</article>
	</div>
	<div style="height: 50px;">
		<!-- 버튼 -->
		<c:choose>
			<%-- allDiaryPage, myDiaryPage 다이어리 작성 버튼 --%>
				<c:when test="${btnType eq 'diaryWrite'}">
					<input id="writeButton" type="button" value="다이어리 작성" 
						onclick="location.href='<c:url value='/diary/write' />'">
				</c:when>
			<%-- routinePage, routine_detail 루틴 등록 버튼 --%>
				<c:when test="${btnType eq 'routineCreate'}">
					<c:if test="${thisIsForUsage eq null}">
						<input id="createButton" type="button" value="루틴 등록" 
							onclick="location.href='<c:url value='/routine/create' />'">
					</c:if>
				</c:when>
			<%-- clubPage 모임 개설 버튼 --%>
				<c:when test="${btnType eq 'clubCreate'}">
					<input id="createButton" type="button" value="모임 개설" 
						onclick="location.href='<c:url value='/club/open/form' />'">
				</c:when>
			<%-- club_detail 스케줄 등록 버튼 --%>
				<c:when test="${btnType eq 'scheduleCreate'}">
					<c:if test="${isMaster eq '1'}">
						<div style="height: 50px;">
							<form name="createForm"  method="GET" action="<c:url value='/club/schedule/create' />">
								<input type="hidden" name="clubId" value="${club.clubId}">
								<input id="createButton" type="button" value="스케줄 등록" onclick="form.submit()">
							</form>
						</div>
					</c:if>
					<c:if test="${!infoIsMaster}">
						<c:if test="${club.signUp eq '1'}">
							<c:if test="${isInClub eq '0' }">
								<div style="height: 50px;">
									<form name="joinForm"  method="GET" action="<c:url value='/club/join' />">
										<input type="hidden" name="clubId" value="${club.clubId}">
										<input type="hidden" name="thisIsForJoin" value="thisIsForJoin">
										<input id="createButton" type="button" value="모임 가입" onclick="joinClub()">
									</form>
								</div>
							</c:if>
						</c:if>
					</c:if>
					<c:if test="${successJoin eq 'successJoin'}"><script>alert('가입이 완료되었습니다.')</script></c:if>
				</c:when>
			<%-- schedule_list 일정 등록 버튼 --%>
				<c:when test="${btnType eq ''}">
					<input type="button" value="일정 등록" onclick=""
						style="width: 400px; height: 50px;">
				</c:when>
				<c:otherwise></c:otherwise>
		</c:choose>
	</div>
</div>