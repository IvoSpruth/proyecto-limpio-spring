package ar.edu.unlam.tallerweb1.delivery.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Fechas {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicial = null;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFinal = null;

    public Fechas(){}

    public Fechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
