<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

</head>
<body>
<%@ include file="../include/menu.jsp" %>
<form id="form1" action="${path}/upload/uploadForm" method="post"
enctype="multipart/form-data" target="iframe1">

<input type="file" name="file"/>
<input type="submit" value="업로드"/>
</form>
<iframe name="iframe1"></iframe>

</body>
</html>