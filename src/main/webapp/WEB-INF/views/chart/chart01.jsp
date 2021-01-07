<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Context-Type" charset="text/html; charset=UTF-8">
<title>구글차트</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
<!-- 구글차트 호출을 위한 js 파일 -->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script>
   //구글 차트 라이브러리 로딩
   google.load('visualization','1',{
      'packages' : ['corechart']
   });
   
   //로딩이 완료되면 drawChart 함수 호출
   google.setOnLoadCallback(drawChart); 
   function drawChart(){
      var jsonData = $.ajax({  // 차트그리에 필요한 json데이터 로딩, 비동기적 방식으로 호출
         url:"${path}/json/sampleData2.json",
         dataType:"json",
         //json에 sampleData.json파일을 불러온다
         //확장자가 json이면 url맵핑을 해줘야함, 안하면 자바파일 인줄 알고 404에러 발생
         //그렇기 때문에 servlet-context파일에 리소스를 맵핑해준다
         async:false
      }).responseText; //json파일을 text파일로 읽어들인다는 뜻
      console.log(jsonData); //json형식으로 데이터가 담김
      
      var data = new google.visualization.DataTable(jsonData); //json형식으로 데이터 테이블 생성      
      //차트를 출력할 div -LineChart, ColumnChart,PieChart
//              차트종류

//       var chart = new google.visualization.PieChart(
//          document.getElementById('chart_div')); // 원형 그래프
         
         
//          var chart = new google.visualization.LineChart(
//             document.getElementById('chart_div')); // 선 그래프
            
            
         var chart = new google.visualization.ColumnChart(
            document.getElementById('chart_div')); // 막대 그래프
            
            
      //차트객체.draw(데이터테이블, 옵션)
      //curveType:"function"  -> 곡선처리
   chart.draw(data,{
      title:"차트예제",
      //curveType:"function",
      width:600,
      height:440
   });
   }
</script>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<!-- 차트 출력 영역 -->
<div id="chart_div"></div>

<!-- 차트 새로고침 버튼 -->
<button id="btn" type="button" onclick="drawChart()">refresh</button>

</body>
</html>