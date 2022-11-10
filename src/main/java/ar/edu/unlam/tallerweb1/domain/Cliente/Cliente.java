package ar.edu.unlam.tallerweb1.domain.Cliente;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(name= "fecha", nullable = false, updatable = false, unique = false)
    private LocalDate fechaIngreso;

    private String nombre;

    private boolean notifEnable;

    private String mail;



    public Long getId() {
        return id;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNotifEnable() {
        return notifEnable;
    }

    public void setNotifEnable(boolean notifEnable) {
        this.notifEnable = notifEnable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
