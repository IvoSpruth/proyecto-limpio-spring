package ar.edu.unlam.tallerweb1.domain.ventas;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VentaProductoId implements Serializable {

        @Column(name = "venta_id")
        private Long ventaId;

        @Column(name = "producto_id")
        private Long productoId;

        private VentaProductoId() {}

        public VentaProductoId(
                Long ventaId,
                Long productoId) {
            this.ventaId = ventaId;
            this.productoId = productoId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass())
                return false;

            VentaProductoId that = (VentaProductoId) o;
            return Objects.equals(ventaId, that.ventaId) &&
                    Objects.equals(productoId, that.productoId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ventaId, productoId);
        }


}
