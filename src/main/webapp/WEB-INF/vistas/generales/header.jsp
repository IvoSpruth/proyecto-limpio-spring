<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
    <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i
            class="fa fa-bars"></i>  Menu
    </button>
    <span class="w3-bar-item w3-right">Sistema Administracion <small>Negocio Fisico v.01</small></span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
    <div class="w3-container w3-row">
        <div class="w3-col s4">
            <div class="fa fa-user-o w3-xxxlarge" style="width:46px"></div>
        </div>
        <div class="w3-col s8 w3-bar">
            <span>Fecha: ${fecha}</span><br>
            <a href="#" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a>
            <a href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
            <a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
        </div>
    </div>
    <hr>
    <div class="w3-container">
        <h5>Empleado: </h5>
    </div>
    <div class="w3-bar-block">
        <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
           onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
        <a href="#" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i> Rol</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i> Nombre</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i> Ventas</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i> Editar</a><br><br>
    </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="close side menu" id="myOverlay"></div>
<div class="w3-main container" style="margin-left:300px;margin-top:43px;">

    <!-- Header -->
    <header class="w3-container" style="padding-top:22px">
        <h5><b><i class="fa fa-dashboard"></i> Centro de control Negocio</b></h5>
    </header>

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
        <%} request.getSession().setAttribute("Mensaje",null);%>
    <div class="w3-row-padding w3-margin-bottom">
        <div class="w3-quarter">
            <div class="w3-container w3-red w3-padding-16">
                <div class="w3-left"><i class="fa fa-money w3-xxxlarge"></i></div>
                <div class="w3-right">
                    <h3>52</h3>
                </div>
                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goVentaForm">Venta</a>
                </h4>
            </div>
        </div>
        <div class="w3-quarter">
            <div class="w3-container w3-blue w3-padding-16">
                <div class="w3-left"><i class="fa fa-archive w3-xxxlarge"></i></div>
                <div class="w3-right">
                    <h3>99</h3>
                </div>
                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goCierreDiario">Cierre Diario</a>
                </h4>
            </div>
        </div>
        <div class="w3-quarter">
            <div class="w3-container w3-teal w3-padding-16">
                <div class="w3-left"><i class="fa fa-gift w3-xxxlarge"></i></div>
                <div class="w3-right">
                    <h3>23</h3>
                </div>
                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goProductoForm">Producto</a>
                </h4>
            </div>
        </div>
        <div class="w3-quarter">
            <div class="w3-container w3-orange w3-text-white w3-padding-16">
                <div class="w3-left"><i class="fa fa-times-circle-o w3-xxxlarge"></i></div>
                <div class="w3-right">
                    <h3>50</h3>
                </div>
                <div class="w3-clear"></div>
                <h4>
                    <a href="${pageContext.servletContext.contextPath}/goOfertas">Ofertas</a>
                </h4>
            </div>
        </div>
    </div>


    <div>

    </div>
