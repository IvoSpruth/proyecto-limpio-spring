<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
    <form:form action="${pageContext.servletContext.contextPath}/envios/form/datosEnvio" modelAttribute="form"
               method="post">
        <form:hidden path="idVenta"/>
        <form:select path="idCliente">
            <form:options items="${clientes}" itemLabel="nombre" itemValue="id"/>
        </form:select>
        <form:button type="submit">Enviar</form:button>
    </form:form>
</div>

<%@ include file="generales/footer.jsp" %>
