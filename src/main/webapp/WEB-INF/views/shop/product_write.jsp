<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- <!-- include summernote css/js --> -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<%-- <link href="${path}/summernote/summernote.css" rel="stylesheet"> --%>
<%-- <script src="${path}/summernote/summernote.js"></script> --%>

<%-- <script src="${path}/ckeditor/ckeditor.js"></script> --%>
<!-- ddddddddd -->

 <c:if test = "${sessionScope.admin_userid == null}" > 
      <script>
           alert("로그인 하신 후 사용하세요.");                  // 다음 메시지를 출력함..
           location.href = "${path}/admin/login.do";          // 로그인 페이지로 이동시켜주는 코드
      </script>
  </c:if>
  
<script>

$(function(){
	$("#description").summernote({
		height:300,
		width:800
	});
});


function product_write(){
	var product_name=document.form1.product_name.value;
	var price=document.form1.price.value;
	var description=document.form1.description.value;
	
	if(product_name==""){
		alert("상품명을 입력하세요");
		document.form1.product_name.focus();
		return;
	}
	if(price==""){
		alert("상품가격을 입력하세요");
		document.form1.price.focus();
		return;
	}
// 	if(description==""){
// 		alert("상품설명을 입력하세요");
// 		document.form1.description.focus();
// 		return;
// 	}
	document.form1.action="${path}/shop/product/insert.do";
	document.form1.submit();
}

// function inNumber(){
// 	alert("숫자를 입력하세요");
//     if(event.keyCode<48 || event.keyCode>57){
//        event.returnValue=false;
//     }


</script>
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
			<td><input type="text" name="price" /></td>
		</tr>
		
		<tr>
			<td>상품설명</td>
			<td><textarea rows="5" cols="60" name="description" id="description"></textarea></td>
		</tr>
		
		<script>
		//id가 description인 태그에 ckeditor 적용
// 		CKEDITOR.replace("description");
		
// 		이미지 업로드를 할 경우
// 		CKEDITOR.replace("description",{
// 			filebrowserUploadUrl:"${path}/imageUpload.do"
// 			});
		
		</script>
		
		
		<tr>
			<td>상품이미지</td>
			<td><input type="file" name="file1"/></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="등록" onclick="product_write()"/>
				<input type="button" value="목록" onclick="location.href='${path}/shop/product/list.do'"/>
			
			</td>
		</tr>
		
	</table>
</form>


</body>
</html>