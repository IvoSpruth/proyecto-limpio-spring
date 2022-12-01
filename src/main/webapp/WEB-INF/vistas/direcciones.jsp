<%@ include file="generales/importsGenerales.jsp" %>


<div class="w3-container">
    <h3>Direcciones de ${cliente.nombre}</h3>

    <form:form action="${pagePath}/clientes/direcciones/addDireccion?id=${cliente.id}" method="POST" acceptCharset="UTF-8"
               modelAttribute="direccion">
        <ul class="w3-ul w3-card-4 w3-white">
            <li class="w3-padding-16">
                <label for="nombreCalle">Nombre calle: </label>
                <form:input path="nombreCalle" type="text" id="nombreCalle" />
            </li>
            <li class="w3-padding-16">
                <label for="alturaCalle">Altura: </label>
                <form:input path="alturaCalle" type="number" id="alturaCalle"/>
            </li>
            <li class="w3-padding-16">
                <label for="descripcion">Descripcion: </label>
                <form:input path="descripcion" type="text" id="descripcion"/>
            </li>
            <li class="w3-padding-16">
                <label for="codigoPostal">Codigo postal: </label>
                <form:input path="codigoPostal" type="number" id="codigoPostal"/>
            </li>
        </ul>
        <button id="btn-registrarme" class="w3-button w3-block w3-teal" Type="Submit">
            Agregar
        </button>
    </form:form>

    <div class="w3-responsive">
        <table class="w3-table-all w3-hoverable">
            <tr class="rowLine">
                <td>ID</td>
                <td>Nombre Calle</td>
                <td>altura</td>
                <td>Descripcion</td>
                <td>Codigo Postal</td>
            </tr>

            <c:forEach items="${direcciones}" var="direccion">
                <tr class="rowLine">
                    <td>${direccion.id}</td>
                    <td>${direccion.nombreCalle}</td>
                    <td>${direccion.alturaCalle}</td>
                    <td>${direccion.descripcion}</td>
                    <td>${direccion.codigoPostal}</td>
                </tr>
            </c:forEach>

        </table>

    </div>
</div>
<%@ include file="generales/footer.jsp" %>
