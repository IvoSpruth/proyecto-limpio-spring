package ar.edu.unlam.tallerweb1.domain.productos;

import ar.edu.unlam.tallerweb1.domain.Estado;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.Objects;


@Entity(name = "Producto")
@Table(name = "producto")
@NaturalIdCache
public class Producto implements Estado{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private double costo;

    private String nombre;

    private int cantidad;

    public Producto() {}

    public Producto(String nombre, int cantidad, double costo, int stockMaximo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo = costo;
        this.stockMaximo = stockMaximo;
    }

    public Producto(long id, String nombre, int cantidad, double costo, int stockMaximo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo = costo;
        this.stockMaximo = stockMaximo;
    }

    private Integer stockMaximo;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(producto.costo, costo) == 0 && cantidad == producto.cantidad && id.equals(producto.id) && Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, costo, nombre, cantidad);
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    @Override
    public String getEstado() {
        if (this.cantidad < 10) {
            return "El producto " + this.nombre + " tiene solo " + this.cantidad + " items restantes";
        }

        if (this.stockMaximo == null) {
            return "El producto no tiene configurado el stock maximo";
        }

        if ((this.stockMaximo - this.cantidad) < 10) {
            return "Al producto " + this.nombre + " le faltan " + (this.stockMaximo - this.cantidad) + " items para alcanzar el stock maximo";
        }

        return "OK";
    }
}
