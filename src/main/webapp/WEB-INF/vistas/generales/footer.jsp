
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

    function closeDesglose() {

        target = event.target;
        id = target.id;

        document.getElementById('desgMinCNContainer').style.display = 'none';
        document.getElementById('desgMinCN').style.display = 'none';


    }

    function openDesglose() {
        target = event.target;
        id = target.id;
        console.log(id);
        //redesList = ['INSTAGRAM', 'FACEBOOK', 'TWITTER', 'UBER', 'SNAPCHAT', 'WHATSAPP'];


        document.getElementById('desgMinCNContainer').style.display = 'block';
        document.getElementById('desgMinCN').style.display = 'block';

    }

</script>

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
