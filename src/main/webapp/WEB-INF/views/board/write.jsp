<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script>
$(function(){
	$("#btnSave").click(function(){
		alert('글쓰기 완료');
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>글쓰기 페이지</h2>
<form id="form1" name="form1" action="${path}/board/insert.do" method="post">
<div>
	제목: <input type="text" name="title" id="title" size="80"
	placeholder="제목을 입력하세요."/>
</div>

<div style="width:800px;">
	내용:<textarea id="content" name="content" rows="3" cols="80" 
	placeholder="내용을 입력하세요"></textarea>
</div>

<div>
 첨부파일을 등록하세요
 <div class="fileDrop"></div>
 <div id="uploadList"></div>
</div>

<div style="width:700px; text-align:center">
	<button type="button" id="btnSave">확인</button>

</div>
</form>


</body>
</html>