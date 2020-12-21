<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
	
<%@ include file="include/header.jsp" %>
</head>
<body>
<%@ include file="include/menu.jsp" %>
1111111111111111111111111<br>
<c:if test="${sessionScope.userid != null}">
	2222222222222222222222
	<h2>${sessionScope.name} (${sessionScope.userid})님의 방문을 환영합니다.</h2>
	
</c:if>
3333333333333333
<h1>Hello world!</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
