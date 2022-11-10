package ar.edu.unlam.tallerweb1.domain.cobros;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

import java.util.List;

public interface RepositorioMercadoPago {
    void guardar(MercadoPago preferencia);

    MercadoPago obtener(MercadoPago preferencia);

    MercadoPago obtener(Venta venta);

    MercadoPago obtener(String preference_id);

    void actualizar(MercadoPago preferencia);

    List<MercadoPago> obtenerLinksSegunVentas(List<Venta> ventas);
}
