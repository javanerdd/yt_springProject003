<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Context-Type" charset="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>

<!-- 구글차트 호출을 위한 js파일 -->
<script type="text/javascript" src="https:www.google.com/jsapi"></script>

<script>
//구글차트 라이브러리 로딩
//google 객체는 위쪽 google src안에 들었음
   google.load('visualization','1',{
      'packages':['corechart']
});

//로딩이 완료되면 drawChart 함수를 호출
   google.setOnLoadCallback(drawChart); //라이브러리를 불러오는 작업이 완료되었으면 drawChart작업을 실행하라는 뜻
function drawChart(){
   var jsonData =$.ajax({
      url:"${path}/chart/cart_money_list.do",
      //chart01에서는 json의 주소를 직접 적었지만 이 페이지에서는 컨트롤러로 이동해
      //맵핑해서 json을 동적으로 직접만들어 그 만든 json을 직접 보낸다
      
      //chart01에서 쓰던 방식 url: "${path}/json/sampleData.json",
      //json에 sampleData.json 파일을 불러온다.
      //확장자가 json이면 url 맵핑을 꼭 해주어야 한다. 안해주면 자바파일인줄 알고 404에러 발생
      //그렇기 때문에 servlet-context파일에서 리소스를 맵핑해준다.
      dataType:"json",
      async:false
   }).responseText;  //json파일을 text파일로 읽어들인다는 뜻
   console.log(jsonData); //데이터테이블 생성
   
   var data = new google.visualization.DataTable(jsonData);
   //json 형식을 구글 테이블 형식으로 바꿔주기 위해서 집어넣음
   //차트를 출력할 div
   //LineChart, ColumnChart,PieChart 에 따라서 차트의 형식이 바뀐다.
   
   var chart = new google.visualization.PieChart(
        document.getElementById('chart_div')); //원형그래프
   
//    var chart =new google.visualization.LineChart(
//          document.getElementById('chart_div')); //선 그래프
         
//    var chart = new google.visualization.ColumChart(
//          document.getElementById('chart_div')); //막대 그래프

         chart.draw(data,{
            title:"장바구니 통계",
            //curveType:"function", 
            width:600,
            height:400
         });
}
</script>

</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>

<div id="chart_div"></div>

<button id="btn" type="button" onclick="drawChart()">refresh</button>

</body>
</html>