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
			<span>Bienvenido/a al sistema</span><br>
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

	<div class="w3-panel">
		<div class="w3-row-padding" style="margin:0 -16px">
			<div class="w3-third">
				<h5>Regiones</h5>
				<img src="https://blogdecarmen2016.files.wordpress.com/2017/02/empresas.jpg?w=620" style="width:100%" alt="Google Regional Map">
			</div>
			<div class="w3-twothird">
				<h5>Informacion</h5>
				<table class="w3-table w3-striped w3-white">
					<tr>
						<td><i class="fa fa-user w3-text-blue w3-large"></i></td>
						<td>Empleados</td>
						<td><i>10</i></td>
					</tr>
					<tr>
						<td><i class="fa fa-bell w3-text-red w3-large"></i></td>
						<td>Notificaciones</td>
						<td><i>15</i></td>
					</tr>
					<tr>
						<td><i class="fa fa-users w3-text-yellow w3-large"></i></td>
						<td>Empresas</td>
						<td><i>17</i></td>
					</tr>
					<tr>
						<td><i class="fa fa-comment w3-text-red w3-large"></i></td>
						<td>Comentarios</td>
						<td><i>25</i></td>
					</tr>
					<tr>
						<td><i class="fa fa-bookmark w3-text-blue w3-large"></i></td>
						<td>Dias importantes</td>
						<td><i>3</i></td>
					</tr>
					<tr>
						<td><i class="fa fa-laptop w3-text-red w3-large"></i></td>
						<td>Sobre el sistema</td>
						<td><i>35</i></td>
					</tr>
					<tr>
						<td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
						<td>Compartir recursos</td>
						<td><i>2</i></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
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

<c:if test="${not empty factura}">
	<a>${factura}</a>
	<br>
</c:if>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>