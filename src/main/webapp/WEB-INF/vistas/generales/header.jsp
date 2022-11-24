<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Top container -->
<header class="w3-bar w3-black w3-large" >
    <span class="w3-bar-item w3-right">Tu negocio WEB<small class="w3-margin-left">versión v1.6</small></span>
</header>

    <!-- Header -->
    <div class="w3-container">
        <h5><b><i class="fa fa-dashboard"></i>Centro de control</b></h5>
    </div>


    <c:if test="${not empty error}">
    <div class="text-bg-danger">
        <h4>${error}</h4>
    </div>
    </c:if>

    <c:if test="${not empty mensaje}">
    <div>
        <h4>${mensaje}</h4>
    </div>
    </c:if>

        <% if (request.getSession().getAttribute("mensaje") == null) {%>
    <div>
        <h4><c:out value="${sessionScope.mensaje}"/></h4>
        <h4>${request.getSession().getAttribute("mensaje")}</h4>
    </div>
        <%}request.getSession().setAttribute("mensaje", null);%>
        <% if (request.getSession().getAttribute("ROL") != null) {%>
    <div class="w3-row-padding w3-margin-bottom">
        <div class="w3-quarter">
            <div class="w3-container w3-red w3-padding-16">
                <div class="w3-left"><i class="fa fa-money w3-xxxlarge"></i></div>

                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goVentaForm" class="link">Hacé tu venta</a>
                </h4>
            </div>
        </div>
        <div class="w3-quarter">
            <div class="w3-container w3-blue w3-padding-16">
                <div class="w3-left"><i class="fa fa-archive w3-xxxlarge"></i></div>

                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goCierreDiario" class="link">Cerrá la caja del día</a>
                </h4>
            </div>
        </div>
        <div class="w3-quarter">
            <div class="w3-container w3-teal w3-padding-16">
                <div class="w3-left"><i class="fa fa-gift w3-xxxlarge"></i></div>

                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goProductoForm" class="link">Registrá tus productos</a>
                </h4>
            </div>
        </div>
        <div class="w3-quarter">
            <div class="w3-container w3-orange w3-text-white w3-padding-16">
                <div class="w3-left"><i class="fa fa-times-circle-o w3-xxxlarge"></i></div>

                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goOfertas" class="link">Enviá tus ofertas</a>
                </h4>
            </div>
        </div>
    </div>
<%}%>