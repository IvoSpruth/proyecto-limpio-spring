package ar.edu.unlam.tallerweb1.domain.cobros;

import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import com.mercadopago.resources.preference.Preference;

import java.util.List;

public interface ServicioMercadoPago {
    Preference crearLinkDePago(Venta venta);

    void guardarPreferencia(MercadoPago preferencia);

    MercadoPago obtener(Venta venta);

    MercadoPago obtener(String preference_id);

    void actualizarPreferencia(MercadoPago preferencia);


    List<MercadoPago> obtenerLinksSegunVentas(List<Venta> ventas);
}
