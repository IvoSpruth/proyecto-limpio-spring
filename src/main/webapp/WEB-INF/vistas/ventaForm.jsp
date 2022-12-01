<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="generales/importsGenerales.jsp" %>

<div id="contenedorVenta" class="w3-container">
  <form:form action="addVenta" method="POST" modelAttribute="datosVenta" acceptCharset="UTF-8">
    <div id="loginbox">
      <h3>Nueva Venta</h3>
    </div>
    <ul id="contenedorProductos" class="w3-ul w3-card-4 w3-white" style="margin: 2em auto;width: 50%;">
      <li id="empleado" class="w3-padding-16">
        <label for="idEmpleado"><h5>Id empleado: </h5></label>
        <form:select path="idEmpleado" class="form-control mb-25" placeholder="Id empleado">
          <c:forEach items="${empleados}" var="empleado">
            <form:option value="${empleado.id}" label="${empleado.name}"/>
          </c:forEach>
        </form:select>
      </li>
      <li id="1" class="w3-padding-16" style="display:none">
        <form:select path="idP1" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP1" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="2" class="w3-padding-16" style="display:none">
        <form:select path="idP2" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP2" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="3" class="w3-padding-16" style="display:none">
        <form:select path="idP3" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP3" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="4" class="w3-padding-16" style="display:none">
        <form:select path="idP4" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP4" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="5" class="w3-padding-16" style="display:none">
        <form:select path="idP5" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP5" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="6" class="w3-padding-16" style="display:none">
        <form:select path="idP6" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP6" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="7" class="w3-padding-16" style="display:none">
        <form:select path="idP7" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP7" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="8" class="w3-padding-16" style="display:none">
        <form:select path="idP8" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP8" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="9" class="w3-padding-16" style="display:none">
        <form:select path="idP9" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP9" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li id="10" class="w3-padding-16" style="display:none">
        <form:select path="idP10" class="w3-select">
          <form:option value="0" label="Seleccione Productos"/>
          <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:input path="cantidadP10" type="number" id="cantidadProducto" class="w3-input"/>
      </li>
      <li class="w3-padding-16">
        <div id="boton-add" class="w3-button w3-block w3-green" onclick="addProducto()"><h5>Añadir producto</h5></div>
      </li>

    </ul>
    <button class="w3-button w3-block w3-red" type="Submit" style="margin: 2em auto;width: 50%;"><h5>Realizar venta</h5>
    </button>
  </form:form>
</div>

<c:if test="${exito==false}">
  <div class="w3-panel w3-red">
    <h4>Alerta!</h4>
    <p>${mensaje}</p>
  </div>
</c:if>

<script>
    var contador = 1;

    function addProducto() {
        if (contador < 11) {
            console.log(contador);
            let producto = document.getElementById(contador);
            producto.style.display = "";
            contador++;
        }
    }

    // window.onload = (event) => {
    // console.log("page is fully loaded");
    //  let content = document.getElementById('contenedorProductos');
    //     let firstChild = content.firstChild.nodeName;
    //     let lis = content.getElementsByTagName('li');
    //     for(li in lis){
    //         console.log(li.id);
    //         //li.style.display = 'none';
    //     }
    //     console.log(firstChild);
    // };


</script>

<%@ include file="generales/footer.jsp" %>
