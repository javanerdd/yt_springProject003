<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script>
	$(function() {
		$("#btnLogin").click(function() {
			var userid = $("#userid").val();
			var passwd = $("#passwd").val();
			if (userid == "") {
				alert("아이디를 입력하세요");
				$("userid").focus();
				return;
			}
			if (passwd == "") {
				alert("비밀번호를 입력하세요");
				$("passwd").focus();
				return;
			}
			document.form1.action="${path}/admin/login_check.do";
			document.form1.submit();
		});

	});
</script>

</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<form name="form1" method="post">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userid" id="userid" /></td>
		</tr>

		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" id="passwd" /></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<button type="button" id="btnLogin">로그인</button>
				<c:if test="${message=='nologin' }">
					<div style="color:red;">
						먼저 로그인 하세요.
					</div>
				</c:if>
				
				<c:if test="${message=='error'}">
					<div style="color:red;">
					 아이디 또는 비밀번호가 일치하지 않습니다.
					</div>
				</c:if>
				
				<c:if test="${message=='logout' }">
					<div style="color:red;">
						로그아웃 되었습니다.
					</div>
				</c:if>
				
			</td>
		</tr>
		</table>


	</form>





</body>
</html>