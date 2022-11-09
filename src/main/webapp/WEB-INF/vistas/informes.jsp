<%--
  Created by IntelliJ IDEA.
  User: Alejandro
  Date: 7/11/22
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
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
<form:form action="informes/ventasEmpleados" modelAttribute="fechas" method="get">
    <form:input type="date" path="fechaInicial" />
    <form:input type="date" path="fechaFinal" />
    <form:button type="submit">Mostrar grafico</form:button>
</form:form>


<%@ include file="generales/footer.jsp" %>
</body>
</html>



