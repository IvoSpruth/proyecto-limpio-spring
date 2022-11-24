<%@ page contentType="text/html;charset=UTF-8" %>

<header class="w3-bar w3-black w3-large">
  <c:if test="${not empty sessionScope.ROL}">
    <a class="w3-bar-item w3-button w3-teal w3-margin" href="${pagePath}/centroControl">Volver al centro de control</a>
  </c:if>
  <h5 class="w3-bar-item w3-right w3-margin">Tu negocio WEB<small class="w3-margin-left">versión v1.6</small></h5>
</header>

<c:if test="${not empty error}">
  <div class="text-bg-danger">
    <h4>${error}</h4>
  </div>
</c:if>

<c:if test="${not empty mensaje}">
  <div>
    <h4>${mensaje}</h4>
  </div>
</c:if>

<% if (request.getSession().getAttribute("mensaje") == null) {%>
<div>
  <h4><c:out value="${sessionScope.mensaje}"/></h4>
  <h4>${request.getSession().getAttribute("mensaje")}</h4>
</div>
<%
  }
  request.getSession().setAttribute("mensaje", null);
%>