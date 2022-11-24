<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
    <h3 class="form-signin-heading">Cierre Diario</h3>
    <h4 style="text-align: center;">Ventas del Dia</h4>

    <div class="w3-card-4 w3-responsive">
        <table class="w3-table-all w3-hoverable">
            <colgroup>
                <col width="36%">
                <col width="16%">
                <col width="16%">
                <col width="16%">
                <col width="16%">
            </colgroup>


            <tr class="rowLine">
                <td>ID</td>
                <td>Empleado</td>
                <td>Cantidad Productos</td>
                <td>Total</td>
                <td>Pagado</td>
                <td>Factura</td>
            </tr>

            <c:forEach items="${ventasDia}" var="v">
                <tr class="valueRows">
                    <td scope="row">${v.id}</td>
                    <td>${v.idEmpleado}</td>
                    <td><!--TODO --></td>
                    <td>${v.total}</td>
                    <td>
                        <c:forEach items="${links}" var="link">
                            <c:if test="${link.venta.id == v.id}">
                                <c:choose>
                                    <c:when test="${link.estado == 'approved'}">
                                        <a href="#" class="btn btn-success"><i class="fa fa-check"></i></a>
                                    </c:when>
                                    <c:when test="${link.estado == 'rejected'}">
                                        <a href="${link.linkDePago}" class="btn btn-danger"><i class="fa fa-times"
                                                                                               aria-hidden="true"></i>
                                        </a>
                                    </c:when>
                                    <c:when test="${link.estado == 'in_process'}">
                                        <a href="${link.linkDePago}" class="btn btn-warning"><i
                                                class="fa fa-exclamation-triangle" aria-hidden="true"></i>
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${link.linkDePago != null}">
                                                <a href="${link.linkDePago}" class="btn btn-default"><i
                                                        class=" fa fa-question-circle"
                                                        aria-hidden="true"></i>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="#" class="btn btn-danger"><i class=" fa fa-question-circle"
                                                                                      aria-hidden="true"></i>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td><a href="${pageContext.servletContext.contextPath}/goPDF" class="btn btn-info"
                           role="button">PDF</a></td>
                </tr>
            </c:forEach>

        </table>

    </div>


    <%-- BOTONES --%>
    <br><br>
    <form:form action="ejecutarCierreDiario" method="POST" modelAttribute="cierre">
        <form:input path="idEmpleado" type="number" class="form-control mb-25" placeholder="empID" value="test"
                    style="display:none"/>
        <button id="btn-registrarme" class="w3-button w3-block w3-blue" type="Submit">
            Ejecutar Cierre
        </button>

    </form:form>
    <br><br>
    <button id="btn-registrarme" class="w3-button w3-block w3-cyan" type="Submit" onclick="openDesglose()">
        Historial Cierres
    </button>

</div>
<c:if test="${exito==false}">
<div classs="container p-5">
    <div class="alert alert-danger" role="alert">
            ${mensaje}
    </div>
</div>
</c:if>
<%-- --- --%>


<div id="desgMinCNContainer" onclick="closeDesglose()">
    <div id="desgMinCN" class="white_content">
        <br><br>
        <table class="basicTable">
            <colgroup>
                <col width="30%">
                <col width="22%">
                <col width="16%">
                <col width="16%">
                <col width="16%">
            </colgroup>


            <tr class="rowLine">
                <td>ID</td>
                <td>Fecha</td>
                <td>Cantidad Productos</td>
                <td>Total</td>
                <td>Id Empleado</td>
            </tr>

            <c:forEach items="${cierres}" var="c">
                <tr class="valueRows">
                    <td scope="row">${c.id}</td>
                    <td>${c.fecha}</td>
                    <td>${c.cantProductos}</td>
                    <td>${c.total}</td>
                    <td>${c.idEmpleado}</td>
                </tr>
            </c:forEach>


        </table>


    </div>
</div>
<br>

<hr>


<%@ include file="generales/footer.jsp" %>
