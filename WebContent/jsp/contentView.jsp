<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="chat.jsp">채팅방</a><!--userID,nickName-->
	<a href="serviceCenter.jsp">서비스 센터</a><!--userID,nickName-->
	<a href="locationView.jsp">지도/컨텐츠 리스트</a>
	<a href="myPage.jsp">마이페이지</a><!--로그인 시에만 보이게 처리(userID,nickName)-->
	<a href="#">로그인</a><!--javascript써서 로그인폼 띄우기-->
	<a href="createUserForm.jsp">회원가입</a>
	<a href="reservationView.jsp">예약하기</a>
	<a href="reviewList.jsp">리뷰 더보기</a><!-- contentID -->
	<!--<a href="logout.do">로그아웃</a> 로그인시 태그 생성하게 처리-->
<c:set var='param' value='<%=request.getParameter("contenttypeid") %>'/>
	<c:choose>
		<c:when test="${param.contenttypeid == '32' }">
			<table border="1">
				<tr>
					<th>방이름</th>
					<th>방크기</th>
					<th>방개수</th>
					<th>기본인원수</th>
					<th>최대인원수</th>
					<th>비수기주중최소</th>
					<th>비수기주말최소</th>
					<th>성수기주중최소</th>
					<th>성수기주말최소</th>
					<th>사진1</th>
					<th>사진2</th>
				</tr>
				<c:forEach var="detail" items="${detail}">
					<tr>
						<td>${detail.roomtitle }</td>
						<td>${detail.roomsize1 }</td>
						<td>${detail.roomcount }</td>
						<td>${detail.roombasecount }</td>
						<td>${detail.roommaxcount }</td>
						<td>${detail.roomoffseasonminfee1 }</td>
						<td>${detail.roomoffseasonminfee2 }</td>
						<td>${detail.roompeakseasonminfee1 }</td>
						<td>${detail.roompeakseasonminfee2 }</td>
						<td><img alt="방사진이에요" src="${detail.roomimg1 }"></td>
						<td><img alt="방사진2에요" src="${detail.roomimg2 }"></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:when test="${param.contenttypeid == '12' }">
			<table border="1">
				<tr>
					<th>제목</th>
					<th>내용</th>
				</tr>
				<c:forEach var="detail" items="${detail}">
					<tr>
						<td>${detail.infoname }</td>
						<td>${detail.infotext }</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			안되요
		</c:otherwise>
	</c:choose>
	
</body>
</html>