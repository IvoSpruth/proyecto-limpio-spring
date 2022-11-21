package ar.edu.unlam.tallerweb1.domain.alertas;


import java.time.LocalDateTime;
public class Alerta {

    private String contenido;
    private LocalDateTime fecha;

    private Boolean visible = true;

    public Alerta(String contenido) {
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Boolean getVisible() {
        return visible;
    }
}
