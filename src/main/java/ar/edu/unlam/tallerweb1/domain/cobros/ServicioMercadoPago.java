package ar.edu.unlam.tallerweb1.domain.cobros;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import com.mercadopago.resources.preference.Preference;

public interface ServicioMercadoPago {
    Preference crearLinkDePago(Venta venta);

    void guardarPreferencia(MercadoPago preferencia);

    MercadoPago obtener(Venta venta);

    MercadoPago obtener(String preference_id);

    void actualizarPreferencia(MercadoPago preferencia);
}
