package ar.edu.unlam.tallerweb1.domain.cobros;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;

public interface RepositorioMercadoPago {
    void guardar(MercadoPago preferencia);

    MercadoPago obtener(MercadoPago preferencia);

    MercadoPago obtener(Venta venta);
}
