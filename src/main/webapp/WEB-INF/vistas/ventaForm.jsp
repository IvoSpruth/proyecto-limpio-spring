<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
    <form:form action="addVenta" method="POST" modelAttribute="venta" acceptCharset="UTF-8">
        <div id="loginbox">
            <h3 class="form-signin-heading">Nueva Venta</h3>
        </div>

        <div id="loginbox">
            <h3 class="form-signin-heading">${message}</h3>
        </div>
        <ul class="w3-ul w3-card-4 w3-white">
            <li class="w3-padding-16">
                <label for="idEmpleado"><h5>Id empleado: </h5></label>
                <form:input path="idEmpleado" type="number" id="idEmpleado"
                            placeholder="Id empleado"/>
            </li>
            <li class="w3-padding-16">
                <form:select path="idProducto">
                    <form:option value="0" label="Seleccione Productos"/>
                    <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
                </form:select>
                <form:input path="cantidadProducto" type="number" id="cantidadProducto"/>
            </li>
            <li class="w3-padding-16">
                <form:select path="idProducto2">
                    <form:option value="0" label="Seleccione Productos"/>
                    <form:options items="${productos}" itemLabel="nombre" itemValue="id"/>
                </form:select>
                <form:input path="cantidadProducto2" type="number" id="cantidadProducto2"
                />
            </li>
        </ul>
        <button id="btn-registrarme" class="w3-button w3-block w3-red" type="Submit">
            Agregar
        </button>
    </form:form>
</div>
<hr>
<%@ include file="generales/footer.jsp" %>
