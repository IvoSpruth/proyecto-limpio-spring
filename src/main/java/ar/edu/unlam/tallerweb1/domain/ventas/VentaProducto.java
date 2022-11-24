package ar.edu.unlam.tallerweb1.domain.ventas;

import ar.edu.unlam.tallerweb1.domain.productos.Producto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "Venta_producto")
@Table(name = "venta_producto")
public class VentaProducto {

        @EmbeddedId
        private VentaProductoId id;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("ventaId")
        private Venta venta;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("productoId")
        private Producto producto;

        @Column(name = "created_on")
        private LocalDate createdOn = LocalDate.now();

        @Column(name = "cantidad")
        private int cantidad;

        private VentaProducto() {}

        public VentaProducto(Venta venta, Producto producto, int cantidad) {
            this.venta = venta;
            this.producto = producto;
            this.cantidad = cantidad;
            this.id = new VentaProductoId(venta.getId(), producto.getId());
        }

    public VentaProductoId getId() {
        return id;
    }

    public void setId(VentaProductoId id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass())
                return false;

            VentaProducto that = (VentaProducto) o;
            return Objects.equals(venta, that.venta) &&
                    Objects.equals(producto, that.producto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(venta, producto);
        }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
