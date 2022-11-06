<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Informes</title>
</head>
<body>
    <h2>Ventas por empleado</h2>
    <form:form action="informes/ventasEmpleados" modelAttribute="fechas" method="get">
        <form:input type="date" path="fechaInicial" />
        <form:input type="date" path="fechaFinal" />
        <form:button type="submit">Mostrar grafico</form:button>
    </form:form>

</body>
</html>