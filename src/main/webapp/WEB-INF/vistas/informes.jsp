<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">

    <h3>Ventas por empleado</h3>

    <form:form action="${pageContext.request.contextPath}/informes/ventasEmpleados" modelAttribute="fechas" method="post">
        <ul class="w3-ul w3-card-4 w3-white">
            <li class="w3-padding-16">
                <form:label path="fechaInicial">Fecha inicial</form:label>
                <form:input type="date" path="fechaInicial"/>
                <form:label path="fechaFinal">Fecha final</form:label>
                <form:input type="date" path="fechaFinal"/>
                <form:button type="submit" class="btn btn-info">Mostrar grafico</form:button>
            </li>
        </ul>
    </form:form>

    <form action="${pageContext.request.contextPath}/informes/ventasHora" method="get">
        <ul class="w3-ul w3-card-4 w3-white">
            <li class="w3-padding-16">
                <label></label>
                <input type="text" name="params"/>
                <label></label>
                <input type="text" name="params"/>
                <label></label>
                <input type="text" name="params"/>
                <button class="btn btn-info" type="Submit">Mostrar grafico</button>
            </li>
        </ul>
    </form>
</div>

<%@ include file="generales/footer.jsp" %>



