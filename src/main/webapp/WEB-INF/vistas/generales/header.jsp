<%@ page contentType="text/html;charset=UTF-8" %>

<header class="w3-bar w3-black w3-large">
    <c:if test="${not empty sessionScope.ROL}">
        <a class="w3-bar-item w3-button w3-teal w3-margin" href="${pagePath}/centroControl">Volver al centro de
            control</a>
    </c:if>
    <c:if test="${empty sessionScope.ROL}">
        <c:if test="${empty login}">
            <script>
                window.onload = function () {
                    window.location.replace("${pagePath}/login");
                }
            </script>
        </c:if>
    </c:if>
    <c:if test="${not empty sessionScope.ROL}">
        <a class="w3-bar-item w3-button w3-teal w3-margin w3-right" href="${pagePath}/cerrarSesion">Cerrar sesion</a>
    </c:if>
    <h5 class="w3-bar-item w3-right w3-margin">Tu negocio WEB<small class="w3-margin-left">versión v1.6</small></h5>
</header>

<!-- TODO: TRES FORMAS DE MOSTRAR ERRORES!!!!!!!!!!!!! -->

<c:if test="${exito==false}">
    <div class="w3-panel w3-red">
        <h4>Alerta!</h4>
        <p>${mensaje}</p>
    </div>
</c:if>

<c:if test="${not empty error}">
    <div class="w3-panel w3-red">
        <h4>Alerta!</h4>
        <p>${error}</p>
    </div>
</c:if>

<c:if test="${not empty mensaje}">
    <div class="w3-panel w3-blue">
        <h4>Info!</h4>
        <p>${mensaje}</p>
    </div>
    <% session.removeAttribute("mensaje");%>
</c:if>