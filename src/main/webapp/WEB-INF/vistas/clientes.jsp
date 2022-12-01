<%@ include file="generales/importsGenerales.jsp" %>


<div class="w3-container">
    <h3>Clientes</h3>

    <div class="botones_separados">
        <h3>Nuevo cliente</h3>
        <form action="${pagePath}/clientes/importarClientes" method="post" enctype="multipart/form-data" class="form-inline">
            <button type="submit" class="w3-bar-item w3-button w3-teal w3-margin w3-right">Enviar</button>
            <label for="file" class="w3-bar-item w3-button w3-light-blue w3-margin w3-right">Seleccionar CSV</label>
            <input type="file" alt="Seleccionar CSV" name="file" id="file" style="visibility:hidden;">
        </form>
    </div>

    <form:form action="clientes/addCliente" method="POST" acceptCharset="UTF-8" modelAttribute="cliente">
        <ul class="w3-ul w3-card-4 w3-white">
            <li class="w3-padding-16">
                <label for="nombre">Nombre: </label>
                <form:input path="nombre" type="text" id="nombre" placeholder="Nombre"/>
            </li>
            <li class="w3-padding-16">
                <label for="mail">Mail: </label>
                <form:input path="mail" type="mail" id="mail"/>
            </li>
            <li class="w3-padding-16">
                <label for="notifEnable">Habilitar notificacion: </label>
                <form:checkbox path="notifEnable" id="notifEnable" value="true"/>
            </li>
        </ul>
        <button id="btn-registrarme" class="w3-button w3-block w3-teal" Type="Submit">
            Agregar
        </button>
    </form:form>

    <div class="botones_separados">
        <h3>Clientes activos</h3>
        <a href="${pagePath}/clientes/exportarClientes" class="w3-bar-item w3-button w3-teal w3-margin w3-right">Exportar</a>
    </div>

    <div class="w3-responsive">
        <table class="w3-table-all w3-hoverable">
            <tr class="rowLine">
                <td>ID</td>
                <td>Nombre</td>
                <td>Mail</td>
                <td>Notificacion</td>
            </tr>

            <c:forEach items="${clientes}" var="cliente">
                <tr class="rowLine">
                    <td>${cliente.id}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.mail}</td>
                    <c:choose>
                        <c:when test="${cliente.notifEnable}">

                            <td><a href="${pagePath}/clientes/desactivarNotif?id=${cliente.id}"><i class="fa fa-check"></i></a></td>

                        </c:when>
                        <c:when test="${!cliente.notifEnable}">

                            <td><a href="${pagePath}/clientes/activarNotif?id=${cliente.id}"><i class="fa fa-remove"></i></a></td>

                        </c:when>
                        <c:otherwise>
                            <td>ERROR</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>

        </table>

    </div>
</div>
<%@ include file="generales/footer.jsp" %>
