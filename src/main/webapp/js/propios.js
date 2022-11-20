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

function showformExportar(){
    document.querySelector(".form_exportar").classList.add("mostrarForm");
}

function hideformExportar(){
    document.querySelector(".form_exportar").classList.remove("mostrarForm");
}