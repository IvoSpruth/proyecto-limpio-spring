<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
    <div class="botones_separados">
        <h3>Nuevo Producto</h3>
        <form action="importarProductos" method="post" enctype="multipart/form-data" class="form-inline">
            <button type="submit" class="w3-bar-item w3-button w3-teal w3-margin w3-right">Enviar</button>
            <label for="file" class="w3-bar-item w3-button w3-light-blue w3-margin w3-right">Seleccionar CSV</label>
            <input type="file" alt="Seleccionar CSV" name="file" id="file" style="visibility:hidden;">
        </form>
    </div>

    <form:form action="addProducto" method="POST" modelAttribute="producto" acceptCharset="UTF-8">
        <ul class="w3-ul w3-card-4 w3-white">
            <li class="w3-padding-16">
                <label for="nombre">Nombre: </label>
                <form:input path="nombre" type="text" id="nombre" placeholder="Nombre"/>
            </li>
            <li class="w3-padding-16">
                <label for="costo">Costo: </label>
                <form:input path="costo" type="text" id="costo"/>
            </li>
            <li class="w3-padding-16">
                <label for="cantidad">Cantidad: </label>
                <form:input path="cantidad" type="number" id="cantidad"/>
            </li>
            <li class="w3-padding-16">
                <label for="cantidad">Stock maximo: </label>
                <form:input path="stockMaximo" type="number" id="stockMaximo"/>
            </li>
        </ul>
        <button id="btn-registrarme" class="w3-button w3-block w3-teal" Type="Submit">
            Agregar
        </button>
    </form:form>

    <div class="botones_separados">
        <h3>Stock actual</h3>
        <a href="exportarProductos" class="w3-bar-item w3-button w3-teal w3-margin w3-right">Exportar</a>
    </div>

    <div class="w3-responsive">
        <table class="w3-table-all w3-hoverable">
            <tr class="rowLine">
                <td>ID</td>
                <td>Nombre</td>
                <td>Cantidad</td>
                <td>costo</td>
                <td>Stock Maximo</td>
            </tr>

            <c:forEach items="${productos}" var="producto">
                <tr class="valueRows">
                    <td>${producto.id}</td>
                    <td>${producto.nombre}</td>
                    <td>${producto.cantidad}</td>
                    <td>${producto.costo}</td>
                    <td>${producto.stockMaximo}</td>
                </tr>
            </c:forEach>

        </table>

    </div>


</div>
<%@ include file="generales/footer.jsp" %>
