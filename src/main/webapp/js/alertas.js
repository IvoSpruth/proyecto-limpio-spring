// https://github.com/apvarun/toastify-js

function crearToast(alerta) {
    Toastify({
        text: alerta.contenido,
        duration: 3500,
        className: "w3-panel w3-orange w3-padding-16",
        close: true,
        style:{
            background: "blue",
        },
        backgroundColor: "blue",
    }).showToast();
}

fetch('http://localhost:8080/proyecto_limpio_spring_war_exploded/alertas')
    .then(res => res.json())
    .then(alertas => alertas.forEach(alerta => crearToast(alerta)));