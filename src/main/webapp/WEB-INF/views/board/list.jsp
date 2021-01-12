<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board 목록</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>

<script>
$(function(){
	$("#btnWrite").click(function(){
		location.href="${path}/board/write.do";
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<button type="button" id="btnWrite">글쓰기</button>
	<table border="2">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>이름</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
		
		<c:forEach items="${map.list}" var="row">
			<tr>
				<td>${row.bno}</td>
				<td>${row.title}</td>
				<td>${row.name}</td>
				<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${row.viewcnt}</td>
			</tr>
		</c:forEach>
	
	</table>


</body>
</html>