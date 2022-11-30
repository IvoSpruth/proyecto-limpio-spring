<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
  <h1>El envío se ha programado correctamente</h1>
  <h2>Detalles del envio</h2>

  <div class="w3-row">
    <h3>Datos de cliente</h3>
    <div class="w3-half w3-panel w3-pale-green w3-leftbar w3-border-green">
      <div class="w3-container">
        <p><span class="w3-large">Email: </span>${envio.cliente.mail}</p>
        <p><span class="w3-large">Nombre: </span>${envio.cliente.nombre}</p>
      </div>
    </div>
  </div>

  <div class="w3-row">
    <h3>Datos de venta</h3>
    <div class="w3-half w3-panel w3-pale-green w3-leftbar w3-border-green">
      <div class="w3-container">
        <p><span class="w3-large">Total: </span><f:formatNumber type="number" value="${envio.venta.total - envio.costo}"
                                                                maxFractionDigits="2"/><span class="w3-large"> $ </span></p>
        <p><span class="w3-large">Fecha de la venta: </span>${envio.venta.fecha.toString()}</p>
        <p><span class="w3-large">Hora de la venta: </span>${envio.venta.hora.toString()}</p>
      </div>
    </div>
  </div>

  <div class="w3-row">
    <h3>Datos de envio</h3>
    <div class="w3-half w3-panel w3-pale-green w3-leftbar w3-border-green">
      <p><span class="w3-large">Costo del envío: </span>${envio.costo}</p>
      <p><span class="w3-large">Direccion de envío: </span>${envio.direccionEnvio.nombreCompleto}</p>
      <p><span class="w3-large">Estado actual del envío:</span> ${envio.estadoEnvio.toString()}</p>
      <p><span class="w3-large">Fecha de salida:</span> ${envio.getFechaSalida('dd-MM-YY')}</p>
      <p><span class="w3-large">Fecha de llegada aprox.: </span>${envio.getFechaLlegada('dd-MM-YY')}</p>
    </div>
  </div>
</div>

<div class="w3-container">
  <a href="${pagePath}/goCierreDiario" class="w3-button w3-green w3-margin-right">Mostrar ventas</a>
  <a href="${pagePath}/envios/mostrar" class="w3-button w3-green">Mostrar envíos</a>
</div>

<%@ include file="generales/footer.jsp" %>