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
      <div class="w3-left"><i class="fa fa-times-circle-o w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/goOfertas" class="link">Enviá tus ofertas</a>
      </h4>
    </div>
  </div>
  <div class="w3-half w3-margin-top">
    <div class="w3-container w3-green w3-padding-16">
      <div class="w3-left"><i class="fa fa-times-circle-o w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/envios/mostrar" class="link">Administrá los envíos</a>
      </h4>
    </div>
  </div>
  <div class="w3-half w3-margin-top">
    <div class="w3-container w3-purple w3-padding-16">
      <div class="w3-left"><i class="fa fa-times-circle-o w3-xxxlarge w3-margin-right"></i></div>
      <h4>
        <a href="${pagePath}/informes" class="link">Visualizá tus informes</a>
      </h4>
    </div>
  </div>
</div>

<div class="w3-panel">
  <div class="w3-row-padding" style="margin:0 -16px">
    <div class="w3-third">
      <h5>Regiones</h5>
      <img src="https://blogdecarmen2016.files.wordpress.com/2017/02/empresas.jpg?w=620" style="width:100%"
           alt="Google Regional Map">
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

<script src="https://cdnjs.cloudflare.com/ajax/libs/toastify-js/1.6.1/toastify.min.js"
        integrity="sha512-79j1YQOJuI8mLseq9icSQKT6bLlLtWknKwj1OpJZMdPt2pFBry3vQTt+NZuJw7NSd1pHhZlu0s12Ngqfa371EA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="js/alertas.js"></script>
<%@ include file="generales/footer.jsp" %>