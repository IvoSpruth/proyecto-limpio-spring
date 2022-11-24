<%@ include file="generales/importsGenerales.jsp" %>

    <div class="w3-container">
        <form:form action="empleado-dueño-control" method="POST" modelAttribute="venta" acceptCharset="UTF-8">
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
                        <th scope="col">Descuento</th>
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
                        <th scope="row">TOTAL</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${sumaTotal}</td>
                    </tr>
                </table><br>
                <button class="w3-button w3-dark-grey">
                    <a href="${pagePath}/centroControl">Volver al inicio</a>
                    <i class="fa fa-arrow-left"></i></button>
                <button class="w3-button w3-dark-grey">
                    <a href="#">Descargar factura</a>
                    <i class="fa fa-arrow-down"></i></button>
                <button class="w3-button w3-dark-grey">
                    <a href="${linkDePago}" target="_blank" class="link">
                        <i class="fa fa-arrow-right"></i>Ir a link de pago</a></button>
                <button class="w3-button w3-dark-grey">
                    <a href="${pagePath}/envios/form/datosCliente?idVenta=${idVenta}">Enviar producto</a>
                </button>
            </div>
            <hr>
        </form:form>
    </div>
    <hr>
<%@ include file="generales/footer.jsp" %>
