<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@ include file="generales/head.jsp" %>
</head>
<body class="w3-light-grey">
<%@ include file="generales/header.jsp" %>


<h2>Ventas por empleado</h2>
<main>
    <form:form action="informes/ventasEmpleados" modelAttribute="fechas" method="post">
        <form:input type="date" path="fechaInicial" />
        <form:input type="date" path="fechaFinal" />
        <form:button type="submit">Mostrar grafico</form:button>
    </form:form>

    <form action="informes/ventasEmpleados" method="get">

    </form>

    <form action="informes/ventasHora" method="get">
        <input type="text" name="params">
        <input type="text" name="params">
        <input type="text" name="params">
        <button type="submit">Mostrar grafico</button>
    </form>
</main>
<%@ include file="generales/footer.jsp" %>
</body>
</html>



