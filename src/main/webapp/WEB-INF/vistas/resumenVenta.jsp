<%--
  Created by IntelliJ IDEA.
  User: mavillal
  Date: 10/10/2022
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
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
<body>
<div class="page-header">
    <h1>Sistema Administracion <small>Negocio Fisico v.01</small></h1>
    <h1>Fecha: ${fecha}</h1>
</div>
<br/>
<br/>
<br/>

<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <h3 class="form-signin-heading">Resumen de Venta</h3>
    <hr class="colorgraph"><br>


    <form:form action="addVenta" method="POST" modelAttribute="venta" acceptCharset="UTF-8">

        <%--<label for="idEmpleado">Id empleado: </label>
        <form:input path="idProducto" type="number" id="idEmpleado" class="form-control mb-25" placeholder="Id empleado"/>

        <table>
            <colgroup class="container">
                <col class="row align-items-center">
                <col class="row align-items-center">
                <col class="row align-items-center">
            </colgroup>--%>

            <tr class="mt-25">
                <td>
                    <table class="table table-bordered">

                            <table class="table table-bordered">
                                <tr>
                                    <th scope="col">Nombre del producto</th>
                                    <th scope="col">Precio unitario</th>
                                    <th scope="col">Id producto</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Total del producto</th>
                                    <th scope="col">Suma total</th>
                                </tr>
                                <tr>
                                    <th scope="row">${nombreProductoUno}</th>
                                    <td>${precioUnitarioUno}</td>
                                    <td>${idProductoUno}</td>
                                    <td>${cantidadUno}</td>
                                    <td>${totalProductoUno}</td>
                                    <td>${sumaTotal}</td>
                                </tr>
                                <tr>
                                    <th scope="row">${nombreProductoDos}</th>
                                    <td>${precioUnitarioDos}</td>
                                    <td>${idProductoDos}</td>
                                    <td>${cantidadDos}</td>
                                    <td>${totalProductoDos}</td>
                                    <td>${sumaTotal}</td>
                                </tr>
                                <tr>
                                    <th scope="row">TOTAL</th>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>${sumaTotal}</td>
                                </tr>
                            </table>

                            </tr>
                        </table>
                    </td>
            </tr>
        </table>

        <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>Agregar venta</button>
        <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Cancel"/>Cancelar</button>
    </form:form>


    <c:if test="${result==false}">
        <div classs="container p-5">
            <div class="alert alert-danger" role="alert">
                    ${message}
            </div>
        </div>
    </c:if>

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
