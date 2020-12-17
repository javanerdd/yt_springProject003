<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<%@ include file="../include/header.jsp" %>
<script>
$(function(){
	$("#bntAdd").click(function(){
		location.href="${path}/shop/product/write.do";
	});
});

</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품목록</h2>
<!-- 관리자만 상품등록표시 -->
<c:if test="${sessionScope.admin_userid!=null }">
	<button type="button" id="btnAdd">상품등록</button>
</c:if>
<table border="1">
	<tr>
		<th>상품ID</th>
		<th>&nbsp;</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
	
	<c:forEach items="${list }" var="row">
		<tr align="center">
			<td>${row.product_id }</td>
			<td><img src="${path}/images/${row.picture_url}" width="150" height="100"></td>
			<td><a href="${path}/shop/product/detail/${row.product_id}">${row.product_name}</a></td>
			<td>
				<fmt:formatNumber value="${row.product_price}" pattern="#,###"/>
			</td>
		</tr>
	</c:forEach>

</table>


</body>
</html>