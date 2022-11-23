<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h3>Detalles del envio</h3>
        <h5>Datos de cliente</h5>
        <div>
            <p>Id cliente: ${envio.cliente.id}</p>
            <p>Email: ${envio.cliente.mail}</p>
            <p>Nombre: ${envio.cliente.nombre}</p>
            <p>Fecha alta: ${envio.cliente.fechaIngreso}</p>
        </div>

        <h5>Datos de venta</h5>
        <div>
            <p>Id venta: ${envio.venta.id}</p>
            <p>Total: <f:formatNumber type="number" value="${envio.venta.total}" maxFractionDigits="2"/></p>
            <p>Fecha: ${envio.venta.fecha.toString()}</p>
            <p>Hora: ${envio.venta.hora.toString()}</p>
        </div>

        <h5>Datos de envio</h5>
        <div>
            <p>Id envio: ${envio.id}</p>
            <p>Direccion de envio: ${envio.direccionEnvio.nombreCompleto}</p>
            <p>Estado actual del envio: ${envio.estadoEnvio.name()}</p>
            <p>Fecha de salida: ${envio.fechaSalida.toString()}</p>
            <p>Fecha de llegada: ${envio.fechaLlegada.toString()}</p>
        </div>

        <a href="${pageContext.request.contextPath}/goVentaForm">Añadir venta</a>
        <a href="${pageContext.request.contextPath}/envios/mostrar">Mostrar envios</a>
    </body>
</html>
