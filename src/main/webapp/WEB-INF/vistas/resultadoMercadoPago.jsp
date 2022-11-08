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


<c:choose>
    <c:when test="${preferencia.estado == 'approved'}">
        <h1>Se ha aprobado el pago</h1>
        <h2><a href="goCierreDiario">Ver en el cierre diario</a></h2>
    </c:when>
    <c:when test="${preferencia.estado == 'rejected'}">
        <h1>El pago ha sido rechazado, por favor <a href="${preferencia.linkDePago}">intentarlo de nuevo</a></h1>
    </c:when>
    <c:when test="${preferencia.estado == 'in_process'}">
        <h1>El pago aun esta pendiente, por favor chequear nuevamente mas tarde</h1>
    </c:when>
    <c:otherwise>
        <h1>El estado del pago es: ${preferencia.estado}</h1>
        <h2>Comuníquese con sistemas para obtener más información</h2>
    </c:otherwise>
</c:choose>

</div>
<%@ include file="generales/footer.jsp" %>
</body>
</html>
