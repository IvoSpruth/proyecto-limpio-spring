<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
  <h2>Datos del cliente</h2>
  <p><span class="w3-large">Nombre: </span>${cliente.nombre}</p>
  <p><span class="w3-large">Email: </span>${cliente.mail}</p>
  <div class="w3-row">
    <h3>Direcciones</h3>
    <div class="w3-half w3-panel w3-pale-green w3-leftbar w3-border-green">
      <ul class="w3-ul">
        <c:forEach var="direccion" items="${cliente.direcciones}">
          <li>
            <div>
              <p><span class="w3-large">Calle: </span>${direccion.nombreCalle}</p>
              <p><span class="w3-large">Altura: </span>${direccion.alturaCalle}</p>
              <p><span class="w3-large">Cod. postal: </span>${direccion.codigoPostal}</p>
              <p><span class="w3-large">Descripcion: </span>${direccion.descripcion}</p>
            </div>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>

  <h3>Seleccioná la dirección del envío</h3>
  <div class="w3-row">
    <form:form action="${pagePath}/envios/procesarEnvio" modelAttribute="form" method="post" class="w3-panel w3-pale-green w3-leftbar w3-border-green w3-padding-32">
      <form:hidden path="idVenta"/>
      <form:hidden path="idCliente"/>
      <form:select path="idDireccion" class="w3-select">
        <form:options items="${direcciones}" itemLabel="nombreCompleto" itemValue="id"/>
      </form:select>
      <form:button type="submit" class="w3-button w3-padding w3-green w3-margin-top">Enviar</form:button>
    </form:form>
  </div>
</div>

<%@ include file="generales/footer.jsp" %>