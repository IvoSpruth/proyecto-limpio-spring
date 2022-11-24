package ar.edu.unlam.tallerweb1.domain.cliente;

import ar.edu.unlam.tallerweb1.domain.envios.Envio;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente")
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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Envio> envios;

    @OneToMany(mappedBy = "cliente")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Direccion> direcciones;

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

    public List<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(List<Envio> envios) {
        this.envios = envios;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
