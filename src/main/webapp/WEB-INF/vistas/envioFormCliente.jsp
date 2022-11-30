<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
    <h3>Seleccioná el cliente que desea el envío:</h3>
    <div class="w3-row">
        <form:form action="${pagePath}/envios/enviar/datosEnvio" modelAttribute="form"
                   method="post" class="w3-panel w3-white w3-padding w3-margin w3-half w3-round-large">
            <form:hidden class="w3-input" path="idVenta"/>
            <form:select class="w3-select w3-padding w3-margin-bottom" path="idCliente">
                <form:options items="${clientes}" itemLabel="nombre" itemValue="id"/>
            </form:select>
            <form:button type="submit" class="w3-button w3-teal w3-padding">Enviar</form:button>
        </form:form>
    </div>
</div>

<%@ include file="generales/footer.jsp" %>
