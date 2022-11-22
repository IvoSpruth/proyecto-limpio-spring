<%@ include file="generales/importsGenerales.jsp" %>
<div class="w3-container">
    <div id="loginbox">
        <h3 class="form-signin-heading">Taller Web I</h3>
    </div>
    <ul class="w3-ul w3-card-4 w3-white">
        <form:form action="validar-login" method="POST" modelAttribute="datosLogin" acceptCharset="UTF-8">
        <li class="w3-padding-16">
            <label for="email">Usuario: </label>
            <form:input path="email" id="email" type="email" class="form-control"/>
        </li>
        <li class="w3-padding-16">
            <label for="password">Password: </label>
            <form:input path="password" type="password" id="password" class="form-control"/>
        </li>
    </ul>
    <button id="btn-registrarme" class="w3-button w3-block w3-teal" Type="Submit"><h5>Login</h5></button>
    </form:form>
</div>

<div class="w3-container">
    <a href="registrar-usuario">Registrarme</a>
</div>

<%@ include file="generales/footer.jsp" %>
