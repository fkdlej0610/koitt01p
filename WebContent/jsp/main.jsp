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
<a href="#">로그인</a><!--javascript써서 로그인폼 뛰우기-->
<a href="createUserForm.jsp">회원가입</a>

<form action="main.do">
<input type="hidden" name="contentTypeID" value="12">
<input type="submit">출력</input>
</form>
<table border="1">
<thead></thead>
<c:forEach var="topContent" items="${topList}">
<tbody>
<tr>
<td>${topContent.contentID}</td>
</tr>
</tbody>
</c:forEach>
</table>

<!--<a href="logout.do">로그아웃</a> 로그인시 태그 생성하게 처리-->
</body>
</html>