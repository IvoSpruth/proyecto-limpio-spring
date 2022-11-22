<%@ include file="generales/importsGenerales.jsp" %>
    <div class="w3-container">
        <div id="loginbox">
            <h3 class="form-signin-heading">Resumen de venta</h3>
        </div>

        <div class="w3-container">
            <h5>Id del empleado: ${idEmpleado}</h5>
            <h5>Comision: ${comision}</h5>
            <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
                <tr>
                    <th scope="col">Nombre del producto</th>
                    <th scope="col">Precio unitario</th>
                    <th scope="col">Id producto</th>
                    <th scope="col">Cantidad</th>
                    <th scope="col">Total del producto</th>
                    <th scope="col">Suma total</th>
                </tr>
                <tr>
                    <th scope="row">${nombreProductoUno}</th>
                    <td>${precioUnitarioUno}</td>
                    <td>${idProductoUno}</td>
                    <td>${cantidadUno}</td>
                    <td>${totalProductoUno}</td>
                    <td>${sumaTotal}</td>
                </tr>
                <tr>
                    <th scope="row">${nombreProductoDos}</th>
                    <td>${precioUnitarioDos}</td>
                    <td>${idProductoDos}</td>
                    <td>${cantidadDos}</td>
                    <td>${totalProductoDos}</td>
                    <td>${sumaTotal}</td>
                </tr>
                <tr>
                    <th scope="row">TOTAL</th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>${sumaTotal}</td>
                </tr>
            </table>
            <br>
            <button class="w3-button w3-dark-grey">
                <a href="${pageContext.servletContext.contextPath}/centroControl" class="link">
                    <i class="fa fa-arrow-left"></i>Volver al inicio</a></button>
            <button class="w3-button w3-dark-grey">
                <a href="#" class="link">
                    <i class="fa fa-arrow-down"></i>Descargar factura</a></button>
            <button class="w3-button w3-dark-grey">
                <a href="${linkDePago}" target="_blank" class="link">
                    <i class="fa fa-arrow-right"></i>Ir a link de pago</a></button>
        </div>
        <hr>
    </div>
    <hr>
<%@ include file="generales/footer.jsp" %>