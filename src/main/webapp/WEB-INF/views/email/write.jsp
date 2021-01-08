<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Context-Type" content="text/html; charset=UTF-8">
<title>이메일 보내기</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@include file="../include/menu.jsp" %>
<h2>이메일 보내기</h2>

<form action="${path}/email/send.do" method="post">
발신자 이름:<input type="text" name="senderName"/><br>
발신자 이메일 주소:<input type="text" name="senderMail"/><br>
수신자 이메일 주소:<input type="text" name="receiveMail"/><br>
제목:<input type="text" name="subject"/><br>
내용:<textarea rows="5" cols="80" name="message"></textarea><br>
<input type="submit" value="전송"/>
</form>
<span style="color:red;">${message}</span>

</body>
</html>