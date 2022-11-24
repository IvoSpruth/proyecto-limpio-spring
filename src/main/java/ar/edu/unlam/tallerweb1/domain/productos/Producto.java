package ar.edu.unlam.tallerweb1.domain.productos;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import ar.edu.unlam.tallerweb1.domain.ventas.VentaProducto;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "Producto")
@Table(name = "producto")
@NaturalIdCache
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private double costo;

    private String nombre;

    private int idProveedor;

    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(producto.costo, costo) == 0 && idProveedor == producto.idProveedor && cantidad == producto.cantidad && id.equals(producto.id) && Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, costo, nombre, idProveedor, cantidad);
    }
}
