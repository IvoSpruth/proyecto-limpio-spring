<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form:form action="${pageContext.servletContext.contextPath}/envios/form/datosEnvio" modelAttribute="form">
    <form:select path="idCliente">
        <form:options items="${clientes}" itemLabel="nombre" itemValue="id"></form:options>
    </form:select>
      <form:button type="submit">Enviar</form:button>
  </form:form>
</body>
</html>