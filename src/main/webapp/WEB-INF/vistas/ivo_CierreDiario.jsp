<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<header class="page-header bg-info">
    <h1>Sistema Administracion <small>Negocio Fisico v.01</small></h1>
    <h1>Fecha: ${fecha}</h1>
</header>
<body>

<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <h3 class="form-signin-heading">Cierre Diario</h3>
    <hr class="colorgraph"><br>

    <h5>Ventas del Dia</h5>

    <table class="table">
        <thead>
            <tr>
            <th scope="col">ID</th>
            <th scope="col">Empleado</th>
            <th scope="col">Cantidad Productos</th>
            <th scope="col">Total</th>
            <th scope="col">Factura</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ventasDia}" var="v">                
                <tr >
                <td scope="row">${v.id}</td>
                <td >${v.idEmpleado}</td>
                <td >${v.cantidadProducto+v.cantidadProducto2}</td>
                <td >${v.total}</td>
                <td "><a href="#link" class="btn btn-info" role="button">PDF</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form:form action="ejecutarCierreDiario" method="POST" modelAttribute="cierre">
        <%-- ${botonHabilitado==false ? 
         <button id="btn-registrarme" class="btn btn-lg btn-warning btn-block" Type="Submit"/>Ejecutar Cierre</button> :
         <button id="btn-registrarme" class="btn btn-lg btn-success btn-block" Type="Submit"/>Ejecutar Cierre</button> 
         } --%>
        <%-- <a class="btn btn-primary btn-lg btn-block" href="${pageContext.servletContext.contextPath}/goProductoForm">Producto</a> --%>
        <form:input path="idEmpleado" type="number" class="form-control mb-25" placeholder="empID" value="test" style="display:none"/>
        <button id="btn-registrarme" class="btn btn-lg btn-warning btn-block" Type="Submit"/>Ejecutar Cierre</button> 
    </form:form>

    <c:if test="${exito==false}">
        <div classs="container p-5">
            <div class="alert alert-danger" role="alert">
                ${mensaje}
            </div>
        </div>   
    </c:if>

</div>

</body>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</footer>
<!-- Footer -->


<!-- Placed at the end of the document so the pages load faster -->
</html>


