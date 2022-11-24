<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema Administracion</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
    </style>
</head>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
    <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
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
        <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
        <a href="#" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-users fa-fw"></i> Rol</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i> Nombre</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i> Ventas</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i> Editar</a><br><br>
    </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

    <!-- Header -->
    <header class="w3-container" style="padding-top:22px">
        <h5><b><i class="fa fa-dashboard"></i> Centro de control Negocio</b></h5>
    </header>

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

    <div class="w3-container">
        <form:form action="empleado-dueño-control" method="POST" modelAttribute="venta" acceptCharset="UTF-8">
            <div id="loginbox">
                <h3 class="form-signin-heading">Resumen de venta</h3>
            </div>

            <div class="w3-container">
                <h5>Id del empleado: ${idEmpleado}</h5>
                <h5>Comision: ${comision}</h5>
                <table class="w3-table w3-striped w3-bordered w3-border w3-hoverable w3-white">
                    <tr>
                        <th scope="col">Nombre del producto</th>
                        <th scope="col">Precio unitario</th>
                        <th scope="col">Id producto</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">Total del producto</th>
                        <th scope="col">Descuento</th>
                    </tr>

                    
                    <c:forEach items="${productos}" var="p">
                        <tr>
                            <td>${p.nombre}</td>
                            <td>${p.precio}</td>
                            <td>${p.id}</td>
                            <td>${p.cantidad}</td>
                            <td>${p.totalProducto}</td>
                            <td>${p.descuento}</td>
                        </tr>
                    </c:forEach>
                    
                    
                    <tr>
                        <th scope="row">TOTAL</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>${sumaTotal}</td>
                    </tr>
                </table><br>
                <button class="w3-button w3-dark-grey">
                    <a href="${pageContext.servletContext.contextPath}/centroControl">Volver al inicio</a>
                    <i class="fa fa-arrow-left"></i></button>
                <button class="w3-button w3-dark-grey">
                    <a href="#">Descargar factura</a>
                    <i class="fa fa-arrow-down"></i></button>
            </div>
            <hr>
        </form:form>
    </div>
    <hr>

    <div class="w3-container w3-dark-grey w3-padding-32">
        <div class="w3-row">
            <div class="w3-container w3-third">
                <h5 class="w3-bottombar w3-border-green">Reportes venta</h5>
            </div>
            <div class="w3-container w3-third">
                <h5 class="w3-bottombar w3-border-red">Reportes cobro</h5>
            </div>
            <div class="w3-container w3-third">
                <h5 class="w3-bottombar w3-border-orange">Reportes empleados</h5>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="w3-container w3-padding-16 w3-light-grey">
        <h4>Visita sistemasdeadministracion.com.ar</h4>
        <p>Basado en <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
    </footer>

    <!-- End page content -->
</div>

<script>
    // Get the Sidebar
    var mySidebar = document.getElementById("mySidebar");

    // Get the DIV with overlay effect
    var overlayBg = document.getElementById("myOverlay");

    // Toggle between showing and hiding the sidebar, and add overlay effect
    function w3_open() {
        if (mySidebar.style.display === 'block') {
            mySidebar.style.display = 'none';
            overlayBg.style.display = "none";
        } else {
            mySidebar.style.display = 'block';
            overlayBg.style.display = "block";
        }
    }

    // Close the sidebar with the close button
    function w3_close() {
        mySidebar.style.display = "none";
        overlayBg.style.display = "none";
    }
</script>

<c:if test="${result==false}">
    <div classs="container p-5">
        <div class="alert alert-danger" role="alert">
                ${message}
        </div>
    </div>
</c:if>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>
