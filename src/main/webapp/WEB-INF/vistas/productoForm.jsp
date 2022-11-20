<%@ include file="generales/importsGenerales.jsp" %>

<div class="botones_separados">
    <h3>Nuevo Producto</h3>
    <button class="btn btn-info" type="button" data-toggle="modal" data-target="#form_exportar">Importar</button>
</div>

<form:form action="addProducto" method="POST" modelAttribute="producto" acceptCharset="UTF-8" >
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
            <label for="idProveedor">Id Proveedor: </label>
            <form:input path="idProveedor" type="number" id="idProveedor"/>
        </li>
        <li class="w3-padding-16">
            <label for="cantidad">Cantidad: </label>
            <form:input path="cantidad" type="number" id="cantidad"/>
        </li>
    </ul>
    <button id="btn-registrarme" class="w3-button w3-block w3-teal" Type="Submit">
        Agregar
    </button>
</form:form>

<div class="botones_separados">
    <h3>Stock actual</h3>
    <a href="exportarProductos" class="btn btn-info">Exportar</a>
</div>

<div class="w3-card-4 w3-responsive">
    <table class="w3-table-all w3-hoverable">
        <tr class="rowLine">
            <td>ID</td>
            <td>Nombre</td>
            <td>Cantidad</td>
            <td>costo</td>
            <td>ID Proveedor</td>
        </tr>

        <c:forEach items="${productos}" var="producto">
            <tr class="valueRows">
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.cantidad}</td>
                <td>${producto.costo}</td>
                <td>${producto.idProveedor}</td>
            </tr>
        </c:forEach>

    </table>

</div>

<div class="modal " id="form_exportar" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Importar CSV</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="importarProductos" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <input type="file" alt="Seleccionar CSV" name="file" id="file">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>


<%@ include file="generales/footer.jsp" %>
