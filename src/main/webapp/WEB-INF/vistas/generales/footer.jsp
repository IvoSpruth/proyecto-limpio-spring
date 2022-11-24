<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Footer -->
<footer class="w3-container w3-padding-16 w3-light-grey">
    <% if (request.getSession().getAttribute("ROL") != null) {%>
        <div class="w3-container w3-dark-grey w3-padding-32">
            <div class="w3-row">
                <div class="w3-container w3-third">
                    <a href="${pageContext.request.contextPath}/informes/" class="link">
                        <h5 class="w3-bottombar w3-border-green">Reportes venta</h5>
                    </a>
                </div>
                <div class="w3-container w3-third">
                    <a href="${pageContext.request.contextPath}/informes/" class="link">
                        <h5 class="w3-bottombar w3-border-red">Reportes cobro</h5>
                    </a>
                </div>
                <div class="w3-container w3-third">
                    <a href="${pageContext.request.contextPath}/informes/" class="link">
                        <h5 class="w3-bottombar w3-border-orange text-white">Reportes empleados</h5>
                    </a>
                </div>
            </div>
        </div>
        <%}%>

    <h4>Visita sistemasdeadministracion.com.ar</h4>
    <p>Basado en <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

</div>

<c:if test="${result==false}">
    <div classs="container p-5">
        <div class="alert alert-danger" role="alert">
                ${message}
        </div>
    </div>
</c:if>

</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastify-js/1.6.1/toastify.min.js" integrity="sha512-79j1YQOJuI8mLseq9icSQKT6bLlLtWknKwj1OpJZMdPt2pFBry3vQTt+NZuJw7NSd1pHhZlu0s12Ngqfa371EA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="js/alertas.js"></script>
<script src="js/propios.js"></script>
</html>
