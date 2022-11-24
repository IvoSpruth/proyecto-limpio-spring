<%@ include file="generales/importsGenerales.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="w3-container">
    <h3 class="w3-center">Logueate</h3>
    <form:form action="validar-login" method="POST" modelAttribute="datosLogin" acceptCharset="UTF-8" class="w3-card w3-white" style="width:50%;margin:auto;">
    <div>
        <div class="w3-padding-16 w3-margin">
            <label for="email">Usuario: </label>
            <form:input path="email" id="email" type="email" class="w3-input"/>
        </div>
        <div class="w3-padding-16 w3-margin">
            <label for="password">Contraseña: </label>
            <form:input path="password" type="password" id="password" class="w3-input"/>
        </div>
    </div>
    <button class="w3-button w3-block w3-teal" type="Submit"><h5>Login</h5></button>
    </form:form>
</div>

<div class="w3-container w3-margin-top">
    <a class="w3-center w3-block" href="registrar-usuario">Registrarme</a>
</div>

<%@ include file="generales/footer.jsp" %>
