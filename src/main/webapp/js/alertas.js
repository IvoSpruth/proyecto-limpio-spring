// https://github.com/apvarun/toastify-js

function crearToast(alerta) {
    Toastify({
        text: alerta.contenido,
        duration: -1,
        close: true,
    }).showToast();
}

fetch('http://localhost:8080/proyecto_limpio_spring_war_exploded/alertas')
    .then(res => res.json())
    .then(alertas => alertas.forEach(alerta => crearToast(alerta)));