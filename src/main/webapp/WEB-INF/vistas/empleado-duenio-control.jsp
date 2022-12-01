<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
  <h3><i class="fa fa-dashboard w3-margin-right"></i>Centro de control</h3>
  <h4>Estás administrando como: ${sessionScope.ROL}</h4>
</div>

<div class="w3-row-padding w3-margin-bottom">
  <div class="w3-quarter">
    <div class="w3-container w3-red w3-padding-16">
      <div class="w3-left"><i class="fa fa-money w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/goVentaForm" class="link">Hacé tu venta</a>
      </h4>
    </div>
  </div>
  <div class="w3-quarter">
    <div class="w3-container w3-blue w3-padding-16">
      <div class="w3-left"><i class="fa fa-archive w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/goCierreDiario" class="link">Cerrá la caja del
          día</a>
      </h4>
    </div>
  </div>
  <div class="w3-quarter">
    <div class="w3-container w3-teal w3-padding-16">
      <div class="w3-left"><i class="fa fa-gift w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/goProductoForm" class="link">Registrá tus
          productos</a>
      </h4>
    </div>
  </div>
  <div class="w3-quarter">
    <div class="w3-container w3-orange w3-text-white w3-padding-16">
      <div class="w3-left"><i class="fa fa-dollar w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/goOfertas" class="link">Enviá tus ofertas</a>
      </h4>
    </div>
  </div>
  <div class="w3-half w3-margin-top">
    <div class="w3-container w3-green w3-padding-16">
      <div class="w3-left"><i class="fa fa-truck w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/envios/mostrar" class="link">Administrá los envíos</a>
      </h4>
    </div>
  </div>
  <div class="w3-half w3-margin-top">
    <div class="w3-container w3-purple w3-padding-16">
      <div class="w3-left"><i class="fa fa-clipboard w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/informes" class="link">Visualizá tus informes</a>
      </h4>
    </div>
  </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/toastify-js/1.6.1/toastify.min.js"
        integrity="sha512-79j1YQOJuI8mLseq9icSQKT6bLlLtWknKwj1OpJZMdPt2pFBry3vQTt+NZuJw7NSd1pHhZlu0s12Ngqfa371EA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="js/alertas.js"></script>
<%@ include file="generales/footer.jsp" %>