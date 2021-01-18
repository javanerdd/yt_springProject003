<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<%@ include file="../include/header.jsp" %>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>

<script>
$(function(){
	listReply("1")
	$("#btnReply").click(function(){
		reply();
	});
	
	$("#btnList").click(function(){
		location.href="${path}/board/list.do";
	});
});

function reply(){
	var replytext=$("#replytext").val();
	var bno="${dto.bno}";
	var param={"replytext" : replytext, "bno":bno};
	
	$.ajax({
		type:"post",
		url:"${path}/reply/insert.do",
		data:param,
		success:function(){
			alert("댓글이 등록되었습니다.");
			listReply(1);
		}
	});
}

function listReply(num){
	$.ajax({
		type:"post",
		url:"${path}/reply/list.do?bno=${dto.bno}&curPage="+num,
		success: function(result){
			console.log(result);
			$("#listReply").html(result);
		}
	});
	
}
</script>



<style>
.fileDrop{
	width:600px;
	height:100px;
	border:1px dotted gray;
	background-color:gray;
}
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<form id="form1" name="form1" method="post">
<div>작성일자: <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>

<div>조회수: ${dto.viewcnt }</div>
<div>이름: ${dto.name }</div>
<div>제목: <input name="title" value="${dto.title}"/></div>
<div style="width:80%;">
내용: <textarea rows="3" cols="80" name="content" id="content">${dto.content}</textarea>
</div>

<script>
CKEDITOR.replace("content",{
	filebrowserUploadUrl:"${path}/imageUpload.do",
	height:"150px"
});
</script>
<div id="uploadedList"></div>
<div class="fileDrop"></div>

<div>
	<input type="hidden" name="bno" value="${dto.bno}"/>
	<c:if test="${sessionScope.userid==dto.writer}">
		<button type="button" id="btnUpdate">수정</button>
		<button type="button" id="btnDelete">삭제</button>
	</c:if>
		<button type="button" id="btnList">목록</button>
</div>
</form>

<!-- 댓글쓰기 -->
<div style="width:700px; text-align:center">
<c:if test="${sessionScope.userid != null}">
	<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성하세요"></textarea>
	<br>
	<button type="button" id="btnReply">댓글쓰기</button>
</c:if>
</div>
<div id="listReply"></div>

</body>
</html>