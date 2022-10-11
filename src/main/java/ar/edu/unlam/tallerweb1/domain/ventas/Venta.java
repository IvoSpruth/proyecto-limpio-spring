package ar.edu.unlam.tallerweb1.domain.ventas;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
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

    @CreationTimestamp
    private Date fecha;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
}
