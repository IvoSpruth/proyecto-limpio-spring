<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Informes de ventas por empleado</title>
</head>
<body>
  <div id="chart" style="width:100%; height:400px;"></div>
</body>
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
</html>