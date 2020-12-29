<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<h2>상품등록</h2>
<form id="form1" name="form1" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>상품명</td>
			<td><input type="text" name="product_name"/></td>
		</tr>
		
		<tr>
			<td>가격</td>
			<td><input type="text" name="price"/></td>
		</tr>
		
	</table>
</form>


</body>
</html>