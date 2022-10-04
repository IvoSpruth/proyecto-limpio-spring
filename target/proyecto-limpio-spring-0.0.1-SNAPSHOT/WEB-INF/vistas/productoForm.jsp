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
		</div>
		<br/>
		<br/>
		<br/>
		
		<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<h3 class="form-signin-heading">Nuevo Producto</h3>
			<hr class="colorgraph"><br>
			<form:form action="addProducto" method="POST" modelAttribute="producto" acceptCharset="UTF-8">
				<label for="nombre">Nombre: </label>
				<form:input path="nombre" type="text" id="nombre" class="form-control mb-25" placeholder="Nombre"/>
				<label for="Name">Costo: </label>
				<form:input path="costo" type="text" id="costo" class="form-control mb-25" />	
				<label for="idProveedor">Id Proveedor: </label>
				<form:input path="idProveedor" type="number" id="idProveedor" class="form-control mb-25" />
				<label for="cantidad">Cantidad: </label>
				<form:input path="cantidad" type="number" id="cantidad" class="form-control mb-25" />
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