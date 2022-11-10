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