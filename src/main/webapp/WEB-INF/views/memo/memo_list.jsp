<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>

<script>
function memo_view(idx){
	location.href="${path}/memo/view/"+idx;
}
</script>

</head>
<body>
<%@ include file="../include/menu.jsp" %>


<h2>한줄 메모장</h2>

<form action="${path}/memo/insert.do">
이름:<input name="write" size="10"/>
메모:<input name="memo" size="40"/>
<input type="submit" value="확인"/><br>

</form>




<table border="2" style="width:500px">
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>메모</td>
		<td>날짜</td>
	</tr>
	<c:forEach items="${list}" var="row">
		<tr>
			<td>${row.idx}</td>
			<td>${row.write}</td>
			<td><a href="#" onclick="memo_view('${row.idx}')">${row.memo}</a></td>
			<td><fmt:formatDate value="${row.post_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			
		</tr>
	
	</c:forEach>

</table>
</body>
</html>