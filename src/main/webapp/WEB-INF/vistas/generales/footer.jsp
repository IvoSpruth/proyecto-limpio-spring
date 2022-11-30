<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result==false}">
  <div class="container p-5">
    <div class="alert alert-danger" role="alert">
        ${message}
    </div>
  </div>
</c:if>

<footer class="w3-center">
  <h4>Realizado por Ivo Spruth, Alejandro Gonzalez y Lucas Cardozo</h4>
</footer>

</body>
<script src="${pagePath}/js/propios.js"></script>
</html>
