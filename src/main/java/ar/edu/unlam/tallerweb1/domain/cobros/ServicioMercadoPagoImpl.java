package ar.edu.unlam.tallerweb1.domain.cobros;

// SDK de Mercado Pago

import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServicioMercadoPagoImpl implements ServicioMercadoPago {


    ServicioProducto servicioProducto;
    RepositorioMercadoPago repositorioMercadoPago;

    public ServicioMercadoPagoImpl() {
    }

    @Autowired
    public ServicioMercadoPagoImpl(ServicioProducto servicioProducto, RepositorioMercadoPago repositorioMercadoPago) {
        this.servicioProducto = servicioProducto;
        this.repositorioMercadoPago = repositorioMercadoPago;
    }

    @Override
    public Preference crearLinkDePago(Venta venta) {
        MercadoPagoConfig.setAccessToken(MercadoPagoCredenciales.PRIVATE_ACCESS_TOKEN);

        PreferenceClient client = new PreferenceClient();

        // Crea un ítem en la preferencia
        List<PreferenceItemRequest> items = new ArrayList<>();

        Producto productoAux;
        //Creo que aca se rompe
        productoAux = servicioProducto.buscarProductoPorID((long) venta.getIdProducto());

        //TODO Reemplazar por un foreach
        PreferenceItemRequest Producto1 = PreferenceItemRequest.builder().title(productoAux.getNombre()).quantity(venta.getCantidadProducto()).unitPrice(BigDecimal.valueOf(productoAux.getCosto())).build();
        items.add(Producto1);

        productoAux = servicioProducto.buscarProductoPorID((long) venta.getIdProducto2());

        PreferenceItemRequest Producto2 = PreferenceItemRequest.builder().title(productoAux.getNombre()).quantity(venta.getCantidadProducto2()).unitPrice(BigDecimal.valueOf(productoAux.getCosto())).build();
        items.add(Producto2);

        //URLs de retorno
        String server = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success(server + "/ml")
                .pending(server + "/ml")
                .failure(server + "/ml")
                .build();

        PreferenceRequest request = PreferenceRequest.builder().items(items).externalReference(String.valueOf(venta.getId())).backUrls(backUrls).autoReturn("approved").build();

        try {
            Preference preferencia = client.create(request);
            guardarPreferencia(new MercadoPago(venta, preferencia));
            return preferencia;
        } catch (MPException e) {
            throw new RuntimeException(e);
        } catch (MPApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void guardarPreferencia(MercadoPago preferencia) {
        repositorioMercadoPago.guardar(preferencia);
    }

    @Override
    public MercadoPago obtener(Venta venta) {
        return repositorioMercadoPago.obtener(venta);
    }

    @Override
    public MercadoPago obtener(String preference_id) {return repositorioMercadoPago.obtener(preference_id);}

    @Override
    public void actualizarPreferencia(MercadoPago preferencia) {
        repositorioMercadoPago.actualizar(preferencia);
    }
}
