<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
<form action="createUser.do">
<input type="text" name="userID"/><br>
<input type="text" name="nickName"/><br>
<input type="password" name="password"/><br>
<input type="password" name="pw_CHECK" placeholder="비밀번호 확인"><br>
<input type="submit" value="입력"/>
<!-- regDate도 있습니다.(가입날짜) -->
</form>
</body>
</html>