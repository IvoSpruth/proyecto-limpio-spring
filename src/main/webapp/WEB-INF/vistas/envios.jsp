<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <th>Número de envío</th>
            <th>Nombre de cliente</th>
            <th>Dirección de envío</th>
            <th>Costo de envío</th>
            <th>Fecha de salida</th>
            <th>Fecha de entrega</th>
            <th>Estado actual de envío</th>
            <th colspan="3">Acciones</th>
        </tr>
        <c:forEach var="envio" items="${envios}">
        <tr>
            <td>${envio.id}</td>
            <td>${envio.cliente.nombre}</td>
            <td>${envio.direccionEnvio.nombreCompleto}</td>
            <td><f:formatNumber type="number" value="${envio.costo}" maxFractionDigits="2"/></td>
            <td>${envio.fechaSalida.toString()}</td>
            <td>${envio.fechaLlegada.toString()}</td>
            <td>${envio.estadoEnvio.toString()}</td>
            <td><a href="${pageContext.servletContext.contextPath}/envios/siguienteEtapa?idEnvio=${envio.id}">Siguiente etapa</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/envios/anteriorEtapa?idEnvio=${envio.id}">Anterior etapa</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/envios/devolver?idEnvio=${envio.id}">Devolver</a></td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
