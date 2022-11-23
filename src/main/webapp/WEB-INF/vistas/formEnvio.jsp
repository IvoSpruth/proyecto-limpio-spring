<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Datos cliente</h3>
<div>
    <p>Id Cliente: ${cliente.id}</p>
    <p>Nombre: ${cliente.nombre}</p>
    <ul>
        <c:forEach var="direccion" items="${cliente.direcciones}">
            <li>Calle: ${direccion.nombreCalle} / Altura: ${direccion.alturaCalle} / CP: ${direccion.codigoPostal} / Descripcion: ${direccion.descripcion}</li>
        </c:forEach>
    </ul>
    <p>${cliente.mail}</p>
</div>
<form:form action="${pageContext.servletContext.contextPath}/envios/procesarEnvio" modelAttribute="form" method="post">
    <form:hidden path="idVenta"/>
    <form:hidden path="idCliente"/>
    <form:select path="idDireccion">
        <form:options items="${direcciones}" itemLabel="nombreCompleto" itemValue="id"/>
    </form:select>
    <form:button type="submit">Enviar</form:button>
</form:form>
</body>
</html>
