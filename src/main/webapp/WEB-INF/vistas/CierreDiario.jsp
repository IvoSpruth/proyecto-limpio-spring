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
    <h3 class="form-signin-heading">Cierre Diario</h3>
    <hr class="colorgraph"><br>

    <table class="table">
        <thead>
            <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
            </tr>
            <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
            </tr>
            <tr>
            <th scope="row">3</th>
            <td>Larry</td>
            <td>the Bird</td>
            <td>@twitter</td>
            </tr>
        </tbody>
    </table>

    <form:form action="addVenta" method="POST" modelAttribute="venta" acceptCharset="UTF-8">
        <%-- ${botonHabilitado==false ? 
         <button id="btn-registrarme" class="btn btn-lg btn-warning btn-block" Type="Submit"/>Ejecutar Cierre</button> :
         <button id="btn-registrarme" class="btn btn-lg btn-success btn-block" Type="Submit"/>Ejecutar Cierre</button> 
         } --%>
        <button id="btn-registrarme" class="btn btn-lg btn-warning btn-block" Type="Submit"/>Ejecutar Cierre</button> :
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


