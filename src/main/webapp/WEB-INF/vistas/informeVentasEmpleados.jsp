<%--
  Created by IntelliJ IDEA.
  User: Alejandro
  Date: 7/11/22
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@ include file="generales/head.jsp" %>
</head>
<body class="w3-light-grey">
<%@ include file="generales/header.jsp" %>


  <div id="chart" style="width:100%; height:400px;"></div>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script>
  document.addEventListener('DOMContentLoaded', function () {

    const chart = Highcharts.chart('chart', {
      chart: {
        type: 'column'
      },
      title: {
        text: '${titulo}'
      },
      xAxis: {
        categories: ["Ventas"]
      },
      yAxis: {
        title: {
          text: 'Dinero total'
        }
      },
      series: ${datos}
    });
  });
</script>

<%@ include file="generales/footer.jsp" %>
</body>
</html>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>