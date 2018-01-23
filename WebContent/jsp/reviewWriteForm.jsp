<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="reviewWrite.do">
<input type="text" name="title"/>
<textarea rows="10" cols="10" name="content"></textarea>
<input type="hidden" name="scope" value="5"/>
<input type="text" name="nickName"/>
<input type="hidden" name="contentID"/>
</form>
</body>
</html>