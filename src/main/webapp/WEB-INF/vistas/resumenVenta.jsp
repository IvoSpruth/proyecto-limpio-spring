<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
  <h3>Resumen de venta</h3>
  <div class="w3-container">
    <h5>ID del empleado: ${idEmpleado}</h5>
    <h5>Comision: ${comision}</h5>
    <table class="w3-table w3-striped">
      <tr class="w3-teal">
        <th>Nombre del producto</th>
        <th>Precio unitario</th>
        <th>Id producto</th>
        <th>Cantidad</th>
        <th>Total del producto</th>
        <th>Descuento</th>
      </tr>

      <c:forEach items="${productos}" var="p">
        <tr>
          <td>${p.nombre}</td>
          <td>${p.precio}</td>
          <td>${p.id}</td>
          <td>${p.cantidad}</td>
          <td>${p.totalProducto}</td>
          <td>${p.descuento}</td>
        </tr>
      </c:forEach>

      <tr>
        <td colspan="6" class="w3-right-align"><span class="w3-large">Total: </span>${sumaTotal} $</td>
      </tr>
    </table>
    <br>
    <button class="w3-button w3-teal">
      <a href="${pagePath}/centroControl">Volver al inicio</a>
      <i class="fa fa-arrow-left"></i></button>
    <button class="w3-button w3-teal">
      <a href="${pagePath}/getFactura?pathUrl=${urlFactura}">Descargar factura</a>
      <i class="fa fa-arrow-down"></i></button>
    <button class="w3-button w3-teal">
      <a href="${linkDePago}" target="_blank">
        <i class="fa fa-arrow-right"></i>Ir a link de pago</a></button>
    <button class="w3-button w3-teal">
      <a href="${pagePath}/envios/enviar/datosCliente?idVenta=${idVenta}">Enviar productos</a>
    </button>
  </div>

</div>

<%@ include file="generales/footer.jsp" %>
