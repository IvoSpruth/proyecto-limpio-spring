<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
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
  <h3 class="form-signin-heading">Nuevo Empleado</h3>
  <hr class="colorgraph">
  <br>
  <form:form action="addEmpleado" method="POST" modelAttribute="empleado" acceptCharset="UTF-8">
  <label for="nombre">Nombre: </label>
    <form:input path="name" type="text" id="nombre" class="form-control mb-25" placeholder="Nombre"/>
  <label for="rol">rol: </label>
    <form:input path="rol" type="text" id="rol" class="form-control mb-25"/>
  <label for="sueldo">Sueldo: </label>
    <form:input path="sueldo" type="number" id="sueldo" class="form-control mb-25"/>
  <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>
  Agregar</button>
  </form:form>

  <br><br>


  <h3>Employee Details</h3>
  <hr size="4" color="gray"/>
  <table>
      <%=eList%>
    <c:forEach items="${eList}" var="employee">
    <tr>
      <td>Employee ID: <c:out value="${employee.eid}"/></td>
      <td>Employee Pass: <c:out value="${employee.ename}"/></td>
    </tr>
    </c:forEach>

    <c:forEach items="${empleadosData}" var="data">
    <c:if test="${data.id == dataToMatchOn.id}">
      <c:set var="selectedItem" value="${data}/>
							</c:if>
						</c:forEach>

						<p>From here on I can work with the selected item:</p>

						${selectedItem.children}

			
		</div>
		<br/>
		<br/>
		<br/>
		


		<!-- Placed at the end of the document so the pages load faster -->
		<script src=" https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>