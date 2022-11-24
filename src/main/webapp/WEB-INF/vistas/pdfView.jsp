<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema Administracion</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <style>
        html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
        .rowLine{
            background-color:#e1e2e3;   
            border-bottom:0px;
        }
        .rowLine > td{
            text-align:center;
        }
        .valueRows > td{
            text-align:center;
        }
        .basicTable{
                border-collapse: collapse;
        }
        .cerrarDesglose{
            display:none;
        }

        .abrirDesglose{
            display: ;
            cursor: pointer;                      
            position: absolute;
            top: 15s%;
            left: 0;
            background-color: white;
            width:100%;
            height:100%;
        }

        .abrirDesglose > td {
            display:block;
        }
        .cerrarDesglose > td {
            display:block;
        }
        .black_overlay {
            display: none;
            position: fixed;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: black;
            z-index: 1001;
            /* -moz-opacity: 0.9; */
            /* opacity: .90; */
            /* filter: alpha(opacity=70); */
        }

        .white_content {
            min-width: 280px;
            display: none;
            position: fixed;
            top: 10%;
            left: 20%;
            width: 60%;
            height: 80%;
            padding: 16px;
            border: 2px solid black;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }
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
            <span>Fecha: ${fecha}</span><br>
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
    
    
    <%-- <iframe src="C:\Users\IvoSpruth\Documents\Personal\Taller Web I\proyecto-limpio-spring\resources\Factura332534496.png" style="width:600px; height:500px;" frameborder="0"></iframe> --%>
    <img src="${pagePath}/${path}">
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

    function closeDesglose(){    
        
        target = event.target;
        id = target.id;
        
        document.getElementById('desgMinCNContainer').style.display = 'none';
        document.getElementById('desgMinCN').style.display = 'none';
        
        
    }
        
    function openDesglose(){             
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>
