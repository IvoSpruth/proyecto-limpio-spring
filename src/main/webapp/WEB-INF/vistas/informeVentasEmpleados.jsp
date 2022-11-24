<%@ include file="generales/importsGenerales.jsp" %>


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