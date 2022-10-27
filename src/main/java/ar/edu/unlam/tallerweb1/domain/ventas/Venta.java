package ar.edu.unlam.tallerweb1.domain.ventas;

import ar.edu.unlam.tallerweb1.domain.cierreDiario.CierreDiario;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int idEmpleado;

    private int idProducto;

    private int cantidadProducto;

    private int idProducto2;

    private int cantidadProducto2;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private CierreDiario cierre;

    @CreationTimestamp
    @Column(name= "fecha", nullable = false)
    private LocalDate fecha;

    private double total;

    public Long getId() {
        return id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdProducto2() {
        return idProducto2;
    }

    public void setIdProducto2(int idProducto2) {
        this.idProducto2 = idProducto2;
    }

    public int getCantidadProducto2() {
        return cantidadProducto2;
    }

    public void setCantidadProducto2(int cantidadProducto2) {
        this.cantidadProducto2 = cantidadProducto2;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CierreDiario getCierre() {
        return cierre;
    }

    public void setCierre(CierreDiario cierre) {
        this.cierre = cierre;
    }
}
