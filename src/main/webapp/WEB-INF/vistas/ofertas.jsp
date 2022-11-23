<%@ include file="generales/importsGenerales.jsp" %>


<div class="w3-container">
    <h3>Ofertas Manager</h3>

    <h4 style="text-align: center;">Ofertas Cargadas</h4>
    <div class="w3-responsive">
        <table class="w3-table-all w3-hoverable">
            <tr class="rowLine">
                <td>ID</td>
                <td>Empleado</td>
                <td>Mensaje</td>
                <td>Fecha</td>
                <td>Valida</td>
            </tr>

            <c:forEach items="${ofertasCargadas}" var="oferta">
                <tr class="rowLine">
                    <td>${oferta.id}</td>
                    <td>${oferta.idEmpleado}</td>
                    <td>${oferta.mensaje}</td>
                    <td>${oferta.fecha}</td>
                    <td>${oferta.valida}</td>
                </tr>
            </c:forEach>

        </table>


        <%-- BOTONES --%>

        <form:form action="enviarNotificaciones" method="POST" modelAttribute="cierre">
            <form:input path="idEmpleado" type="number" class="form-control mb-25" placeholder="empID" value="test"
                        style="display:none"/>
            <button id="btn-registrarme" class="w3-button w3-block btn-warning" type="Submit">
                Enviar Notificaciones
            </button>
        </form:form>

        <c:if test="${exito==false}">
            <div classs="container p-5">
                <div class="alert alert-danger" role="alert">
                        ${mensaje}
                </div>
            </div>
        </c:if>
        <c:if test="${exito==true}">
            <br><br>
            <div classs="container p-5">
                <div class="alert alert-success" role="alert">
                        ${mensaje}
                </div>
            </div>
        </c:if>

    </div>
</div>
<%@ include file="generales/footer.jsp" %>
