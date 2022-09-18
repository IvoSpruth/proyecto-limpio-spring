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

		<div class="card">
			<div class="card-header">
				<h1 class="text-center">Centro de control Negocio</h1>
			</div>
			<div class="card-body">
				<div class = "container">
					<div class="form-group w-75 mr-auto ml-auto">
						<a class="btn btn-primary btn-lg btn-block w-75" href="${pageContext.servletContext.contextPath}/Control">Venta</a>				
					</div>
					<div class="form-group">
						<a class="btn btn-primary btn-lg btn-block" href="${pageContext.servletContext.contextPath}/goEmpleadoForm">Empleado</a>
					</div>
					<div class="form-group">
						<a class="btn btn-primary btn-lg btn-block" href="${pageContext.servletContext.contextPath}/goProductoForm">Producto</a>
					</div>
					<div class="form-group">
						<a class="btn btn-primary btn-lg btn-block" href="${pageContext.servletContext.contextPath}/Control">Salir</a>
					</div>
				</div>
			</div>
		</div>




		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>