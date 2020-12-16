<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
$(function(){
	$("#btnUpdate").click(function(){
		document.form1.action="${path}/memo/update/${dto.idx}";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
			location.href="${path}/memo/delete/${dto.idx}";
		}
	})
});
</script>

</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모 보기</h2>

<form name="form1" method="post">
<table border="1" width="500px">
	<tr>
		<td>번호</td>
		<td>${dto.idx}</td>
	</tr>
	
	<tr>
		<td>이름</td>
		<td><input name="write" value="${dto.write}"/></td>
	</tr>
	
	<tr>
		<td>메모</td>
		<td><input name="memo" value="${dto.memo}"/></td>
	</tr>
	
	<tr align="center">
		<td colspan="2">
			<input type="hidden" name="idx" value="${dto.idx }"/>
			<button type="button" id="btnUpdate">수정하기</button>
			<button type="button" id="btnDelete">삭제하기</button>
			
		</td>
	</tr>

</table>

</form>
</body>
</html>