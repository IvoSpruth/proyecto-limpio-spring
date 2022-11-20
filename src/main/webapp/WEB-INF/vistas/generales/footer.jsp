<!-- Footer -->
<footer class="w3-container w3-padding-16 w3-light-grey">
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


    <h4>Visita sistemasdeadministracion.com.ar</h4>
    <p>Basado en <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

<!-- End page content -->

</div>


<c:if test="${result==false}">
    <div classs="container p-5">
        <div class="alert alert-danger" role="alert">
                ${message}
        </div>
    </div>
</c:if>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>
