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

function list(page){
	location.href="${path}/board/list.do?curPage="+page
			+"&search_option=${map.search_option}"
			+"&keyword=${map.keyword}";
}

</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>

<h2>게시판</h2>
<form name="form1" method="post" action="${path}/board/list.do">
	<select name="search_option">
		<option value="all">이름+내용+제목</option>
		<option value="writer">이름</option>
		<option value="content">내용</option>
		<option value="title">제목</option>
	</select>
<input name="keyword" value="${map.keyword}"/>
<input type="submit" value="조회"/>

</form>


<button type="button" id="btnWrite">글쓰기</button>
총 게시물수 : ${map.count } <br>
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
				<td><a href="${path}/board/view.do?bno=${row.bno}&curPage=${map.pager.curPage}&search_option=${map.search_option}&keyword=${map.keyword}">${row.title}</a>
<!-- 				댓글갯수 -->
				<c:if test="${row.cnt>0}">
					<span style="color:red;">(${row.cnt})</span>
				</c:if>
				</td>
				<td>${row.name}</td>
				<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${row.viewcnt}</td>
			</tr>
		</c:forEach>
		
<!-- 페이지 네비게이션		 -->
		<tr>
			<td colspan="5" align="center">
				<c:if test="${map.pager.curBlock>1}">
				<a href="javascript:list('1')">[처음]</a>
				</c:if>
				
				<c:if test="${map.pager.curBlock>1}">
				<a href="javascript:list('${map.pager.prevPage}')">[이전]</a>
				</c:if>
			
				<c:forEach var="num" begin="${map.pager.blockBegin}" end="${map.pager.blockEnd}">
					<c:choose>
						<c:when test="${num==map.pager.curPage}">
							<span style="color:red;">${num}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="javascript:list('${num}')">${num}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${map.pager.curBlock <= map.pager.totBlock}">
					<a href="javascript:list('${map.pager.nextPage }')">[다음]</a>
				</c:if>
				
				<c:if test="${map.pager.curBlock <= map.pager.totBlock}">
					<a href="javascript:list('${map.pager.totPage}')">[끝]</a>
				</c:if>	
			</td>
		</tr>
	
	</table>


</body>
</html>