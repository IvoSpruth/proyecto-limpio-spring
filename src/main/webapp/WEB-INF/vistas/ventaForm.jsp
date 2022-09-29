<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<div class="page-header">
    <h1>Sistema Administracion <small>Negocio Fisico v.01</small></h1>
    <h1>Fecha: ${fecha}</h1>
</div>
<br/>
<br/>
<br/>

<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <h3 class="form-signin-heading">Nuevo Producto</h3>
    <hr class="colorgraph"><br>
    <form:form action="addVenta" method="POST" modelAttribute="venta" acceptCharset="UTF-8">
        <label for="idEmpleado">Id empleado: </label>
        <form:input path="idEmpleado" type="number" id="idEmpleado" class="form-control mb-25" placeholder="Id empleado"/>


        <label for="idProducto">Id producto 1: </label>
        <form:input path="idProducto" type="number" id="idProducto" class="form-control mb-25" />


        <label for="cantidadProducto">Cantidad producto 1: </label>
        <form:input path="cantidadProducto" type="number" id="cantidadProducto" class="form-control mb-25" />


        <label for="idProducto2">Id producto 2: </label>
        <form:input path="idProducto2" type="number" id="idProducto2" class="form-control mb-25" />


        <label for="cantidadProducto2">Cantidad producto 2: </label>
        <form:input path="cantidadProducto2" type="number" id="cantidadProducto2" class="form-control mb-25" />


        <%--<form:select path="cantidadProducto">
            <form:options items="${productos}" itemLabel="nombre" />
        </form:select>--%>



        <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>Agregar</button>
    </form:form>

</div>
<br/>
<br/>
<br/>



<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>


